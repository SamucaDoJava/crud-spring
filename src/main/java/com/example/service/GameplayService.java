package com.example.service;

import com.example.model.Alternative;
import com.example.model.Question;
import com.example.repository.AlternativeRepository;
import com.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameplayService {

    @Autowired
    AlternativeRepository alternativeRepository;

    @Autowired
    QuestionRepository questionRepository;


    //Primeira quest do seu rest lá na controller, você chama uma controller de nome start gameplay
    public Question startGameplay(){
        Question question = questionRepository.findRandonQuestion();
        return question;
    }

    //Aqui eu chamo outra request da controller gameplay que vai me listar as possiveis alternativas da questão
    //Lembre-se de colocar um DTO que não traz o campo isCorret para ter mais graça jogar sem frontend.
    public List<Alternative> listPossiblesAlternatives(Long questionId){
        List<Alternative> alternativeList = alternativeRepository.alternativesByQuestionId(questionId);
        return alternativeList;
    }

    //Valida se o usuário acertou a alternativa que foi passada
    public String validateSelectedAlternative(Long questionId, String userAlternative){
        Alternative correctAlternative = alternativeRepository.findCorrectAlternative(questionId);

        if(correctAlternative.getReferenceLetter().equals(userAlternative)){
            //TODO inserir aqui uma logica que guarda pontuação na tabela do usuário toda vez que passar por aqui.
            return "O usuário venceu uhull e vai ganhar 1000 pontos";
        } else {
            return "O usuário perdeu";
        }
    }

}
