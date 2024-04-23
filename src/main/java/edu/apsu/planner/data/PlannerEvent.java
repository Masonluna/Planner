package edu.apsu.planner.data;

import java.io.Serializable;

public class PlannerEvent implements Serializable {

    //Instances
    private final String name;
    private final String description;
    private int startingHour;
    private int startingMinute;
    private String startingAmOrPm;
    private int endingHour;
    private int endingMinute;
    private String endingAmOrPm;
    private String time;
    private final Tag tag;

    // Constructors
    public PlannerEvent() {
        name = "";
        description = "";
        time = "";
        tag = null;
    }

    public PlannerEvent(String name, String description, int startingHour, int startingMinute, String startingAmOrPm,
                        int endingHour, int endingMinute, String endingAmOrPm, Tag tag) {
        this.name = name;
        this.description = description;
        this.startingHour = startingHour;
        this.startingMinute = startingMinute;
        this.startingAmOrPm = startingAmOrPm;
        this.endingHour = endingHour;
        this.endingMinute = endingMinute;
        this.endingAmOrPm = endingAmOrPm;
        this.time = String.format("%d:%02d %s - %d:%02d %s", startingHour, startingMinute, startingAmOrPm,
                endingHour, endingMinute, endingAmOrPm);
        this.tag = tag;
    }


    // Methods
    @Override
    public String toString() {
        return String.format("%s\n%s\n%s", this.name, this.time, this.description);
    }

    public String toStringWithDescription() {
        return String.format("%s\n%s\n\n%s", this.name, this.time, this.description);
    }

    // Accessors and Mutators
    public int getStartingHour() {
        return startingHour;
    }

    public int getStartingMinute() {
        return startingMinute;
    }

    public String getStartingAmOrPm() {
        return startingAmOrPm;
    }

    public int getEndingHour() {
        return endingHour;
    }
    public int getEndingMinute() {
        return endingMinute;
    }

    public String getEndingAmOrPm() {
        return endingAmOrPm;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Tag getTag() {
        return tag;
    }
}
