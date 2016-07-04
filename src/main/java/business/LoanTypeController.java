package business;

import model.dao.LoanTypeDAO;
import model.entity.GrantConditionEntity;
import model.entity.LoanTypeEntity;
import org.apache.log4j.Logger;
import util.LoanUtil;

import java.util.List;

/**
 * Created by m.farsiabi on 6/28/2016.
 */
public class LoanTypeController {
    private static business.LoanTypeController ourInstance = new business.LoanTypeController();

    public static business.LoanTypeController getInstance() {
        return ourInstance;
    }

    public static final Logger logger = Logger.getLogger(LoanTypeController.class.getName());

    private LoanTypeController(){

    }

    public long addLoanType(String loanTypeName, int interestRate) {
        if (LoanTypeDAO.getInstance().getLoanTypeByName(loanTypeName) != null) {
            logger.error("Loan type with name: " + loanTypeName + " is redundant.");
            return LoanUtil.REDUNDANT_LOAN_TYPE;
        }
        if (getCurrentLoanTypeEntity().getGrantConditions().size() < 1) {
            logger.error("No grant was defined for loan with name:" + loanTypeName);
            return LoanUtil.EMPTY_GRANT_CONDITIONS;
        }
        logger.info("Loan type:" + loanTypeName + " has been added right now!");
        return LoanTypeDAO.getInstance().addLoanType(loanTypeName, interestRate);
    }

    public long addGrantConditionToLoanType(LoanTypeEntity loanTypeEntity, String grantConditionName, int minimumDurationDays, int maximumDurationDays, long minimumBalance, long maximumBalance) {
        return LoanTypeDAO.getInstance().addGrantConditionToLoanType(loanTypeEntity, grantConditionName, minimumDurationDays, maximumDurationDays, minimumBalance, maximumBalance);
    }

    public int addGrantConditionToCurrentLoanType (GrantConditionEntity grantConditionEntity) {
        if(hasGrantCondition(grantConditionEntity.getGrantConditionName())) {
            return LoanUtil.REDUNDANT_GRANT_CONDITION;
        }
        else {
            getCurrentLoanTypeEntity().getGrantConditions().add(grantConditionEntity);
            return 0;
        }
    }

    public long addLoanFileToLoanType(LoanTypeEntity loanTypeEntity, long customerNumber, int duration, long price) {
        int resultCode = -1;
        for (GrantConditionEntity grantConditionEntity : loanTypeEntity.getGrantConditions()) {
            if (price >= grantConditionEntity.getMinimumBalance() && price <= grantConditionEntity.getMaximumBalance() &&
                    duration >= grantConditionEntity.getMinimumDurationDays() && duration <= grantConditionEntity.getMaximumDurationDays()) {
                resultCode = 0;
                System.out.println("grant found!!!!");
                break;
            }
        }

        if (resultCode == 0) {
            logger.info("Loan file for customer " + customerNumber + "with duration " + duration + " and price " + price + " assigned to loan type " + loanTypeEntity.getLoanTypeName());
        } else {
            logger.warn("No grant found for customer " + customerNumber + "with duration " + duration + " and price " + price + " using loan type " + loanTypeEntity.getLoanTypeName());
        }
        return (resultCode == 0) ? LoanTypeDAO.getInstance().addLoanFileToLoanType(loanTypeEntity, customerNumber, duration, price) : LoanUtil.NO_GRANT_FITS;
    }

    public LoanTypeEntity getLoanTypeById(long loanTypeId) {
        LoanTypeEntity loanTypeEntity = LoanTypeDAO.getInstance().getLoanTypeById(loanTypeId);
        if (loanTypeEntity == null) {
            logger.error("There is no loan type with id:" + loanTypeId);
        } else {
            logger.info("Loan type with id " + loanTypeId + " was found!");
        }
        return loanTypeEntity;
    }

    public LoanTypeEntity getLoanTypeByName(String loanTypeName) {
        LoanTypeEntity loanTypeEntity = LoanTypeDAO.getInstance().getLoanTypeByName(loanTypeName);
        if (loanTypeEntity == null) {
            logger.error("There is no loan type with name:" + loanTypeName);
        } else {
            logger.info("Loan type with name " + loanTypeName + " was found!");
        }
        return loanTypeEntity;
    }

    public List getAllLoans() {
        return LoanTypeDAO.getInstance().getAllLoans();
    }

    public boolean hasGrantCondition(String grantConditionName) {
        if (LoanUtil.getCurrentLoanTypeEntity().getGrantConditions() == null)
            return false;
        for (GrantConditionEntity grantConditionEntity : LoanUtil.getCurrentLoanTypeEntity().getGrantConditions()) {
            if (grantConditionEntity.getGrantConditionName().equals(grantConditionName)) {
                return true;
            }
        }
        return false;
    }

    public LoanTypeEntity getCurrentLoanTypeEntity() {
        return LoanUtil.getCurrentLoanTypeEntity();
    }

    public void setCurrentLoanTypeEntity(LoanTypeEntity currentLoanTypeEntity) {
        LoanUtil.setCurrentLoanTypeEntity(currentLoanTypeEntity);
    }

}
