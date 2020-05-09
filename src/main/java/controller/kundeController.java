package controller;

import DbTest.dbConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.Statement;
import kunde.*;

public class kundeController {

    public DefaultTableModel kundenSuchen(String str){
        kunde kunde = new kunde();
        DefaultTableModel defaultTableModel = (DefaultTableModel) kunde.getTable_kunde().getModel();
        kunde.getDbConnection().init();

        //mann kann entweder auf Name oder KundenNr suchen
        String query = "SELECT KUNDENNR, NAME, ADR from APP.KUNDEN WHERE NAME LIKE '"+ "%" +str+ "%" +"' " +
                "OR KUNDENNR LIKE '"+ "%" +str+ "%" +"' " ;


        try {
            if (defaultTableModel.getRowCount() != 0){
                System.out.println("The Table is not empty"); //Uberprufung ob die Tablle alte Daten beibehaltet
                defaultTableModel.setRowCount(0); //delete alte Ergebnisse fur neue Ergebnisse
            }
            Statement statement = kunde.getDbConnection().getMyConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while ( resultSet.next()) {
                defaultTableModel.addRow(new Object[] {resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), });

            }
        } catch(Exception e) { System.out.println("Fehler #1 in kundenSuchen: " + e.getMessage()); e.printStackTrace(); }

        kunde.getDbConnection().destroy();
        return defaultTableModel;
    }

}
