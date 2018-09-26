package View;

import Model.Database.DailyTimeLineFacade;
import java.awt.Color;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.*;

public class WeeklyPanelClientTableModel extends WeeklyPanelTableModel {

    public WeeklyPanelClientTableModel(DailyTimeLineFacade[] dailyTimeLineCalculatorFacades) throws Exception {
        super(dailyTimeLineCalculatorFacades);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            if (rowIndex % 2 == 0) {
                return String.format("%02d:%02d", (int) (rowIndex / 2), 0);
            }
            return null;
        } else {
            if (dailyTimeLineCalculatorFacades[columnIndex-1].appointmentTimeline[rowIndex] != null) {
                Color currColor = super.rowColor(rowIndex, columnIndex-1);
                if (currColor != null) {
                    if (currColor.equals(Color.GREEN)) {
                        return "Your appointment with Doctor : "
                                + dailyTimeLineCalculatorFacades[columnIndex-1].appointmentTimeline[rowIndex].getDoctorUsername();
                    }
                }
                return "Doctor : " + dailyTimeLineCalculatorFacades[columnIndex-1].appointmentTimeline[rowIndex].getDoctorUsername()
                        + " spot taken!";
            } else if (dailyTimeLineCalculatorFacades[columnIndex-1].doctorEventTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacades[columnIndex-1].doctorEventTimeline[rowIndex].getDoctorUsername()
                        + " "
                        + (dailyTimeLineCalculatorFacades[columnIndex-1].doctorEventTimeline[rowIndex].isAvailable() ? "Available" : "Not Available");
            } else if (dailyTimeLineCalculatorFacades[columnIndex-1].doctorScheduleTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacades[columnIndex-1].doctorScheduleTimeline[rowIndex].getDoctorUsername() + " Available";
            } else {
                return null;
            }
        }
    }

}
