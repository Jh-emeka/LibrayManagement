package both;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;


public class On_loan implements Serializable {

    private int loan_Id;
    private int book_Id;
    private int person_Id;
    private int loan_Period;
    private String loan_Start;
    private String loan_End;
    private String returned_Date;
    private String return_Status;

   public On_loan(int loan_Id, int book_Id, int person_Id, int loan_Period, String loan_Start, String loan_End, String returned_Date, String return_Status ){

       this.loan_Id = loan_Id;
       this.book_Id = book_Id;
       this.person_Id = person_Id;
       this.loan_Period = loan_Period;


       this.loan_Start = loan_Start;
       this.loan_End = loan_End;
       this.returned_Date = returned_Date;
       this.return_Status = return_Status;

   }

    public On_loan() {

    }

    public static On_loan newOnLoanFromResultSet(ResultSet resultSet) throws SQLException {



        return new On_loan(
                resultSet.getInt(1),
                resultSet.getInt(2),
                resultSet.getInt(3),
                resultSet.getInt(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8));


    }


    public int getLoan_Id() {
        return loan_Id;
    }

    public void setLoan_Id(int loan_Id) {
        this.loan_Id = loan_Id;
    }


    public int getBook_Id() {
        return book_Id;
    }

    public void setBook_Id(int book_Id) {
        this.book_Id = book_Id;
    }

    public int getPerson_Id() {
        return person_Id;
    }

    public void setPerson_Id(int personId) {
        this.person_Id = personId;
    }

    public int getLoan_Period() {
        return loan_Period;
    }

    public void setLoan_Period(int loan_Period) {
        this.loan_Period = loan_Period;
    }

    public String getLoan_Start() {
        return loan_Start;
    }

    public void setLoan_Start(String loan_Start)
    {




        this.loan_Start = loan_Start;



    }

    public String getLoan_End() {return loan_End;}

    public void setLoan_End(String loan_End) {this.loan_End = loan_End;}

    public String getReturned_Date() { return returned_Date;}

    public void setReturned_Date(String returned_date) { this.returned_Date = returned_date;}

    public String getReturn_Status() {return return_Status;}

    public void setReturn_Status(String return_Status) {this.return_Status = return_Status;}


}
