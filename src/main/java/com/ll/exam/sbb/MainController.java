package com.ll.exam.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
    private int rs = -1;
    private int i = 0;
    private List<ArticleDto> articleDtos;
    private List<Person> personList;

    MainController(){
        articleDtos = new ArrayList<>();
        personList = new ArrayList<>();
    }

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

    @GetMapping("/addArticle")
    @ResponseBody
    public String addArticle(String title, String body) {
        i++;
        ArticleDto articleDto = new ArticleDto(i, title, body);
        articleDtos.add(articleDto);
        return "%d번 글이 등록됐습니다.".formatted(i);
    }

    @GetMapping("/article/{id}")
    @ResponseBody
    public ArticleDto getArticle( @PathVariable int id){
        return articleDtos.get(id-1);
    }

    @GetMapping("/modifyArticle")
    @ResponseBody
    public String modifyArticle(int id, String title, String body){
        ArticleDto articleDto = articleDtos.get(id-1);
        articleDto.setTitle(title);
        articleDto.setBody(body);
        return "%d번 글이 수정 됐습니다.".formatted(id);
    }

    @GetMapping("/addPerson/{id}")
    @ResponseBody
    public String addPerson(Person person){
        personList.add(person);
        return person.toString();
    }
}