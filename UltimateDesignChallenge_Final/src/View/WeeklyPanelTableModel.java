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

public class WeeklyPanelTableModel extends AbstractTableModel {

    DailyTimeLineFacade[] dailyTimeLineCalculatorFacades;
    Color[][] coloredRow;

    public WeeklyPanelTableModel(DailyTimeLineFacade[] dailyTimeLineCalculatorFacades) throws Exception {
        
        if (dailyTimeLineCalculatorFacades == null) {
            throw new NullPointerException("The dailyTimeLineCalculatorFacades parameter is null!");
        }
        if (dailyTimeLineCalculatorFacades.length != 7) {
            throw new ArrayIndexOutOfBoundsException("Please input 7 DailyTimeLineCalculatorFacade!");
        }
        this.dailyTimeLineCalculatorFacades = dailyTimeLineCalculatorFacades;
        this.coloredRow = new Color[7][48];
        int counter = 0;
        for (DailyTimeLineFacade dailyTimeLineCalculatorFacade : dailyTimeLineCalculatorFacades) {
            coloredRow[counter] = dailyTimeLineCalculatorFacade.getColorTimeline();
            counter++;
        }
//        System.out.println(Arrays.toString(this.coloredRow));
    }

    private int timeToIndex(int hour, int minute) {
        return (hour * 2) + (minute / 30);
    }

    public Color rowColor(int row, int column) {
        try {
            return this.coloredRow[column - 1][row];
        } catch (ArrayIndexOutOfBoundsException ex) {
            return null;
            //DO Nothing
        }
    }

    @Override
    public int getRowCount() {
        return 48;
    }

    @Override
    public int getColumnCount() {
        return 8;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Time";
            case 1:
                return "Sunday";
            case 2:
                return "Monday";
            case 3:
                return "Tuesday";
            case 4:
                return "Wednesday";
            case 5:
                return "Thursday";
            case 6:
                return "Friday";
            case 7:
                return "Saturday";
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
            if (dailyTimeLineCalculatorFacades[columnIndex - 1].appointmentTimeline[rowIndex] != null) {
                return "Appointment Doctor : "
                        + dailyTimeLineCalculatorFacades[columnIndex - 1].appointmentTimeline[rowIndex].getDoctorUsername()
                        + " Client :" + dailyTimeLineCalculatorFacades[columnIndex - 1].appointmentTimeline[rowIndex].getClientUsername();
            } else if (dailyTimeLineCalculatorFacades[columnIndex - 1].doctorEventTimeline[rowIndex] != null) {
                return dailyTimeLineCalculatorFacades[columnIndex - 1].doctorEventTimeline[rowIndex].getDoctorUsername()
                        + " "
                        + (dailyTimeLineCalculatorFacades[columnIndex - 1].doctorEventTimeline[rowIndex].isAvailable() ? "Available" : "Not Available");
            } else if (dailyTimeLineCalculatorFacades[columnIndex - 1].doctorScheduleTimeline[rowIndex] != null) {
                return "Doctor : "
                        + dailyTimeLineCalculatorFacades[columnIndex - 1].doctorScheduleTimeline[rowIndex].getDoctorUsername()
                        + " Schedule";
            } else {
                return null;
            }
        }
    }

}
