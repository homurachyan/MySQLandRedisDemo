package com.seele.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OldJdbcImpl implements OldJdbc {
	
    String driver = "com.mysql.jdbc.Driver";
    String userName = "root";
    String passwrod = "123456";
    String url = "jdbc:mysql://localhost:3306/test";
	@Override
	public void insertPerson(String username) {
      try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(url, userName,passwrod);
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("insert into test.user (username) values (?)");
            pstmt.setString(1, username);
            pstmt.executeUpdate();
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	@Override
	public void findAllPerson() {
        try {
            Class.forName(driver);
            Connection conn = (Connection) DriverManager.getConnection(url, userName,passwrod);
            PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement("select * from test.user");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("username : " + rs.getString(2));
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
