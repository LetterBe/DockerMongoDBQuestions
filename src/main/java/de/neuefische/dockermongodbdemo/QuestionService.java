package de.neuefische.dockermongodbdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question createNewQuestion(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> findById(String id) {
        return questionRepository.findById(id);
    }

    public List<Question> getAllQuestions () {
        return questionRepository.findAll();
    }

    public void deleteById(String id) {
        questionRepository.deleteById(id);
    }
}
