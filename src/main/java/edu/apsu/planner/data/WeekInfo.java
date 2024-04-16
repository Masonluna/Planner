package edu.apsu.planner.data;

import java.io.Serializable;
import java.util.ArrayList;

public class WeekInfo implements Serializable {
    // Instances
    private ArrayList<DayInfo> days;

    // Constructors
    public WeekInfo() {
        days = new ArrayList<>();
    }

    public WeekInfo(ArrayList<DayInfo> days) {
        this.days = days;
    }

    // Methods
    @Override
    public String toString() {
        return days.get(0).toString() + " - " + days.get(days.size() - 1).toString();
    }

    // Accessors and Mutators
    public ArrayList<DayInfo> getDays() {
        return this.days;
    }
}
