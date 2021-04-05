package both;

import java.io.Serializable;


public class  Parcel implements Serializable {

    private  Command command;

    private  Table table;

    private  Object newData;

    public Parcel(Command command, Table table, Object newData){

        this.command = command;

        this.table = table;

        this.newData = newData;

    }

    public Command getCommand()
    {

        return this.command;

    }

    public Table getTable()
    {

        return this.table;


    }

    public Object getNewData()
    {
        return this.newData;

    }
}

