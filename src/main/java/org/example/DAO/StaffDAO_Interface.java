package org.example.DAO;

import org.example.DTO.Staff;
import org.example.Exceptions.DaoException;
import java.util.List;

public interface StaffDAO_Interface
{
    public List<Staff> findAllStaff() throws DaoException;
    public Staff findStaffByID(int StaffID) throws DaoException;
    public String deleteById(int Id) throws DaoException;
    public String addStaff(Staff staff) throws DaoException;

    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterWorkHour() throws DaoException;

    /**Using Comparator - StaffWorkHoursComparator*/
    public List<Staff> findStaffUsingFilterWorkHourComparator() throws DaoException;

    /**Using DB Query - Order By*/
    public List<Staff> findStaffUsingFilterFirstName() throws DaoException;

    /**Using Comparator - StaffFirstNameComparator*/
    public List<Staff> findStaffUsingFilterFirstNameComparator() throws DaoException;

    public String findAllStaffJSON() throws DaoException;
    public String findStaffByIDJSON(int id) throws DaoException;
}
