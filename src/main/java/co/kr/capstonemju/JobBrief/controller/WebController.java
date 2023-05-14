package co.kr.capstonemju.JobBrief.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/")
    public String vue(){
        return "vue/index";
    }
}
