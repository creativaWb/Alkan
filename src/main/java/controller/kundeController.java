package controller;

import DbTest.dbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import kunde.*;
import DbTest.*;

public class kundeController {
    private Statement statement	= null;
    private ResultSet resultSet	= null;
    private String	query = "";
    private dbConnection dbConnection = new dbConnection();
    private DefaultTableModel defaultTableModel = null;


    public DefaultTableModel kundenSuchen(String str){
        kunde kunde = new kunde();
        defaultTableModel = (DefaultTableModel) kunde.getTable_kunde().getModel();
        dbConnection.init();
                            //mann kann entweder auf Name oder KundenNr suchen
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

    /**
     * Standartisiertes Update
     * @param query		: Statement
     * @param n			: 0 = Verbindung wird nicht extra aufgebaut; 1 = Verbindung wird aufgbaut und geschlossen
     */
    private void updateStatement(String query) {
       dbConnection.init();
        try {
            statement = dbConnection.getMyConnection().createStatement();
            statement.executeUpdate(query);
            dbConnection.destroy();
        } catch(Exception e) {
            System.out.println(query + "\nFehler in updateStatement(String query): " + e.getMessage());
        }
    }


    public void bankEinfuegen(String[] bankArray){
        query = "INSERT INTO APP.test_BANK VALUES ('"+bankArray[0]+"', "+bankArray[1]+", "+bankArray[2]+", "+bankArray[3]+", "+bankArray[4]+")";
        updateStatement(query);
    }

}
