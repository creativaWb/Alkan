package controller;

import DbTest.dbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import kunde.*;
import DbTest.*;

public class kundeController {
    private Statement statement	= null;
    private ResultSet resultSet	= null;
    private String	query = "";
    private dbConnection dbConnection = new dbConnection();
    private DefaultTableModel defaultTableModel = null;


    public DefaultTableModel kundenSuchen(String str) throws SQLException {
        kunde kunde = new kunde();
        defaultTableModel = (DefaultTableModel) kunde.getTable_kunde().getModel();
        dbConnection.init();
                            //mann kann entweder nach den Name oder nach den KundenNr suchen
        String query = "SELECT KUNDENNR, NAME, ADR from APP.KUNDEN WHERE NAME LIKE '"+ "%" +str+ "%" +"' " +
                "OR KUNDENNR LIKE '"+ "%" +str+ "%" +"' " ;

        try {
            if (defaultTableModel.getRowCount() != 0){
                System.out.println("The Table is not empty"); //Uberprufung ob die Tablle alte Daten beibehaltet
                defaultTableModel.setRowCount(0); //delete alte Ergebnisse fur neue Ergebnisse
            }
            statement = dbConnection.getMyConnection().createStatement();
            resultSet = statement.executeQuery(query);
            while ( resultSet.next()) {
                defaultTableModel.addRow(new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), });

            }
        } catch(Exception e) { System.out.println("Fehler in kundenSuchen: " + e.getMessage()); e.printStackTrace(); }
        statement.close();
        resultSet.close();
        dbConnection.destroy();
        return defaultTableModel;
    }


    public static DefaultTableModel offeneKontenAnzeigen(){
        offeneKonten offeneKonten = new offeneKonten();
        // str ist nur für Test, später wird auf passende SELECT statement ersetzt
        String str = "Mari";
        DefaultTableModel defaultTableModel = (DefaultTableModel) offeneKonten.getTable_offeneKonten().getModel();
        offeneKonten.getDbConnection().init();

        //mann kann entweder auf Name oder KundenNr suchen
        String query = "SELECT KUNDENNR, NAME, ADR from APP.KUNDEN WHERE NAME LIKE '"+ "%" +str+ "%" +"' " +
                "OR KUNDENNR LIKE '"+ "%" +str+ "%" +"' " ;


        try {
            Statement statement = offeneKonten.getDbConnection().getMyConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while ( resultSet.next()) {
                defaultTableModel.addRow(new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), });

            }
        } catch(Exception e) { System.out.println("Fehler in offeneKonten " + e.getMessage()); e.printStackTrace(); }
        offeneKonten.getDbConnection().destroy();
        return defaultTableModel;
    }

    public void bankEinfuegen(String[] bankArray){
        query = "INSERT INTO APP.test_BANK VALUES ('"+bankArray[0]+"', "+bankArray[1]+", "+bankArray[2]+", "+bankArray[3]+", "+bankArray[4]+")";
        try {
            dbConnection.init();
            statement = dbConnection.getMyConnection().createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"  "+bankArray[0]+  " ist in  der Datenbank eingefügt!");
            dbConnection.destroy();
            statement.close();
        } catch(Exception e) {
            System.out.println(query + "\nFehler in updateStatement(String query): " + e.getMessage());
            JOptionPane.showMessageDialog(null,"Fehler bei der Bank Erstellung");
        }finally {
            dbConnection.destroy();
        }
    }

    public void kundeErstellen(String[] kundeArray) {
        query = "INSERT INTO APP.TEST_KUNDE VALUES (default, '"+kundeArray[0]+"', '"+kundeArray[1]+"', "+kundeArray[2]+", '"+kundeArray[3]+"')";
        try {
            dbConnection.init();
            statement = dbConnection.getMyConnection().createStatement();
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null,"  "+kundeArray[0]+  " ist in  der Datenbank eingefügt!");
            dbConnection.destroy();
            statement.close();
        } catch(Exception e) {
            System.out.println(query + "\nFehler in updateStatement(String query): " + e.getMessage());
            JOptionPane.showMessageDialog(null,"Fehler bei der Kunde Erstellung");
        }finally {
            dbConnection.destroy();
        }
    }

}
