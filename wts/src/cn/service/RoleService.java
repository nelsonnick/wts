package cn.service;

import java.util.List;

import cn.entity.PageBean;
import cn.entity.Role;

public interface RoleService {

	List<Role> findAll();

	void delete(Long id);

	void save(Role role);

	Role getById(Long id);
	
	List<Role> getByIds(Long[] ids);

	void update(Role role);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
