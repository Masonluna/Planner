package edu.apsu.planner.data;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

public class MonthInfo implements Serializable {

    // Instances
    String name;
    ArrayList<WeekInfo> weeks;

    // Constructors
    public MonthInfo() {
        this.weeks = new ArrayList<>();
    }


    /**
     * Parameterized constructor that takes an int year and a Month object and instantiates the weeks ArrayList as well
     * as the corresponding WeekInfo and DayInfo instances for each day in the month.
     *
     * @param month month of the year that the instance will represent
     * @param year the year in which the given month takes place.
     * @throws NullPointerException if month is null
     */
    public MonthInfo(int year, Month month) {


        this.weeks = new ArrayList<>();
        this.name = month.name();
        LocalDate date = LocalDate.of(year, month, 1);
        ArrayList<DayInfo> days = new ArrayList<>();


        // for loop iterates through each day of the month,
        // adding the DayInfo instance to the corresponding WeekInfo instance
        for (int i = 0; i < month.length(date.isLeapYear()); i++) {
            DayInfo tmp = new DayInfo(date);
            days.add(tmp);

            // If the day of the week is saturday or the last day of the month, then add the days list to the
            // weeks ArrayList. Then, instantiate a new days ArrayList.
            if(tmp.getDate().getDayOfWeek() == DayOfWeek.SATURDAY ||
                    i == month.length(date.isLeapYear()) - 1) {
                this.weeks.add(new WeekInfo(days));
                days = new ArrayList<>();
            }

            date = date.plusDays(1);
        }

    }

    // Methods
    @Override
    public String toString() {
        return this.name;
    }

    // Accessors and Mutators

    public ArrayList<WeekInfo> getWeeksList() {
        return this.weeks;
    }

    public WeekInfo getWeek(int index) {
        return this.weeks.get(index);
    }

    /**
     * Takes an int and returns the DayInfo object that corresponds to the given day of the month
     *
     * @param dayOfMonth the day of Month to be searched for.
     * @return The DayInfo instance corresponding to the day of month if the day is found,
     * or null if the day is not in range.
     */
    public DayInfo getDayOf(int dayOfMonth) {
        for (WeekInfo weekInfo : weeks) {
            for (DayInfo dayInfo : weekInfo.getDays()) {
                if  (dayInfo.getDate().getDayOfMonth() == dayOfMonth) {
                    return dayInfo;
                }
            }
        }

        return null;
    }
}
