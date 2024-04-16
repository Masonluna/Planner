package edu.apsu.planner.data;

import java.io.Serializable;

public class PlannerEvent implements Serializable {

    //Instances
    private String name;
    private String description;
    private String time;
    private Tag tag;

    // Constructors
    public PlannerEvent() {
        name = "";
        description = "";
        time = "";
        tag = null;
    }

    public PlannerEvent(String name, String description, String time, Tag tag) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.tag = tag;
    }


    // Methods
    @Override
    public String toString() {
        return this.name;
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
