package edu.apsu.planner.data;

import java.time.LocalDate;
import java.util.ArrayList;

public class DayInfo {
    // Instances
    private ArrayList<PlannerEvent> events;
    private LocalDate date;
    private String monthName;
    private int dayOfMonth;

    // Constructors
    public DayInfo() {
        events = new ArrayList<>();
        date = null;
    }

    public DayInfo(LocalDate date) {
        events = new ArrayList<>();
        this.date = date;
        this.monthName = date.getMonth().toString();
        this.dayOfMonth = date.getDayOfMonth();
    }

    // Methods
    @Override
    public String toString() {
        return this.date.getDayOfWeek() + ", " + this.monthName + " " + dayOfMonth;
    }

    // Accessors and Mutators
    public ArrayList<PlannerEvent> getEvents() {
        return events;
    }

    public void setEvents(ArrayList<PlannerEvent> events) {
        this.events = events;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
