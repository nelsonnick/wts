package cn.service;

import java.util.List;

import cn.entity.Deleted;
import cn.entity.PageBean;

public interface DeleteService {

	List<Deleted> findAll();

	void delete(Long id);

	void save(Deleted entity);

	Deleted getById(Long id);

	void update(Deleted entity);

	List<Deleted> findByDnumber(String dnumber);

	List<Deleted> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
