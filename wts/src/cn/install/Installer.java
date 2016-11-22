package cn.install;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Installer {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

		/** 数据——用户权限*/
		Install_Privilege installer_Privilege = (Install_Privilege) ac.getBean("install_Privilege");
		installer_Privilege.install();
		
		/** 数据——管理员*/
		Install_User installer_User = (Install_User) ac.getBean("install_User");
		installer_User.install();

	}
}
