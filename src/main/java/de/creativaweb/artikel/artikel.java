/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.creativaweb.artikel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import kunde.kunde;
/**
 *
 * @author Nail
 */
public class artikel extends javax.swing.JFrame {
    /**
     * Creates new form startSeite
     */
    public artikel() {
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

        jButton1 = new javax.swing.JButton();
        btn_kunde = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btn_neuerArtikel = new javax.swing.JButton();
        btn_neuerPfand = new javax.swing.JButton();
        btn_angebot = new javax.swing.JButton();
        btn_neueGruppe = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(242, 242, 242));

        jButton1.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton1.setText("Artikel");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusable(false);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btn_kunde.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        btn_kunde.setText("Kunde");
        btn_kunde.setBorder(null);
        btn_kunde.setBorderPainted(false);
        btn_kunde.setContentAreaFilled(false);
        btn_kunde.setFocusable(false);
        btn_kunde.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_kunde.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_kunde.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kundeMouseClicked(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton3.setText("Lieferant");
        jButton3.setBorder(null);
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton4.setText("Mitarbeiter");
        jButton4.setBorder(null);
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton5.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton5.setText("Bestellungen");
        jButton5.setBorder(null);
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jButton7.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jButton7.setText("Kunden Auftrag");
        jButton7.setBorder(null);
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        btn_neuerArtikel.setBackground(new java.awt.Color(170, 170, 170));
        btn_neuerArtikel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_neuerArtikel.setForeground(new java.awt.Color(255, 255, 255));
        btn_neuerArtikel.setText("Neuer Artikel");
        btn_neuerArtikel.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_neuerArtikel.setMaximumSize(new java.awt.Dimension(142, 41));
        btn_neuerArtikel.setOpaque(true);
        btn_neuerArtikel.setSize(new java.awt.Dimension(142, 41));
        btn_neuerArtikel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_neuerArtikelMouseClicked(evt);
            }
        });

        btn_neuerPfand.setBackground(new java.awt.Color(170, 170, 170));
        btn_neuerPfand.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_neuerPfand.setForeground(new java.awt.Color(255, 255, 255));
        btn_neuerPfand.setText("Neuer Pfand");
        btn_neuerPfand.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_neuerPfand.setOpaque(true);
        btn_neuerPfand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_neuerPfandMouseClicked(evt);
            }
        });

        btn_angebot.setBackground(new java.awt.Color(170, 170, 170));
        btn_angebot.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_angebot.setForeground(new java.awt.Color(255, 255, 255));
        btn_angebot.setText("ANGEBOT");
        btn_angebot.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_angebot.setOpaque(true);
        btn_angebot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_angebotMouseClicked(evt);
            }
        });

        btn_neueGruppe.setBackground(new java.awt.Color(170, 170, 170));
        btn_neueGruppe.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        btn_neueGruppe.setForeground(new java.awt.Color(255, 255, 255));
        btn_neueGruppe.setText("Neue Gruppe");
        btn_neueGruppe.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        btn_neueGruppe.setOpaque(true);
        btn_neueGruppe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_neueGruppeMouseClicked(evt);
            }
        });

        jLabel1.setText("Suche Artikel");

        jTextField1.setSize(new java.awt.Dimension(300, 25));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ArtikelNr", "Artikel Name", "Menge", "Verkaufspreis", "Stückpreisl", "Einheit", "Regal", ""
            }
        ));
        jTable1.setRowHeight(31);
        jTable1.setShowGrid(false);
        jTable1.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(33, 33, 33)
                        .addComponent(btn_kunde)
                        .addGap(33, 33, 33)
                        .addComponent(jButton4)
                        .addGap(33, 33, 33)
                        .addComponent(jButton3)
                        .addGap(33, 33, 33)
                        .addComponent(jButton5)
                        .addGap(33, 33, 33)
                        .addComponent(jButton7))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_neuerArtikel, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_neuerPfand, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_angebot, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_neueGruppe, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_kunde, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_neuerArtikel, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btn_neuerPfand, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_angebot, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_neueGruppe, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 513, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btn_neuerPfandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_neuerPfandMouseClicked
        // TODO add your handling code here:
        neuerPfandFrame.setVisible(true);
    }//GEN-LAST:event_btn_neuerPfandMouseClicked

    private void btn_angebotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_angebotMouseClicked
        angebotFrame.setVisible(true);
    }//GEN-LAST:event_btn_angebotMouseClicked

    private void btn_neueGruppeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_neueGruppeMouseClicked
        gruppeFrame.setVisible(true);
    }//GEN-LAST:event_btn_neueGruppeMouseClicked

    private void btn_neuerArtikelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_neuerArtikelMouseClicked
        neuerArtikelFrame.setVisible(true);
    }//GEN-LAST:event_btn_neuerArtikelMouseClicked

    private void btn_kundeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kundeMouseClicked
        kundeFrame.setVisible(true);
    }//GEN-LAST:event_btn_kundeMouseClicked

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
            java.util.logging.Logger.getLogger(artikel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(artikel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(artikel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(artikel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new artikel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_angebot;
    private javax.swing.JButton btn_kunde;
    private javax.swing.JButton btn_neueGruppe;
    private javax.swing.JButton btn_neuerArtikel;
    private javax.swing.JButton btn_neuerPfand;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    private final neuerArtikel neuerArtikelFrame = new neuerArtikel();
    private final neuerPfand neuerPfandFrame = new neuerPfand();
    private final angebot angebotFrame = new angebot();
    private final gruppe gruppeFrame = new gruppe();
    private final kunde kundeFrame = new kunde();
}
