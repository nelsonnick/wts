package cn.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import cn.entity.User;
import cn.service.BorrowService;
import cn.service.DeleteService;
import cn.service.DocumentService;
import cn.service.DrawService;
import cn.service.PrivilegeService;
import cn.service.ResaveService;
import cn.service.ReturnService;
import cn.service.RoleService;
import cn.service.SaveService;
import cn.service.UserService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements
		ModelDriven<T> {

	private static final long serialVersionUID = 607100333608090252L;

	@SuppressWarnings("unchecked")
	public BaseAction() {
		try {
			ParameterizedType pt = (ParameterizedType) this.getClass()
					.getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}
	
	/** 获取当前登录的用户 */
	protected User getCurrentUser() {
		return (User) ActionContext.getContext().getSession().get("user");
	}
	
	// =============== ModelDriven的支持 ==================
	protected T model;

	// =============== Service实例的声明 ==================
	@Resource
	protected RoleService roleService;
	@Resource
	protected UserService userService;
	@Resource
	protected DocumentService documentService;
	@Resource
	protected BorrowService borrowService;
	@Resource
	protected DeleteService deleteService;
	@Resource
	protected DrawService drawService;
	@Resource
	protected ResaveService resaveService;
	@Resource
	protected ReturnService returnService;
	@Resource
	protected SaveService saveService;
	@Resource
	protected PrivilegeService privilegeService;

	
	// ============== 分页用的参数 =============

	protected int pageNum = 1; // 当前页
	protected int pageSize = 10; // 每页显示多少条记录
	protected String query = ""; // 查询条件
	protected String result = ""; // Ajax返回值
	
	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
}
