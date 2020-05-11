
package de.creativaweb.database;
//
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.sql.ResultSetMetaData;
//import masks.MaskeLieferant;

//import basics.*;
//import starts.Alkan_Basic;
//stmt.executeUpdate("CREATE TABLE product4(id  INTEGER NOT NULL  PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1,increment by 1) ,mitarbeiter VARCHAR(30) NOT NULL,linie varchar(10) NOT NULL,auschuss VARCHAR(30) NOT NULL,zeit varchar(30) NOT NULL,datum varchar(30)NOT NULL)");
public class OwnDerby implements Runnable{
	//private Basics				basics			= new Basics();
	private Thread				trd				= null;
	
	private static Connection			con				= null; private Statement			stmtT		= null;
    private Statement			stmt			= null;
    private ResultSet			rset			= null;
	private DatabaseMetaData	metadata		= null;
	private ResultSetMetaData	rsMetaData		= null;
    private int					eU				= 0;
    private String				query			= "";
    private String				startPunkt		= "";
    private JTable				artTbl			= null;
    public DefaultTableModel	tblModel1		= null;
    private String[]			artTblHead2		= { "LIEFERANTNR","LIEFERANTNAME"};
    JComboBox combo = new JComboBox();
	private static String[]			serverliste		= { 
		// 0
		"jdbc:derby://localhost:1527/alkan;create=true;",
		// 1
		"jdbc:derby://10.70.10.31:1527/alkan;create=true;",
		// 2
		"jdbc:derby://10.70.10.47:1527/alkan;create=true;",
		// 3
		"jdbc:derby://192.168.2.149:1527/alkan;create=true;",
		// 4
		"jdbc:derby://192.168.2.108:1527/alkan;create=true;",
		// 5
		"jdbc:derby://v3.cw-bo.de:24042/alkan;create=true;"
	};	
	/*
	private int					setServer		= 2;/*/
	private static int					setServer		= 5;//*/
	public String 				lieferantname;
	private DefaultTableModel	tblModel	= null;
	private String[]			tblHeader2	= { "","" };
	private String[]			tblHeader3	= { "","","" };
	private static boolean				dbStarted	= false;

	private String[]			nT			= null;	// basics.newTables
	private String[][]			cN			= null;	// basics.colName
	private String[][]			cD			= null;	// besics.colDim
	
    public void run() {};
    
	/*public OwnDerby() {
		trd = new Thread(this);
		trd.start();
		checkNewTables();		
	}
	*/
    //
	public void setMessageLabel() {
	}
	// datenbank verbindung 
	public static void openCon(){
		String server = serverliste[setServer];
		try {
			Class.forName( "org.apache.derby.jdbc.ClientDriver" );
			Properties props = new Properties();
            props.put("user", "alkan");
            props.put("password", "getin");
			//con = DriverManager.getConnection(server,props);
			con = DriverManager.getConnection(server);
			dbStarted	= true;
		} catch(Exception e) {
			//if(!dbStarted) startDataBaseViaDosCommand();
			System.out.println("Fehler #1 in conToDb(): " + e.getMessage());
			e.printStackTrace();
			dbStarted	= false;
		}
	}
	public void createTable2() throws SQLException
	{
		openCon();
		stmt	= con.createStatement();
		stmt.executeUpdate("CREATE TABLE HAUPTGRUPPE2(ID INTEGER NOT NULL  PRIMARY KEY GENERATED ALWAYS AS IDENTITY (start with 1,increment by 1) ,HGRUPPE VARCHAR(50) NOT NULL,UGRUPPE varchar(50) )");
	}
	//test
	private void startDataBaseViaDosCommand() {
	    try {
	    	//Process process = Runtime.getRuntime().exec("DerbyLocal.bat");
	    	
	    	//JOptionPane.showMessageDialog(null,"Sie sind mit  DatenBank verbunden!", null,JOptionPane.INFORMATION_MESSAGE);

	    } catch (Exception e) {
	    	System.out.println("Fehler #1 in startDataBaseViaDosCommand(): " + e.getMessage());
	    	JOptionPane.showMessageDialog(null,"keine Verbindung zum  DatenBank!", null,JOptionPane.INFORMATION_MESSAGE);
	        e.printStackTrace();
	    }
	    openCon();
	}
	
	public void stopDataBaseViaDosCommand() {
	    try {
	    	Process process = Runtime.getRuntime().exec("stopDerbyLocal.bat");
	    	
	    } catch (IOException e) {
	    	System.out.println("Fehler #1 in stopDataBaseViaDosCommand(): " + e.getMessage());
	        e.printStackTrace();
	    }
	}
	
	public void closeCon() {
		try {
			con.close();
		} catch(Exception e) {}
	}
	
	public void stopThread() {
		closeCon();
		//stopDataBaseViaDosCommand();	// only with stand-alone-applications
		try {
			trd.interrupt();
		} catch(Exception e) {}
	}
	
	/*
	 * In dieser Routine wird ueberprueft, ob die neuen Tabellen bereits in der Datenbank gegeben sind.
	 * Wenn nicht, werden diese automatisch hinzugefuegt
	 */
	private void checkNewTables() {
		//nT				= basics.newTables;	// Tabellen-Name
		//cN				= basics.colName;	// Spaltenbezeichnung
	//	cD				= basics.colDim;	// Spaltentyp
		List	liste	= new List();
		boolean	ok		= false;
		String	tblName	= "";
		
		openCon();
		
		try {
			DatabaseMetaData dbmd = con.getMetaData();
			stmt	= con.createStatement();
			rset = stmt.executeQuery("SELECT SYS.SYSTABLES.TABLENAME FROM SYS.SYSTABLES WHERE SYS.SYSTABLES.TABLETYPE = 'T'");
			while (rset.next()) {
		    	if(!rset.getString(1).startsWith("TBL")) liste.add(rset.getString(1));
			}
			rset.close();
			stmt.close();
		} catch(Exception e) {}	

		for(int t = 0; t < nT.length; t++) {
			ok = false;
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(nT[t].toUpperCase().equals(liste.getItem(i))) {
					ok = true;
					break;
				}
			}
			if(!ok) createTable(nT[t].toUpperCase()); 
		}
		
