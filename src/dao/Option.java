/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


public class Option {
    public int optionID;
    public int questionID;
    public String content;
    public boolean answer;

    public Option() {
    }

    public Option(int optionID, int questionID, String content, boolean answer) {
        this.optionID = optionID;
        this.questionID = questionID;
        this.content = content;
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Option{" + "optionID=" + optionID + ", questionID=" + questionID + ", content=" + content + ", answer=" + answer + '}';
    }
    
    
    
    
    
    
}
