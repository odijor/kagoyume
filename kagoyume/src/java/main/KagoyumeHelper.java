package main;

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
}
