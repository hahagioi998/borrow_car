package org.car.util;

import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {
	public MyDefaultTableModel(Object[][] data,Object[] columnNames) {
		super(data,columnNames);
	}
	@Override
	 public Class getColumnClass(int column) {  
         Class returnValue;  
         if ((column >= 0) && (column < getColumnCount())) {  
           returnValue = getValueAt(0, column).getClass();  
         } else {  
           returnValue = Object.class;  
         }  
         return returnValue;  
       }  
}
