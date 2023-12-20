package com.arrkadique.bankingsystem.controller;

import com.arrkadique.bankingsystem.service.HelperService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helper")
public class HelperController {

    private final HelperService helperService;

    public HelperController(HelperService helperService) {
        this.helperService = helperService;
    }

    @GetMapping
    public String getHelper(){
        return "helper";
    }

    @PostMapping
    public String solveProblem(String text){
        return helperService.problemAnalyse(text);
    }
}
