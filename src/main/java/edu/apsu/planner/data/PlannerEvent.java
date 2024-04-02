package edu.apsu.planner.data;

public class PlannerEvent {

    //Instances
    private String name;
    private String time;
    private Type type;

    // Constructors
    public PlannerEvent() {
        name = "";
        time = "";
        type = null;
    }

    public PlannerEvent(String name, String time, Type type) {
        this.name = name;
        this.time = time;
        this.type = type;
    }


    // Methods


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
