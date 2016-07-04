package util;

import model.entity.LoanTypeEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by m.farsiabi on 6/28/2016.
 */
public class LoanUtil {
    private static LoanUtil ourInstance = new LoanUtil();

    private static SessionFactory sessionFactory;

    private static LoanTypeEntity currentLoanTypeEntity;

    public static int REDUNDANT_LOAN_TYPE = -1;
    public static int REDUNDANT_LOAN_FILE = -2;
    public static int REDUNDANT_GRANT_CONDITION = -3;
    public static int EMPTY_GRANT_CONDITIONS = -4;
    public static int NO_GRANT_FITS = -5;

    public static LoanTypeEntity getCurrentLoanTypeEntity() {
        return currentLoanTypeEntity;
    }

    public static void setCurrentLoanTypeEntity(LoanTypeEntity currentLoanTypeEntity) {
        LoanUtil.currentLoanTypeEntity = currentLoanTypeEntity;
    }

    public static LoanUtil getInstance() {
        return ourInstance;
    }

    private LoanUtil() {
    }

    public SessionFactory getSessionFactory() {
        if (sessionFactory != null)
            return sessionFactory;
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(
                configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        return sessionFactory;
    }
}
