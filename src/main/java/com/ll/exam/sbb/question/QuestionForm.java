package com.ll.exam.sbb.question;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수 입니다.")
    @Size(max = 20)
    private String subject;

    @NotEmpty(message = "내용은 필수 입니다.")
    private String content;
}
