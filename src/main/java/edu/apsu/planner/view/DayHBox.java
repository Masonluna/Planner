package edu.apsu.planner.view;

import edu.apsu.planner.data.DayInfo;
import javafx.scene.layout.HBox;

public class DayHBox extends HBox {


    public DayHBox(DayInfo dayInfo) {
        super();
        setUserData(dayInfo);
    }

    public DayInfo getDayInfo() {
        return (DayInfo) getUserData();
    }
}
