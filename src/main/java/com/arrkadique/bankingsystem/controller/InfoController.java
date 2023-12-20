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
@RequestMapping("/info")
public class InfoController {
    private final UserService userService;
    private final CardService cardService;

    public InfoController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }

    @GetMapping
    public String info(Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);
        List<UserCard> userCardList = cardService.getAllCardsById(user.getId());

        model.put("username", user.getUsername());
        model.put("last_name", user.getLastName());
        model.put("phone_number", user.getPhoneNumber());
        model.put("cards", userCardList);
        model.put("credit", cardService.getCreditByUserId(user.getId()));
        return "info";
    }

    @PostMapping
    public String createCard(String number, String date, String cvv){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);

        cardService.createUserCard(number,date, cvv, user.getId());

        return "redirect:/info";
    }
}
