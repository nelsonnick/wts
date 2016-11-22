package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.ReturnDao;
import cn.entity.PageBean;
import cn.entity.Returnd;
import cn.service.ReturnService;

@Service
@Transactional
public class ReturnServiceImpl implements ReturnService {

	@Resource
	private ReturnDao dao;

	public Returnd getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Returnd entity) {
		dao.save(entity);
	}

	public void update(Returnd entity) {
		dao.update(entity);
	}

	public List<Returnd> findAll() {
		return dao.findAll();
	}

	public List<Returnd> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Returnd> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
