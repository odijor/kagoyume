package main;

import java.sql.Timestamp;

/**
 * ユーザー情報を持ちまわるJavaBeans
 * データベースのカラムと型に対応させている(DTO)。データの挿入、取り出しどちらにも便利
 * @author sige
 */
public class UserDataDTO {
    private int userID,total,deleteflg;
    private String name,password,mail,address;
    private Timestamp newDate;
    
    
    
    public int getUserID(){return this.userID;}
    public void setUserID(int userID){this.userID=userID;}
    
    public int getTotal(){return this.total;}
    public void setTotal(int total){this.total=total;}
    
    public int getDeleteflg(){return this.deleteflg;}
    public void setDeleteflg(int deleteflg){this.deleteflg=deleteflg;}
    
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    
    public String getPassword(){return this.password;}
    public void setPassword(String password){this.password=password;}
    
    public String getMail(){return this.mail;}
    public void setMail(String mail){this.mail=mail;}
    
    public String getAddress(){return this.address;}
    public void setAddress(String address){this.address=address;}
    
    public Timestamp getNewDate(){return this.newDate;}
    public void setNewDate(Timestamp newDate){this.newDate=newDate;}
}

