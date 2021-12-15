package ir.maktab58.dao;

import ir.maktab58.dao.singletonsessionfactory.SessionUtil;
import ir.maktab58.models.BankTransaction;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * @author Taban Soleymani
 */
public class BankTransactionDao extends BaseDaoInterfaceImpl<BankTransaction> {
    public List<BankTransaction> findTransactionsByAccountID(int accountId) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query<BankTransaction> query = session.createQuery("FROM BankTransaction transaction WHERE transaction.account.id=:accountId", BankTransaction.class);
        query.setParameter("accountId", accountId);
        List<BankTransaction> transactions = query.getResultList();
        transaction.commit();
        session.close();
        return transactions;
    }

    public void removeOldTransaction(BankTransaction oldTransaction, int accountId) {
        Session session = SessionUtil.getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("delete from BankTransaction transaction WHERE transaction.id=:transactionId AND transaction.account.id=:accountId");
        query.setParameter("transactionId", oldTransaction.getId());
        query.setParameter("accountId", accountId);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }
}
