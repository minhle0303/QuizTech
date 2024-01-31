/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import dao.Option;
import dao.Question;
import dao.User;
import dao.Result;
import dao.ResultDAO;
import dao.dbConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 *
 * @author minh
 */
public class hardQuiz extends javax.swing.JFrame {

    /**
     * Creates new form hardQuiz
     */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public int count;
    public String answer;
    public int marks = 0;
    public java.util.Timer timer;
    public Date today = new Date();
    private int userid;
    private int resultId;
    private String topic = "";
    private java.util.Timer t;
    int index = 0;
    int indexQ = 1;
    private int secondsRemaining;
    private int elapsedTimeInSeconds;
    private int elapsedMins;
    private int elapsedSecs;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Option> ops = new ArrayList<>();
    ArrayList<User> dsUser = new ArrayList<>();
    ArrayList<Result> dsrs = new ArrayList<>();

    public hardQuiz() {
        initComponents();
        initQuestions();

        DateLabel();
        inforUser();
        initTimer();
    }

    hardQuiz(String topic, int userid) {

        this.topic = topic;
        initComponents();
        initQuestions();
        DateLabel();

        this.userid = userid;
        inforUser();
        initTimer();

    }

    private void initQuestions() {

        questions = (ArrayList<Question>) dao.QuestionDAO.getz(3, topic);
        String sIDs = "";

        for (Question d : questions) {
            sIDs += (d.questionID + ",");
        }
        sIDs = sIDs.substring(0, sIDs.length() - 1);
        ops = dao.QuestionDAO.getOptions(sIDs);

        renderQuestion(index);
        renderAnswer(index);

    }

    private void inforUser() {
        dsUser = dao.UserDAO.getByID(userid);
        LabelID.setText(String.valueOf(dsUser.get(index).userID));
        LabelName.setText(dsUser.get(index).name);
        labelTopic.setText(this.topic);

    }

