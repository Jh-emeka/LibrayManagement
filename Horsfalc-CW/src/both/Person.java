package both;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Person implements Serializable {

    private int personId; // primary key
    private String first_name;
    private String last_name;
    private double library_card;


    public Person(int personId, String first_name, String last_name, double library_card){

        this.personId = personId;
        this.first_name = first_name;
        this.last_name = last_name;
        this.library_card = library_card;

    }


    public static Person newPersonFromResultSet(ResultSet resultSet) throws SQLException {
        return new Person(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getDouble(4));
    }


    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) { this.personId = personId; }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) { this.last_name = last_name; }

    public double getLibraryCard() {
        return library_card;
    }

    public void setLibrary_card(double library_card) {
        this.library_card = library_card;
    }


}
