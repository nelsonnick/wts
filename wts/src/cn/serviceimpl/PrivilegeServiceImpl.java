package cn.serviceimpl;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.PrivilegeDao;
import cn.entity.Privilege;
import cn.service.PrivilegeService;

@Service
@Transactional
public class PrivilegeServiceImpl implements PrivilegeService {

	@Resource
	private PrivilegeDao privilegeDao;

	public Privilege getById(Long id) {
		return privilegeDao.getById(id);
	}

	public void delete(Long id) {
		privilegeDao.delete(id);
	}

	public void save(Privilege privilege) {
		privilegeDao.save(privilege);
	}

	public void update(Privilege privilege) {
		privilegeDao.update(privilege);
	}

	public List<Privilege> findAll() {
		return privilegeDao.findAll();
	}

	
	public List<Privilege> findTopList() {
		
		return privilegeDao.findTopList();
	}

	
	public Collection<String> getAllPrivilegeUrls() {
		
		return privilegeDao.getAllPrivilegeUrls();
	}


	public List<Privilege> getByIds(Long[] privilegeIds) {
		return privilegeDao.getByIds(privilegeIds);
	}

	
	
	
}
