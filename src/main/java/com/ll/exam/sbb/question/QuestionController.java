package com.ll.exam.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "question_detail";
    }
}