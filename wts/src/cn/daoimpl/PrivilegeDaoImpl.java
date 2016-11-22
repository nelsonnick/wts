package cn.daoimpl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.base.BaseDaoImpl;
import cn.dao.PrivilegeDao;
import cn.entity.Privilege;

@Repository
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements PrivilegeDao {

	@SuppressWarnings("unchecked")
	public List<Privilege> findTopList() {
	
		return getSession().createQuery(//
				"FROM Privilege p WHERE p.parent IS NULL")//
				.list();
	}
	@SuppressWarnings("unchecked")
	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
	}
}
