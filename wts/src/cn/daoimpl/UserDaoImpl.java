package cn.daoimpl;

import org.springframework.stereotype.Repository;

import cn.base.BaseDaoImpl;
import cn.dao.UserDao;
import cn.entity.User;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	@Override
	public User findByAccountAndPassword(String account, String password) {
		if (password != null) {
			//String md5Digest = DigestUtils.md5Hex(password);
			return (User) getSession()
					.createQuery(//
							"FROM User u WHERE u.account=? AND u.password=? AND u.active='激活'")//
					.setParameter(0, account)//
					.setParameter(1, password)//
					.uniqueResult();
		} else {
			return null;
		}
	}


	@Override
	public User findByAccount(String account) {
		return (User) getSession()
				.createQuery(//
						"FROM User u WHERE u.account=?")
				.setParameter(0, account)
				.uniqueResult();

	}

}
