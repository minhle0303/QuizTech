package view;

import dao.Option;
import dao.Question;
import dao.QuestionDAO;
import dao.Result;
import dao.ResultDAO;
import dao.User;
import dao.dbConnect;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.TimerTask;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.util.Timer;

public class mediumQuiz extends javax.swing.JFrame {

    public String answer;
    public int marks = 0;
    public Timer timer;
    public Date today = new Date();
    private int userid;
    private String topic = "";
    private int resultId;
    int index = 0;
    int indexQ = 1;
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Option> ops = new ArrayList<>();
    ArrayList<User> dsUser = new ArrayList<>();
    ArrayList<ViewResult> dsResults = new ArrayList<>();
    private int secondsRemaining;
    private int elapsedTimeInSeconds;
    private int elapsedMins;
    private int elapsedSecs;
    private java.util.Timer t;

    public mediumQuiz() {
        initComponents();
        initQuestions();
        DateLabel();
        initTimer();

    }

    mediumQuiz(String topic, int userid) {

        this.topic = topic;
        initComponents();
        initQuestions();
        DateLabel();
        initTimer();

        this.userid = userid;
        inforUser();

    }

    private void initQuestions() {

        questions = (ArrayList<Question>) dao.QuestionDAO.getz(2, topic);
        String sIDs = "";
//        labelll.setText(String.valueOf(marks));
        for (Question d : questions) {
            sIDs += (d.questionID + ",");
        }
        sIDs = sIDs.substring(0, sIDs.length() - 1);
        ops = dao.QuestionDAO.getOptions(sIDs);

        renderQuestion(index);
        renderAnswer(index);

    }

    private void renderAnswer(int index) {
        String[] answer = new String[4];
        int qID = questions.get(index).questionID;
        var op = ops.stream().filter(x -> x.questionID == qID).toList();

        Option1.setText(op.get(0).content);
        Option2.setText(op.get(1).content);
        Option3.setText(op.get(2).content);
        Option4.setText(op.get(3).content);
    }

    private void renderQuestion(int index) {

        LabelIndex.setText(String.valueOf(indexQ));
        jLabel6.setText(String.valueOf(indexQ));
        LabelQuestion.setText(questions.get(index).content);

    }

    private void inforUser() {
        dsUser = dao.UserDAO.getByID(userid);
        

        LabelID.setText(String.valueOf(dsUser.get(index).userID));
        LabelName.setText(dsUser.get(index).name);
        labelTopic.setText(this.topic);
    }

