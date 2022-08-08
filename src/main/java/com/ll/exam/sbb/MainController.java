package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {
    private int rs = -1;

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        return "안녕하세요 sbb에 오신것을 환영합니다.";
    }
    @GetMapping("/plus")
    @ResponseBody
    public int showPlus(int a, int b) {
        return a + b;
    }
    @GetMapping("/increase")
    @ResponseBody
    public String increase() {
        return """
                 <h1> %d</h1>
                 """.formatted(++rs);
    }

    @GetMapping("/saveSession/{name}/{value}")
    @ResponseBody
    public String saveSession(@PathVariable String name, @PathVariable String value, HttpServletRequest req) {
        HttpSession session = req.getSession();

        session.setAttribute(name, value);

        return "세션변수 %s의 값이 %s(으)로 설정되었습니다.".formatted(name, value);
    }

    @GetMapping("/getSession/{name}")
    @ResponseBody
    public String getSession(@PathVariable String name, HttpSession session) {
        String value = (String) session.getAttribute(name);

        return "세션변수 %s의 값이 %s 입니다.".formatted(name, value);
    }
}