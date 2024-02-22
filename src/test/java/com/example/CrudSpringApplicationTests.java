package com.example;

import com.example.model.Alternative;
import com.example.model.Question;
import com.example.repository.AlternativeRepository;
import com.example.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CrudSpringApplicationTests {

	@Autowired
	AlternativeRepository alternativeRepository;

	@Autowired
	QuestionRepository questionRepository;


	@Test
	void gameplayIntegration(){
		//Passo 1 pegando a questão randomica.
		Question question = startGameplay();
		System.out.println("Questão: " + question);
		System.out.println("Pause");


		//Passo 2 Listando possiveis alternativas da questão
		List<Alternative> alternativeList = listPossiblesAlternatives(question.getId());
		for(Alternative alternative:alternativeList) {
			System.out.println(alternative.getReferenceLetter() + " " + alternative.getAlternative());
		}
		System.out.println("Pause");

		//Passo 3 Validação das alternativas;
		String validationMessage = validateSelectedAlternative(question.getId(), "B");

		System.out.println("Fim do jogo: " + validationMessage);
		System.out.println("Pause");
	}


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

	private void createAlternativeForTests(){
		Question question = new Question("Quem é Hacker Da sala");
		questionRepository.save(question);

		Alternative alternativeA = new Alternative(null, "Paula Pedrosa", true, "A", question, false);
		Alternative alternativeB = new Alternative(null, "Robson", false, "B", question, false);
		Alternative alternativeC = new Alternative(null, "Val Jr", false, "C", question, false);
		Alternative alternativeD = new Alternative(null, "Larissa Perdigão", false, "D", question, false);

		alternativeRepository.save(alternativeA);
		alternativeRepository.save(alternativeB);
		alternativeRepository.save(alternativeC);
		alternativeRepository.save(alternativeD);
	}

}
