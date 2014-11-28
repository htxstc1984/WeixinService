package com.itg.weixin.web;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import nc.ws.intf.IPsnService;
import nc.ws.intf.IPsnServicePortType;
import nc.ws.vo.PsnInfoVO;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.itg.weixin.model.UserEntity;
import com.itg.weixin.service.UserService;
import com.itg.weixin.vo.MessageVO;
import com.itg.weixin.vo.NewsVO;
import com.itg.weixin.vo.Slogan;
import com.itg.weixin.vo.VoteItemVO;
import com.itg.weixin.vo.VotedVO;
import com.itg.weixin.vo.WeixinMsgVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryOperators;

import flexjson.JSONSerializer;

@Controller
public class WeixinControll implements ServletContextAware {

	private static Properties operationMap;
	private static Properties operationNewMap;
	private static Properties operationAuMap;
	private static Properties errorMap;
	private static Properties eTypeMap;
	private static Properties regMap;
	private static Properties disMap;
	private static Properties urlMap;
	private static Properties picMap;
	private static Properties grpMap;
	private static Properties termMap;

	private Map auMap;

	@Autowired(required = true)
	@Qualifier("cmsDataSource")
	private ComboPooledDataSource cmsds;

	@Autowired(required = true)
	@Qualifier("weixinDataSource")
	private ComboPooledDataSource wxds;

	@Autowired(required = true)
	@Qualifier("mmsDataSource")
	private ComboPooledDataSource mmsds;

	@Autowired(required = true)
	private UserService userService;

	@Autowired(required = true)
	private MongoTemplate mongoTemplate;

	private static Logger log = Logger.getLogger(WeixinControll.class);

	/** setvlet�����Ķ��� **/
	private ServletContext servletContext;

	private IPsnServicePortType service = new IPsnService()
			.getIPsnServiceSOAP11PortHttp();

	public WeixinControll() {
		// TODO Auto-generated constructor stub
	}

	/** �ṩ��Ӧ��set������SpringMVC���Զ���ע�� **/
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;

