package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.BorrowDao;
import cn.entity.Borrow;
import cn.entity.PageBean;
import cn.service.BorrowService;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {

	@Resource
	private BorrowDao dao;

	public Borrow getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Borrow entity) {
		dao.save(entity);
	}

	public void update(Borrow entity) {
		dao.update(entity);
	}

	public List<Borrow> findAll() {
		return dao.findAll();
	}

	public List<Borrow> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Borrow> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
