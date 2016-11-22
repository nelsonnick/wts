package cn.serviceimpl;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.RoleDao;
import cn.entity.PageBean;
import cn.entity.Role;
import cn.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	public void delete(Long id) {
		roleDao.delete(id);
	}

	public void save(Role role) {
		roleDao.save(role);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public List<Role> getByIds(Long[] roleIds) {
		return roleDao.getByIds(roleIds);
	}

	@Override
	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return roleDao.getPageBean(pageNum,pageSize,hql);
	}

}
