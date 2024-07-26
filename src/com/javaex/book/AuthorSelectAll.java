package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorSelectAll {

	public static void main(String[] args) {
		
		System.out.println("모든작가리스트예제");
		
		
		List<BookVO> authorList = new ArrayList<BookVO>();
		
		
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
			
			// - 바인딩 
			pstmt = conn.prepareStatement(query);
			
			// - 실행 
			rs = pstmt.executeQuery(); 


			// 4.결과처리
			
			while (rs.next()) {
				
				int id = rs.getInt("author_id");
				String name = rs.getString("author_name");
				String desc = rs.getString("aythor_desc");
				
				BookVO authorVo = new BookVO(id, name, desc);
				authorList.add(authorVo);
				
				
			}
						
			System.out.println("리스트 모두 출력 되었습니다.");
						
		
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
		
		//어디서나 데이터를 찾아서 쓸수 있다
		System.out.println(authorList.get(2).getName());
		
		//전체 출력
		for (int i = 0; i < authorList.size(); i++) {
			
			System.out.println(authorList.get(i).getId());
			System.out.println(authorList.get(i).getName());
			System.out.println(authorList.get(i).getDesc());
			
		}
		
		System.out.println("-------------------------");
		
		for (BookVO vo : authorList ) {
			System.out.print(vo.getId() + ".   ");
			System.out.print(vo.getName() + "\t");
			System.out.println(vo.getDesc());
		}
		
		
		sc.close();

	}

}
