package com.arrkadique.bankingsystem.controller;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.entity.UserCard;
import com.arrkadique.bankingsystem.service.CardService;
import com.arrkadique.bankingsystem.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/credit")
public class CreditController {
    private final UserService userService;
    private final CardService cardService;

    public CreditController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping
    public String credit(Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);
        List<UserCard> userCardList = cardService.getAllCardsById(user.getId());

        model.put("cards", userCardList);
        return "credit";
    }

    @PostMapping
    public String getCredit(String date, String card, String sum, String percent, Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);
        cardService.getCredit(date,card,sum,percent, user.getId());

        return "redirect:/credit";
    }
}
