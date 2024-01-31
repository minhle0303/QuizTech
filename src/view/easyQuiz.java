/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.Question;
import dao.Result;
import dao.ResultDAO;
import dao.User;
import dao.dbConnect;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author minh
 */
public class easyQuiz extends javax.swing.JFrame {

    private int userid;
    int index = 0;
    int indexQ = 1;
    int mark = 0;
    private String topic = "";
    public Date today = new Date();
    private int secondsRemaining;
    private int elapsedTimeInSeconds;
    private int elapsedMins;
    private int elapsedSecs;
    private int count;
    private int resultId;
    private java.util.Timer t;
    ArrayList<Question> ds = new ArrayList<>();
    ArrayList<User> dsUser = new ArrayList<>();

    public easyQuiz() {
        initComponents();

        initQuestion();
        answerCheck();
        DateLabel();
        initTimer();

    }

    easyQuiz(String topic, int userid) {
        initComponents();
        this.topic = topic;
        this.userid = userid;
        initTimer();
        initQuestion();
        answerCheck();
        DateLabel();
        inforUser();

    }

    private void initQuestion() {

        ds = (ArrayList<Question>) dao.QuestionDAO.getz(1, topic);
        renderQuestion(index);
    }

    private void renderQuestion(int index) {

        LabelIndex.setText(String.valueOf(indexQ));
        jLabel6.setText(String.valueOf(indexQ));
        LabelQuestion.setText(ds.get(index).content);

    }

