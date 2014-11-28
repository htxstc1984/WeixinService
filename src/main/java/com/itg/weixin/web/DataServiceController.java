package com.itg.weixin.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;

import com.itg.weixin.model.UserEntity;
import com.itg.weixin.service.UserService;
import com.itg.weixin.vo.MenuItemVO;
import com.itg.weixin.vo.MenuRootVO;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Controller
public class DataServiceController implements ServletContextAware {

	@Autowired(required = true)
	private UserService userService;

	private static Logger log = Logger.getLogger(DataServiceController.class);

	private ServletContext servletContext;

	public DataServiceController() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		this.servletContext = servletContext;
	}

	@RequestMapping(value = "/testJson.html")
	@ResponseBody
	public void testJson(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = ReadFile(
				servletContext.getRealPath("/json/testButton.json"))
				.replaceAll("\t", "").trim();
		response.getOutputStream().write(json.getBytes("utf-8"));
	}

	@RequestMapping(value = "/login.html")
	public ModelAndView login(HttpServletRequest request,
			HttpServletResponse response) {

		Object loginUser = request.getSession().getAttribute("loginUser");
		if (loginUser != null) {
			return new ModelAndView("admin/main");
		}

		return new ModelAndView("admin/login");
	}

	@RequestMapping(value = "/logout.html")
	public ModelAndView logout(HttpServletRequest request,
			HttpServletResponse response) {

		request.getSession().removeAttribute("loginUser");

		return new ModelAndView("admin/login");
	}

	@RequestMapping(value = "/loginAction.html")
	public ModelAndView loginAction(@RequestParam String username,
			@RequestParam String password, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		UserEntity user = userService.findByUserName(username);

		if (user == null || user.getPwd() == null
				|| !user.getPwd().equalsIgnoreCase(password)) {
			return new ModelAndView("admin/login");
		}

		request.getSession().setAttribute("loginUser", user);

		return new ModelAndView("admin/main");
	}

	@RequestMapping(value = "/userManager.html")
	public ModelAndView userManager(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("admin/userManager");
	}

	@RequestMapping(value = "/menuManager.html")
	public ModelAndView menuManager(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("admin/menuManager");
	}

	@RequestMapping(value = "/getWxUsers.html")
	@ResponseBody
	public void getWxUsers(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<UserEntity> users = userService.selectAll();

		String resultJson = new JSONSerializer().serialize(users);
		response.getOutputStream().write(resultJson.getBytes("utf-8"));
	}

	@RequestMapping(value = "/getUserByID.html")
	@ResponseBody
	public void getUserByID(@RequestParam String id,
			HttpServletResponse response) throws Exception {
		UserEntity user = userService.selectByPrimaryKey(Long.valueOf(id));
		String resultJson = new JSONSerializer().serialize(user);
		response.getOutputStream().write(resultJson.getBytes("utf-8"));
	}

	@RequestMapping(value = "/insertUser.html")
	@ResponseBody
	public void insertUser(UserEntity user, HttpServletResponse response)
			throws Exception {
		int rs = userService.insert(user);
		if (rs == 1) {
			response.getOutputStream().write(
					"{\"success\":true}".getBytes("utf-8"));
		} else {
			response.getOutputStream().write(
					"{\"success\":false}".getBytes("utf-8"));
		}

	}

	@RequestMapping(value = "/updateUser.html")
	@ResponseBody
	public void updateUser(UserEntity user, HttpServletResponse response)
			throws Exception {
		int rs = userService.update(user);
		if (rs == 1) {
			response.getOutputStream().write(
					"{\"success\":true}".getBytes("utf-8"));
		} else {
			response.getOutputStream().write(
					"{\"success\":false}".getBytes("utf-8"));
		}

	}

	// 读文件，返回字符串
	public String ReadFile(String path) {
		File file = new File(path);
		BufferedReader reader = null;
		String laststr = "";
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), "utf-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//System.out.println("line " + line + ": " + tempString);
				laststr = laststr + tempString;
				line++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		return laststr;
	}
}
