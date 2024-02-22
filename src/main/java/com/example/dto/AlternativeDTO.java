package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AlternativeDTO {

    private Long id;
    private String alternative;
    private Boolean itsCorrect;
    private String referenceLetter;
    private QuestionDTO question;

}