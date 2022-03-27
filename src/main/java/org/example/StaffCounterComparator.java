package org.example;

import org.example.DTO.Staff;

import java.util.Comparator;

public class StaffCounterComparator implements Comparator<Staff>{

    @Override
    public int compare ( Staff s1, Staff s2){

        return s1.getFirst_name().compareTo(s2.getFirst_name());

    }
}
