package edu.apsu.planner.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;

public class DayInfo implements Serializable {
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

    public void sortEvents() {
        events.sort(new Comparator<PlannerEvent>() {
            @Override
            public int compare(PlannerEvent o1, PlannerEvent o2) {
                if (!o1.getStartingAmOrPm().equals(o2.getStartingAmOrPm())) {
                    if (o1.getStartingAmOrPm().equals("AM")) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else if (o1.getStartingHour() < o2.getStartingHour()) {
                    return -1;
                } else if (o2.getStartingHour() < o1.getStartingHour()) {
                    return 1;
                } else if (o1.getStartingMinute() < o2.getStartingMinute()) {
                    return -1;
                } else if (o2.getStartingMinute() < o1.getStartingMinute()) {
                    return 1;
                }

                return 0;
            }
        });
    }


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

    public String getWeekViewString() {
        return this.monthName + " " + this.dayOfMonth;
    }
}
