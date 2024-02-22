package com.example.controller;

import com.example.model.Alternative;
import com.example.model.Question;
import com.example.service.GameplayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/gameplay")
public class GameplayController {

    private final GameplayService gameplayService;

    @Autowired
    public GameplayController(GameplayService gameplayService) {
        this.gameplayService = gameplayService;
    }

    @GetMapping("/start")
    public ResponseEntity<Question> startGameplay() {
        Question question = gameplayService.startGameplay();
        return new ResponseEntity<>(question, HttpStatus.OK);
    }

    @GetMapping("/alternatives/{questionId}")
    public ResponseEntity<List<Alternative>> listPossiblesAlternatives(@PathVariable Long questionId) {
        List<Alternative> alternativeList = gameplayService.listPossiblesAlternatives(questionId);
        return new ResponseEntity<>(alternativeList, HttpStatus.OK);
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateSelectedAlternative(
            @RequestParam Long questionId,
            @RequestParam String userAlternative
    ) {
        String result = gameplayService.validateSelectedAlternative(questionId, userAlternative);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
