/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import dtos.Game;
import java.sql.*;
import javax.naming.NamingException;
import utils.DBConnect;
import dtos.User;
import java.util.ArrayList;

/**
 *
 * @author SE130280
 */
public class UserDAO {

    public boolean Register(User user) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String SQL = "INSERT INTO Account(username, password, email, country) "
                + "VALUES (?, ?, ?, ?)";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(SQL);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getCountry());

                ps.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return false;
    }

    public ArrayList getEmail() throws SQLException {
        Connection con = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;
        ArrayList list = new ArrayList<>();
        String sql = "select email from Account ";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String email = rs.getString("email");
                    list.add(email);
                    System.out.println("Gotit");
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstm != null) {
                pstm.close();
            }
            if (con != null) {
                pstm.close();
            }
        }
        return list;
    }

    public static boolean checkLogin(String username, String password) throws NamingException, SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from Account where username = ? and password = ?";
        try {
            con = DBConnect.makeConnection();
            if (con != null) {
                ps = con.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();

                if (rs.next()) {
                    String name = rs.getString("username");
                    String pass = rs.getString("password");
                    if (name.equals(username) && pass.equals(password)) {
                        return true;
                    }
                    return false;
                } else {
                    return false;
                }
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
}
