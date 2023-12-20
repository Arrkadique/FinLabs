package com.arrkadique.bankingsystem.service;

import com.arrkadique.bankingsystem.dto.CreditDto;
import com.arrkadique.bankingsystem.entity.Credit;
import com.arrkadique.bankingsystem.entity.UserCard;
import com.arrkadique.bankingsystem.repository.CardBalanceRepository;
import com.arrkadique.bankingsystem.repository.CreditRepository;
import com.arrkadique.bankingsystem.repository.UserCardsRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CardService {
    final CardBalanceRepository cardBalanceRepository;
    final UserCardsRepository userCardsRepository;
    final CreditRepository creditRepository;

    public CardService(CardBalanceRepository cardBalanceRepository, UserCardsRepository userCardsRepository, CreditRepository creditRepository) {
        this.cardBalanceRepository = cardBalanceRepository;
        this.userCardsRepository = userCardsRepository;
        this.creditRepository = creditRepository;
    }

    public void createUserCard(String cardNumber, String cardDate, String cvv, Long userId){
        UserCard userCard = new UserCard(userId, cardNumber, cardDate, cvv, (float)0);
        userCardsRepository.save(userCard);
    }

    public List<UserCard> getAllCardsById(Long id){
        return userCardsRepository.findAllByUserId(id);
    }

    public void deleteUserCard(Long cardId){
        userCardsRepository.deleteById(cardId);
        cardBalanceRepository.deleteByCardId(cardId);
    }

    @Transactional
    public void makeTransaction(String userCardNumberFrom, String userCardNumberTo, float sum){
        UserCard userCardFrom = userCardsRepository.findByCardNumber(userCardNumberFrom);
        UserCard userCardTo = userCardsRepository.findByCardNumber(userCardNumberTo);
        log.error(userCardFrom.getBalance() + "  qweqe  " + userCardTo.getBalance());
        float balance = userCardFrom.getBalance() - sum;
        userCardFrom.setBalance(balance);
        balance = userCardTo.getBalance() + sum;
        userCardTo.setBalance(balance);
        userCardsRepository.save(userCardFrom);
        userCardsRepository.save(userCardTo);
    }

    @Transactional
    public void getCredit(String date, String card, String sum, String percent, Long userId){
        UserCard card1 = userCardsRepository.findByCardNumber(card);
        card1.setBalance(card1.getBalance() + Float.parseFloat(sum));
        userCardsRepository.save(card1);
        log.error(card1.getId() + " ");
        Credit credit = new Credit(Float.parseFloat(sum), Integer.parseInt(date),
                Float.parseFloat(percent), userId,
                card1.getId());
        creditRepository.save(credit);

    }

    public List<CreditDto> getCreditByUserId(Long userId){
        List<CreditDto> list = new ArrayList<>();
        double st, down,dr, ps;
        for (Credit c: creditRepository.findAllByUserId(userId)) {
            ps = c.getPercent()/(100*12);
            st = -c.getPaymentDate();
            down = Math.pow((1 + ps), st);
            dr = ps/(1 - down);
            list.add(new CreditDto(String.valueOf(c.getCreditSum() * dr),
                    String.valueOf(c.getCreditSum()),
                    String.valueOf(c.getPercent()), String.valueOf(c.getPaymentDate())));
        }
        return list;
    }

}
