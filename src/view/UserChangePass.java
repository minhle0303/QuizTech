package view;

import dao.User;
import dao.dbConnect;
import static dao.dbConnect.user;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class UserChangePass extends javax.swing.JFrame {

    /**
     * Creates new form UserChangePass
     */
    private int userid;
    private int index;
    ArrayList<User> ds = new ArrayList<>();

    public UserChangePass() {

        initComponents();

    }

    UserChangePass(int userID) {
        initComponents();
        this.userid = userID;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        txtOLDpass = new javax.swing.JPasswordField();
        txtNEWpass = new javax.swing.JPasswordField();
        txtCONpass = new javax.swing.JPasswordField();
        showcon = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 75));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Old Password :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, 140, -1));

        jLabel4.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("New Password :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, -1, -1));

        jLabel5.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Confirm Password :");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 102, 255));
        jLabel1.setText("New Password");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 250, 40));

        btnBack.setBackground(new java.awt.Color(153, 255, 255));
        btnBack.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        btnBack.setForeground(new java.awt.Color(0, 0, 0));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        getContentPane().add(btnBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 20, -1, -1));

        btnSave.setBackground(new java.awt.Color(204, 204, 255));
        btnSave.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(0, 0, 0));
        btnSave.setText("SAVE");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        getContentPane().add(btnSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, -1, -1));

        txtOLDpass.setBackground(new java.awt.Color(255, 255, 255));
        txtOLDpass.setForeground(new java.awt.Color(0, 0, 0));
        txtOLDpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOLDpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtOLDpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 163, 30));

        txtNEWpass.setBackground(new java.awt.Color(255, 255, 255));
        txtNEWpass.setForeground(new java.awt.Color(0, 0, 0));
        txtNEWpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNEWpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtNEWpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 250, 163, 30));

        txtCONpass.setBackground(new java.awt.Color(255, 255, 255));
        txtCONpass.setForeground(new java.awt.Color(0, 0, 0));
        txtCONpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCONpassActionPerformed(evt);
            }
        });
        getContentPane().add(txtCONpass, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 330, 163, 30));

        showcon.setBackground(new java.awt.Color(204, 204, 204));
        showcon.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        showcon.setForeground(new java.awt.Color(0, 0, 0));
        showcon.setText("show password");
        showcon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showconActionPerformed(evt);
            }
        });
        getContentPane().add(showcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, 130, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/fofm.jpg"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 560));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed

        String oldpass, newpass, cnfpass;

        oldpass = String.valueOf(txtOLDpass.getPassword());
        newpass = String.valueOf(txtNEWpass.getPassword());
        cnfpass = String.valueOf(txtCONpass.getPassword());

        // Check if new password matches confirm password
        if (oldpass.equals(newpass)) {
            JOptionPane.showMessageDialog(this, "the old password can not match the new password.");
            return; // Exit the method if passwords don't match
        } else if (!newpass.equals(cnfpass)) {
            JOptionPane.showMessageDialog(this, "New password does not match the confirm password.");
            return; // Exit the method if passwords don't match
        } else {

            // Kiểm tra xem mật khẩu cũ có khớp với mật khẩu hiện tại không
            String checkOldPassQuery = "SELECT * FROM tbUser WHERE PASSWORD=?";
            try (Connection conn = dbConnect.Connection(); PreparedStatement checkOldPassStmt = conn.prepareStatement(checkOldPassQuery)) {

                checkOldPassStmt.setString(1, oldpass);

                ResultSet rs = checkOldPassStmt.executeQuery();

                if (rs.next()) {
                    // Mật khẩu cũ khớp, tiến hành cập nhật mật khẩu mới
                    String updatePassQuery = "UPDATE tbUser SET PASSWORD=? WHERE userID=?";
                    try (PreparedStatement updatePassStmt = conn.prepareStatement(updatePassQuery)) {

                        updatePassStmt.setString(1, newpass);
                        updatePassStmt.setInt(2, userid); // Assuming userid is defined somewhere

                        int rowsAffected = updatePassStmt.executeUpdate();

                        if (rowsAffected > 0) {
                            // Cập nhật thành công
                            JOptionPane.showMessageDialog(this, "Password changed successfully!");
                            // You may also want to clear the password fields after successful change
                            txtOLDpass.setText("");
                            txtNEWpass.setText("");
                            txtCONpass.setText("");
                            this.dispose();
                        } else {
                            // Cập nhật thất bại
                            JOptionPane.showMessageDialog(this, "Failed to change password. Please try again.");
                        }
                    }
                } else {
                    // Mật khẩu cũ không khớp
                    JOptionPane.showMessageDialog(this, "Incorrect old password. Please try again.");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Database error occurred. Please try again later.");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "An error occurred. Please try again later.");
            }
        }
    }//GEN-LAST:event_btnSaveActionPerformed

    private void txtOLDpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOLDpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOLDpassActionPerformed

    private void txtNEWpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNEWpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNEWpassActionPerformed

    private void txtCONpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCONpassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCONpassActionPerformed

    private void showconActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showconActionPerformed
        // TODO add your handling code here:
        if (showcon.isSelected()) {
            txtCONpass.setEchoChar((char) 0);
            txtNEWpass.setEchoChar((char) 0);
            txtOLDpass.setEchoChar((char) 0);
        } else {
            txtCONpass.setEchoChar('*');
            txtNEWpass.setEchoChar('*');
            txtOLDpass.setEchoChar('*');
        }
    }//GEN-LAST:event_showconActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        ds = dao.UserDAO.getByID(userid);
        System.out.println("" + ds);
        this.dispose();


    }//GEN-LAST:event_btnBackActionPerformed

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
            java.util.logging.Logger.getLogger(UserChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserChangePass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                User user = new User();
                new UserChangePass().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JCheckBox showcon;
    private javax.swing.JPasswordField txtCONpass;
    private javax.swing.JPasswordField txtNEWpass;
    private javax.swing.JPasswordField txtOLDpass;
    // End of variables declaration//GEN-END:variables
}
