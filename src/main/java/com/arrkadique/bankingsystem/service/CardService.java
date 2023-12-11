package com.arrkadique.bankingsystem.service;

import com.arrkadique.bankingsystem.annotation.Loggable;
import com.arrkadique.bankingsystem.entity.CardBalance;
import com.arrkadique.bankingsystem.entity.UserCard;
import com.arrkadique.bankingsystem.repository.CardBalanceRepository;
import com.arrkadique.bankingsystem.repository.UserCardsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CardService {
    final CardBalanceRepository cardBalanceRepository;
    final UserCardsRepository userCardsRepository;

    public CardService(CardBalanceRepository cardBalanceRepository, UserCardsRepository userCardsRepository) {
        this.cardBalanceRepository = cardBalanceRepository;
        this.userCardsRepository = userCardsRepository;
    }

    @Loggable
    public UserCard createUserCard(String cardNumber, Date cardDate, String cvv, Long userId){
        UserCard newCard = userCardsRepository.save(new UserCard(userId, cardNumber, cardDate, cvv));
        cardBalanceRepository.save(new CardBalance(newCard.getUserId()));
        return newCard;
    }

    public void deleteUserCard(Long cardId){
        userCardsRepository.deleteById(cardId);
        cardBalanceRepository.deleteByCardId(cardId);
    }

    @Transactional
    public String makeTransaction(Long userIdTo, String userCardTo, float sum){
        //TODO: Authorised user card in from
        CardBalance cardBalanceFrom = cardBalanceRepository
                .findByCardId(userCardsRepository.findByCardNumber(userCardTo).getId());
        CardBalance cardBalanceTo = cardBalanceRepository
                .findByCardId(userCardsRepository.findByCardNumber(userCardTo).getId());
        cardBalanceFrom.setBalance(cardBalanceFrom.getBalance() - sum);
        cardBalanceTo.setBalance(cardBalanceTo.getBalance() + sum);
        cardBalanceRepository.save(cardBalanceFrom);
        cardBalanceRepository.save(cardBalanceTo);
        return "Transaction successful";
    }


}
