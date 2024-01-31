/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


public class Result {
    public int resultid;
    public int userID;
    public int score;
    public String duration;
    public String date;
    public int level;
    public String topic; 
    
    public Result() {
    }

    public Result(int resultid, int userID, int score, String duration, String date, int level, String topic) {
        this.resultid = resultid;
        this.userID = userID;
        this.score = score;
        this.duration = duration;
        this.date = date;
        this.level = level;
        this.topic = topic;
    }

    @Override
    public String toString() {
        return "result{" + "resultid=" + resultid + ", userID=" + userID + ", score=" + score + ", duration=" + duration + ", date=" + date + ", level=" + level + ", topic=" + topic + '}';
    }
    
    
    
}
