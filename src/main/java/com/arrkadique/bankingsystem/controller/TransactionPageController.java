package com.arrkadique.bankingsystem.controller;

import com.arrkadique.bankingsystem.entity.User;
import com.arrkadique.bankingsystem.entity.UserCard;
import com.arrkadique.bankingsystem.service.CardService;
import com.arrkadique.bankingsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/transactions")
@Slf4j
public class TransactionPageController {
    private final UserService userService;
    private final CardService cardService;

    public TransactionPageController(UserService userService, CardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }
    @GetMapping
    public String transaction(Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        User user = userService.getUserByUsername(currentPrincipalName);
        List<UserCard> userCardList = cardService.getAllCardsById(user.getId());

        model.put("cards", userCardList);
        return "transaction";
    }

    @PostMapping
    public String makeTransaction(String yourCard, String card, String sum, Map<String, Object> model){
        log.error(yourCard);
        cardService.makeTransaction(yourCard, card, Float.parseFloat(sum));

        return "redirect:/transactions";
    }
}
