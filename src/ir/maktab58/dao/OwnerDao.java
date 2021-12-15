package ir.maktab58.dao;

import ir.maktab58.dao.singletonsessionfactory.SessionUtil;
import ir.maktab58.models.Owner;
import ir.maktab58.models.factory.Account;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;

/**
 * @author Taban Soleymani
 */
public class OwnerDao extends BaseDaoInterfaceImpl<Owner> {
    public Owner findOwnerByUserAndPass(String username, String password) {
        Owner owner;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Owner> query = session.createQuery("FROM Owner owner WHERE owner.username=:username AND owner.password=:password", Owner.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            owner = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            owner = null;
        }
        return owner;
    }

    public Owner findOwnerByNationalCode(String nationalCode) {
        Owner owner;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Owner> query = session.createQuery("FROM Owner owner WHERE owner.nationalCode=:nationalCode", Owner.class);
            query.setParameter("nationalCode", nationalCode);
            owner = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            owner = null;
        }
        return owner;
    }

    public Owner findOwnerByAccount(Account account) {
        Owner owner;
        try {
            Session session = SessionUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query<Owner> query = session.createQuery("select owner FROM Owner owner join Account account on owner.id=account.owner.id " +
                    "where account.id=:account_id", Owner.class);
            query.setParameter("account_id", account.getId());
            owner = query.getSingleResult();
            transaction.commit();
            session.close();
        } catch (NoResultException e) {
            owner = null;
        }
        return owner;
    }
}