		String opePath = servletContext
				.getRealPath("/WEB-INF/conf/Operation.properties");
		String opeNewPath = servletContext
				.getRealPath("/WEB-INF/conf/OperationNew.properties");
		String opeAuPath = servletContext
				.getRealPath("/WEB-INF/conf/OperationAu.properties");
		String errPath = servletContext
				.getRealPath("/WEB-INF/conf/Error.properties");
		String eTypePath = servletContext
				.getRealPath("/WEB-INF/conf/EmailType.properties");
		String regPath = servletContext
				.getRealPath("/WEB-INF/conf/Regex.properties");
		String disPath = servletContext
				.getRealPath("/WEB-INF/conf/Disable.properties");
		String urlPath = servletContext
				.getRealPath("/WEB-INF/conf/OperationUrl.properties");
		String picPath = servletContext
				.getRealPath("/WEB-INF/conf/OperationPic.properties");
		String grpPath = servletContext
				.getRealPath("/WEB-INF/conf/OperationGroup.properties");
		String termPath = servletContext
				.getRealPath("/WEB-INF/conf/KeyTerm.properties");
		InputStream in;
		try {
			in = new FileInputStream(opePath);
			operationMap = new Properties();
			operationMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(opeNewPath);
			operationNewMap = new Properties();
			operationNewMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(opeAuPath);
			operationAuMap = new Properties();
			operationAuMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(errPath);
			errorMap = new Properties();
			errorMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(eTypePath);
			eTypeMap = new Properties();
			eTypeMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(regPath);
			regMap = new Properties();
			regMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(disPath);
			disMap = new Properties();
			disMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(urlPath);
			urlMap = new Properties();
			urlMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(picPath);
			picMap = new Properties();
			picMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(grpPath);
			grpMap = new Properties();
			grpMap.load(new InputStreamReader(in, "utf-8"));

			in = new FileInputStream(termPath);
			termMap = new Properties();
			termMap.load(new InputStreamReader(in, "utf-8"));

			auMap = new HashMap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getChat.html")
	public String getChat(HttpServletRequest request,
			HttpServletResponse response) {
		return "chat";
	}

	@RequestMapping(value = "/getSummary.html")
	public String getSummary(HttpServletRequest request,
			HttpServletResponse response) {
		return "location_new";
	}

	@RequestMapping(value = "/getSummaryNew.html")
	public String getSummaryNew(HttpServletRequest request,
			HttpServletResponse response) {
		return "location_new";
	}

	@RequestMapping(value = "/getLinks.html")
	public String getLinks(HttpServletRequest request,
			HttpServletResponse response) {
		return "links";
	}

	@RequestMapping(value = "/getStock.html")
	public String getStock() {
		return "stock";
	}

	@RequestMapping(value = "/portal.html")
	public ModelAndView getPortal(HttpServletRequest request,
			HttpServletResponse response) {
		return new ModelAndView("jt/portal_jt");
	}

	@RequestMapping(value = "/portal1111.html")
	public ModelAndView getPortal1111(HttpServletRequest request,
			HttpServletResponse response) {

		UserEntity user = userService.selectByPrimaryKey(1l);

		return new ModelAndView("portal_jt1111");
	}

	// @RequestMapping(value = "/approve.html")
	// public String approve() {
	// return "approve";
	// }

	// @RequestMapping(value = "/login.html")
	// public String login(HttpServletRequest request, HttpServletResponse
	// response) {
	// if (request.getSession().getAttribute("isLogining") != null) {
	// return "approve";
	// }
	// return "login";
	// }
	//
	// @RequestMapping(value = "/loginAction.html")
	// public ModelAndView loginAction(HttpServletRequest request,
	// HttpServletResponse response) {
	// if (request.getSession().getAttribute("isLogining") != null) {
	// ModelAndView model = new ModelAndView("approve");
	// return model;
	// }
	//
	// String user = request.getParameter("user");
	// String pwd = request.getParameter("pwd");
	//
	// if (checkApproveUser(user, pwd)) {
	// request.getSession().setAttribute("isLogining", "success");
	// ModelAndView model = new ModelAndView("approve");
	// return model;
	// } else {
	// ModelAndView model = new ModelAndView("login");
	// model.addObject("title", "提示");
	// model.addObject("errorMsg", "请重新登陆");
	// return model;
	// }
	// }

	@RequestMapping(value = "/displayMsg.html")
	public String displayMsg() {
		return "displayMsg";
	}

	@RequestMapping(value = "/messageBar.html")
	public ModelAndView messageBar(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");
		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() != 0) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "您的微信号未通过验证，无法参与互动活动。 请先点击员工专区按钮进行认证");
			return model;
		}
		request.getSession().setAttribute("openid", openid);
		ModelAndView model = new ModelAndView("messageBar");
		return model;
	}

	@RequestMapping(value = "/getVote.html")
	public ModelAndView getVote(HttpServletRequest request,
			HttpServletResponse response) {
		String mainid = request.getParameter("mainid");

		if (mainid == null || mainid.equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "页面有误");
			return model;
		} else {
			boolean flag = checkVoteFlag();
			if (!flag) {
				ModelAndView model = new ModelAndView("error");
				model.addObject("title", "提示");
				model.addObject("errorMsg", "当前活动已经结束");
				return model;
			}
			String openid = request.getParameter("openid");
			PsnInfoVO psnInfo = service.checkOpenId(openid);
			if (psnInfo.getResultCode().getValue() != 0) {
				ModelAndView model = new ModelAndView("error");
				model.addObject("title", "提示");
				model.addObject("errorMsg", "您的微信号未通过验证，无法参与投票。 请先点击员工专区按钮进行认证");
				return model;
			}
			request.getSession().setAttribute("openid", openid);

			List<VoteItemVO> list = getVoteItems(mainid);
			if (list == null) {
				ModelAndView model = new ModelAndView("error");
				model.addObject("title", "提示");
				model.addObject("errorMsg", "目前没有节目单");
				return model;
			} else {
				VotedVO vo = checkopenidvoted(openid, mainid);
				ModelAndView model = new ModelAndView("vote");
				model.addObject("mainid", mainid);
				model.addObject("itemList", list);
				model.addObject("psnname", psnInfo.getPsnname().getValue()
						.toString());
				if (vo.isVoted()) {
					model.addObject("content", vo.getVote_content());
				} else {
					model.addObject("content", "empty");
				}
				return model;
			}
		}
	}

	@RequestMapping(value = "/vote.html")
	@ResponseBody
	public void vote(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String idList = request.getParameter("idList");
		String openid = request.getParameter("openid");
		String mainid = request.getParameter("mainid");

		if (idList == null || idList.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"选项有误!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (openid == null || openid.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"无法确认您的身份!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (mainid == null || mainid.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"选择主题有误!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() != 0) {
			String jsonStr = "{\"code\":999,\"msg\":\"您没有通过验证无法投票!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		VotedVO vo = checkopenidvoted(openid, mainid);

		if (vo.isVoted()) {
			boolean issucess = reVote(openid, mainid, idList,
					vo.getVote_content());
			if (issucess) {
				String jsonStr = "{\"code\":0,\"msg\":\"重新投票成功!\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			} else {
				String jsonStr = "{\"code\":999,\"msg\":\"投票失败，可能系统繁忙，请重试!\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}
		} else {
			boolean iscommit = submitVote(openid, mainid, idList);
			if (iscommit) {
				String jsonStr = "{\"code\":0,\"msg\":\"投票成功!\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			} else {
				String jsonStr = "{\"code\":999,\"msg\":\"投票失败，可能系统繁忙，请重试!\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}
		}

	}

	@RequestMapping(value = "/getNews.html")
	public ModelAndView getNews() {
		List<NewsVO> list = getNewsIndex();
		if (list == null || list.isEmpty()) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "无法访问国贸新闻");
			return model;
		} else {
			ModelAndView model = new ModelAndView("newsList");
			return model;
		}
	}

	@RequestMapping(value = "/getSendPage.html")
	public ModelAndView getSendPage(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");

		if (openid == null) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "无法访问此页面");
			return model;
		}

		request.getSession().setAttribute("openid", openid);

		String keyword = getCurrentKeyword();
		if (keyword.equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "当前没有活动,请等待活动通知");
			return model;
		} else {

			PsnInfoVO psnInfo = service.checkOpenId(openid);
			String unitname = "";
			String deptname = "";
			String psnname = "非认证用户";
			if ((psnInfo.getResultCode().getValue() == 0)) {
				unitname = psnInfo.getUnitname().getValue().toString();
				deptname = psnInfo.getDeptname().getValue().toString();
				psnname = psnInfo.getPsnname().getValue().toString();
			}
			ModelAndView model = new ModelAndView("sendMsg");
			model.addObject("keyword", keyword);
			model.addObject("unitname", unitname);
			model.addObject("deptname", deptname);
			model.addObject("psnname", psnname);
			return model;
		}
	}

	@RequestMapping(value = "/send.html")
	@ResponseBody
	public void send(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String nickname = request.getParameter("nickname");
		String unitname = request.getParameter("unitname");
		String deptname = request.getParameter("deptname");
		String psnname = request.getParameter("psnname");
		String openid = request.getParameter("openid");
		String message = request.getParameter("message");
		String keyword = request.getParameter("keyword");

		if (nickname == null || nickname.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请输入昵称!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (openid == null || openid.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"无法确认您的身份!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		if (message == null || message.equalsIgnoreCase("")) {
			String jsonStr = "{\"code\":999,\"msg\":\"请输入您要说的话!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		MessageVO vo = new MessageVO();
		vo.setOpenid(openid);
		vo.setNickname(nickname);
		vo.setMessage(message);
		vo.setKeyword(keyword);
		if (unitname != null && !unitname.equalsIgnoreCase("")
				&& deptname != null && !deptname.equalsIgnoreCase("")) {
			vo.setBz(unitname + "-" + deptname);
		}
		if (psnname != null && !psnname.equalsIgnoreCase("")) {
			vo.setPsnname(psnname);
		}

		boolean isSuccess = insertMessage(vo);
		if (isSuccess) {
			String jsonStr = "{\"code\":0,\"msg\":\"发言成功，请等待审核，欢迎继续发言!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"发言失败，可能系统繁忙或被禁言，请稍后在发言!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

	}

	@RequestMapping(value = "/approveMsg.html")
	@ResponseBody
	public void approveMsg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recId = request.getParameter("recId");
		String nickname = request.getParameter("nickname");
		String message = request.getParameter("message");
		if (recId == null) {
			String jsonStr = "{\"code\":999,\"msg\":\"找不到信息编号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
		if (nickname == null) {
			nickname = "空白";
		}
		if (message == null) {
			message = "空白";
		}
		boolean isSuccess = approveOperation(recId, nickname, message);
		if (isSuccess) {
			String jsonStr = "{\"code\":0,\"msg\":\"审核通过成功!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"操作失败，请重试!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/unApproveMsg.html")
	@ResponseBody
	public void unApproveMsg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recId = request.getParameter("recId");
		if (recId == null) {
			String jsonStr = "{\"code\":999,\"msg\":\"找不到信息编号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
		boolean isSuccess = unApproveOperation(recId);
		if (isSuccess) {
			String jsonStr = "{\"code\":0,\"msg\":\"驳回成功!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"操作失败，请重试!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/redoApproveMsg.html")
	@ResponseBody
	public void redoApproveMsg(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recId = request.getParameter("recId");
		if (recId == null) {
			String jsonStr = "{\"code\":999,\"msg\":\"找不到信息编号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		boolean isSuccess = redoApproveOperation(recId);
		if (isSuccess) {
			String jsonStr = "{\"code\":0,\"msg\":\"取消审核通过成功!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"操作失败，请重试!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/setBlack.html")
	@ResponseBody
	public void setBlack(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String recId = request.getParameter("recId");
		String openid = request.getParameter("openid");
		String psnname = request.getParameter("psnname");
		String bz = request.getParameter("bz");
		if (recId == null) {
			String jsonStr = "{\"code\":999,\"msg\":\"找不到信息编号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
		if (openid == null) {
			String jsonStr = "{\"code\":999,\"msg\":\"找不到微信编号!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
		if (psnname == null) {
			psnname = "匿名";
		}
		if (bz == null) {
			bz = "";
		}
		boolean isSuccess = setBlack(recId, openid, psnname, bz);
		if (isSuccess) {
			String jsonStr = "{\"code\":0,\"msg\":\"设置黑名单成功!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"操作失败，请重试!\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/createButtons.html")
	@ResponseBody
	public void createButtons(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String json = getButtons();
		String token_obj = getAccessToken();

		if (!token_obj.equalsIgnoreCase("")) {
			JSONObject jsonObject = JSONObject.fromObject(token_obj);
			String token = (String) jsonObject.get("access_token");
			try {
				URL accessUrl = new URL(
						"https://api.weixin.qq.com/cgi-bin/menu/create?access_token="
								+ token);

				TrustManager[] tm = { new X509TrustManager() {
					@Override
					public X509Certificate[] getAcceptedIssuers() {
						// TODO Auto-generated method stub
						return null;
					}

					@Override
					public void checkServerTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
					}

					@Override
					public void checkClientTrusted(X509Certificate[] arg0,
							String arg1) throws CertificateException {
						// TODO Auto-generated method stub
					}
				} };

				SSLContext sslContext = SSLContext
						.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new java.security.SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
				HttpsURLConnection httpsConn = (HttpsURLConnection) accessUrl
						.openConnection();

				httpsConn.setRequestMethod("POST");
				httpsConn.setRequestProperty("content-type", "text/html");
				httpsConn.setDoOutput(true);
				httpsConn.setSSLSocketFactory(ssf);
				byte[] b = json.toString().getBytes("utf-8");
				httpsConn.getOutputStream().write(b, 0, b.length);
				httpsConn.getOutputStream().flush();
				httpsConn.getOutputStream().close();

				InputStream in = httpsConn.getInputStream();
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						in, "UTF-8"));
				String tempLine = rd.readLine();
				StringBuffer temp = new StringBuffer();
				String crlf = System.getProperty("line.separator");
				while (tempLine != null) {
					temp.append(tempLine);
					temp.append(crlf);
					tempLine = rd.readLine();
				}
				String responseContent = temp.toString();
				System.out.println(responseContent);
				rd.close();
				in.close();

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

	@RequestMapping(value = "/getReceiveMsg.html")
	@ResponseBody
	public void getReceiveMsg(HttpServletRequest request,
			HttpServletResponse response) {
		String fromId = request.getParameter("fromId");

		String jsonStr = getReceiveMsg(fromId);
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedPageCount.html")
	@ResponseBody
	public void getApprovedPageCount(HttpServletRequest request,
			HttpServletResponse response) {
		String pageCount = getApprovedPageCount();
		try {
			response.getOutputStream().write(pageCount.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedMsgByPage.html")
	@ResponseBody
	public void getApprovedMsgByPage(HttpServletRequest request,
			HttpServletResponse response) {
		String pageNo = request.getParameter("pageNo");
		String jsonStr = "[]";
		if (pageNo != null) {
			jsonStr = getApprovedMsgByPage(pageNo);
		}
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedMsg.html")
	@ResponseBody
	public void getApprovedMsg(HttpServletRequest request,
			HttpServletResponse response) {
		String fromId = request.getParameter("fromId");
		String openid = request.getParameter("openid");
		if (openid == null) {
			openid = "";
		}

		String jsonStr = getApprovedMsg(openid, fromId);
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedMsgPre.html")
	@ResponseBody
	public void getApprovedMsgPre(HttpServletRequest request,
			HttpServletResponse response) {
		String ltId = request.getParameter("ltId");
		String openid = request.getParameter("openid");
		if (openid == null) {
			openid = "";
		}

		String jsonStr = getApprovedMsgPre(openid, ltId);
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedMsgByGood.html")
	@ResponseBody
	public void getApprovedMsgByGood(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");
		if (openid == null) {
			openid = "";
		}

		String jsonStr = getApprovedMsgByGood(openid);
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getApprovedMsgBySelf.html")
	@ResponseBody
	public void getApprovedMsgBySelf(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");
		if (openid == null) {
			openid = "";
		}

		String jsonStr = getApprovedMsgBySelf(openid);
		try {
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/setGood.html")
	@ResponseBody
	public void setGood(HttpServletRequest request, HttpServletResponse response) {
		String openid = request.getParameter("openid");
		String apprId = request.getParameter("apprId");
		try {
			if (openid == null || openid.equalsIgnoreCase("")) {
				String jsonStr = "{\"code\":999,\"msg\":\"无法确认您的身份\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}

			if (apprId == null || apprId.equalsIgnoreCase("")) {
				String jsonStr = "{\"code\":999,\"msg\":\"找不到此信息，无法操作\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}

			boolean issuccess = setGood(apprId, openid);
			if (issuccess) {
				String jsonStr = "{\"code\":0,\"msg\":\"操作成功\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			} else {
				String jsonStr = "{\"code\":999,\"msg\":\"操作失败,次信息可能已被取消，请刷新消息\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/getPageNews.html")
	@ResponseBody
	public void getPageNews(HttpServletRequest request,
			HttpServletResponse response) {

		String pageNum = request.getParameter("pageNum");
		String newsType = request.getParameter("newsType");
		Connection dbConn;

		try {
			// Class.forName(driverName);
			// dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			dbConn = cmsds.getConnection();
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"SELECT TOP 10 infoid,infotitle,infodatetime,rownumber,infocontent "
									+ "FROM ( SELECT ROW_NUMBER() OVER (ORDER BY infodatetime desc) AS RowNumber,* FROM sw_info where columnid="
									+ newsType + " and infoisdisplay=1 ) A "
									+ "WHERE RowNumber > 10*(" + pageNum
									+ "-1)");

			List<NewsVO> voList = new ArrayList<NewsVO>();

			while (rs.next()) {
				NewsVO vo = new NewsVO();
				vo.setInfoId(rs.getInt("infoid"));
				vo.setTitle(rs.getString("infotitle"));
				vo.setInfoDate(rs.getString("infodatetime"));
				vo.setRowNum(rs.getInt("rownumber"));
				vo.setContent(getFirstImgTag(rs.getString("infocontent")));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			String resultJson = new JSONSerializer().serialize(voList);
			response.getOutputStream().write(resultJson.getBytes("utf-8"));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getNew.html")
	public ModelAndView getNew(HttpServletRequest request,
			HttpServletResponse response) {

		String infoId = request.getParameter("infoId");

		if (infoId == null || infoId.equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "此新闻不存在或已经删除");
			return model;
		} else {
			NewsVO vo = getNewsBody(infoId);
			if (vo == null) {
				ModelAndView model = new ModelAndView("error");
				model.addObject("title", "提示");
				model.addObject("errorMsg", "此新闻不存在或已经删除");
				return model;
			} else {
				ModelAndView model = new ModelAndView("news");
				model.addObject("newsBody", vo);
				return model;
			}
		}
	}

	@RequestMapping(value = "/getQueryRs.html")
	@ResponseBody
	public void getQueryRs(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!isWeixin(request)) {
			String jsonStr = "{\"code\":999,\"msg\":\"您无法进行此操作\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
		String openid = request.getParameter("openid");
		String cond = request.getParameter("cond").trim();

		String result = service.getPsnPhoneVOs(openid, cond);
		response.getOutputStream().write(result.getBytes("utf-8"));
	}

	@RequestMapping(value = "/register.html")
	// out
	public ModelAndView register(HttpServletRequest request,
			HttpServletResponse response) {

		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您无法访问此页面");
			return model;
		}

		String openid = request.getParameter("openid");
		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() == 0) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "温馨提示");
			model.addObject("errorMsg", "您的微信号已经通过验证，无需校验");
			return model;
		}
		ModelAndView model = new ModelAndView("register");
		request.getSession().setAttribute("openid", openid);
		return model;
	}

	@RequestMapping(value = "/sendAuth.html")
	@ResponseBody
	public void sendAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			String jsonStr = "{\"code\":999,\"msg\":\"您无法进行此操作\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		String openid = request.getParameter("openid");
		String type = request.getParameter("type");
		String content = request.getParameter("content");

		if (type.equalsIgnoreCase("email")) {
			String email = content;
			String[] email_info = email.split("@");
			if (email_info.length < 2) {
				String jsonStr = "{\"code\":999,\"msg\":\"您输入的邮箱格式有误\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}
			if (!eTypeMap.containsValue(email_info[1].trim())) {
				String jsonStr = "{\"code\":999,\"msg\":\"不支持国贸邮箱以外的email认证\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}

			int code = service.bindWeixin(email, "email", openid);
			if (code == 0) {
				String jsonStr = "{\"code\":0,\"msg\":\"已经向您的国贸邮箱发送认证邮件，请进入邮箱确认!\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			} else {
				String jsonStr = "{\"code\":999,\"msg\":\""
						+ (String) errorMap.get(String.valueOf(code)) + "\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			}
		} else if (type.equalsIgnoreCase("tel")) {
			String phone = content;
			Pattern pattern = Pattern.compile("[0-9]*");
			ModelAndView model_err = new ModelAndView("error");
			model_err.addObject("title", "警告");
			boolean isDig = pattern.matcher(phone).matches();
			if (!isDig) {
				String jsonStr = "{\"code\":999,\"msg\":\"请输入合法的手机号码\"}";
				response.getOutputStream().write(jsonStr.getBytes("utf-8"));
			} else {
				int code = service.bindWeixin(phone, "mms", openid);
				if (code > 999999) {
					String mmscode = String.valueOf(code);
					mmscode = mmscode.substring(1, mmscode.length());
					// System.out.println(mmscode);
					int sendcode = authByMMS(phone, "您的国贸微信平台认证码是：" + mmscode
							+ ",请在微信平台输入此验证码.");
					if (sendcode == 0) {
						String jsonStr = "{\"code\":0,\"msg\":\""
								+ "已向【"
								+ phone
								+ "】发送校验码，请填写验证码!短信可能会有延时，如长时间无法收到认证短信，可能短信机发生故障，请您使用邮箱认证。"
								+ "\"}";
						response.getOutputStream().write(
								jsonStr.getBytes("utf-8"));
					} else {
						String jsonStr = "{\"code\":999,\"msg\":\""
								+ (String) errorMap.get(String.valueOf(code))
								+ "\"}";
						response.getOutputStream().write(
								jsonStr.getBytes("utf-8"));
					}

				} else {
					String jsonStr = "{\"code\":999,\"msg\":\""
							+ (String) errorMap.get(String.valueOf(code))
							+ "\"}";
					response.getOutputStream().write(jsonStr.getBytes("utf-8"));
				}
			}
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"内部错误\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	// @RequestMapping(value = "/sendAuth.html")//过时
	// public ModelAndView sendAuth(HttpServletRequest request,
	// HttpServletResponse response) throws Exception {
	//
	// if (!isWeixin(request)) {
	// ModelAndView model = new ModelAndView("error");
	// model.addObject("title", "错误");
	// model.addObject("errorMsg", "您无法访问此页面");
	// return model;
	// }
	//
	// String openid = request.getParameter("openid");
	// String type = request.getParameter("type");
	// String content = request.getParameter("content");
	//
	// if (type.equalsIgnoreCase("email")) {
	// String email = content;
	// String[] email_info = email.split("@");
	// ModelAndView model_err = new ModelAndView("error");
	// model_err.addObject("title", "拒绝访问");
	// if (email_info.length < 2) {
	// model_err.addObject("errorMsg", "您输入的邮箱格式有误");
	// return model_err;
	// }
	// if (!eTypeMap.containsValue(email_info[1].trim())) {
	// model_err.addObject("errorMsg", "不支持国贸邮箱以外的email认证");
	// return model_err;
	// }
	//
	// int code = service.bindWeixin(email, "email", openid);
	// if (code == 0) {
	// ModelAndView model_suc = new ModelAndView("success");
	// model_suc.addObject("title", "操作成功");
	// model_suc.addObject("sucMsg", "已经向您的国贸邮箱发送认证邮件，请进入邮箱确认!");
	// return model_suc;
	// } else {
	// model_err.addObject("errorMsg",
	// (String) errorMap.get(String.valueOf(code)));
	// return model_err;
	// }
	// } else if (type.equalsIgnoreCase("tel")) {
	// String phone = content;
	// Pattern pattern = Pattern.compile("[0-9]*");
	// ModelAndView model_err = new ModelAndView("error");
	// model_err.addObject("title", "警告");
	// boolean isDig = pattern.matcher(phone).matches();
	// if (!isDig) {
	// model_err.addObject("errorMsg", "请输入合法的手机号码");
	// return model_err;
	// } else {
	// int code = service.bindWeixin(phone, "mms", openid);
	// if (code > 999999) {
	// String mmscode = String.valueOf(code);
	// mmscode = mmscode.substring(1, mmscode.length());
	// // System.out.println(mmscode);
	// int sendcode = authByMMS(phone, "您的国贸微信平台认证码是：" + mmscode
	// + ",请在微信平台输入此验证码.");
	// if (sendcode == 0) {
	// ModelAndView model_suc = new ModelAndView("checkmms");
	// model_suc.addObject("title", "操作成功");
	// model_suc.addObject("openid", openid);
	// model_suc
	// .addObject(
	// "sucMsg",
	// "已向【"
	// + phone
	// + "】发送校验码，请填写验证码!短信可能会有延时，如长时间无法收到认证短信，可能短信机发生故障，请您使用邮箱认证。");
	// return model_suc;
	// } else {
	// model_err.addObject("errorMsg",
	// (String) errorMap.get(String.valueOf(code)));
	// return model_err;
	// }
	//
	// } else {
	// model_err.addObject("errorMsg",
	// (String) errorMap.get(String.valueOf(code)));
	// return model_err;
	// }
	// }
	// } else {
	// ModelAndView model_err = new ModelAndView("error");
	// model_err.addObject("title", "警告");
	// model_err.addObject("errorMsg", "内部错误");
	// return model_err;
	// }
	// }

	@RequestMapping(value = "/checkmms.html")
	@ResponseBody
	public void checkmms(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (!isWeixin(request)) {
			String jsonStr = "{\"code\":999,\"msg\":\"您无法进行此操作\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		String openid = request.getParameter("openid");
		String mmscode = request.getParameter("mmscode");
		String jsonStr = "";
		int rscode = service.confirmBindMMS(mmscode, openid);
		if (rscode == 0) {
			jsonStr = "{\"code\":0,\"msg\":\"认证成功，请返回选择其他操作\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			jsonStr = "{\"code\":999,\"msg\":\"您的账号已经通过验证或者校验码已经无效\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/employee.html")
	public ModelAndView employee(HttpServletRequest request,
			HttpServletResponse response) {
		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您无法访问此页面");
			return model;
		}

		String openid;
		openid = (String) request.getSession().getAttribute("openid");
		if (openid == null) {
			openid = request.getParameter("openid");
			request.getSession().setAttribute("openid", openid);
		}
		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() == 0) {
			request.getSession().setAttribute("psnname",
					psnInfo.getPsnname().getValue());
			ModelAndView model = new ModelAndView("query");
			return model;
		}
		ModelAndView model = new ModelAndView("register");
		request.getSession().setAttribute("openid", openid);
		return model;
	}

	@RequestMapping(value = "/query.html")
	public ModelAndView query(HttpServletRequest request,
			HttpServletResponse response) {

		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您无法访问此页面");
			return model;
		}
		String openid;
		openid = (String) request.getSession().getAttribute("openid");
		if (openid == null) {
			openid = request.getParameter("openid");
			request.getSession().setAttribute("openid", openid);
		}

		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() != 0) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "温馨提示");
			model.addObject("errorMsg", "您还未通过国贸微信平台认证，无法使用此服务");
			return model;
		} else {
			request.getSession().setAttribute("psnname",
					psnInfo.getPsnname().getValue());
			ModelAndView model = new ModelAndView("query");
			return model;
		}
	}

	@RequestMapping(value = "/removeAuth.html")
	// out
	public ModelAndView removeAuth(HttpServletRequest request,
			HttpServletResponse response) {

		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您无法访问此页面");
			return model;
		}

		// String openid = request.getParameter("openid");
		String openid = (String) request.getSession().getAttribute("openid");
		PsnInfoVO psnInfo = service.checkOpenId(openid);
		if (psnInfo.getResultCode().getValue() == 0) {
			ModelAndView remove = new ModelAndView("remove");
			request.getSession().setAttribute("psnname",
					(String) psnInfo.getPsnname().getValue());
			return remove;
		} else {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您还未通过认证，无需解除");
			return model;
		}

	}

	@RequestMapping(value = "/confirmRemoveAuth.html")
	@ResponseBody
	public void confirmRemoveAuth(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// String jsonStr = "{\"code\":999,\"msg\":\"您无法进行此操作\"}";
		if (!isWeixin(request)) {
			String jsonStr = "{\"code\":999,\"msg\":\"您无法进行此操作\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

		String openid = (String) request.getSession().getAttribute("openid");
		int code = service.unbindWeixin(openid);
		// int code=999;
		if (code == 0) {
			String jsonStr = "{\"code\":0,\"msg\":\"您已经成功解除认证\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} else {
			String jsonStr = "{\"code\":999,\"msg\":\"解除失败\"}";
			response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}
	}

	@RequestMapping(value = "/confirmRemove.html")
	// out
	public ModelAndView confirmRemove(HttpServletRequest request,
			HttpServletResponse response) {

		if (!isWeixin(request)) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "错误");
			model.addObject("errorMsg", "您无法访问此页面");
			return model;
		}

		String openid = request.getParameter("openid");
		int code = service.unbindWeixin(openid);
		if (code == 0) {
			ModelAndView model_suc = new ModelAndView("success");
			model_suc.addObject("title", "操作成功");
			model_suc.addObject("sucMsg", "解除认证成功,请返回进行其他操作");
			return model_suc;
		} else {
			ModelAndView model_err = new ModelAndView("error");
			model_err.addObject("title", "失败提示");
			model_err.addObject("errorMsg",
					(String) errorMap.get(String.valueOf(code)));
			return model_err;
		}
	}

	public String getMenu(String openid, String group) {
		PsnInfoVO psnInfo = service.checkOpenId(openid);
		int code = psnInfo.getResultCode().getValue().intValue();
		Map orderedProp;
		String openstr = "";
		if (group.equalsIgnoreCase("yuanxiao")) {
			openstr = "2014总结表彰大会";
		} else {
			openstr = "欢迎关注厦门国贸！";
		}
		int l_count = 0;

		StringBuffer b = new StringBuffer();
		orderedProp = new TreeMap(operationNewMap);
		if (code == 0) {
			String psnname = psnInfo.getPsnname().getValue().toString();
			String sex = psnInfo.getSex().getValue().toString();
			// openstr = "您好，" + psnname
			// + (sex.equalsIgnoreCase("男") ? "先生" : "女士") + "，您可选择以下操作：";
			b.append("<item>");
			b.append("<Title><![CDATA[" + openstr + "]]></Title>");
			b.append("<PicUrl><![CDATA[http://59.57.246.46/WeixinService/image/logo4.jpg]]></PicUrl>");
			b.append("</item>");
		} else {
			b.append("<item>");
			b.append("<Title><![CDATA[" + openstr + "]]></Title>");
			b.append("<PicUrl><![CDATA[http://59.57.246.46/WeixinService/image/logo4.jpg]]></PicUrl>");
			b.append("</item>");
		}
		Iterator it = orderedProp.entrySet().iterator();
		StringBuffer buffer = new StringBuffer();
		buffer.append(openstr).append("\n\n");

		StringBuffer b_h = new StringBuffer();

		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();

			String ope = operationNewMap.getProperty(key);
			String url = urlMap.getProperty(key);
			String pic = picMap.getProperty(key);
			String grp = grpMap.getProperty(key);
			StringBuffer b1 = new StringBuffer();
			if (grp != null && grp.equalsIgnoreCase(group)) {
				b1.append("<item>");
				b1.append("<Title><![CDATA[" + ope + "]]></Title>");
				b1.append("<Url><![CDATA[" + String.format(url, openid)
						+ "]]></Url>");
				b1.append("<PicUrl><![CDATA[" + pic + "]]></PicUrl>");
				b1.append("</item>");
				b_h.insert(0, b1.toString());
				l_count++;
			}

		}

		String count_str = "<ArticleCount>" + (l_count + 1) + "</ArticleCount>"
				+ "<Articles>" + b.append(b_h).toString() + "</Articles>";

		return count_str;
	}

	// @RequestMapping(value = "/getNew.html")
	// @ResponseBody
	// public void getNew(HttpServletRequest request, HttpServletResponse
	// response)
	// throws Exception {
	// request.setCharacterEncoding("UTF-8");
	// response.setCharacterEncoding("UTF-8");
	//
	// String id = request.getParameter("newid"); // 得到图片路径
	// String html =
	// "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"
	// + "<html>"
	// + "<body>"
	// + "<img src=\"http://59.57.246.46/WeixinService/getImg.html?imgid="
	// + id + "\" />" + "</body>" + "</html>";
	// response.getOutputStream().write(html.getBytes());
	// }

	@RequestMapping(value = "/getImg.html")
	@ResponseBody
	public void getImg(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String id = request.getParameter("imgid"); // 得到图片路径

		File files = new File("D:\\shots\\");
		String filename[];
		filename = files.list();
		for (int i = 0; i < filename.length; i++) {
			if (filename[i].substring(0, 5).equalsIgnoreCase(id)) {
				File file = new File("D:\\shots\\" + filename[i]);

				response.setHeader("Pragma", "No-cache");
				response.setHeader("Cache-Control", "no-cache");
				response.setDateHeader("Expires", 0L);
				response.setHeader("Content-Length", file.length() + "");
				response.setContentType("image/jpg;charset=GB2312");

				InputStream in = new FileInputStream(file);
				OutputStream out = response.getOutputStream();
				byte[] bs = new byte[1024];
				int len;
				while ((len = in.read(bs)) != -1) {
					out.write(bs, 0, len);
				}
				in.close();
				out.close();
			}
		}

	}

	@RequestMapping(value = "/confirmEmail.html")
	public ModelAndView confirmEmail(HttpServletRequest request,
			HttpServletResponse response) {
		String code = request.getParameter("code");
		ModelAndView model = new ModelAndView("confirmEmail");
		model.addObject("code", code);
		return model;
	}

	@RequestMapping(value = "/bindpsn.html")
	@ResponseBody
	public void bindpsn(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String code = request.getParameter("code");

		// IPsnServicePortType service = new IPsnService()
		// .getIPsnServiceSOAP11PortHttp();
		int rs = service.confirmBind(code);
		String rsStr = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
				+ "<html xmlns=\"http://www.w3.org/1999/xhtml\">"
				+ "<head>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=gb2312\" />"
				+ "<title>微信认证</title>"
				+ "<style type=\"text/css\">"
				+ "<!--"
				+ ".STYLE1 {font-size: 24px}"
				+ "-->"
				+ "</style>"
				+ "</head>"
				+ "<body>"
				+ "	<br /><br /><br /><div align=\"center\"><span class=\"STYLE1\">%s</span></div>"
				+ "</body>" + "</html>";
		if (rs == 0) {
			// String.format(rsStr, "欢迎您，请在微信会话框输入任意字符，获取微信平台为国贸员工定制的信息服务！");
			response.getOutputStream().write(
					String.format(rsStr, "欢迎您，请使用微信平台为国贸员工定制的信息服务！").getBytes(
							"utf-8"));
		} else {
			response.getOutputStream().write(
					String.format(rsStr,
							((String) errorMap.get(String.valueOf(rs))))
							.getBytes("utf-8"));
		}

	}

	@RequestMapping(value = "/weixinrec.html")
	@ResponseBody
	public void weixinrec(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		// System.out.println(request.getMethod() + "||"
		// + request.getParameter("echostr"));
		if (request.getMethod().equalsIgnoreCase("GET")) {
			response.getOutputStream().write(
					request.getParameter("echostr").getBytes("UTF-8"));
			// return request.getParameter("echostr");
		} else {
			WeixinMsgVO params = getVOFromXML(request.getInputStream());

			if (params.getEvent() != null) {
				if (params.getEvent().equalsIgnoreCase("CLICK")) {
					if (params.getEventKey() != null) {

						String term = termMap.getProperty(params.getEventKey());
						if (term == null) {
							return;
						}
						if (!term.equalsIgnoreCase("all")) {
							String[] terms = term.split("-");
							Date now = new Date();
							SimpleDateFormat fmt = new SimpleDateFormat(
									"yyyyMMddhhmmss");
							Date from = fmt.parse(terms[0]);
							Date to = fmt.parse(terms[1]);
							if (now.before(from) || now.after(to)) {
								String str = "<xml>"
										+ "<ToUserName><![CDATA["
										+ params.getFromUserName()
										+ "]]></ToUserName><FromUserName><![CDATA["
										+ params.getToUserName()
										+ "]]></FromUserName><CreateTime>"
										+ System.currentTimeMillis()
										+ "</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA["
										+ "此按钮当前无效" + "]]></Content>"
										+ "</xml>";
								response.getOutputStream().write(
										str.getBytes("utf-8"));
								return;
							}
						}

						String menu = getMenu(params.getFromUserName(),
								params.getEventKey());

						String return_content = "<xml>"
								+ "<ToUserName><![CDATA["
								+ params.getFromUserName() + "]]></ToUserName>"
								+ "<FromUserName><![CDATA["
								+ params.getToUserName() + "]]></FromUserName>"
								+ "<CreateTime>" + System.currentTimeMillis()
								+ "</CreateTime>"
								+ "<MsgType><![CDATA[news]]></MsgType>" + menu
								+ "</xml>";
						response.getOutputStream().write(
								return_content.getBytes("utf-8"));
						return;
					}
				}
			}
			if (params.getContent() != null) {

				if (params.getContent().equalsIgnoreCase("元宵")) {
					String menu = getMenu(params.getFromUserName(), "yuanxiao");
					String return_content = "<xml>" + "<ToUserName><![CDATA["
							+ params.getFromUserName() + "]]></ToUserName>"
							+ "<FromUserName><![CDATA["
							+ params.getToUserName() + "]]></FromUserName>"
							+ "<CreateTime>" + System.currentTimeMillis()
							+ "</CreateTime>"
							+ "<MsgType><![CDATA[news]]></MsgType>" + menu
							+ "</xml>";
					response.getOutputStream().write(
							return_content.getBytes("utf-8"));
				}
			}

		}
	}

	public int checkMMSCode(String code, String openid) {

		// Long sendDate = (Long) auMap.get(openid);
		int rscode = service.confirmBindMMS(code, openid);
		if (rscode == 0) {
			auMap.remove(openid);
		}
		return 0;
	}

	public WeixinMsgVO getVOFromXML(InputStream in) throws Exception {

		BufferedReader rd = new BufferedReader(new InputStreamReader(in,
				"UTF-8"));
		String tempLine = rd.readLine();
		StringBuffer temp = new StringBuffer();
		String crlf = System.getProperty("line.separator");
		while (tempLine != null) {
			temp.append(tempLine);
			temp.append(crlf);
			tempLine = rd.readLine();
		}
		String data = temp.toString();
		// System.out.println(data);

		rd.close();
		in.close();

		StringReader sr = new StringReader(data);
		InputSource is = new InputSource(sr);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse(is);
		WeixinMsgVO vo = new WeixinMsgVO();
		NodeList root = document.getChildNodes();
		NodeList params = root.item(0).getChildNodes();
		for (int i = 0; i < params.getLength(); i++) {
			Node para = params.item(i);
			if (para.getNodeName().equalsIgnoreCase("ToUserName")) {
				vo.setToUserName(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("FromUserName")) {
				vo.setFromUserName(getNoCDATAText(para));
				if (getNoCDATAText(para).equalsIgnoreCase(
						"oGWhot6q83jPLENglsitEv1xjYCw")) {
					System.out.println(data);
				}
			} else if (para.getNodeName().equalsIgnoreCase("CreateTime")) {
				vo.setCreateTime(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("MsgType")) {
				vo.setMsgType(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("Content")) {
				vo.setContent(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("MsgId")) {
				vo.setMsgId(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("Event")) {
				vo.setEvent(getNoCDATAText(para));
			} else if (para.getNodeName().equalsIgnoreCase("EventKey")) {
				vo.setEventKey(getNoCDATAText(para));
			}
		}
		// System.out.println(vo.getEvent());
		return vo;
	}

	public String getNoCDATAText(Node node) {
		Node data = node.getFirstChild();

		if (data.getNodeType() == Node.CDATA_SECTION_NODE) {
			CDATASection dataNode = (CDATASection) data;
			// System.out.println(dataNode.getTextContent());
			return dataNode.getTextContent();
		} else {
			return data.getTextContent();
		}
	}

	public int authByMMS(String telno, String msg) throws SQLException {
		try {
			// Class.forName("com.mysql.jdbc.Driver").newInstance();
			// String url =
			// "jdbc:MySql://59.57.246.61:3306/mas?&dbcharset=gb2312&characterEncoding=gb2312";
			// Connection conn = (Connection) DriverManager.getConnection(url,
			// "OATEST", "123456789");
			Connection conn = mmsds.getConnection();
			PreparedStatement ps = conn
					.prepareStatement("insert into api_mt_BBB(mobiles,content,is_wap) values(?,?,0)");

			ps.setString(1, telno);
			ps.setString(2, msg);
			ps.executeUpdate();

			ps.close();
			conn.close();

		} catch (Exception e) {
			// TODO: handle exception
			return 110;
		}
		return 0;
		// return "已向" + telno + "发送校验码，请收到后给我们发送校验码进行认证";
	}

	public boolean isWeixin(HttpServletRequest request) {
		String userAgent = request.getHeader("USER-AGENT");

		if (userAgent.indexOf("MicroMessenger") != -1) {
			return true;
		}

		return true;
	}

	public NewsVO getNewsBody(String infoId) {
		// String driverName = "net.sourceforge.jtds.jdbc.Driver"; // 加载JDBC驱动
		// String dbURL =
		// "jdbc:jtds:sqlserver://172.16.10.237:1433/itggroupcms"; //
		// 连接服务器和数据库test
		// String userName = "itggroupcms"; // 默认用户名
		// String userPwd = "itggr0upcns"; // 密码
		Connection dbConn;

		try {
			// Class.forName(driverName);
			// dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			dbConn = cmsds.getConnection();
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select infoid,infotitle,infocontent,infodatetime from sw_info where infoisdisplay=1 and infoid="
									+ infoId);

			while (rs.next()) {
				NewsVO vo = new NewsVO();
				vo.setInfoId(rs.getInt("infoid"));
				vo.setTitle(rs.getString("infotitle"));
				vo.setContent(getRightHTML(rs.getString("infocontent")));
				vo.setInfoDate(rs.getString("infodatetime"));
				rs.close();
				dbConn.close();
				return vo;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<NewsVO> getNewsIndex() {
		// String driverName = "net.sourceforge.jtds.jdbc.Driver"; // 加载JDBC驱动
		// String dbURL =
		// "jdbc:jtds:sqlserver://172.16.10.237:1433/itggroupcms"; //
		// 连接服务器和数据库test
		// String userName = "itggroupcms"; // 默认用户名
		// String userPwd = "itggr0upcns"; // 密码
		Connection dbConn;

		try {
			// Class.forName(driverName);
			// dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			dbConn = cmsds.getConnection();
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(10) infoid,infotitle,infodatetime from sw_info where infoisdisplay=1 and columnid in (2,3) order by infodatetime desc ");

			List<NewsVO> voList = new ArrayList<NewsVO>();

			while (rs.next()) {
				NewsVO vo = new NewsVO();
				vo.setInfoId(rs.getInt("infoid"));
				vo.setTitle(rs.getString("infotitle"));
				vo.setInfoDate(rs.getString("infodatetime"));

				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			return voList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRightHTML(String html) {

		return html.replaceAll("(<[Ii][Mm][Gg].+?src=['|\"](?!http))",
				"$1http://www.itg.com.cn").replaceAll(
				"(<v:imagedata.+?src[\\s]*=[\\s]*['|\"](?!http))",
				"$1http://www.itg.com.cn");
	}

	public List<VoteItemVO> getVoteItems(String mainid) {
		Connection dbConn;
		boolean flag = false;
		List<VoteItemVO> list = new ArrayList<VoteItemVO>();
		try {
			dbConn = wxds.getConnection();
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select * from itg_weixin_vote_item where main_id='"
							+ mainid + "'");

			while (rs.next()) {
				VoteItemVO vo = new VoteItemVO();
				vo.setId(rs.getInt("id"));
				vo.setItemno(rs.getInt("itemno"));
				vo.setItemname(rs.getString("itemname"));
				vo.setItemdesc(rs.getString("itemdesc"));
				vo.setVote_count(rs.getInt("vote_count"));
				list.add(vo);
			}

			rs.close();
			dbConn.close();

			if (list.isEmpty()) {
				return null;
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean getOpeMsgFlag() {
		Connection dbConn;
		boolean flag = false;
		try {
			dbConn = wxds.getConnection();
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(1) conf_value from itg_weixin_config where conf_name='ope_message'");
			while (rs.next()) {
				String conf_value = rs.getString("conf_value");
				flag = (conf_value.equalsIgnoreCase("1")) ? true : false;
			}

			rs.close();
			dbConn.close();

			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean reVote(String openid, String mainid, String idList,
			String oldIdList) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);

			PreparedStatement stmt1 = dbConn
					.prepareStatement("update itg_weixin_vote_openid set vote_content=? where openid=? and main_id=?");
			stmt1.setString(1, idList);
			stmt1.setString(2, openid);
			stmt1.setString(3, mainid);
			stmt1.execute();

			dbConn.createStatement().executeUpdate(
					"update itg_weixin_vote_item set vote_count=vote_count-1 where main_id="
							+ mainid + " and itemno in (" + oldIdList + ")");

			dbConn.createStatement().executeUpdate(
					"update itg_weixin_vote_item set vote_count=vote_count+1 where main_id="
							+ mainid + " and itemno in (" + idList + ")");

			dbConn.commit();

			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean submitVote(String openid, String mainid, String idList) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);

			PreparedStatement stmt1 = dbConn
					.prepareStatement("insert into itg_weixin_vote_openid(openid,main_id,vote_content,bz) values(?,?,?,?)");
			stmt1.setString(1, openid);
			stmt1.setString(2, mainid);
			stmt1.setString(3, idList);
			stmt1.setString(4, null);
			stmt1.execute();

			dbConn.createStatement().executeUpdate(
					"update itg_weixin_vote_item set vote_count=vote_count+1 where main_id="
							+ mainid + " and itemno in (" + idList + ")");
			dbConn.commit();

			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean insertMessage(MessageVO vo) {
		Connection dbConn;
		boolean isBlack = false;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn.createStatement().executeQuery(
					"select * from itg_weixin_message_black where openid='"
							+ vo.getOpenid() + "'");
			while (rs.next()) {
				isBlack = true;
			}

			if (!isBlack) {
				PreparedStatement stmt = dbConn
						.prepareStatement("insert into itg_weixin_message_rec(openid,message,email,mobile,isapprove,psnname,bz,nickname,keyword) values(?,?,?,?,?,?,?,?,?)");
				stmt.setString(1, vo.getOpenid());
				stmt.setString(2, vo.getMessage());
				stmt.setString(3, vo.getEmail());
				stmt.setString(4, vo.getMobile());
				stmt.setString(5, "0");
				stmt.setString(6, vo.getPsnname());
				stmt.setString(7, vo.getBz());
				stmt.setString(8, vo.getNickname());
				stmt.setString(9, vo.getKeyword());
				stmt.execute();
			}
			dbConn.close();
			if (isBlack) {
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean approveOperation(String recId, String nickname,
			String message) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);
			dbConn.createStatement()
					.executeUpdate(
							"insert into itg_weixin_message_appr(rec_id,openid,nickname,psnname,message,keyword,bz,good_count)"
									+ " select id,openid,'"
									+ nickname
									+ "' as nickname,psnname,'"
									+ message
									+ "' as message,keyword,bz,0 as good_count"
									+ " from itg_weixin_message_rec where isapprove='0' and id="
									+ recId);
			dbConn.createStatement().executeUpdate(
					"update itg_weixin_message_rec set isapprove='1' where id="
							+ recId);
			dbConn.commit();
			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean unApproveOperation(String recId) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.createStatement().executeUpdate(
					"update itg_weixin_message_rec set isapprove='2' where id="
							+ recId);
			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean redoApproveOperation(String recId) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);
			dbConn.createStatement()
					.executeUpdate(
							"delete from itg_weixin_message_appr where rec_id="
									+ recId);
			dbConn.createStatement().executeUpdate(
					"update itg_weixin_message_rec set isapprove='0' where id="
							+ recId);
			dbConn.commit();
			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean setBlack(String recId, String openid, String psnname,
			String bz) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);
			dbConn.createStatement().executeUpdate(
					"update itg_weixin_message_rec set isapprove='2' where id="
							+ recId);

			dbConn.createStatement().executeUpdate(
					"insert into itg_weixin_message_black(openid,psnname,bz) values('"
							+ openid + "','" + psnname + "','" + bz + "')");
			dbConn.commit();
			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isBlack(String openid) {
		Connection dbConn;
		boolean isRight = false;
		try {
			dbConn = wxds.getConnection();
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select * from itg_weixin_message_black where openid='"
							+ openid + "'");
			while (rs.next()) {
				isRight = true;
			}
			dbConn.close();
			return isRight;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getReceiveMsg(String fromId) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select id,openid,nickname,message,psnname,keyword,bz from itg_weixin_message_rec where id>"
									+ fromId
									+ " and isapprove='0' order by id asc");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getApprovedPageCount() {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			int count = 0;
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select count(*) as count from itg_weixin_message_rec where isapprove<>'0'");

			while (rs.next()) {
				count = rs.getInt("count");
				count = (int) Math.ceil(count / 20.0);
			}

			rs.close();
			dbConn.close();

			return String.valueOf(count);

		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	public String getApprovedMsgByPage(String pageNo) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"SELECT TOP 20 id,isapprove,openid,nickname,message,psnname,keyword,bz FROM ( SELECT ROW_NUMBER() OVER (ORDER BY isapprove desc,id desc) AS RowNumber,* FROM itg_weixin_message_rec where isapprove<>'0' ) A WHERE RowNumber > 10*("
									+ pageNo + "-1)");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setIsapprove(rs.getString("isapprove"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				// System.out.println(resultJson);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getApprovedMsg(String openid, String fromId) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(20) a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count,count(b.id) as hasgood from itg_weixin_message_appr as a left join itg_weixin_message_good as b on a.id=b.appr_id and b.openid='"
									+ openid
									+ "' where a.id>"
									+ fromId
									+ " group by a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count order by a.id desc");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				vo.setGood_count(rs.getInt("good_count"));
				vo.setHasgood(rs.getInt("hasgood"));
				voList.add(vo);
			}

			Comparator<MessageVO> comparator = new Comparator<MessageVO>() {
				public int compare(MessageVO m1, MessageVO m2) {
					return m1.getId() - m2.getId();
				}
			};
			if (!voList.isEmpty()) {
				Collections.sort(voList, comparator);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				// System.out.println(resultJson);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getApprovedMsgPre(String openid, String ltId) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(20) a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count,count(b.id) as hasgood from itg_weixin_message_appr as a left join itg_weixin_message_good as b on a.id=b.appr_id and b.openid='"
									+ openid
									+ "' where a.id<"
									+ ltId
									+ " group by a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count order by a.id desc");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				vo.setGood_count(rs.getInt("good_count"));
				vo.setHasgood(rs.getInt("hasgood"));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				// System.out.println(resultJson);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getApprovedMsgBySelf(String openid) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(50) a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count,count(b.id) as hasgood from itg_weixin_message_appr as a left join itg_weixin_message_good as b on a.id=b.appr_id and b.openid='"
									+ openid
									+ "' where a.openid='"
									+ openid
									+ "' group by a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count order by a.id desc");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				vo.setGood_count(rs.getInt("good_count"));
				vo.setHasgood(rs.getInt("hasgood"));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				// System.out.println(resultJson);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getApprovedMsgByGood(String openid) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();

			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select top(50) a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count,count(b.id) as hasgood from itg_weixin_message_appr as a left join itg_weixin_message_good as b on a.id=b.appr_id and b.openid='"
									+ openid
									+ "' group by a.id,a.openid,a.nickname,a.message,a.psnname,a.keyword,a.bz,a.good_count order by a.good_count desc");

			List<MessageVO> voList = new ArrayList<MessageVO>();

			while (rs.next()) {
				MessageVO vo = new MessageVO();
				vo.setId(rs.getInt("id"));
				vo.setNickname(rs.getString("nickname"));
				vo.setOpenid(rs.getString("openid"));
				vo.setMessage(rs.getString("message"));
				vo.setPsnname(rs.getString("psnname"));
				vo.setKeyword(rs.getString("keyword"));
				vo.setBz(rs.getString("bz"));
				vo.setGood_count(rs.getInt("good_count"));
				vo.setHasgood(rs.getInt("hasgood"));
				voList.add(vo);
			}

			rs.close();
			dbConn.close();

			if (voList.isEmpty()) {
				return "[]";
			} else {
				String resultJson = new JSONSerializer().serialize(voList);
				// System.out.println(resultJson);
				return resultJson;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "[]";
		}
	}

	public String getCurrentKeyword() {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			Date now = new Date();
			String keyword = "";
			SimpleDateFormat matter1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			String now_str = matter1.format(now);
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select keyword from itg_weixin_keyword where '" + now_str
							+ "' between startdate and enddate");
			while (rs.next()) {
				keyword = keyword + rs.getString("keyword") + ",";
			}
			if (!keyword.equalsIgnoreCase("")) {
				keyword = keyword.substring(0, keyword.length() - 1);
			}
			dbConn.close();
			return keyword;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public boolean checkVoteFlag() {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			boolean flag = false;
			ResultSet rs = dbConn
					.createStatement()
					.executeQuery(
							"select conf_value from itg_weixin_config where conf_name='voteFlag'");
			while (rs.next()) {
				String conf_value = rs.getString("conf_value");
				if (conf_value.equalsIgnoreCase("1")) {
					flag = true;
				}
			}

			dbConn.close();
			return flag;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean checkApproveUser(String username, String pwd) {
		Connection dbConn;
		boolean isRight = false;
		try {
			dbConn = wxds.getConnection();
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select * from itg_weixin_user where username='" + username
							+ "' and pwd='" + pwd + "' ");
			while (rs.next()) {
				isRight = true;
			}
			dbConn.close();
			return isRight;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public VotedVO checkopenidvoted(String openid, String mainid) {
		Connection dbConn;
		VotedVO vo = new VotedVO();
		vo.setVoted(false);
		try {
			dbConn = wxds.getConnection();
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select vote_content from itg_weixin_vote_openid where openid='"
							+ openid + "' and main_id='" + mainid + "'");
			while (rs.next()) {
				vo.setVoted(true);
				vo.setVote_content(rs.getString("vote_content"));
			}
			dbConn.close();

			return vo;
		} catch (Exception e) {
			e.printStackTrace();
			return vo;
		}
	}

	public boolean setGood(String apprId, String openid) {
		Connection dbConn;
		try {
			dbConn = wxds.getConnection();
			dbConn.setAutoCommit(false);
			boolean flag = false;
			ResultSet rs = dbConn.createStatement().executeQuery(
					"select id from itg_weixin_message_good where appr_id="
							+ apprId + " and openid='" + openid + "'");

			while (rs.next()) {
				flag = true;
			}

			if (flag) {
				return false;
			}

			PreparedStatement stmt = dbConn
					.prepareStatement("insert into itg_weixin_message_good(appr_id,openid) values(?,?)");

			stmt.setInt(1, Integer.valueOf(apprId).intValue());
			stmt.setString(2, openid);
			stmt.execute();

			PreparedStatement stmt1 = dbConn
					.prepareStatement("update itg_weixin_message_appr set good_count=good_count+1 where id=?");
			stmt1.setInt(1, Integer.valueOf(apprId).intValue());
			stmt1.execute();

			dbConn.commit();
			dbConn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public String getAccessToken() {
		URL accessUrl;
		String responseContent = null;
		String appid = "wxf0364f589da8ea45";
		String secret = "c9d8f4f5898d4e02fac60a081a9db121";
		try {
			accessUrl = new URL(
					"https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
							+ appid + "&secret=" + secret);

			TrustManager[] tm = { new X509TrustManager() {
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
				}

				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
				}
			} };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			// 创建HttpsURLConnection对象，并设置其SSLSocketFactory对象
			HttpsURLConnection httpsConn = (HttpsURLConnection) accessUrl
					.openConnection();
			httpsConn.setSSLSocketFactory(ssf);

			// 取得该连接的输入流，以读取响应内容
			InputStreamReader insr = new InputStreamReader(
					httpsConn.getInputStream());
			BufferedReader in = new BufferedReader(insr);
			String token = in.readLine();
			// System.out.print(in.readLine());
			return token;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public String getButtons() {
		String buttonPath = servletContext
				.getRealPath("/WEB-INF/conf/button.xml");
		try {
			FileInputStream fio = new FileInputStream(new File(buttonPath));
			InputStreamReader insr = new InputStreamReader(fio, "utf-8");
			BufferedReader in = new BufferedReader(insr);
			String xml = "";
			String lineTxt = null;
			while ((lineTxt = in.readLine()) != null) {
				xml += lineTxt;
			}
			String json = "{\"button\":" + xml2JSON(xml) + "}";
			return json;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}

	}

	public String xml2JSON(String xml) {
		return new XMLSerializer().read(xml).toString();
	}

	public String getFirstImgTag(String content) {
		String r_content = getRightHTML(content);

		Pattern p = Pattern.compile("(<[Ii][Mm][Gg].+?src=['|\"](.*?)['|\"])");
		Matcher m = p.matcher(r_content);
		while (m.find()) {
			// System.out.println(m.group(2));
			return m.group(2);
		}

		return "";
	}

	@RequestMapping(value = "/pushSlogan.html")
	public ModelAndView pushSlogan(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("slogan/pushSlogan");
		model.addObject("title", "参与活动");
		return model;
	}

	@RequestMapping(value = "/collectSlogan.html")
	public ModelAndView collectSlogan(HttpServletRequest request,
			HttpServletResponse response) {
		String openid = request.getParameter("openid");
		if (openid == null || openid.trim().equalsIgnoreCase("")) {
			openid = (String) request.getSession().getAttribute("openid");
			if (openid == null || openid.trim().equalsIgnoreCase("")) {
				ModelAndView model = new ModelAndView("error");
				model.addObject("title", "提示");
				model.addObject("errorMsg", "无法访问此页面");
				return model;
			} else {
				request.getSession().removeAttribute("truename");
			}
		}
		request.getSession().setAttribute("openid", openid);

		if (openid != null && !openid.trim().equalsIgnoreCase("")) {
			PsnInfoVO psnInfo = service.checkOpenId(openid);
			if (psnInfo.getResultCode().getValue() == 0) {

				request.getSession().setAttribute("psnInfo", psnInfo);
				request.getSession().setAttribute("truename",
						psnInfo.getPsnname().getValue());
				request.getSession().setAttribute("mobile",
						psnInfo.getMobile().getValue());
			}
		}
		ModelAndView model = new ModelAndView("slogan/collectSlogan");
		return model;
	}

	@RequestMapping(value = "/addSlogan.html")
	public ModelAndView addSolgan(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		try {
			String openid = (String) request.getSession()
					.getAttribute("openid");
			String truename = request.getParameter("truename");
			String mobile = request.getParameter("mobile");
			String content = request.getParameter("content");
			Slogan s = new Slogan();
			s.setOpenid(openid);
			s.setMobile(mobile);
			s.setTruename(truename);
			s.setContent(content);
			SimpleDateFormat matter1 = new SimpleDateFormat(
					"yyyy-MM-dd hh:mm:ss");
			s.setCreateDate(matter1.format(new Date()));
			mongoTemplate.insert(s);
			ModelAndView model = new ModelAndView("info");
			model.addObject("title", "提示");
			model.addObject("infoMsg", "成功提交");
			model.addObject("redirect", "pushSlogan.html");
			return model;
			// String jsonStr = "{\"code\":0,\"msg\":\"成功发送!\"}";
			// response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "提交失败，请重新尝试");
			model.addObject("redirect", "collectSlogan.html");
			return model;
			// String jsonStr = "{\"code\":999,\"msg\":\"提交失败!\"}";
			// response.getOutputStream().write(jsonStr.getBytes("utf-8"));
		}

	}

	@RequestMapping(value = "/getSlogans.html")
	public ModelAndView getSlogans(HttpServletRequest request,
			HttpServletResponse response) throws UnsupportedEncodingException,
			IOException {
		String openid = (String) request.getSession().getAttribute("openid");
		if (openid == null || openid.trim().equalsIgnoreCase("")) {
			ModelAndView model = new ModelAndView("error");
			model.addObject("title", "提示");
			model.addObject("errorMsg", "无法访问此页面");
			return model;
		}
		Query query = new Query();

		BasicDBObject cond = new BasicDBObject();
		BasicDBObject keys = new BasicDBObject();
		DBCollection coll = mongoTemplate.getCollection("slogan");
		cond.put("openid", openid);
		keys.put("_id", 0);
		// keys.put("_class", 0);
		keys.put("openid", 1);
		keys.put("truename", 1);
		keys.put("mobile", 1);
		keys.put("email", 1);
		keys.put("createDate", 1);
		keys.put("content", 1);
		DBCursor cur = coll.find(cond, keys);
		BasicDBList dblist = new BasicDBList();
		List<DBObject> list = cur.toArray();
		for (DBObject dbObject : list) {
			dblist.add(dbObject);

		}
		// System.out.println(dblist.toString());
		ModelAndView model = new ModelAndView("slogan/mySlogan");
		model.addObject("title", "我发布的口号");
		model.addObject("slogans", dblist.toString());
		return model;
		// mongoTemplate.findAll(Slogan.class);
		// response.getOutputStream().write("aaa".getBytes("utf-8"));
	}

	@RequestMapping(value = "/allSlogans.html")
	public ModelAndView allSlogans(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("slogan/allSlogans");
		model.addObject("title", "主题口号");
		return model;
	}

	@RequestMapping(value = "/getAllSlogans.html")
	@ResponseBody
	public void getAllSlogans(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int page = 1, rows = 20;
		String pPage = request.getParameter("page");
		String pRows = request.getParameter("rows");

		if (pPage != null) {
			page = Integer.valueOf(pPage);
		}
		if (pRows != null) {
			rows = Integer.valueOf(pRows);
			if (rows == 10) {
				rows = 15;
			}
		}

		BasicDBObject cond = new BasicDBObject();
		BasicDBObject keys = new BasicDBObject();
		BasicDBObject orderBy = new BasicDBObject();
		DBCollection coll = mongoTemplate.getCollection("slogan");

		cond.put("content", new BasicDBObject(QueryOperators.EXISTS, true));

		keys.put("_id", 0);
		// keys.put("_class", 0);
		keys.put("openid", 1);
		keys.put("truename", 1);
		keys.put("mobile", 1);
		keys.put("email", 1);
		keys.put("createDate", 1);
		keys.put("content", 1);

		orderBy.put("openid", 1);
		orderBy.put("createDate", 1);
		DBCursor cur;
		if (page > 1) {
			cur = coll.find(cond, keys).sort(orderBy).skip((page - 1) * rows)
					.limit(rows);
		} else {
			cur = coll.find(cond, keys).sort(orderBy).limit(rows);
		}

		BasicDBList dblist = new BasicDBList();
		List<DBObject> list = cur.toArray();
		for (DBObject dbObject : list) {
			dblist.add(dbObject);

		}
		BasicDBObject rs = new BasicDBObject();
		rs.put("total", coll.find(cond, keys).size());
		rs.put("rows", dblist);
		rs.put("rs", coll.distinct("truename"));
		// System.out.println(dblist.toString());
		// ModelAndView model = new ModelAndView("slogan/mySlogan");
		// model.addObject("title", "我发布的口号");
		// model.addObject("slogans", dblist.toString());
		// return model;
		// mongoTemplate.findAll(Slogan.class);
		response.getOutputStream().write(rs.toString().getBytes("utf-8"));
	}
}
