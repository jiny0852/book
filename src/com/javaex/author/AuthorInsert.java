package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorInsert {

	public static void main(String[] args) {
		
		System.out.println("작가등록예제");
		
		// 0. import java.sql.*;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
			// 2. Connection 얻어오기
			String url = "jdbc:mysql://localhost:3306/book_db";
			conn = DriverManager.getConnection(url, "book", "book"); //대문자 Static
			
			
			// 3. SQL문 준비 / 바인딩 / 실행   ## 중요중요
			
			// - sql문 준비 (insert 문을 자바의 문자열로 만든다)
			String query = "";
			query += " insert into author ";
			query += " values(null, ?, ?) "; // ?는 데이터 빈자리 표시용 '붙으면 안됨 문자열이라도 ?만 표시
			
			// - 바인딩 -- 데이터 ? 와 데이터를 매칭 시킴, 숫자 중요
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, "이문열");
			pstmt.setString(2, "경북 영양양");
			
			// - 실행 -- 성공하면 1 반환
			int count = pstmt.executeUpdate();
			
			
			// 4.결과처리
			System.out.println(count + "건 등록 되었습니다.");

		
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
		

	}

}
