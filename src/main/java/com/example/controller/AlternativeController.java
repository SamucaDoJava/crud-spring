package com.example.controller;

import com.example.dto.AlternativeDTO;
import com.example.service.AlternativeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alternatives")
public class AlternativeController {

    private final AlternativeService alternativeService;

    @Autowired
    public AlternativeController(AlternativeService alternativeService) {
        this.alternativeService = alternativeService;
    }

    @GetMapping
    public ResponseEntity<List<AlternativeDTO>> getAllAlternatives() {
        List<AlternativeDTO> alternatives = alternativeService.getAllAlternativeDTOs();
        return new ResponseEntity<>(alternatives, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlternativeDTO> getAlternativeById(@PathVariable Long id) {
        Optional<AlternativeDTO> alternativeDTO = alternativeService.getAlternativeDTOById(id);
        return alternativeDTO.map(dto -> new ResponseEntity<>(dto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<AlternativeDTO> createAlternative(@RequestBody AlternativeDTO alternativeDTO) {
        AlternativeDTO createdAlternativeDTO = alternativeService.saveAlternativeDTO(alternativeDTO);
        return new ResponseEntity<>(createdAlternativeDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlternative(@PathVariable Long id) {
        alternativeService.deleteAlternative(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
