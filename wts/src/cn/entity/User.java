package cn.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;

import cn.util.SessionListener;

import com.opensymphony.xwork2.ActionContext;

@Entity
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -4247827583460090912L;

	private Long id;
	private String name;// 真实姓名
	private String account;// 账户名
	private String password;// 密码
	private String active;// 状态（是否可以使用）
	private Set<Role> roles = new HashSet<Role>();
	private Set<Returnd> backs = new HashSet<Returnd>();
	private Set<Borrow> borrows = new HashSet<Borrow>();
	private Set<Draw> draws = new HashSet<Draw>();
	private Set<Deleted> deletes = new HashSet<Deleted>();
	private Set<Resave> resaves = new HashSet<Resave>();
	
	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@OneToMany(mappedBy = "user")
	public Set<Deleted> getDeletes() {
		return deletes;
	}

	public void setDeletes(Set<Deleted> deletes) {
		this.deletes = deletes;
	}

	@OneToMany(mappedBy = "user")
	public Set<Returnd> getBacks() {
		return backs;
	}

	public void setBacks(Set<Returnd> backs) {
		this.backs = backs;
	}

	@OneToMany(mappedBy = "user")
	public Set<Borrow> getBorrows() {
		return borrows;
	}

	public void setBorrows(Set<Borrow> borrows) {
		this.borrows = borrows;
	}

	@OneToMany(mappedBy = "user")
	public Set<Draw> getDraws() {
		return draws;
	}

	public void setDraws(Set<Draw> draws) {
		this.draws = draws;
	}

	@OneToMany(mappedBy = "user")
	public Set<Resave> getResaves() {
		return resaves;
	}

	public void setResaves(Set<Resave> resaves) {
		this.resaves = resaves;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User() {

	}

	public User(Long id, String name, String account, String password) {
		this.id = id;
		this.name = name;
		this.account = account;
		this.password = password;
		this.active ="激活";
	}

	public String getAccount() {
		return account;
	}

	

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "role_user", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	public Set<Role> getRoles() {
		return roles;
	}

	// ====================================================
	/**
	 * 判断本用户是否有指定名称的权限
	 * 
	 */
	@Transient
	public boolean hasPrivilegeByName(String name) {
		// 超级管理有所有的权限
		if (isAdmin()) {
			return true;
		}

		// 普通用户要判断是否含有这个权限
		for (Role role : roles) {
			for (Privilege priv : role.getPrivileges()) {
				if (priv.getName().equals(name)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断本用户是否有指定URL的权限
	 * 
	 */
	@Transient
	public boolean hasPrivilegeByUrl(String privUrl) {
		// 超级管理有所有的权限
		if (isAdmin()) {
			return true;
		}

		// >> 去掉后面的参数
		int pos = privUrl.indexOf("?");
		if (pos > -1) {
			privUrl = privUrl.substring(0, pos);
		}
		// >> 去掉UI后缀
		if (privUrl.endsWith("UI")) {
			privUrl = privUrl.substring(0, privUrl.length() - 2);
		}

		// 如果本URL不需要控制，则登录用户就可以使用
		@SuppressWarnings("unchecked")
		Collection<String> allPrivilegeUrls = (Collection<String>) ActionContext
				.getContext().getApplication().get("allPrivilegeUrls");
		if (!allPrivilegeUrls.contains(privUrl)) {
			return true;
		} else {
			// 普通用户要判断是否含有这个权限
			for (Role role : roles) {
				for (Privilege priv : role.getPrivileges()) {
					if (privUrl.equals(priv.getUrl())) {
						return true;
					}
				}
			}
			return false;
		}
	}

	/**
	 * 判断本用户是否是超级管理员
	 * 
	 */
	@Transient
	public boolean isAdmin() {
		return "admin".equals(account);
	}

	public void setAccount(String account) {
		this.account = account;
	}


	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public boolean isLogonUser(Long userId) {
		Set<HttpSession> keys = SessionListener.loginUser.keySet();
		for (HttpSession key : keys) {
			if (SessionListener.loginUser.get(key).equals(userId)) {
				return true;
			}
		}
		return false;
	}

}
