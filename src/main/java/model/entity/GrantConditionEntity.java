package model.entity;

import java.math.BigDecimal;

/**
 * Created by m.farsiabi on 6/28/2016.
 */

public class GrantConditionEntity {

    private long grantConditionId;

    private String grantConditionName;

    private int minimumDurationDays;

    private int maximumDurationDays;

    private long minimumBalance;

    private long maximumBalance;

    private LoanTypeEntity loanType;

    public GrantConditionEntity(String grantConditionName, int minimumDurationDays, int maximumDurationDays, long minimumBalance, long maximumBalance) {

        this.grantConditionName = grantConditionName;
        this.minimumDurationDays = minimumDurationDays;
        this.maximumDurationDays = maximumDurationDays;
        this.minimumBalance = minimumBalance;
        this.maximumBalance = maximumBalance;
    }

    public GrantConditionEntity() {
    }

    public void setLoanType(LoanTypeEntity loanType) {
        this.loanType = loanType;
    }

    public void setGrantConditionId(long grantConditionId) {
        this.grantConditionId = grantConditionId;
    }

    public void setGrantConditionName(String grantConditionName) {
        this.grantConditionName = grantConditionName;
    }

    public void setMinimumDurationDays(int minimumDurationDays) {
        this.minimumDurationDays = minimumDurationDays;
    }

    public void setMaximumDurationDays(int maximumDurationDays) {
        this.maximumDurationDays = maximumDurationDays;
    }

    public void setMinimumBalance(long minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public void setMaximumBalance(long maximumBalance) {
        this.maximumBalance = maximumBalance;
    }

    public long getGrantConditionId() {

        return grantConditionId;
    }

    public String getGrantConditionName() {
        return grantConditionName;
    }

    public int getMinimumDurationDays() {
        return minimumDurationDays;
    }

    public int getMaximumDurationDays() {
        return maximumDurationDays;
    }

    public long getMinimumBalance() {
        return minimumBalance;
    }

    public long getMaximumBalance() {
        return maximumBalance;
    }

    public LoanTypeEntity getLoanType() {
        return loanType;
    }
}
