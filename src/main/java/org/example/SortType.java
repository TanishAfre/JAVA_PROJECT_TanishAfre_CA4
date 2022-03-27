package org.example;

// An enumerated type that is passed into a Comparator to set the
// order to "Ascending" or "Descending" order of the field/attributes
// order specified in the Comparator.

public enum SortType
{
    Ascending(1, "Sort Ascending"),
    Descending(-1, "Sort Descending");

    private int value;
    private String description;

    private SortType(int value, String description)
    {
        this.value =  value;
        this.description = description;
    }

    public int getValue()
    {
        return this.value;
    }

    public String toString()
    {
        return this.description;
    }
}
