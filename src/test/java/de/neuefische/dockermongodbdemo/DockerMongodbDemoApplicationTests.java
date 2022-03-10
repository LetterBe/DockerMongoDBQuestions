package de.neuefische.dockermongodbdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DockerMongodbDemoApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
		Question question = new Question();
		question.setQuestion("is forró tipically played with triangolo?");
		question.setAnswer("yes");

		ResponseEntity<Question> questionResponse = restTemplate.postForEntity("/api/questions", question, Question.class);
		assertThat(questionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(questionResponse.getBody().getQuestion()).isEqualTo("is forró tipically played with triangolo?");
		assertThat(questionResponse.getBody().getAnswer()).isEqualTo("yes");
		assertThat(questionResponse.getBody().getId()).isNotNull();
	}

    @Test
	void contextDelete (){
		Question question = new Question();
		question.setQuestion("is forró tipically played with triangolo?");
		question.setAnswer("yes");


		ResponseEntity<Question> questionResponse = restTemplate.postForEntity("/api/questions", question, Question.class);
		assertThat(questionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(questionResponse.getBody().getQuestion()).isEqualTo("is forró tipically played with triangolo?");
		assertThat(questionResponse.getBody().getAnswer()).isEqualTo("yes");
		assertThat(questionResponse.getBody().getId()).isNotNull();

		ResponseEntity<Void> questionDelete =  restTemplate.exchange("/api/questions/" + questionResponse.getBody().getId(), HttpMethod.DELETE,null,Void.class );
		assertThat(questionDelete.getStatusCode()).isEqualTo(HttpStatus.OK);

		ResponseEntity<Question> questionGet = restTemplate.getForEntity ("/api/questions/" + questionResponse.getBody().getId(), Question.class);
		assertThat(questionGet.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
	}

}
