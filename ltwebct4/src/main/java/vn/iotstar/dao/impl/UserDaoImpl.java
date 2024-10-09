package vn.iotstar.dao.impl;

import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import vn.iotstar.configs.DBConnectSQL;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {

	public Connection conn = null;
	public PreparedStatement ps = null;
	public ResultSet rs = null;
		
	@Override
	public List<UserModel> findAll() {
		String sql = " select * from users"; 		
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<UserModel> list = new ArrayList<UserModel>();
			while (rs.next()) {
				list.add(new UserModel(
						rs.getInt("id"),
						rs.getString("username"),
						rs.getString("password"),
						rs.getString("images"),
						rs.getString("fullname"),
						rs.getString("email"),
						rs.getString("phone"),
						rs.getInt("roleid"),
						rs.getDate("createDate")));
				
				}
			return list;
			} catch (Exception e) {
				e.printStackTrace(); 
			}		
		return null;
	}

	@Override
	public UserModel findById(int id) {
		String sql = "SELECT * FROM users WHERE id =  " + id + " ";
		try {
		conn = new DBConnectSQL().getConnection();
		ps = conn.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
		UserModel user = new UserModel();
		user.setId(rs.getInt("id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setImages(rs.getString("images"));
		user.setFullname(rs.getString("fullname"));
		user.setEmail(rs.getString("email"));
		user.setPhone(rs.getString("phone"));
		user.setRoleid(Integer.parseInt(rs.getString("roleid")));		
		user.setCreateDate(rs.getDate("createDate"));
		return user; }
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return null;
	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(username, password, images, fullname, email, phone, roleid, createDate) VALUES (?,?,?,?,?,?,?,?)";
		try {
		conn = new DBConnectSQL().getConnection();
		ps = conn.prepareStatement(sql);		
		ps.setString(1, user.getUsername());
		ps.setString(2, user.getPassword());
		ps.setString(3, user.getImages());
		ps.setString(4, user.getFullname());		
		ps.setString(5, user.getEmail());
		ps.setString(6,user.getPhone());
		ps.setInt(7,user.getRoleid());
		ps.setDate(8, user.getCreateDate());
		ps.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static void main(String[] args) {
		UserDaoImpl userDao = new UserDaoImpl();
		System.out.println(userDao.findAll());
		Date date = null;
		userDao.update("b", "a");
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM users WHERE username = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setFullname(rs.getString("fullname"));
				user.setEmail(rs.getString("email"));
				user.setPhone(rs.getString("phone"));		
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setCreateDate(rs.getDate("createDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkExistEmail(String email) {
		boolean duplicate = false;
		String query = "select * from users where email = ?";
		try {
		conn = new DBConnectSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, email);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistUsername(String username) {
		boolean duplicate = false;
		String query = "select * from users where username = ?";
		try {
		conn = new DBConnectSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, username);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		boolean duplicate = false;
		String query = "select * from users where phone = ?";
		try {
		conn = new DBConnectSQL().getConnection();
		ps = conn.prepareStatement(query);
		ps.setString(1, phone);
		rs = ps.executeQuery();
		if (rs.next()) {
		duplicate = true;
		}
		ps.close();
		conn.close();
		} catch (Exception ex) {}
		return duplicate;
	}

	@Override
	public void update(String pw, String email) {
		String sql = "UPDATE users SET password = ? WHERE email = ?";
		try {
			UserModel user = new UserModel();
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, pw);
			ps.setString(2, email);
			ps.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		
	}

	@Override
	public void updateacc(int id, String images, String fullname, String phone) {
		String sql = "UPDATE users SET images = ?, fullname = ?, phone = ? WHERE id = ?";
		try {
			UserModel user = new UserModel();
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, images);
	        ps.setString(2, fullname);
	        ps.setString(3, phone);
	        ps.setInt(4, id); 
			ps.executeUpdate();
		} catch (Exception e) {e.printStackTrace();}
		
	}	
}
