package cn.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
@ParentPackage("default")
@Namespace(value = "/")
public class HomeAction extends ActionSupport {

	private static final long serialVersionUID = 8667651625865700434L;

	@Action(value = "home_index", results = { @Result(name = "home_index", location = "/jsp/home/home_index.jsp") })
	public String home_index() throws Exception {
		return "home_index";
	}

	@Action(value = "home_top", results = { @Result(name = "home_top", location = "/jsp/home/home_top.jsp") })
	public String home_top() throws Exception {
		return "home_top";
	}

	@Action(value = "home_bottom", results = { @Result(name = "home_bottom", location = "/jsp/home/home_bottom.jsp") })
	public String home_bottom() throws Exception {
		return "home_bottom";
	}

	@Action(value = "home_left", results = { @Result(name = "home_left", location = "/jsp/home/home_left.jsp") })
	public String home_left() throws Exception {
		return "home_left";
	}

	@Action(value = "home_right", results = { @Result(name = "home_right", location = "/jsp/home/home_right.jsp") })
	public String home_right() throws Exception {
		return "home_right";
	}
}
