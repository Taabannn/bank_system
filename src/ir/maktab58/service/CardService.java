package ir.maktab58.service;

import ir.maktab58.dao.CardDao;
import ir.maktab58.models.Card;
import ir.maktab58.models.factory.Account;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Random;

/**
 * @author Taban Soleymani
 */
public class CardService {
    private final CardDao cardDao = new CardDao();
    public void generateACard(Account account) {
        Random random = new Random();
        long cardNumber = 1000000000 + random.nextLong(1000000000);
        int cvv2 = 5000 + random.nextInt(5000);
        LocalDate creationLocalDate = account.getCreationDate().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        Date expiration = Date.from(creationLocalDate.plusYears(4)
                .atStartOfDay(ZoneId.systemDefault()).toInstant());
        Card card = Card.builder()
                .withCardNumber(cardNumber)
                .withCvv2(cvv2)
                .withExpirationDate(expiration).build();
        //cardDao.save(card);
        account.setCard(card);
    }
}
