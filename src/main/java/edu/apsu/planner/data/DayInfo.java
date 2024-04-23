package edu.apsu.planner.data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class DayInfo implements Serializable {
    // Instances
    private final ArrayList<PlannerEvent> events;
    private final LocalDate date;
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

        events.sort((o1, o2) -> {
            if (!o1.getStartingAmOrPm().equals(o2.getStartingAmOrPm())) {
                if (o1.getStartingAmOrPm().equals("AM")) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (o1.getStartingHour() < o2.getStartingHour()) {
                if (o2.getStartingHour() != 12)
                    return -1;
            } else if (o2.getStartingHour() < o1.getStartingHour()) {
                if (o1.getStartingHour() != 12)
                    return 1;
            }
            return Integer.compare(o1.getStartingMinute(), o2.getStartingMinute());

        });
    }


    @Override
    public String toString() {
        return this.date.getDayOfWeek() + ",\n" + this.monthName + " " + dayOfMonth;
    }

    // Accessors and Mutators
    public ArrayList<PlannerEvent> getEvents() {
        return events;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getWeekViewString() {
        return this.monthName + " " + this.dayOfMonth;
    }
}
