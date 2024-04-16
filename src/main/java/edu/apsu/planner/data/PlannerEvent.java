package edu.apsu.planner.data;

import java.io.Serializable;

public class PlannerEvent implements Serializable {

    //Instances
    private String name;
    private String description;
    private String time;
    private Type type;

    // Constructors
    public PlannerEvent() {
        name = "";
        description = "";
        time = "";
        type = null;
    }

    public PlannerEvent(String name, String description, String time, Type type) {
        this.name = name;
        this.description = description;
        this.time = time;
        this.type = type;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