    public void DateLabel() {
        Date today = new Date();

        // Set the locale to Vietnam
        Locale localeVietnam = new Locale("vi", "VN");

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, localeVietnam);
        String strDate = df.format(today);
        DateLabel.setText(strDate);
    }

    public void answerCheck() {
        var userAnswer = "";
        String[] options = new String[4];
        int qID = questions.get(index).questionID;
        var correctAnswers = ops.stream().filter(x -> x.questionID == qID && x.answer).toList();

        if (Option1.isSelected()) {
            userAnswer = Option1.getText();

        } else if (Option2.isSelected()) {

            userAnswer = Option2.getText();
        } else if (Option3.isSelected()) {

            userAnswer = Option3.getText();

        } else if (Option4.isSelected()) {

            userAnswer = Option4.getText();
        } else {
            userAnswer = null;
        }

        for (int i = 0; i < correctAnswers.size(); i++) {
            if (userAnswer != null && userAnswer.equals(correctAnswers.get(i).content) && correctAnswers.get(i).answer) {
                                JOptionPane.showMessageDialog(this, "Correct!");

                marks++;
                break; // No need to check further once a correct answer is found
            }
        }

        if (userAnswer != null && marks == 0) {
            JOptionPane.showMessageDialog(this, "Incorrect!");
            marks = marks + 0;
        }

        optionGroup.clearSelection();
    }

    int count = 0;

    private void initTimer() {
        secondsRemaining = 300; // 10 minutes

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
        int score = marks;
        //Time
        String duration = String.format("%02d:%02d", elapsedMins, elapsedSecs);
        //Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        var sDate = dateFormat.format(today);
        int level = 2;
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        optionGroup = new javax.swing.ButtonGroup();
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
        LabelQuestion = new javax.swing.JLabel();
        Option1 = new javax.swing.JRadioButton();
        Option2 = new javax.swing.JRadioButton();
        Option3 = new javax.swing.JRadioButton();
        Option4 = new javax.swing.JRadioButton();
        ButtonNext = new javax.swing.JButton();
        ButtonSubmit = new javax.swing.JButton();
        labelTopic = new javax.swing.JLabel();
        LabelQuestion1 = new javax.swing.JLabel();
        LabelIndex = new javax.swing.JLabel();
        LabelIndex1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Topic = new javax.swing.JLabel();
        LableID2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(75, 75));

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

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

        jLabel7.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("10");

        jLabel8.setFont(new java.awt.Font("PT Serif Caption", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("/");

        DateLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        DateLabel.setForeground(new java.awt.Color(0, 0, 0));
        DateLabel.setText("23/23/1000");

        jLabel9.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("DATE:");

        TimeLabel.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        TimeLabel.setForeground(new java.awt.Color(255, 51, 51));
        TimeLabel.setText("DURATION:");

        LabelQuestion.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelQuestion.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion.setText("Content");

        Option1.setBackground(new java.awt.Color(255, 204, 153));
        optionGroup.add(Option1);
        Option1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        Option1.setForeground(new java.awt.Color(0, 0, 0));
        Option1.setText("Option1");
        Option1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option1ActionPerformed(evt);
            }
        });

        Option2.setBackground(new java.awt.Color(255, 204, 153));
        optionGroup.add(Option2);
        Option2.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        Option2.setForeground(new java.awt.Color(0, 0, 0));
        Option2.setText("Option2");
        Option2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option2ActionPerformed(evt);
            }
        });

        Option3.setBackground(new java.awt.Color(255, 204, 153));
        optionGroup.add(Option3);
        Option3.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        Option3.setForeground(new java.awt.Color(0, 0, 0));
        Option3.setText("Option3");
        Option3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option3ActionPerformed(evt);
            }
        });

        Option4.setBackground(new java.awt.Color(255, 204, 153));
        optionGroup.add(Option4);
        Option4.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        Option4.setForeground(new java.awt.Color(0, 0, 0));
        Option4.setText("Option4");
        Option4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Option4ActionPerformed(evt);
            }
        });

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

        labelTopic.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        labelTopic.setForeground(new java.awt.Color(0, 0, 0));
        labelTopic.setText("..");

        LabelQuestion1.setBackground(new java.awt.Color(255, 255, 255));
        LabelQuestion1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelQuestion1.setForeground(new java.awt.Color(0, 0, 0));
        LabelQuestion1.setText("Question ");

        LabelIndex.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelIndex.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex.setText("1");

        LabelIndex1.setBackground(new java.awt.Color(255, 255, 255));
        LabelIndex1.setFont(new java.awt.Font("PT Sans Caption", 0, 18)); // NOI18N
        LabelIndex1.setForeground(new java.awt.Color(0, 0, 0));
        LabelIndex1.setText(":");

        jLabel14.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("DURATION:");

        jLabel5.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Medium");

        Topic.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        Topic.setForeground(new java.awt.Color(0, 0, 0));
        Topic.setText("TOPIC:");

        LableID2.setFont(new java.awt.Font("PT Serif Caption", 1, 20)); // NOI18N
        LableID2.setForeground(new java.awt.Color(0, 0, 0));
        LableID2.setText("LEVEL:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(188, 188, 188)
                        .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(513, 513, 513)
                        .addComponent(ButtonSubmit))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(Option1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(Option4, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Option3, javax.swing.GroupLayout.PREFERRED_SIZE, 409, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 982, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Option2, javax.swing.GroupLayout.PREFERRED_SIZE, 1116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(LabelID)
                                            .addComponent(LabelName)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel14)
                                    .addComponent(Topic)
                                    .addComponent(LableID2))
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(DateLabel)
                                    .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTopic)
                                    .addComponent(jLabel5))
                                .addGap(169, 169, 169)))))
                .addContainerGap(99, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                                .addGap(17, 17, 17)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(DateLabel)
                                .addGap(24, 24, 24)
                                .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(19, 19, 19)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelTopic)
                                    .addComponent(Topic)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(20, 20, 20)
                                .addComponent(jLabel14)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(LableID2)))
                        .addGap(18, 18, 18)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelIndex)
                    .addComponent(LabelQuestion1)
                    .addComponent(LabelIndex1)
                    .addComponent(LabelQuestion))
                .addGap(22, 22, 22)
                .addComponent(Option1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Option2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(Option3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Option4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonNext, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ButtonSubmit, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
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

    private void ButtonSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonSubmitActionPerformed

        int a = JOptionPane.showConfirmDialog(null, "Submit?");
        if (a == 0) {
            t.cancel();
            submit();
            Result result = ResultDAO.getByID(resultId);

            new ViewResult(result).setVisible(true);
            this.dispose();
        }

    }//GEN-LAST:event_ButtonSubmitActionPerformed

    private void endSubmit() {

        submit();
        Result result = ResultDAO.getByID(resultId);

        new ViewResult(result).setVisible(true);
        this.dispose();

    }
    private void ButtonNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonNextActionPerformed
        // TODO add your handling code here:

        if (optionGroup.getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Please get any choice");

        } else {
            
            answerCheck();
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

    private void Option1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option1ActionPerformed
        // TODO add your handling code here:
        if (Option1.isSelected()) {
            Option2.setSelected(false);
            Option3.setSelected(false);
            Option4.setSelected(false);
        }
    }//GEN-LAST:event_Option1ActionPerformed

    private void Option2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option2ActionPerformed
        // TODO add your handling code here:
        if (Option2.isSelected()) {
            Option1.setSelected(false);
            Option3.setSelected(false);
            Option4.setSelected(false);
        }
    }//GEN-LAST:event_Option2ActionPerformed

    private void Option4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option4ActionPerformed
        // TODO add your handling code here:
        if (Option4.isSelected()) {
            Option1.setSelected(false);
            Option2.setSelected(false);
            Option3.setSelected(false);
        }
    }//GEN-LAST:event_Option4ActionPerformed

    private void Option3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Option3ActionPerformed
        // TODO add your handling code here:
        if (Option3.isSelected()) {
            Option1.setSelected(false);
            Option2.setSelected(false);
            Option4.setSelected(false);
        }
    }//GEN-LAST:event_Option3ActionPerformed

    private void ButtonSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ButtonSubmitMouseClicked

    }//GEN-LAST:event_ButtonSubmitMouseClicked

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
            java.util.logging.Logger.getLogger(mediumQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mediumQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mediumQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mediumQuiz.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mediumQuiz().setVisible(true);
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
    private javax.swing.JLabel LableID2;
    private javax.swing.JRadioButton Option1;
    private javax.swing.JRadioButton Option2;
    private javax.swing.JRadioButton Option3;
    private javax.swing.JRadioButton Option4;
    private javax.swing.JLabel TimeLabel;
    private javax.swing.JLabel Topic;
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
    private javax.swing.ButtonGroup optionGroup;
    // End of variables declaration//GEN-END:variables

}
