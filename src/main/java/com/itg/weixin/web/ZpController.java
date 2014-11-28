package com.itg.weixin.web;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.weixin.vo.ResumeVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Controller
public class ZpController {

	@Autowired(required = true)
	@Qualifier("weixinDataSource")
	private ComboPooledDataSource wxds;

	public ZpController() {
		// TODO Auto-generated constructor stub
	}

	@RequestMapping(value = "/demo.html")
	public ModelAndView getDemo(HttpServletRequest request,
			HttpServletResponse response) {
		String modid = request.getParameter("modid");

		ModelAndView mav = new ModelAndView("zp/zhaopin");

		mav.addObject("modid", modid);

		return mav;
	}
	
	@RequestMapping(value = "/testPic.html")
	public ModelAndView testPic(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("zp/test");
		return mav;
	}
	
	@RequestMapping(value = "/business.html")
	public ModelAndView business(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("business");
		return mav;
	}
	
	@RequestMapping(value = "/demo2.html")
	public ModelAndView getDemo2(HttpServletRequest request,
			HttpServletResponse response) {
		String modid = request.getParameter("modid");

		ModelAndView mav = new ModelAndView("zp/zhaopin");

		mav.addObject("modid", modid);

		return mav;
	}
	
	@RequestMapping(value = "/imageView.html")
	public ModelAndView imageView(HttpServletRequest request,
			HttpServletResponse response) {
		String src = request.getParameter("src");
		if (src == null || src.equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "无法识别的路径");
			return model;
		}
		
		
		ModelAndView mav = new ModelAndView("imageView");

		mav.addObject("src", src);

		return mav;
	}

	@RequestMapping(value = "/schedule.html")
	public ModelAndView getSchedule(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("zp/schedule");
	}
	
	@RequestMapping(value = "/develop.html")
	public ModelAndView getDevelop(HttpServletRequest request,
			HttpServletResponse response) {

		return new ModelAndView("zp/develop");
	}

	@RequestMapping(value = "/getResume.html")
	public ModelAndView getResume(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");
		if (openid == null || openid.equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "无法识别的微信号");
			return model;
		}
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);

			PreparedStatement stmt1 = dbConn
					.prepareStatement("select * from itg_weixin_zp_resume where openid=?");
			stmt1.setString(1, openid);
			ResultSet rs = stmt1.executeQuery();
			ResumeVO resume = new ResumeVO();
			resume.setOpenid(openid);
			while (rs.next()) {
				resume.setName(rs.getString("name"));
				resume.setSex(rs.getString("sex"));
				resume.setEducation(rs.getString("education"));
				resume.setSchool(rs.getString("school"));
				resume.setPro(rs.getString("pro"));
				resume.setHome(rs.getString("home"));
				resume.setPhone(rs.getString("phone"));
				resume.setDescr(rs.getString("descr"));
			}

			dbConn.commit();

			dbConn.close();
			ModelAndView model = new ModelAndView("zp/resume");
			model.addObject("resume", resume);
			return model;
		} catch (Exception e) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "发生错误");
			return model;
		}

	}

	@RequestMapping(value = "/subResume.html")
	@ResponseBody
	public void subResume(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String openid = request.getParameter("openid");

		String name = request.getParameter("name");
		String sex = request.getParameter("sex");
		String education = request.getParameter("education");
		String school = request.getParameter("school");
		String pro = request.getParameter("pro");
		String home = request.getParameter("home");
		String phone = request.getParameter("phone");
		String descr = request.getParameter("descr");

		if (openid == null || openid.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"无法识别的微信号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (name == null || name.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写姓名!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (sex == null || sex.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写性别!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (education == null || education.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写学历!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (school == null || school.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写学校!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (pro == null || pro.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写学位!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (home == null || home.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写家庭所在地!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (phone == null || phone.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写经历!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (descr == null || descr.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请填写姓名!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		Connection dbConn;
		boolean existflag = false;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);

			PreparedStatement stmt1 = dbConn
					.prepareStatement("select * from itg_weixin_zp_resume where openid=?");
			stmt1.setString(1, openid);
			ResultSet rs = stmt1.executeQuery();
			while (rs.next()) {
				existflag = true;
				PreparedStatement stmt_update = dbConn
						.prepareStatement("update itg_weixin_zp_resume set name=? ,sex=? , education=? , school=? , pro=? ,home=? ,phone=? ,descr=? where openid=?");
				stmt_update.setString(1, name);
				stmt_update.setString(2, sex);
				stmt_update.setString(3, education);
				stmt_update.setString(4, school);
				stmt_update.setString(5, pro);
				stmt_update.setString(6, home);
				stmt_update.setString(7, phone);
				stmt_update.setString(8, descr);
				stmt_update.setString(9, openid);
				stmt_update.executeUpdate();
			}

			if (!existflag) {
				PreparedStatement stmt_insert = dbConn
						.prepareStatement("insert into itg_weixin_zp_resume (openid,name,sex,education,school,pro,home,phone,descr) values (?,?,?,?,?,?,?,?,?)");
				stmt_insert.setString(1, openid);
				stmt_insert.setString(2, name);
				stmt_insert.setString(3, sex);
				stmt_insert.setString(4, education);
				stmt_insert.setString(5, school);
				stmt_insert.setString(6, pro);
				stmt_insert.setString(7, home);
				stmt_insert.setString(8, phone);
				stmt_insert.setString(9, descr);
				stmt_insert.execute();
			}

			dbConn.commit();

			dbConn.close();
			String jsonStr = "{\"code\":0,\"msg\":\"成功提交简历!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (Exception e) {
			String jsonStr = "{\"code\":999,\"msg\":\"提交失败!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

	}

}
