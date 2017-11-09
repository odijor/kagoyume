package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author sige
 */
@WebServlet(name = "LoginResult", urlPatterns = {"/LoginResult"})
public class LoginResult extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            
            //アクセスルートチェック
            String accesschk = request.getParameter("ac");
            if(accesschk ==null || (Integer)session.getAttribute("ac")!=Integer.parseInt(accesschk)){
                throw new Exception("不正なアクセスです");
            }
            
            UserData ud = new UserData();
            String URL = (String)session.getAttribute("URL");
            HashMap <Integer,ArrayList<SearchDataBeans>>userlog=new HashMap<>();
                if(session.getAttribute("userList")!=null)userlog =(HashMap <Integer,ArrayList<SearchDataBeans>>)session.getAttribute("userList");
            
            
            //入力された名前とパスワード
            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("pass"));
            
            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO DTO = ud.castToDTO();
            
            //DBで一致するものがあるか確認「
            UserDataDTO loginUser = UserDataDAO .getInstance().login(DTO);
            //成功したらアクティブユーザーとログイン履歴更新、失敗したらそのままloginページへ
            if(loginUser != null){
                
                session.setAttribute("active",loginUser);
                
                ArrayList<SearchDataBeans> usercartlog=new ArrayList<>();
                if(session.getAttribute("usercart")!=null)usercartlog =(ArrayList<SearchDataBeans>)session.getAttribute("usercart");
                
                if((ArrayList<SearchDataBeans>)session.getAttribute("usercart") !=null)usercartlog = (ArrayList<SearchDataBeans>)session.getAttribute("usercart");
                if(userlog.containsKey(loginUser.getUserID())){
                     ArrayList<SearchDataBeans> usercart =userlog.get(loginUser.getUserID());
                     usercartlog.addAll(usercart);
                }
                session.setAttribute("userList", userlog);
                session.setAttribute("usercart", usercartlog);
                request.getRequestDispatcher(URL).forward(request, response);
                }else{
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
            
        }catch(Exception e){
            //何らかの理由で失敗したらエラーページにエラー文を渡して表示。
            request.setAttribute("error", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
