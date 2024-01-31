/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

public class Question {



    public int questionID;
    public String content;
    public int type;
    public String topic;
    public boolean answer;
    
   
    

    public Question() {
    }

    public Question(int questionID, String content, int type, String topic, boolean answer) {
        this.questionID = questionID;
        this.content = content;
        this.type = type;
        this.topic = topic;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" + "questionID=" + questionID + ", content=" + content + ", type=" + type + ", topic=" + topic + ", answer=" + answer + '}';
    }

}
