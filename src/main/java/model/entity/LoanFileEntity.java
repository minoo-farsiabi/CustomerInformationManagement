package model.entity;

/**
 * Created by m.farsiabi on 6/28/2016.
 */
public class LoanFileEntity {
    private long loanFileId;

    private LoanTypeEntity loanType;

    private long loanFileOwnerId;

    private int durationInDays;

    private long price;

    public LoanFileEntity() {
    }

    public void setLoanFileOwnerId(long loanFileOwnerId) {
        this.loanFileOwnerId = loanFileOwnerId;
    }

    public long getLoanFileOwnerId() {

        return loanFileOwnerId;
    }

    public void setLoanFileId(long loanFileId) {
        this.loanFileId = loanFileId;
    }

    public void setLoanType(LoanTypeEntity loanType) {
        this.loanType = loanType;
    }

    public long getLoanFileId() {

        return loanFileId;
    }

    public LoanTypeEntity getLoanType() {
        return loanType;
    }

    public void setDurationInDays(int durationInDays) {
        this.durationInDays = durationInDays;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getDurationInDays() {

        return durationInDays;
    }

    public long getPrice() {
        return price;
    }

    public LoanFileEntity(LoanTypeEntity loanType, long loanFileOwnerId, int durationInDays, long price) {

        this.loanType = loanType;
        this.loanFileOwnerId = loanFileOwnerId;
        this.durationInDays = durationInDays;
        this.price = price;
    }
}
