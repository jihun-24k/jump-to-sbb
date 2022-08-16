package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class QuestionController
{
    private final QuestionService questionService;

    @RequestMapping("/")
    public String root() {
        return "redirect:/question/list";
    }

    @RequestMapping("/question/list")
    public String showList(Model model) {
        List<Question> questionList = questionService.findAll();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }

    @RequestMapping(value = "/question/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }
    @GetMapping("/question/create")
    public String questionCreate(){
        return "question_form";
    }
    @PostMapping("/question/post")
    public String questionCreate(@RequestParam String subject, @RequestParam String content) {
        this.questionService.create(subject, content);
        return "redirect:/question/list"; // 질문 저장후 질문목록으로 이동
    }
}