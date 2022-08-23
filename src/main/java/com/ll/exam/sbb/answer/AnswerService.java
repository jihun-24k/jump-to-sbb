package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.DataNotFoundException;
import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

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

    public Answer getAnswer(Long id){
        Optional<Answer> answer = this.answerRepository.findById(id);
        if (answer.isPresent()){
            return answer.get();
        }
        throw new DataNotFoundException("Answer가 존재하지 않습니다.");
    }

    public void delete(Answer answer) {
        this.answerRepository.delete(answer);
    }

    public void modify(Answer answer, String content) {
        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);
    }
}
