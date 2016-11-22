package cn.action;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.base.BaseAction;
import cn.entity.Document;

@Controller
@Scope("prototype")
@Namespace(value = "/check")
public class CheckAction extends BaseAction<Document>{

	private static final long serialVersionUID = -727602926855590057L;

	/** 检查身份证号*/
	@Action(value = "checkPnumber")
	public String checkPnumber() throws Exception {
		String pnumber=ServletActionContext.getRequest().getParameter("pnumber");
		Document d = new Document();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		if(pnumber == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if (documentService.findByIDNumber(pnumber) != null) {
			result = " 该证件号码已有一份档案在档";;
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if (documentService.findByNumber(pnumber) != null) {
			result = " 该证件号码已存在！";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		
		else if (!d.availablePnumber(pnumber)) {
			result = " 身份证号码错误！";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查人员姓名*/
	@Action(value = "checkPname")
	public String checkPname() throws Exception {
		String pname=ServletActionContext.getRequest().getParameter("pname");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		if(pname == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if (pname.length()>6 || pname.length()<2) {
			result = " 姓名应在2~6个之间";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查用户姓名*/
	@Action(value = "checkUname")
	public String checkUname() throws Exception {
		String uname=ServletActionContext.getRequest().getParameter("uname");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		if(uname == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if (uname.length()>6 || uname.length()<2) {
			result = " 姓名应在2~6个之间";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查电话号码*/
	@Action(value = "checkPhone")
	public String checkPphone() throws Exception {
		String phone=ServletActionContext.getRequest().getParameter("phone");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		if(phone == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if (!phone.matches("\\d{11}")) {
			result = " 联系电话应为11位数字";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查档案编号*/
	@Action(value = "checkDnumber")
	public String checkDnumber() throws Exception {
		String dnumber=ServletActionContext.getRequest().getParameter("dnumber");
		Document d = new Document();
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		if(dnumber == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		
		else if (documentService.findByNumber(dnumber) != null) {
			result = " *该档案编号已存在！";
			ServletActionContext.getResponse().getWriter().print(result);
	
		}
//		else if (!d.availableDnumber(dnumber)) {
//			result = " 档案编号错误！";
//			ServletActionContext.getResponse().getWriter().print(result);
//		}
		
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查当前用户账户*/
	@Action(value = "checkAccountBefore")
	public String checkAccountBefore() throws Exception {
		String accountBefore=ServletActionContext.getRequest().getParameter("accountBefore");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		if(accountBefore == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if(!getCurrentUser().getAccount().equals(accountBefore)){
			result = " 登录名与当前用户不一致";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 正确";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查之后用户账户*/
	@Action(value = "checkAccountAfter")
	public String checkAccountAfter() throws Exception {
		String accountAfter=ServletActionContext.getRequest().getParameter("accountAfter");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		if(accountAfter == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if(accountAfter.length() < 4 || accountAfter.length() > 18){
			result = " 登录名应在4~18个字符之内";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if(userService.findByAccount(accountAfter) != null){
			result = " 该登录名已使用";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 正确";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查当前用户密码*/
	@Action(value = "checkPasswordBefore")
	public String checkPasswordBefore() throws Exception {
		String passwordBefore=ServletActionContext.getRequest().getParameter("passwordBefore");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		if(passwordBefore == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if(!passwordBefore.equals(getCurrentUser().getPassword())){
			result = " 登录密码与当前用户不一致";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 正确";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	/** 检查之后用户密码*/
	@Action(value = "checkPasswordAfter")
	public String checkPasswordAfter() throws Exception {
		String passwordAfter=ServletActionContext.getRequest().getParameter("passwordAfter");
		ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		
		if(passwordAfter == ""){
			result = " *必填";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else if(passwordAfter.length() < 6 || passwordAfter.length() > 18){
			result = " 登录密码应在6~18个字符之内";
			ServletActionContext.getResponse().getWriter().print(result);
		}
		else{
			result = " 可以使用！";
			ServletActionContext.getResponse().getWriter().print(result);}
		return null;
	}
	
	
}