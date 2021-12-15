package ir.maktab58.dao;

import ir.maktab58.dao.singletonsessionfactory.SessionUtil;
import ir.maktab58.models.Card;
import ir.maktab58.models.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * @author Taban Soleymani
 */
public class CardDao extends BaseDaoInterfaceImpl<Card> {
    public Card findCardByCardNumber(long destCardNumber) {
        Card card;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Card> query = session.createQuery("FROM Card card WHERE card.cardNumber=:destCardNumber", Card.class);
            query.setParameter("destCardNumber", destCardNumber);
            card = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            card = null;
        }
        return card;
    }
}
