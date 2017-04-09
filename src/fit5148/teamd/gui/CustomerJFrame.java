/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fit5148.teamd.gui;

import fit5148.teamd.dao.CustomerDAO;
import fit5148.teamd.dao.MembershipDAO;
import fit5148.teamd.dao.Util;
import fit5148.teamd.pojo.CustomerPOJO;
import fit5148.teamd.pojo.MembershipPOJO;
import fit5148.teamd.pojo.PersonalDetailsPOJO;
import java.sql.SQLException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Varun
 */
public class CustomerJFrame extends javax.swing.JFrame {

        CustomerDAO custDao ;
        MembershipDAO memDao ;
        ArrayList<MembershipPOJO> membArr = new ArrayList<>();
        HashMap<CustomerPOJO,PersonalDetailsPOJO> cust = new HashMap<>();
        DefaultTableModel searchResultModel ;
        int custSearchResultTableSelectedRowNo,custSearchResultTableSelectedColumnNo;
        String updCustTitle,addCTitle,updCustFname,addCFName,updCustLname,addCLName,updCustStreet,addCStreet,updCustCountry,addCCountry,updCustCity,addCCity,updCustPostCode,addCPostCode,updCustEml,addCEml,updCustMemTier;
        int updCustMemCred,updCustPhNo,addCPhNo,updCustId,updCustPdId,updCustHotelId;
        Date updCustDob,addCDoB;
        CustomerPOJO updCust;
        PersonalDetailsPOJO updCustPD;
        int currentHotelId;
        
        
        
        
    /**
     * Creates new form NewJFrame
     */
    public CustomerJFrame() {
        
           
                initComponents();
                
                this.currentHotelId = 1;
            try {
                custDao= new CustomerDAO();
                
                memDao= new MembershipDAO();
                 
            } catch (SQLException ex) {
                setErrorMessage(glbErrLabel, "There was a problem accessing Database. Please reopen");
                Logger.getLogger(CustomerJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            init();
    }
    private void init(){
        searchModifyTabbedPane.setEnabledAt(2, false);
        displayToggleComponent(modifyCustSearchResultPanel);
        displayToggleComponent(modifyCustSearchResultTable);
        clearErrorMessage(searchModifyCustErrorMsgLabel);
         //memTierSrchResTableComboBox = new JComboBox();
        membArr = memDao.getAllMembership();
        for(int i = 0; i<membArr.size(); i++){
           
            memTierSearchComboBox.addItem(membArr.get(i).getMem_Tier());
            updateMemTierComboBox1.addItem(membArr.get(i).getMem_Tier());
        }
        //modifyCustSearchResultTable.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(memTierSrchResTableComboBox)); 
         modifyCustSearchResultTable.getSelectionModel().addListSelectionListener(new RowSelectionListener());
         //modifyCustSearchResultTable.getCellEditor(1, 2).
    }
    
    
    private class RowSelectionListener implements ListSelectionListener{

        @Override
        public void valueChanged(ListSelectionEvent e) {
            custSearchResultTableSelectedRowNo = modifyCustSearchResultTable.getSelectedRow();
            custSearchResultTableSelectedColumnNo = modifyCustSearchResultTable.getSelectedColumn();
            //System.out.println("row = "+custSearchResultTableSelectedRowNo +" colu = "+ custSearchResultTableSelectedColumnNo);
            modifyCustChildPanelUpdateBtn.setEnabled(true);
            modifyCustChildPanelDelButton.setEnabled(true);
        }
    
    
    }
    
    private boolean validateNAddCust(){
        if(addCustTitleTextField.getText().compareTo("")==0){
            setErrorMessage(addCustErrMsgLabel, "Title cannot be empty");
            return false;
        }else
            addCTitle=addCustTitleTextField.getText();
        
        if(addCustFNameTextField.getText().compareTo("")==0){
            setErrorMessage(addCustErrMsgLabel, "First Name cannot be empty");
            return false;
        }else
            addCFName=addCustFNameTextField.getText();
        if(addCustLNameTextField.getText().compareTo("")==0){
            setErrorMessage(addCustErrMsgLabel, "Last Name cannot be empty");
            return false;
        }
        else
            addCLName=addCustLNameTextField.getText();
        
        if(addCustDOBFormattedTextField.getText().compareTo("")==0){
            addCDoB= null;
        }
        else
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = null;
            try {
                inputDate = dateFormat.parse(addCustDOBFormattedTextField.getText());
            } catch (ParseException ex) {
                setErrorMessage(addCustErrMsgLabel, "Check your date of birth format - yyyy-MM-dd");
                 return false;
            }
            if(addCustDOBFormattedTextField.getText().compareTo("")==0 || inputDate==null){
                setErrorMessage(addCustErrMsgLabel, "Check your date of birth format - yyyy-MM-dd");
                return false;
            }
            
            addCDoB= inputDate;
        }
        addCCountry = (addCustCountryTextField.getText().compareTo("")==0)?null:addCustCountryTextField.getText();
        addCCity = (addCustCityTextField.getText().compareTo("")==0)?null:addCustCityTextField.getText();
        addCStreet = (addCustStreetTextField.getText().compareTo("")==0)?null:addCustStreetTextField.getText();    
        addCPostCode = (addCustPostCodeTextField.getText().compareTo("")==0)?null:addCustPostCodeTextField.getText();  
        addCPhNo = (addCustPhNoFormattedTextField.getText().compareTo("")==0)?0:Integer.parseInt(addCustPhNoFormattedTextField.getText());  
        
        
       
        
        if(addCustEmlTextField.getText().compareTo("")==0){
            setErrorMessage(addCustErrMsgLabel, "Email cannot be empty");
            return false;
        }
        
            
        if(!Util.validateEml(addCustEmlTextField.getText())){
             setErrorMessage(addCustErrMsgLabel, "Please check your Email");
             return false;
        }
        else
            addCEml= addCustEmlTextField.getText();
        clearErrorMessage(addCustErrMsgLabel);
       
        return true;
        
    
    }
    private boolean validateUpdateFields(){
        if(updateCustTitleTextField1.getText().compareTo("")==0){
            setErrorMessage(updCustErrorMsgLabel, "Title cannot be empty");
            return false;
        }else
            updCustTitle=updateCustTitleTextField1.getText();
        
        if(updateCustFNameTextField1.getText().compareTo("")==0){
            setErrorMessage(updCustErrorMsgLabel, "First Name cannot be empty");
            return false;
        }else
            updCustFname=updateCustFNameTextField1.getText();
        if(updateCustLNameTextField1.getText().compareTo("")==0){
            setErrorMessage(updCustErrorMsgLabel, "Last Name cannot be empty");
            return false;
        }
        else
            updCustLname=updateCustLNameTextField1.getText();
        
        if(updateCustDOBFormattedTextField.getText().compareTo("")==0){
            updCustDob= null;
        }
        else
        {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inputDate = null;
            try {
                inputDate = dateFormat.parse(updateCustDOBFormattedTextField.getText());
            } catch (ParseException ex) {
                setErrorMessage(updCustErrorMsgLabel, "Check your date of birth format - MM/DD/YYYY");
            }
            if(inputDate==null){
                setErrorMessage(updCustErrorMsgLabel, "Check your date of birth format - MM/DD/YYYY");
                return false;
            }
            
            updCustDob= inputDate;
        }
        updCustCountry = (updateCustCountryTextField1.getText().compareTo("")==0)?null:updateCustCountryTextField1.getText();
        updCustCity = (updateCustCityTextField1.getText().compareTo("")==0)?null:updateCustCityTextField1.getText();
        updCustStreet = (updateCustStreetTextField1.getText().compareTo("")==0)?null:updateCustStreetTextField1.getText();    
        updCustPostCode = (updateCustPostCodeTextField1.getText().compareTo("")==0)?null:updateCustPostCodeTextField1.getText();  
        updCustPhNo = (updateCustPhNoFormattedTextField.getText().compareTo("")==0)?0:Integer.parseInt(updateCustPhNoFormattedTextField.getText());  
        
        
        if(updateCustMemCredFormattedTextField.getText().compareTo("")==0){
            setErrorMessage(updCustErrorMsgLabel, "Membership Credits cannot be empty");
            return false;
        }
        
        if(updateCustEmlTextField1.getText().compareTo("")==0){
            setErrorMessage(updCustErrorMsgLabel, "Email cannot be empty");
            return false;
        }
        
            
        if(!Util.validateEml(updateCustEmlTextField1.getText())){
             setErrorMessage(updCustErrorMsgLabel, "Please check your Email");
             return false;
        }
        else
            updCustEml= updateCustEmlTextField1.getText();
        clearErrorMessage(updCustErrorMsgLabel);
        updCustMemTier=updateMemTierComboBox1.getSelectedItem().toString();
        updCustMemCred=Integer.parseInt(updateCustMemCredFormattedTextField.getText());
        return true;
    
    }
    private void setErrorMessage(JLabel jl,String msg){
        jl.setText(msg);
        jl.setVisible(true);
    }
    private void clearErrorMessage(JLabel... jl){
        
        for(int i = 0; i < jl.length; i++){
             jl[i].setText("");
             jl[i].setVisible(false);
         }
       
        
    }
    
    private void displayToggleComponent(JComponent jc){
        if(jc.isEnabled() || jc.isVisible()){
            jc.setEnabled(false);
            jc.setVisible(false);
        }
        else{
            jc.setVisible(true);
            jc.setEnabled(true);
        }
           
      }
    private void populateResultTable(HashMap<CustomerPOJO,PersonalDetailsPOJO> cust){
                        Object[] tableCustData =  new Object[14];
                            int i=0;
                            searchResultModel = ((DefaultTableModel)(modifyCustSearchResultTable.getModel())); 
                            searchResultModel.setRowCount(0);
                            for (Map.Entry<CustomerPOJO, PersonalDetailsPOJO> entry : cust.entrySet()) {

                                CustomerPOJO key = entry.getKey();
                                PersonalDetailsPOJO value = entry.getValue();
                                tableCustData[0]=value.getTitle();

                                tableCustData[1]=value.getF_Name();
                                tableCustData[2]=value.getL_Name();
                                tableCustData[3]=key.getMem_Credit();
                                tableCustData[4]=key.getMem_Tier();
                                tableCustData[5]=value.getDob();
                                tableCustData[6]=value.getCountry();
                                tableCustData[7]=value.getCity();
                                tableCustData[8]=value.getStreet();
                                tableCustData[9]=value.getPostCode();
                                tableCustData[10]=value.getPh_no();
                                tableCustData[11]=value.getEmail(); 

                                tableCustData[12]=key.getCustId(); 
                                tableCustData[13]=value.getPd_id(); 
                             
                               // System.out.println(tableCustData);
                                 
                              searchResultModel.addRow(tableCustData);

                            }
                     // "Title", "First Name", "Last Name", "Membership Credits", "Membership_Tier", "Date of Birth", "Country", "City", "Street", "Postcode", "Ph_no", "Email"
                        
                         modifyCustSearchResultPanel.setEnabled(true);
                           modifyCustSearchResultPanel.setVisible(true);
                             modifyCustSearchResultTable.setEnabled(true);
                             modifyCustSearchResultTable.setVisible(true);
    }

   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        custSearchBtnGrp = new javax.swing.ButtonGroup();
        searchModifyTabbedPane = new javax.swing.JTabbedPane();
        searchModifyCustomerPanel = new javax.swing.JPanel();
        searchCustomerCustFrameBtn = new javax.swing.JButton();
        searchCustEmlTextField = new javax.swing.JTextField();
        searchByEmlLabel = new javax.swing.JLabel();
        memTierSearchComboBox = new javax.swing.JComboBox<>();
        modifyCustSearchResultPanel = new javax.swing.JPanel();
        modifyCustSearchResultScrollPane = new javax.swing.JScrollPane();
        modifyCustSearchResultTable = new javax.swing.JTable();
        selectCustomerLabel = new javax.swing.JLabel();
        modifyOrSavePanel = new javax.swing.JPanel();
        modifyCustChildPanel = new javax.swing.JPanel();
        modifyCustChildPanelUpdateBtn = new javax.swing.JButton();
        modifyCustChildPanelLabel1 = new javax.swing.JLabel();
        modifyCustChildPanelDelButton = new javax.swing.JButton();
        searchModifyCustErrorMsgLabel = new javax.swing.JLabel();
        searchCatEmlRad = new javax.swing.JRadioButton();
        searchCatMemRad = new javax.swing.JRadioButton();
        addNewCustomerPanel = new javax.swing.JPanel();
        addCustTitleLabel = new javax.swing.JLabel();
        addCustFirstNameLabel = new javax.swing.JLabel();
        addCustLNameLabel = new javax.swing.JLabel();
        addCustDOBLabel = new javax.swing.JLabel();
        addCustCityLabel = new javax.swing.JLabel();
        addCustCountryLabel = new javax.swing.JLabel();
        addCustStreetLabel = new javax.swing.JLabel();
        addCustPostCodeLabel = new javax.swing.JLabel();
        addCustPhNoLabel = new javax.swing.JLabel();
        addCustEmailLabel = new javax.swing.JLabel();
        addCustTitleTextField = new javax.swing.JTextField();
        addCustFNameTextField = new javax.swing.JTextField();
        addCustLNameTextField = new javax.swing.JTextField();
        addCustCountryTextField = new javax.swing.JTextField();
        addCustCityTextField = new javax.swing.JTextField();
        addCustStreetTextField = new javax.swing.JTextField();
        addCustPostCodeTextField = new javax.swing.JTextField();
        addCustEmlTextField = new javax.swing.JTextField();
        addCustAddBtn = new javax.swing.JButton();
        addCustDOBFormattedTextField = new javax.swing.JFormattedTextField();
        addCustPhNoFormattedTextField = new javax.swing.JFormattedTextField();
        addCustErrMsgLabel = new javax.swing.JLabel();
        updateTabPanel = new javax.swing.JPanel();
        updateCustTitleLabel1 = new javax.swing.JLabel();
        updateCustFirstNameLabel1 = new javax.swing.JLabel();
        updateCustLNameLabel1 = new javax.swing.JLabel();
        updateCustDOBLabel1 = new javax.swing.JLabel();
        updateCustCountryLabel1 = new javax.swing.JLabel();
        updateCustTitleTextField1 = new javax.swing.JTextField();
        updateCustFNameTextField1 = new javax.swing.JTextField();
        updateCustLNameTextField1 = new javax.swing.JTextField();
        updateCustCountryTextField1 = new javax.swing.JTextField();
        updateCustCityTextField1 = new javax.swing.JTextField();
        updateCustStreetTextField1 = new javax.swing.JTextField();
        updateCustPostCodeTextField1 = new javax.swing.JTextField();
        updateCustEmlTextField1 = new javax.swing.JTextField();
        updateCustPhNoLabel1 = new javax.swing.JLabel();
        updateCustEmailLabel1 = new javax.swing.JLabel();
        updateCustPostCodeLabel1 = new javax.swing.JLabel();
        updateCustStreetLabel1 = new javax.swing.JLabel();
        updateCustCityLabel1 = new javax.swing.JLabel();
        updateCustMemCredLabel = new javax.swing.JLabel();
        updateCustCityLabel2 = new javax.swing.JLabel();
        updateMemTierComboBox1 = new javax.swing.JComboBox<>();
        updCustSavePanel = new javax.swing.JPanel();
        updCustSaveChildPanel = new javax.swing.JPanel();
        updCustSaveBtn = new javax.swing.JButton();
        updateCustPhNoFormattedTextField = new javax.swing.JFormattedTextField();
        updateCustDOBFormattedTextField = new javax.swing.JFormattedTextField();
        updateCustMemCredFormattedTextField = new javax.swing.JFormattedTextField();
        updCustErrorMsgLabel = new javax.swing.JLabel();
        manageCustFrameLabel = new javax.swing.JLabel();
        glbErrLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        searchModifyCustomerPanel.setPreferredSize(new java.awt.Dimension(800, 500));

        searchCustomerCustFrameBtn.setText("Search");
        searchCustomerCustFrameBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerCustFrameBtnActionPerformed(evt);
            }
        });

        searchCustEmlTextField.setToolTipText("Enter email");
        searchCustEmlTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchCustEmlTextFieldMouseClicked(evt);
            }
        });
        searchCustEmlTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustEmlTextFieldActionPerformed(evt);
            }
        });

        searchByEmlLabel.setText("Search by Email ID or Membership Tier");

        memTierSearchComboBox.setEnabled(false);
        memTierSearchComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                memTierSearchComboBoxMouseClicked(evt);
            }
        });
        memTierSearchComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memTierSearchComboBoxActionPerformed(evt);
            }
        });

        modifyCustSearchResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title", "First Name", "Last Name", "Membership Credits", "Membership_Tier", "Date of Birth", "Country", "City", "Street", "Postcode", "Ph_no", "Email", "CustId", "Pd id"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        modifyCustSearchResultTable.setEnabled(false);
        modifyCustSearchResultScrollPane.setViewportView(modifyCustSearchResultTable);

        selectCustomerLabel.setText("Click and Select Customer to modify");
        selectCustomerLabel.setFocusable(false);
        selectCustomerLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        modifyOrSavePanel.setLayout(new java.awt.CardLayout());

        modifyCustChildPanelUpdateBtn.setText("Update Customer");
        modifyCustChildPanelUpdateBtn.setEnabled(false);
        modifyCustChildPanelUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyCustChildPanelUpdateBtnActionPerformed(evt);
            }
        });

        modifyCustChildPanelLabel1.setText("Modify Selected Customer");

        modifyCustChildPanelDelButton.setText("Delete Customer");
        modifyCustChildPanelDelButton.setEnabled(false);
        modifyCustChildPanelDelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyCustChildPanelDelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout modifyCustChildPanelLayout = new javax.swing.GroupLayout(modifyCustChildPanel);
        modifyCustChildPanel.setLayout(modifyCustChildPanelLayout);
        modifyCustChildPanelLayout.setHorizontalGroup(
            modifyCustChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyCustChildPanelLayout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(modifyCustChildPanelUpdateBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                .addComponent(modifyCustChildPanelDelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
            .addGroup(modifyCustChildPanelLayout.createSequentialGroup()
                .addGap(292, 292, 292)
                .addComponent(modifyCustChildPanelLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        modifyCustChildPanelLayout.setVerticalGroup(
            modifyCustChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyCustChildPanelLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(modifyCustChildPanelLabel1)
                .addGap(18, 18, 18)
                .addGroup(modifyCustChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modifyCustChildPanelUpdateBtn)
                    .addComponent(modifyCustChildPanelDelButton))
                .addGap(35, 35, 35))
        );

        modifyOrSavePanel.add(modifyCustChildPanel, "card3");

        javax.swing.GroupLayout modifyCustSearchResultPanelLayout = new javax.swing.GroupLayout(modifyCustSearchResultPanel);
        modifyCustSearchResultPanel.setLayout(modifyCustSearchResultPanelLayout);
        modifyCustSearchResultPanelLayout.setHorizontalGroup(
            modifyCustSearchResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                .addGroup(modifyCustSearchResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(modifyCustSearchResultScrollPane))
                    .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                        .addGroup(modifyCustSearchResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                                .addGap(494, 494, 494)
                                .addComponent(selectCustomerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                                .addGap(233, 233, 233)
                                .addComponent(modifyOrSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 226, Short.MAX_VALUE)))
                .addContainerGap())
        );
        modifyCustSearchResultPanelLayout.setVerticalGroup(
            modifyCustSearchResultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(modifyCustSearchResultPanelLayout.createSequentialGroup()
                .addComponent(selectCustomerLabel)
                .addGap(14, 14, 14)
                .addComponent(modifyCustSearchResultScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyOrSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        searchModifyCustErrorMsgLabel.setForeground(new java.awt.Color(255, 0, 51));

        custSearchBtnGrp.add(searchCatEmlRad);
        searchCatEmlRad.setSelected(true);
        searchCatEmlRad.setText("Email");
        searchCatEmlRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCatEmlRadActionPerformed(evt);
            }
        });

        custSearchBtnGrp.add(searchCatMemRad);
        searchCatMemRad.setText("Membership Tier");
        searchCatMemRad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCatMemRadActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchModifyCustomerPanelLayout = new javax.swing.GroupLayout(searchModifyCustomerPanel);
        searchModifyCustomerPanel.setLayout(searchModifyCustomerPanelLayout);
        searchModifyCustomerPanelLayout.setHorizontalGroup(
            searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(modifyCustSearchResultPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                .addGap(541, 541, 541)
                .addComponent(searchCustomerCustFrameBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchModifyCustErrorMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                        .addGap(437, 437, 437)
                        .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                                .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchCatMemRad)
                                    .addComponent(searchCatEmlRad))
                                .addGap(56, 56, 56)
                                .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(searchCustEmlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(memTierSearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(searchByEmlLabel))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        searchModifyCustomerPanelLayout.setVerticalGroup(
            searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchModifyCustomerPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(searchByEmlLabel)
                .addGap(18, 18, 18)
                .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchCustEmlTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchCatEmlRad))
                .addGap(18, 18, 18)
                .addGroup(searchModifyCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memTierSearchComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchCatMemRad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchModifyCustErrorMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchCustomerCustFrameBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifyCustSearchResultPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(271, 271, 271))
        );

        searchModifyTabbedPane.addTab("Search and Modify", searchModifyCustomerPanel);

        addCustTitleLabel.setText("Title");

        addCustFirstNameLabel.setText("First Name");

        addCustLNameLabel.setText("Last Name");

        addCustDOBLabel.setText("Date of birth");

        addCustCityLabel.setText("City");

        addCustCountryLabel.setText("Country");

        addCustStreetLabel.setText("Street");

        addCustPostCodeLabel.setText("Postcode");

        addCustPhNoLabel.setText("Ph No");

        addCustEmailLabel.setText("Email");

        addCustLNameTextField.setToolTipText("");

        addCustAddBtn.setText("Add New Customer");
        addCustAddBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustAddBtnActionPerformed(evt);
            }
        });

        addCustDOBFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        addCustPhNoFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        javax.swing.GroupLayout addNewCustomerPanelLayout = new javax.swing.GroupLayout(addNewCustomerPanel);
        addNewCustomerPanel.setLayout(addNewCustomerPanelLayout);
        addNewCustomerPanelLayout.setHorizontalGroup(
            addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewCustomerPanelLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(addCustCountryLabel)
                    .addComponent(addCustDOBLabel)
                    .addComponent(addCustLNameLabel)
                    .addComponent(addCustFirstNameLabel)
                    .addComponent(addCustTitleLabel))
                .addGap(58, 58, 58)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(addCustErrMsgLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(addNewCustomerPanelLayout.createSequentialGroup()
                        .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(addCustAddBtn)
                            .addGroup(addNewCustomerPanelLayout.createSequentialGroup()
                                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addCustTitleTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                                    .addComponent(addCustFNameTextField)
                                    .addComponent(addCustLNameTextField)
                                    .addComponent(addCustCountryTextField)
                                    .addComponent(addCustDOBFormattedTextField))
                                .addGap(159, 159, 159)
                                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addCustCityLabel)
                                    .addComponent(addCustEmailLabel)
                                    .addComponent(addCustPhNoLabel)
                                    .addComponent(addCustPostCodeLabel)
                                    .addComponent(addCustStreetLabel))))
                        .addGap(35, 35, 35)
                        .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(addCustPostCodeTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(addCustStreetTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCustCityTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addCustEmlTextField)
                            .addComponent(addCustPhNoFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING))))
                .addContainerGap(320, Short.MAX_VALUE))
        );
        addNewCustomerPanelLayout.setVerticalGroup(
            addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addNewCustomerPanelLayout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCustTitleLabel)
                    .addComponent(addCustCityLabel)
                    .addComponent(addCustTitleTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustCityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCustFirstNameLabel)
                    .addComponent(addCustStreetLabel)
                    .addComponent(addCustFNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustStreetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCustLNameLabel)
                    .addComponent(addCustPostCodeLabel)
                    .addComponent(addCustLNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustPostCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCustDOBLabel)
                    .addComponent(addCustPhNoLabel)
                    .addComponent(addCustDOBFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustPhNoFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addNewCustomerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addCustCountryLabel)
                    .addComponent(addCustEmailLabel)
                    .addComponent(addCustCountryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustEmlTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(addCustErrMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addCustAddBtn)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        searchModifyTabbedPane.addTab("Add New", addNewCustomerPanel);

        updateCustTitleLabel1.setText("Title");

        updateCustFirstNameLabel1.setText("First Name");

        updateCustLNameLabel1.setText("Last Name");

        updateCustDOBLabel1.setText("Date of birth");

        updateCustCountryLabel1.setText("Country");

        updateCustLNameTextField1.setToolTipText("");

        updateCustPhNoLabel1.setText("Ph No");

        updateCustEmailLabel1.setText("Email");

        updateCustPostCodeLabel1.setText("Postcode");

        updateCustStreetLabel1.setText("Street");

        updateCustCityLabel1.setText("City");

        updateCustMemCredLabel.setText("Membership Credits");

        updateCustCityLabel2.setText("Membership Tier");

        updCustSavePanel.setLayout(new java.awt.CardLayout());

        updCustSaveBtn.setText("Save");
        updCustSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updCustSaveBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout updCustSaveChildPanelLayout = new javax.swing.GroupLayout(updCustSaveChildPanel);
        updCustSaveChildPanel.setLayout(updCustSaveChildPanelLayout);
        updCustSaveChildPanelLayout.setHorizontalGroup(
            updCustSaveChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updCustSaveChildPanelLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(updCustSaveBtn)
                .addContainerGap(244, Short.MAX_VALUE))
        );
        updCustSaveChildPanelLayout.setVerticalGroup(
            updCustSaveChildPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updCustSaveChildPanelLayout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(updCustSaveBtn)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        updCustSavePanel.add(updCustSaveChildPanel, "card3");

        updateCustPhNoFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));

        updateCustDOBFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("yyyy-MM-dd"))));

        updateCustMemCredFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0"))));

        javax.swing.GroupLayout updateTabPanelLayout = new javax.swing.GroupLayout(updateTabPanel);
        updateTabPanel.setLayout(updateTabPanelLayout);
        updateTabPanelLayout.setHorizontalGroup(
            updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateTabPanelLayout.createSequentialGroup()
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateTabPanelLayout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(updateCustCountryLabel1)
                            .addComponent(updateCustDOBLabel1)
                            .addComponent(updateCustLNameLabel1)
                            .addComponent(updateCustFirstNameLabel1)
                            .addComponent(updateCustTitleLabel1)
                            .addComponent(updateCustMemCredLabel))
                        .addGap(113, 113, 113)
                        .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateCustTitleTextField1)
                            .addComponent(updateCustFNameTextField1)
                            .addComponent(updateCustLNameTextField1)
                            .addComponent(updateCustCountryTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                            .addComponent(updateCustDOBFormattedTextField)
                            .addComponent(updateCustMemCredFormattedTextField))
                        .addGap(159, 159, 159)
                        .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateCustCityLabel1)
                            .addComponent(updateCustEmailLabel1)
                            .addComponent(updateCustPhNoLabel1)
                            .addComponent(updateCustPostCodeLabel1)
                            .addComponent(updateCustStreetLabel1)
                            .addComponent(updateCustCityLabel2))
                        .addGap(35, 35, 35)
                        .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(updateCustPostCodeTextField1)
                            .addComponent(updateCustStreetTextField1)
                            .addComponent(updateCustCityTextField1)
                            .addComponent(updateCustEmlTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                            .addComponent(updateMemTierComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateCustPhNoFormattedTextField)))
                    .addGroup(updateTabPanelLayout.createSequentialGroup()
                        .addGap(466, 466, 466)
                        .addComponent(updCustSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateTabPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(updCustErrorMsgLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(291, 291, 291))
        );
        updateTabPanelLayout.setVerticalGroup(
            updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateTabPanelLayout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCustTitleLabel1)
                    .addComponent(updateCustCityLabel1)
                    .addComponent(updateCustTitleTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustCityTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCustFirstNameLabel1)
                    .addComponent(updateCustStreetLabel1)
                    .addComponent(updateCustFNameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustStreetTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCustLNameLabel1)
                    .addComponent(updateCustPostCodeLabel1)
                    .addComponent(updateCustLNameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustPostCodeTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCustDOBLabel1)
                    .addComponent(updateCustPhNoLabel1)
                    .addComponent(updateCustDOBFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustPhNoFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCustCountryLabel1)
                    .addComponent(updateCustEmailLabel1)
                    .addComponent(updateCustCountryTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateCustEmlTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateCustMemCredLabel)
                        .addComponent(updateCustMemCredFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(updateTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(updateCustCityLabel2)
                        .addComponent(updateMemTierComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updCustErrorMsgLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 17, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updCustSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
        );

        searchModifyTabbedPane.addTab("Update", updateTabPanel);

        manageCustFrameLabel.setText("Manage Customers");

        glbErrLabel.setForeground(new java.awt.Color(255, 0, 0));
        glbErrLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(searchModifyTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 1226, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(544, 544, 544)
                        .addComponent(manageCustFrameLabel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(238, 238, 238)
                        .addComponent(glbErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(manageCustFrameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(glbErrLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(searchModifyTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        searchModifyTabbedPane.getAccessibleContext().setAccessibleName("customerTabs");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void searchCustomerCustFrameBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerCustFrameBtnActionPerformed
        // TODO add your handling code here:
        if(searchCatEmlRad.isSelected()){
            if(!Util.validateEml(searchCustEmlTextField.getText()))
            {
                setErrorMessage(searchModifyCustErrorMsgLabel, "Please check your email id");

            }else{

                    if(searchCustEmlTextField.getText().compareTo("")!=0 )
                     cust= custDao.searchCustomerByEmail(searchCustEmlTextField.getText());
                    
                    if(cust!=null && cust.size()>0){
                            populateResultTable(cust);
                            clearErrorMessage(searchModifyCustErrorMsgLabel);
                    }else{
                            Util.clearErrorMessage(glbErrLabel);
                            setErrorMessage(searchModifyCustErrorMsgLabel, "Customer Email Id does not exist");
                    }
                   
            }
        }
        else{
                    cust=custDao.searchCustomerByMembership(memTierSearchComboBox.getSelectedItem().toString());
                 if(cust!=null && cust.size()>0){
                            populateResultTable(cust);
                           clearErrorMessage(searchModifyCustErrorMsgLabel);
                    }else{
                            setErrorMessage(searchModifyCustErrorMsgLabel, "Customers belonging to"+memTierSearchComboBox.getSelectedItem().toString()+" do not exist");
                    }
        
        }
    }//GEN-LAST:event_searchCustomerCustFrameBtnActionPerformed

    private void searchCustEmlTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustEmlTextFieldActionPerformed
        // TODO add your handling code here:
      
    }//GEN-LAST:event_searchCustEmlTextFieldActionPerformed

    private void modifyCustChildPanelUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyCustChildPanelUpdateBtnActionPerformed
        // TODO add your handling code here:
        //
        updCustId = Integer.parseInt(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 12).toString());
        updCustPdId = Integer.parseInt(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 13).toString());
         
        searchModifyTabbedPane.setEnabledAt(2, true);
        if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 0)!=null)
        updateCustTitleTextField1.setText( (modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 0).toString()) );
         if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 1)!=null)
           updateCustFNameTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 1).toString());
          if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 2)!=null)
         updateCustLNameTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 2).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 3)!=null)
         updateCustMemCredFormattedTextField.setText((modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 3)).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 4)!=null)
         updateMemTierComboBox1.setSelectedItem(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 4).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 5)!=null)
         updateCustDOBFormattedTextField.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 5).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 6)!=null)
         updateCustCountryTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 6).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 7)!=null)
         updateCustCityTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 7).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 8)!=null)
         updateCustStreetTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 8).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 9)!=null)
          updateCustPostCodeTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 9).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 10)!=null)
        updateCustPhNoFormattedTextField.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 10).toString());
           if(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 11)!=null)
        updateCustEmlTextField1.setText(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 11).toString());
        
        searchModifyTabbedPane.setSelectedIndex(2);


           
    }//GEN-LAST:event_modifyCustChildPanelUpdateBtnActionPerformed

    private void addCustAddBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCustAddBtnActionPerformed
        // TODO add your handling code here:
        boolean status =false;
        Util.clearErrorMessage(addCustErrMsgLabel);
        if(validateNAddCust()){
                status= custDao.createNewCustomer(new PersonalDetailsPOJO(0, addCTitle, addCFName, addCLName, addCDoB, addCCity, addCCountry, addCStreet, addCPostCode, addCPhNo, addCEml), currentHotelId);
                 if(status){
                    setErrorMessage(addCustErrMsgLabel, "Customer added successfully");
                 }else
                     setErrorMessage(addCustErrMsgLabel, "Problem occured adding customer or Email already exists");
             
       
        }
    }//GEN-LAST:event_addCustAddBtnActionPerformed

    private void memTierSearchComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memTierSearchComboBoxActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_memTierSearchComboBoxActionPerformed

    private void searchCustEmlTextFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchCustEmlTextFieldMouseClicked
        // TODO add your handling code here:
        
         
    }//GEN-LAST:event_searchCustEmlTextFieldMouseClicked

    private void memTierSearchComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_memTierSearchComboBoxMouseClicked
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_memTierSearchComboBoxMouseClicked

    private void searchCatMemRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCatMemRadActionPerformed
        // TODO add your handling code here:
        searchCustEmlTextField.setEnabled(false);
         memTierSearchComboBox.setEnabled(true);
    }//GEN-LAST:event_searchCatMemRadActionPerformed

    private void searchCatEmlRadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCatEmlRadActionPerformed
        // TODO add your handling code here:
           memTierSearchComboBox.setEnabled(false);
         searchCustEmlTextField.setEnabled(true);
    }//GEN-LAST:event_searchCatEmlRadActionPerformed

    private void updCustSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updCustSaveBtnActionPerformed
        // TODO add your handling code here:
        if(validateUpdateFields())
        {
            
            updCust = new CustomerPOJO(updCustId, updCustMemCred, updCustMemTier, updCustPdId);
            updCustPD = new PersonalDetailsPOJO(updCustPdId, updCustTitle, updCustFname, updCustLname, updCustDob, updCustCity, updCustCountry, updCustStreet, updCustPostCode, updCustPhNo, updCustEml);
            HashMap<CustomerPOJO,PersonalDetailsPOJO> hm= new HashMap<>();
            hm.put(updCust, updCustPD);
            boolean upStatus = custDao.updateCustomerDetails(hm);
            if(!upStatus)
                setErrorMessage(updCustErrorMsgLabel, "Update had a problem. Contact technical support or restart");
            else
            {
                 searchModifyTabbedPane.setSelectedIndex(0);
                 searchModifyTabbedPane.setEnabledAt(2, false);
                  displayToggleComponent(modifyCustSearchResultPanel);
                  displayToggleComponent(modifyCustSearchResultTable);
                  modifyCustChildPanelUpdateBtn.setEnabled(false);
                   modifyCustChildPanelDelButton.setEnabled(false);
                 setErrorMessage(glbErrLabel, "Update succesful");
                 
            }
         }
        
         
        
        
    }//GEN-LAST:event_updCustSaveBtnActionPerformed

    private void modifyCustChildPanelDelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyCustChildPanelDelButtonActionPerformed
        // TODO add your handling code here:
        
        if(custDao.deleteCustomer(Integer.parseInt(modifyCustSearchResultTable.getValueAt(modifyCustSearchResultTable.getSelectedRow(), 13).toString())))
        {
             searchModifyTabbedPane.setSelectedIndex(0);
                 searchModifyTabbedPane.setEnabledAt(2, false);
                  displayToggleComponent(modifyCustSearchResultPanel);
                  displayToggleComponent(modifyCustSearchResultTable);
                  modifyCustChildPanelUpdateBtn.setEnabled(false);
                   modifyCustChildPanelDelButton.setEnabled(false);
                 setErrorMessage(glbErrLabel, "Delete succesful");
        }else{
                 setErrorMessage(glbErrLabel, "There was a problem while deleting. Please search again.");
        }
        
    }//GEN-LAST:event_modifyCustChildPanelDelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new CustomerJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCustAddBtn;
    private javax.swing.JLabel addCustCityLabel;
    private javax.swing.JTextField addCustCityTextField;
    private javax.swing.JLabel addCustCountryLabel;
    private javax.swing.JTextField addCustCountryTextField;
    private javax.swing.JFormattedTextField addCustDOBFormattedTextField;
    private javax.swing.JLabel addCustDOBLabel;
    private javax.swing.JLabel addCustEmailLabel;
    private javax.swing.JTextField addCustEmlTextField;
    private javax.swing.JLabel addCustErrMsgLabel;
    private javax.swing.JTextField addCustFNameTextField;
    private javax.swing.JLabel addCustFirstNameLabel;
    private javax.swing.JLabel addCustLNameLabel;
    private javax.swing.JTextField addCustLNameTextField;
    private javax.swing.JFormattedTextField addCustPhNoFormattedTextField;
    private javax.swing.JLabel addCustPhNoLabel;
    private javax.swing.JLabel addCustPostCodeLabel;
    private javax.swing.JTextField addCustPostCodeTextField;
    private javax.swing.JLabel addCustStreetLabel;
    private javax.swing.JTextField addCustStreetTextField;
    private javax.swing.JLabel addCustTitleLabel;
    private javax.swing.JTextField addCustTitleTextField;
    private javax.swing.JPanel addNewCustomerPanel;
    private javax.swing.ButtonGroup custSearchBtnGrp;
    private javax.swing.JLabel glbErrLabel;
    private javax.swing.JLabel manageCustFrameLabel;
    private javax.swing.JComboBox<String> memTierSearchComboBox;
    private javax.swing.JPanel modifyCustChildPanel;
    private javax.swing.JButton modifyCustChildPanelDelButton;
    private javax.swing.JLabel modifyCustChildPanelLabel1;
    private javax.swing.JButton modifyCustChildPanelUpdateBtn;
    private javax.swing.JPanel modifyCustSearchResultPanel;
    private javax.swing.JScrollPane modifyCustSearchResultScrollPane;
    private javax.swing.JTable modifyCustSearchResultTable;
    private javax.swing.JPanel modifyOrSavePanel;
    private javax.swing.JLabel searchByEmlLabel;
    private javax.swing.JRadioButton searchCatEmlRad;
    private javax.swing.JRadioButton searchCatMemRad;
    private javax.swing.JTextField searchCustEmlTextField;
    private javax.swing.JButton searchCustomerCustFrameBtn;
    private javax.swing.JLabel searchModifyCustErrorMsgLabel;
    private javax.swing.JPanel searchModifyCustomerPanel;
    private javax.swing.JTabbedPane searchModifyTabbedPane;
    private javax.swing.JLabel selectCustomerLabel;
    private javax.swing.JLabel updCustErrorMsgLabel;
    private javax.swing.JButton updCustSaveBtn;
    private javax.swing.JPanel updCustSaveChildPanel;
    private javax.swing.JPanel updCustSavePanel;
    private javax.swing.JLabel updateCustCityLabel1;
    private javax.swing.JLabel updateCustCityLabel2;
    private javax.swing.JTextField updateCustCityTextField1;
    private javax.swing.JLabel updateCustCountryLabel1;
    private javax.swing.JTextField updateCustCountryTextField1;
    private javax.swing.JFormattedTextField updateCustDOBFormattedTextField;
    private javax.swing.JLabel updateCustDOBLabel1;
    private javax.swing.JLabel updateCustEmailLabel1;
    private javax.swing.JTextField updateCustEmlTextField1;
    private javax.swing.JTextField updateCustFNameTextField1;
    private javax.swing.JLabel updateCustFirstNameLabel1;
    private javax.swing.JLabel updateCustLNameLabel1;
    private javax.swing.JTextField updateCustLNameTextField1;
    private javax.swing.JFormattedTextField updateCustMemCredFormattedTextField;
    private javax.swing.JLabel updateCustMemCredLabel;
    private javax.swing.JFormattedTextField updateCustPhNoFormattedTextField;
    private javax.swing.JLabel updateCustPhNoLabel1;
    private javax.swing.JLabel updateCustPostCodeLabel1;
    private javax.swing.JTextField updateCustPostCodeTextField1;
    private javax.swing.JLabel updateCustStreetLabel1;
    private javax.swing.JTextField updateCustStreetTextField1;
    private javax.swing.JLabel updateCustTitleLabel1;
    private javax.swing.JTextField updateCustTitleTextField1;
    private javax.swing.JComboBox<String> updateMemTierComboBox1;
    private javax.swing.JPanel updateTabPanel;
    // End of variables declaration//GEN-END:variables
}
