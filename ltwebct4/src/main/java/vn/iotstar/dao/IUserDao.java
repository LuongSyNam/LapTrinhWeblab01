package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.models.UserModel;

public interface IUserDao {

	List<UserModel> findAll();
	
	UserModel findById(int id);
	
	void insert(UserModel user);
	
	UserModel findByUserName(String username);
	
	boolean checkExistEmail(String email);
	
	boolean checkExistUsername(String username);
	
	boolean checkExistPhone(String phone);
	
	void update(String pw, String email);

	void updateacc(int id, String images, String fullname, String phone);
}
