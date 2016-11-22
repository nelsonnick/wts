package cn.base;

import java.util.List;

import cn.entity.PageBean;

public interface BaseDao<T> {

	void save(T entity);

	void delete(Long id);

	void update(T entity);

	T getById(Long id);

	List<T> getByIds(Long[] ids);

	List<T> findAll();
	
	List<T> findByDnumber(String dnumber);
	
	List<T> findByPnumber(String pnumber);
	
	public PageBean getPageBean(int pageNum, int pageSize, String hql) ;
}
