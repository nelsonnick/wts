package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.DocumentDao;
import cn.entity.Document;
import cn.entity.PageBean;
import cn.service.DocumentService;

@Service
@Transactional
public class DocumentServiceImpl implements DocumentService {

	@Resource
	private DocumentDao dao;

	public Document getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Document entity) {
		dao.save(entity);
	}

	public void update(Document entity) {
		dao.update(entity);
	}

	public List<Document> findAll() {
		return dao.findAll();
	}

	public List<Document> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Document> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}
	
	@Override
	public Document findByNumber(String number) {
		return dao.findByNumber(number);
	}

	@Override
	public Document findByIDNumber(String idnumber) {
		return dao.findByIDNumber(idnumber);
	}
	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
