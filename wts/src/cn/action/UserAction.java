package cn.action;

import java.util.HashSet;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.base.BaseAction;
import cn.entity.PageBean;
import cn.entity.Role;
import cn.entity.User;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace(value = "/user")
public class UserAction extends BaseAction<User> {

	private static final long serialVersionUID = 4951328375371538536L;
	private String passwordCheck;
	private String passwordBefore;
	private String passwordAfter;
	private Long[] roleIds;
	

	/** 用户登录 */
	@Action(value = "user_login", results = {
			@Result(name = "user_loginUI", location = "/jsp/user/user_loginUI.jsp"),
			@Result(name = "home_index", location = "/jsp/home/home_index.jsp") })
	public String user_login() throws Exception {

		User user = userService.findByAccountAndPassword(model.getAccount(),
				model.getPassword());

		if (user == null) {
			addFieldError("login", "登录名或密码不正确！或用户处于非激活状态！");
			return "user_loginUI";
		} else {
			if (user.isLogonUser(user.getId())) {
				addFieldError("login", "重复登录！");
				return "user_loginUI";
			}
			ActionContext.getContext().getSession().put("user", user);
			return "home_index";
		}
	}

	/** 登录页面 */
	@Action(value = "user_loginUI", results = { @Result(name = "user_loginUI", location = "/jsp/user/user_loginUI.jsp") })
	public String user_loginUI() throws Exception {
		return "user_loginUI";
	}

	/** 用户注销 */
	@Action(value = "user_logout", results = {
			@Result(name = "user_logout", location = "/jsp/user/user_logoutUI.jsp")})
	public String user_logout() throws Exception {
		ActionContext.getContext().getSession().remove("user");
		return "user_logout";
	}
	
	/** 用户列表 */
	@Action(value = "user_list", results = { @Result(name = "user_list", location = "/jsp/user/user_list.jsp") })
	public String user_list() throws Exception {
		
		String s="FROM User u WHERE u.name LIKE '%" + query + "%' OR u.account LIKE '%" + query + "%'";
		
		PageBean pageBean = userService.getPageBean(pageNum, pageSize, s);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "user_list";
	}

	/** 用户添加 */
	@Action(value = "user_add", results = {
			@Result(name = "user_list", type = "redirectAction", location = "user_list"),
			@Result(name = "user_addUI", location = "/jsp/user/user_saveUI.jsp") })
	public String user_add() throws Exception {
		if (userService.findByAccount(model.getAccount()) == null) {
			model.setActive("激活");// 默认值为激活状态
			model.setPassword("123456");// 设定初始密码
			List<Role> roleList = roleService.getByIds(roleIds);
			model.setRoles(new HashSet<Role>(roleList));
			userService.save(model);
			return "user_list";
		} else {
			addFieldError("user", "登录名已存在");
			return "user_addUI";
		}
	}

	/** 用户添加页面 */
	@Action(value = "user_addUI", results = { @Result(name = "user_addUI", location = "/jsp/user/user_saveUI.jsp") })
	public String user_addUI() throws Exception {
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "user_addUI";
	}

	/** 用户修改 */
	@Action(value = "user_edit", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list"), 
			@Result(name = "user_editUI", location = "/jsp/user/user_saveUI.jsp")})
	public String user_edit() throws Exception {
		User user = userService.getById(model.getId());
		if (!user.getAccount().equals(model.getAccount())) {
			if (userService.findByAccount(model.getAccount()) == null) {
				user.setName(model.getName());
				user.setAccount(model.getAccount());
				userService.update(user);
				return "user_list";
			} else {
				addFieldError("user", "登录名已存在");
				return "user_editUI";
			}
		} else {
			user.setName(model.getName());
			user.setAccount(model.getAccount());
			userService.update(user);
			return "user_list";
		}
	}

	/** 用户修改页面 */
	@Action(value = "user_editUI", results = { @Result(name = "user_editUI", location = "/jsp/user/user_saveUI.jsp") })
	public String user_editUI() throws Exception {
		
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		
		return "user_editUI";
	}
	
	/** 用户角色修改 */
	@Action(value = "user_role", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list"), 
			@Result(name = "user_roleUI", location = "/jsp/user/user_roleUI.jsp")})
	public String user_role() throws Exception {
		User user = userService.getById(model.getId());
		List<Role> roleList = roleService.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		userService.update(user);
		return "user_list";
	}
	
	/** 用户角色修改页面 */
	@Action(value = "user_roleUI", results = { @Result(name = "user_roleUI", location = "/jsp/user/user_roleUI.jsp") })
	public String user_roleUI() throws Exception {
		
		List<Role> roleList = roleService.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		User user = userService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(user);
		if (user.getRoles() != null) {
			roleIds = new Long[user.getRoles().size()];
			int index = 0;
			for (Role role : user.getRoles()) {
				roleIds[index++] = role.getId();
			}
		}
		return "user_roleUI";
	}
	
	/** 用户删除 */
	@Action(value = "user_delete", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list") })
	public String user_delete() throws Exception {
		userService.delete(model.getId());
		return "user_list";
	}
	
	/** 用户停用 */
	@Action(value = "active_no", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list") })
	public String active_no() throws Exception {
		User user = userService.getById(model.getId());
		user.setActive("未激活");
		userService.update(user);
		return "user_list";
	}

	/** 用户启用 */
	@Action(value = "active_yes", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list") })
	public String active_yes() throws Exception {
		User user = userService.getById(model.getId());
		user.setActive("激活");
		userService.update(user);
		return "user_list";
	}

	/** 密码重置 */
	@Action(value = "user_reset", results = { @Result(name = "user_list", type = "redirectAction", location = "user_list") })
	public String user_reset() throws Exception {
		User user = userService.getById(model.getId());
		user.setPassword("123456");// 设定初始密码
		userService.update(user);
		return "user_list";
	}

	/** 密码修改 */
	@Action(value = "user_change", results = {
			@Result(name = "user_logout", type = "redirectAction", location = "user_logout"),
			@Result(name = "user_changeUI", location = "/jsp/user/user_changeUI.jsp") })
	public String user_change() throws Exception {
		
		if (passwordCheck.equals("")) {
			addFieldError("user", "登录密码不能为空");
			return "user_changeUI";
		}
		if (passwordCheck.length() < 6) {
			addFieldError("user", "登录密码不能小于六位");
			return "user_changeUI";
		}
		
		
		User user = userService.getById(getCurrentUser().getId());
		user.setPassword(passwordCheck);
		userService.update(user);
		return "user_logout";
		
	}

	/** 密码修改页面 */
	@Action(value = "user_changeUI", results = { @Result(name = "user_changeUI", location = "/jsp/user/user_changeUI.jsp") })
	public String user_changeUI() throws Exception {
		
		User user = userService.getById(getCurrentUser().getId());
		ActionContext.getContext().getValueStack().push(user);
		
		return "user_changeUI";
	}
	
	//=============================================================
	public Long[] getRoleIds() {
		return roleIds;
	}
	
	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}
	
	public String getPasswordCheck() {
		return passwordCheck;
	}
	
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}

	public String getPasswordBefore() {
		return passwordBefore;
	}

	public void setPasswordBefore(String passwordBefore) {
		this.passwordBefore = passwordBefore;
	}

	public String getPasswordAfter() {
		return passwordAfter;
	}

	public void setPasswordAfter(String passwordAfter) {
		this.passwordAfter = passwordAfter;
	}

	
	
}
