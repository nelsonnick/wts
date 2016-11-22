package cn.service;

import java.util.List;

import cn.entity.Document;
import cn.entity.PageBean;

public interface DocumentService {

	List<Document> findAll();

	void delete(Long id);

	void save(Document entity);

	Document getById(Long id);

	void update(Document entity);

	List<Document> findByDnumber(String dnumber);

	List<Document> findByPnumber(String pnumber);
	
	Document findByNumber(String number);

	Document findByIDNumber(String idnumber);

	PageBean getPageBean(int pageNum, int pageSize, String hql);

}
