package impl;

import bean.Admin;
import service.IAdminService;

public class AdminServiceImp implements IAdminService{
	AdminDaoImp adminDao = new AdminDaoImp();
	@Override
	public int updateAdmin(Admin admin) {
		return adminDao.updateAdmin(admin);
	}

	@Override
	public Admin findAdmin(String username, String password) {
		return adminDao.findAdmin(username, password);
	}

	@Override
	public int saveAdmin(Admin admin) {
		return adminDao.saveAdmin(admin);
	}

	@Override
	public Admin findAdmin(String username) {
		return adminDao.findAdmin(username);
	}
	
}
