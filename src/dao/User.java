
package dao;



public class User {
    public int userID;
    public String name;
    public String phone;
    public String gender;
    public String bod;
    public String role;
    public String username;
    public String password;
    public String email;

    public User() {
    }

    public User(int userID, String name, String phone, String gender, String bod, String role, String username, String password, String email) {
        this.userID = userID;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.bod = bod;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "userID=" + userID + ", name=" + name + ", phone=" + phone + ", gender=" + gender + ", bod=" + bod + ", role=" + role + ", Username=" + username + ", password=" + password + ", email=" + email + '}';
    }
    
    
    
    

}

