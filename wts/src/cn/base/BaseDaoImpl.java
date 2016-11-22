package cn.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import cn.entity.PageBean;


@SuppressWarnings("unchecked")
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;

	public BaseDaoImpl() {
		// 使用反得到T的真实类型
		ParameterizedType pt = (ParameterizedType) this.getClass()
				.getGenericSuperclass(); // 获取当前new的对象的 泛型的父类型
		this.clazz = (Class<T>) pt.getActualTypeArguments()[0]; // 获取第一个类型参数的真实类型
	}

	/**
	 * 获取当前可用的Session
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void save(T entity) {
		getSession().save(entity);
	}

	public void update(T entity) {
		getSession().update(entity);
	}

	public void delete(Long id) {
		Object obj = getById(id);
		if (obj != null) {
			getSession().delete(obj);
		}
	}

	public T getById(Long id) {
		return (T) getSession().get(clazz, id);
	}

	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + //
				" f ORDER BY f.id DESC ")//
				.list();
	}
	
	public List<T> findByDnumber(String dnumber) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + //
				" d WHERE d.dnumber= " + dnumber + " ORDER BY d.id DESC ")//
				.list();
	}
	
	public List<T> findByPnumber(String pnumber) {
		return getSession().createQuery(//
				"FROM " + clazz.getSimpleName() + //
				" d WHERE d.pnumber= " + pnumber + " ORDER BY d.id DESC ")//
				.list();
	}
	
	// 公共的查询分页信息的方法（最终版）
	@SuppressWarnings("rawtypes")
	public PageBean getPageBean(int pageNum, int pageSize, String hql) {
		String h="SELECT COUNT(*) "+hql;
		// 查询本页的数据列表
		List list =getSession().createQuery(hql).setFirstResult((pageNum - 1) * pageSize).setMaxResults(pageSize).list();
		// 查询总记录数量
		Long count = (Long) getSession().createQuery(h).uniqueResult(); // 执行查询

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}
	
}
