package com.arrkadique.bankingsystem.service;

import org.springframework.stereotype.Service;

@Service
public class HelperService {
    public String problemAnalyse(String text){
        if(text.contains("send")){
            return "redirect:/transactions";
        }
        if(text.contains("get")){
            return "redirect:/credit";
        }
        if(text.contains("info")){
            return "redirect:/info";
        }
        return "redirect:/helper";
    }
}
