package cn.service;

import java.util.List;

import cn.entity.PageBean;
import cn.entity.Save;

public interface SaveService {

	List<Save> findAll();

	void delete(Long id);

	void save(Save entity);

	Save getById(Long id);

	void update(Save entity);

	List<Save> findByDnumber(String dnumber);

	List<Save> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
