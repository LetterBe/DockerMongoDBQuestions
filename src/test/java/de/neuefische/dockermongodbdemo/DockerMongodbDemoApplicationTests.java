package de.neuefische.dockermongodbdemo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
		question.setId("2");

		ResponseEntity<Question> questionResponse = restTemplate.postForEntity("/api/questions", question, Question.class);
		assertThat(questionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(questionResponse.getBody().getQuestion()).isEqualTo("is forró tipically played with triangolo?");
		assertThat(questionResponse.getBody().getQuestion()).isEqualTo("x");
	}



}
