package model.entity.old;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/21/2016.
 */
public class LegalCustomerEntity {
    private BigDecimal customerNumber;
    private String companyName;
    private Date dateOfConfirmation;
    private String economicalId;

    public LegalCustomerEntity(BigDecimal customerNumber, String companyName, Date dateOfConfirmation, String economicalId) {
        this.customerNumber = customerNumber;
        this.companyName = companyName;
        this.dateOfConfirmation = dateOfConfirmation;
        this.economicalId = economicalId;
    }

    public BigDecimal getCustomerNumber() {
        return customerNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Date getDateOfConfirmation() {
        return dateOfConfirmation;
    }

    public String getEconomicalId() {
        return economicalId;
    }
}
