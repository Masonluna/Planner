package edu.apsu.planner.data;

import java.io.Serializable;

public class PlannerEvent implements Serializable {

    //Instances
    private String name;
    private String description;
    private int startingHour;
    private int startingMinute;
    private String startingAmOrPm;
    private int endingHour;
    private int endingMinute;
    private String endingAmOrPm;
    private String time;
    private Tag tag;

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
        return String.format("%s\n%s", this.name, this.time);
    }

    // Accessors and Mutators
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setTag(Tag tag) {
        this.tag = tag;
    }
}
