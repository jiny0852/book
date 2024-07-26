package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorSelectOne {

	public static void main(String[] args) {
		
		System.out.println("book 1권 정보(책정보+작가정보)");
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book");
			
			
			// 3. SQL문 준비 / 바인딩 / 실행   ## 중요중요
			
			// - sql문 준비 (insert 문을 자바의 문자열로 만든다)
			String query = ""; 
			query += " select   author_id, ";
			query += " 			author_name, ";
			query += " 			aythor_desc ";
			query += " from author ";
			query += " where author_id = ? ";
			
			// - 바인딩 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1);
			
			// - 실행 
			rs = pstmt.executeQuery(); //주소값 출력됨
			//System.out.println(rs + "건 출력 되었습니다.");


			// 4.결과처리
			rs.next(); //컬럼명(Cursor) 한칸 내리기 첨에 데이터가 두줄로 들어옴
			int id = rs.getInt("author_id");
			String name = rs.getString("author_name");
			String desc = rs.getString("aythor_desc");
			
			System.out.println("author_id : " + id);
			System.out.println("author_name : " + name);
			System.out.println("author_desc : " + desc);
			
			System.out.println("리스트 출력 되었습니다.");
			
			BookVO authorVo = new BookVO(id, name, desc);
			System.out.println(authorVo.toString());
		
			
		
		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {

			// 5. 자원정리
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error:" + e);
			}
		}
		
		sc.close();

	}

}
