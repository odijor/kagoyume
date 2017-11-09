package main;

import java.util.ArrayList;

/**
 *  ページで入出力されるユーザー情報を持ちまわるJavaBeans。DTOと違い画面表示系への結びつきが強い
 * @author sige
 */
public class UserData {
    private String name;
    private String password;
    private String mail;
    private String address;
    private String loginID;
    
    public void UserData(){
        this.name = "";
        this.password= "";
        this.mail="";
        this.address="";
        this.loginID=null;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        //空文字(未入力)の場合空文字をセット
        if(name.trim().length()==0){
            this.name = "";
        }else{
            this.name = name;
        }
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        //空文字(未入力)の場合空文字をセット
        if(password.trim().length()==0){
            this.password = "";
        }else{
            this.password = password;
        }
    }
    
    public String getMail() {
        return mail;
    }
    public void setMail(String mail) {
        //空文字(未入力)の場合空文字をセット
        if(mail.trim().length()==0){
            this.mail = "";
        }else{
            this.mail = mail;
        }
    }
    
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        //空文字(未入力)の場合空文字をセット
        if(address.trim().length()==0){
            this.address = "";
        }else{
            this.address = address;
        }
    }
    public String getLogin(){return loginID;}
    public void setLogin(String loginID){this.loginID=loginID;}
    
    public ArrayList<String> chkproperties(){
        ArrayList<String> chkList = new ArrayList<>();
        if(this.name.equals("")){
            chkList.add("name");
        }
        if(this.password.equals("")){
            chkList.add("password");
        }
        if(this.mail.equals("")){
            chkList.add("mail");
        }
        if(this.address.equals("")){
            chkList.add("address");
        }
        
        return chkList;
    }
    
    public UserDataDTO castToDTO(){
        UserDataDTO DTO =new UserDataDTO();
        DTO.setName(this.name);
        DTO.setPassword(this.password);
        DTO.setMail(this.mail);
        DTO.setAddress(this.address);
        return DTO;
    }
    
    public void castFromDTO(UserDataDTO DTO){
        this.name=DTO.getName();
        this.password=DTO.getPassword();
        this.mail=DTO.getMail();
        this.address=DTO.getAddress();
    }
    
}
