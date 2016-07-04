package model.entity;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by m.farsiabi on 6/28/2016.
 */
public class LoanTypeEntity {

    private long loanTypeId;

    private String loanTypeName;

    private Set<GrantConditionEntity> grantConditions;

    private Set<LoanFileEntity> loanFileEntities;

    private int interestRate;

    public LoanTypeEntity(String loanTypeName, int interestRate) {

        this.loanTypeName = loanTypeName;
        this.interestRate = interestRate;
        this.grantConditions = new HashSet<GrantConditionEntity>();
    }

    public LoanTypeEntity() {
    }

    public void setLoanFileEntities(Set<LoanFileEntity> loanFileEntities) {
        this.loanFileEntities = loanFileEntities;
    }

    public Set<LoanFileEntity> getLoanFileEntities() {

        return loanFileEntities;
    }

    public void setGrantConditions(Set<GrantConditionEntity> grantConditions) {
        this.grantConditions = grantConditions;
    }

    public Set<GrantConditionEntity> getGrantConditions() {

        return grantConditions;
    }

    public void setLoanTypeId(long loanTypeId) {
        this.loanTypeId = loanTypeId;
    }

    public void setLoanTypeName(String loanTypeName) {
        this.loanTypeName = loanTypeName;
    }

    public void setInterestRate(int interestRate) {
        this.interestRate = interestRate;
    }

    public long getLoanTypeId() {

        return loanTypeId;
    }

    public String getLoanTypeName() {
        return loanTypeName;
    }

    public int getInterestRate() {

        return interestRate;
    }
}
