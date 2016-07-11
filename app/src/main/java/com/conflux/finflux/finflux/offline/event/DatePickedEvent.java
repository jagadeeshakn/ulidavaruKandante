package com.conflux.finflux.finflux.offline.event;

import com.conflux.finflux.finflux.offline.fragment.DatePicker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Praveen J U on 7/10/2016.
 */
public class DatePickedEvent {
    private ArrayList<Date> selectedDates;

    public ArrayList<Date> getSelectedDates() {
        return selectedDates;
    }

    public DatePickedEvent(ArrayList<Date> selectedDates){
        this.selectedDates = selectedDates;
    }
}
