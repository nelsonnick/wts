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
import cn.entity.Privilege;
import cn.entity.Role;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace(value = "/role")
public class RoleAction extends BaseAction<Role> {

	private static final long serialVersionUID = 4951328375371538536L;
	private Long[] privilegeIds;

	/** 角色列表 */
	@Action(value = "role_list", results = { @Result(name = "role_list", location = "/jsp/role/role_list.jsp") })
	public String role_list() throws Exception {
	
		String s="FROM Role r WHERE r.name LIKE '%" + query + "%' OR r.description LIKE '%" + query + "%'";
		PageBean pageBean = roleService.getPageBean(pageNum, pageSize, s);
		ActionContext.getContext().getValueStack().push(pageBean);
		
		return "role_list";
	}
	
	/** 角色添加 */
	@Action(value = "role_add", results = { @Result(name = "role_list", type = "redirectAction", location = "role_list") })
	public String role_add() throws Exception {
		roleService.save(model);
		return "role_list";
	}
	
	/** 角色添加页面 */
	@Action(value = "role_addUI", results = { @Result(name = "role_addUI", location = "/jsp/role/role_saveUI.jsp") })
	public String role_addUI() throws Exception {
		return "role_addUI";	
	}
	
	/** 角色修改 */
	@Action(value = "role_edit", results = { @Result(name = "role_list", type = "redirectAction", location = "role_list") })
	public String role_edit() throws Exception {
		Role role = roleService.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		roleService.update(role);
		return "role_list";
	}
	
	/** 角色修改页面 */
	@Action(value = "role_editUI", results = { @Result(name = "role_editUI", location = "/jsp/role/role_saveUI.jsp") })
	public String role_editUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		return "role_editUI";
	}
	
	/** 角色删除 */
	@Action(value = "role_delete", results = { @Result(name = "role_list", type = "redirectAction", location = "role_list") })
	public String role_delete() throws Exception {
		roleService.delete(model.getId());
		return "role_list";
	}
	
	/** 设置权限页面 */
	@Action(value = "role_privilegeUI", results = { @Result(name = "role_privilegeUI", location = "/jsp/role/role_privilegeUI.jsp")})
	public String role_privilegeUI() throws Exception {
		Role role = roleService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);
		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}
		List<Privilege> privilegeList = privilegeService.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList);
		return "role_privilegeUI";
	}

	/** 设置权限 */
	@Action(value = "role_privilege", results = { @Result(name = "role_list", type = "redirectAction", location = "role_list") })
	public String role_privilege() throws Exception {
		Role role = roleService.getById(model.getId());
		List<Privilege> privilegeList = privilegeService.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));
		roleService.update(role);
		return "role_list";
	}
	
	//========================================================== 
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}
	
	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}
}
