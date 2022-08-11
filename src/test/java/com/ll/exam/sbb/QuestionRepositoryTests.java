package com.ll.exam.sbb;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.internal.matchers.Equality;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuestionRepositoryTests {
    @Autowired
    private QuestionRepository questionRepository;

    @BeforeAll
    void beforeAll(){
        for (int i = 0; i<6; i++){
            Question q = new Question();
            q.setSubject("sbb가 무엇인가요?");
            q.setContent("sbb에 대해서 알고 싶습니다.");
            q.setCreateDate(LocalDateTime.now());
            questionRepository.save(q);
        }
    }

    @AfterAll
    void afterAll(){
        this.questionRepository.disableForeignKey();
        this.questionRepository.truncateQuestionTable();
        this.questionRepository.enableForeignKey();
    }

    @Test
    void testFindAll(){
        List<Question> questionList = questionRepository.findAll();
        assertEquals(questionList.size(), 6);
    }
}
