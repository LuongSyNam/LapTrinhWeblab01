package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {

	UserModel login(String username, String password);
	UserModel FindByUserName(String username);
	void insert(UserModel user);
	UserModel register(String username, String password, String email, String fullname, String phone);
	boolean checkExistEmail(String email);
	boolean checkExistUsername(String username);
	boolean checkExistPhone(String phone);
	void update(String pw, String email);
	void updateacc(int id, String images, String fullname, String phone);
	UserModel FindById(int id);
}
