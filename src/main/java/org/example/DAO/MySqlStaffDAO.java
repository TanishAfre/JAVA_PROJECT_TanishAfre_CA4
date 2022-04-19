package org.example.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.DTO.Staff;
import org.example.Exceptions.DaoException;
import org.example.SortType;
import org.example.StaffCounterComparator;
import org.example.StaffWorkHourComparator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.example.menus.App.VALID_EMAIL_ADDRESS_REGEX;

public class MySqlStaffDAO extends MySqlDAO implements StaffDAO_Interface {

    @Override
    public List<Staff> findAllStaff() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "SELECT * FROM Bar_Staff";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String staff_position = resultSet.getString("staff_position");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                Staff s = new Staff(staff_id, first_name, last_name, staff_position, rate_per_hour, work_hours, email);
                staffList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("All Staff - " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
        return staffList;     // may be empty
    }

    @Override
    public Staff findStaffByID(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Staff s = null;
        try {
            connection = this.getConnection();

            String query = "SELECT * FROM Bar_Staff WHERE staff_id = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String staff_position = resultSet.getString("staff_position");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                s = new Staff(staff_id, first_name, last_name, staff_position, rate_per_hour, work_hours, email);

            }
        } catch (SQLException e) {
            throw new DaoException("findStaffByID() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findStaffByID() " + e.getMessage());
            }
        }
        return s;     // reference to User object, or null value
    }

    @Override
    public String deleteById(int id) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        int staff_id = id;

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "DELETE FROM Bar_Staff WHERE staff_id = ?;";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, staff_id);

            preparedStatement.executeUpdate();

            return "Deleted Successfully";

        } catch (SQLException e) {
            throw new DaoException("findAllStaffResultSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
    }

    @Override
    public String addStaff(Staff staff) throws DaoException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int STAFF_ID = staff.getStaff_id();
        String FIRST_NAME = staff.getFirst_name();
        String LAST_NAME = staff.getLast_name();
        double RATE_PER_HOUR  = staff.getRate_per_hour();
        int WORK_HOURS  = staff.getWork_hours();
        String EMAIL = staff.getEmail();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "INSERT INTO Bar_Staff\n" +
                    "VALUES (?,?, ?, ?, ?,?);";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, STAFF_ID);
            preparedStatement.setString(2, FIRST_NAME);
            preparedStatement.setString(3, LAST_NAME);
            preparedStatement.setDouble(4, RATE_PER_HOUR);
            preparedStatement.setInt(5, WORK_HOURS);
            preparedStatement.setString(6, EMAIL);

            preparedStatement.executeUpdate();

            return "Added Successfully";

        } catch (SQLException e) {
            throw new DaoException("findAllStaffSet() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
    }
    @Override
    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterWorkHour() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "Select * from Bar_Staff ORDER by workHours";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String staff_position = resultSet.getString("staff_position");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                Staff s = new Staff(staff_id, first_name, last_name, staff_position, rate_per_hour, work_hours, email);
                staffList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllStaffFilter() " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
        return staffList;     // may be empty
    }

    @Override
    /**Using Comparator - StaffWorkHoursComparator*/
    public List<Staff> findStaffUsingFilterWorkHourComparator() throws DaoException {

        List<Staff> staffList = findAllStaff();
        StaffWorkHourComparator workHourComparator = new StaffWorkHourComparator(SortType.Ascending);
        Collections.sort( staffList, workHourComparator );

        return staffList;
    }

    @Override
    /**Using Comparator - StaffFirstNameComparator*/
    public List<Staff> findStaffUsingFilterFirstNameComparator() throws DaoException {

        List<Staff> staffList = findAllStaff();
        StaffCounterComparator firstNameComp = new StaffCounterComparator();
        Collections.sort( staffList, firstNameComp);

        return staffList;
    }

    @Override
    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterFirstName() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Staff> staffList = new ArrayList<>();

        try {
            //Get connection object using the methods in the super class (MySqlDao.java)...
            connection = this.getConnection();

            String query = "Select * from Bar_Staff ORDER by first_name";
            ps = connection.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int staff_id = resultSet.getInt("staff_id");
                String staff_position = resultSet.getString("staff_position");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                double rate_per_hour = resultSet.getDouble("rate_per_hour");
                int work_hours = resultSet.getInt("work_hours");
                String email = resultSet.getString("email");
                Staff s = new Staff(staff_id, staff_position, first_name, last_name, rate_per_hour, work_hours, email);
                staffList.add(s);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllStaffFilter() " + e.getMessage());
        } finally {
            try{
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllStaff() " + e.getMessage());
            }
        }
        return staffList;     // may be empty
    }

    public String findAllStaffJSON(){
        System.out.println("All Staff as JSON String: ");
        try {
            List<Staff> staffList = findAllStaff();
            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
            String StaffJSON = gsonParser.toJson(staffList);

            return StaffJSON;

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";
    }

    public String findStaffByIDJSON(int id){
        try{
            List<Staff> staffList = findAllStaff();
            String StaffJSON="";
            Staff sTest= findStaffByID(id);
            if(sTest==null){
                System.out.println("Staff with ID = "+id+" doesn't exist.");
            }
            else{
                System.out.println("Staff with id "+id+" as a JSON String: ");
            }
            Gson gsonParser =  new GsonBuilder().setPrettyPrinting().create();
            for(Staff s: staffList){
                if(s.getStaff_id()==id) {
                    StaffJSON = gsonParser.toJson(s);
                }
            }
            return StaffJSON;
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";
    }

    public String findStaffbyIDJSONoFormatting(int id){
        try{
            List<Staff> staffList = findAllStaff();
            String StaffJSON="";
            Staff sTest= findStaffByID(id);
            if(sTest==null){
                System.out.println("Staff with ID = "+id+" doesn't exist.");
            }
            else{
                System.out.println("Staff with id "+id+" as a JSON String: ");
            }
            Gson gsonParser = new Gson();
            for(Staff s: staffList){
                if(s.getStaff_id()==id) {
                    StaffJSON = gsonParser.toJson(s);
                }
            }
            return StaffJSON;
        }
        catch( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";
    }

    public String findAllStaffJSONNoFormatting(){

        System.out.println("All Staff as JSON String: ");
        try {
            List<Staff> staffList = findAllStaff();
            Gson gsonParser = new Gson();
            String StaffJSON = gsonParser.toJson(staffList);

            return StaffJSON;

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return "Error cannot Parse to JSON";

    }

    public String summaryDataJSON(){
        String JSONRes="";
        HashMap<String,Double> res = new HashMap<>();
        try {
            //average salary
            List<Staff> staffList = findAllStaff();
            double total =0;
            double count =0;
            double avg = 0;
            for(Staff s: staffList){
                total += s.getRate_per_hour()*s.getWork_hours();
                count++;
            }
            avg = total/count;
            res.put("AVERAGE SALARY of Staff",avg);


            //standard deviation
            double sub=0;
            double standardDeviation =0;
            double subTotal=0;
            double subCount=0;
            for(Staff s:staffList){
                sub = Math.abs(((s.getRate_per_hour()*s.getWork_hours())-avg)*((s.getRate_per_hour()*s.getWork_hours())-avg));
                subTotal+=sub;
                subCount++;
            }
            standardDeviation = Math.sqrt(subTotal/subCount);
            res.put("STANDARD DEVIATION of Salaries",standardDeviation);

            //total salary earned by each Staff
            for(Staff s:staffList){
                res.put("total Salary of "+s.getFirst_name()+" "+s.getLast_name(),s.getWork_hours()*s.getRate_per_hour());
            }

            Gson gsonParser = new GsonBuilder().setPrettyPrinting().create();
            JSONRes = gsonParser.toJson(res);

        }catch ( DaoException e )
        {
            e.printStackTrace();
        }
        return JSONRes;
    }

}
