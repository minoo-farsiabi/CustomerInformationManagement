package model.entity.old;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class RealCustomerEntity {
    private BigDecimal customerNumber;
    private String firstname;
    private String lastname;
    private String fathername;
    private Date dateOfBirth;
    private String nationalId;

    public BigDecimal getCustomerNumber() {
        return customerNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFathername() {
        return fathername;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getNationalId() {
        return nationalId;
    }

    public RealCustomerEntity(BigDecimal customerNumber, String firstname, String lastname, String fathername, Date dateOfBirth, String nationalId) {
        this.customerNumber = customerNumber;
        this.firstname = firstname;
        this.lastname = lastname;
        this.fathername = fathername;
        this.dateOfBirth = dateOfBirth;
        this.nationalId = nationalId;
    }
}
