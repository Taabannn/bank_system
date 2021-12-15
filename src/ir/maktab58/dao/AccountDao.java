package ir.maktab58.dao;

import ir.maktab58.dao.singletonsessionfactory.SessionUtil;
import ir.maktab58.models.factory.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * @author Taban Soleymani
 */
public class AccountDao extends BaseDaoInterfaceImpl<Account> {
    public List<Account> findAccountsByOwnerId(int ownerId) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<Account> query = session.createQuery("FROM Account account WHERE account.owner.id=:ownerId", Account.class);
        query.setParameter("ownerId", ownerId);
        List<Account> accounts = query.getResultList();
        accounts.forEach(Account::getCard);
        transaction.commit();
        session.close();
        return accounts;
    }

    public Account findAccountsByOwnerIdAndAccountNumber(long accountNumber, int ownerId) {
        Account account;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Account> query = session.createQuery("FROM Account account WHERE account.accountNumber=:accountNumber AND account.owner.id=:ownerId", Account.class);
            query.setParameter("accountNumber", accountNumber);
            query.setParameter("ownerId", ownerId);
            account = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            account = null;
        }
        return account;
    }

    public Account findAccountByCardId(int cardId) {
        Account account;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Account> query = session.createQuery("FROM Account account WHERE account.card.id=:cardId", Account.class);
            query.setParameter("cardId", cardId);
            account = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            account = null;
        }
        return account;
    }
}
