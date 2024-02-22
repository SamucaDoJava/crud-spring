package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@SequenceGenerator(name = "tb_questao_seq", allocationSize = 1)
@Entity
@Table(name = "tb_questao")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_questao_seq")
    private Long id;

    @Column(name = "pergunta")
    private String question;

    @Column(name = "jogado")
    private boolean isPlayed;

    public Question(){

    }

    public Question(String question, Boolean isPlayed) {
        this.question = question;
        this.isPlayed = isPlayed;
    }

    public Question(String question) {
        this.question = question;
    }



}
