package DbTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;



public class dbConnection {
    private Connection myConnection;

    public dbConnection(){

    }

    public void init(){
        try {
            String dbURL = "jdbc:derby://v3.cw-bo.de:24042/alkan";
            String user = "egal";
            String passwort = "egal";

            Class.forName("org.apache.derby.jdbc.ClientDriver");
            myConnection = DriverManager.getConnection(dbURL, user, passwort);
            if (myConnection != null) {
                System.out.println("Connected to database #2");
            }
        }
        catch (Exception e){
            System.out.println("Failed to get Connection");
            e.printStackTrace();
        }
    }

    public Connection getMyConnection(){
        return myConnection;
    }

    public void close(ResultSet rs){
        if(rs !=null){
            try{
                rs.close();
            }
            catch(Exception e){}
        }
    }

    public void close(java.sql.Statement stmt){

        if(stmt !=null){
            try{
                stmt.close();
            }
            catch(Exception e){}

        }
    }

    public void destroy(){

        if(myConnection !=null){

            try{
                myConnection.close();
                System.out.println("Connection closed");
            }
            catch(Exception e){}


        }
    }


}
