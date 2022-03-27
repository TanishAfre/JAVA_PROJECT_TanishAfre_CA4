package org.example;

import org.example.DTO.Staff;

import java.util.Comparator;

public class StaffWorkHourComparator implements Comparator<Staff>{

    private SortType sortType;

    public StaffWorkHourComparator(SortType sortType)
    {
        this.sortType = sortType;
    }

    @Override
    public int compare(Staff s1, Staff s2)
    {
        int direction = sortType.getValue();
        return direction * (s1.getWork_hours() - s2.getWork_hours());
    }
}
