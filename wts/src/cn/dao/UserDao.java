package cn.dao;

import cn.base.BaseDao;
import cn.entity.User;


public interface UserDao extends BaseDao<User> {

	User findByAccountAndPassword(String account,String password);

	User findByAccount(String account);

}
