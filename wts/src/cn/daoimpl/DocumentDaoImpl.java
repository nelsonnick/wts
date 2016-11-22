package cn.daoimpl;

import org.springframework.stereotype.Repository;

import cn.base.BaseDaoImpl;
import cn.dao.DocumentDao;
import cn.entity.Document;

@Repository
public class DocumentDaoImpl extends BaseDaoImpl<Document> implements DocumentDao {

	@Override
	public Document findByNumber(String dnumber) {
		return (Document) getSession()
				.createQuery("FROM Document d WHERE d.dnumber=?")
				.setParameter(0, dnumber).uniqueResult();
	}

	@Override
	public Document findByIDNumber(String idnumber) {
		return (Document) getSession()
				.createQuery(
						"FROM Document d WHERE d.state = '在档' AND d.pnumber = ?")
				.setParameter(0, idnumber).uniqueResult();
	}

}
