/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import static com.microsoft.sqlserver.jdbc.StringUtils.isNumeric;
import dao.Option;
import dao.Question;
import java.sql.*;
import dao.dbConnect;
import java.awt.Color;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.ButtonModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author minh
 */
public class indexAdminm extends javax.swing.JFrame {

    /**
     * Creates new form indexAdminm
     */
    ArrayList<Question> questions = new ArrayList<>();

    List<Option> ops = new ArrayList<Option>();
    private int questionID;

    public indexAdminm() {
        initComponents();
        Checkbox1.setEnabled(false);
        Checkbox2.setEnabled(false);
        Checkbox3.setEnabled(false);
        Checkbox4.setEnabled(false);

        TextOp1.setEditable(false);
        TextOp2.setEditable(false);
        TextOp3.setEditable(false);
        TextOp4.setEditable(false);

        this.jP2.setVisible(false);
        this.jP3.setVisible(false);
        this.jP4.setVisible(false);
        this.jP5.setVisible(false);
        this.jPanel9.setVisible(false);

        display_talbeQuestion();
        display_talbeUser();
        display_tableResult();

    }

    private void display_talbeQuestion() {
        int CC;
        String type;
        String topic;
        String id;

        if (FilterTextF.getText().isEmpty()) {
            try {

                Connection con = dbConnect.Connection();
                Statement st = con.createStatement();
                String sql = " select * from tbQuestion\n";
                ResultSet rs = st.executeQuery(sql);

                ResultSetMetaData RSMD = rs.getMetaData();

                CC = RSMD.getColumnCount();

                DefaultTableModel DFT = (DefaultTableModel) QuestionTable.getModel();
                DFT.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= CC; i++) {
                        v2.add(rs.getInt("questionID"));
                        v2.add(rs.getString("content"));
                        v2.add(rs.getInt("type"));
                        v2.add(rs.getString("topic"));
                        v2.add(rs.getBoolean("answer"));

                    }
                    DFT.addRow(v2);

                }
            } catch (Exception e) {
                System.err.println("ERROR" + e.getMessage());
            }
        } else {

            try {

                Connection con = dbConnect.Connection();
                Statement st = con.createStatement();
                String sql = "SELECT * FROM tbQuestion";

//                if (FilterCombobox.getSelectedItem().) {
//
//                   topic = FilterTextF.getText();
//                    System.out.println(""+topic);
//                    sql += " WHERE topic LIKE '%" + topic + "%'";
//                }
                if (FilterCombobox.getSelectedItem().equals("Topic")) {
                    topic = FilterTextF.getText();
                    sql += " WHERE topic LIKE '%" + topic + "%'";

                }
                if (FilterCombobox.getSelectedItem().equals("Level")) {
                    type = FilterTextF.getText();

                    sql += " WHERE type = '" + type + "'";
                }
                if (FilterCombobox.getSelectedItem().equals("ID")) {
                    id = FilterTextF.getText();

                    sql += " WHERE questionid = '" + id + "'";
                }

                ResultSet rs = st.executeQuery(sql);

                ResultSetMetaData RSMD = rs.getMetaData();

                CC = RSMD.getColumnCount();

                DefaultTableModel DFT = (DefaultTableModel) QuestionTable.getModel();
                DFT.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= CC; i++) {
                        v2.add(rs.getInt("questionID"));
                        v2.add(rs.getString("content"));
                        v2.add(rs.getInt("type"));
                        v2.add(rs.getString("topic"));
                        v2.add(rs.getBoolean("answer"));

                    }
                    DFT.addRow(v2);

                }
            } catch (Exception e) {
                System.err.println("ERROR" + e.getMessage());
            }

        }
    }

    private void display_talbeUser() {
        int CC;

        try {
            Connection conn = dbConnect.Connection();
            Statement st = conn.createStatement();
            String sql = "select * from tbUser";
            ResultSet rs = st.executeQuery(sql);

            ResultSetMetaData RSMD = rs.getMetaData();

            CC = RSMD.getColumnCount();

            DefaultTableModel DFT = (DefaultTableModel) TablelUser.getModel();
            DFT.setRowCount(0);
            while (rs.next()) {
                Vector v3 = new Vector();
                for (int i = 1; i <= CC; i++) {
                    v3.add(rs.getInt("userID"));
                    v3.add(rs.getString("name"));
                    v3.add(rs.getString("phone"));
                    v3.add(rs.getString("gender"));
                    v3.add(rs.getString("bod"));
                    v3.add(rs.getString("role"));
                    v3.add(rs.getString("username"));
                    v3.add(rs.getString("password"));
                    v3.add(rs.getString("email"));
                }
                DFT.addRow(v3);
            }
        } catch (Exception e) {
            System.err.println("ERROR" + e.getMessage());
        }
    }

    private void display_tableResult() {
        int CC;
        String level;
        String topic;
        String id;

        if (FilterResultTextF.getText().isEmpty()) {
            try {
                Connection con = dbConnect.Connection();
                Statement st = con.createStatement();
                String sql = "select*from tbresult";
                ResultSet rs = st.executeQuery(sql);

                ResultSetMetaData RSMD = rs.getMetaData();

                CC = RSMD.getColumnCount();

                DefaultTableModel DFT = (DefaultTableModel) ResultTable.getModel();
                DFT.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= CC; i++) {
                        v2.add(rs.getInt("resultID"));
                        v2.add(rs.getInt("userID"));
                        v2.add(rs.getInt("score"));
                        v2.add(rs.getString("duration"));
                        v2.add(rs.getString("date"));
                        v2.add(rs.getInt("level"));
                        v2.add(rs.getString("topic"));
                    }
                    DFT.addRow(v2);
                }
            } catch (Exception e) {
                System.err.println("ERROR: " + e.getMessage());
            }
        } else {

            try {

                Connection con = dbConnect.Connection();
                Statement st = con.createStatement();
                String sql = "SELECT * FROM tbResult";

                //                if (FilterCombobox.getSelectedItem().) {
//
//                   topic = FilterTextF.getText();
//                    System.out.println(""+topic);
//                    sql += " WHERE topic LIKE '%" + topic + "%'";
//                }
                if (FilterResultCombobox.getSelectedItem().equals("userID")) {
                    id = FilterResultTextF.getText();
                    sql += " WHERE  userID = " + id + "";

                }
                if (FilterResultCombobox.getSelectedItem().equals("Level")) {
                    level = FilterResultTextF.getText();
                    sql += " WHERE level = '" + level + "'";

                }
                if (FilterResultCombobox.getSelectedItem().equals("Topic")) {
                    topic = FilterResultTextF.getText();
                    sql += " WHERE topic LIKE '%" + topic + "%'";
                }

                ResultSet rs = st.executeQuery(sql);

                ResultSetMetaData RSMD = rs.getMetaData();

                CC = RSMD.getColumnCount();

                DefaultTableModel DFT = (DefaultTableModel) ResultTable.getModel();
                DFT.setRowCount(0);
                while (rs.next()) {
                    Vector v2 = new Vector();
                    for (int i = 1; i <= CC; i++) {
                        v2.add(rs.getInt("resultID"));
                        v2.add(rs.getInt("userID"));
                        v2.add(rs.getInt("score"));
                        v2.add(rs.getString("duration"));
                        v2.add(rs.getString("date"));
                        v2.add(rs.getInt("level"));
                        v2.add(rs.getString("topic"));

                    }
                    DFT.addRow(v2);

                }
            } catch (Exception e) {
                System.err.println("ERROR" + e.getMessage());
            }

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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        tab1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tab2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tab3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tab4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        tab5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tab6 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jP1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        QuestionTable = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TextOp1 = new javax.swing.JTextField();
        TextOp2 = new javax.swing.JTextField();
        TextOp3 = new javax.swing.JTextField();
        TextOp4 = new javax.swing.JTextField();
        Checkbox1 = new javax.swing.JCheckBox();
        Checkbox2 = new javax.swing.JCheckBox();
        Checkbox3 = new javax.swing.JCheckBox();
        Checkbox4 = new javax.swing.JCheckBox();
        DeleteQuestionBtn = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        FilterTextF = new javax.swing.JTextField();
        FilterCombobox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jP2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        QuestionTextF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        TopicComboBox = new javax.swing.JComboBox<>();
        LevelComboBox = new javax.swing.JComboBox<>();
        anserCheck = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        OptionBtn = new javax.swing.JButton();
        TextOp5 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        TextOp6 = new javax.swing.JTextField();
        TextOp7 = new javax.swing.JTextField();
        jLabel49 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        TextOp8 = new javax.swing.JTextField();
        Checkbox5 = new javax.swing.JCheckBox();
        Checkbox6 = new javax.swing.JCheckBox();
        Checkbox7 = new javax.swing.JCheckBox();
        Checkbox8 = new javax.swing.JCheckBox();
        IdQuestionTextF = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel42 = new javax.swing.JLabel();
        jP4 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        ResultTable = new javax.swing.JTable();
        FilterResultCombobox = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        FilterResultTextF = new javax.swing.JTextField();
        DeleteResultBNT = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jP3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        QuestionContentTextF = new javax.swing.JTextField();
        ButtonUpdate = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        textsearchid = new javax.swing.JTextField();
        jLabel50 = new javax.swing.JLabel();
        TopicComboboxUpdate = new javax.swing.JComboBox<>();
        jCheckBox1 = new javax.swing.JCheckBox();
        Jp10 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        TextOp15 = new javax.swing.JTextField();
        TextOp14 = new javax.swing.JTextField();
        TextOp13 = new javax.swing.JTextField();
        TextOp12 = new javax.swing.JTextField();
        Checkbox9 = new javax.swing.JCheckBox();
        Checkbox10 = new javax.swing.JCheckBox();
        Checkbox11 = new javax.swing.JCheckBox();
        Checkbox12 = new javax.swing.JCheckBox();
        jButton4 = new javax.swing.JButton();
        LevelUpdate = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jP5 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablelUser = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jTextField23 = new javax.swing.JTextField();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        textsearch = new javax.swing.JTextField();
        addbtn = new javax.swing.JButton();
        updatebtn = new javax.swing.JButton();
        removebtn = new javax.swing.JButton();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quiz");
        setLocation(new java.awt.Point(75, 75));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(76, 180, 171));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tab1.setBackground(new java.awt.Color(179, 179, 179));
        tab1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab1MouseExited(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(36, 36, 36));
        jLabel1.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("LIST QUESTION");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(70, 70, 70))
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(21, 21, 21))
        );

        jPanel1.add(tab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 240, 70));

        tab2.setBackground(new java.awt.Color(179, 179, 179));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab2MouseExited(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(36, 36, 36));
        jLabel2.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ADD QUESTION");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(77, 77, 77))
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        jPanel1.add(tab2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 240, 70));

        tab3.setBackground(new java.awt.Color(179, 179, 179));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab3MouseExited(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(36, 36, 36));
        jLabel3.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("UPDATE QUESTION");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel3)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(tab3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 360, 240, 70));

        tab4.setBackground(new java.awt.Color(179, 179, 179));
        tab4.setForeground(new java.awt.Color(0, 0, 0));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab4MouseExited(evt);
            }
        });

        jLabel4.setBackground(new java.awt.Color(36, 36, 36));
        jLabel4.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("RANK");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                .addContainerGap(96, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(95, 95, 95))
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(20, 20, 20))
        );

        jPanel1.add(tab4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 450, 240, 70));

        tab5.setBackground(new java.awt.Color(179, 179, 179));
        tab5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab5MouseExited(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(36, 36, 36));
        jLabel7.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("LIST USER");

        javax.swing.GroupLayout tab5Layout = new javax.swing.GroupLayout(tab5);
        tab5.setLayout(tab5Layout);
        tab5Layout.setHorizontalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(jLabel7)
                .addContainerGap(76, Short.MAX_VALUE))
        );
        tab5Layout.setVerticalGroup(
            tab5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab5Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel7)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jPanel1.add(tab5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 540, 240, 70));

        tab6.setBackground(new java.awt.Color(179, 179, 179));
        tab6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tab6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tab6MouseExited(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(36, 36, 36));
        jLabel6.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("LOG OUT");

        javax.swing.GroupLayout tab6Layout = new javax.swing.GroupLayout(tab6);
        tab6.setLayout(tab6Layout);
        tab6Layout.setHorizontalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab6Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel6)
                .addContainerGap(82, Short.MAX_VALUE))
        );
        tab6Layout.setVerticalGroup(
            tab6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab6Layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(21, 21, 21))
        );

        jPanel1.add(tab6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 630, 240, 70));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 120, 10));

        jLabel11.setFont(new java.awt.Font("PT Sans Caption", 1, 48)); // NOI18N
        jLabel11.setText("ADMIN");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 260, 720));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jP1.setBackground(new java.awt.Color(204, 204, 204));
        jP1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("PT Sans Caption", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/product.png"))); // NOI18N
        jLabel8.setText("LIST QUESTION ");
        jP1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jP1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 920, 10));

        QuestionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Question", "Type", "Topic", "Answer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        QuestionTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuestionTableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(QuestionTable);
        if (QuestionTable.getColumnModel().getColumnCount() > 0) {
            QuestionTable.getColumnModel().getColumn(0).setPreferredWidth(1);
            QuestionTable.getColumnModel().getColumn(1).setResizable(false);
            QuestionTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            QuestionTable.getColumnModel().getColumn(2).setResizable(false);
            QuestionTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            QuestionTable.getColumnModel().getColumn(3).setResizable(false);
            QuestionTable.getColumnModel().getColumn(3).setPreferredWidth(20);
            QuestionTable.getColumnModel().getColumn(4).setPreferredWidth(20);
        }

        jTabbedPane1.addTab("tab1", jScrollPane2);

        jP1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 890, 340));

        jPanel8.setBackground(new java.awt.Color(204, 204, 204));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel19.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setText("Option 1:");
        jPanel8.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 88, 20));

        jLabel43.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 0, 0));
        jLabel43.setText("Option 2:");
        jPanel8.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 53, 88, 20));

        jLabel44.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 0, 0));
        jLabel44.setText("Option 3:");
        jPanel8.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 94, 88, 20));

        jLabel5.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Option 4:");
        jPanel8.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 130, 88, 20));

        TextOp1.setBackground(new java.awt.Color(204, 204, 204));
        TextOp1.setForeground(new java.awt.Color(0, 0, 0));
        TextOp1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextOp1ActionPerformed(evt);
            }
        });
        jPanel8.add(TextOp1, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 16, 520, 24));

        TextOp2.setBackground(new java.awt.Color(204, 204, 204));
        TextOp2.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(TextOp2, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 52, 520, 24));

        TextOp3.setBackground(new java.awt.Color(204, 204, 204));
        TextOp3.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(TextOp3, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 90, 520, 24));

        TextOp4.setBackground(new java.awt.Color(204, 204, 204));
        TextOp4.setForeground(new java.awt.Color(0, 0, 0));
        jPanel8.add(TextOp4, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 130, 520, 22));

        Checkbox1.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox1.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox1.setText("True");
        Checkbox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox1ActionPerformed(evt);
            }
        });
        jPanel8.add(Checkbox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 17, -1, -1));

        Checkbox2.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox2.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox2.setText("True");
        Checkbox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox2ActionPerformed(evt);
            }
        });
        jPanel8.add(Checkbox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 53, -1, -1));

        Checkbox3.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox3.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox3.setText("True");
        Checkbox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox3ActionPerformed(evt);
            }
        });
        jPanel8.add(Checkbox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 91, -1, -1));

        Checkbox4.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox4.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox4.setText("True");
        Checkbox4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox4ActionPerformed(evt);
            }
        });
        jPanel8.add(Checkbox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(642, 130, -1, -1));

        DeleteQuestionBtn.setBackground(new java.awt.Color(204, 204, 204));
        DeleteQuestionBtn.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        DeleteQuestionBtn.setForeground(new java.awt.Color(0, 0, 0));
        DeleteQuestionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/clear.png"))); // NOI18N
        DeleteQuestionBtn.setText("Delete");
        DeleteQuestionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteQuestionBtnActionPerformed(evt);
            }
        });
        jPanel8.add(DeleteQuestionBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 100, 110, 40));

        jP1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 890, 170));

        jLabel14.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jLabel14.setText("Filter by");
        jP1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        FilterTextF.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        FilterTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterTextFActionPerformed(evt);
            }
        });
        FilterTextF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilterTextFKeyReleased(evt);
            }
        });
        jP1.add(FilterTextF, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 110, 110, 30));

        FilterCombobox.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        FilterCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", " ", "Level", "ID", "Topic" }));
        FilterCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterComboboxActionPerformed(evt);
            }
        });
        jP1.add(FilterCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 90, 30));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pages background student.jpg"))); // NOI18N
        jLabel15.setText("jLabel15");
        jP1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jPanel2.add(jP1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jP2.setBackground(new java.awt.Color(102, 102, 102));
        jP2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("PT Sans Caption", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Orders.png"))); // NOI18N
        jLabel9.setText("ADD NEW QUESTION");
        jP2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jP2.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 920, 10));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setFont(new java.awt.Font("PT Sans Caption", 1, 16)); // NOI18N
        jButton2.setForeground(new java.awt.Color(0, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/close.png"))); // NOI18N
        jButton2.setText("CLEAR ALL");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jP2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 660, 160, 50));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        QuestionTextF.setBackground(new java.awt.Color(204, 204, 204));
        QuestionTextF.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        QuestionTextF.setForeground(new java.awt.Color(0, 0, 0));
        QuestionTextF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Topic:");

        jLabel16.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("Question:");

        jLabel12.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Type");

        jLabel13.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("Answer");

        TopicComboBox.setBackground(new java.awt.Color(204, 204, 204));
        TopicComboBox.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        TopicComboBox.setForeground(new java.awt.Color(0, 0, 0));
        TopicComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "AJS", "SQL", "PHP", "HTML", "CSS", "AJS" }));

        LevelComboBox.setBackground(new java.awt.Color(204, 204, 204));
        LevelComboBox.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        LevelComboBox.setForeground(new java.awt.Color(0, 0, 0));
        LevelComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "1", "2", "3", " " }));

        anserCheck.setBackground(new java.awt.Color(204, 204, 204));
        anserCheck.setForeground(new java.awt.Color(0, 0, 0));
        anserCheck.setText("True");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TopicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuestionTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 751, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(anserCheck))
                .addGap(20, 20, 20))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(QuestionTextF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TopicComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LevelComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13)
                    .addComponent(anserCheck))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jP2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 910, 230));

        jPanel9.setBackground(new java.awt.Color(204, 204, 204));
        jPanel9.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel18.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setText("ID:");
        jPanel9.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 88, -1));

        OptionBtn.setBackground(new java.awt.Color(204, 204, 204));
        OptionBtn.setFont(new java.awt.Font("PT Sans Caption", 1, 16)); // NOI18N
        OptionBtn.setForeground(new java.awt.Color(0, 0, 0));
        OptionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        OptionBtn.setText("ADD OPTION");
        OptionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OptionBtnActionPerformed(evt);
            }
        });
        jPanel9.add(OptionBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(747, 186, 160, 50));

        TextOp5.setBackground(new java.awt.Color(204, 204, 204));
        TextOp5.setForeground(new java.awt.Color(0, 0, 0));
        TextOp5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextOp5ActionPerformed(evt);
            }
        });
        jPanel9.add(TextOp5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 520, 30));

        jLabel20.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Option 1:");
        jPanel9.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 88, 20));

        jLabel46.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(0, 0, 0));
        jLabel46.setText("Option 2:");
        jPanel9.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 88, 20));

        TextOp6.setBackground(new java.awt.Color(204, 204, 204));
        TextOp6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(TextOp6, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 120, 520, 30));

        TextOp7.setBackground(new java.awt.Color(204, 204, 204));
        TextOp7.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(TextOp7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 170, 520, 30));

        jLabel49.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(0, 0, 0));
        jLabel49.setText("Option 3:");
        jPanel9.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 88, 20));

        jLabel17.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setText("Option 4:");
        jPanel9.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 88, 20));

        TextOp8.setBackground(new java.awt.Color(204, 204, 204));
        TextOp8.setForeground(new java.awt.Color(0, 0, 0));
        jPanel9.add(TextOp8, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 220, 520, 30));

        Checkbox5.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox5.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox5.setText("True");
        Checkbox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox5ActionPerformed(evt);
            }
        });
        jPanel9.add(Checkbox5, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 80, -1, -1));

        Checkbox6.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox6.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox6.setText("True");
        Checkbox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox6ActionPerformed(evt);
            }
        });
        jPanel9.add(Checkbox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 130, -1, -1));

        Checkbox7.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox7.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox7.setText("True");
        Checkbox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox7ActionPerformed(evt);
            }
        });
        jPanel9.add(Checkbox7, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 180, -1, -1));

        Checkbox8.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox8.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox8.setText("True");
        Checkbox8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox8ActionPerformed(evt);
            }
        });
        jPanel9.add(Checkbox8, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 230, -1, -1));

        IdQuestionTextF.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        IdQuestionTextF.setForeground(new java.awt.Color(0, 0, 0));
        IdQuestionTextF.setText("1");
        jPanel9.add(IdQuestionTextF, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, -1, -1));

        jP2.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(36, 370, 920, 270));

        jButton1.setBackground(new java.awt.Color(204, 204, 204));
        jButton1.setFont(new java.awt.Font("PT Sans Caption", 1, 16)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        jButton1.setText("ADD Question");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jP2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 660, 200, 50));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pages background student.jpg"))); // NOI18N
        jLabel42.setText("jLabel42");
        jP2.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jPanel2.add(jP2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jP4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setBackground(new java.awt.Color(0, 0, 0));
        jLabel45.setFont(new java.awt.Font("PT Sans Caption", 1, 36)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(0, 0, 0));
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/all student result.png"))); // NOI18N
        jLabel45.setText("Results");
        jP4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, -1, -1));
        jP4.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 920, 10));

        ResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ResultID", "UserID", "Score", "Duration", "Date", "Level", "Topic"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ResultTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ResultTableMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(ResultTable);
        if (ResultTable.getColumnModel().getColumnCount() > 0) {
            ResultTable.getColumnModel().getColumn(0).setPreferredWidth(2);
            ResultTable.getColumnModel().getColumn(1).setPreferredWidth(1);
            ResultTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            ResultTable.getColumnModel().getColumn(3).setPreferredWidth(20);
            ResultTable.getColumnModel().getColumn(4).setPreferredWidth(20);
            ResultTable.getColumnModel().getColumn(5).setPreferredWidth(5);
            ResultTable.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        jP4.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 942, -1));

        FilterResultCombobox.setBackground(new java.awt.Color(204, 204, 204));
        FilterResultCombobox.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        FilterResultCombobox.setForeground(new java.awt.Color(0, 0, 0));
        FilterResultCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select--", "Level", "userID", "Topic" }));
        FilterResultCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FilterResultComboboxActionPerformed(evt);
            }
        });
        jP4.add(FilterResultCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 100, 30));

        jLabel47.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(0, 0, 0));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jLabel47.setText("Filter by");
        jP4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 110, -1, -1));

        FilterResultTextF.setBackground(new java.awt.Color(204, 204, 204));
        FilterResultTextF.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        FilterResultTextF.setForeground(new java.awt.Color(0, 0, 0));
        FilterResultTextF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                FilterResultTextFKeyReleased(evt);
            }
        });
        jP4.add(FilterResultTextF, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 110, 30));

        DeleteResultBNT.setBackground(new java.awt.Color(204, 204, 204));
        DeleteResultBNT.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        DeleteResultBNT.setForeground(new java.awt.Color(0, 0, 0));
        DeleteResultBNT.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete.png"))); // NOI18N
        DeleteResultBNT.setText("DELETE");
        DeleteResultBNT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteResultBNTActionPerformed(evt);
            }
        });
        jP4.add(DeleteResultBNT, new org.netbeans.lib.awtextra.AbsoluteConstraints(874, 627, -1, 43));

        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pages background student.jpg"))); // NOI18N
        jLabel48.setText("jLabel48");
        jP4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jPanel2.add(jP4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jP3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setToolTipText("");
        jPanel4.setMinimumSize(new java.awt.Dimension(1280, 720));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel21.setFont(new java.awt.Font("PT Sans Caption", 1, 36)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Update Question.png"))); // NOI18N
        jLabel21.setText("UPDATE QUESTION");
        jPanel4.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 41, -1, -1));
        jPanel4.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 116, 968, -1));

        jLabel22.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Question ID:");
        jPanel4.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 140, -1, -1));

        jLabel23.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Question:");
        jLabel23.setToolTipText("");
        jPanel4.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, -1, -1));

        jLabel28.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 0, 0));
        jLabel28.setText("Answer:");
        jLabel28.setToolTipText("");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 340, -1, -1));

        QuestionContentTextF.setBackground(new java.awt.Color(255, 255, 255));
        QuestionContentTextF.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        QuestionContentTextF.setMaximumSize(null);
        QuestionContentTextF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuestionContentTextFActionPerformed(evt);
            }
        });
        jPanel4.add(QuestionContentTextF, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 751, 30));

        ButtonUpdate.setBackground(new java.awt.Color(204, 204, 204));
        ButtonUpdate.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        ButtonUpdate.setForeground(new java.awt.Color(0, 0, 0));
        ButtonUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/save.png"))); // NOI18N
        ButtonUpdate.setText("Update Question");
        ButtonUpdate.setMaximumSize(new java.awt.Dimension(105, 35));
        ButtonUpdate.setMinimumSize(new java.awt.Dimension(105, 35));
        ButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonUpdateActionPerformed(evt);
            }
        });
        jPanel4.add(ButtonUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 660, 210, 50));

        jLabel29.setBackground(new java.awt.Color(255, 255, 255));
        jLabel29.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 0, 0));
        jLabel29.setText("Level");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, -1, -1));

        textsearchid.setBackground(new java.awt.Color(255, 255, 255));
        textsearchid.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        textsearchid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsearchidActionPerformed(evt);
            }
        });
        jPanel4.add(textsearchid, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 140, 60, 30));

        jLabel50.setBackground(new java.awt.Color(255, 255, 255));
        jLabel50.setFont(new java.awt.Font("PT Sans Caption", 1, 20)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(0, 0, 0));
        jLabel50.setText("Topic:");
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        TopicComboboxUpdate.setBackground(new java.awt.Color(255, 255, 255));
        TopicComboboxUpdate.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        TopicComboboxUpdate.setForeground(new java.awt.Color(0, 0, 0));
        TopicComboboxUpdate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Select Topic--", "AJS", "PHP", "SQL", "CSS", "HTML", "Java" }));
        TopicComboboxUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopicComboboxUpdateActionPerformed(evt);
            }
        });
        jPanel4.add(TopicComboboxUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 290, 200, 35));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("True");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });
        jPanel4.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 338, -1, 25));

        Jp10.setBackground(new java.awt.Color(204, 204, 204));
        Jp10.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Jp10.setForeground(new java.awt.Color(204, 204, 204));
        Jp10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Option 1:");
        Jp10.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 88, 20));

        jLabel51.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(0, 0, 0));
        jLabel51.setText("Option 2:");
        Jp10.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 88, 20));

        jLabel52.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 0, 0));
        jLabel52.setText("Option 3:");
        Jp10.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 88, 20));

        jLabel25.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 0, 0));
        jLabel25.setText("Option 4:");
        Jp10.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 88, 20));

        TextOp15.setBackground(new java.awt.Color(204, 204, 204));
        TextOp15.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        TextOp15.setForeground(new java.awt.Color(0, 0, 0));
        TextOp15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextOp15ActionPerformed(evt);
            }
        });
        Jp10.add(TextOp15, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 650, 30));

        TextOp14.setBackground(new java.awt.Color(204, 204, 204));
        TextOp14.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        TextOp14.setForeground(new java.awt.Color(0, 0, 0));
        Jp10.add(TextOp14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 650, 30));

        TextOp13.setBackground(new java.awt.Color(204, 204, 204));
        TextOp13.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        TextOp13.setForeground(new java.awt.Color(0, 0, 0));
        Jp10.add(TextOp13, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 650, 30));

        TextOp12.setBackground(new java.awt.Color(204, 204, 204));
        TextOp12.setFont(new java.awt.Font("PT Sans Caption", 1, 12)); // NOI18N
        TextOp12.setForeground(new java.awt.Color(0, 0, 0));
        TextOp12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextOp12ActionPerformed(evt);
            }
        });
        Jp10.add(TextOp12, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 650, 30));

        Checkbox9.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox9.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox9.setText("True");
        Checkbox9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox9ActionPerformed(evt);
            }
        });
        Jp10.add(Checkbox9, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 20, -1, -1));

        Checkbox10.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox10.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox10.setText("True");
        Checkbox10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox10ActionPerformed(evt);
            }
        });
        Jp10.add(Checkbox10, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 70, -1, -1));

        Checkbox11.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox11.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox11.setText("True");
        Checkbox11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox11ActionPerformed(evt);
            }
        });
        Jp10.add(Checkbox11, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 110, -1, -1));

        Checkbox12.setBackground(new java.awt.Color(204, 204, 204));
        Checkbox12.setForeground(new java.awt.Color(0, 0, 0));
        Checkbox12.setText("True");
        Checkbox12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Checkbox12ActionPerformed(evt);
            }
        });
        Jp10.add(Checkbox12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 160, -1, -1));

        jPanel4.add(Jp10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 950, 200));

        jButton4.setBackground(new java.awt.Color(204, 204, 204));
        jButton4.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(0, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jButton4.setText("Search");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 140, 110, 30));

        LevelUpdate.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        LevelUpdate.setForeground(new java.awt.Color(0, 0, 0));
        LevelUpdate.setText("0");
        jPanel4.add(LevelUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 234, 50, 30));

        jLabel26.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/pages background student.jpg"))); // NOI18N
        jLabel26.setText("jLabel26");
        jPanel4.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jP3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1020, 720));

        jPanel2.add(jP3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jP5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TablelUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {"", "", null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "NAME", "PHONE", "GENDER", "BOD", "ROLE", "USERNAME", "PASSWORD", "EMAIL"
            }
        ));
        TablelUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablelUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablelUser);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 831, 281));

        jLabel30.setText("ID");
        jLabel30.setToolTipText("");

        jLabel31.setText("NAME");
        jLabel31.setToolTipText("");

        jLabel32.setText("PHONE");
        jLabel32.setToolTipText("");

        jLabel33.setText("GENDER");
        jLabel33.setToolTipText("");

        jLabel34.setText("BOD");
        jLabel34.setToolTipText("");

        jLabel35.setText("ROLE");
        jLabel35.setToolTipText("");

        jLabel36.setText("USERNAME");
        jLabel36.setToolTipText("");

        jLabel37.setText("PASSWORD");
        jLabel37.setToolTipText("");

        jLabel39.setText("EMAIL");
        jLabel39.setToolTipText("");

        jTextField17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField17ActionPerformed(evt);
            }
        });

        jTextField18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(jLabel30))
                        .addGap(30, 30, 30)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel32)
                        .addGroup(jPanel6Layout.createSequentialGroup()
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel33)
                                .addComponent(jLabel34))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField21)
                                .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(192, 192, 192)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel39))
                .addGap(35, 35, 35)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel30))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(jTextField23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel37))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel32)
                        .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jTextField20, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jTextField21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField25, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel39)))
                .addGap(485, 485, 485))
        );

        jPanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 670, 260));

        jPanel7.setToolTipText("");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search.png"))); // NOI18N
        jButton7.setText("Search");
        jButton7.setToolTipText("");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        textsearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textsearchActionPerformed(evt);
            }
        });
        textsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textsearchKeyReleased(evt);
            }
        });

        addbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/add new question.png"))); // NOI18N
        addbtn.setText("ADD");
        addbtn.setToolTipText("");
        addbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addbtnActionPerformed(evt);
            }
        });

        updatebtn.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        updatebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Update Question.png"))); // NOI18N
        updatebtn.setText("UPDATE");
        updatebtn.setToolTipText("");
        updatebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updatebtnActionPerformed(evt);
            }
        });

        removebtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/delete Question.png"))); // NOI18N
        removebtn.setText("REMOVE");
        removebtn.setToolTipText("");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(textsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton7))
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(updatebtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(addbtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(removebtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)))
                .addGap(0, 23, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(updatebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(addbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(removebtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 8, Short.MAX_VALUE))
        );

        jPanel5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 60, -1, 280));

        jLabel40.setFont(new java.awt.Font("PT Sans Caption", 1, 36)); // NOI18N
        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Users.png"))); // NOI18N
        jLabel40.setText("LIST USER");
        jPanel5.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/background3.png"))); // NOI18N
        jLabel41.setText("jLabel28");
        jPanel5.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        jP5.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(-50, 0, -1, 770));

        jPanel2.add(jP5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 0, 1020, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        // TODO add your handling code here:
        jP1.setVisible(true);
        jP2.setVisible(false);
        jP3.setVisible(false);
        jP4.setVisible(false);
        jP5.setVisible(false);
        tab1.setBackground(Color.white);
        tab2.setBackground(new Color(179, 179, 179));
        tab3.setBackground(new Color(179, 179, 179));
        tab4.setBackground(new Color(179, 179, 179));
        tab5.setBackground(new Color(179, 179, 179));
        tab6.setBackground(new Color(179, 179, 179));
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        jP1.setVisible(false);
        jP2.setVisible(true);
        jP3.setVisible(false);
        jP4.setVisible(false);
        jP5.setVisible(false);
        tab2.setBackground(Color.white);
        tab1.setBackground(new Color(179, 179, 179));
        tab3.setBackground(new Color(179, 179, 179));
        tab4.setBackground(new Color(179, 179, 179));
        tab5.setBackground(new Color(179, 179, 179));
        tab6.setBackground(new Color(179, 179, 179));

    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        // TODO add your handling code here:
        jP1.setVisible(false);
        jP2.setVisible(false);
        jP3.setVisible(true);
        jP4.setVisible(false);
        jP5.setVisible(false);
        tab3.setBackground(Color.white);
        tab2.setBackground(new Color(179, 179, 179));
        tab1.setBackground(new Color(179, 179, 179));
        tab4.setBackground(new Color(179, 179, 179));
        tab5.setBackground(new Color(179, 179, 179));
        tab6.setBackground(new Color(179, 179, 179));
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        // TODO add your handling code here:
        jP1.setVisible(false);
        jP2.setVisible(false);
        jP3.setVisible(false);
        jP4.setVisible(true);
        jP5.setVisible(false);
        tab4.setBackground(Color.white);
        tab2.setBackground(new Color(179, 179, 179));
        tab3.setBackground(new Color(179, 179, 179));
        tab1.setBackground(new Color(179, 179, 179));
        tab5.setBackground(new Color(179, 179, 179));
        tab6.setBackground(new Color(179, 179, 179));
    }//GEN-LAST:event_tab4MouseClicked

    private void tab5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseClicked
        // TODO add your handling code here:
        jP1.setVisible(false);
        jP2.setVisible(false);
        jP3.setVisible(false);
        jP4.setVisible(false);
        jP5.setVisible(true);
        tab5.setBackground(Color.white);
        tab2.setBackground(new Color(179, 179, 179));
        tab3.setBackground(new Color(179, 179, 179));
        tab4.setBackground(new Color(179, 179, 179));
        tab1.setBackground(new Color(179, 179, 179));
        tab6.setBackground(new Color(179, 179, 179));
    }//GEN-LAST:event_tab5MouseClicked

    private void tab6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseClicked
        // TODO add your handling code here:
        tab6.setBackground(Color.white);
        tab2.setBackground(new Color(179, 179, 179));
        tab3.setBackground(new Color(179, 179, 179));
        tab4.setBackground(new Color(179, 179, 179));
        tab5.setBackground(new Color(179, 179, 179));
        tab1.setBackground(new Color(179, 179, 179));
        JFrame jf = new JFrame();
        jf.setAlwaysOnTop(true);
        int a = JOptionPane.showConfirmDialog(jf, "Do you want to Logout?", "Select", JOptionPane.YES_NO_OPTION);
        if (a == 0) {
            setVisible(false);
            new index().setVisible(true);
        }

    }//GEN-LAST:event_tab6MouseClicked

    //reset add new
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        TopicComboBox.setSelectedItem("--Select--");
        LevelComboBox.setSelectedItem("--Select--");
        QuestionTextF.setText("");
        anserCheck.setSelected(false);
        TextOp5.setText("");
        TextOp6.setText("");
        TextOp7.setText("");
        TextOp8.setText("");
        Checkbox8.setSelected(false);
        Checkbox8.setSelected(false);
        Checkbox8.setSelected(false);

        Checkbox8.setSelected(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        boolean answer;
        String content = QuestionTextF.getText();
        String topic = (String) TopicComboBox.getSelectedItem();
        String typeStr = (String) LevelComboBox.getSelectedItem();

        if (anserCheck.isSelected()) {
            answer = true;
        } else {
            answer = false;
        }

        if (content.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Question cannot be blank!");
        } else if ("--Select--".equals(topic)) {
            JOptionPane.showMessageDialog(null, "The topic must be selected!");
        } else if ("--Select--".equals(typeStr)) {
            JOptionPane.showMessageDialog(null, "The type must be selected!");
        } else {
            try {
                int type = Integer.parseInt(typeStr);

                Connection con = dbConnect.Connection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO tbQuestion (content, type, topic, answer) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, content);
                ps.setInt(2, type);
                ps.setString(3, topic);
                ps.setBoolean(4, answer);

                int affectedRows = ps.executeUpdate(); // Execute the SQL query

                if (affectedRows > 0) {
                    JOptionPane.showMessageDialog(null, "Value inserted successfully!");
                    try (var generatedKeys = ps.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            questionID = generatedKeys.getInt(1);
                            System.out.println("Value inserted successfully! questionID: " + questionID);

                        }
                    }

                    if (type == 1) {
                        this.dispose();
                        new indexAdminm().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please insert options for level " + type + "!");

                        IdQuestionTextF.setText(String.valueOf(questionID));
                        jPanel9.setVisible(true);
                        jButton1.setVisible(false);

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error: Insertion failed!");
                }
            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace(); // Log the exception for debugging purposes
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

//hover
    private void tab1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tab1MouseEntered

    private void tab1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_tab1MouseExited

    private void tab2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tab2MouseEntered

    private void tab2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_tab2MouseExited

    private void tab3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseEntered
        // TODO add your handling code here:


    }//GEN-LAST:event_tab3MouseEntered

    private void tab3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_tab3MouseExited

    private void tab4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tab4MouseEntered

    private void tab4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseExited

    }//GEN-LAST:event_tab4MouseExited

    private void tab5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tab5MouseEntered

    private void tab5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab5MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_tab5MouseExited

    private void tab6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_tab6MouseEntered

    private void tab6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab6MouseExited
        // TODO add your handling code here:

    }//GEN-LAST:event_tab6MouseExited

    private void TablelUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablelUserMouseClicked
        DefaultTableModel model = (DefaultTableModel) TablelUser.getModel();
        int selectedRowIndex = TablelUser.getSelectedRow();
        jTextField17.setText(model.getValueAt(selectedRowIndex, 0).toString());
        jTextField18.setText(model.getValueAt(selectedRowIndex, 1).toString());
        jTextField19.setText(model.getValueAt(selectedRowIndex, 2).toString());
        jTextField20.setText(model.getValueAt(selectedRowIndex, 3).toString());
        jTextField21.setText(model.getValueAt(selectedRowIndex, 4).toString());
        jTextField22.setText(model.getValueAt(selectedRowIndex, 5).toString());
        jTextField23.setText(model.getValueAt(selectedRowIndex, 6).toString());
        jTextField24.setText(model.getValueAt(selectedRowIndex, 7).toString());
        jTextField25.setText(model.getValueAt(selectedRowIndex, 8).toString());

        // TODO add your handling code here:
    }//GEN-LAST:event_TablelUserMouseClicked

    private void jTextField17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField17ActionPerformed

    private void jTextField18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel ob = (DefaultTableModel) TablelUser.getModel();
        TableRowSorter<DefaultTableModel> obj = new TableRowSorter<>(ob);
        TablelUser.setRowSorter(obj);
        obj.setRowFilter(RowFilter.regexFilter(textsearch.getText()));
    }//GEN-LAST:event_jButton7ActionPerformed

    private void textsearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textsearchActionPerformed

    private void textsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textsearchKeyReleased
        DefaultTableModel model = (DefaultTableModel) TablelUser.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        TablelUser.setRowSorter(sorter);

        // Get the search text and convert it to lowercase
        String searchText = textsearch.getText().toLowerCase();

        // Apply a row filter that ignores case
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));         // TODO add your handling code here:
    }//GEN-LAST:event_textsearchKeyReleased

    private void addbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addbtnActionPerformed
        // TODO add your handling code here:
        int userID;
        String name = jTextField18.getText();
        String phone = jTextField19.getText();
        String gender = jTextField20.getText();
        String bod = jTextField21.getText();
        String role = jTextField22.getText();
        String username = jTextField23.getText();
        String password = jTextField24.getText();
        String email = jTextField25.getText();
        if (name.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in NAME");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (role.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in ROLE");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (username.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in USERNAME");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (phone.isEmpty()) {
            // Kiểm tra xem phone chỉ chứa số

            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter only number");
            return;

        } else if (gender.isEmpty() || (!gender.equals("Male") && !gender.equals("Female") && !gender.equals("Unknow"))) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid gender (MALE, FEMALE, or Unknow).");
            return;
        } else if (password.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a password.");
            return;
        } // Kiểm tra xem password có ít nhất 6 ký tự không
        else if (password.length() < 6) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Password must be at least 6 characters.");
            return;
        } // Kiểm tra xem email có được nhập không
        else if (email.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter an email address.");
            return;
        } // Kiểm tra xem email có chứa ký tự "@" không
        else if (!email.contains("@")) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid email address.");
            return;
        } else if (bod.isEmpty() || !bod.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid date of birth (format: YYYY-MM-DD).");
            return;
        } else {
            try (Connection conn = dbConnect.Connection(); PreparedStatement ps = conn.prepareStatement("INSERT INTO tbUSER ( name, phone, gender, bod, role, username, password, email) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?)")) {

                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, gender);
                ps.setString(4, bod);
                ps.setString(5, role);
                ps.setString(6, username);
                ps.setString(7, password);
                ps.setString(8, email);

                ps.executeUpdate();

                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, "Successfully Inserted!!!");
                setVisible(false);
                new indexAdminm().setVisible(true);

            } catch (Exception e) {
                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, e.getMessage());

            }
        }
    }//GEN-LAST:event_addbtnActionPerformed

    private void updatebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updatebtnActionPerformed
        String userID = jTextField17.getText();
        jTextField17.setEditable(false);
        String name = jTextField18.getText();
        String phone = jTextField19.getText();
        String gender = jTextField20.getText();
        String bod = jTextField21.getText();
        String role = jTextField22.getText();
        String username = jTextField23.getText();
        String password = jTextField24.getText();
        String email = jTextField25.getText();

        if (name.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in NAME");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (role.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in ROLE");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (username.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please fill in USERNAME");
            return; // Không thực hiện UPDATE nếu có ô nhập bị bỏ trống
        } else if (phone.isEmpty()) {
            // Kiểm tra xem phone chỉ chứa số

            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter only number");
            return;

        } else if (gender.isEmpty() || (!gender.equals("Male") && !gender.equals("Female") && !gender.equals("Unknow"))) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid gender (MALE, FEMALE, or Unknow).");
            return;
        } else if (password.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a password.");
            return;
        } // Kiểm tra xem password có ít nhất 6 ký tự không
        else if (password.length() < 6) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Password must be at least 6 characters.");
            return;
        } // Kiểm tra xem email có được nhập không
        else if (email.isEmpty()) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter an email address.");
            return;
        } // Kiểm tra xem email có chứa ký tự "@" không
        else if (!email.contains("@")) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid email address.");
            return;
        } else if (bod.isEmpty() || !bod.matches("\\d{4}-\\d{2}-\\d{2}")) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Please enter a valid date of birth (format: YYYY-MM-DD).");
            return;
        } else {
            try (Connection conn = dbConnect.Connection(); PreparedStatement ps = conn.prepareStatement("UPDATE tbUSER SET name=?, phone=?, gender=?, bod=?, role=?, username=?, password=?, email=? WHERE userid=?")) {

                ps.setString(1, name);
                ps.setString(2, phone);
                ps.setString(3, gender);
                ps.setString(4, bod);
                ps.setString(5, role);
                ps.setString(6, username);
                ps.setString(7, password);
                ps.setString(8, email);
                ps.setString(9, userID);

                ps.executeUpdate();

                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, "Successfully Updated!!!");
                setVisible(false);
                new indexAdminm().setVisible(true);

            } catch (Exception e) {
                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, e.getMessage());
            }
            // TODO add} your handling code here:
        }
    }//GEN-LAST:event_updatebtnActionPerformed

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) TablelUser.getModel();

        try {
            int selectedRowIndex = TablelUser.getSelectedRow();

            // Check if a row is selected
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this result?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return; // User canceled deletion
            }

            // Get the values from the selected row
            String userID = model.getValueAt(selectedRowIndex, 0).toString();  // Assuming the userID is in the first column

            // Delete the row from the database
            Connection conn = dbConnect.Connection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tbUser WHERE userid = ?; DELETE FROM tbresult WHERE userid = ?");
            ps.setString(1, userID);
            ps.setString(2, userID);

            ps.executeUpdate();

            // Remove the row from the table
            model.removeRow(selectedRowIndex);

            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Successfully Removed!!!");

        } catch (Exception ex) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_removebtnActionPerformed

    private void Checkbox4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox4ActionPerformed

    private void Checkbox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox3ActionPerformed

    private void Checkbox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox2ActionPerformed

    private void Checkbox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox1ActionPerformed

    private void TextOp1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextOp1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextOp1ActionPerformed

    private void FilterTextFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilterTextFKeyReleased
        // TODO add your handling code here:
        display_talbeQuestion();
    }//GEN-LAST:event_FilterTextFKeyReleased

    private void QuestionTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuestionTableMouseClicked
        // TODO add your handling code here:
        int rowIndex = QuestionTable.getSelectedRow();

        if (rowIndex != -1) {
            int questionType = (int) QuestionTable.getValueAt(rowIndex, 2);

            if (questionType == 1) {
                TextOp1.setText("");
                TextOp2.setText("");
                TextOp3.setText("");
                TextOp4.setText("");
                Checkbox1.setSelected(false);
                Checkbox2.setSelected(false);
                Checkbox3.setSelected(false);
                Checkbox4.setSelected(false);
            } else if (questionType == 2 || questionType == 3) {
                Object id = QuestionTable.getValueAt(rowIndex, 0);

                ArrayList<Option> ops = (ArrayList<Option>) dao.OptionDAO.get((int) id);

                // Ensure ops list is not null and has at least 4 elements before accessing
                if (ops != null && ops.size() >= 4) {
                    TextOp1.setText(ops.get(0).content);
                    TextOp2.setText(ops.get(1).content);
                    TextOp3.setText(ops.get(2).content);
                    TextOp4.setText(ops.get(3).content);

                    Checkbox1.setSelected(ops.get(0).answer);
                    Checkbox2.setSelected(ops.get(1).answer);
                    Checkbox3.setSelected(ops.get(2).answer);
                    Checkbox4.setSelected(ops.get(3).answer);
                } else {
                    // Handle the case where the ops list doesn't have enough elements
                    JOptionPane.showMessageDialog(this, "dont have options for the question.");
                    TextOp1.setText("");
                    TextOp2.setText("");
                    TextOp3.setText("");
                    TextOp4.setText("");
                    Checkbox1.setSelected(false);
                    Checkbox2.setSelected(false);
                    Checkbox3.setSelected(false);
                    Checkbox4.setSelected(false);
                }
            }
        }


    }//GEN-LAST:event_QuestionTableMouseClicked

    private void OptionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OptionBtnActionPerformed
        // TODO add your handling code here:

        String[] optionContents = {TextOp5.getText(), TextOp6.getText(), TextOp7.getText(), TextOp8.getText()};
        boolean[] booleanAnswer = {Checkbox5.isSelected(), Checkbox6.isSelected(), Checkbox7.isSelected(), Checkbox8.isSelected()};
        boolean allFieldsFilled = true;

        for (String content : optionContents) {
            if (content.isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }

        boolean atLeastOneSelected = false;
        for (boolean answer : booleanAnswer) {
            if (answer) {
                atLeastOneSelected = true;
                break;
            }
        }
        if (!allFieldsFilled) {
            JOptionPane.showMessageDialog(null, "Please fill in all option fields.");
        } else if (!atLeastOneSelected) {
            JOptionPane.showMessageDialog(null, "Please select at least one correct answer.");
        } else {

            try {

                Connection con = dbConnect.Connection();
                PreparedStatement ps = con.prepareStatement("INSERT INTO tboption (questionID, content,answer) VALUES (?, ?,?)");

                for (int i = 0; i < optionContents.length; i++) {
                    ps.setString(1, String.valueOf(questionID));
                    ps.setString(2, optionContents[i]);
                    ps.setBoolean(3, booleanAnswer[i]);

                    int affectedRows = ps.executeUpdate();

                    if (affectedRows > 0) {
                        System.out.println("Option " + (i + 1) + " inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Insertion failed for option " + (i + 1));
                    }
                }

                JOptionPane.showMessageDialog(null, "Options inserted successfully!");
                this.dispose();
                new indexAdminm().setVisible(true);

            } catch (SQLException | NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_OptionBtnActionPerformed

    private void ResultTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ResultTableMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_ResultTableMouseClicked

    private void FilterResultTextFKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_FilterResultTextFKeyReleased
        // TODO add your handling code here:
        display_tableResult();

    }//GEN-LAST:event_FilterResultTextFKeyReleased

    private void DeleteResultBNTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteResultBNTActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) ResultTable.getModel();

        try {
            int selectedRowIndex = ResultTable.getSelectedRow();

            // Check if a row is selected
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this result?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return; // User canceled deletion
            }

            // Get the values from the selected row
            String ResultID = model.getValueAt(selectedRowIndex, 0).toString();  // Assuming the userID is in the first column

            // Delete the row from the database
            Connection conn = dbConnect.Connection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tbResult WHERE resultID = ?");
            ps.setString(1, ResultID);
            ps.executeUpdate();

            // Remove the row from the table
            model.removeRow(selectedRowIndex);

            JOptionPane.showMessageDialog(this, "Successfully Removed!!!");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_DeleteResultBNTActionPerformed

    private void FilterResultComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterResultComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterResultComboboxActionPerformed

    private void FilterTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterTextFActionPerformed

    private void TextOp5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextOp5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextOp5ActionPerformed

    private void Checkbox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox5ActionPerformed

    private void Checkbox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox6ActionPerformed

    private void Checkbox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox7ActionPerformed

    private void Checkbox8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox8ActionPerformed

    private void DeleteQuestionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteQuestionBtnActionPerformed
        DefaultTableModel model = (DefaultTableModel) QuestionTable.getModel();

        try {
            int selectedRowIndex = QuestionTable.getSelectedRow();

            // Check if a row is selected
            if (selectedRowIndex == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }

            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this question?", "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return; // User canceled deletion
            }

            // Get the values from the selected row
            String questionID = model.getValueAt(selectedRowIndex, 0).toString();  // Assuming the userID is in the first column

            // Delete the row from the database
            Connection conn = dbConnect.Connection();
            PreparedStatement ps = conn.prepareStatement("DELETE FROM tboption WHERE questionid = ?; DELETE FROM tbquestion WHERE questionid = ?");
            ps.setString(1, questionID);
            ps.setString(2, questionID);

            ps.executeUpdate();

            // Remove the row from the table
            model.removeRow(selectedRowIndex);

            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Successfully Removed!!!");
            TextOp1.setText("");
            TextOp2.setText("");
            TextOp3.setText("");
            TextOp4.setText("");
            Checkbox1.setSelected(false);
            Checkbox2.setSelected(false);
            Checkbox3.setSelected(false);
            Checkbox4.setSelected(false);

        } catch (Exception ex) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "Error: " + ex.getMessage());
        }
    }//GEN-LAST:event_DeleteQuestionBtnActionPerformed

    private void Checkbox12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox12ActionPerformed

    private void Checkbox11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox11ActionPerformed

    private void Checkbox10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox10ActionPerformed

    private void Checkbox9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Checkbox9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Checkbox9ActionPerformed

    private void TextOp12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextOp12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextOp12ActionPerformed

    private void ButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonUpdateActionPerformed
        String idUpdate = textsearchid.getText();
        String questionUpdate = QuestionContentTextF.getText();
        String levelUpdate = LevelUpdate.getText();
        String topicUpdate = TopicComboboxUpdate.getSelectedItem().toString();
        boolean answerUpdate = jCheckBox1.isSelected();

        if (idUpdate.isEmpty() || questionUpdate.isEmpty() || levelUpdate.isEmpty() || topicUpdate.equals("--Select Topic--")) {
            JOptionPane.showMessageDialog(null, "Please fill in all fields before updating.");
            return;
        }

        try (Connection conn = dbConnect.Connection()) {
            conn.setAutoCommit(false);

            // Update question
            String query = "UPDATE tbQuestion SET content=?, type=?, topic=?, answer=? WHERE questionID=?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, questionUpdate);
                ps.setString(2, levelUpdate);
                ps.setString(3, topicUpdate);
                ps.setBoolean(4, answerUpdate);
                ps.setString(5, idUpdate);

                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Successfully Updated Question");

                } else {
                    JOptionPane.showMessageDialog(this, "No question with ID " + idUpdate + " found to update.");
                }
            }

            // Update options if necessary
            if (levelUpdate.equals("2") || levelUpdate.equals("3")) {
                updateOptions(conn, idUpdate);
            }

            conn.commit(); // Commit transaction
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }//GEN-LAST:event_ButtonUpdateActionPerformed

    private void updateOptions(Connection conn, String questionId) throws SQLException {
        String[] optionContentUpdate = {TextOp12.getText(), TextOp13.getText(), TextOp14.getText(), TextOp15.getText()};
        boolean[] booleanAnswerUpdate = {Checkbox9.isSelected(), Checkbox10.isSelected(), Checkbox11.isSelected(), Checkbox12.isSelected()};
        ArrayList<Option> options = (ArrayList<Option>) dao.OptionDAO.get(Integer.parseInt(textsearchid.getText()));
        int[] optionid = {options.get(0).optionID, options.get(1).optionID, options.get(2).optionID, options.get(3).optionID};
        boolean allFieldsFilled = true;

        for (String content : optionContentUpdate) {
            if (content.isEmpty()) {
                allFieldsFilled = false;
                break;
            }
        }

        boolean atLeastOneSelected = false;
        for (boolean answer : booleanAnswerUpdate) {
            if (answer) {
                atLeastOneSelected = true;
                break;
            }
        }
        if (!allFieldsFilled) {
            JOptionPane.showMessageDialog(null, "Please fill in all option fields.");
        } else if (!atLeastOneSelected) {
            JOptionPane.showMessageDialog(null, "Please select at least one correct answer.");
        } else {
            Connection con = dbConnect.Connection();
            try {

                con.setAutoCommit(false);

                PreparedStatement ps = con.prepareStatement("UPDATE tbOption SET content=?, answer=? WHERE questionID=? and optionID=?");

                for (int i = 0; i < optionContentUpdate.length; i++) {

                    System.out.println("opt content: " + optionContentUpdate[i]);
                    System.out.println("ans : " + booleanAnswerUpdate[i]);

                    ps.setString(1, optionContentUpdate[i]);
                    ps.setBoolean(2, booleanAnswerUpdate[i]);
                    ps.setString(3, questionId);
                    ps.setInt(4, optionid[i]);

                    ps.addBatch();

                }
                int[] affectedRows = ps.executeBatch();

                con.commit();
                con.close();

                int i = 0;
                for (int affectedRow : affectedRows) {

                    if (affectedRow > 0) {
                        System.out.println("Option [" + (i + 1) + "] inserted successfully!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error: Insertion failed for option ");
                    }
                    i++;
                }
                JOptionPane.showMessageDialog(null, "Options inserted successfully!!");
                clearFields();

            } catch (SQLException | NumberFormatException e) {
                con.rollback();
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

    private void clearFields() {
        textsearchid.setText("");
        QuestionContentTextF.setText("");
        LevelUpdate.setText("");
        TopicComboboxUpdate.setSelectedIndex(0);
        jCheckBox1.setSelected(true);
        TextOp12.setText("");
        TextOp13.setText("");
        TextOp14.setText("");
        TextOp15.setText("");
        Checkbox9.setSelected(false);
        Checkbox10.setSelected(false);
        Checkbox11.setSelected(false);
        Checkbox12.setSelected(false);
    }

    private void QuestionContentTextFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuestionContentTextFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_QuestionContentTextFActionPerformed


    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        String id = textsearchid.getText();
        try {
            Connection cn = dbConnect.Connection();
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM tbQuestion where questionID='" + id + "' ");

            if (rs.next()) {
                QuestionContentTextF.setText(rs.getString(2));
                LevelUpdate.setText(rs.getString(3));
                TopicComboboxUpdate.setSelectedItem(rs.getString("topic"));
                boolean isChecked = rs.getBoolean("answer");
                jCheckBox1.setSelected(isChecked);
                String level = LevelUpdate.getText();
                if (level.equals("1")) {
                    TextOp12.setText("");
                    TextOp13.setText("");
                    TextOp14.setText("");
                    TextOp15.setText("");

                    Checkbox9.setSelected(false);
                    Checkbox10.setSelected(false);
                    Checkbox11.setSelected(false);
                    Checkbox12.setSelected(false);

                    TextOp12.setEditable(false);
                    TextOp13.setEditable(false);
                    TextOp14.setEditable(false);
                    TextOp15.setEditable(false);

                    Checkbox9.setEnabled(false);
                    Checkbox10.setEnabled(false);
                    Checkbox11.setEnabled(false);
                    Checkbox12.setEnabled(false);

                } else if (level.equals("2") || level.equals("3")) {
                    TextOp12.setEditable(true);
                    TextOp13.setEditable(true);
                    TextOp14.setEditable(true);
                    TextOp15.setEditable(true);

                    Checkbox9.setEnabled(true);
                    Checkbox10.setEnabled(true);
                    Checkbox11.setEnabled(true);
                    Checkbox12.setEnabled(true);
                    int Idup = Integer.parseInt(id);

                    ArrayList<Option> options = (ArrayList<Option>) dao.OptionDAO.get(Idup);
                    // Ensure ops list is not null and has at least 4 elements before accessing
                    if (options != null && options.size() >= 4) {
                        TextOp12.setText(options.get(0).content);
                        TextOp13.setText(options.get(1).content);
                        TextOp14.setText(options.get(2).content);
                        TextOp15.setText(options.get(3).content);

                        Checkbox9.setSelected(options.get(0).answer);
                        Checkbox10.setSelected(options.get(1).answer);
                        Checkbox11.setSelected(options.get(2).answer);
                        Checkbox12.setSelected(options.get(3).answer);

                    } else {
                        // Handle the case where the ops list doesn't have enough elements
                        System.err.println("Not enough options for the question.");

                    }

                }
            } else {
                JFrame jf = new JFrame();
                jf.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(jf, "Question ID does not exist");
            }
        } catch (Exception e) {
            JFrame jf = new JFrame();
            jf.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(jf, "LOI: " + e.getMessage());
        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void textsearchidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textsearchidActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_textsearchidActionPerformed

    private void TopicComboboxUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopicComboboxUpdateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TopicComboboxUpdateActionPerformed

    private void FilterComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FilterComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FilterComboboxActionPerformed

    private void TextOp15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextOp15ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextOp15ActionPerformed

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(indexAdminm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(indexAdminm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(indexAdminm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(indexAdminm.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new indexAdminm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonUpdate;
    private javax.swing.JCheckBox Checkbox1;
    private javax.swing.JCheckBox Checkbox10;
    private javax.swing.JCheckBox Checkbox11;
    private javax.swing.JCheckBox Checkbox12;
    private javax.swing.JCheckBox Checkbox2;
    private javax.swing.JCheckBox Checkbox3;
    private javax.swing.JCheckBox Checkbox4;
    private javax.swing.JCheckBox Checkbox5;
    private javax.swing.JCheckBox Checkbox6;
    private javax.swing.JCheckBox Checkbox7;
    private javax.swing.JCheckBox Checkbox8;
    private javax.swing.JCheckBox Checkbox9;
    private javax.swing.JButton DeleteQuestionBtn;
    private javax.swing.JButton DeleteResultBNT;
    private javax.swing.JComboBox<String> FilterCombobox;
    private javax.swing.JComboBox<String> FilterResultCombobox;
    private javax.swing.JTextField FilterResultTextF;
    private javax.swing.JTextField FilterTextF;
    private javax.swing.JLabel IdQuestionTextF;
    private javax.swing.JPanel Jp10;
    private javax.swing.JComboBox<String> LevelComboBox;
    private javax.swing.JLabel LevelUpdate;
    private javax.swing.JButton OptionBtn;
    private javax.swing.JTextField QuestionContentTextF;
    private javax.swing.JTable QuestionTable;
    private javax.swing.JTextField QuestionTextF;
    private javax.swing.JTable ResultTable;
    private javax.swing.JTable TablelUser;
    private javax.swing.JTextField TextOp1;
    private javax.swing.JTextField TextOp12;
    private javax.swing.JTextField TextOp13;
    private javax.swing.JTextField TextOp14;
    private javax.swing.JTextField TextOp15;
    private javax.swing.JTextField TextOp2;
    private javax.swing.JTextField TextOp3;
    private javax.swing.JTextField TextOp4;
    private javax.swing.JTextField TextOp5;
    private javax.swing.JTextField TextOp6;
    private javax.swing.JTextField TextOp7;
    private javax.swing.JTextField TextOp8;
    private javax.swing.JComboBox<String> TopicComboBox;
    private javax.swing.JComboBox<String> TopicComboboxUpdate;
    private javax.swing.JButton addbtn;
    private javax.swing.JCheckBox anserCheck;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox1;
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
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jP1;
    private javax.swing.JPanel jP2;
    private javax.swing.JPanel jP3;
    private javax.swing.JPanel jP4;
    private javax.swing.JPanel jP5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JButton removebtn;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JPanel tab5;
    private javax.swing.JPanel tab6;
    private javax.swing.JTextField textsearch;
    private javax.swing.JTextField textsearchid;
    private javax.swing.JButton updatebtn;
    // End of variables declaration//GEN-END:variables
}
