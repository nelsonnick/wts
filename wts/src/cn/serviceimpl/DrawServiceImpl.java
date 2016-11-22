package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.DrawDao;
import cn.entity.Draw;
import cn.entity.PageBean;
import cn.service.DrawService;

@Service
@Transactional
public class DrawServiceImpl implements DrawService {

	@Resource
	private DrawDao dao;

	public Draw getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Draw entity) {
		dao.save(entity);
	}

	public void update(Draw entity) {
		dao.update(entity);
	}

	public List<Draw> findAll() {
		return dao.findAll();
	}

	public List<Draw> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Draw> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
