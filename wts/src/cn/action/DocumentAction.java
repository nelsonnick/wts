package cn.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.base.BaseAction;
import cn.entity.Borrow;
import cn.entity.Deleted;
import cn.entity.Document;
import cn.entity.Draw;
import cn.entity.PageBean;
import cn.entity.Resave;
import cn.entity.Returnd;
import cn.entity.Save;

import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace(value = "/document")
public class DocumentAction extends BaseAction<Document> {

	private static final long serialVersionUID = -7522005542954046103L;

	private String cname;
	
	/** 档案列表*/
	@Action(value = "document_list", results = {
			@Result(name = "document_list", location = "/jsp/document/document_list.jsp") })
	public String document_list() throws Exception {
		String hql = "FROM Document d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = documentService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_list";
	}

	/** 提档列表*/
	@Action(value = "document_draw_list", results = {
			@Result(name = "document_draw_list", location = "/jsp/document/document_draw_list.jsp") })
	public String document_draw_list() throws Exception {
		String hql = "FROM Draw d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = drawService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_draw_list";
	}
	
	/** 重存列表*/
	@Action(value = "document_resave_list", results = {
			@Result(name = "document_resave_list", location = "/jsp/document/document_resave_list.jsp") })
	public String document_resave_list() throws Exception {
		String hql = "FROM Resave d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = resaveService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_resave_list";
	}
	
	/** 还档列表*/
	@Action(value = "document_return_list", results = {
			@Result(name = "document_return_list", location = "/jsp/document/document_return_list.jsp") })
	public String document_return_list() throws Exception {
		String hql = "FROM Returnd d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = returnService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_return_list";
	}
	
	/** 存档列表*/
	@Action(value = "document_save_list", results = {
			@Result(name = "document_save_list", location = "/jsp/document/document_save_list.jsp") })
	public String document_save_list() throws Exception {
		String hql = "FROM Save d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = saveService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_save_list";
	}
	
	/** 借档列表*/
	@Action(value = "document_borrow_list", results = {
			@Result(name = "document_borrow_list", location = "/jsp/document/document_borrow_list.jsp") })
	public String document_borrow_list() throws Exception {
		String hql = "FROM Borrow d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = borrowService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_borrow_list";
	}
	
	/** 删档列表*/
	@Action(value = "document_delete_list", results = {
			@Result(name = "document_delete_list", location = "/jsp/document/document_delete_list.jsp") })
	public String document_delete_list() throws Exception {
		String hql = "FROM Deleted d WHERE "//
				+ "    d.dnumber LIKE '%" + query + "%'"//
				+ " OR d.pnumber LIKE '%" + query + "%'"//
				+ " OR d.name LIKE '%" + query + "%'"//
				+ " ORDER BY d.time DESC";//
		PageBean pageBean = deleteService.getPageBean(pageNum, pageSize, hql);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "document_delete_list";
	}
	
