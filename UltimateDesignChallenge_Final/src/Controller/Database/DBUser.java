package Controller.Database;

import Model.Database.DoctorEvent;
import Model.Database.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class DBUser {

    private Connection cnt;

    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Statement st;

    public DBUser(Connection cnt) {
        this.cnt = cnt;
    }

    public User getUser(String username) throws SQLException {
        String sql = "SELECT * FROM `usertbl` WHERE `username` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        rs.next();
        User result = new User(rs.getString("type"),
                rs.getString("username"),
                rs.getString("password"));
        return result;
    }

    public List<User> getUserByType(String type) throws SQLException {
        String sql = "SELECT * FROM `usertbl` WHERE `type` LIKE ?";
        ps = cnt.prepareStatement(sql);
        ps.setString(1, type);
        ResultSet rs = ps.executeQuery();
        List<User> result = new LinkedList<>();
        while (rs.next()) {
            User newUser = new User(rs.getString("type"),
                    rs.getString("username"),
                    rs.getString("password"));
            result.add(newUser);
        }
        return result;
    }

    public boolean checkUserPassword(User user, String password) {
        return user.getPassword().equals(password);
    }
}
