package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.SaveDao;
import cn.entity.PageBean;
import cn.entity.Save;
import cn.service.SaveService;

@Service
@Transactional
public class SaveServiceImpl implements SaveService {

	@Resource
	private SaveDao dao;

	public Save getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Save entity) {
		dao.save(entity);
	}

	public void update(Save entity) {
		dao.update(entity);
	}

	public List<Save> findAll() {
		return dao.findAll();
	}

	public List<Save> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Save> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
