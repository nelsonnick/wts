package cn.install;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.entity.User;

@Component
public class Install_User {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();
		session.save(new User((long) 1, "超级管理员", "admin", "admin"));
		session.save(new User((long) 2, "查询用户", "cxyh", "cxyh"));
		session.save(new User((long) 3, "王天硕", "wangtianshuo", "123456"));
		session.save(new User((long) 4, "桑白", "sangbai", "123456"));
		session.save(new User((long) 5, "王燕", "wangyan", "123456"));
		session.save(new User((long) 6, "刘臻", "liuzhen", "123456"));
		session.save(new User((long) 7, "王媛", "wangyuan", "123456"));
		session.save(new User((long) 8, "钟波", "zhongbo", "123456"));
		session.save(new User((long) 9, "刘文勃", "liuwenbo", "123456"));
	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Install_User installer_User = (Install_User) ac.getBean("install_User");
		installer_User.install();
	}
}
