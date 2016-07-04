package model.dao;

import model.entity.GrantConditionEntity;
import model.entity.LoanFileEntity;
import model.entity.LoanTypeEntity;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import util.LoanUtil;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by m.farsiabi on 6/28/2016.
 */
public class LoanTypeDAO {
    private static LoanTypeDAO ourInstance = new LoanTypeDAO();

    public static LoanTypeDAO getInstance() {
        return ourInstance;
    }

    public long addLoanType(String loanTypeName, int interestRate) {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = null;
        long loanTypeId = -1;
        try {
            transaction = session.beginTransaction();
            LoanTypeEntity loanTypeEntity = new LoanTypeEntity(loanTypeName,interestRate);
            loanTypeId = (Long)session.save(loanTypeEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        session.close();

        System.out.println("loan Type id:" + loanTypeId);

        return loanTypeId;
    }

    public long addGrantConditionToLoanType (LoanTypeEntity loanTypeEntity,String grantConditionName, int minimumDurationDays, int maximumDurationDays, long minimumBalance, long maximumBalance) {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = null;
        long grantConditionId = -1;
        try {
            transaction = session.beginTransaction();
            GrantConditionEntity grantConditionEntity = new GrantConditionEntity(grantConditionName,minimumDurationDays,maximumDurationDays,minimumBalance,maximumBalance);
            grantConditionEntity.setLoanType(loanTypeEntity);
            loanTypeEntity.getGrantConditions().add(grantConditionEntity);
            grantConditionId = (Long)session.save(grantConditionEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        session.close();

        System.out.println("grant condition id:" + loanTypeEntity);

        return grantConditionId;
    }

    public long addLoanFileToLoanType (LoanTypeEntity loanTypeEntity,long customerNumber, int duration, long price) {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = null;
        long loanFileEntityId = -1;
        try {
            transaction = session.beginTransaction();
            LoanFileEntity loanFileEntity = new LoanFileEntity(loanTypeEntity,customerNumber,duration,price);
            loanTypeEntity.getLoanFileEntities().add(loanFileEntity);
            loanFileEntityId = (Long)session.save(loanFileEntity);
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        session.close();

        System.out.println("loan file entity id:" + loanFileEntityId);

        return loanFileEntityId;
    }

    public LoanTypeEntity getLoanTypeById (long loanTypeId) {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LoanTypeEntity.class);

        criteria.add(Restrictions.eq("loanTypeId", loanTypeId));

        if (criteria.list().size() > 0) {
            return (LoanTypeEntity) criteria.list().get(0);
        }
        else {
            return null;
        }
    }

    public LoanTypeEntity getLoanTypeByName (String loanTypeName) {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(LoanTypeEntity.class);

        criteria.add(Restrictions.eq("loanTypeName", loanTypeName));

        if (criteria.list().size() > 0) {
            return (LoanTypeEntity) criteria.list().get(0);
        }
        else {
            return null;
        }
    }

    public List getAllLoans () {
        Session session = LoanUtil.getInstance().getSessionFactory().openSession();
        Transaction transaction = null;
        List allLoans = null;
        try {
            transaction = session.beginTransaction();
             allLoans = session.createQuery("from LoanTypeEntity").list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        session.close();

        return allLoans;
    }
}
