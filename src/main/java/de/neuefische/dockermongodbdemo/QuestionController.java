package de.neuefische.dockermongodbdemo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor

public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public Question createNewJoke(@RequestBody Question question) {
        return questionService.createNewQuestion(question);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Question> findQuestionbyId(@PathVariable String id){
        return ResponseEntity.of(questionService.findById(id));

    }

    @GetMapping()
    public List<Question> getAllQuestions (){
        return questionService.getAllQuestions();
    }


    @DeleteMapping("/{id}")
    public void deleteById (@PathVariable String id ){
        questionService.deleteById(id);
    }
}
