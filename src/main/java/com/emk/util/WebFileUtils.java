package com.emk.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.jeecgframework.core.common.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public class WebFileUtils
{
  public static void uploadFileToServer()
  {
    String str = "http://localhost:2906/Default.aspx?id=1&user=2&type=3";
    String filePath = "D:\\Wildlife.wmv";
    String fileName = "Wildlife.wmv";
    try
    {
      URL url = new URL(str);
      HttpURLConnection connection = (HttpURLConnection)url.openConnection();
      connection.setDoInput(true);
      connection.setDoOutput(true);
      connection.setRequestMethod("POST");
      connection.addRequestProperty("FileName", fileName);
      connection.setRequestProperty("content-type", "text/html");
      BufferedOutputStream out = new BufferedOutputStream(connection.getOutputStream());
      

      File file = new File(filePath);
      FileInputStream fileInputStream = new FileInputStream(file);
      byte[] bytes = new byte[1024];
      int numReadByte = 0;
      while ((numReadByte = fileInputStream.read(bytes, 0, 1024)) > 0) {
        out.write(bytes, 0, numReadByte);
      }
      out.flush();
      fileInputStream.close();
      
      DataInputStream localDataInputStream = new DataInputStream(connection.getInputStream());
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public static String saveFile(MultipartFile file, String webpath)
  {
    if (file.getSize() == 0L) {
      return null;
    }
    String timestamp = System.currentTimeMillis() + "";
    String filename = timestamp + "." + getFileSuffix(file.getOriginalFilename());
    try
    {
      FileUtils.copyInputStreamToFile(file.getInputStream(), new File(webpath, filename));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return filename;
  }
  
  public static ResponseEntity<byte[]> getDownloadFile(String localFilename, String downloadFilename, String webpath, boolean del)
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", URLEncoder.encode(downloadFilename, "UTF-8"));
    File file = new File(webpath + "/" + localFilename);
    ResponseEntity<byte[]> r = new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    if (del) {
      file.delete();
    }
    return r;
  }
  
  public static ResponseEntity<byte[]> getDownloadFile2(File file, boolean del)
    throws IOException
  {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
    headers.setContentDispositionFormData("attachment", URLEncoder.encode(file.getName(), "UTF-8"));
    
    ResponseEntity<byte[]> r = new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
    if (del) {
      file.delete();
    }
    return r;
  }
  
  public static String getFileSuffix(String filename)
  {
    if (filename == null) {
      return null;
    }
    int dot = filename.lastIndexOf(".");
    if (dot <= 0) {
      return "";
    }
    String suffix = filename.substring(dot + 1, filename.length());
    return suffix;
  }
  
  public static void downLoad(String filePath, HttpServletResponse response, boolean isOnLine)
    throws Exception
  {
    try
    {
      File f = new File(filePath);
      if (f != null)
      {
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));
        byte[] buf = new byte[1024];
        int len = 0;
        response.reset();
        if (isOnLine)
        {
          URL u = new URL(filePath);
          response.setContentType(u.openConnection().getContentType());
          

          response.setHeader("Content-Disposition", "inline; filename=" + new String(f.getName().getBytes("GBK"), "ISO8859-1"));
        }
        else
        {
          response.setContentType("application/x-msdownload");
          response.setHeader("Content-Disposition", "attachment; filename=" + new String(f.getName().getBytes("GBK"), "ISO8859-1"));
        }
        OutputStream out = response.getOutputStream();
        while ((len = br.read(buf)) > 0) {
          out.write(buf, 0, len);
        }
        out.flush();
        br.close();
        out.close();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
      throw new BusinessException(e.getMessage());
    }
  }
}
