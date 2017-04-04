/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.gui;

import fit5148.teamd.dao.GuestDAO;
import fit5148.teamd.pojo.GuestFramePOJO;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import org.jdatepicker.impl.JDatePickerImpl;
//import org.jdatepicker.impl.UtilDateModel;

/**
 *
 * @author Vinh Phan
 */
public class GuestFrame extends javax.swing.JFrame {

    //JDatePickerImpl datePicker = GuestDAO.getDatePickerModel();
    private GuestFramePOJO guestFramePojo = new GuestFramePOJO();
    private GuestDAO guestDao = new GuestDAO();
    private Boolean clickUpdate = false;
    private Boolean clickRegister = false;
    private Boolean clickSelect = false;
    /**
     * Creates new form GuestFrame
     */
    public GuestFrame() {
        initComponents();
        injectComponents();
    }

    private void injectComponents() {
        jradioName.setSelected(true);
        jbtnGroupBy.add(jradioId);
        jbtnGroupBy.add(jradioName);
               
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnGroupBy = new javax.swing.ButtonGroup();
        jfSearchResult = new javax.swing.JFrame();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane = new javax.swing.JScrollPane();
        jtResult = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jbtnCancelSearch = new javax.swing.JButton();
        jbtnOkay = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jradioId = new javax.swing.JRadioButton();
        jradioName = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jtfSearchKey = new javax.swing.JTextField();
        jbtnCreate = new javax.swing.JButton();
        jbtnSearch = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jpanelEdit = new javax.swing.JPanel();
        jlMessage = new javax.swing.JLabel();
        jcbDelete = new javax.swing.JCheckBox();
        jbCancel = new javax.swing.JButton();
        jbApply = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jtfGuestId = new javax.swing.JTextField();
        jtfPreferences = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtfCheckIn = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jtfHotel = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jtfPersonId = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jtfTitle = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jtfFirstName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jtfDob = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jtfCountry = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jtfCity = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jtfStreet = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jtfPostcode = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jtfPhone = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jtfEmail = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jtfLastName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbUpdate = new javax.swing.JCheckBox();

        jfSearchResult.setTitle("Search Result");
        jfSearchResult.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jfSearchResult.setResizable(false);
        jfSearchResult.setSize(new java.awt.Dimension(450, 280));
        jfSearchResult.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                jfSearchResultWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                jfSearchResultWindowClosed(evt);
            }
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                jfSearchResultWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                jfSearchResultWindowOpened(evt);
            }
        });

        jPanel4.setPreferredSize(new java.awt.Dimension(400, 200));
        jPanel4.setLayout(new java.awt.CardLayout());

        jtResult.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jtResult.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtResultKeyPressed(evt);
            }
        });
        jScrollPane.setViewportView(jtResult);

        jPanel4.add(jScrollPane, "card2");

        jfSearchResult.getContentPane().add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setPreferredSize(new java.awt.Dimension(400, 100));

        jbtnCancelSearch.setText("Cancel");
        jbtnCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCancelSearchActionPerformed(evt);
            }
        });

        jbtnOkay.setText("Okay");
        jbtnOkay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnOkayActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(301, Short.MAX_VALUE)
                .addComponent(jbtnOkay, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCancelSearch)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnCancelSearch)
                    .addComponent(jbtnOkay))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jfSearchResult.getContentPane().add(jPanel5, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Guest Panel Control");
        setMinimumSize(new java.awt.Dimension(730, 310));
        setPreferredSize(new java.awt.Dimension(727, 360));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 60));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("GUESTS  PANEL");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 680, 40));

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jradioId.setText("By ID");

        jradioName.setSelected(true);
        jradioName.setText("By Name");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("Search:");

        jtfSearchKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchKeyActionPerformed(evt);
            }
        });
        jtfSearchKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtfSearchKeyKeyPressed(evt);
            }
        });

        jbtnCreate.setBackground(new java.awt.Color(255, 102, 102));
        jbtnCreate.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbtnCreate.setForeground(new java.awt.Color(255, 255, 255));
        jbtnCreate.setText("Register");
        jbtnCreate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jbtnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnCreateActionPerformed(evt);
            }
        });

        jbtnSearch.setBackground(new java.awt.Color(255, 153, 0));
        jbtnSearch.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        jbtnSearch.setForeground(new java.awt.Color(255, 255, 255));
        jbtnSearch.setText("Search");
        jbtnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnSearchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jtfSearchKey)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jradioName)
                            .addComponent(jradioId))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jbtnCreate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbtnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jSeparator2)
                    .addContainerGap()))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jradioId)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jradioName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jtfSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbtnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jbtnCreate)
                .addContainerGap())
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel6Layout.createSequentialGroup()
                    .addGap(38, 38, 38)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(202, Short.MAX_VALUE)))
        );

        jPanel2.add(jPanel6, java.awt.BorderLayout.LINE_START);

        jpanelEdit.setBackground(new java.awt.Color(255, 255, 255));
        jpanelEdit.setToolTipText("");
        jpanelEdit.setEnabled(false);
        jpanelEdit.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlMessage.setFont(new java.awt.Font("Tahoma", 2, 12)); // NOI18N
        jlMessage.setForeground(new java.awt.Color(255, 0, 51));
        jpanelEdit.add(jlMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 320, 20));

        jcbDelete.setBackground(new java.awt.Color(255, 255, 255));
        jcbDelete.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jcbDelete.setText("Do you want to Delete Guest's profile?");
        jcbDelete.setEnabled(false);
        jcbDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbDeleteActionPerformed(evt);
            }
        });
        jpanelEdit.add(jcbDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 270, 36));

        jbCancel.setText("Cancel");
        jpanelEdit.add(jbCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, -1, -1));

        jbApply.setText("Apply");
        jbApply.setEnabled(false);
        jbApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbApplyActionPerformed(evt);
            }
        });
        jpanelEdit.add(jbApply, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 220, 69, -1));

        jLabel4.setText("Guest ID");
        jpanelEdit.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jtfGuestId.setEnabled(false);
        jpanelEdit.add(jtfGuestId, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 98, -1));

        jtfPreferences.setEnabled(false);
        jpanelEdit.add(jtfPreferences, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 99, -1));

        jLabel5.setText("Preferences");
        jpanelEdit.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        jtfCheckIn.setEnabled(false);
        jpanelEdit.add(jtfCheckIn, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 99, -1));

        jLabel6.setText("Checked In(*)");
        jpanelEdit.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, -1, -1));

        jtfHotel.setEnabled(false);
        jpanelEdit.add(jtfHotel, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 99, -1));

        jLabel7.setText("Hotel ID");
        jpanelEdit.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, -1, -1));

        jtfPersonId.setEnabled(false);
        jpanelEdit.add(jtfPersonId, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 11, 99, -1));

        jLabel8.setText("Person ID");
        jpanelEdit.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(265, 14, -1, -1));

        jtfTitle.setEnabled(false);
        jpanelEdit.add(jtfTitle, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 39, 99, -1));

        jLabel9.setText("Title(*)");
        jpanelEdit.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jtfFirstName.setToolTipText("");
        jtfFirstName.setEnabled(false);
        jpanelEdit.add(jtfFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(322, 70, 99, -1));

        jLabel10.setText("First Name(*)");
        jpanelEdit.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 70, -1, -1));

        jtfDob.setEnabled(false);
        jpanelEdit.add(jtfDob, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 99, -1));

        jLabel11.setText("Last name(*)");
        jpanelEdit.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabel12.setText("DOB");
        jpanelEdit.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 135, -1, -1));

        jtfCountry.setToolTipText("country");
        jtfCountry.setEnabled(false);
        jpanelEdit.add(jtfCountry, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 99, -1));

        jLabel13.setText("Country");
        jpanelEdit.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jtfCity.setEnabled(false);
        jpanelEdit.add(jtfCity, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 11, 99, -1));

        jLabel14.setText("City");
        jpanelEdit.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(464, 14, -1, -1));

        jtfStreet.setEnabled(false);
        jpanelEdit.add(jtfStreet, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 39, 99, -1));

        jLabel15.setText("Street");
        jpanelEdit.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(453, 42, -1, -1));

        jtfPostcode.setEnabled(false);
        jpanelEdit.add(jtfPostcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 70, 99, -1));

        jLabel16.setText("Postcode");
        jpanelEdit.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(439, 73, -1, -1));

        jtfPhone.setEnabled(false);
        jpanelEdit.add(jtfPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 101, 99, -1));

        jLabel17.setText("Phone");
        jpanelEdit.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(449, 104, -1, -1));

        jtfEmail.setEnabled(false);
        jpanelEdit.add(jtfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(493, 132, 99, -1));

        jLabel18.setText("Email(*)");
        jpanelEdit.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 130, -1, -1));

        jtfLastName.setEnabled(false);
        jpanelEdit.add(jtfLastName, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 101, 99, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Message:");
        jpanelEdit.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        jcbUpdate.setBackground(new java.awt.Color(255, 255, 255));
        jcbUpdate.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
        jcbUpdate.setText("Do you want to update Guest's profile?");
        jcbUpdate.setEnabled(false);
        jcbUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbUpdateActionPerformed(evt);
            }
        });
        jpanelEdit.add(jcbUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 270, 36));

        jPanel2.add(jpanelEdit, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    
    private void jbApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbApplyActionPerformed
        // TODO add your handling code here:
        if(clickSelect){
            if(jcbUpdate.isSelected() && jcbDelete.isSelected()){
                jlMessage.setText("You can't select both Update & Delete option!");
                return;
            }
            if(jcbUpdate.isSelected()){
                // Update record
                if(guestDao.updateGuest(updateFormData(guestFramePojo))){
                    JOptionPane.showMessageDialog(null, "Updated Successfully!", "Notification", JOptionPane.INFORMATION_MESSAGE);             
                    returnPreviousFrame();
                }else{
                    jlMessage.setText("Update failed?");
                }
            }else if(jcbDelete.isSelected()){
                // Delete record
                if(guestDao.deleteGuest(guestFramePojo)){
                    JOptionPane.showMessageDialog(null, "Updated Successfully!", "Notification", JOptionPane.INFORMATION_MESSAGE);             
                    returnPreviousFrame();
                }
                else{
                    jlMessage.setText("Delete failed?");
                }
            }else{
                jlMessage.setText("Unable to click Apply!");
            }
        } else if(clickRegister){
            //  Insert new record to DB_B
            Integer tmp = -1;
            try {
                tmp = guestDao.createNewGuest(collectFormData());
            } catch (SQLException ex) {
                jlMessage.setText(ex.getMessage().toString());
                Logger.getLogger(GuestFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(tmp != -1){
                JOptionPane.showMessageDialog(null, "Registered Successfully!, "
                        + "Your Guest ID is: "+tmp, "Notification", JOptionPane.INFORMATION_MESSAGE);             
                returnPreviousFrame();
            }
            else{
                jlMessage.setText("Please fill out the missing fields");
            }
        }else
            jlMessage.setText("Click Toggle Error");
    }//GEN-LAST:event_jbApplyActionPerformed

    private void jcbDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbDeleteActionPerformed
        if(clickUpdate==false){
            jbApply.setEnabled(true);
            clickUpdate=true;
        }
        else{
            jbApply.setEnabled(false);
            clickUpdate=false;
        }
    }//GEN-LAST:event_jcbDeleteActionPerformed

    private void enableComponents(Boolean click){
        jtfCheckIn.setEnabled(click);
        jtfPreferences.setEnabled(click);
        jtfTitle.setEnabled(click);
        jtfFirstName.setEnabled(click);
        jtfDob.setEnabled(click);
        jtfCity.setEnabled(click);
        jtfStreet.setEnabled(click);
        jtfPostcode.setEnabled(click);
        jtfEmail.setEnabled(click);
        jtfCountry.setEnabled(click);
        jtfPhone.setEnabled(click);
        jtfLastName.setEnabled(click);
        jbApply.setEnabled(click);
        clickUpdate = click;
    }
    
    private void jbtnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCreateActionPerformed
        jpanelEdit.setEnabled(true);
        jcbUpdate.setEnabled(false);
        jcbDelete.setEnabled(false);
        enableComponents(true);
        jlMessage.setText(("Please fill out the form for Guest registration"));
        clickRegister = true;
    }//GEN-LAST:event_jbtnCreateActionPerformed

    private GuestFramePOJO collectFormData(){
        //  Validation missing
        GuestFramePOJO gf = new GuestFramePOJO();
        gf.setPreferences(jtfPreferences.getText());
        gf.setCheckedIn(jtfCheckIn.getText());
        gf.setF_Name(jtfFirstName.getText());
        gf.setL_Name(jtfLastName.getText());
        //gf.setDob(jtfDob.getText());
        gf.setCity(jtfCity.getText());
        gf.setStreet(jtfStreet.getText());
        gf.setPostCode(jtfPostcode.getText());
        if(!jtfPhone.getText().isEmpty())
            gf.setPh_no(Integer.parseInt(jtfPhone.getText()));
        gf.setEmail(jtfEmail.getText());
        gf.setTitle(jtfTitle.getText());
        gf.setCountry(jtfCountry.getText());
        return gf;
    }
    
    private GuestFramePOJO updateFormData(GuestFramePOJO gf){
        //  Validation missing
        gf.setPreferences(jtfPreferences.getText());
        gf.setCheckedIn(jtfCheckIn.getText());
        gf.setF_Name(jtfFirstName.getText());
        gf.setL_Name(jtfLastName.getText());
        //gf.setDob(jtfDob.getText());
        gf.setCity(jtfCity.getText());
        gf.setStreet(jtfStreet.getText());
        gf.setPostCode(jtfPostcode.getText());
        if(!jtfPhone.getText().isEmpty())
            gf.setPh_no(Integer.parseInt(jtfPhone.getText()));
        gf.setEmail(jtfEmail.getText());
        gf.setTitle(jtfTitle.getText());
        gf.setCountry(jtfCountry.getText());
        return gf;
    }
    
    private void jbtnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnSearchActionPerformed
        searchProcess();
        clickSelect = true;
    }//GEN-LAST:event_jbtnSearchActionPerformed

    private void searchProcess(){
        // Turn on the edit panel
        if(jtfSearchKey.getText()==null){
            jlMessage.setText("Please type up your searching keywords!");
            return;
        }
        else{
            jpanelEdit.setEnabled(true);
            jcbUpdate.setEnabled(true);
            jcbDelete.setEnabled(true);
            // Searching guest by condition
            if(jbtnGroupBy.getSelection().equals(jradioName.getModel())){
                //  Pop up the jTable Result frame
                jfSearchResult.setVisible(true);
                jfSearchResult.setEnabled(true);
                this.setEnabled(false);
            }
            else{
                String keyword = jtfSearchKey.getText();
                try {
                    guestFramePojo = guestDao.searchById(keyword);
                } catch (SQLException ex) {
                    jlMessage.setText("Can't search due to the connection");
                    Logger.getLogger(GuestFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                showUpData(guestFramePojo);
            }
        }
    }
    
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        System.out.println("Closingggggggggggggggggggggg");
    }//GEN-LAST:event_formWindowClosed

    private void jcbUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbUpdateActionPerformed
        // TODO add your handling code here:
        if(clickUpdate==false)
            enableComponents(true);
        else
            enableComponents(false);
        
    }//GEN-LAST:event_jcbUpdateActionPerformed

    private void jfSearchResultWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jfSearchResultWindowActivated
        // TODO add your handling code here:
        DefaultTableModel dtm = null;
        try {
            dtm = guestDao.searchByName(jtfSearchKey.getText());
        } catch (SQLException ex) {
            jlMessage.setText("Can't search Name due to the conenction");
            Logger.getLogger(GuestFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        jtResult.setModel(dtm);
        jScrollPane.setViewportView(jtResult);
    }//GEN-LAST:event_jfSearchResultWindowActivated

    private void jtResultKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtResultKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            //  Get the object
            guestFramePojo = guestDao.getListGF().get(jtResult.getSelectedRow());
            showUpData(guestFramePojo);
            
            returnGuestFrame();
        }else if(evt.getKeyCode()==KeyEvent.VK_ESCAPE){
            returnGuestFrame();
        }
    }//GEN-LAST:event_jtResultKeyPressed
    
    private void returnGuestFrame(){
        jfSearchResult.setVisible(false);
        this.setEnabled(true);
        this.setVisible(true);
    }
    
    private void jbtnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnCancelSearchActionPerformed
        returnGuestFrame();
    }//GEN-LAST:event_jbtnCancelSearchActionPerformed

    private void jbtnOkayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnOkayActionPerformed
        guestFramePojo = guestDao.getListGF().get(jtResult.getSelectedRow());
        showUpData(guestFramePojo);
        
        returnGuestFrame();
    }//GEN-LAST:event_jbtnOkayActionPerformed

    private void jtfSearchKeyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtfSearchKeyKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            searchProcess();
        }
    }//GEN-LAST:event_jtfSearchKeyKeyPressed

    private void jfSearchResultWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jfSearchResultWindowClosed
        // TODO add your handling code here:
        returnGuestFrame();
    }//GEN-LAST:event_jfSearchResultWindowClosed

    private void jfSearchResultWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jfSearchResultWindowDeactivated
        // TODO add your handling code here:
    }//GEN-LAST:event_jfSearchResultWindowDeactivated

    private void jfSearchResultWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_jfSearchResultWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_jfSearchResultWindowOpened

    private void jtfSearchKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchKeyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchKeyActionPerformed

    private void showUpData(GuestFramePOJO gf){
        if(gf.getGuestId()==null){
            jlMessage.setText("Couldn't load data from DB_B");
            return;
        }
        jtfGuestId.setText(gf.getGuestId().toString());
        jtfPreferences.setText(gf.getPreferences());
        jtfCheckIn.setText(Character.toString(gf.getCheckedIn()));
        jtfHotel.setText(gf.getHotelId().toString());
        jtfCountry.setText(gf.getCountry());
        jtfPersonId.setText(gf.getPd_id().toString());
        jtfTitle.setText(gf.getTitle());
        jtfFirstName.setText(gf.getF_Name());
        jtfLastName.setText(gf.getL_Name());
        //jtfDob.setText(gf.getDob().toString());
        jtfCity.setText(gf.getCity());
        jtfStreet.setText(gf.getStreet());
        jtfPostcode.setText(gf.getPostCode());
        jtfPhone.setText(gf.getPh_no().toString());
        jtfEmail.setText(gf.getEmail());
    }
    
    private void returnPreviousFrame(){
        // How to return?
        
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
            java.util.logging.Logger.getLogger(GuestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GuestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GuestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GuestFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GuestFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton jbApply;
    private javax.swing.JButton jbCancel;
    private javax.swing.JButton jbtnCancelSearch;
    private javax.swing.JButton jbtnCreate;
    private javax.swing.ButtonGroup jbtnGroupBy;
    private javax.swing.JButton jbtnOkay;
    private javax.swing.JButton jbtnSearch;
    private javax.swing.JCheckBox jcbDelete;
    private javax.swing.JCheckBox jcbUpdate;
    private javax.swing.JFrame jfSearchResult;
    private javax.swing.JLabel jlMessage;
    private javax.swing.JPanel jpanelEdit;
    private javax.swing.JRadioButton jradioId;
    private javax.swing.JRadioButton jradioName;
    private javax.swing.JTable jtResult;
    private javax.swing.JTextField jtfCheckIn;
    private javax.swing.JTextField jtfCity;
    private javax.swing.JTextField jtfCountry;
    private javax.swing.JTextField jtfDob;
    private javax.swing.JTextField jtfEmail;
    private javax.swing.JTextField jtfFirstName;
    private javax.swing.JTextField jtfGuestId;
    private javax.swing.JTextField jtfHotel;
    private javax.swing.JTextField jtfLastName;
    private javax.swing.JTextField jtfPersonId;
    private javax.swing.JTextField jtfPhone;
    private javax.swing.JTextField jtfPostcode;
    private javax.swing.JTextField jtfPreferences;
    private javax.swing.JTextField jtfSearchKey;
    private javax.swing.JTextField jtfStreet;
    private javax.swing.JTextField jtfTitle;
    // End of variables declaration//GEN-END:variables
}
