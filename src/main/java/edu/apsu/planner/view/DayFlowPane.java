package edu.apsu.planner.view;

import edu.apsu.planner.data.DayInfo;
import javafx.scene.layout.FlowPane;

public class DayFlowPane extends FlowPane {


    public DayFlowPane(DayInfo dayInfo) {
        super();
        setUserData(dayInfo);
    }

    public DayInfo getDayInfo() {
        return (DayInfo) getUserData();
    }
}