		closeCon();
	}
	
	private void createTable(String tblName) {
		String query = "";
		int pos = 0;
		for(pos = 0; pos < nT.length; pos++) {
			if(nT[pos].toUpperCase().equals(tblName)) break;
		}
		query = autoGetQuery(tblName,pos);
		
		try {
			stmt	= con.createStatement();
    		eU		= stmt.executeUpdate(query);
    		stmt.close();
		} catch(Exception e) {
            System.out.println(tblName + " Fehler in OwnDerby.createTable(): " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	private String autoGetQuery(String tblName, int n) {
		//String query = "CREATE TABLE " + basics.app + tblName + " (";
		for(int i = 0; i < cN[n].length; i++) {
			query += cN[n][i] + cD[n][i];
			if(i < cN[n].length - 1) query += ",";// else query += ""; 
		}
		query += ")";
		try {
			query = query.replaceAll("\\", "");
		} catch(Exception e) { }
		return query;
	}
	
	/**
	 * Eine Auswahltabelle auslesen und zurueckgeben
	 * @param str	= query
	 * @return		= Auswahltabelle
	 * @throws InterruptedException 
	 */
	public JTable getAuswahlTabelle(String queryT, int s) {
		JTable tbl	= new JTable();

		if(s == 2) tblModel	= new DefaultTableModel(tblHeader2, 0); else tblModel	= new DefaultTableModel(tblHeader3, 0);
		tbl			= new JTable(tblModel);
		
		openCon();
		try {
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(queryT);
			while (rset.next()) {
				if(s == 2) {
					if(rset.getString(1).trim().length() > 0) {
						tblModel.addRow(new Object[] {rset.getString(1),rset.getString(2)});
					}
				} else {
					if(rset.getString(3).trim().length() > 0) {
						tblModel.addRow(new Object[] {rset.getString(1),rset.getString(2),rset.getString(3)});
					}
				}
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in OwnDerby.getAuswahlTabelle(): " + e.getMessage());
            e.printStackTrace();
		}
		closeCon();
		if(s == 2) formatColumn(tbl, 1, 0); else formatColumn(tbl, 2, 0);
		
		return tbl;
	}
	
	/**
	 * Zeile einer Tabelle auslesen und zurückgeben
	 * @param tbl	= Name der Tabelle
	 * @param str	= Suchkriterium
	 * @return		= Datensatz
	 * @throws InterruptedException 
	 */
	public String[] getData(String queryT, int n) {
		String[]	cNames	= null;
		String[]	data	= null;
		int			cCount	= 0;
		
		if(n == 1) openCon();
		
		try {
			stmt		= con.createStatement();
			rset		= stmt.executeQuery(queryT);
			rsMetaData	= rset.getMetaData();
			
			cCount		= rsMetaData.getColumnCount();
		    cNames		= new String[cCount];
		    data		= new String[cCount];		    
		    while(rset.next()) {
		    	for(int i = 0; i < cCount; i++) {
			    	data[i] = rset.getString(i + 1);
			    	if(data[i] == null) {
			    		data[i] = "";
			    	}
		    	}
		    }
		} catch(Exception e) {
            System.out.println("Fehler # 1 in OwnDerby.getData: " + e.getMessage());
		}
		
		if(n == 1) closeCon();
		return data;
	}
	
	/**
	 * 2020-01-10JF ueberpruefen, ob eine Kunden-Nummer schon in die neue Datenbank uebertragen ist
	 * @param str	= Kunden-Nummer
	 * @return
	 */
	public boolean checkDataInNewTable(String tbl, String colName, String colComp, String str, int n) {
		boolean ok = false;
		//query = "SELECT " + colName + " FROM " + basics.app + tbl + " WHERE " + colComp + " ='" + str + "'";
		if(n == 1) openCon();
		try {
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);
			while (rset.next()) {
				ok = true;
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in checkDataInNewTable: " + e.getMessage());
		}
		if(n == 1) closeCon();
		return ok;
	}
	
	/**
	 * 2020-01-10JF
	 * @param id	= KundenNummer
	 * @param n		= conOpen und conClose wird nicht gebraucht
	 * @return		= ausgelesener Datensatz
	 */
	public String[] getKundenFromNewTable(String id) {
	//	query = "SELECT * FROM " + basics.app + "kunden WHERE kundenNr='" + id  + "'";
		return getData(query, 12, 1);
	}
	
	/**
	 * 2020-01-10JF
	 * @param id	= Id der Adresse, die ausgelesen werden soll
	 * @param n		= conOpen und conClose wird nicht gebraucht
	 * @return		= ausgelesener Datensatz
	 */
	public String[] getAdresseFromNewTable(String id) {
	//	query = "SELECT * FROM " + basics.app + "adresse WHERE id='" + id  + "'";
		return getData(query, 15, 1);
	}
	
	/**
	 * 2020-01-10JF
	 * @param id	= Id der Bankdaten, die ausgelesen werden sollen
	 * @param n		= conOpen und conClose wird nicht gebraucht
	 * @return		= ausgelesener Datensatz
	 */
	public String[] getBankDataFromNewTable(String id) {
	//	query = "SELECT * FROM " + basics.app + "bankdata WHERE id='" + id  + "'";
		return getData(query, 7, 1);
	}
	
	/**
	 * 2020-01-10JF
	 * Auslesen von Daten aus der Datenbank
	 * @param query	= Auslesefunktion
	 * @param s		= Anzahl der Felder in String-Array
	 * @param n		= conOpen und conClose nutzen
	 * @return		= Rueckgabe der ausgelesenen Daten
	 */
	public String[] getData(String query, int s, int n) {
		String[] data = new String[s];
		if(n == 1) openCon();
		try {
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);
			while (rset.next()) {
				for(int i = 0 ; i < data.length; i++) {
					data[i] = "";
					data[i] = rset.getString(i + 1);
				}
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("query = " + query + "\nFehler in getData(String query, int s, int n): " + e.getMessage());
		}
		if(n == 1) closeCon();
		return data;
	}
	
	/**
	 * Eine ID in einer Tabelle aktualisieren
	 * @param tbl	= Name der tabelle
	 * @param col	= zu aktualisierende Spalte
	 * @param id	= einzutragende neue ID
	 * @param comp	= Kundennummer bzw ID
	 */
	public void setIDinTable(String tbl, String colSet, String colComp, String id, String compID) {
		//query = "UPDATE " + basics.app + tbl + " SET " + colSet + "='" + id + "' WHERE " + colComp + "='" + compID + "'";
		updateStatement(query, 1);
	}
	
	/**
	 * Standartisiertes Update
	 * @param query		: Statement
	 * @param n			: 0 = Verbindung wird nicht extra aufgebaut; 1 = Verbindung wird aufgbaut und geschlossen
	 */
	private void updateStatement(String query, int n) {
		if(n == 1) openCon();
		try {
			stmt = con.createStatement();
    		eU = stmt.executeUpdate(query);
    		stmt.close();
		} catch(Exception e) {			
            System.out.println(query + "\nFehler in updateStatement(String query): " + e.getMessage());
		}
		if(n == 1) closeCon();
	}
	
	public String getNameForID(String queryT) {
		String str = "";
		try {
			Statement stmt1	= con.createStatement();
			ResultSet rset1	= stmt.executeQuery(queryT);
			while (rset1.next()) {
				str = rset1.getString(1);
			}
			stmt1.close();
			rset1.close();
		} catch(Exception e) {
            System.out.println("Fehler in getNameForID: " + e.getMessage());
		}
		
		//System.out.println(str);
		return str;
	}
	
	public JTable getLieferantenForMaskeArtikel(String search) {
		JTable tbl = new JTable();	
		//					"ALNr","LieferantenNr","Lieferant","Liefer-ArtikelNr","LArtPreis"
		String[] header = { "","","Lieferant","Liefer-ArtikelNr","LArtPreis" };
		tblModel	= new DefaultTableModel(header, 0);
		tbl			= new JTable(tblModel);
		//query = "SELECT ALNr,LieferantenNr,LArtNr,LArtPreis FROM " + basics.app + "tblArtLiefer WHERE ArtikelNr ='" + search + "'";		
		this.openCon();		
		try {
			stmt	= con.createStatement();
			rset		= stmt.executeQuery(query);
			while (rset.next()) {
				tblModel.addRow(new Object[] {rset.getString(1),rset.getString(2),"",rset.getString(3),rset.getString(4)});
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in OwnDerby.getLieferantenForMaskeArtikel(): " + e.getMessage());
            e.printStackTrace();
		}		
		for(int r = 0; r < tbl.getRowCount(); r++) {
			String str = getNameForID("SELECT LieferName FROM app.tblLieferanten WHERE LieferantenNr ='" + tbl.getValueAt(r, 1).toString() + "'");
			tbl.setValueAt(str, r, 2);
		}		
		this.closeCon();
		return tbl;
	}

	public JTable getGruppenrozenteForMasteArktikel(String search) {
		JTable tbl = new JTable();
		String str = "";
		String[] header = { "PGNr","Abholpreis-%","Lieferpreis-%","StkVK-%","StkAH-%" };
		tblModel	= new DefaultTableModel(header, 0);
		tbl			= new JTable(tblModel);		
		//String query = "SELECT PGNr,GABHPreis,GLiefPreis,StkGLPreis,StkGAHPreis FROM " + basics.app + "tblPGDetail WHERE ArtikelNr ='" + search + "' ORDER BY PGNr";
		openCon();
		try {
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);
			while (rset.next()) {
				tblModel.addRow(new Object[] {rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5)});
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler #2 in OwnDerby.getGruppenrozenteForMasteArktikel: " + e.getMessage());
            e.printStackTrace();
		}
		closeCon();
		return tbl;
	}
	
	public JTable getPGruppenArtikelPreiseForMaskeArtikel(String search) {
		//System.out.println(search);
		JTable tbl = new JTable();
		//					PGNr,PGruppe,Lieferpreise, Abholpreis,StkVKPreis
		String[] header = { "PGNr","PGruppe","Lieferpreise","Abholpreis","StkVKPreis" };
		tblModel	= new DefaultTableModel(header, 0);
		tbl			= new JTable(tblModel);
		//query = "SELECT PGNr,GLiefPreis,GAbhPreis,StkGLPreis,StkGAHPreis FROM " + basics.app + "tblPGDetail WHERE ArtikelNr ='" + search + "'";
		try {
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);
			while (rset.next()) {
				tblModel.addRow(new Object[] {rset.getString(1),"",rset.getString(2),rset.getString(3),rset.getString(4)});
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in OwnDerby.getPGruppenArtikelPreiseForMaskeArtikel: " + e.getMessage());
            e.printStackTrace();
		}
		
		for(int r = 0; r < tbl.getRowCount(); r++) {
			String str = getNameForID("SELECT PGName FROM app.tblPGruppen WHERE PGNr ='" + tbl.getValueAt(r, 0).toString() + "'");
			tbl.setValueAt(str, r, 1);
		}	
		return tbl;
	}

	public JTable geBarcodeForMaskeArtikel(String search) {	
		String[] header = { "Barcode" };
		tblModel		= new DefaultTableModel(header, 0);
		JTable tbl		= new JTable(tblModel);
		openCon();
		try {
			//String query = "SELECT Barcode FROM " + basics.app + "tblBarcode WHERE ArtikelNr ='" + search + "'";	
			stmt		= con.createStatement();
			rset		= stmt.executeQuery(query);
			while (rset.next()) {
				tblModel.addRow(new Object[] {rset.getString(1)});
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in OwnDerby.geBarcodeForMaskeArtikel(): " + e.getMessage());
            e.printStackTrace();
		}	
		closeCon();
		return tbl;
	}
	
	/*einfuegen von Bankdaten in die Tabelle 'bankdata'
	 * 
	 */
	public void setBankData(String id, String[] data) {
		String idT = getNewID("bankdata");
		//query = "UPDATE " + basics.app + "tblKunden SET bank='" + idT + "',BLZ='',KONTO='',IBAN='',BIC='' WHERE KundenNr ='" + id + "'";
		updateStatement(query, 1);
		//query = "INSERT INTO "+ basics.app + "bankdata VALUES(idT,id,data[0],data[1],data[2],data[3],data[4])";
		updateStatement(query, 1);
	}
	
	/**
	 * 2020-01-20jf
	 * einfuegen/aendern eines Eintrages in die Tabelle 'lieferadresse'
	 * @param id	= Kunden-Nummer
	 * @param data	= einzutragenden Daten
	 */
	public void setLieferAdresse(String id, String[] data) {
		boolean neu = true;
		if(!data[0].trim().equals("")) neu = false;
		if(neu) {													// neue Adresse	
			openCon();
			data[0] = getNewID("adresse");
			closeCon();
			data[1] = id;
			//query = "INSERT INTO " + basics.app + "adresse VALUES('";
			for(int i = 0; i < data.length; i++) {
				query += data[i] + "','";
			}
			query = query.substring(0, query.length() - 2);
			query += ")";
		} else {													// geaenderte Adresse
		//	query = "Update " + basics.app + "adresse SET NAME='" + data[2] + "',VORNAME='" + data[3] + "',ADR1='" + data[4] + "',ADR2='" + data[5] + "',PLZ='" + data[6] + "',ORT='" + data[7] + "',LAND='" + data[8] + "',TEL1='" + data[9] + "',TEL2='" + data[10] + "',HANDY='" + data[11] + "',FAX='" + data[12] + "',EMAIL='" + data[13] + "',WEB='" + data[14] + "' WHERE id='" + data[0] + "'";
		}		
		updateStatement(query, 1);
		if(neu) {													// neue Adress-ID in Tabelle Kunden eintragen
			//query = "Update " + basics.app + "kunden SET " + cN[0][4] + "='" + data[0] + "' WHERE kundennr ='" + data[1] + "'";	
			updateStatement(query, 1);
		}
		//query = "INSERT INTO "+ basics.app + "bankdata VALUES(idT,id,data[0],data[1],data[2],data[3],data[4])";
		//updateStatement(query, 1);
	}
	
	private String getNewID(String tbl) {
		String id = "0000000000";
		try {
			Statement stmtT = con.createStatement();
			//ResultSet rsetT = stmtT.executeQuery("SELECT count(*) AS rowcount from " + basics.app + tbl);
		//rsetT.next();
			//int n = rsetT.getInt("rowcount") + 1;
			//id += "" + n;
			id = id.substring(id.length() - 10, id.length());
			//System.out.println("ID = " + id);
			//rsetT.close();
			stmtT.close();
		} catch (SQLException e) {
            System.out.println("Fehler #1 in OwnDerby.getNewID(): " + e.getMessage());
            e.printStackTrace();
		}
		return id;
	}
	
	public String[] getLieferAdresse(String idT) {
		String[] data = null;
		// TODO
		return data;
	}
	
	/**
	 * 2020-01-20jf - fertiggestellt
	 * Daten aus der alten Datenbank in die neue uebertragen
	 * @param data	= Daten aus der alten Datenbank
	 */
	public void writeToNewTableKunden(String[] data) {
		/*
		for(int i = 0; i < data.length; i++) {
			String[] felder = {"KUNDENNR","FIRMANAME","VORNAME","FAHRERNR","TFAHRERNR","EGKUNDE","STRASSE","PLZ","ORT","LAND","LADRSNR","TEL1","TEL2","HANDY","FAX","EMAIL","WEBSEITE","USTID","STEUERNR","BANK","BLZ","KONTO","IBAN","BIC","PGNR","BRIEF","ALTOFF","ALTROFF","INAKTIV"};
			System.out.println(i + ": \t\t" + felder[i] + "\t = " + data[i]);
		}//*/
		if(!checkDataInNewTable("kunden","kundennr","kundennr",data[0],0)) {
			// 2020-01-10JF Datensatz in der alten Tabelle als uebertragen markieren
			//query = "UPDATE " + basics.app + "tblkunden SET inaktiv='9' WHERE kundennr='" + data[0] + "'";
			updateStatement(query, 0);
			
			// 2020-01-10JF Daten in die neuen Tabellen eintragen
			String kdID = getNewID("kunden");
			String adID = getNewID("adresse");
			String bkID = getNewID("bankdata");
			String feID = getNewID("fideoid");
			//                                                    id,            kundennr,         name,                           kontakt,        lieferadresse, bank,      fid,eoid, ust, str, pg   , kng, aktiv
			//                                                    '0000000001',  '10012',          'Ac Spiellounge Gmbh',          '0000000001',   '0000000001','0000000001','','','','','0000000075','0')
			                                        //            0              1                 2                 3              4              5              6      8                  9                  10              11
			//query = "INSERT INTO " + basics.app + "kunden VALUES('" + kdID + "','" + data[0] + "','" + data[1] + "','" + adID + "','" + adID + "','"  + bkID + "','','','" + data[17] + "','" + data[18] + "','" + data[24] + "','0')";
			//System.out.println(query);
			updateStatement(query, 0);
			
			// Adresse uebertragen
			//query = "INSERT INTO " + basics.app + "adresse VALUES('" + adID + "','" + data[0] + "','" + data[1] + "','" + data[2] + "','" + data[6] + "','','" + data[7] + "','" + data[8] + "','" + data[9] + "','" + data[11] + "','" + data[12] + "','" + data[13] + "','" + data[14] + "','" + data[15] + "','" + data[16] + "')";
			updateStatement(query, 0);
			
			// Bankdaten uebertragen
			//query = "INSERT INTO " + basics.app + "bankdata VALUES('" + bkID + "','" + data[0] + "','" + data[19] + "','" + data[20] + "','" + data[21] + "','" + data[22] + "','" + data[23] + "')";
			updateStatement(query, 0);
			
			// FID und EOID uebertragen - ACHTUNG - Diese Daten sind nicht in TBLKUNDEN !
			//query = "INSERT INTO " + basics.app + "fideoid VALUES('" + feID + "','" + data[0] + "','','')";
			updateStatement(query, 0);	
		}
	}
	
	public void formatColumn(JTable tblT, int c, int w) {
		tblT.getColumnModel().getColumn(c).setMinWidth(w);
		tblT.getColumnModel().getColumn(c).setMaxWidth(w);
		tblT.getColumnModel().getColumn(c).setWidth(w);		// Spalte 'c' auf die Breite 'w' setzen
	}
	
	public void updateKundenEintrag(String[] kDat, String[] aDat, String[] bDat, String[] fid) {
		System.out.println("Got here - updateKundenEintrag(...)");
	}
	
	private String reservKundenID = "";
	
	/** Neuen Kunden eintragen
	 * 20200204jf
	 * @param kDat	= Kundendaten
	 * @param aDat	= Adressdaten
	 * @param bdat	= Bankdaten
	 */
	public void eintragNeuKunde(String[] kDat, String[] aDat, String[] bDat, String[] fid) {		
		openCon();
		String id = getAndReserveKundenNr();		// Kundennummer
		
		String idB = getNewID("bankdata");			// BankData ID
		//query = "INSERT INTO " + basics.app + "bankdata VALUES('" + idB + "','" + id + "','" + bDat[0] + "','" + bDat[1] + "','" + bDat[2] + "','" + bDat[3] + "','" + bDat[4] + "')";
		updateStatement(query, 0);
		
		aDat[2] = kDat[1];
		String idA = getNewID("adresse");			// AddressData ID
		//query = "INSERT INTO " + basics.app + "adresse VALUES('" + idA + "','" + id + "','" + aDat[2] + "','" + aDat[3] + "','" + aDat[4] + "','" + aDat[5] + "','" + aDat[6] + "','" + aDat[7] + "','" + aDat[8] + "','" + aDat[9] + "','" + aDat[10] + "','" + aDat[11] + "','" + aDat[12] + "','" + aDat[13] + "','" + aDat[14] + "')";
		updateStatement(query, 0);

		// Eintrag von FID und EOID
		String fID = getNewID("FIDEOID");			// FIDEOID ID
		//query = "INSERT INTO "+ basics.app + "FIDEOID VALUES('" + fID + "','" + kDat[1] + "','" + fid[0] + "','" + fid[1] + "')";
		//updateStatement(query, 0);
		
		// Eintrag in die Tabelle 'Kunden'
	//	query = "UPDATE " + basics.app + "kunden SET kundennr='" + id + "', name='" + kDat[1] + "', adr='" + idA + "', "
		//		+ "ladr='" + idA + "', ust='" + kDat[5] + "', str='" + kDat[6] + "', bank='" + idB + "', "
		//		+ "pg='" + kDat[8] + "', aktiv='" + kDat[9] +"' WHERE id='" + reservKundenID + "'";		
		updateStatement(query, 0);
		
		closeCon();
	}
	
	/** Eine Kundennummer erzeugen und zurückgeben
	 * 20200204jf
	 * @return	= neue Kundennummer
	 */
	private String getAndReserveKundenNr() {
		String id	= "";
		String idT	= "00000";
		//query = "SELECT kundennr FROM " + basics.app + "TBLKUNDEN order by kundennr DESC";
		try {
			stmt		= con.createStatement();
			rset		= stmt.executeQuery(query);
			while (rset.next()) {
				id = rset.getString(1);
				break;
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {}
		int nr = Integer.parseInt(id) + 1;	// neue Kundennummer
		idT += nr;
		id = idT.substring(idT.length() - 5, idT.length());
		
		// Kundennummer in die alte Tabelle eintragen (alles ohne Werte)
		//query = "INSERT INTO " + basics.app + "TBLKUNDEN VALUES ('" + id + "','in Tabelle Kunden','','','','','','','','','','','','','','','','','','','','','','','','','','','9')";
		updateStatement(query, 0);
		// Kundennummer in die neue Tabelle eintragen und reservieren
		reservKundenID = getNewID("kunden");
		//                                                     0                        1           2  3  4  5  6  7  8  9  10 11
		//query = "INSERT INTO " + basics.app + "KUNDEN VALUES ('" + reservKundenID + "','" + id + "','','','','','','','','','','')";
		updateStatement(query, 0);
		
		return id;
	}
	
	public List getListe(String tbl, String spalte, int n) {
		List liste = new List();
		//String query = "SELECT " + spalte + " FROM " + basics.app + tbl;
		if(n == 1) openCon();
		try {			
			stmt = con.createStatement();
			rset = stmt.executeQuery(query);
			while (rset.next()) {
			    liste.add(rset.getString(1));
			}
			rset.close();
			stmt.close();
		} catch(Exception e) {
		}
		if(n == 1) closeCon();
		return liste;
	}
	
	// ********************       Bestellung nach Lieferanten       *****************************************
	 public  void setName(String m)
		{
			 lieferantname = m;
		}
	 public  String getName()
		{
		 	
			return lieferantname;
		}
	
	
	public JTable getLieferantenBestellung(String lieferantennr) {
		
		JTable tbl = new JTable();	
		//					"ALNr","LieferantenNr","Lieferant","Liefer-ArtikelNr","LArtPreis"
		String[] header = { "ArtikelNr" , "Beschreibung","MinBestand", "Menge","EKPreis"};
		tblModel	= new DefaultTableModel(header, 0);
		tbl			= new JTable(tblModel);
		query = "SELECT tbllieferanten.lieferantennr,tbllieferanten.liefername,tblartliefer.artikelnr,tblartikel.beschreibung,tblartikel.minbestand,tblartikel.ekpreis,tblartikel.regal from tbllieferanten,tblartliefer,tblartikel  where tbllieferanten.lieferantennr ='" + lieferantennr + "' and tbllieferanten.lieferantennr=tblartliefer.lieferantennr and tblartliefer.artikelnr= tblartikel.artikelnr";		
		
		this.openCon();		
		try {
			 
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);

			while (rset.next()) {
				//tbl.getTableHeader();
				tblModel.addRow(new Object[] {rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7)});
				//System.out.println("Tabelle Lieferant und deren Beziehungen -- "+rset.getString(3)+"----"+rset.getString(4)+"----"+ rset.getString(5) +"----"+ rset.getString(6) + "----"+ rset.getString(7));
				setName(rset.getString(2).toString());
				
			
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            System.out.println("Fehler in OwnDerby.getLieferantenForMaskeArtikel(): " + e.getMessage());
            e.printStackTrace();
		}		
		/*for(int r = 0; r < tbl.getRowCount(); r++) {
			String str = getNameForID("SELECT LieferName FROM tblLieferanten WHERE LieferantenNr ='" + tbl.getValueAt(r, 1).toString() + "'");
			tbl.setValueAt(str, r, 2);
		}*/		
		
		this.closeCon();
		return tbl;
	}
	
	public void removeSelectedFromTable(JTable from) {
	        int[] rows = from.getSelectedRows();
	             TableModel tm = (DefaultTableModel) from.getModel();
	        //for (int row : rows) {
	          //  ((DefaultTableModel) tm).removeRow(from.convertRowIndexToModel(row));
	       // }
	        Arrays.sort(rows);
	        for(int i=rows.length-1; i>=0; i--) {
	            ((DefaultTableModel) tm).removeRow(rows[i]);    
	        }
	        //from.clearSelection();
	}
	
	public void setStartpunkt() {
	//	startPunkt = basics.getDatum();
	}
	
	public void copyTablesFromOldToNew() {
		List liste = null;
		//Kunden
		if(!checkUpdates("kunden")) {
			liste = getListe("tblkunden","kundennr",1);
			openCon();
			setStartpunkt();
			for(int i = 0; i < liste.getItemCount(); i++) {
				//query ="SELECT * FROM " + basics.app + "tblkunden WHERE kundennr='" + liste.getItem(i) + "'";
				String[] data = getData(query, 29, 0);
				writeToNewTableKunden(data);
			}
			updateUpdates("kunden, adresse, bankdata und fideoid", startPunkt, 0);
			closeCon();
			
			//Artikel
			liste = getListe("tblartikel","artikelnr",1);
			openCon();
			setStartpunkt();
			//if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("artikel","artikelnr","artikelnr",liste.getItem(i),0)) {
					//transferTable(3,"tblartikel","artikelnr",45,"artikel","nr","nr",basics.colName[3].length,liste.getItem(i));
				}
			}
			updateUpdates("artikel", startPunkt, 0);
			closeCon();
		}
		
		// Artikelgruppen
		if(!checkUpdates("artikelgruppe")) {
			liste = getListe("tblgruppen", "gnr",1);
			openCon();
			setStartpunkt();
			//if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("artikelgruppe","nr","nr",liste.getItem(i),0)) {
				//	transferTable(12,"tblgruppen","gnr",5,"artikelgruppe","nr","nr",basics.colName[12].length,liste.getItem(i));
				}
			}
			updateUpdates("artikelgruppe", startPunkt, 0);
			closeCon();
		}

		// Hauptgruppen
		if(!checkUpdates("Hauptgruppe")) {
			liste = getListe("tblhgruppen", "hgnr",1);		
			openCon();
			setStartpunkt();
			//if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("Hauptgruppe","nr","nr",liste.getItem(i),0)) {
				//	transferTable(11,"tblhgruppen","hgnr",2,"Hauptgruppe","nr","nr",basics.colName[11].length,liste.getItem(i));
				}
			}
			updateUpdates("Hauptgruppe", startPunkt, 0);
			closeCon();
		}
		
		// USteuer
		if(!checkUpdates("USteuer")) {
			liste = getListe("tblust","ustnr",1);
			openCon();
			setStartpunkt();
			//if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("USteuer","nr","nr",liste.getItem(i),0)) {
					//transferTable(15,"tblust","ustnr",3,"USteuer","nr","nr",basics.colName[15].length,liste.getItem(i));
				}
			}
			updateUpdates("USteuer", startPunkt, 0);
			closeCon();
		}
		
		// Preisgruppe
		if(!checkUpdates("Preisgruppe")) {
			liste = getListe("tblpgruppen","pgnr",1);
			openCon();
			setStartpunkt();
			//if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("Preisgruppe","nr","nr",liste.getItem(i),0)) {
			//		transferTable(6,"tblpgruppen","pgnr",2,"Preisgruppe","nr","nr",basics.colName[6].length,liste.getItem(i));
				}
			}
			updateUpdates("Preisgruppe", startPunkt, 0);
			closeCon();
		}
		
		// Barcode
		if(!checkUpdates("Barcode")) {
			liste = getListe("tblbarcode","bcnr",1);
			openCon();
			setStartpunkt();
	//		if(basics.showText) System.out.println("von: " + startPunkt);
			for(int i = 0; i < liste.getItemCount(); i++) {
				if(!checkDataInNewTable("Barcode","nr","nr",liste.getItem(i),0)) {
		//			transferTable(16,"tblbarcode","bcnr",3,"Barcode","nr","nr",basics.colName[16].length,liste.getItem(i));
				}
			}
			updateUpdates("Barcode", startPunkt, 0);
			closeCon();
		}
	}
	
	/** 20200213jf
	 * 
	 * @param tblNeuPos
	 * @param tblAlt
	 * @param colSelAlt
	 * @param colNumAlt
	 * @param tblNeu
	 * @param colSelNeu
	 * @param colComNeu
	 * @param colNumNeu
	 * @param comp
	 */
	
	private void transferTable(int tblNeuPos, String tblAlt,String colSelAlt,int colNumAlt,String tblNeu,String colSelNeu,String colComNeu,int colNumNeu, String comp) {
		String[] data = new String[colNumAlt];
		String[] datb = new String[colNumNeu];
		//query = "SELECT * FROM " + basics.app + tblAlt + " WHERE " + colSelAlt + "='" + comp + "'";
		data = this.getData(query, colNumAlt, 0);
		datb[ 0] = this.getNewID(tblNeu);
		datb[ 1] = comp;
		switch(tblNeuPos) {
			case  3:	// Artikel
				datb[ 2] = data[ 4];
				datb[ 3] = data[31];
				datb[ 4] = data[ 2];
				datb[ 5] = data[ 1];
				datb[ 6] = data[ 8];
				datb[ 7] = data[10];
				datb[ 8] = "";
				datb[ 9] = "";
				datb[10] = "";
				datb[11] = "";
				datb[12] = data[42];
				datb[13] = data[33];
				datb[14] = "";
				datb[15] = data[ 9];
				datb[16] = data[40];
				break;
			case 6:		// Preisgruppe
				datb[ 2] = data[ 1];
				datb[ 3] = "";
				break;
			case 11:	// Hauptgruppe
				datb[ 2] = data[ 1];
				break;
			case 12:	// Artikelgruppe	
				datb[ 2] = data[ 1];
				datb[ 3] = data[ 2];
				datb[ 4] = data[ 3];
				datb[ 5] = data[ 4];
				break;
			case 15:	// USteuer
				datb[ 2] = data[ 2];
				datb[ 3] = data[ 1];
				break;
			case 16:	// Barcode
				datb[ 2] = data[ 1];
				datb[ 3] = data[ 2];
				break;
		}
		//query = "INSERT INTO " + basics.app + tblNeu + " VALUES('";
		for(int x = 0; x < datb.length; x++) {
			query += datb[x];
			if(x < datb.length - 1) query += "','";
		}
		query +="')";
		updateStatement(query, 0);
	}
	
	//20200213jf ----- Aktualisierungen ueberpruefen -------------------------------------------------------	
	private boolean checkUpdates(String tbl) {
		boolean ok = false;
		//query = "SELECT thema FROM " + basics.app + "updates WHERE thema LIKE '" + tbl + "%'";		
		openCon();
		try {
			stmt	= con.createStatement();
			rset = stmt.executeQuery(query);
			while (rset.next()) {
				ok = true;
			}
			rset.close();
			stmt.close();
		} catch(Exception e) {}	
		closeCon();
		return ok;
	}
	// **********************************   lieferant_selec   ***********************************************
	public String[] lieferant_selec(String lieferantennr)
	
	{ 
		String[] templieferant = new String[21];
	
query = ("SELECT LIEFERANTNR,LIEFERANTNAME,KNNR,ADR,PLZ,ORT,LAND,TEL1,TEL2,FAX,EMAIL,WEB,STEUER,USTID,HRB,KONTO,BANK,BLZ,IBAN,BIC,GEBMENGE FROM LIEFERANTEN WHERE LIEFERANTNR ='" + lieferantennr + "' ");		
		
				
		try {
			 openCon();
			stmt	= con.createStatement();
			rset	= stmt.executeQuery(query);

			while (rset.next()) {
				//tbl.getTableHeader();
				System.out.println("System out Print****"+ rset.getString(2));
				
				templieferant[0]=rset.getString(1);
				System.out.println("System out Arry"+ templieferant[0]);
				templieferant[1]=rset.getString(2);
				templieferant[2]=rset.getString(3);
				templieferant[3]=rset.getString(4);
				templieferant[4]=rset.getString(5);
				templieferant[5]=rset.getString(6);
				templieferant[6]=rset.getString(7);
				templieferant[7]=rset.getString(8);
				templieferant[8]=rset.getString(9);
				templieferant[9]=rset.getString(10);
				templieferant[10]=rset.getString(11);
				templieferant[11]=rset.getString(12);
				templieferant[12]=rset.getString(13);
				templieferant[13]=rset.getString(14);
				templieferant[14]=rset.getString(15);
				templieferant[15]=rset.getString(16);
				templieferant[16]=rset.getString(17);
				templieferant[17]=rset.getString(18);
				templieferant[18]=rset.getString(19);
				templieferant[19]=rset.getString(20);
				templieferant[20]=rset.getString(21);
				
				System.out.println("System out Print"+ templieferant[20]);
				
			
			}
			stmt.close();
			rset.close();
		} catch(Exception e) {
            //System.out.println("Fehler in OwnDerby.getLieferantenForMaskeArtikel(): " + e.getMessage());
            e.printStackTrace();
		}	
		return templieferant;
		
	}
	
	//  +++++++++++++++++getLieferantAuflistung()  *********************************
			public JTable getLieferantAuflistung() {
			//String query	= "SELECT ARTIKELNUMMER,ARTIKELBEZEICHNUNG,LINIE FROM BRAUSESCHLAUCH ORDER BY ARTIKELBEZEICHNUNG,ARTIKELNUMMER,LINIE";
			//String query	= "SELECT IDGRP,ARTIKELNUMMER,ARTIKELBEZEICHNUNG,LINIE FROM BRAUSESCHLAUCH ORDER BY IDGRP";
				openCon();	
				String query	= "SELECT LIEFERANTNR,LIEFERANTNAME FROM LIEFERANTEN";
			tblModel1		= new DefaultTableModel(artTblHead2, 0);
			artTbl			= new JTable(tblModel1);
			//		"Nummer","Bezeichung","Linie"
		//	artTbl.getColumnModel().getColumn(0).setPreferredWidth(120);// .setMaxWidth(120);
			//artTbl.getColumnModel().getColumn(2).setMaxWidth(0);//*/
			
			try {
				
				 stmt = con.createStatement();
				 rset = stmt.executeQuery(query);
				 while ( rset.next()) { // 89 Spalten
					 //tblModel.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
					 tblModel1.addRow(new Object[] {rset.getString(1),rset.getString(2)});
					 
				 }    		
			 } catch(Exception e) { System.out.println("Fehler #1 in getLieferantAuflistung(): " + e.getMessage()); e.printStackTrace(); };		 	
			
			return artTbl;
		}
	//************           delete Lieferant  ***************************************************
			public void delete(String str )
		    {
				openCon();	
			String sum=null;


			try
			{
			    Statement stmt = con.createStatement();
			    //String stri=null;
			    //  stmt.executeUpdate( "delete * from client where id='"+str+"'"+"or id=null"  );
			    stmt.executeUpdate("delete  from lieferanten where lieferantnr='"+str+"'");
			   

			    stmt.close();
			}

			catch ( SQLException e )
			{
			    e.printStackTrace();
			}

		    }

	// *********************************     lieferant_  *******
		public void	lieferant_(String[] lief1) throws SQLException
			{
			 this.openCon();
			stmt = con.createStatement();
			  stmt.executeUpdate( "insert into lieferanten (LIEFERANTNR,LIEFERANTNAME,KNNR,ADR,PLZ,ORT,LAND,TEL1,TEL2,FAX,EMAIL,WEB,STEUER,USTID,HRB,KONTO,BANK,BLZ,IBAN,BIC,GEBMENGE) values("+"'"+lief1[0]+"',"+"'"+lief1[1]+"',"+"'"+lief1[2]+"',"+"'"+lief1[3]+"',"+"'"+lief1[4]+"',"+"'"+lief1[5]+"',"+"'"+lief1[6]+"',"+" '"+lief1[7]+"',"+" '"+lief1[8]+"' ,"+" '"+lief1[9]+"', "+" '"+lief1[10]+"'  ,"+" '"+lief1[11]+"', "+" '"+lief1[12]+"' ,"+" '"+lief1[13]+"' ,"+" '"+lief1[14]+"',"+" '"+lief1[15]+"',"+" '"+lief1[16]+"',"+" '"+lief1[17]+"',"+" '"+lief1[18]+"',"+"'"+lief1[19]+"',"+"'"+lief1[20]+"')");
			}
	
	public void updateUpdates(String tbl, String start, int n) {
		if(n == 1) openCon();
		//if(basics.showText) System.out.println("bis: " + basics.getDatum());
		if(!checkDataInNewTable("updates","thema","thema",tbl,0)) {
			String id = getNewID("updates");
			//query = "INSERT INTO " + basics.app + "updates VALUES('" + id + "','" + tbl + "','" + start + "','" + basics.getDatum() + "')";
			updateStatement(query, 0);
		}
		if(n == 1) closeCon();
	}
	public static  Connection getConnection()
	{
		
		openCon();
		return con;
	}
	
