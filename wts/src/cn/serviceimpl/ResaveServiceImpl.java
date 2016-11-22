package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.ResaveDao;
import cn.entity.PageBean;
import cn.entity.Resave;
import cn.service.ResaveService;

@Service
@Transactional
public class ResaveServiceImpl implements ResaveService {

	@Resource
	private ResaveDao dao;

	public Resave getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Resave entity) {
		dao.save(entity);
	}

	public void update(Resave entity) {
		dao.update(entity);
	}

	public List<Resave> findAll() {
		return dao.findAll();
	}

	public List<Resave> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Resave> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
