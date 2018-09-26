package View.Client;

import Model.Database.Appointment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.*;

public class RemoveAppointmentPanelTableModel extends AbstractTableModel {

    Appointment[] data;

    public RemoveAppointmentPanelTableModel(Appointment[] data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Client Username";
            case 1:
                return "Doctor Username";
            case 2:
                return "Date";
            case 3:
                return "Time Start";
            case 4:
                return "Time End";
            case 5:
                return "Recurring";
            default:
                return null;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return this.data[rowIndex].getClientUsername();
            case 1:
                return this.data[rowIndex].getDoctorUsername();
            case 2:
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                return df.format(this.data[rowIndex].getDate());
            case 3:
                return this.data[rowIndex].getTime_start();
            case 4:
                return this.data[rowIndex].getTime_end();
            case 5:
                return this.data[rowIndex].isRecurring();
            default:
                return null;
        }
    }

}