	/** 档案提取 */
	@Action(value = "document_draw", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "error", location = "/jsp/public/error.jsp") })
	public String document_draw() throws Exception {

		Document document = documentService.getById(model.getId());

		if (document.getState().equals("在档")) {
			Draw ddraw = new Draw();
			ddraw.setDnumber(document.getDnumber());
			ddraw.setPnumber(document.getPnumber());
			ddraw.setName(document.getName());
			ddraw.setTime(new java.util.Date());
			ddraw.setCompany(cname);
			ddraw.setUser(getCurrentUser().getName());
			drawService.save(ddraw);// 保存档案变更信息

			document.setState("提档");
			documentService.update(document);

			return "document_list";
		} else {
			addFieldError("Error", "该人员无法办理提档");
			return "error";
		}

	}

	/** 档案出借 */
	@Action(value = "document_borrow", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "error", location = "/jsp/public/error.jsp") })
	public String document_borrow() throws Exception {

		Document document = documentService.getById(model.getId());
		System.out.print(document.getState());
		if (document.getState().equals("在档")) {
			Borrow borrow = new Borrow();
			borrow.setDnumber(document.getDnumber());
			borrow.setPnumber(document.getPnumber());
			borrow.setName(document.getName());
			borrow.setTime(new java.util.Date());
			borrow.setCompany(cname);
			borrow.setUser(getCurrentUser().getName());
			borrowService.save(borrow);// 保存档案变更信息

			document.setState("借档");
			documentService.update(document);

			return "document_list";
		} else {
			addFieldError("Error", "该人员无法办理借档");
			return "error";
		}

	}

	/** 档案归还 */
	@Action(value = "document_return", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "error", location = "/jsp/public/error.jsp") })
	public String document_return() throws Exception {

		Document document = documentService.getById(model.getId());

		if (document.getState() .equals("借档")) {
			Returnd dreturn = new Returnd();
			dreturn.setDnumber(document.getDnumber());
			dreturn.setPnumber(document.getPnumber());
			dreturn.setName(document.getName());
			dreturn.setTime(new java.util.Date());
			dreturn.setUser(getCurrentUser().getName());
			returnService.save(dreturn);// 保存档案变更信息

			document.setState("在档");
			documentService.update(document);

			return "document_list";
		} else {
			addFieldError("Error", "该人员档案已在档");
			return "error";
		}

	}

	/** 提档重存*/
	@Action(value = "document_resave", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "error", location = "/jsp/public/error.jsp") })
	public String document_resave() throws Exception {

		Document document = documentService.getById(model.getId());

		if (!document.getState().equals("在档")) {
			Resave dsave = new Resave();
			dsave.setDnumber(document.getDnumber());
			dsave.setPnumber(document.getPnumber());
			dsave.setName(document.getName());
			dsave.setTime(new java.util.Date());
			dsave.setUser(getCurrentUser().getName());
			resaveService.save(dsave);// 保存档案变更信息

			document.setState("在档");
			documentService.update(document);

			return "document_list";
		} else {
			addFieldError("Error", "该人员档案已在档");
			return "error";
		}

	}
	
	/** 新增档案 */
	@Action(value = "document_add", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "document_addUI", location = "/jsp/document/document_addUI.jsp") })
	public String document_add() throws Exception {

		Document document = new Document();
		if (documentService.findByNumber(model.getDnumber()) != null) {
			addFieldError("Number", "该档案编号已存在！");
			return "person_addUI";
		}
		if (!document.availableDnumber(model.getDnumber())) {
			addFieldError("Number", "档案编号错误！");
			return "person_addUI";
		}
		
		if (documentService.findByIDNumber(model.getPnumber()) != null) {
			addFieldError("Number", "该证件号码已存在一份在档档案！");
			return "person_addUI";
		}
		
		if (!document.availablePnumber(model.getPnumber())) {
			addFieldError("Number", "身份证号码错误！");
			return "person_addUI";
		}
		
		if (!(model.getPhone().matches("\\d{11}") || model
				.getPhone().equals(""))) {
			addFieldError("Phone", "联系电话错误！");
			return "person_addUI";
		}
		
		document.setDnumber(model.getDnumber());
		document.setPnumber(model.getPnumber());
		document.setName(model.getName());
		document.setAddress(model.getAddress());
		document.setPhone(model.getPhone());
		document.setRemark(model.getRemark());
		document.setPlace("职介中心");
		document.setState("在档");
		document.setTime(new java.util.Date());
		
		documentService.save(document);

		
		Save save = new Save();
		save.setDnumber(model.getDnumber());
		save.setPnumber(model.getPnumber());
		save.setName(model.getName());
		save.setUser(getCurrentUser().getName());
		save.setTime(new java.util.Date());
		saveService.save(save);

		return "document_list";

	}

	/** 档案修改 */
	@Action(value = "document_edit", results = {
			@Result(name = "document_list", type = "redirectAction", location = "document_list"),
			@Result(name = "error", location = "/jsp/public/error.jsp") })
	public String document_edit() throws Exception {
		
		Document document = documentService.getById(model.getId());
		
		document.setPnumber(model.getPnumber());
		document.setDnumber(model.getDnumber());
		document.setPhone(model.getPhone());
		document.setAddress(model.getAddress());
		document.setName(model.getName());
		document.setRemark(model.getRemark());
		
		documentService.update(document);
		return "document_list";
				
	}

	/** 档案删除 */
	@Action(value = "document_delete", results = { @Result(name = "document_list", type = "redirectAction", location = "document_list") })
	public String document_delete() throws Exception {
		if (documentService.getById(model.getId()) != null) {
			
			Document document = documentService.getById(model.getId());
			
			Deleted dsave = new Deleted();
			dsave.setDnumber(document.getDnumber());
			dsave.setPnumber(document.getPnumber());
			dsave.setName(document.getName());
			dsave.setTime(new java.util.Date());
			dsave.setUser(getCurrentUser().getName());
			deleteService.save(dsave);// 保存档案变更信息

			documentService.delete(model.getId());
			return "document_list";
		} else {
			return "document_list";
		}
	}

	/** 借档页面 */
	@Action(value = "document_borrowUI", results = { @Result(name = "document_borrowUI", location = "/jsp/document/document_borrowUI.jsp") })
	public String document_borrowUI() throws Exception {
		Document document = documentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(document);
		return "document_borrowUI";
	}
	
	/** 档案页面 */
	@Action(value = "document_editUI", results = { @Result(name = "document_editUI", location = "/jsp/document/document_editUI.jsp") })
	public String document_editUI() throws Exception {
		Document document = documentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(document);
		return "document_editUI";
	}
	
	/** 新增页面 */
	@Action(value = "document_addUI", results = { @Result(name = "document_addUI", location = "/jsp/document/document_addUI.jsp") })
	public String document_addUI() throws Exception {
		return "document_addUI";
	}
	
	/** 提档页面 */
	@Action(value = "document_drawUI", results = { @Result(name = "document_drawUI", location = "/jsp/document/document_drawUI.jsp") })
	public String document_drawUI() throws Exception {
		Document document = documentService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(document);
		return "document_drawUI";
	}
		
	
	//================================================================

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
