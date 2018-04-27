package board;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;


@WebServlet("/List")
public class List extends HttpServlet {
   
   private Connection con;
   private java.util.List<HashMap<String, Object>> list;
   private String sql, html;
   
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
	   DBConnection dbc = new DBConnection();
	   
	   String boardNo = request.getParameter("boardNo");
	   if (boardNo == null) {
		   request.setAttribute("boardNo", "");
	   }else {
		   request.setAttribute("boardNo", boardNo);
	   }
	   
      
      try {
         con = dbc.openDB();
         sql = "select * from board";
         list = dbc.select(con, sql);
         System.out.println(list);
         html = "";
         for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            html += list.get(i) + "<br>"; 
         }
//         response.setContentType("text/html; charset=UTF-8");
//         response.setCharacterEncoding("UTF-8");
//         response.getWriter().append(html);
         
         request.setAttribute("list", list);
         RequestDispatcher RD = request.getRequestDispatcher("list.jsp");
         RD.forward(request, response);
         con.close();
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
   }

}