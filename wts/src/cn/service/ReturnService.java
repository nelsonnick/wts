package cn.service;

import java.util.List;

import cn.entity.PageBean;
import cn.entity.Returnd;

public interface ReturnService {

	List<Returnd> findAll();

	void delete(Long id);

	void save(Returnd entity);

	Returnd getById(Long id);

	void update(Returnd entity);

	List<Returnd> findByDnumber(String dnumber);

	List<Returnd> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
