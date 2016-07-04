package model.dao.old;

import model.entity.old.LegalCustomerEntity;
import model.entity.old.LegalCustomerEntity;
import util.old.DBUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.farsiabi on 6/21/2016.
 */
public class LegalCustomerDAO {
    private static LegalCustomerDAO ourInstance = new LegalCustomerDAO();

    public static LegalCustomerDAO getInstance() {
        return ourInstance;
    }

    private LegalCustomerDAO() {
    }

    public List<LegalCustomerEntity> getAllCustomers() {
        ResultSet resultSet = null;

        try {
            resultSet = DBUtil.getInstance().getStatement().executeQuery("select * from legalcustomer");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return writeResultToList(resultSet);
    }

    public List<LegalCustomerEntity> getAllCustomersWithFilters(String companyNameFilterText, String economicalIdFilterText, String legalCustomerNumberFilterText) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        try {
            companyNameFilterText = (companyNameFilterText == null) ? "" : companyNameFilterText;
            economicalIdFilterText = (economicalIdFilterText == null) ? "" : economicalIdFilterText;
            legalCustomerNumberFilterText = (legalCustomerNumberFilterText == null) ? "" : legalCustomerNumberFilterText;

            String query = "select * from legalCustomer where companyName like ? and economicalId like ? and legalCustomerNumber like ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setNString(1, "%" + companyNameFilterText + "%");
            preparedStatement.setNString(2, "%" + economicalIdFilterText + "%");
            preparedStatement.setNString(3, "%" + legalCustomerNumberFilterText + "%");
            System.out.println("query:" + query);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return writeResultToList(resultSet);
    }

    private List<LegalCustomerEntity> writeResultToList(ResultSet resultSet) {
        List<LegalCustomerEntity> allCustomers = new ArrayList<LegalCustomerEntity>();
        try {
            while (resultSet.next()) {
                allCustomers.add(new LegalCustomerEntity(new BigDecimal(resultSet.getString("legalCustomerNumber"))
                        , resultSet.getString("companyName"), Date.valueOf(resultSet.getString("dateOfConfirmation")), resultSet.getString("economicalId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    public void addCustomer(String companyName, Date dateOfConfirmation, String economicalId) {
        String legalInsertQuery = "insert into legalcustomer (SELECT LAST_INSERT_ID(),?, date ? , ?);";
        String customerInsertQuery = "insert into customer (customerNumber) value(NULL);";

        PreparedStatement legalInsertStatement;
        PreparedStatement customerInsertStatement;

        System.out.println("query:\n" + legalInsertQuery);

        try {
            DBUtil.getInstance().getConnect().setAutoCommit(false);

            customerInsertStatement = DBUtil.getInstance().getConnect().prepareStatement(customerInsertQuery);
            customerInsertStatement.executeUpdate();

            legalInsertStatement = DBUtil.getInstance().getConnect().prepareStatement(legalInsertQuery);
            legalInsertStatement.setNString(1, companyName);
            legalInsertStatement.setDate(2, dateOfConfirmation);
            legalInsertStatement.setNString(3, economicalId);
            legalInsertStatement.executeUpdate();

            DBUtil.getInstance().getConnect().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(BigDecimal customerNumber, String companyName, Date dateOfConfirmation, String economicalId) {
        PreparedStatement preparedStatement;
        String legalUpdateQuery = "update legalcustomer set companyName=?,dateOfConfirmation= date ?,economicalId=? where legalCustomerNumber=?";

        System.out.println("update query:\n" + legalUpdateQuery);

        try {
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(legalUpdateQuery);
            preparedStatement.setNString(1, companyName);
            preparedStatement.setDate(2, dateOfConfirmation);
            preparedStatement.setNString(3, economicalId);
            preparedStatement.setBigDecimal(4, customerNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LegalCustomerEntity findCustomerByCustomerNumber(BigDecimal customerNumber) {
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            String query = "select * from legalcustomer where legalCustomerNumber = ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setBigDecimal(1, customerNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new LegalCustomerEntity(new BigDecimal(resultSet.getString("legalCustomerNumber"))
                        , resultSet.getString("companyName"), Date.valueOf(resultSet.getString("dateOfConfirmation")), resultSet.getString("economicalId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public LegalCustomerEntity findCustomerByEconomicalId(String economicalId) {
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            String query = "select * from legalcustomer where economicalId = ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setNString(1, economicalId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new LegalCustomerEntity(new BigDecimal(resultSet.getString("legalCustomerNumber"))
                        , resultSet.getString("companyName"), Date.valueOf(resultSet.getString("dateOfConfirmation")), resultSet.getString("economicalId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteCustomer(BigDecimal customerNumber) {
        PreparedStatement preparedStatement;
        try {
            String query = "delete from customer where customerNumber = ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setBigDecimal(1, customerNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
