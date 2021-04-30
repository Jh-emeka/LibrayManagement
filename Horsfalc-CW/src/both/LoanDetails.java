package both;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoanDetails implements Serializable {

    private int loanId;

    private String firstName;

    private String lastName;

    private String title;

    private String returnStatus;



    public LoanDetails (int loanId, String firstName, String lastName, String title, String returnStatus){


        this.loanId = loanId;

        this.firstName = firstName;

        this.lastName = lastName;

        this.title = title;

        this.returnStatus = returnStatus;


    }


    public LoanDetails()
    {


    }


    public static LoanDetails newLoanDetailsFromResultSet(ResultSet resultSet) throws SQLException{

        return new LoanDetails(

               resultSet.getInt(1),
               resultSet.getString(2),
               resultSet.getString(3),
               resultSet.getString(4),
               resultSet.getString(5));

    }

    public int getLoanId(){return loanId;}

    public String getTitle(){return title;}

    public  String getFirstName(){return firstName;}

    public String getLastName(){return lastName;}

    public String getReturnStatus(){return returnStatus;}


//    @Override
//    public String toString() {
//        return "LoanDetails{" +
//                "loanId=" + loanId +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", title='" + title + '\'' +
//                ", returnStatus='" + returnStatus + '\'' +
//                '}';
//    }

}
