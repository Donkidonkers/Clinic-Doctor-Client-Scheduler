package View;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class DayCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {

        //Cells are by default rendered as a JLabel.
        JLabel l = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

        //Get the status for the current row.
        DayPanelTableModel tableModel = (DayPanelTableModel) table.getModel();
        Color getColor = tableModel.rowColor(row);
        if (getColor != null) {
            l.setBackground(getColor);
            if (getColor.equals(Color.CYAN)) {
                l.setForeground(Color.BLACK);
            } else if (getColor.equals(Color.ORANGE)) {
                l.setForeground(Color.BLACK);
            }  else if (getColor.equals(Color.GREEN)) {
                l.setForeground(Color.BLACK);
            } else {
                l.setForeground(Color.WHITE);
            }
        } else {
            l.setBackground(Color.WHITE);
            l.setForeground(Color.BLACK);
        }

        //Return the JLabel which renders the cell.
        return l;

    }
}
