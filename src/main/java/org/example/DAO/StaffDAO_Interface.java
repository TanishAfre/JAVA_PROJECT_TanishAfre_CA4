package org.example.DAO;

import org.example.DTO.Staff;
import org.example.Exceptions.DaoException;
import java.util.List;

public interface StaffDAO_Interface
{
    public List<Staff> findAllStaff() throws DaoException;
    public Staff findStaffbyID(int StaffID) throws DaoException;
}