package con.hauphvnjavaweb.service.impl;

import javax.inject.Inject;

import com.hauphvnjavaweb.dao.IUserDAO;
import com.hauphvnjavaweb.model.UserModel;
import com.hauphvnjavaweb.service.IUserService;

public class UserService implements IUserService{

	@Inject
	private IUserDAO userDao;
	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDao.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
