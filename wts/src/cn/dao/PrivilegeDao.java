package cn.dao;

import java.util.Collection;
import java.util.List;

import cn.base.BaseDao;
import cn.entity.Privilege;


public interface PrivilegeDao extends BaseDao<Privilege> {

	Collection<String> getAllPrivilegeUrls();

	List<Privilege> findTopList();
	
}
