package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSelect {

	public static void main(String[] args) {
		
		System.out.println("book 전체리스트");
		
		
		List<BookVO> bookList = new ArrayList<BookVO>();
		
		
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
			
			
			// 3. SQL문 준비 / 바인딩 / 실행   
			
			// - sql문 준비 
			String query = ""; 
			query += " select 	book_id,";
			query += "			title, ";
			query += " 			pubs, ";
			query += " 			pub_date, ";
			query += " 			author_id ";
			query += " from book ";
			
			// - 바인딩 
			pstmt = conn.prepareStatement(query);
			
			// - 실행 
			rs = pstmt.executeQuery(); 


			// 4.결과처리
			
			while (rs.next()) {
				
				int bookId = rs.getInt("book_id");
				String title = rs.getString("title");
				String pubs = rs.getString("pubs");
				String pubDate = rs.getString("pub_date");
				int authorId = rs.getInt("author_id");
				
				//BookVO authorVo = new BookVO(rs.getInt("book_id") ,rs.getString("title"));
				BookVO authorVo = new BookVO(bookId, title, pubs, pubDate, authorId);
				bookList.add(authorVo);
				
				
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
		
		
		//전체 출력
		for (int i = 0; i < bookList.size(); i++) {
			
			System.out.println(bookList.get(i).getBookId());
			System.out.println(bookList.get(i).getTitle());
			System.out.println(bookList.get(i).getPubs());
			System.out.println(bookList.get(i).getPub_date());
			System.out.println(bookList.get(i).getAuthor_id());
			
		}
		
		System.out.println("-------------------------");
		
		for (BookVO vo : bookList ) {
			System.out.print(vo.getBookId() + ".   ");
			System.out.print(vo.getTitle() + "\t");
			System.out.print(vo.getPubs() + "\t");
			System.out.println(vo.getPub_date());
		}
		
		
		sc.close();

	}

}
