/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mitarbeiter;

import de.creativaweb.artikel.*;

/**
 *
 * @author Nail
 */
public class packer extends javax.swing.JFrame {

    /**
     * Creates new form startSeite
     */
    public packer() {
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

        btn_artikel = new javax.swing.JButton();
        btn_kunde = new javax.swing.JButton();
        btn_lieferant = new javax.swing.JButton();
        btn_mitarbeiter = new javax.swing.JButton();
        btn_bestellungen = new javax.swing.JButton();
        btn_kundenAuftrag = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
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
                .addComponent(btn_kundenAuftrag)
                .addContainerGap(448, Short.MAX_VALUE))
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
                .addContainerGap(650, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lieferantActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lieferantActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_lieferantActionPerformed

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
            java.util.logging.Logger.getLogger(packer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(packer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(packer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(packer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new packer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_artikel;
    private javax.swing.JButton btn_bestellungen;
    private javax.swing.JButton btn_kunde;
    private javax.swing.JButton btn_kundenAuftrag;
    private javax.swing.JButton btn_lieferant;
    private javax.swing.JButton btn_mitarbeiter;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
