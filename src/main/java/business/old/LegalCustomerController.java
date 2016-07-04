package business.old;

import model.dao.old.LegalCustomerDAO;
import model.entity.old.LegalCustomerEntity;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

/**
 * Created by m.farsiabi on 6/21/2016.
 */
public class LegalCustomerController {
    private static LegalCustomerController ourInstance = new LegalCustomerController();

    public static LegalCustomerController getInstance() {
        return ourInstance;
    }

    private LegalCustomerController() {
    }

    public List<LegalCustomerEntity> getAllCustomers() {
        return LegalCustomerDAO.getInstance().getAllCustomers();
    }

    public List<LegalCustomerEntity> getAllCustomersWithFilters(String companyNameFilterText, String economicalIdFilterText, String legalCustomerNumberFilterText) {
        return LegalCustomerDAO.getInstance().getAllCustomersWithFilters(companyNameFilterText, economicalIdFilterText, legalCustomerNumberFilterText);
    }

    public int addCustomer(String companyName, Date dateOfConfirmation, String economicalId) {
        if (LegalCustomerController.getInstance().findCustomerByEconomicalId(economicalId) == null) {
            LegalCustomerDAO.getInstance().addCustomer(companyName, dateOfConfirmation, economicalId);
            return 0;
        }
        else {
            return 1;
        }
    }

    public LegalCustomerEntity findCustomerByEconomicalId(String economicalId) {
        return LegalCustomerDAO.getInstance().findCustomerByEconomicalId(economicalId);
    }

    public LegalCustomerEntity findCustomerByCustomerNumber(BigDecimal customerNumber) {
        return LegalCustomerDAO.getInstance().findCustomerByCustomerNumber(customerNumber);
    }

    public void editCustomer(BigDecimal customerNumber, String companyName, Date dateOfConfirmation, String economicalId) {
        LegalCustomerDAO.getInstance().editCustomer(customerNumber, companyName, dateOfConfirmation, economicalId);
    }

    public void deleteCustomer(BigDecimal customerNumber) {
        LegalCustomerDAO.getInstance().deleteCustomer(customerNumber);
    }
}