    public void DateLabel() {
        Date today = new Date();

        // Set the locale to Vietnam
        Locale localeVietnam = new Locale("vi", "VN");

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localeVietnam);
        String strDate = df.format(today);
        DateLabel.setText(strDate);
    }

    private void inforUser() {
        dsUser = dao.UserDAO.getByID(userid);
        if (!dsUser.isEmpty()) {
            LabelID.setText(String.valueOf(dsUser.get(0).userID));
            LabelName.setText(dsUser.get(0).name);
            labelTopic.setText(this.topic);
        } else {
            // Handle the case where no user information is found for the given userid
            // For example, display a message or set default values for labels
        }
    }

    private void answerCheck() {
        Boolean userAnswer = null; // Initialize as null

// Determine user's answer
        if (TrueRadio.isSelected()) {
            userAnswer = true;
        } else if (FalseRadio.isSelected()) {
            userAnswer = false;
        }

// Handling both options selected
        if (userAnswer != null) {
            boolean correctAnswer = ds.get(index).answer;

            if (userAnswer == correctAnswer) {
                mark++; // Increment mark if answer is correct
                JOptionPane.showMessageDialog(this, "Correct!");
            } else {
                // No need for mark = mark + 0, you can omit this
                JOptionPane.showMessageDialog(this, "Incorrect.");
            }

        }
        buttonGroup2.clearSelection();
    }

    private void initTimer() {
        secondsRemaining = 300; // 5 minutes

       t = new java.util.Timer();

        TimerTask task = new TimerTask() {

            @Override
            public void run() {

                if (secondsRemaining > 0) {
                    secondsRemaining--;
                    elapsedTimeInSeconds++; //thoi gian choi 
                    updateTimerLabel();

                } else {
                    t.cancel();
                    JOptionPane.showMessageDialog(null, "Time's up! Quiz has ended.");
                    endSubmit();

                }

            }
        };
        t.schedule(task, 200, 1000);

    }

    private void updateTimerLabel() {
        int mins = secondsRemaining / 60;
        int secs = secondsRemaining % 60;
        TimeLabel.setText(String.format("%02d:%02d", mins, secs));

        elapsedMins = elapsedTimeInSeconds / 60;
        elapsedSecs = elapsedTimeInSeconds % 60;

    }

    public void submit() {
        updateTimerLabel();
        int userid = Integer.parseInt(LabelID.getText());
        int score = mark;
        //Time
        String duration = String.format("%02d:%02d", elapsedMins, elapsedSecs);
        //Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var sDate = dateFormat.format(today);
        int level = 1;
        String topic1 = this.topic;

        try (Connection con = dbConnect.Connection()) {
            String sql = "INSERT INTO tbResult(userID, score, duration, date, level, topic) VALUES (?, ?, ?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setInt(1, userid);
                preparedStatement.setInt(2, score);
                preparedStatement.setString(3, duration);
                preparedStatement.setString(4, sDate);
                preparedStatement.setInt(5, level);
                preparedStatement.setString(6, topic1);

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (var generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            resultId = generatedKeys.getInt(1);

                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LabelID = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        ButtonNext = new javax.swing.JButton();
        ButtonSubmit = new javax.swing.JButton();
        labelTopic = new javax.swing.JLabel();
        LabelQuestion = new javax.swing.JLabel();
        LabelIndex = new javax.swing.JLabel();
        LabelIndex1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        markLabel = new javax.swing.JLabel();
        TrueRadio = new javax.swing.JRadioButton();
        FalseRadio = new javax.swing.JRadioButton();
        LabelMark1 = new javax.swing.JLabel();
        LabelMark2 = new javax.swing.JLabel();
        LabelQuestion2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(75, 75));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("NAME:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 100, -1, -1));

        jLabel2.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Question:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, -1, -1));

        LabelID.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelID.setForeground(new java.awt.Color(0, 0, 0));
        LabelID.setText("1");
        jPanel1.add(LabelID, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        LabelName.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelName.setForeground(new java.awt.Color(0, 0, 0));
        LabelName.setText("Minh Le");
        jPanel1.add(LabelName, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, -1, -1));

        jLabel6.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("10");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, -1, -1));

        jLabel7.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("10");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 150, -1, -1));

        jLabel8.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("/");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 150, -1, -1));

        DateLabel.setBackground(new java.awt.Color(255, 204, 153));
        DateLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(0, 0, 0));
        DateLabel.setText("23/23/1000");
        jPanel1.add(DateLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 42, -1, -1));

        jLabel9.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DATE:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 42, -1, -1));

        TimeLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        TimeLabel.setForeground(new java.awt.Color(255, 51, 51));
        TimeLabel.setText("DURATION:");
        jPanel1.add(TimeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 96, 110, 20));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 1020, 10));

        ButtonNext.setBackground(new java.awt.Color(204, 204, 204));
        ButtonNext.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        ButtonNext.setForeground(new java.awt.Color(0, 0, 0));
        ButtonNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Next.png"))); // NOI18N
        ButtonNext.setText("Next");
        ButtonNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonNextActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 580, 120, 50));

        ButtonSubmit.setBackground(new java.awt.Color(204, 204, 204));
        ButtonSubmit.setFont(new java.awt.Font("PT Sans Caption", 1, 14)); // NOI18N
        ButtonSubmit.setForeground(new java.awt.Color(0, 0, 0));
        ButtonSubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/save.png"))); // NOI18N
        ButtonSubmit.setText("Submit");
        ButtonSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ButtonSubmitMouseClicked(evt);
            }
        });
        ButtonSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonSubmitActionPerformed(evt);
            }
        });
        jPanel1.add(ButtonSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 580, 120, 50));

        labelTopic.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        labelTopic.setForeground(new java.awt.Color(0, 0, 0));
        labelTopic.setText("Easy");
        jPanel1.add(labelTopic, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 140, -1, -1));

        LabelQuestion.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        LabelQuestion.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion.setText("121");
        jPanel1.add(LabelQuestion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 990, 30));

        LabelIndex.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        LabelIndex.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex.setText("1");
        jPanel1.add(LabelIndex, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 300, -1, -1));

        LabelIndex1.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelIndex1.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex1.setText(":");
        jPanel1.add(LabelIndex1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 300, -1, -1));

        jLabel14.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("DURATION:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(718, 92, -1, -1));

        markLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        markLabel.setForeground(new java.awt.Color(0, 0, 0));
        markLabel.setText("Easy");
        jPanel1.add(markLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 190, -1, -1));

        TrueRadio.setBackground(new java.awt.Color(255, 204, 153));
        buttonGroup2.add(TrueRadio);
        TrueRadio.setFont(new java.awt.Font("PT Sans Caption", 1, 22)); // NOI18N
        TrueRadio.setForeground(new java.awt.Color(0, 0, 0));
        TrueRadio.setText("True");
        jPanel1.add(TrueRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 460, -1, -1));

        FalseRadio.setBackground(new java.awt.Color(255, 204, 153));
        buttonGroup2.add(FalseRadio);
        FalseRadio.setFont(new java.awt.Font("PT Sans Caption", 1, 22)); // NOI18N
        FalseRadio.setForeground(new java.awt.Color(0, 0, 0));
        FalseRadio.setText("False");
        jPanel1.add(FalseRadio, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, -1, -1));

        LabelMark1.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelMark1.setForeground(new java.awt.Color(0, 0, 0));
        LabelMark1.setText("TOPIC: ");
        jPanel1.add(LabelMark1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, -1, -1));

        LabelMark2.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelMark2.setForeground(new java.awt.Color(0, 0, 0));
        LabelMark2.setText("LEVEL:");
        jPanel1.add(LabelMark2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, -1, -1));

        LabelQuestion2.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion2.setFont(new java.awt.Font("PT Sans Caption", 1, 18)); // NOI18N
        LabelQuestion2.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion2.setText("Question ");
        jPanel1.add(LabelQuestion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void endSubmit() {

        submit();
        Result result = ResultDAO.getByID(resultId);

        new ViewResult(result).setVisible(true);
        this.dispose();

    }
    private void ButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNextActionPerformed
        // TODO add your handling code here:

        if (buttonGroup2.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please get any choice");
            mark += 0;
        } else {
            answerCheck();

            if (10 > (index + 1)) {
                index++;
                indexQ++;

                renderQuestion(index);

                if (index == 10) {
                    ButtonNext.setVisible(false);
                }

            } else {
                ButtonNext.setVisible(false);
            }
        }

    }//GEN-LAST:event_ButtonNextActionPerformed

    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Submit?");
        if (a == 0) {
            submit();
            Result result = ResultDAO.getByID(resultId);
            t.cancel();

            new ViewResult(result).setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void ButtonSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSubmitMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_ButtonSubmitMouseClicked

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
            java.util.logging.Logger.getLogger(easyQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(easyQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(easyQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(easyQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new easyQuiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonNext;
    private javax.swing.JButton ButtonSubmit;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JRadioButton FalseRadio;
    private javax.swing.JLabel LabelID;
    private javax.swing.JLabel LabelIndex;
    private javax.swing.JLabel LabelIndex1;
    private javax.swing.JLabel LabelMark1;
    private javax.swing.JLabel LabelMark2;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelQuestion;
    private javax.swing.JLabel LabelQuestion2;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JRadioButton TrueRadio;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelTopic;
    private javax.swing.JLabel markLabel;
    // End of variables declaration//GEN-END:variables

}
