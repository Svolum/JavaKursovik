package Viev;

import Logic.PassKeeper;

import javax.swing.table.AbstractTableModel;

public class MyTableModel extends AbstractTableModel {
    private PassKeeper data;

    public MyTableModel(PassKeeper data){
        this.data = data;
    }
    @Override
    public int getRowCount() {
        return data.getSize();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
