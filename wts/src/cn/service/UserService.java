package cn.service;

import java.util.List;

import cn.entity.PageBean;
import cn.entity.User;

public interface UserService {

	List<User> findAll();

	void save(User user);

	User getById(Long id);

	void update(User user);

	User findByAccountAndPassword(String account, String password);
	
	User findByAccount(String account);

	void delete(Long id);

	PageBean getPageBean(int pageNum, int pageSize, String hql);
	
}