    private void DateLabel() {

        // Set the locale to Vietnam
        Locale localeVietnam = new Locale("vi", "VN");

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localeVietnam);
        String strDate = df.format(today);
        DateLabel.setText(strDate);
    }

    private void renderQuestion(int index) {

        LabelIndex.setText(String.valueOf(indexQ));
        jLabel6.setText(String.valueOf(indexQ));
        LabelQuestion.setText(questions.get(index).content);

    }

    private void renderAnswer(int index) {
        String[] answer = new String[4];
        int qID = questions.get(index).questionID;
        var op = ops.stream().filter(x -> x.questionID == qID).toList();

        jCheckBox1.setText(op.get(0).content);
        jCheckBox2.setText(op.get(1).content);
        jCheckBox3.setText(op.get(2).content);
        jCheckBox4.setText(op.get(3).content);

    }

    private void initTimer() {
        secondsRemaining = 420; 

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

    private void checkAnswer() {
        int qID = questions.get(index).questionID;
        var correctOptions = ops.stream()
                .filter(x -> x.questionID == qID && x.answer)
                .map(x -> x.content)
                .collect(Collectors.toList());

        List<String> selectedOptions = new ArrayList<>();
        if (jCheckBox1.isSelected()) {
            selectedOptions.add(jCheckBox1.getText());
        }
        if (jCheckBox2.isSelected()) {
            selectedOptions.add(jCheckBox2.getText());
        }
        if (jCheckBox3.isSelected()) {
            selectedOptions.add(jCheckBox3.getText());
        }
        if (jCheckBox4.isSelected()) {
            selectedOptions.add(jCheckBox4.getText());
        }

        if (selectedOptions.equals(correctOptions)) {
//            System.out.println("Correct!");
            JOptionPane.showMessageDialog(this, "Correct!");
            marks++;
        } else {
            JOptionPane.showMessageDialog(this, "InCorrect!");

            marks = marks + 0;
//            System.out.println("Incorrect! Correct answer(s) must be: " + selectedOptions);
        }

        jCheckBox1.setSelected(false);
        jCheckBox2.setSelected(false);
        jCheckBox3.setSelected(false);
        jCheckBox4.setSelected(false);
    }

    public void submit() {
        updateTimerLabel();
        int userid = Integer.parseInt(LabelID.getText());
        int score = marks;
        //Time
        String duration = String.format("%02d:%02d", elapsedMins, elapsedSecs);
        //Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var sDate = dateFormat.format(today);
        int level = 3;
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

        jPanel1 = new javax.swing.JPanel();
        labelTopic = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        DateLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        TimeLabel = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        LabelID = new javax.swing.JLabel();
        LabelName = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        LabelIndex = new javax.swing.JLabel();
        LabelQuestion1 = new javax.swing.JLabel();
        LabelIndex1 = new javax.swing.JLabel();
        LabelQuestion = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jCheckBox2 = new javax.swing.JCheckBox();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        ButtonNext = new javax.swing.JButton();
        ButtonSubmit = new javax.swing.JButton();
        LableID1 = new javax.swing.JLabel();
        LableID2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(75, 75));

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        labelTopic.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        labelTopic.setForeground(new java.awt.Color(0, 0, 0));
        labelTopic.setText("..");

        jLabel14.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("DURATION:");

        jLabel5.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Hard");

        jLabel7.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("10");

        jLabel8.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("/");

        DateLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(0, 0, 0));
        DateLabel.setText("23/23/1000");

        jLabel9.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DATE:");

        TimeLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        TimeLabel.setForeground(new java.awt.Color(0, 0, 0));
        TimeLabel.setText("DURATION:");

        jLabel1.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("NAME:");

        jLabel2.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("ID:");

        jLabel3.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Question:");

        LabelID.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelID.setForeground(new java.awt.Color(0, 0, 0));
        LabelID.setText("1");

        LabelName.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LabelName.setForeground(new java.awt.Color(0, 0, 0));
        LabelName.setText("Minh Le");

        jLabel6.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("10");

        LabelIndex.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelIndex.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex.setText("1");

        LabelQuestion1.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelQuestion1.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion1.setText("Question ");

        LabelIndex1.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelIndex1.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex1.setText(":");

        LabelQuestion.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelQuestion.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion.setText("Content");

        jCheckBox1.setBackground(new java.awt.Color(255, 204, 153));
        jCheckBox1.setFont(new java.awt.Font("PT Sans Caption", 0, 16)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox1.setText("jCheckBox1");

        jCheckBox2.setBackground(new java.awt.Color(255, 204, 153));
        jCheckBox2.setFont(new java.awt.Font("PT Sans Caption", 0, 16)); // NOI18N
        jCheckBox2.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox2.setText("jCheckBox2");

        jCheckBox3.setBackground(new java.awt.Color(255, 204, 153));
        jCheckBox3.setFont(new java.awt.Font("PT Sans Caption", 0, 16)); // NOI18N
        jCheckBox3.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox3.setText("jCheckBox3");

        jCheckBox4.setBackground(new java.awt.Color(255, 204, 153));
        jCheckBox4.setFont(new java.awt.Font("PT Sans Caption", 0, 16)); // NOI18N
        jCheckBox4.setForeground(new java.awt.Color(0, 0, 0));
        jCheckBox4.setText("jCheckBox4");

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

        LableID1.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LableID1.setForeground(new java.awt.Color(0, 0, 0));
        LableID1.setText("TOPIC: ");

        LableID2.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LableID2.setForeground(new java.awt.Color(0, 0, 0));
        LableID2.setText("LEVEL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(LabelID)
                                    .addComponent(LabelName)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(12, 12, 12)
                                        .addComponent(jLabel8)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel14)
                                    .addComponent(LableID1)
                                    .addComponent(LableID2))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateLabel)
                                    .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTopic)
                                    .addComponent(jLabel5))
                                .addGap(102, 102, 102))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(513, 513, 513)
                                        .addComponent(ButtonSubmit))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(85, 85, 85)
                                                .addComponent(LabelIndex))
                                            .addComponent(LabelQuestion1))
                                        .addGap(8, 8, 8)
                                        .addComponent(LabelIndex1)
                                        .addGap(34, 34, 34)
                                        .addComponent(LabelQuestion))
                                    .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jCheckBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 849, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jCheckBox4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
                                            .addComponent(jCheckBox2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(135, 135, 135)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1019, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(126, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel1)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(LabelID)
                                .addGap(18, 18, 18)
                                .addComponent(LabelName)
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7))))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(LableID1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(DateLabel)
                                .addGap(24, 24, 24)
                                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addComponent(labelTopic)))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(LableID2))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelIndex)
                    .addComponent(LabelQuestion1)
                    .addComponent(LabelIndex1)
                    .addComponent(LabelQuestion))
                .addGap(52, 52, 52)
                .addComponent(jCheckBox1)
                .addGap(33, 33, 33)
                .addComponent(jCheckBox2)
                .addGap(34, 34, 34)
                .addComponent(jCheckBox3)
                .addGap(33, 33, 33)
                .addComponent(jCheckBox4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNextActionPerformed
        // TODO add your handling code here:

        if (!jCheckBox1.isSelected() && !jCheckBox2.isSelected() && !jCheckBox3.isSelected() && !jCheckBox4.isSelected()) {
            JOptionPane.showMessageDialog(null, "Please select at least one option");

        } else {
            checkAnswer();
            index++;
            indexQ++;
            if (index < 10) {
                renderQuestion(index);
                renderAnswer(index);

                if (index == 10) {
                    ButtonNext.setVisible(false);
                }

            } else {
                ButtonNext.setVisible(false);
            }
        }
    }//GEN-LAST:event_ButtonNextActionPerformed

    private void endSubmit() {

        submit();
        Result result = ResultDAO.getByID(resultId);

        new ViewResult(result).setVisible(true);
        this.dispose();

    }
    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed
        // TODO add your handling code here:
        int a = JOptionPane.showConfirmDialog(null, "Submit?");
        if (a == 0) {
            t.cancel();
            submit();
            Result result = ResultDAO.getByID(resultId);

            new ViewResult(result).setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void ButtonSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSubmitMouseClicked


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
            java.util.logging.Logger.getLogger(hardQuiz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hardQuiz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hardQuiz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hardQuiz.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new hardQuiz().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonNext;
    private javax.swing.JButton ButtonSubmit;
    private javax.swing.JLabel DateLabel;
    private javax.swing.JLabel LabelID;
    private javax.swing.JLabel LabelIndex;
    private javax.swing.JLabel LabelIndex1;
    private javax.swing.JLabel LabelName;
    private javax.swing.JLabel LabelQuestion;
    private javax.swing.JLabel LabelQuestion1;
    private javax.swing.JLabel LableID1;
    private javax.swing.JLabel LableID2;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelTopic;
    // End of variables declaration//GEN-END:variables
}
