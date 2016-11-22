package cn.service;

import java.util.Collection;
import java.util.List;

import cn.entity.Privilege;

public interface PrivilegeService {

	List<Privilege> findAll();

	void delete(Long id);

	void save(Privilege privilege);

	Privilege getById(Long id);

	void update(Privilege privilege);
	
	List<Privilege> findTopList();

	Collection<String> getAllPrivilegeUrls();

	List<Privilege> getByIds(Long[] privilegeIds);
}
