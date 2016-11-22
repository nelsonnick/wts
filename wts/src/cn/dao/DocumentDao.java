package cn.dao;

import cn.base.BaseDao;
import cn.entity.Document;

public interface DocumentDao extends BaseDao<Document> {

	Document findByNumber(String number);

	Document findByIDNumber(String idnumber);
}
