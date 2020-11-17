import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.nio.Buffer;

public class Graphics {
//    public void update(Graphics var1) {
//        this.paint(var1);
//    }

//    public void paint(Graphics var1) {
//        if (GUI.gg != null && var1 != null) {
//            GUI.gg.setColor(GUI.bg);
//            GUI.gg.fillRect(0, 0, GUI.getSize().width, GUI.getSize().height);
//            GUI.ca.paint(GUI.gg);
//            var1.drawImage(GUI.buffer, 0, 0, this);
//        }
//    }

    private void drawImage(Buffer buffer, int i, int i1, Graphics graphics) {
    }

    public void colorRow(int rowNumber) {
        // MARK : highlight row functionality
        GUI.numTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
            {
                final Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setBackground(row == rowNumber ? Color.LIGHT_GRAY : Color.WHITE);
                return c;
            }
        });
    }
}
