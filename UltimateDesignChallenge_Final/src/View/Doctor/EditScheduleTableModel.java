package View.Doctor;

import Model.Calendar.*;
import Model.Database.RecurringDoctorSchedule;
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.table.*;

public class EditScheduleTableModel extends AbstractTableModel {

    RecurringDoctorSchedule[] data;

    public EditScheduleTableModel(RecurringDoctorSchedule[] data) {
        this.data = data;
    }


    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Doctor Username";
            case 1:
                return "Day";
            case 2:
                return "Time Start";
            case 3:
                return "Time End";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.data[rowIndex].getDoctorUsername();
            case 1:
                return this.data[rowIndex].getDay();
            case 2:
                return this.data[rowIndex].getTime_start();
            case 3:
                return this.data[rowIndex].getTime_end();
            default:
                return null;
        }
    }

}
