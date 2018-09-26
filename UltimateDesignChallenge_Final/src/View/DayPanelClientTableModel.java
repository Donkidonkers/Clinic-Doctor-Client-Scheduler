/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.Database.Appointment;
import Model.Database.DailyTimeLineFacade;
import java.awt.Color;

/**
 *
 * @author egapr
 */
public class DayPanelClientTableModel extends DayPanelTableModel {

    public DayPanelClientTableModel(DailyTimeLineFacade dailyTimeLineCalculatorFacade) {
        super(dailyTimeLineCalculatorFacade);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            if (rowIndex % 2 == 0) {
                return String.format("%02d:%02d", (int) (rowIndex / 2), 0);
            }
            return null;
        } else {
            if (dailyTimeLineCalculatorFacade.appointmentTimeline[rowIndex] != null) {
                Color currColor = super.rowColor(rowIndex);
                if (currColor != null) {
                    if (currColor.equals(Color.GREEN)) {
                        return "Your appointment with Doctor : " + dailyTimeLineCalculatorFacade.appointmentTimeline[rowIndex].getDoctorUsername();
                    }
                }
                return "Doctor : " + dailyTimeLineCalculatorFacade.appointmentTimeline[rowIndex].getDoctorUsername() + " spot taken!";
            } else if (dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex].getDoctorUsername() + " " + (dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex].isAvailable() ? "Available" : "Not Available");
            } else if (dailyTimeLineCalculatorFacade.doctorScheduleTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacade.doctorScheduleTimeline[rowIndex].getDoctorUsername() + " Available";
            } else {
                return null;
            }
        }
    }

}
