package board;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.DBConnection;

@WebServlet("/Insert")
public class Insert extends HttpServlet {
	private Connection con;
	private String sql, html, title, content, regUser, regDate ;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DBConnection dbc = new DBConnection();

		try {
			title = request.getParameter("title");
			content = request.getParameter("content");
			regUser = "admin";
			regDate = "20180427";
			
			con = dbc.openDB();
			sql = "insert into board(title, content, regUser, regDate)";
			sql += "values";
			sql += "(?,?,?,?)";


			java.util.List list = new ArrayList();	
			list.add(title);
			list.add(content);
			list.add(regUser);
			list.add(regDate);
			
			int result = dbc.edit(con, sql, list);
			System.out.println(result);
			response.sendRedirect("List");
			
			con.close();

			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {

		doGet(request, response);
	}

}
