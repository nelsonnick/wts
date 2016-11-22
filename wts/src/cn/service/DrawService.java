package cn.service;

import java.util.List;

import cn.entity.Draw;
import cn.entity.PageBean;

public interface DrawService {

	List<Draw> findAll();

	void delete(Long id);

	void save(Draw entity);

	Draw getById(Long id);

	void update(Draw entity);

	List<Draw> findByDnumber(String dnumber);

	List<Draw> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
