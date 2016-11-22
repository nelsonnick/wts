package cn.serviceimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.dao.DeleteDao;
import cn.entity.Deleted;
import cn.entity.PageBean;
import cn.service.DeleteService;

@Service
@Transactional
public class DeleteServiceImpl implements DeleteService {

	@Resource
	private DeleteDao dao;

	public Deleted getById(Long id) {
		return dao.getById(id);
	}

	public void delete(Long id) {
		dao.delete(id);
	}

	public void save(Deleted entity) {
		dao.save(entity);
	}

	public void update(Deleted entity) {
		dao.update(entity);
	}

	public List<Deleted> findAll() {
		return dao.findAll();
	}

	public List<Deleted> findByDnumber(String dnumber) {
		return dao.findByDnumber(dnumber);
	}

	public List<Deleted> findByPnumber(String pnumber) {
		return dao.findByPnumber(pnumber);
	}

	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		return dao.getPageBean(pageNum,pageSize,hql);
	}

}
