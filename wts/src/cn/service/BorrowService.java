package cn.service;

import java.util.List;

import cn.entity.Borrow;
import cn.entity.PageBean;

public interface BorrowService {

	List<Borrow> findAll();

	void delete(Long id);

	void save(Borrow entity);

	Borrow getById(Long id);

	void update(Borrow entity);

	List<Borrow> findByDnumber(String dnumber);

	List<Borrow> findByPnumber(String pnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
