package Clientpkg;

import both.Person;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class Person_table extends AbstractTableModel {

    private final String[] columnNames = {"person_id", "first_name", "last_name", "library_card" };

    private final ArrayList<Object[]> personData = new ArrayList<>();



    public void readData(ArrayList<Person> data) {
        for (Person datum : data) {

            int personId = datum.getPersonId();
            String firstName = datum.getFirst_name();
            String lastName = datum.getLast_name();
            double libraryCard = datum.getLibraryCard();

            Object[] tData = {personId, firstName, lastName, libraryCard};
            personData.add(tData);


        }

    }



    @Override
    public int getRowCount() {
        return personData.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return personData.get(rowIndex)[columnIndex];
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
        personData.get(rowIndex)[columnIndex] = aValue.toString();
    }

}






