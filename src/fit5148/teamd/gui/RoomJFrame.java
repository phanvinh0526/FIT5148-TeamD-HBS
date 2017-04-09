/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.gui;

import fit5148.teamd.dao.FacilityDAO;
import fit5148.teamd.dao.RoomDAO;
import fit5148.teamd.dao.Util;
import fit5148.teamd.pojo.FacilityPOJO;
import fit5148.teamd.pojo.RoomFramePOJO;
import fit5148.teamd.pojo.RoomPOJO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Varun
 */
public class RoomJFrame extends javax.swing.JFrame {

    /**
     * Creates new form RoomJFrame
     */
    int currentHotelId;
    RoomDAO rdao;
    FacilityDAO fdao;
    ArrayList<FacilityPOJO> facArr;
    DefaultTableModel searchResultModel, updateTablModel, createTableModel;
    int updRID = 0;
    int updRMaxCap, crtRMaxCap;
    String updRNo, crtRNo, updRType = "", crtRType, updRFac = "", crtRFacStr, currRNo;
    Float updRPrice, crtRPrice;
    HashMap<Integer, String> crtRFac;
    HashMap<String, Integer> fac;
    boolean creating = false;

    public RoomJFrame() {
        initComponents();
        injectComponents();
        this.currentHotelId = 1;
        try {
            facArr = new ArrayList<>();
            fdao = new FacilityDAO();
            rdao = new RoomDAO();
            fac = new HashMap<>();
            crtRFac = new HashMap<>();
        } catch (SQLException ex) {
            Util.setErrorMessage(glbErrLabel, "There was a problem accessing Database. Please reopen");
            Logger.getLogger(CustomerJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    private void injectComponents() {
        initJFrame(this, true, true);
    }
    
    private void initJFrame(JFrame jf, Boolean enable, Boolean visible){
        jf.pack();
        jf.setLocationRelativeTo(null);
        jf.setVisible(visible);
        jf.setEnabled(enable);
    }
    
    private void returnPreviousFrame(){
        // How to return?
        
    }
    
    private void returnMainFrame(){
        this.removeAll();
        this.dispose();
    }
    
    private void init() {
        manRoomTabbedPane.setEnabledAt(2, false);
        manRoomTabbedPane.setEnabledAt(3, false);
        rmSrchResTable.setEnabled(false);
        updateRoomButton.setEnabled(false);
        delRoomButton.setEnabled(false);

        Util.clearErrorMessage(glbErrLabel);
        //memTierSrchResTableComboBox = new JComboBox();
        facArr = fdao.getAllFacilities();
        if (facArr.size() == 0) {
            Util.setErrorMessage(glbErrLabel, "There are no facilities available for rooms.Please enter new manually");
        } else {
            for (int i = 0; i < facArr.size(); i++) {
                fac.put(facArr.get(i).getDescr(), facArr.get(i).getFacility_Id());
                rFacComboBox.addItem(facArr.get(i).getDescr());
                rFacComboBox1.addItem(facArr.get(i).getDescr());

            }
        }
        createTableModel = ((DefaultTableModel) (crtRAddFacTable.getModel()));

        rmSrchResTable.getSelectionModel().addListSelectionListener(new RowSelectionListener());
        rmSrchResTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }

    private boolean validateUpdateVars() {
        // int updateRID,updRMaxCap;
        // String updRNo,updRType,updRFac;
        // Float updRPrice;
        if (rUpNoTextField2.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Room number cannot be empty");
            return false;
        } else {
            updRNo = rUpNoTextField2.getText();
        }
        if (maxCapFormattedTextField1.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "max capacity cannot be empty");
            return false;
        } else {
            updRMaxCap = Integer.parseInt(maxCapFormattedTextField1.getText());
        }
        if (rPriceFormattedTextField2.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Price cannot be empty");
            return false;
        } else {
            updRPrice = Float.parseFloat(rPriceFormattedTextField2.getText());
        }
        if (rTypeTextField.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Warning - Room has been added without a type");
            updRType = rTypeTextField.getText();
        } else {
            updRType = rTypeTextField.getText();
        }
        if (upRfacTable.getRowCount() == 0) {
            Util.setErrorMessage(glbErrLabel, "Warning - Room has been added without any facility");
            updRFac = "";
        } else {
            updRFac = "";
            for (int i = 0; i < upRfacTable.getRowCount(); i++) {

                if (updRFac.compareTo("") == 0) {
                    updRFac += upRfacTable.getValueAt(i, 0).toString();
                } else {
                    updRFac += "+" + upRfacTable.getValueAt(i, 0).toString();
                }

            }

        }
        return true;

    }

    private boolean validateCreateVars() {
        if (rAddNoTextField.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Room number cannot be empty");
            return false;
        } else {
            crtRNo = rAddNoTextField.getText();
        }
        if (maxCapFormattedTextField.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "max capacity cannot be empty");
            return false;
        } else {
            crtRMaxCap = Integer.parseInt(maxCapFormattedTextField.getText());
        }
        if (rPriceFormattedTextField1.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Price cannot be empty");
            return false;
        } else {
            crtRPrice = Float.parseFloat(rPriceFormattedTextField1.getText());
        }
        if (addRTypeTextField1.getText().compareTo("") == 0) {
            Util.setErrorMessage(glbErrLabel, "Warning - Room has been added without a type");
            crtRType = addRTypeTextField1.getText();
        } else {
            crtRType = addRTypeTextField1.getText();
        }
        if (crtRAddFacTable.getRowCount() == 0) {
            Util.setErrorMessage(glbErrLabel, "Warning - Room has been added without any facility");
            crtRFacStr = "";
        } else {
            crtRFacStr = "";
            crtRFac.clear();
            for (int i = 0; i < crtRAddFacTable.getRowCount(); i++) {

                crtRFac.put(Integer.parseInt(crtRAddFacTable.getValueAt(i, 0).toString()), crtRAddFacTable.getValueAt(i, 1).toString());

            }

        }
        return true;
    }

    private class RowSelectionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {

            updateRoomButton.setEnabled(true);
            delRoomButton.setEnabled(true);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        manRoomTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        srchRoomsButton = new javax.swing.JButton();
        resultPanel3 = new javax.swing.JPanel();
        updateRoomButton = new javax.swing.JButton();
        delRoomButton = new javax.swing.JButton();
        jbCancel = new javax.swing.JButton();
        rFacComboBox = new javax.swing.JComboBox<>();
        vAllButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        rmSrchResTable = new javax.swing.JTable();
        createRoomPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        maxCapFormattedTextField = new javax.swing.JFormattedTextField();
        addFacButton = new javax.swing.JButton();
        addNewRoomButton = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        rPriceFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        crtRAddFacTable = new javax.swing.JTable();
        rmvAllFacButton = new javax.swing.JButton();
        rAddNoTextField = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        addRTypeTextField1 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        addRDescrTextField2 = new javax.swing.JTextField();
        updateRoomPanel = new javax.swing.JPanel();
        maxCapFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        addFacButton2 = new javax.swing.JButton();
        rPriceFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        addNewRoomButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        upRfacTable = new javax.swing.JTable();
        rmvAllFacButton1 = new javax.swing.JButton();
        rTypeTextField = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        rUpNoTextField2 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        updRDescrTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        addFacPanel = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        addFacButton1 = new javax.swing.JButton();
        rFacComboBox1 = new javax.swing.JComboBox<>();
        CancelBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        glbErrLabel = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Search for a room by facility");

        jLabel3.setText("Facility");

        srchRoomsButton.setText("Search");
        srchRoomsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                srchRoomsButtonActionPerformed(evt);
            }
        });

        updateRoomButton.setText("Update");
        updateRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateRoomButtonActionPerformed(evt);
            }
        });

        delRoomButton.setText("Delete");
        delRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delRoomButtonActionPerformed(evt);
            }
        });

        jbCancel.setText("Cancel");
        jbCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resultPanel3Layout = new javax.swing.GroupLayout(resultPanel3);
        resultPanel3.setLayout(resultPanel3Layout);
        resultPanel3Layout.setHorizontalGroup(
            resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel3Layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(updateRoomButton)
                .addGap(256, 256, 256)
                .addComponent(delRoomButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 233, Short.MAX_VALUE)
                .addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        resultPanel3Layout.setVerticalGroup(
            resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultPanel3Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(resultPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateRoomButton)
                    .addComponent(delRoomButton))
                .addContainerGap(34, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jbCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        vAllButton.setText("View All");
        vAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vAllButtonActionPerformed(evt);
            }
        });

        rmSrchResTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Rm_ID", "Room No.", "Price", "Max Capacity", "All Facilities", "Type", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(rmSrchResTable);
        if (rmSrchResTable.getColumnModel().getColumnCount() > 0) {
            rmSrchResTable.getColumnModel().getColumn(0).setMaxWidth(50);
            rmSrchResTable.getColumnModel().getColumn(1).setMaxWidth(80);
            rmSrchResTable.getColumnModel().getColumn(2).setMaxWidth(60);
            rmSrchResTable.getColumnModel().getColumn(3).setMaxWidth(120);
            rmSrchResTable.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(214, 214, 214)
                                .addComponent(jLabel3)
                                .addGap(104, 104, 104)
                                .addComponent(rFacComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(357, 357, 357)
                                .addComponent(srchRoomsButton)
                                .addGap(33, 33, 33)
                                .addComponent(vAllButton))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(372, 372, 372)
                                .addComponent(jLabel2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(resultPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rFacComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(24, 24, 24)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(srchRoomsButton)
                    .addComponent(vAllButton))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        manRoomTabbedPane.addTab("View and Update", jPanel1);

        jLabel4.setText("Create a new Room");

        jLabel5.setText("Room number");

        jLabel6.setText("Max Capacity");

        jLabel7.setText("Facilities");

        maxCapFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        addFacButton.setText("Add Existing Facility");
        addFacButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacButtonActionPerformed(evt);
            }
        });

        addNewRoomButton.setText("Add New Room");
        addNewRoomButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewRoomButtonActionPerformed(evt);
            }
        });

        jLabel9.setText("Price");

        rPriceFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        crtRAddFacTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Facility no.", "Desc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(crtRAddFacTable);

        rmvAllFacButton.setText("Remove All");
        rmvAllFacButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmvAllFacButtonActionPerformed(evt);
            }
        });

        jLabel20.setText("Room Type");

        jLabel21.setText("Description");

        javax.swing.GroupLayout createRoomPanelLayout = new javax.swing.GroupLayout(createRoomPanel);
        createRoomPanel.setLayout(createRoomPanelLayout);
        createRoomPanelLayout.setHorizontalGroup(
            createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createRoomPanelLayout.createSequentialGroup()
                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createRoomPanelLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel4))
                    .addGroup(createRoomPanelLayout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel9)
                            .addComponent(jLabel20)
                            .addComponent(jLabel21))
                        .addGap(165, 165, 165)
                        .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createRoomPanelLayout.createSequentialGroup()
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addRTypeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rPriceFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addFacButton)
                                    .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(rAddNoTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
                                        .addComponent(maxCapFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(createRoomPanelLayout.createSequentialGroup()
                                .addComponent(addRDescrTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addGap(35, 35, 35)
                .addComponent(rmvAllFacButton)
                .addGap(58, 58, 58))
            .addGroup(createRoomPanelLayout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(addNewRoomButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        createRoomPanelLayout.setVerticalGroup(
            createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(createRoomPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(createRoomPanelLayout.createSequentialGroup()
                        .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(createRoomPanelLayout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(createRoomPanelLayout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(rAddNoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(24, 24, 24)
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(maxCapFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(addFacButton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(rPriceFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel20)
                                    .addComponent(addRTypeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 37, Short.MAX_VALUE)))
                        .addGap(43, 43, 43))
                    .addGroup(createRoomPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(rmvAllFacButton)
                        .addGap(123, 123, 123)
                        .addGroup(createRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(addRDescrTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(addNewRoomButton)
                .addGap(26, 26, 26))
        );

        manRoomTabbedPane.addTab("Create", createRoomPanel);

        maxCapFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        jLabel11.setText("Max Capacity");

        jLabel12.setText("Facilities");

        addFacButton2.setText("Add Existing Facility");
        addFacButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacButton2ActionPerformed(evt);
            }
        });

        rPriceFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));

        jLabel13.setText("Price");

        addNewRoomButton1.setText("Update Room");
        addNewRoomButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewRoomButton1ActionPerformed(evt);
            }
        });

        upRfacTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Facility Desc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(upRfacTable);

        rmvAllFacButton1.setText("Remove All");
        rmvAllFacButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmvAllFacButton1ActionPerformed(evt);
            }
        });

        jLabel18.setText("Room no");

        jLabel19.setText("Room Type");

        jLabel22.setText("Description");

        jLabel10.setText("Update Room");

        javax.swing.GroupLayout updateRoomPanelLayout = new javax.swing.GroupLayout(updateRoomPanel);
        updateRoomPanel.setLayout(updateRoomPanelLayout);
        updateRoomPanelLayout.setHorizontalGroup(
            updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateRoomPanelLayout.createSequentialGroup()
                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateRoomPanelLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jLabel22))
                        .addGap(171, 171, 171)
                        .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateRoomPanelLayout.createSequentialGroup()
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rPriceFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addFacButton2)
                                    .addComponent(maxCapFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(rUpNoTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(81, 81, 81)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(rmvAllFacButton1))
                            .addComponent(updRDescrTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(updateRoomPanelLayout.createSequentialGroup()
                        .addGap(413, 413, 413)
                        .addComponent(jLabel10))
                    .addGroup(updateRoomPanelLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(addNewRoomButton1)))
                .addContainerGap(71, Short.MAX_VALUE))
        );
        updateRoomPanelLayout.setVerticalGroup(
            updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateRoomPanelLayout.createSequentialGroup()
                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateRoomPanelLayout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateRoomPanelLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel10)
                        .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(updateRoomPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(rUpNoTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(21, 21, 21)
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel11)
                                    .addComponent(maxCapFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(addFacButton2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(rPriceFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(rTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(updateRoomPanelLayout.createSequentialGroup()
                                .addGap(93, 93, 93)
                                .addComponent(rmvAllFacButton1)))))
                .addGap(34, 34, 34)
                .addGroup(updateRoomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(updRDescrTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addComponent(addNewRoomButton1)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        manRoomTabbedPane.addTab("Update", updateRoomPanel);

        jLabel8.setText("Facility Description");

        addFacButton1.setText("Add");
        addFacButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addFacButton1ActionPerformed(evt);
            }
        });

        CancelBtn.setText("Done/Cancel");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addFacPanelLayout = new javax.swing.GroupLayout(addFacPanel);
        addFacPanel.setLayout(addFacPanelLayout);
        addFacPanelLayout.setHorizontalGroup(
            addFacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addFacPanelLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 441, Short.MAX_VALUE)
                .addComponent(rFacComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addGroup(addFacPanelLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(addFacButton1)
                .addGap(58, 58, 58)
                .addComponent(CancelBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addFacPanelLayout.setVerticalGroup(
            addFacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addFacPanelLayout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(addFacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(rFacComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(addFacPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addFacButton1)
                    .addComponent(CancelBtn))
                .addContainerGap(272, Short.MAX_VALUE))
        );

        manRoomTabbedPane.addTab("Add Facility", addFacPanel);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manage Rooms");

        glbErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        glbErrLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(manRoomTabbedPane)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(118, 118, 118)
                        .addComponent(glbErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 771, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(400, 400, 400)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(glbErrLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 16, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manRoomTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void srchRoomsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_srchRoomsButtonActionPerformed
        // TODO add your handling code here:
        Util.clearErrorMessage(glbErrLabel);
        ArrayList<RoomFramePOJO> rms = rdao.getRoomsByFac(rFacComboBox.getSelectedItem().toString(), currentHotelId);
        if (rms == null) {
            Util.setErrorMessage(glbErrLabel, "Some error occured");
        } else if (rms.size() == 0) {
            Util.setErrorMessage(glbErrLabel, "no rooms with this facility exist");
        } else {
            rmSrchResTable.setEnabled(true);
            Object[] tableCustData = new Object[7];
            int i = 0;
            searchResultModel = ((DefaultTableModel) (rmSrchResTable.getModel()));
            searchResultModel.setRowCount(0);
            for (RoomFramePOJO entry : rms) {

                tableCustData[0] = entry.getRoom_id();

                tableCustData[1] = entry.getRoomNo();
                tableCustData[2] = entry.getPrice();
                tableCustData[3] = entry.getMax_cap();
                tableCustData[4] = entry.getAllFacilities();
                tableCustData[5] = entry.getRoom_type();
                tableCustData[6] = entry.getDescription();

                searchResultModel.addRow(tableCustData);

            }

        }
    }//GEN-LAST:event_srchRoomsButtonActionPerformed

    private void vAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vAllButtonActionPerformed
        // TODO add your handling code here:
        Util.clearErrorMessage(glbErrLabel);
        ArrayList<RoomFramePOJO> rms = rdao.getAllRooms(currentHotelId);
        if (rms == null) {
            Util.setErrorMessage(glbErrLabel, "Some error occured");
        } else if (rms.size() == 0) {
            Util.setErrorMessage(glbErrLabel, "no rooms with this facility exist");
        } else {
            rmSrchResTable.setEnabled(true);
            Object[] tableCustData = new Object[7];
            int i = 0;
            searchResultModel = ((DefaultTableModel) (rmSrchResTable.getModel()));
            searchResultModel.setRowCount(0);
            for (RoomFramePOJO entry : rms) {

                tableCustData[0] = entry.getRoom_id();

                tableCustData[1] = entry.getRoomNo();
                tableCustData[2] = entry.getPrice();
                tableCustData[3] = entry.getMax_cap();
                tableCustData[4] = entry.getAllFacilities();
                tableCustData[5] = entry.getRoom_type();
                tableCustData[6] = entry.getDescription();

                searchResultModel.addRow(tableCustData);

            }

        }

    }//GEN-LAST:event_vAllButtonActionPerformed

    private void updateRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateRoomButtonActionPerformed
        // TODO add your handling code here:
        Util.clearErrorMessage(glbErrLabel);
        currRNo = rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 1).toString();
        updRID = (Integer) rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 0);
        manRoomTabbedPane.setEnabledAt(2, true);
        manRoomTabbedPane.setSelectedIndex(2);
        rUpNoTextField2.setText(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 1).toString());
        rPriceFormattedTextField2.setText(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 2).toString());
        maxCapFormattedTextField1.setText(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 3).toString());
        String facArr[] = rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 4).toString().split("\\+");
        updateTablModel = ((DefaultTableModel) (upRfacTable.getModel()));
        updateTablModel.setRowCount(0);
        if (!(facArr.length == 1 && facArr[0].compareTo("") == 0)) {
            Object upRfacTableData[] = new Object[1];

            for (int i = 0; i < facArr.length; i++) {
                upRfacTableData[0] = facArr[i];
                updateTablModel.addRow(upRfacTableData);

            }

        }
        rTypeTextField.setText(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 5).toString());
        updRDescrTextField3.setText(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 6).toString());
        // rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), ).toString());
    }//GEN-LAST:event_updateRoomButtonActionPerformed

    private void addFacButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacButton2ActionPerformed
        // TODO add your handling code here:

        manRoomTabbedPane.setEnabledAt(3, true);
        manRoomTabbedPane.setSelectedIndex(3);
        //System.out.println("upRfacTable "+upRfacTable.getValueAt(0, 0).toString());
        // System.out.println("row count "+upRfacTable.getRowCount());

    }//GEN-LAST:event_addFacButton2ActionPerformed

    private void addFacButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacButton1ActionPerformed
        // TODO add your handling code here:
        //System.out.println("upRfacTable "+upRfacTable.getValueAt(0, 0).toString());
        //System.out.println("row count "+upRfacTable.getRowCount());
        if (!creating) {
            if (upRfacTable.getRowCount() == 0) {

                Object o[] = new Object[1];
                o[0] = rFacComboBox1.getSelectedItem().toString();
                updateTablModel.addRow(o);
                manRoomTabbedPane.setEnabledAt(3, false);
                manRoomTabbedPane.setSelectedIndex(2);
            } else {
                boolean found = false;
                int x = upRfacTable.getRowCount();
                for (int i = 0; i < x; i++) {
                    if (rFacComboBox1.getSelectedItem().toString().compareTo((upRfacTable.getValueAt(i, 0).toString())) == 0) {
                        Util.setErrorMessage(glbErrLabel, "Facility already added");
                        found = true;
                        break;
                    }

                }
                if (!found) {
                    Object o[] = new Object[1];
                    o[0] = rFacComboBox1.getSelectedItem().toString();
                    updateTablModel.addRow(o);
                    Util.setErrorMessage(glbErrLabel, "Facility added");
                    manRoomTabbedPane.setEnabledAt(3, false);
                    manRoomTabbedPane.setSelectedIndex(2);
                }
            }
        } else {
            if (crtRAddFacTable.getRowCount() == 0) {

                Object o[] = new Object[2];
                o[1] = rFacComboBox1.getSelectedItem().toString();
                o[0] = fac.get(rFacComboBox1.getSelectedItem().toString());
                createTableModel.addRow(o);
                manRoomTabbedPane.setEnabledAt(3, false);
                manRoomTabbedPane.setSelectedIndex(1);
                Util.setErrorMessage(glbErrLabel, "Facility added");
            } else {
                boolean found = false;
                int x = crtRAddFacTable.getRowCount();
                for (int i = 0; i < x; i++) {
                    if (rFacComboBox1.getSelectedItem().toString().compareTo((crtRAddFacTable.getValueAt(i, 1).toString())) == 0) {
                        Util.setErrorMessage(glbErrLabel, "Facility already added");
                        found = true;
                        break;
                    }

                }
                if (!found) {
                    Object o[] = new Object[2];
                    o[1] = rFacComboBox1.getSelectedItem().toString();
                    o[0] = fac.get(o[1].toString());
                    createTableModel.addRow(o);
                    Util.setErrorMessage(glbErrLabel, "Facility added");
                    manRoomTabbedPane.setEnabledAt(3, false);
                    manRoomTabbedPane.setSelectedIndex(1);

                }
            }
        }

    }//GEN-LAST:event_addFacButton1ActionPerformed

    private void CancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelBtnActionPerformed
        // TODO add your handling code here:
        if (creating) {
            manRoomTabbedPane.setEnabledAt(3, false);
            manRoomTabbedPane.setSelectedIndex(1);
        } else {
            manRoomTabbedPane.setEnabledAt(3, false);
            manRoomTabbedPane.setSelectedIndex(2);
        }
        creating = false;
        Util.clearErrorMessage(glbErrLabel);
    }//GEN-LAST:event_CancelBtnActionPerformed

    private void rmvAllFacButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmvAllFacButton1ActionPerformed
        // TODO add your handling code here:
        //updateTablModel = ((DefaultTableModel)(upRfacTable.getModel())); 
        updateTablModel.setRowCount(0);
    }//GEN-LAST:event_rmvAllFacButton1ActionPerformed

    private void addNewRoomButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewRoomButton1ActionPerformed
        // TODO add your handling code here:
        if (validateUpdateVars()) {
            boolean isRoomNoChanged = (updRNo.compareTo(currRNo) == 0) ? false : true;

            if (rdao.updateRoom(new RoomFramePOJO(updRID, updRNo, updRPrice, updRMaxCap, updRFac, updRType, updRDescrTextField3.getText()), updRID, currentHotelId, isRoomNoChanged)) {
                if (glbErrLabel.getText().contains("Warning")) {
                    Util.setErrorMessage(glbErrLabel, "Update Successful" + glbErrLabel.getText());
                } else {
                    Util.setErrorMessage(glbErrLabel, "Update Successful");
                }
                manRoomTabbedPane.setEnabledAt(2, false);
                manRoomTabbedPane.setSelectedIndex(0);

                updateRoomButton.setEnabled(false);
                delRoomButton.setEnabled(false);
                searchResultModel.setRowCount(0);
            } else {
                Util.setErrorMessage(glbErrLabel, "Some problem occured while updating or roomNo already exists");
            }
        }
    }//GEN-LAST:event_addNewRoomButton1ActionPerformed

    private void addFacButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFacButtonActionPerformed
        // TODO add your handling code here:
        manRoomTabbedPane.setEnabledAt(3, true);
        manRoomTabbedPane.setSelectedIndex(3);
        creating = true;
    }//GEN-LAST:event_addFacButtonActionPerformed

    private void addNewRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewRoomButtonActionPerformed
        // TODO add your handling code here:
        if (validateCreateVars()) {

            if (rdao.createRoom(new RoomPOJO(crtRPrice, crtRType, crtRNo, crtRMaxCap, currentHotelId, addRDescrTextField2.getText()), crtRFac)) {
                if (glbErrLabel.getText().contains("Warning")) {
                    Util.setErrorMessage(glbErrLabel, "ADded Successful" + glbErrLabel.getText());
                } else {
                    Util.setErrorMessage(glbErrLabel, "Added Successful");
                }
                manRoomTabbedPane.setEnabledAt(2, false);
                manRoomTabbedPane.setSelectedIndex(0);
            } else {
                Util.setErrorMessage(glbErrLabel, "Some problem occured while Adding or it already exists in this hotel");
            }
        }

    }//GEN-LAST:event_addNewRoomButtonActionPerformed

    private void delRoomButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delRoomButtonActionPerformed
        // TODO add your handling code here:
        if (!rdao.delRoom(Integer.parseInt(rmSrchResTable.getValueAt(rmSrchResTable.getSelectedRow(), 0).toString()))) {
            Util.setErrorMessage(glbErrLabel, "Some problem occured while deleting");
        } else {
            Util.setErrorMessage(glbErrLabel, "Delete succesful.Search again");
            searchResultModel.setRowCount(0);
            updateRoomButton.setEnabled(false);
            delRoomButton.setEnabled(false);
        }

    }//GEN-LAST:event_delRoomButtonActionPerformed

    private void rmvAllFacButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmvAllFacButtonActionPerformed
        // TODO add your handling code here:
        createTableModel.setRowCount(0);
    }//GEN-LAST:event_rmvAllFacButtonActionPerformed

    private void jbCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbCancelActionPerformed
        // TODO add your handling code here:
        System.out.println("Closing Room Frame");
        returnMainFrame();
    }//GEN-LAST:event_jbCancelActionPerformed

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
            java.util.logging.Logger.getLogger(RoomJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RoomJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RoomJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RoomJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RoomJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton addFacButton;
    private javax.swing.JButton addFacButton1;
    private javax.swing.JButton addFacButton2;
    private javax.swing.JPanel addFacPanel;
    private javax.swing.JButton addNewRoomButton;
    private javax.swing.JButton addNewRoomButton1;
    private javax.swing.JTextField addRDescrTextField2;
    private javax.swing.JTextField addRTypeTextField1;
    private javax.swing.JPanel createRoomPanel;
    private javax.swing.JTable crtRAddFacTable;
    private javax.swing.JButton delRoomButton;
    private javax.swing.JLabel glbErrLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton jbCancel;
    private javax.swing.JTabbedPane manRoomTabbedPane;
    private javax.swing.JFormattedTextField maxCapFormattedTextField;
    private javax.swing.JFormattedTextField maxCapFormattedTextField1;
    private javax.swing.JTextField rAddNoTextField;
    private javax.swing.JComboBox<String> rFacComboBox;
    private javax.swing.JComboBox<String> rFacComboBox1;
    private javax.swing.JFormattedTextField rPriceFormattedTextField1;
    private javax.swing.JFormattedTextField rPriceFormattedTextField2;
    private javax.swing.JTextField rTypeTextField;
    private javax.swing.JTextField rUpNoTextField2;
    private javax.swing.JPanel resultPanel3;
    private javax.swing.JTable rmSrchResTable;
    private javax.swing.JButton rmvAllFacButton;
    private javax.swing.JButton rmvAllFacButton1;
    private javax.swing.JButton srchRoomsButton;
    private javax.swing.JTable upRfacTable;
    private javax.swing.JTextField updRDescrTextField3;
    private javax.swing.JButton updateRoomButton;
    private javax.swing.JPanel updateRoomPanel;
    private javax.swing.JButton vAllButton;
    // End of variables declaration//GEN-END:variables
}
