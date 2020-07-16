package com.igeekhome.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jdbc.utils.JDBCUtil;

/**
 * Servlet implementation class QueryDriver
 */
public class QueryDriver extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		
		String chedui=req.getParameter("chedui");
		
		Statement st=null;
		ResultSet rs=null;
		Connection conn=null;
		try {
			conn=JDBCUtil.getconn();
			st=conn.createStatement();
			System.out.println("请求查询  "+chedui+"  的司机情况");
			String sql="select *from driver WHERE chedui='"+chedui+"'";
			rs=st.executeQuery(sql);
			
			while(rs.next()) {
				System.out.println(rs.getString("name")+"\t"+rs.getString("sex")+"\t"+rs.getInt("wnum")+"\t"+rs.getString("luxian")+"\t"+rs.getString("chedui"));
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		  finally {
			  JDBCUtil.close(conn, st, rs);
		  }	
	}

}
