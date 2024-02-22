package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "tb_alternativa_seq", allocationSize = 1)
@Entity
@Table(name = "tb_alternativa")
public class Alternative {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_alternativa_seq")
    private Long id;

    @Column(name = "alternativa")
    private String alternative;

    @Column(name = "correta")
    private Boolean itsCorrect;

    @Column(name = "letra-referencia")
    private String referenceLetter;


    @ManyToOne
    @JoinColumn(name = "id_questao")
    private Question question;

    @Column(name = "ativa")
    private Boolean isEnable;

}
