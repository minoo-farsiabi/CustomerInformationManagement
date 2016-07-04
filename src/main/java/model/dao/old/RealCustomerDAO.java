package model.dao.old;

import model.entity.old.RealCustomerEntity;
import model.entity.old.RealCustomerEntity;
import util.old.DBUtil;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by m.farsiabi on 6/20/2016.
 */
public class RealCustomerDAO {
    private static RealCustomerDAO ourInstance = new RealCustomerDAO();

    public static RealCustomerDAO getInstance() {
        return ourInstance;
    }

    private RealCustomerDAO() {
    }

    public void initializeConnection() {

    }

    public List<RealCustomerEntity> getAllCustomers() {
        ResultSet resultSet = null;
        try {
            resultSet = DBUtil.getInstance().getStatement().executeQuery("select * from realcustomer");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return writeResultToList(resultSet);
    }

    public List<RealCustomerEntity> getAllCustomersWithFilters(String firstNameFilterText, String lastNameFilterText, String nationalIdFilterText, String realCustomerNumberFilterText) {
        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        try {
            firstNameFilterText = (firstNameFilterText == null) ? "" : firstNameFilterText;
            lastNameFilterText = (lastNameFilterText == null) ? "" : lastNameFilterText;
            nationalIdFilterText = (nationalIdFilterText == null) ? "" : nationalIdFilterText;
            realCustomerNumberFilterText = (realCustomerNumberFilterText == null) ? "" : realCustomerNumberFilterText;

            String query = "select * from realCustomer where firstname like ? and lastname like ? and nationalId like ? and realCustomerNumber like ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setNString(1, "%" + firstNameFilterText + "%");
            preparedStatement.setNString(2, "%" + lastNameFilterText + "%");
            preparedStatement.setNString(3, "%" + nationalIdFilterText + "%");
            preparedStatement.setNString(4, "%" + realCustomerNumberFilterText + "%");
            System.out.println("query:" + query);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return writeResultToList(resultSet);
    }

    private List<RealCustomerEntity> writeResultToList(ResultSet resultSet) {
        List<RealCustomerEntity> allCustomers = new ArrayList<RealCustomerEntity>();
        try {
            while (resultSet.next()) {
                allCustomers.add(new RealCustomerEntity(new BigDecimal(resultSet.getString("realCustomerNumber"))
                        , resultSet.getString("firstname"), resultSet.getString("lastname"),
                        resultSet.getString("fathername"), Date.valueOf(resultSet.getString("dateOfBirth")),
                        resultSet.getString("nationalId")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    public void addCustomer(String firstName, String lastName, String fatherName, Date dateOfBirth, String nationalId) {
        String realInsertQuery = "insert into realcustomer (SELECT LAST_INSERT_ID(),?,?,?, date ? , ?);";
        String customerInsertQuery = "insert into customer (customerNumber) value(NULL);";

        PreparedStatement realInsertStatement;
        PreparedStatement customerInsertStatement;

        System.out.println("query:\n" + realInsertQuery);

        try {
            DBUtil.getInstance().getConnect().setAutoCommit(false);

            customerInsertStatement = DBUtil.getInstance().getConnect().prepareStatement(customerInsertQuery);
            customerInsertStatement.executeUpdate();

            realInsertStatement = DBUtil.getInstance().getConnect().prepareStatement(realInsertQuery);
            realInsertStatement.setNString(1, firstName);
            realInsertStatement.setNString(2, lastName);
            realInsertStatement.setNString(3, fatherName);
            realInsertStatement.setDate(4, dateOfBirth);
            realInsertStatement.setNString(5, nationalId);
            realInsertStatement.executeUpdate();

            DBUtil.getInstance().getConnect().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editCustomer(BigDecimal customerNumber, String firstName, String lastName, String fatherName, Date dateOfBirth, String nationalId) {
        PreparedStatement preparedStatement;
        String realUpdateQuery = "update realcustomer set firstname=?,lastname=?,fathername=?,dateOfBirth= date ?,nationalId=? where realCustomerNumber=?";

        System.out.println("update query:\n" + realUpdateQuery);

        try {
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(realUpdateQuery);
            preparedStatement.setNString(1, firstName);
            preparedStatement.setNString(2, lastName);
            preparedStatement.setNString(3, fatherName);
            preparedStatement.setDate(4, dateOfBirth);
            preparedStatement.setNString(5, nationalId);
            preparedStatement.setBigDecimal(6, customerNumber);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public RealCustomerEntity findCustomerByCustomerNumber(BigDecimal customerNumber) {
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            String query = "select * from realcustomer where realCustomerNumber = ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setBigDecimal(1, customerNumber);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new RealCustomerEntity(new BigDecimal(resultSet.getString("realCustomerNumber"))
                        , resultSet.getString("firstname"), resultSet.getString("lastname"),
                        resultSet.getString("fathername"), Date.valueOf(resultSet.getString("dateOfBirth")),
                        resultSet.getString("nationalId"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public RealCustomerEntity getCustomerWithNationalId(String nationalId) {
        ResultSet resultSet;
        PreparedStatement preparedStatement = null;
        try {
            String query = "select * from realcustomer where nationalId = ?";
            preparedStatement = DBUtil.getInstance().getConnect().prepareStatement(query);
            preparedStatement.setNString(1, nationalId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new RealCustomerEntity(new BigDecimal(resultSet.getString("realCustomerNumber"))
                        , resultSet.getString("firstname"), resultSet.getString("lastname"),
                        resultSet.getString("fathername"), Date.valueOf(resultSet.getString("dateOfBirth")),
                        resultSet.getString("nationalId"));
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