//  +++++++++++++++++getArtikel()  *********************************
			public JTable getArtikel() {
			//String query	= "SELECT ARTIKELNUMMER,ARTIKELBEZEICHNUNG,LINIE FROM BRAUSESCHLAUCH ORDER BY ARTIKELBEZEICHNUNG,ARTIKELNUMMER,LINIE";
			//String query	= "SELECT IDGRP,ARTIKELNUMMER,ARTIKELBEZEICHNUNG,LINIE FROM BRAUSESCHLAUCH ORDER BY IDGRP";
				String[] headerArtikel = { "ARTIKELNR","BESCHREIBUNG","EKPREIS","STKVKPREIS","EINHEITSMENGE","REGAL", "ADMIN" };
				openCon();	
				
				String query	= "SELECT ARTIKELNR,BESCHREIBUNG, EKPREIS, STKVKPREIS, EINHEITSMENGE, REGAL FROM TBLARTIKEL";
			tblModel1		= new DefaultTableModel( headerArtikel, 0);
			artTbl			= new JTable(tblModel1);
			//		"Nummer","Bezeichung","Linie"
		//	artTbl.getColumnModel().getColumn(0).setPreferredWidth(120);// .setMaxWidth(120);
			//artTbl.getColumnModel().getColumn(2).setMaxWidth(0);//*/
			
			try {
				
				 stmt = con.createStatement();
				 rset = stmt.executeQuery(query);
				 while ( rset.next()) { // 89 Spalten
					 //tblModel.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
					 tblModel1.addRow(new Object[] {rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6),});
					 
				 }    		
			 } catch(Exception e) { System.out.println("Fehler #1 in getArtikel(): " + e.getMessage()); e.printStackTrace(); };		 	
			
			return artTbl;
		}
	
			//Hauptgruppe2 ***********************************************
			public void	hauptgruppe2(String hgruppe) throws SQLException
			{
			 this.openCon();
			stmt = con.createStatement();
			  stmt.executeUpdate( "insert into HAUPTGRUPPE2 (HGRUPPE) values('"+hgruppe+"')");
			  //con.close();
			  //stmt.close();
			}
			
			// ***************************************************************************
			public String [] hgruppecombo() throws SQLException
			
			{ 
				String[] hgruppe = new String[20];
				int i=0;
		query = ("SELECT HGRUPPE from HAUPTGRUPPE2");		
				
						
				try {
					 openCon();
					stmt	= con.createStatement();
					rset	= stmt.executeQuery(query);

					while (rset.next()) {
						
						//System.out.println("System out Print****"+ rset.getString("HGRUPPE"));
						
						//combo.addItem(rset.getString("HGRUPPE"));
						hgruppe[i] = rset.getString("HGRUPPE");
						
						//System.out.println("System out Print****"+ hgruppe[i]);
						
					i++;
					}
					stmt.close();
					rset.close();
				} catch(Exception e) {
		            //System.out.println("Fehler in OwnDerby.getLieferantenForMaskeArtikel(): " + e.getMessage());
		            e.printStackTrace();
				}	
				
				return hgruppe;
			}
			//insert Untergruppe
			public void	untergruppe(String ugruppe, String hgruppe) throws SQLException
			{
			 this.openCon();
			stmt = con.createStatement();
			//stmt.executeQuery("select HGRUPPE from HAUPTGRUPPE2 where HGRUPPE='"+hgruppe+"'");
			 // stmt.executeUpdate( "insert into HAUPTGRUPPE2 (UGRUPPE) values('"+ugruppe+"') where HGRUPPE= '"+hgruppe+"'");
			
			stmt.executeUpdate("UPDATE HAUPTGRUPPE2 SET ugruppe ='"+ugruppe+"' WHERE HGRUPPE='"+hgruppe+"'");
			  con.close();
			  stmt.close();
			}
			// *******************************************************************************************************
			//**************************************************************
			 public static void main(String args[]) throws SQLException {
				 OwnDerby onderby = new OwnDerby();
						 //onderby.createTable2();
				// onderby.hgruppecombo();
			 }
}
