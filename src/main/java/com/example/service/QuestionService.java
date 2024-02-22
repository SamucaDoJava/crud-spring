package com.example.service;

import com.example.dto.QuestionDTO;
import com.example.mapper.QuestionMapper;
import com.example.model.Question;
import com.example.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public List<QuestionDTO> getAllQuestionDTOs() {
        List<Question> questions = questionRepository.findAll();
        return questionMapper.toListDTO(questions);
    }

    public Optional<QuestionDTO> getQuestionDTOById(Long id) {
        Optional<Question> questionOptional = questionRepository.findById(id);
        return questionOptional.map(questionMapper::toDTO);
    }

    public QuestionDTO saveQuestionDTO(QuestionDTO questionDTO) {
        Question question = questionMapper.toEntity(questionDTO);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toDTO(savedQuestion);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }


}
