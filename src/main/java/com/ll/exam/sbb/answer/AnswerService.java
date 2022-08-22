package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser siteUser) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        this.answerRepository.save(answer);
    }
}
