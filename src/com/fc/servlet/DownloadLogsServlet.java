package com.fc.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.fc.bean.FcServiceLogBean;
import com.fc.bean.M2FServiceLogBean;
import com.fc.dao.db.SaveDmsDataDao;
import com.fc.util.DateUtil;
import com.fc.util.DownloadLogsUtil;
import com.fc.util.XMLString;
import com.fc.util.XmlSaveUtil;

public class DownloadLogsServlet extends HttpServlet {
	private static final long serialVersionUID = -2869003506126974953L;
	private static Logger log = Logger.getLogger(DownloadLogsServlet.class);
	private XmlSaveUtil util = new XmlSaveUtil();
	private static Map<Integer, File> stMap = new HashMap<Integer, File>();
	private SaveDmsDataDao dao = new SaveDmsDataDao();
	private DownloadLogsUtil dowUtil = new DownloadLogsUtil();
	private static Map<String, String> fileMap = new HashMap<String, String>();

	/**
	 * Constructor of the object.
	 */
	public DownloadLogsServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		fileMap.clear();
		super.destroy(); // Just puts "destroy" string in log
	}

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dowUtil.db(request, response);
		String is = request.getParameter("D");
		String pasd = request.getParameter("passwd");
		Object sessionPwd = request.getSession().getAttribute("PassWD");
		
		if (sessionPwd == null || pasd!=null ) {
			if (pasd != null && !"".equals(pasd)) {
				String passwds = util.SET_WD;
				if (passwds != null) {
					if (passwds.equals(XMLString.MD5(pasd))) {
						request.getSession().setAttribute("PassWD", XMLString.MD5(pasd));
					} else {
						printMessage("<font color='red' >ERROR密码错误!</font>",
								request, response);
					}
				} else {
					printMessage("<font color='red' >ERROR默认文件设定密码为空!</font>",
							request, response);
				}

			} else {
				printMessage("<font color='red' >ERROR密码错误!</font>", request,
						response);
			}
		} else {
			String passwds = util.SET_WD;
			if (passwds != null) {
				if (!(passwds.equals(sessionPwd.toString()))) {
					printMessage("<font color='red' >ERROR密码错误!</font>",
							request, response);
				}
			}else{
				printMessage("<font color='red' >ERROR默认文件设定密码为空!</font>",
						request, response);
			}
		}
		if (is != null && !"".equals(is)) {
			String md = XMLString.MD5("FC" + getDate());
			if (md.equals(is)) {
				String number = request.getParameter("number");
				File file = stMap.get(Integer.parseInt(number));
				download(file, request, response);
			} else {
				boolean isCQ = true;
				int temp = 0;
				try {
					String numb = request.getParameter("number");
					temp = Integer.parseInt(numb);
				} catch (Exception e) {
					isCQ = false;
				}
				if (isCQ) {
					if (temp >= DownloadLogsUtil.dates.length) {
						printMessage("<font color='red' >ERROR错误的请求!</font>",
								request, response);
					} else {
						String fileMd = XMLString.MD5("DOWFILEDATA"
								+ DownloadLogsUtil.dates[temp] + getDate());
						if (fileMd.equals(is)) {
							File zipFile = dowUtil.dowZipData(temp);
							if (zipFile != null) {
								download(zipFile, request, response);
							} else {
								printMessage(
										"<font color='red'>ERROR文件下载错误!</font>",
										request, response);
							}
						} else {
							printMessage(
									"<font color='red'>ERROR错误的请求!</font>",
									request, response);
						}
					}
				} else {
					printMessage("<font color='red'>ERROR错误的请求!</font>",
							request, response);
				}
			}
		} else {
			String ml = request.getParameter("ML");
			if (ml != null && !"".equals(ml)) {
				if (fileMap != null && fileMap.size() > 0) {
					String path = fileMap.get(ml);
					if (path != null && !"".equals(path)) {
						File f = new File(path);
						if (f.isDirectory()) {
							doPost(request, response, f);
						} else {
							download(f, request, response);
						}
					} else {
						doPost(request, response, null);
					}
				} else {
					response.sendRedirect("index.jsp?message=ERROR");
				}
			} else {
				doPost(request, response, null);
			}
		}
	}

	/**
	 * 打印信息
	 */
	private void printMessage(String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		out.println(message);
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	/**
	 * 选项卡对应面板
	 */
	public void doPost(HttpServletRequest request,
			HttpServletResponse response, File f) throws ServletException,
			IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		request.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();

		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("<HEAD><title>FC数据处理系统日志观察</title>");
		out.println(style());
		out.println(script());
		out.println("</HEAD><BODY>");
		out.println("<div id='Tab'>");

		out.println(menu());

		out.println("<div class='Contentbox'> ");
		out.println(panel1(f));
		out.println(panel2());
		out.println("</div>");

		out.println("</div>");
		out.println("</BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

	/**
	 * 选项卡样式
	 * 
	 * @return
	 */
	private String style() {
		StringBuilder bud = new StringBuilder();
		bud
				.append("<style type='text/css'>")
				.append("body{font:'宋体';font-size:12px;}")
				.append(
						"a:link,a:visited{font-size:12px;color:#666;text-decoration:none;}")
				.append("a:hover{color:black;text-decoration:underline;}")
				.append("#Tab{margin:0 auto;width:100%;border:1px solid Gray}")
				.append(
						".Menubox{height:28px;border-bottom:1px solid Gray;background:LightGray}")
				.append(
						".Menubox ul{list-style:none;margin:7px 40px;padding:0;position:absolute;}")
				.append(
						".Menubox ul li{float:left;background:Gray;line-height:20px;display:block;cursor:pointer;width:120px;text-align:center;color:#fff;font-weight:bold;border-top:1px solid Gray;border-left:1px solid Gray;border-right:1px solid Gray;}")
				.append(
						".Menubox ul li.hover{background:#fff;border-bottom:1px solid #fff;color:black;}")
				.append(
						".Contentbox{clear:both;margin-top:0px;border-top:none;padding-top:8px;}")
				.append(".Contentbox ul{list-style:none;margin:7px;padding:0;}")
				.append(
						".Contentbox ul li{line-height:24px;border-bottom:1px dotted #ccc;}")
				.append(" </style>");
		return bud.toString();
	}

	/**
	 * javaScript脚本
	 * 
	 * @return
	 */
	private String script() {
		StringBuilder bud = new StringBuilder();
		bud.append("<script>");
		bud.append("function setTab(name,cursel,n){").append(
				"for(i=1;i<=n;i++){").append(
				"var menu=document.getElementById(name+i);").append(
				"var con=document.getElementById('con_'+name+'_'+i);").append(
				"menu.className=i==cursel?'hover':'';").append(
				"con.style.display=i==cursel?'block':'none';").append("}")
				.append("}");
		bud.append("</script>");
		return bud.toString();
	}

	// 选项卡1
	private String panel1(File fty) {
		StringBuilder bud = new StringBuilder();
		if (DateUtil.logFileDow != null) {
			bud.append(DateUtil.logFileDow);
		} else {
			File f = new File("D:\\PYSYSTEM\\logs");
			if (f.exists()) {
				File[] fil = f.listFiles();
				int k = 1;
				for (File fi : fil) {
					stMap.put(k, fi);
					k++;
				}
			}
			bud.append("<div id='con_menu_1' class='hover'><ul>");
			if (stMap != null && stMap.size() > 0) {
				bud
						.append("<table border='1' width='100%' style='font-size:11pt;border-collapse:collapse;'   ><tr><td colspan='2' ><b>【文件日志列表】</b></td></tr><tr style='background-color:LightGray' ><td><b>文件名</b></td><td style='width:50px' ><b>操作</b></td></tr>");
				String a = XMLString.MD5("FC" + getDate());
				for (Entry<Integer, File> ent : stMap.entrySet()) {
					bud
							.append("<tr><td>" + ent.getValue().getName()
									+ "</td><td><a href='dowloadLOG.do?D=" + a
									+ "&number=" + ent.getKey()
									+ "' >下载</a></td></tr>");
				}
				bud.append("</table><br/>");
			}
			DateUtil.logFileDow = bud.toString();
		}
		String qjbud = createLogs();
		if (qjbud != null && !"".equals(qjbud) && qjbud.length() > 0) {
			bud.append(qjbud);
		}
		String m2log = createM2ServiceLog();
		if (m2log != null && !"".equals(m2log) && m2log.length() > 0) {
			bud.append(m2log);
		}
		if (fty != null) {
			bud.append(printDirectoryInfo(fty));
		} else {
			File fy = new File("D:\\PYSYSTEM");
			bud.append(printDirectoryInfo(fy));
		}
		bud.append("</ul></div>");
		return bud.toString();
	}

	// 选项卡2
	private String panel2() {
		StringBuilder bud = new StringBuilder();
		bud.append("<div id='con_menu_2' style='display:none'><ul>");
		if (DateUtil.logFileInfoDow != null) {
			bud.append(DateUtil.logFileInfoDow);
		} else {
			bud.append(dowUtil.getFilesInfo());
		}
		bud.append("</ul></div>");
		return bud.toString();
	}

	// 菜单
	private String menu() {
		StringBuilder bud = new StringBuilder();
		bud
				.append("<div class='Menubox'><ul>")
				.append(
						"<li id='menu1' onclick=\"setTab('menu',1,2)\" class='hover'>系统全局日志</li>")
				.append(
						"<li id='menu2' onclick=\"setTab('menu',2,2)\" >数据处理信息</li>")
				.append("</ul></div>");
		return bud.toString();
	}

	/**
	 * 创建售前全局日志
	 * 
	 * @return
	 */
	private String createM2ServiceLog() {
		StringBuilder bud = new StringBuilder();
		try {
			List<M2FServiceLogBean> m2Service = dao.getM2LogBean();
			bud
					.append(
							"<table border='1' width='100%' style='font-size:11pt;border-collapse:collapse;'   >")
					.append(
							"<tr><td colspan='9' ><b>【售前业务全局日志列表】</b></td></tr>")
					.append("<tr style='background-color:LightGray' >")
					.append("<td style='text-align:center' ><b>业务开始时间</b></td>")
					.append(
							"<td style='text-align:center' ><b>CC是否在当天获取过售前数据</b></td>")
					.append(
							"<td style='text-align:center' ><b>系统当天是否接收到CC的售前数据</b></td>")
					.append(
							"<td style='text-align:center' ><b>系统当天是否提交数据至DMS处理</b></td>")
					.append(
							"<td style='text-align:center' ><b>系统当天是否获取到DMS反馈数据</b></td>")
					.append("</tr>");
			for (M2FServiceLogBean m2 : m2Service) {
				bud.append("<tr>").append(
						"<td style='text-align:center' >" + m2.getCREATE_DATE()
								+ "</td>").append(
						"<td style='text-align:center' >"
								+ getStr(Long.parseLong(m2
										.getIS_SUB_CALL_CENTER()
										+ "")) + "</td>").append(
						"<td style='text-align:center' >"
								+ getStr(Long.parseLong(m2
										.getIS_GET_CALL_CENTER()
										+ "")) + "</td>").append(
						"<td style='text-align:center' >"
								+ getStr(Long
										.parseLong(m2.getIS_SUB_DMS() + ""))
								+ "</td>").append(
						"<td style='text-align:center' >"
								+ getStr(Long
										.parseLong(m2.getIS_GET_DMS() + ""))
								+ "</td>").append("</tr>");
			}
			bud.append("</table>");
		} catch (Exception e) {
			log.error("获取售后全局日志异常！", e);
		}
		return bud.toString();
	}

	/**
	 * 遍历产售后生全局日志
	 */
	private String createLogs() {
		StringBuilder bud = new StringBuilder();
		try {
			List<FcServiceLogBean> fcservice = dao.getFcLogBean();
			if (fcservice.size() > 0) {
				bud
						.append(
								"<table border='1' width='100%' style='font-size:11pt;border-collapse:collapse;'   >")
						.append(
								"<tr><td colspan='9' ><b>【售后业务全局日志列表】</b></td></tr>")
						.append("<tr style='background-color:LightGray' >")
						.append(
								"<td style='text-align:center' ><b>业务开始时间</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否已获取DMS数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否校验车主数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否产生空错号数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否存在可OB的数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>CallCenter是否在当天获取过本数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>本系统是否在当天获取CallCenter数据</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否查询到CallCenter数据含空错号</b></td>")
						.append(
								"<td style='text-align:center' ><b>是否将空错号数据发送至DMS</b></td>")
						.append("</tr>");
				for (FcServiceLogBean fc : fcservice) {
					bud
							.append("<tr>")
							.append(
									"<td style='text-align:center' >"
											+ fc.getStartDate() + "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsDmsToData())
											+ "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsCheckedData())
											+ "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsProduceError())
											+ "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsSubCall())
											+ "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsCCGet()) + "</td>")
							.append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsZeitCC())
											+ "</td>").append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsCCError())
											+ "</td>").append(
									"<td style='text-align:center' >"
											+ getStr(fc.getIsSubDms())
											+ "</td>").append("</tr>");
				}
				bud.append("</table><br/>");
			}
		} catch (Exception e) {
			log.error("获取售后全局日志异常！", e);
		}
		return bud.toString();
	}

	/**
	 * 打印目录信息
	 * 
	 * @return
	 */
	private String printDirectoryInfo(File f) {
		StringBuilder bud = new StringBuilder();
		if (f.exists()) {
			File[] fDir = f.listFiles();
			bud
					.append(
							"<br/><table border='1' width='100%' style='font-size:11pt;border-collapse:collapse;'   >")
					.append(
							"<tr><td colspan='2' ><b>【FC数据处理系统相关操作目录】</b></td></tr>")
					.append(
							"<tr  style='background-color:LightGray' ><td colspan='2' >当前目录："
									+ f.getAbsolutePath() + "</td></tr>")
					.append("<tr>")
					.append(
							"<td style='text-align:center' ><b>文件或文件夹名称</b></td>")
					.append("<td style='text-align:center' ><b>相关操作</b></td>");
			fileMap.clear();
			for (int i = 0; i < fDir.length; i++) {
				bud.append("<tr>");
				fileMap.put(i + "", fDir[i].getAbsolutePath());
				if (fDir[i].isDirectory()) {
					bud.append("<td>" + fDir[i].getName() + "</td>");
					bud.append("<td><a href='dowloadLOG.do?ML=" + i
							+ "' >查看</a></td>");
				} else {
					bud.append("<td>" + fDir[i].getName() + "</td>");
					bud.append("<td><a href='dowloadLOG.do?ML=" + i
							+ "' >下载</a></td>");
				}
				bud.append("</tr>");
			}
			bud.append("</table>");
		}
		return bud.toString();
	}

	private String getStr(Long am) {
		return (am > 0 ? "是" : "否");
	}

	public HttpServletResponse download(File file, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			// 取得文件名。
			String filename = file.getName();
			// 取得文件的后缀名。
			// String ext = filename.substring(filename.lastIndexOf(".") +
			// 1).toUpperCase();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(filename.getBytes("UTF-8"), "UTF-8"));
			response.addHeader("Content-Length", "" + file.length());
			OutputStream toClient = new BufferedOutputStream(response
					.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return response;
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	private String getDate() {
		return XmlSaveUtil.formaty.format(new Date());
	}
}
