package cn.install;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.entity.Privilege;

@Component
public class Install_Privilege {

	@Resource
	private SessionFactory sessionFactory;

	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		/** 档案管理菜单 */
		Privilege document = new Privilege("档案管理", null, null);
		session.save(document);
		
		Privilege documentList = new Privilege("档案列表", "/document/document_list", document);
		session.save(documentList);
		session.save(new Privilege("新建档案——操作", "/document/document_add", documentList));
		session.save(new Privilege("新建档案——页面", "/document/document_addUI", documentList));
		session.save(new Privilege("档案修改——操作", "/document/document_edit", documentList));
		session.save(new Privilege("档案修改——页面", "/document/document_editUI", documentList));
		session.save(new Privilege("档案出借——操作", "/document/document_borrow", documentList));
		session.save(new Privilege("档案出借——页面", "/document/document_borrowUI", documentList));
		session.save(new Privilege("档案提取——操作", "/document/document_draw", documentList));
		session.save(new Privilege("档案提取——页面", "/document/document_drawUI", documentList));
		session.save(new Privilege("档案归还——操作", "/document/document_return", documentList));
		session.save(new Privilege("提档重存——操作", "/document/document_resave", documentList));
		session.save(new Privilege("档案删除——操作", "/document/document_delete", documentList));

		
		/** 记录查阅菜单 */
		Privilege info = new Privilege("记录查阅", null, null);
		session.save(info);
		
		Privilege documentBorrow = new Privilege("借档记录", "/document/document_borrow_list", info);
		session.save(documentBorrow);
		
		Privilege documentDelete = new Privilege("删档记录", "/document/document_delete_list", info);
		session.save(documentDelete);
		
		Privilege documentDraw = new Privilege("提档记录", "/document/document_draw_list", info);
		session.save(documentDraw);
		
		Privilege documentResave = new Privilege("重存记录", "/document/document_resave_list", info);
		session.save(documentResave);
		
		Privilege documentReturn = new Privilege("还档记录", "/document/document_return_list", info);
		session.save(documentReturn);
		
		Privilege documentSave = new Privilege("存档记录", "/document/document_save_list", info);
		session.save(documentSave);
		

		/** 系统管理菜单 */
		Privilege system = new Privilege("系统管理", null, null);
		session.save(system);

		Privilege user = new Privilege("用户管理", "/user/user_list", system);
		session.save(user);

		session.save(new Privilege("用户新增——操作", "/user/user_add", user));
		session.save(new Privilege("用户新增——页面", "/user/user_addUI", user));
		session.save(new Privilege("用户修改——操作", "/user/user_edit", user));
		session.save(new Privilege("用户修改——页面", "/user/user_editUI", user));
		session.save(new Privilege("用户激活——操作", "/user/active_yes", user));
		session.save(new Privilege("用户停用——操作", "/user/active_no", user));
		session.save(new Privilege("用户删除——操作", "/user/user_delete", user));
		session.save(new Privilege("密码重置——操作", "/user/user_reset", user));
		session.save(new Privilege("用户名及密码修改——操作", "/user/user_change", user));
		session.save(new Privilege("用户名及密码修改——页面", "/user/user_changeUI", user));
		
		Privilege role = new Privilege("角色管理", "/role/role_list", system);
		session.save(role);

		session.save(new Privilege("角色新增——操作", "/role/role_add", role));
		session.save(new Privilege("角色新增——页面", "/role/role_addUI", role));
		session.save(new Privilege("角色修改——操作", "/role/role_edit", role));
		session.save(new Privilege("角色修改——页面", "/role/role_editUI", role));
		session.save(new Privilege("设置权限——操作", "/role/role_privilege", role));
		session.save(new Privilege("设置权限——页面", "/role/role_privilegeUI", role));
		session.save(new Privilege("角色删除——操作", "/role/role_delete", role));

	}

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				"applicationContext.xml");
		Install_Privilege installer_Privilege = (Install_Privilege) ac
				.getBean("install_Privilege");
		installer_Privilege.install();
	}
}
