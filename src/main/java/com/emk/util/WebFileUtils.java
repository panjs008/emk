package com.emk.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 文件上传目录将统一存放到/WEB-INF/upload下，没有子目录
 */
public class WebFileUtils {

	public static void uploadFileToServer(){
		String str="http://localhost:2906/Default.aspx?id=1&user=2&type=3";
		String filePath="D:\\Wildlife.wmv";
		String fileName="Wildlife.wmv";
		try {
			URL url=new URL(str);
			HttpURLConnection connection=(HttpURLConnection)url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.addRequestProperty("FileName", fileName);
			connection.setRequestProperty("content-type", "text/html");
			BufferedOutputStream out=new BufferedOutputStream(connection.getOutputStream());

			//读取文件上传到服务器
			File file=new File(filePath);
			FileInputStream fileInputStream=new FileInputStream(file);
			byte[]bytes=new byte[1024];
			int numReadByte=0;
			while((numReadByte=fileInputStream.read(bytes,0,1024))>0)
			{
				out.write(bytes, 0, numReadByte);
			}

			out.flush();
			fileInputStream.close();
			//读取URLConnection的响应
			DataInputStream in=new DataInputStream(connection.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * @param file
	 * @param webpath request.getSession().getServletContext().getRealPath("/")+保存目录;
	 * @return 处理后的文件名(时间戳) 下载时使用
	 */
	public static String saveFile(MultipartFile file, String webpath){
		if(file.getSize()==0){
			return null;
		}

		String timestamp= System.currentTimeMillis()+"";
		String suffix = WebFileUtils.getFileSuffix(file.getOriginalFilename());
		String filename=timestamp+"."+suffix;
		try {
			if(suffix.equalsIgnoreCase("jpg") || suffix.equalsIgnoreCase("jpeg")
					|| suffix.equalsIgnoreCase("png") || suffix.equalsIgnoreCase("gif")){
				Image img = ImageIO.read(file.getInputStream());
				// 获取图片宽高
				int w = img.getWidth(null);
				int h = img.getHeight(null);
				// 图片设置宽高最大650
				if(w > 650){
					h = (int) (h * (650 / (double) w));
					w = 650;
				}
				if(h > 650){
					w = (int) (w * (650 / (double) h));
					h = 650;
				}
				// 绘制图像
				BufferedImage tag = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
				tag.getGraphics().drawImage(img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null);
				// 文件夹不存在，则新建一个
				File saveFile = new File(webpath);
				if(!saveFile.exists()){
					saveFile.mkdirs();
				}
				ImageIO.write(tag, suffix, new File(webpath,filename));
			}else{
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(webpath,filename));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filename;
	}
	
	/**
	 * @param localFilename 本地存储文件名
	 * @param downloadFilename  下载文件使用的名字
	 * @param webpath	存储路径
	 * @param del	下载后是否删除文件
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> getDownloadFile(String localFilename, String downloadFilename, String webpath, boolean del) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(downloadFilename, "UTF-8"));
		File file=new File(webpath+"/"+localFilename);
		ResponseEntity<byte[]> r=new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		if(del){
			file.delete();
		}
		return r;
	}
	
	/**
	 * @param
	 * @param
	 * @param
	 * @param del	下载后是否删除文件
	 * @return
	 * @throws IOException
	 */
	public static ResponseEntity<byte[]> getDownloadFile2(File file, boolean del) throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
		//File file=new File(webpath+"/"+localFilename);
		ResponseEntity<byte[]> r=new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
		if(del){
			file.delete();
		}
		return r;
	}
	
	/**
	 * 获取文件后缀
	 * @param filename
	 * @return
	 */
	public static String getFileSuffix(String filename){
		if(filename==null){
			return null;
		}
		int dot=filename.lastIndexOf(".");
		if(dot<=0){
			return "";
		}
		String suffix=filename.substring(dot+1,filename.length());
		return suffix;
	}

	public static int getFileSize(String path){
		FileInputStream fis= null;
		int filesize = 0;
		try{
			File f = new File(path);
			fis = new FileInputStream(f);
			filesize = fis.available();
		}catch(Exception e){

		} finally{
			if (null!=fis){
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		return  filesize;
	}

	public static Workbook createWorkBook(File file) throws Exception {
		if (file.getName().toLowerCase().endsWith("xlsx")) {
			return new XSSFWorkbook(new FileInputStream(file));
		}
		if (file.getName().toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(new FileInputStream(file));
		}
		return null;
	}


	public static HSSFWorkbook createHSSFWorkBook(File file) throws Exception {
		if (file.getName().toLowerCase().endsWith("xlsx")) {
			return new HSSFWorkbook(new FileInputStream(file));
		}
		if (file.getName().toLowerCase().endsWith("xls")) {
			return new HSSFWorkbook(new FileInputStream(file));
		}
		return null;
	}
	/**
	 * 下载文件
	 *
	 * @param filePath
	 * @param response
	 * @param isOnLine
	 * @throws Exception
	 */
	public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine) throws Exception {
		File f = new File(filePath);
		/*
		 * if (!f.exists()) { response.sendError(404, "File not found!");
		 * return; }
		 */
		BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
		byte[] buf = new byte[1024];
		int len = 0;
		response.reset(); // 非常重要
		// 在线打开方式
		if (isOnLine) {
			URL u = new URL(filePath);
			response.setContentType(u.openConnection().getContentType());
			// response.setHeader("Content-Disposition", "inline; filename=" +
			// toUTF8(f.getName()));
			response.setHeader("Content-Disposition",
					"inline; filename=" + new String(f.getName().getBytes("GBK"), "ISO8859-1"));
			// 文件名应该编码成UTF-8
		}
		// 纯下载方式
		else {
			response.setContentType("application/x-msdownload");
			response.setHeader("Content-Disposition",
					"attachment; filename=" + new String(f.getName().getBytes("GBK"), "ISO8859-1"));
			// response.setHeader("Content-Disposition", "attachment; filename="
			// + toUTF8(f.geztName()));
		}
		OutputStream out = response.getOutputStream();
		while ((len = br.read(buf)) > 0)
			out.write(buf, 0, len);
		out.flush();
		br.close();
		out.close();
	}

	public static void delete(String fileName){
		try {
			File file = new File(fileName);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
