package Clientpkg;


import both.On_loan;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Onloan_table extends AbstractTableModel {

    private final String[] columnNames = {"loan_id", "book_id", "person_id", "loan_period", "loan_start", "loan_end", "returned_date", "return_status"};

    private final ArrayList<Object[]> onLoanData = new ArrayList<>();


    public void readData(ArrayList<On_loan> data) {
        for (On_loan datum : data) {

            int loan_id = datum.getLoan_Id();
            int book_id = datum.getBook_Id();
            int person_id = datum.getPerson_Id();
            int loan_period = datum.getLoan_Period();
            String loan_start = datum.getLoan_Start();
            String loan_end = datum.getLoan_End();
            String returned_date = datum.getReturned_Date();
            String return_status = datum.getReturn_Status();


            Object[] table_data = {loan_id, book_id, person_id, loan_period, loan_start, loan_end, returned_date, return_status};

            onLoanData.add(table_data);


        }


    }


    @Override
    public int getRowCount() {
        return onLoanData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return onLoanData.get(rowIndex)[columnIndex];
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        onLoanData.get(rowIndex)[columnIndex] = aValue.toString();
    }

    @Override
    public void fireTableRowsDeleted(int firstRow, int lastRow) {
        super.fireTableRowsDeleted(firstRow, lastRow);
    }

}
