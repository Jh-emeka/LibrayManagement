package both;

import java.io.Serializable;


public class  Parcel implements Serializable {

    private  Command command;

    private  Object newBook;

    public Parcel(Command command,Object newBook){

        this.command = command;

        this.newBook = newBook;

    }



    public Command getCommand()
    {

        return this.command;

    }

    public Object getBook()
    {
        return this.newBook;

    }
}

