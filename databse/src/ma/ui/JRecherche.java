/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.ui;

import javax.swing.table.DefaultTableModel;
import ma.beans.Client;
import ma.beans.Service;
import ma.service.ClientService;
import ma.service.ServiceService;

/**
 *
 * @author ACER
 */
public class JRecherche extends javax.swing.JFrame {
    ServiceService ss;
    ClientService cs;
    DefaultTableModel model = null;
    String selectedValue ;
    
    
    
    
    
    public JRecherche() {
        initComponents();
        ss=new ServiceService();
        cs=new ClientService();
        model  = (DefaultTableModel) listCl.getModel();
        loadService();
       
        
    }
    
   void loadCli() {
        model.setRowCount(0);
        for (Service s : ss.findAll()) {
            if (s.getNom().equals(selectedValue)) {
                for (Client o : cs.findAll()) {
                    int nb1 = o.getService().getId();
                    int nb2 = s.getId();
                    if (nb1 == nb2) {
                        model.addRow(new Object[]{o.getId(), o.getNom(), o.getPrenom(), o.getDate(), o.getService().getNom()});

                    }
                 }
            }
        } 
    }


    
    void loadService(){
        for(Service o : ss.findAll()){
            jCombo.addItem(o.getNom());
        }
            
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jCombo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCl = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Veuillez selecionner un service");

        jCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboActionPerformed(evt);
            }
        });

        listCl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nom", "Prenom", "Date"
            }
        ));
        jScrollPane1.setViewportView(listCl);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(jCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(69, 69, 69)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(203, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboActionPerformed
        // TODO add your handling code here:
        
        
             selectedValue  = jCombo.getSelectedItem().toString(); 
             loadCli();
         
    }//GEN-LAST:event_jComboActionPerformed

    

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jCombo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable listCl;
    // End of variables declaration//GEN-END:variables
}
