package com.example.mapper;

import com.example.dto.QuestionDTO;
import com.example.model.Question;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionMapper {

    private final ModelMapper modelMapper;

    public QuestionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public QuestionDTO toDTO(Question model) {
        return modelMapper.map(model, QuestionDTO.class);
    }

    public Question toEntity(QuestionDTO dto) {
        return modelMapper.map(dto, Question.class);
    }


    public List<QuestionDTO> toListDTO(List<Question> modelList) {
        return modelList.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    public List<Question> toList(List<QuestionDTO> dtosList) {
        return dtosList.stream()
                .map(this::toEntity).collect(Collectors.toList());
    }

}
