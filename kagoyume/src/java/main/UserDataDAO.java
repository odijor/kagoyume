
package main;

import base.DBManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する
 * DB接続系はDBManagerクラスに一任
 * 基本的にはやりたい1種類の動作に対して1メソッド
 * @author sige
 */
public class UserDataDAO {
    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance(){
    return new UserDataDAO();
    }
    
    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
     public void insert(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
     }
     
     /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
     public void update(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t set name=?,password=?,mail=?,address=?,newDate=? where userID=?;");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.setInt(6, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
     }
     
     /**
     * データの削除フラグ処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     */
     public void delete(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            st =  con.prepareStatement("UPDATE user_t set deleteflg=1 where userID=?;");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            System.out.println("delete completed");
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    /**
     * ログイン処理を行う。一致しなければnullを返す。
     * @param ud 入力されたデータ
     * @return 一致したデータ
     * @throws SQLException 
     */
    public UserDataDTO login(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            //
            String sql = "SELECT * FROM user_t where name=? and password=? and deleteflg=0;";
            st =  con.prepareStatement(sql);
            st.setString(1,ud.getName());
            st.setString(2,ud.getPassword());
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                    UserDataDTO resultUd = new UserDataDTO();
                    resultUd.setUserID(rs.getInt(1));
                    resultUd.setName(rs.getString(2));
                    resultUd.setPassword(rs.getString(3));
                    resultUd.setMail(rs.getString(4));
                    resultUd.setAddress(rs.getString(5));
                    resultUd.setTotal(rs.getInt(6));
                    resultUd.setNewDate(rs.getTimestamp(7));
                
            System.out.println("search completed");
            
            
            return resultUd;
            }else{
            return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }

    }
    
    /**
     * ユーザーIDによる購入データの検索処理を行う。
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー 
     * @return 検索結果
     */
    public ArrayList<ArrayList> searchBuy(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            String sql = "SELECT * FROM buy_t WHERE userID = ?";
            
            st =  con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());
            
            ResultSet rs = st.executeQuery();
            ArrayList<ArrayList>resultList=new ArrayList<>();
            while(rs.next()){
            ArrayList<Object> resultUd = new ArrayList<>();
            resultUd.add(rs.getString(3));
            resultUd.add(rs.getInt(4));
            resultUd.add(rs.getDate(5));
            resultList.add(resultUd);
            }
            System.out.println("searchByID completed");

            return resultList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
    
    /**
     * 更新内容について同じ名前、パスワードのデータを返す
     * @param ud 対応したデータを保持しているJavaBeans
     * @return 同じ名前、パスワードのデータ
     * @throws SQLException 
     */
    public UserDataDTO updatechk(UserDataDTO ud) throws SQLException{
        Connection con = null;
        PreparedStatement st = null;
        try{
            con = DBManager.getConnection();
            
            //
            String sql = "SELECT * FROM user_t where userID!=? and name=? and password=? and deleteflg=0;";
            st =  con.prepareStatement(sql);
            st.setInt(1,ud.getUserID());
            st.setString(2,ud.getName());
            st.setString(3,ud.getPassword());
            
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                    UserDataDTO resultUd = new UserDataDTO();
                    resultUd.setUserID(rs.getInt(1));
                    resultUd.setName(rs.getString(2));
                    resultUd.setPassword(rs.getString(3));
                    resultUd.setMail(rs.getString(4));
                    resultUd.setAddress(rs.getString(5));
                    resultUd.setTotal(rs.getInt(6));
                    resultUd.setNewDate(rs.getTimestamp(7));
                
            System.out.println("search completed");
            
            
            return resultUd;
            }else{
            return null;
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
            throw new SQLException(e);
        }finally{
            if(con != null){
                con.close();
            }
        }
    }
}
