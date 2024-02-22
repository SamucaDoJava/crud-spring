package com.example.service;

import com.example.dto.AlternativeDTO;
import com.example.mapper.AlternativeMapper;
import com.example.model.Alternative;
import com.example.repository.AlternativeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlternativeService {

    private final AlternativeRepository alternativeRepository;
    private final AlternativeMapper alternativeMapper;

    @Autowired
    public AlternativeService(AlternativeRepository alternativeRepository, AlternativeMapper alternativeMapper) {
        this.alternativeRepository = alternativeRepository;
        this.alternativeMapper = alternativeMapper;
    }

    public List<AlternativeDTO> getAllAlternativeDTOs() {
        List<Alternative> alternatives = alternativeRepository.findAll();
        return alternativeMapper.toListDTO(alternatives);
    }

    public Optional<AlternativeDTO> getAlternativeDTOById(Long id) {
        Optional<Alternative> alternativeOptional = alternativeRepository.findById(id);
        return alternativeOptional.map(alternativeMapper::toDTO);
    }

    public AlternativeDTO saveAlternativeDTO(AlternativeDTO alternativeDTO) {
        Alternative alternative = alternativeMapper.toEntity(alternativeDTO);
        Alternative savedAlternative = alternativeRepository.save(alternative);
        return alternativeMapper.toDTO(savedAlternative);
    }

    public void deleteAlternative(Long id) {
        alternativeRepository.deleteById(id);
    }

}
