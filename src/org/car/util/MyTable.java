package org.car.util;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MyTable extends JTable{
    public MyTable(DefaultTableModel defaultTableModel) {
        super(defaultTableModel);
    }
    @Override
    public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
        DefaultTableCellRenderer cr = (DefaultTableCellRenderer) super
                .getDefaultRenderer(columnClass); // 获得表格的单元格对象
        // 设置单元格内容居中显示
        cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        return cr;
    }

}