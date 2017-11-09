package main;

import javax.servlet.http.HttpSession;
import main.UserData;
/**
 * 画面系の処理や表示を簡略化するためのヘルパークラス。
 * @author sige
 */
public class KagoyumeHelper {
    //トップへのリンクを定数として設定
    private final String homeURL = "top.jsp";
    
    public static KagoyumeHelper getInstance(){
        return new KagoyumeHelper();
    }
    
    //トップへのリンクを返却
    public String home(){
        return "<a href=\""+homeURL+"\">トップへ戻る</a>";
    }
    
    //ログイン情報管理
    private String loginID;
    private String name;
    
    public String getLoginID(){return this.loginID;}
    public void setLoginID(String loginID){this.loginID=loginID;}
    
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    
    /**
     * ログイン管理
     * @param URL 戻る為のURL基本的にはtop.   検索画面と詳細画面のみ直前のページに返す
     * @param loginID ログインしているユーザーのID  
     * @param name  ログインしているユーザーの名前
     * @return  非ログイン時:ログインボタン   ログイン時:ログアウトと買い物ボタン
     */
    public String loginCheck(String URL,int loginID,String name){
        String header;
        if(loginID==-1){
            header ="<h1>全ての機能を使うためにはログインをしてください。<h1>\n" +
                    "<form action=\"Login\" method=\"post\">\n" +
                    "<input type=\"hidden\" name=\"URL\" value="+URL+">\n" +
                    "<input type=\"submit\" value=\"ログイン\">\n" +
                    "</form><br><br><br><br>";
        }else{
            header ="<h1>ようこそ<a href=\"Mydata\""+loginID+"%>"+name+"</a>さん。<h1>\n"+
                    "<form action=\"Login\" method=\"post\">\n" +
                    "<input type=\"hidden\" name=\"URL\" value="+URL+">\n" +
                    "<input type=\"submit\" value=\"ログアウト\">\n" +
                    "</form>"+
                    "<form action=\"Cart\" method=\"post\">\n" +
                    "<input type=\"submit\" value=\"買い物籠\">\n"+
                    "</form>";
        }
        return header;
    }
    
    public String exTypenum(int i){
        switch(i){
            case 1:
                return "宅配便";
            case 2:
                return "コンビニで受け取り";
        }
        return "";
    }
}
