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

public class DayPanelTableModel extends AbstractTableModel {

    DailyTimeLineFacade dailyTimeLineCalculatorFacade;
    Color[] coloredRow;

    public DayPanelTableModel(DailyTimeLineFacade dailyTimeLineCalculatorFacade) {

        try {
            this.dailyTimeLineCalculatorFacade = dailyTimeLineCalculatorFacade;
            coloredRow = this.dailyTimeLineCalculatorFacade.getColorTimeline();
//            System.out.println(Arrays.toString(this.coloredRow));
//            throw new Exception("");
        } catch (Exception ex) {
            Logger.getLogger(DayPanelTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int timeToIndex(int hour, int minute) {
        return (hour * 2) + (minute / 30);
    }

    public Color rowColor(int row) {
        return this.coloredRow[row];
    }

    @Override
    public int getRowCount() {
        return 48;
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Time";
            case 1:
                return "Occasion";
            default:
                return null;
        }
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
                return "Appointment Doctor : " + dailyTimeLineCalculatorFacade.appointmentTimeline[rowIndex].getDoctorUsername() + " Client :" + dailyTimeLineCalculatorFacade.appointmentTimeline[rowIndex].getClientUsername();
            } else if (dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex].getDoctorUsername() + " " + (dailyTimeLineCalculatorFacade.doctorEventTimeline[rowIndex].isAvailable() ? "Available" : "Not Available");
            } else if (dailyTimeLineCalculatorFacade.doctorScheduleTimeline[rowIndex] != null) {
                return "Doctor : " + dailyTimeLineCalculatorFacade.doctorScheduleTimeline[rowIndex].getDoctorUsername() + " Schedule";
            } else {
                return null;
            }
        }
    }

}
