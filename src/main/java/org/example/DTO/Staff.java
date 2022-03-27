package org.example.DTO;


import java.util.Objects;

public class Staff implements Comparable<Staff>{

    private int staff_id;
    private String staff_position;
    private String first_name;
    private String last_name;
    private double rate_per_hour;
    private int work_hours;
    private String email;

    // Equals and hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return staff_id == staff.staff_id && Double.compare(staff.rate_per_hour, rate_per_hour) == 0 && work_hours == staff.work_hours && Objects.equals(first_name, staff.first_name) && Objects.equals(last_name, staff.last_name) && Objects.equals(email, staff.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(staff_id, first_name, rate_per_hour, last_name, work_hours, email);
    }

    // Constructor
    public Staff(int staff_id, String staff_position, String first_name, String last_name, double rate_per_hour, int work_hours, String email) {
        this.staff_id = staff_id;
        this.staff_position = staff_position;
        this.first_name = first_name;
        this.last_name = last_name;
        this.rate_per_hour = rate_per_hour;
        this.work_hours = work_hours;
        this.email = email;
    }

    // Getters and setters

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_position() {
        return staff_position;
    }

    public void setStaff_position(String staff_position) {
        this.staff_position = staff_position;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public double getRate_per_hour() {
        return rate_per_hour;
    }

    public void setRate_per_hour(double rate_per_hour) {
        this.rate_per_hour = rate_per_hour;
    }

    public int getWork_hours() {
        return work_hours;
    }

    public void setWork_hours(int work_hours) {
        this.work_hours = work_hours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // toString

    @Override
    public String toString() {
        return "staff{" +
                "staff_id=" + staff_id +
                ", staff_position='" + staff_position + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", rate_per_hour=" + rate_per_hour +
                ", work_hours=" + work_hours +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public int compareTo(Staff o) {
        {
            boolean bSameFirst =
                    this.getFirst_name().equalsIgnoreCase(o.getFirst_name());

            boolean bSameLast =
                    this.getLast_name().equalsIgnoreCase(o.getLast_name());

            if (bSameFirst && bSameLast) // Both first and last names are the same
            {
                //so, compare based on Work Hours
                return this.getWork_hours() - o.getWork_hours();
            } else if (!bSameFirst && bSameLast) //Different first, same last
            {
                return this.getFirst_name().compareToIgnoreCase(  // so compare on first
                        o.getFirst_name());
            } else //All other cases
            {
                return this.getLast_name().compareToIgnoreCase(
                        o.getLast_name());
            }
        }
    }
}

