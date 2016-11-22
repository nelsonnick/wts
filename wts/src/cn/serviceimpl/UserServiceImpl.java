package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.UserDao;
import cn.entity.PageBean;
import cn.entity.User;
import cn.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	public User getById(Long id) {
		return userDao.getById(id);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public void save(User user) {
		userDao.save(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByAccountAndPassword(String account, String password) {
		return userDao.findByAccountAndPassword(account,password);
	}

	@Override
	public User findByAccount(String account) {
		return userDao.findByAccount(account);
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return userDao.getPageBean(pageNum,pageSize,hql);
	}


}
