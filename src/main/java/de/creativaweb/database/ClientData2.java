package de.creativaweb.database;

// $Id: ClientData2.java,v 1.1 2020/03/12 13:14:13 oldocvs Exp $


import javax.swing.ImageIcon;
import javax.imageio.ImageIO;

import java.io.ByteArrayInputStream;

import javax.xml.bind.DatatypeConverter;

import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.sql.rowset.RowSetMetaDataImpl;

import java.sql.SQLException;

import java.sql.Timestamp;

import java.util.Arrays;
import java.util.ArrayList;
import java.lang.RuntimeException;
import java.io.IOException;
import java.awt.Graphics2D;
import java.io.ByteArrayOutputStream;
import java.awt.Image;
import javax.swing.Icon;
import javax.imageio.ImageWriter;
import javax.imageio.ImageWriteParam;
import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.imageio.IIOImage;


/*

Kapselung der Daten der 'client'-Zeile, um unterschiedliche Datentypen
in einem "Stück" zurückgeben zu können 

Derzeit:  ImageIcon für das Bild , Strings für den Rest

*/



public class ClientData2
    implements Comparable
{

    private final static boolean DebugAusgabe = false;


    private ImageIcon image;

    private String[] datenArray;
    private String[] spaltenNamen;

    private int spaltenAnzahl = 0;

    private int[] vergleich_wichtigkeit = null;


    private ArrayList<String>  longSpalten ;
    private ArrayList<String>  booleanSpalten ;
    private ArrayList<String>  timestampSpalten ;

    private final static int maxlen_string_image = 32672;


    public static ImageIcon decodeToImage(String imageString) 
    {
        if (null == imageString) return null;
        if (imageString.length()<10) return null;

        BufferedImage image = null;
        byte[] imageByte;
        try 
	{
	    imageByte = DatatypeConverter.parseBase64Binary(imageString);
	    ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	    image = ImageIO.read(bis);
	    bis.close();
	    return new ImageIcon(image);
	} 
	catch (Exception e) 
	{
	    e.printStackTrace();
	    return null;
	}
       
    }


    public static String encodeToString_db(ImageIcon iic) 
    {
        if (null == iic) return null;
        
        String h = encodeToString(iic,false);
        Image ic = iic.getImage();
        Image newimg;
        ImageIcon tempicon;
        int breite = iic.getIconWidth();
        int hoehe = iic.getIconHeight();
        int verkl = 0;

        while (h.length() > maxlen_string_image) 
        {
            if (DebugAusgabe) System.out.println("Bild zu groß, verkleinerung um "+verkl+" Bildpunkte ("+h.length()+" Zeichen)");
            newimg = ic.getScaledInstance(breite-verkl, hoehe-verkl,  java.awt.Image.SCALE_SMOOTH);
            tempicon = new ImageIcon(newimg);
            h = encodeToString_jpeg(tempicon,0.85f);
            if (DebugAusgabe) System.out.println("neue Größe: "+h.length()+" Zeichen");
            if (h.length() > maxlen_string_image*5) verkl = verkl+25;
            if (h.length() > maxlen_string_image*4) verkl = verkl+20;
            if (h.length() > maxlen_string_image*3) verkl = verkl+15;
            if (h.length() > maxlen_string_image*2) verkl = verkl+10;
            if (h.length() > (maxlen_string_image+maxlen_string_image/2)) verkl = verkl+5;
            if (h.length() > (maxlen_string_image+maxlen_string_image/4)) verkl = verkl+5;
            if (h.length() > (maxlen_string_image+maxlen_string_image/8)) verkl = verkl+4;
            verkl = verkl+1;
        }
        return h;       
    }        

    public static String encodeToString(ImageIcon imgic,boolean jpeg) 
    {
        return encodeToString(imgic.getImage());
    }

    public static String encodeToString(ImageIcon imgic) 
    {
        return encodeToString(imgic,false) ;
    }

    public static String encodeToString(Image img)
    {
        return encodeToString(img,false); 
    }


    public static String encodeToString(Image img,boolean jpeg) 
    {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        String type = "png";
        if (jpeg) return encodeToString_jpeg(img);

        try 
        {
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
            
            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();
            
            ImageIO.write(bimage, type, bos);
            byte[] imageBytes = bos.toByteArray();
            
            imageString = DatatypeConverter.printBase64Binary(imageBytes);
            
            bos.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return imageString;
    }



    public static String encodeToString_jpeg(Image img) 
    {
        return encodeToString_jpeg(img, 0.8f) ;
    }


    public static String encodeToString_jpeg(ImageIcon img, float qualitaet) 
    {
        return encodeToString_jpeg(img.getImage(), qualitaet) ;
    }


    public static String encodeToString_jpeg(Image img, float qualitaet) 
    {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        // String type = "png";
        // if (jpeg) type = "jpg";

        try 
        {
            BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_3BYTE_BGR);
            // Draw the image on to the buffered image
            Graphics2D bGr = bimage.createGraphics();
            bGr.drawImage(img, 0, 0, null);
            bGr.dispose();


            ImageWriter jpgWriter = ImageIO.getImageWritersByFormatName("jpg").next();
            ImageWriteParam jpgWriteParam = jpgWriter.getDefaultWriteParam();
            jpgWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            jpgWriteParam.setCompressionQuality(qualitaet);

            // ImageOutputStream outputStream = createOutputStream(); // For example implementations see below
            //            jpgWriter.setOutput(outputStream);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
            jpgWriter.setOutput(new MemoryCacheImageOutputStream(baos));
            IIOImage outputImage = new IIOImage(bimage, null, null);
            jpgWriter.write(null, outputImage, jpgWriteParam);
            jpgWriter.dispose();
            baos.flush(); 
            byte[] imageBytes = baos.toByteArray(); 
            baos.close();

 
            imageString = DatatypeConverter.printBase64Binary(imageBytes);
            
            bos.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        return imageString;
    }




    private int Name_zu_Position(String s)
    {

        int position =  Arrays.asList(spaltenNamen).indexOf(s.toUpperCase());
        if (position>=0) 
	{
        	
	    return position;
	}
        
	return -1;
    }


    public int compareTo(Object cd2)
    {
	if (cd2 == null) throw new NullPointerException();
	
	int i;
	if (null == vergleich_wichtigkeit)
	{
	    vergleich_wichtigkeit = new int[datenArray.length];

	    for (i=0;i<datenArray.length;i++)
	    {
		vergleich_wichtigkeit[i]=i;
	    }
	}
	
	for (i=0;i<vergleich_wichtigkeit.length;i++)
	{
	    String w1 =  datenArray[vergleich_wichtigkeit[i]];
	    String w2 =  ((ClientData2) cd2).get_index(vergleich_wichtigkeit[i]);
	    
	    if (w1 == null)
	    {
		if (w2 != null)
		{
		    return -1000;
		}
		else
		{
		    // weiter
		}
	    }
	    else
	    {
		if (w2 == null)
		{
		    return 1000;
		}
		else
		{
		    int v = w1.compareTo(w2);
		    if (v != 0)
		    {
			return v;
		    }
		    else
		    {
			// weiter
		    }
		}
	    }
	}
	return 0;
    }

    public int [] vergleichsspaltennummern( int [] liste)
    {
	if (liste == null)
	{
	    return vergleich_wichtigkeit;
	}
	int  [] altliste = vergleich_wichtigkeit;
	vergleich_wichtigkeit = liste;
	return altliste;
    }


    public ClientData2(  ) // throws SQLException
    {
	spaltenAnzahl = 0;
	datenArray = new String[0];
	spaltenNamen = new String[0];
        longSpalten = new ArrayList<String>();
        booleanSpalten = new ArrayList<String>();
        timestampSpalten = new ArrayList<String>();
    }


    public ClientData2( ResultSet rs ) throws SQLException
    {
	String imagestring;
	ResultSetMetaData  metadaten = rs.getMetaData();
	int Spalten = metadaten.getColumnCount();

	spaltenAnzahl = Spalten;

	datenArray = new String[Spalten];
	spaltenNamen = new String[Spalten];
        longSpalten = new ArrayList<String>();
        booleanSpalten = new ArrayList<String>();
        timestampSpalten = new ArrayList<String>();

	int i;

	for (i=0;i<Spalten;i++)
	{
	    spaltenNamen[i] = metadaten.getColumnName(i+1).toUpperCase();
	    if (spaltenNamen[i].equals("IMAGE"))
	    {
		datenArray[i] = "";
		imagestring = rs.getString(i+1);
		image = decodeToImage(imagestring);
		// System.out.print("Bild gefunden ");
	    }
	    else
	    {
		String h = rs.getString(i+1);
		if (h==null)
		{
		    datenArray[i] = "";
		}
		else
		{
		    datenArray[i] = new String(h);
		}
	    }
	    if (DebugAusgabe) System.out.println(""+i+" "+spaltenNamen[i]);
	}

	

    }


    public ImageIcon getImage()
    {
	return image;
    }



    public String get_index(int i )
    {
	return datenArray[i];
    }

    public String get_indexname(int i )
    {
	return spaltenNamen[i];
    }

    /*
      public String get_index(String s )
      {
      String rg = "";
      int i=0;
      String vg = s.toUpperCase();

      try 
      {


      while (i<spaltenNamen.length && ! spaltenNamen[i].toUpperCase().equals(vg))
      {
      i++;
			
      };
      if (spaltenNamen[i].toUpperCase().equals(vg))
      {
      return datenArray[i];
      }
      }
      catch (Exception e)
      {
      }
      return "";
      }
    */
    
    public String get_index(String s )
    {
        int position = Name_zu_Position(s);
        if (position>=0) 
	{
	    return datenArray[position];
	}
        return null;
    }

    // Auf jeden Fall einen String zurückgeben
    // bei 'null' wird ein leerer String zurückgegeben
    // _es  ->  Ergebnis String
    // bei einem leeren String als Parameter gibt es eine Exception
    public String get_index_es(String s )
    {
        if (s.length() == 0) 
        {
            throw  new RuntimeException("ClientData: Leere Zeichenkette als Elementbezeichnung angegeben!");
        }
        String rg =  get_index(s);
        
        if (null == rg) return "";
        else return rg;
    }

    // kürzerer Alias 
    public String get_i_s(String s )
    {
        return get_index_es(s);
    }



    public boolean get_index_boolean(String s )
    {
	String h = get_index(s);
	if (ist_boolean(s))
	{
	    if (null == h) return false;
	    return h.equals("true");
	}
	return get_index_eb(s);
    }

    public boolean get_index_boolean(int i )
    {
	String h = get_index(i);
	if (ist_boolean(i))
	{
	    if (null == h) return false;
	    return h.equals("true");
	}
        return false;
    }


    // Auf jeden Fall einen booleschen Wert zurückgeben
    // bei 'null' wird false zurückgegeben
    // _eb  ->  Ergebnis Boolean
    // bei einem leeren String als Parameter gibt es eine Exception
    public boolean get_index_eb(String s )
    {
        if (s.length() == 0) 
        {
            throw  new RuntimeException("ClientData: Leere Zeichenkette als Elementbezeichnung angegeben!");
        }
        String rg =  get_index(s);
        
        if (null == rg) return false;

        rg = rg.toLowerCase().trim();

        return rg.equals("x") || rg.equals("ja") || rg.equals("true");
    }

    // kürzerer Alias 
    public boolean get_i_b(String s )
    {
        return get_index_eb(s);
    }


    public boolean get_index_eb(String s, String vorgabe )
    {
        if (s.length() == 0) 
        {
            throw  new RuntimeException("ClientData: Leere Zeichenkette als Elementbezeichnung angegeben!");
        }
        if (vorgabe.length() == 0) 
        {
            throw  new RuntimeException("ClientData: Leere Zeichenkette als boolsche Vergleichsvorgabe angegeben!");
        }

        String rg =  get_index(s);
        
        if (null == rg) return false;

        rg = rg.toLowerCase().trim();

        return rg.equals(vorgabe);
    }

    // kürzerer Alias 
    public boolean get_i_b(String s, String vorgabe )
    {
        return get_index_eb(s,vorgabe);
    }


    public ImageIcon get_index_image(String s, int bildgroesse, String vorgabe)
    {
        String h = get_index(s);
        ImageIcon ii = null;

        if (null != h) ii = decodeToImage(h);
        
        if (null == ii) ii = decodeToImage(vorgabe);

        if (null != ii)
        {
            int breite = ii.getIconWidth();
            int hoehe = ii.getIconHeight();
            if ((breite != bildgroesse) || (hoehe != bildgroesse))
            {
                Image ic = ii.getImage();
                ii = new ImageIcon(ic.getScaledInstance(bildgroesse, bildgroesse,  java.awt.Image.SCALE_SMOOTH));
            }
        }
        return ii;
    }
        
    
    public ImageIcon get_i_i(String s, int bildgroesse, String vorgabe)
    {
        return get_index_image( s,  bildgroesse, vorgabe);
    }


    public long get_index_long(String s)
    {
        return Long.parseLong(get_index(s));
    }

    public Timestamp get_timestamp(String s)
    {
	return Timestamp.valueOf(get_index(s));
    }



    public boolean gleich(int Index, ClientData2 cd2)
    {
	if (Index<0) return false;
	if (Index>=spaltenNamen.length) return false;
	if (cd2 == null) return false;
	String vw = cd2.get_index(spaltenNamen[Index]);
	if (vw == null) return datenArray[Index] == null;
	return datenArray[Index].equals(cd2.get_index(Index));
    }

    public boolean gleich(String strIndex, ClientData2 cd2)
    {
	if (cd2 == null) return false;
	String inhalt = this.get_index(strIndex);
	if (inhalt == null) return false;
	return inhalt.equals(cd2.get_index(strIndex));
    }

    public boolean[] gleich(ClientData2 cd2)
    {
	if (cd2 == null) return null;
	boolean[] rg = new boolean[spaltenNamen.length];
	int i;
	for (i=0;i<spaltenNamen.length;i++)
	{
	    if (datenArray[i]==null)
	    {
		rg[i] = cd2.get_index(i) == null;
	    }
	    else
	    {
		rg[i] = datenArray[i].equals(cd2.get_index(spaltenNamen[i]));
	    }
	}	
	return rg;
    }



    public String[] ungleich(ClientData2 cd2)
    {
	if (cd2 == null) return null;

        ArrayList<String> rg = new ArrayList<String>();

	int i;
	for (i=0;i<spaltenNamen.length;i++)
	{
	    if (datenArray[i]==null)
	    {
                /* nicht vorhanden -> kein Unterschied */
	    }
	    else
	    {
		if (!datenArray[i].equals(cd2.get_index(spaltenNamen[i])))
                {
                    rg.add(spaltenNamen[i]);
                }
	    }
	}	
        String[] a = new String[0];
	return rg.toArray(a);
    }

   
    public boolean set_index(int index, String inhalt)
    {
	if (index<0) return false;
	if (index>=spaltenNamen.length) return false;
	if (inhalt==null) return false;
	datenArray[index] = inhalt;
	return true;
    }


    
    public boolean set_index(String index, String inhalt)
    {
	if (index == null) return false;
	if (inhalt==null) return false;
        int position =  Arrays.asList(spaltenNamen).indexOf(index.toUpperCase());
        if (position>=0) 
	{
	    datenArray[position] = inhalt;
            return true;
	}

	return false;
    }

    public boolean add_Spalte_long(String Spaltenname, long wert )
    {
        if (Spaltenname == null) return false;
        if (Spaltenname.length()<1) return false;
        if (get_index(Spaltenname) != null) return false;
        
        String wertstring = Long.toString(wert);
        
        ArrayList<String> sp_daten = new ArrayList<String>(Arrays.asList(datenArray));
        ArrayList<String> sp_namen = new ArrayList<String>(Arrays.asList(spaltenNamen));

        longSpalten.add(Spaltenname.toUpperCase());
        if (DebugAusgabe) 
	{
	    System.out.println("-- add_spalte_long "+Spaltenname);
	    for (String s :  longSpalten)
	    {
		System.out.println("  "+s);
	    }
	    System.out.println("--");
	}

        sp_namen.add(Spaltenname.toUpperCase());
        sp_daten.add(wertstring);

	// datenArray = new String[sp_daten.size()];
	// spaltenNamen = new String[sp_namen.size()];

        datenArray = sp_daten.toArray(new String[0]);
        spaltenNamen = sp_namen.toArray(new String[0]);

	spaltenAnzahl = spaltenAnzahl+1;


        return true;
    }

    public boolean add_Spalte_boolean(String Spaltenname, boolean wert )
    {
        if (Spaltenname == null) return false;
        if (Spaltenname.length()<1) return false;
        if (get_index(Spaltenname) != null) return false;
        
        String wertstring = Boolean.toString(wert);
        
        ArrayList<String> sp_daten = new ArrayList<String>(Arrays.asList(datenArray));
        ArrayList<String> sp_namen = new ArrayList<String>(Arrays.asList(spaltenNamen));

        booleanSpalten.add(Spaltenname.toUpperCase());
        if (DebugAusgabe) 
	{
	    System.out.println("-- add_spalte_boolean "+Spaltenname);
	    for (String s :  booleanSpalten)
	    {
		System.out.println("  "+s);
	    }
	    System.out.println("--");
	}

        sp_namen.add(Spaltenname.toUpperCase());
        sp_daten.add(wertstring);

	// datenArray = new String[sp_daten.size()];
	// spaltenNamen = new String[sp_namen.size()];

        datenArray = sp_daten.toArray(new String[0]);
        spaltenNamen = sp_namen.toArray(new String[0]);

	spaltenAnzahl = spaltenAnzahl+1;


        return true;
    }

    public boolean add_Spalte_Timestamp(String Spaltenname, Timestamp zeitpunkt )
    {
        if (null == Spaltenname) return false;
        if (Spaltenname.length()<1) return false;
        if (get_index(Spaltenname) != null) return false;
	if (null == zeitpunkt) return false;

        String wertstring = zeitpunkt.toString();
        
        ArrayList<String> sp_daten = new ArrayList<String>(Arrays.asList(datenArray));
        ArrayList<String> sp_namen = new ArrayList<String>(Arrays.asList(spaltenNamen));

        timestampSpalten.add(Spaltenname.toUpperCase());
        if (DebugAusgabe) 
	{
	    System.out.println("-- add_spalte_Timestamp "+Spaltenname);
	    for (String s :  timestampSpalten)
	    {
		System.out.println("  "+s);
	    }
	    System.out.println("--");
	}

        sp_namen.add(Spaltenname.toUpperCase());
        sp_daten.add(wertstring);

	// datenArray = new String[sp_daten.size()];
	// spaltenNamen = new String[sp_namen.size()];

        datenArray = sp_daten.toArray(new String[0]);
        spaltenNamen = sp_namen.toArray(new String[0]);

	spaltenAnzahl = spaltenAnzahl+1;


        return true;
    }




    public boolean ist_long(int i)
    {
        return ist_long(get_indexname(i));
    }


    public boolean ist_long(String Spaltenname)
    {
        
        int position =  longSpalten.indexOf(Spaltenname.toUpperCase());
        if (DebugAusgabe) System.out.println("istlong("+Spaltenname+") = "+position); 
        if (position>=0) 
	{
	    return true;
	}
	return false;
    }

    public boolean ist_boolean(int i)
    {
        return ist_boolean(get_indexname(i));
    }

    public boolean ist_boolean(String Spaltenname)
    {
        
        int position =  booleanSpalten.indexOf(Spaltenname.toUpperCase());
        if (DebugAusgabe) System.out.println("ist_boolean("+Spaltenname+") = "+position); 
        if (position>=0) 
	{
	    return true;
	}
	return false;
    }


    public boolean add_Spalte_string(String Spaltenname, String wert, boolean upcase )
    {
        if (Spaltenname == null) return false;
        if (Spaltenname.length()<1) return false;
        if (get_index(Spaltenname) != null) return false;
	if (wert == null) return false;
	
        if (upcase) wert = wert.toUpperCase();

        ArrayList<String> sp_daten = new ArrayList<String>(Arrays.asList(datenArray));
        ArrayList<String> sp_namen = new ArrayList<String>(Arrays.asList(spaltenNamen));

        sp_namen.add(Spaltenname.toUpperCase());
        sp_daten.add(wert);

	// datenArray = new String[sp_daten.size()];
	// spaltenNamen = new String[sp_namen.size()];

        datenArray = sp_daten.toArray(new String[0]);
        spaltenNamen = sp_namen.toArray(new String[0]);

	spaltenAnzahl = spaltenAnzahl+1;


        return true;
    }




    public boolean add_Spalte_string(String Spaltenname, String wert )
    {
        return add_Spalte_string(Spaltenname, wert, false );
    }


    public boolean add_Spalte_stringupcase(String Spaltenname, String wert )
    {
        return add_Spalte_string(Spaltenname, wert, true );
    }




    /*  Kurzschreibweise für 'add_Spalte_string'  */
    public boolean a_S_s(String Spaltenname, String wert )
    {
        return add_Spalte_string(Spaltenname,  wert);
    }

    public boolean a_S_su(String Spaltenname, String wert )
    {
        return add_Spalte_stringupcase(Spaltenname,  wert);
    }


    public boolean add_Spalte_Image(String Spaltenname,Icon bildchen)
    {
        if (bildchen instanceof javax.swing.ImageIcon)
            return add_Spalte_string(Spaltenname, encodeToString_db((ImageIcon) bildchen));
        else return false;
    }
    
    public boolean a_S_i(String Spaltenname,Icon bildchen)
    {
        return add_Spalte_Image(Spaltenname,bildchen);
    }


    public boolean add_Spalte_Boolean_x(String Spaltenname,boolean zustand)
    {
        String zustandstext = zustand ? "x" : "";
        return add_Spalte_string(Spaltenname, zustandstext);
    }

    public boolean a_S_bx(String Spaltenname,boolean zustand)
    {
        return add_Spalte_Boolean_x(Spaltenname,zustand);
    }
    
    public boolean add_Spalte_Boolean_janein(String Spaltenname,boolean zustand)
    {
        String zustandstext = zustand ? "ja" : "nein";
        return add_Spalte_string(Spaltenname, zustandstext);
    }

    public boolean a_S_b_jn(String Spaltenname,boolean zustand)
    {
        return add_Spalte_Boolean_janein(Spaltenname,zustand);
    }



    public boolean del_Spalte(int Spaltennummer)
    {
	if (Spaltennummer<0) return false;
	if (Spaltennummer>=spaltenNamen.length) return false;

	       
        ArrayList<String> sp_daten = new ArrayList<String>(Arrays.asList(datenArray));
        ArrayList<String> sp_namen = new ArrayList<String>(Arrays.asList(spaltenNamen));

	sp_daten.remove(Spaltennummer);
	sp_namen.remove(Spaltennummer);

        datenArray = sp_daten.toArray(new String[0]);
        spaltenNamen = sp_namen.toArray(new String[0]);

	spaltenAnzahl = spaltenAnzahl-1;

        return true;
    }

    public boolean del_Spalte(String Spaltenname)
    {
        if (Spaltenname == null) return false;
        if (Spaltenname.length()<1) return false;



	int i = Name_zu_Position(Spaltenname);
        if (i<0) return false;
        
	
	return del_Spalte(i);
    }

    public int Spaltenanzahl()
    {
	return spaltenAnzahl;
    }

    public void print_columnnames()
    {
        for (String s: spaltenNamen)
        {
            System.out.print(s);
            System.out.print(" , ");
        }
        System.out.println(spaltenAnzahl);

    }


    public String toString()
    {
	StringBuffer sb = new StringBuffer();
	int i;

	for (i=0;i<spaltenAnzahl;i++)
	{
	    if (sb.length() > 0) sb.append(" ; ");
	    sb.append(spaltenNamen[i]);
	    sb.append(": ");
	    sb.append(datenArray[i]);
	}
	return sb.toString();
    }


	public void lieferant_selec(String text) {
		// TODO Auto-generated method stub
		
	}

}






