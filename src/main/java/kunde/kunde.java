/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kunde;

import de.creativaweb.artikel.*;
import de.creativaweb.database.OwnDerby;
import DbTest.*;
import controller.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nail
 */
public class kunde extends javax.swing.JFrame {
    /**
     * Creates new form startSeite
     */
    public kunde() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_artikel = new JButton();
        btn_kunde = new JButton();
        btn_lieferant = new JButton();
        btn_mitarbeiter = new JButton();
        btn_bestellungen = new JButton();
        btn_kundenAuftrag = new JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_neuerKunde = new JButton();
        btn_offeneKonten = new JButton();
        jLabel1 = new javax.swing.JLabel();
        field_kundenSuchen = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table_kunde = new JTable();
        btn_suchen = new JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(242, 242, 242));

        btn_artikel.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_artikel.setText("Artikel");
        btn_artikel.setBorder(null);
        btn_artikel.setBorderPainted(false);
        btn_artikel.setContentAreaFilled(false);
        btn_artikel.setFocusable(false);
        btn_artikel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_artikel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_kunde.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_kunde.setText("Kunde");
        btn_kunde.setBorder(null);
        btn_kunde.setBorderPainted(false);
        btn_kunde.setContentAreaFilled(false);
        btn_kunde.setFocusable(false);
        btn_kunde.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_kunde.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_lieferant.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_lieferant.setText("Lieferant");
        btn_lieferant.setBorder(null);
        btn_lieferant.setBorderPainted(false);
        btn_lieferant.setContentAreaFilled(false);
        btn_lieferant.setFocusable(false);
        btn_lieferant.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_lieferant.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_lieferant.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lieferantActionPerformed(evt);
            }
        });

        btn_mitarbeiter.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_mitarbeiter.setText("Mitarbeiter");
        btn_mitarbeiter.setBorder(null);
        btn_mitarbeiter.setBorderPainted(false);
        btn_mitarbeiter.setContentAreaFilled(false);
        btn_mitarbeiter.setFocusable(false);
        btn_mitarbeiter.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_mitarbeiter.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_bestellungen.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_bestellungen.setText("Bestellungen");
        btn_bestellungen.setBorder(null);
        btn_bestellungen.setBorderPainted(false);
        btn_bestellungen.setContentAreaFilled(false);
        btn_bestellungen.setFocusable(false);
        btn_bestellungen.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_bestellungen.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_kundenAuftrag.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_kundenAuftrag.setText("Kunden Auftrag");
        btn_kundenAuftrag.setBorder(null);
        btn_kundenAuftrag.setBorderPainted(false);
        btn_kundenAuftrag.setContentAreaFilled(false);
        btn_kundenAuftrag.setFocusable(false);
        btn_kundenAuftrag.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_kundenAuftrag.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        btn_neuerKunde.setBackground(new java.awt.Color(170, 170, 170));
        btn_neuerKunde.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_neuerKunde.setForeground(new java.awt.Color(255, 255, 255));
        btn_neuerKunde.setText("Neuer Kunde");
        btn_neuerKunde.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_neuerKunde.setMaximumSize(new java.awt.Dimension(142, 41));
        btn_neuerKunde.setOpaque(true);
        btn_neuerKunde.setSize(new java.awt.Dimension(142, 41));
        btn_neuerKunde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_neuerKundeMouseClicked(evt);
            }
        });

        btn_offeneKonten.setBackground(new java.awt.Color(170, 170, 170));
        btn_offeneKonten.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_offeneKonten.setForeground(new java.awt.Color(255, 255, 255));
        btn_offeneKonten.setText("Offene Konten");
        btn_offeneKonten.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_offeneKonten.setOpaque(true);
        btn_offeneKonten.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_offeneKontenMouseClicked(evt);
            }
        });

        jLabel1.setText("Kunden suchen");

        field_kundenSuchen.setSize(new java.awt.Dimension(300, 25));

        table_kunde.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {  },
            new String [] {
                "KundenNr", "Firmen Namen", "Vorname", "Nachname", "Strasse", "PLZ", "Ort", "Land", ""
            }
        ));
        table_kunde.setRowHeight(31);
        table_kunde.setShowGrid(false);
        table_kunde.setShowVerticalLines(true);
        jScrollPane1.setViewportView(table_kunde);


        btn_suchen.setText("suchen");
        btn_suchen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_suchenKundeMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btn_artikel)
                        .addGap(33, 33, 33)
                        .addComponent(btn_kunde)
                        .addGap(33, 33, 33)
                        .addComponent(btn_mitarbeiter)
                        .addGap(33, 33, 33)
                        .addComponent(btn_lieferant)
                        .addGap(33, 33, 33)
                        .addComponent(btn_bestellungen)
                        .addGap(33, 33, 33)
                        .addComponent(btn_kundenAuftrag))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(field_kundenSuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_suchen))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_neuerKunde, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_offeneKonten, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_artikel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kunde, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_bestellungen, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_mitarbeiter, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kundenAuftrag, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_lieferant, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_neuerKunde, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_offeneKonten, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(field_kundenSuchen, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(btn_suchen))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lieferantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lieferantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lieferantActionPerformed

    private void btn_offeneKontenMouseClicked(java.awt.event.MouseEvent evt) {
        offeneKonten.main(new String[0]);
    }

    private void btn_neuerKundeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_neuerKundeMouseClicked
        neuerKundeFrame.setVisible(true);
    }

    private void btn_suchenKundeMouseClicked(java.awt.event.MouseEvent evt) {
        String suchBegriff = field_kundenSuchen.getText();
        table_kunde.setModel(kundeController.kundenSuchen(suchBegriff));
    }



    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kunde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kunde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kunde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kunde.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kunde().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btn_artikel;
    private JButton btn_bestellungen;
    private JButton btn_kunde;
    private JButton btn_kundenAuftrag;
    private JButton btn_lieferant;
    private JButton btn_mitarbeiter;
    private JButton btn_neuerKunde;
    private JButton btn_offeneKonten;
    private JButton btn_suchen;
    private javax.swing.JTextField field_kundenSuchen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private JTable table_kunde;
    // End of variables declaration//GEN-END:variables
    private final neuerKunde neuerKundeFrame = new neuerKunde();
//    private final offeneKonten offeneKontenFrame = new offeneKonten();
    private dbConnection dbConnection = new dbConnection();
    private kundeController kundeController = new kundeController();

    //    Getters
    public JTable getTable_kunde() {
        return table_kunde;
    }

    public DbTest.dbConnection getDbConnection() {
        return dbConnection;
    }





}
