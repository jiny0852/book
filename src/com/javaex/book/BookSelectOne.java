package com.javaex.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BookSelectOne {

	public static void main(String[] args) {
		
		System.out.println("book 1권 정보(책정보+작가정보)");
		
		List<Book2VO> bookList = new ArrayList<Book2VO>();
		
		
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
			query += " select 	b.book_id, ";
			query += " 			b.title, ";
			query += " 			b.pubs, ";
			query += " 			b.pub_date, ";
			query += " 			b.author_id, ";
			query += " 			a.author_name, ";
			query += " 			a.author_desc ";
			query += " from book b,  author a ";
			query += " where b.book_id = a.author_id ";
			query += " and b.book_id = ? ";
			
			// - 바인딩 
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, 1);
			
			// - 실행 
			rs = pstmt.executeQuery(); 


			// 4.결과처리
			
			while (rs.next()) {
				
				int bookId = rs.getInt("b.book_id");
				String title = rs.getString("b.title");
				String pubs = rs.getString("b.pubs");
				String pubDate = rs.getString("b.pub_date");
				int authorId = rs.getInt("b.author_id");
				String name = rs.getString("a.author_name");
				String desc = rs.getString("a.author_desc");
				
				//BookVO authorVo = new BookVO(rs.getInt("book_id") ,rs.getString("title"));
				Book2VO book2Vo = new Book2VO(bookId, title, pubs, pubDate, authorId, name, desc);
				bookList.add(book2Vo);
				
				
			}
						
			System.out.println("리스트 하나 출력 되었습니다.");
						
		
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
			
			System.out.println(bookList.get(i).getId());
			System.out.println(bookList.get(i).getTitle());
			System.out.println(bookList.get(i).getPubs());
			System.out.println(bookList.get(i).getPub_date());
			System.out.println(bookList.get(i).getAuthor_id());
			System.out.println(bookList.get(i).getName());
			System.out.println(bookList.get(i).getDesc());
			
		}
		
		System.out.println("-------------------------");
		
		for (Book2VO vo : bookList ) {
			System.out.print(vo.getId() + ".   ");
			System.out.print(vo.getTitle() + "\t");
			System.out.print(vo.getPubs() + "\t");
			System.out.println(vo.getPub_date() + "\t");
			System.out.println(vo.getAuthor_id() + "\t");
			System.out.println(vo.getName() + "\t");
			System.out.println(vo.getDesc());
		}
		
		
		sc.close();

	}

}
