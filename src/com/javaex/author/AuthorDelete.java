package com.javaex.author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AuthorDelete {

	public static void main(String[] args) {
		
		System.out.println("작가삭제예제");
		
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
			
			// - sql문 준비 (insert 문을 자바의 문자열로 만든다) ## ; 금지, 띄어쓰기 중요
			String query = ""; // 줄 예쁘게 쓰려고 선언
			query += " delete from author ";
			query += " where author_id = ? ";
			
			// - 바인딩 -- 데이터 ? 와 데이터를 매칭 시킴, 숫자 중요
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 15); // # (n번째, n) 
			
			// - 실행 -- 성공하면 1 반환, # 퀴리 날림문 실행
			int count = pstmt.executeUpdate();
			
			
			// 4.결과처리
			System.out.println(count + "건 삭제 되었습니다.");

		
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
