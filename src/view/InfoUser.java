/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.Question;
import dao.User;
import dao.dbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class InfoUser extends javax.swing.JFrame {

    private User user;
    private Object com;
    private int userid;

    ArrayList<Question> dsQuestion = new ArrayList<>();
    int index = 0;
    ArrayList<User> ds = new ArrayList<>();

    public InfoUser(User user) {
        initComponents();

        this.user = user;
        infouser();
        jTextArea1.setEditable(false);
    }

    public InfoUser(int id) {

        initComponents();
        this.userid = id;
        infouser();
        jTextArea1.setEditable(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GenderGroup = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        txtMail = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnChangePass = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        btnFemale = new javax.swing.JRadioButton();
        btnMale = new javax.swing.JRadioButton();
        txtID = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtBod = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        TopicCombobox = new javax.swing.JComboBox<>();
        LevelCombobox = new javax.swing.JComboBox<>();
        StartBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(75, 75));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 204, 153));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Name  :");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, 90, 40));

        jLabel3.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Phone  :");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 100, 30));

        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Gender:");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 110, 40));

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Bod    :");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, 40));

        jLabel6.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("Email  :");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, 90, -1));

        txtPhone.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 190, 30));

        txtMail.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 190, 30));

        btnUpdate.setBackground(new java.awt.Color(204, 204, 204));
        btnUpdate.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnUpdate.setForeground(new java.awt.Color(0, 0, 0));
        btnUpdate.setText("UPDATE ");
        btnUpdate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnUpdateMouseClicked(evt);
            }
        });
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 480, 110, 60));

        btnChangePass.setBackground(new java.awt.Color(204, 204, 204));
        btnChangePass.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        btnChangePass.setForeground(new java.awt.Color(0, 0, 0));
        btnChangePass.setText("Change Password");
        btnChangePass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePassActionPerformed(evt);
            }
        });
        jPanel2.add(btnChangePass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 480, -1, 60));

        jLabel7.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("userID :");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, -1, -1));

        txtName.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtName, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 150, 190, 30));

        btnFemale.setBackground(new java.awt.Color(255, 204, 153));
        GenderGroup.add(btnFemale);
        btnFemale.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        btnFemale.setForeground(new java.awt.Color(0, 0, 0));
        btnFemale.setText("Female");
        jPanel2.add(btnFemale, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 270, -1, -1));

        btnMale.setBackground(new java.awt.Color(255, 204, 153));
        GenderGroup.add(btnMale);
        btnMale.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        btnMale.setForeground(new java.awt.Color(0, 0, 0));
        btnMale.setText("Male");
        jPanel2.add(btnMale, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, -1, -1));

        txtID.setFont(new java.awt.Font("PT Sans Caption", 1, 22)); // NOI18N
        txtID.setForeground(new java.awt.Color(0, 0, 0));
        txtID.setText("jLabel8");
        jPanel2.add(txtID, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 80, 20));

        jLabel8.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("INFOMATION USER");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 268, 64));

        txtBod.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.add(txtBod, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 320, 190, 30));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 460, 570));

        jPanel3.setBackground(new java.awt.Color(255, 204, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        TopicCombobox.setBackground(new java.awt.Color(204, 204, 204));
        TopicCombobox.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        TopicCombobox.setForeground(new java.awt.Color(0, 0, 0));
        TopicCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Topic", "AJS", "HTML", "Java", "CSS", "PHP ", "SQL", " " }));
        TopicCombobox.setName(""); // NOI18N
        TopicCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TopicComboboxActionPerformed(evt);
            }
        });
        jPanel3.add(TopicCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(64, 480, 110, 80));

        LevelCombobox.setBackground(new java.awt.Color(204, 204, 204));
        LevelCombobox.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        LevelCombobox.setForeground(new java.awt.Color(0, 0, 0));
        LevelCombobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Level :", "1", "2", "3" }));
        LevelCombobox.setName(""); // NOI18N
        LevelCombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LevelComboboxActionPerformed(evt);
            }
        });
        jPanel3.add(LevelCombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(224, 480, 120, 80));

        StartBtn.setBackground(new java.awt.Color(204, 204, 204));
        StartBtn.setFont(new java.awt.Font("Constantia", 1, 18)); // NOI18N
        StartBtn.setForeground(new java.awt.Color(0, 0, 0));
        StartBtn.setText("START");
        StartBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StartBtnActionPerformed(evt);
            }
        });
        jPanel3.add(StartBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 480, 110, 80));

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("PT Sans Caption", 0, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(0, 0, 0));
        jTextArea1.setRows(5);
        jTextArea1.setText("1.Objective:\n- The goal of a quiz game is to answer the questions correctly and the main goal \nof a quiz game is to entertain and test knowledge.\n2. Levels and Time:\n- Level 1 (5 minutes): Players have 5 minutes to answer true/false questions.\n- Level 2 (5 minutes): Players have 5 minutes to select one answer for each question.\n- Level 3 (7 minutes): Players have 7 minutes to select multiple answers for each\n question.\n3. Gameplay:\n- Each level will present questions according to its respective format.\n- Players must answer true/false questions in Level 1 and select single or multiple \nanswers in Levels 2 and 3.\n- After selecting an answer, players can press the \"Next\" button to move to the next\n question.\n- Each time the \"Next\" button is pressed, the player earns 1 point.\n4. Time Limit:\n- A time limit is set for each level as described above.\n- If the time runs out for any level, the quiz will automatically submit the player's \nanswers.\n5. Scoring:\t\n- Players earn 1 point each time they press the \"Next\" button.\n- Points are accumulated as the player progresses through the questions.\n6. Acknowledgment:\n\nBy participating in the quiz game, players acknowledge and agree to abide by the rules and decisions of the quiz organizer.\n\n\n");
        jTextArea1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 530, 330));

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("INSTRUCTOR GAME PLAY");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 310, 64));

        jButton1.setText("Show more");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 430, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 80, 610, 570));

        jButton2.setBackground(new java.awt.Color(204, 204, 204));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Logout.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 0, 80, 80));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/index background.png"))); // NOI18N
        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LevelComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LevelComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LevelComboboxActionPerformed

    private void TopicComboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TopicComboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TopicComboboxActionPerformed

    private void StartBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StartBtnActionPerformed
        String topic = (String) TopicCombobox.getSelectedItem();
        String levelString = LevelCombobox.getSelectedItem() != null ? LevelCombobox.getSelectedItem().toString() : "";
        int level = 0;
        if (topic == null || topic.equals("Topic")) {
            JOptionPane.showMessageDialog(this, "Please select a topic to play the game!");
            return;
        }

        try {
            level = Integer.parseInt(levelString);
        } catch (NumberFormatException e) {
            // Handle parsing error (e.g., levelString is not a valid integer)
            JOptionPane.showMessageDialog(this, "Pls select level  to play game!");
            return;
        }

        if (level == 1) {
            this.dispose();
            new easyQuiz(topic, user.userID).setVisible(true);
        } else if (level == 2) {
            this.dispose();

            new mediumQuiz(topic, user.userID).setVisible(true);
        } else {
            this.dispose();

            new hardQuiz(topic, user.userID).setVisible(true);
        }


    }//GEN-LAST:event_StartBtnActionPerformed

    private void btnChangePassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePassActionPerformed
        // TODO add your handling code here:

        new UserChangePass(user.userID).setVisible(true);
    }//GEN-LAST:event_btnChangePassActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        // TODO add your handling code here:
        //        setVisible(false);
        //        new UserUpdate().setVisible(true);

        ////----------------------------
        String name = txtName.getText();
        String phone = txtPhone.getText();
        String gender = btnMale.isSelected() ? "Male" : "Female";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String bod = dateFormat.format(txtBod.getDate());
        String email = txtMail.getText();
        String userID = txtID.getText();

        Connection cn = dbConnect.Connection();
        PreparedStatement ps = null;
        ///    sua doan code try o duoi
        try {
            String sql = "UPDATE tbUser SET name=?, phone=?, gender=?,bod=?, email=? WHERE userID=?";
            ps = cn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, gender);
            ps.setString(4, bod);
            ps.setString(5, email);
            ps.setString(6, userID);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Record updated successfully");
            } else {
                JOptionPane.showMessageDialog(this, "No records updated. User with userID " + userID + " not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (cn != null) {
                    cn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnUpdateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnUpdateMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUpdateMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        new Instructor().setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Do you want really log out?", "Select", JOptionPane.YES_NO_OPTION);

        if (a == 0) {
            this.dispose();
            new UserLogin().setVisible(true);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void infouser() {

        txtID.setText(String.valueOf(user.userID)); // Convert int to String
        txtName.setText(user.name);
        txtPhone.setText(user.phone);
//GenderGroup.isSelected(user.gender);
        if ("Male".equals(user.gender)) {
            GenderGroup.setSelected(btnMale.getModel(), true);
            btnFemale.setSelected(false);
        } else {
            GenderGroup.setSelected(btnMale.getModel(), false);
            btnFemale.setSelected(true);
        }

        String dateString = user.bod; // Giả sử user.bod là một chuỗi có định dạng "yyyy-MM-dd"
        if (dateString != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = dateFormat.parse(dateString);
                txtBod.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace(); // Xử lý ngoại lệ phân tích cú pháp một cách thích hợp
            }
        }
        txtMail.setText(user.email);

    }/////////////////////////////////////////////////////////////////

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
            java.util.logging.Logger.getLogger(InfoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InfoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InfoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InfoUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = new User();
                new InfoUser(user).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GenderGroup;
    private javax.swing.JComboBox<String> LevelCombobox;
    private javax.swing.JButton StartBtn;
    private javax.swing.JComboBox<String> TopicCombobox;
    private javax.swing.JButton btnChangePass;
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private com.toedter.calendar.JDateChooser txtBod;
    private javax.swing.JLabel txtID;
    private javax.swing.JTextField txtMail;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtPhone;
    // End of variables declaration//GEN-END:variables
}
