package cn.service;

import java.util.List;

import cn.entity.PageBean;
import cn.entity.Resave;

public interface ResaveService {

	List<Resave> findAll();

	void delete(Long id);

	void save(Resave entity);

	Resave getById(Long id);

	void update(Resave entity);

	List<Resave> findByDnumber(String dnumber);

	List<Resave> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
