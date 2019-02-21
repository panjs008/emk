package com.emk.util;

import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntityA;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.supplier.entity.EmkSupplierEntity;
import com.emk.storage.supplier.entity.EmkSupplierEntity2;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.service.custom.entity.YmkCustomEntity;
import com.service.custom.entity.YmkCustomEntityA;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;


public class createPdf {

    Document document = null;// 建立一个Document对象
    private static Font headFont ;
    private static Font titleFont ;
    private static Font keyFont ;
    private static Font textfont_H ;
    private static Font textfont_B ;
    int maxWidth = 520;


    static{
        BaseFont bfChinese_H;
        try {
            /**
             * 新建一个字体,iText的方法 STSongStd-Light 是字体，在iTextAsian.jar 中以property为后缀
             * UniGB-UCS2-H 是编码，在iTextAsian.jar 中以cmap为后缀 H 代表文字版式是 横版， 相应的 V 代表竖版
             */
            //bfChinese_H = BaseFont.createFont("STSong-Light","UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);
            bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            headFont = new Font(bfChinese_H, 10, Font.NORMAL);
            titleFont = new Font(bfChinese_H, 9, Font.NORMAL);
            keyFont = new Font(bfChinese_H, 18, Font.BOLD);
            textfont_H = new Font(bfChinese_H, 10, Font.NORMAL);
            textfont_B = new Font(bfChinese_H, 12, Font.NORMAL);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置页面属性
     * @param filed
     */
    public createPdf(File file) {

        //自定义纸张
        //Rectangle rectPageSize = new Rectangle(450, 250);
        //Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        // 定义A4页面大小
        Rectangle rectPageSize = new Rectangle(PageSize.A4);
        rectPageSize = rectPageSize.rotate();// 加上这句可以实现页面的横置
        document = new Document(rectPageSize,70, 80, 10, 40);

        try {
            PdfWriter.getInstance(document,new FileOutputStream(file));
            document.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 建表格(以列的数量建)
     * @param colNumber
     * @return
     */
    public PdfPTable createTable(int colNumber){
        PdfPTable table = new PdfPTable(colNumber);
        try{
            //table.setTotalWidth(maxWidth);
            //table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            table.setWidthPercentage(150);
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }

    /**
     * 建表格(以列的宽度比建)
     * @param widths
     * @return
     */
    public PdfPTable createTable(float[] widths){
        PdfPTable table = new PdfPTable(widths);
        try{
            table.setTotalWidth(maxWidth);
            table.setLockedWidth(true);
            table.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.getDefaultCell().setBorder(1);
            table.setSpacingBefore(10);
            //table.setWidthPercentage(150);

           /* table.setSpacingBefore(10f);
            table.setWidthPercentage(200);// 设置表格宽度为100%*/
        }catch(Exception e){
            e.printStackTrace();
        }
        return table;
    }


    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(align);
        cell.setPhrase(new Phrase(value,font));
        cell.setBorder(1);
        return cell;
    }

    /**
     * 表格中单元格
     * @param value
     * @param font
     * @param align
     * @param colspan
     * @param rowspan
     * @return
     */
    public PdfPCell createCell(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(20);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCell01(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(30);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCell02(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(180);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCellForGysImg(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(250);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    public PdfPCell createCellForCustom(String value,Font font,int align_v,int align_h,int colspan,int rowspan){
        PdfPCell cell = new PdfPCell();
        cell.setVerticalAlignment(align_v);
        cell.setHorizontalAlignment(align_h);
        cell.setColspan(colspan);
        cell.setRowspan(rowspan);
        //cell.setBorder(1);
        cell.setPhrase(new Phrase(value,font));
        cell.setMinimumHeight(80);
        cell.setBorderWidthTop(0.1f);
        cell.setBorderWidthBottom(0.1f);
        cell.setBorderWidthLeft(0.1f);
        cell.setBorderWidthRight(0.1f);
        return cell;
    }
    /**
     * 建短语
     * @param value
     * @param font
     * @return
     */
    public Phrase createPhrase(String value,Font font){
        Phrase phrase = new Phrase();
        phrase.add(value);
        phrase.setFont(font);
        return phrase;
    }

    /**
     * 建段落
     * @param value
     * @param font
     * @param align
     * @return
     */
    public Paragraph createParagraph(String value,Font font,int align){
        Paragraph paragraph = new Paragraph();
        paragraph.add(new Phrase(value,font));
        paragraph.setAlignment(align);
        return paragraph;
    }

    /**
     * 客户档案
     * @param emkEnquiryEntity  客户档案表实体
     * @return
     */
    public void generateEmkCustomPDF(YmkCustomEntityA customEntity) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("客  户  档  案  ",keyFont,Element.ALIGN_CENTER));
        document.add(createParagraph("档案编号："+customEntity.getCusNum(),headFont,Element.ALIGN_RIGHT));

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {15f,40f,15f,40f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);

        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务部门", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinesseDeptName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("客户代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinesserName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("客户地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("业务跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getTracerName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("生产跟单员", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getDeveloperName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCellForCustom("主营产品", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForCustom(customEntity.getMainContent(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("客户类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCusTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("业务类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBusinessType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("潜在业务量/年", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getQzywl(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("建立商业关系时间", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getRelationDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("区域", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getShengFen(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        table.addCell(createCell01("国家", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getGuoJia(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,3,1));

        document.add(table);
        document.add(createParagraph("【联系人】",headFont,Element.ALIGN_LEFT));
        float[] widths2 = {8f,15f,15f,15f,15f,15f,15f,15f};
        table = createTable(widths2);
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("业务联系人一", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("包装联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("测试联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("质量联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("验厂联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("船务联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("法律联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("姓名", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxr(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxrEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getBzlxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getZllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYclxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getCslxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getFllxrTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        document.add(table);

        document.add(createParagraph("【业务联系人】",headFont,Element.ALIGN_LEFT));
        table = createTable(widths2);
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人二", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人三", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人四", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("联系人五", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("姓名", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5Email(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr2Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr3Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr4Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(customEntity.getYwlxr5Telphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);
        document.add(blankRow41);
        document.close();
    }

    /**
     * 供应商档案表
     * @param emkEnquiryEntity  供应商档案表单实体
     * @return
     */
    public void generateEmkSupplierPDF(EmkSupplierEntity2 supplierEntity) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
//        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("供  应  商  档  案",keyFont,Element.ALIGN_CENTER));

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {25f,40f,15f,40f,15f,40f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);

        table.addCell(createCell01("供应商代码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplierCode(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("供应商类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplierType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("产品类型", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getProductType(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("企业全称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getSupplier(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCellForCustom("地址", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForCustom(supplierEntity.getAddress(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("纳税人识别号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getTaxpayerNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户行", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户账号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankAccount(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("开户账号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getBankAccount(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,5,1));

        table.addCell(createCell01("法定代表人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegaler(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegalerTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getLegalerEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacter(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacterTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getContacterEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("财务联系人", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacter(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("电话", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacterTelphone(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell01("邮箱", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(supplierEntity.getCwContacterEmail(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        document.add(table);

        document.newPage();
        String imagepath = "";
        Image image = null;
        if(Utils.notEmpty(supplierEntity.getLicenceUrl())){
            imagepath = supplierEntity.getLicenceUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,350);
            image.scaleAbsolute(400,220);
            document.add(image);
        }

        if(Utils.notEmpty(supplierEntity.getFactoryInfoUrl())){
            imagepath = supplierEntity.getFactoryInfoUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,100);
            image.scaleAbsolute(400,220);
            document.add(image);
        }


        float[] widths3 = {21f,200f};
        table = createTable(widths3);
        table.addCell(createCellForGysImg("营业执照", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCellForGysImg("工厂信息表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCellForGysImg(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        document.add(table);
        document.add(blankRow41);
        document.close();
    }

    /**
     * 意向询盘单
     * @param emkEnquiryEntity  意向询盘单实体
     * @param detailEntityList  明细数据
     * @return
     */
    public void generateEmkEnquiryPDF(EmkEnquiryEntityA emkEnquiryEntity, List<Map<String, Object>> detailEntityList) throws Exception{
        BaseFont bfChinese_H = BaseFont.createFont( "c://windows//fonts//simsun.ttc,1" , BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        com.itextpdf.text.Font FontChinese18 = new com.itextpdf.text.Font(bfChinese_H, 8, Font.NORMAL);
        Paragraph blankRow41 = new Paragraph(8f, " ", FontChinese18);
        //页头信息
        document.add(createParagraph("【XXXX有限公司】",headFont,Element.ALIGN_LEFT));
        document.add(createParagraph("意  向  询  盘  单",keyFont,Element.ALIGN_CENTER));
        document.add(createParagraph("意向订单号："+emkEnquiryEntity.getEnquiryNo(),headFont,Element.ALIGN_RIGHT));
        document.add(createParagraph("日期："+emkEnquiryEntity.getKdDate(),headFont,Element.ALIGN_RIGHT));
        document.add(createParagraph("生产跟单员："+emkEnquiryEntity.getDeveloperName(),headFont,Element.ALIGN_RIGHT));
        document.add(createParagraph("业务跟单员："+emkEnquiryEntity.getBusinesserName(),headFont,Element.ALIGN_RIGHT));

        //表格信息
        //float[] widths = {4f,10f,10f,20f,15f,8f,11f,12f,10f};
        float[] widths = {15f,40f,60f};
        //float[] widths = {28f,14f};
        PdfPTable table = createTable(widths);
        String imagepath = "";
        Image image = null;
        if(Utils.notEmpty(emkEnquiryEntity.getCustomSampleUrl())){
            imagepath = emkEnquiryEntity.getCustomSampleUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(450,305);
            image.scaleAbsolute(180,170);
            document.add(image);
        }

        table.addCell(createCell01("客户编号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getCusNum(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,6));

        table.addCell(createCell01("客户名称", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getCusName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getSampleNo(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("工艺种类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getGyzl(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("描述", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getSampleNoDesc(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("款式大类", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getProTypeName(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        document.add(table);

        float[] widths2 = {15f,15f,15f,15f,15f,15f,15f};
        table = createTable(widths2);

        table.addCell(createCell("颜色", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("色号", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,2));
        table.addCell(createCell("尺码", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,5,1));

        table.addCell(createCell("S", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("M", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("L", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("XL", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell("XXL", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        for(Map map : detailEntityList){
            table.addCell(createCell(map.get("colorName").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("colorVal").toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("stotal") != null ? map.get("stotal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("mtotal") != null ? map.get("mtotal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("ltotal") != null ? map.get("ltotal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("xltotal") != null ? map.get("xltotal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
            table.addCell(createCell(map.get("xxltotal") != null ? map.get("xxltotal").toString():"", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        }
        document.add(table);

        document.newPage();
        float[] widths3 = {21f,60f,21f,60f};
        table = createTable(widths3);

        if(Utils.notEmpty(emkEnquiryEntity.getOldImageUrl())){
            imagepath = emkEnquiryEntity.getOldImageUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,405);
            image.scaleAbsolute(180,170);
            document.add(image);
        }


        if(Utils.notEmpty(emkEnquiryEntity.getDgrImageUrl())){
            imagepath = emkEnquiryEntity.getDgrImageUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(490,405);
            image.scaleAbsolute(180,170);
            document.add(image);
        }

        table.addCell(createCell02("客户原样", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell02("设计稿", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        if(Utils.notEmpty(emkEnquiryEntity.getSizeImageUrl())){
            imagepath = emkEnquiryEntity.getSizeImageUrl();
            image = Image.getInstance(imagepath);
            image.setAlignment(image.UNDERLYING);
            image.setAbsolutePosition(230,230);
            image.scaleAbsolute(180,170);
            document.add(image);
        }

        table.addCell(createCell02("尺寸表", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));
        table.addCell(createCell02("", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell02(" ", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_LEFT,1,1));

        table.addCell(createCell01("意向大货交期", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getYsDate(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01("距交期剩天数", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getLevelDays().toString(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));

        table.addCell(createCell01("备注", textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,1,1));
        table.addCell(createCell01(emkEnquiryEntity.getRemark(), textfont_H,Element.ALIGN_MIDDLE, Element.ALIGN_CENTER,3,1));

        document.add(table);
        document.add(blankRow41);

        document.close();
    }
    public static void main(String[] args) throws Exception {
        File file = new File("d:\\PDF\\T3.pdf");
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        file.delete();
/*
        try {
            // 第一步，实例化一个document对象
            Document document = new Document();
            // 第二步，设置要到出的路径
            // FileOutputStream out = new  FileOutputStream("H:/workbook111.pdf");
            FileOutputStream out = new  FileOutputStream("d:\\PDF\\T3.pdf");
            //如果是浏览器通过request请求需要在浏览器中输出则使用下面方式
            //OutputStream out = response.getOutputStream();
            // 第三步,设置字符
            BaseFont bfChinese = BaseFont.createFont("C:/windows/fonts/simsun.ttc,1", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            //BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", false);
            Font fontZH = new Font(bfChinese, 12.0F, 0);
            // 第四步，将pdf文件输出到磁盘
            PdfWriter writer = PdfWriter.getInstance(document, out);
            // 第五步，打开生成的pdf文件
            document.open();
            // 第六步,设置内容
            String title = "导出pdf测试的情况";
            document.add(new Paragraph(new Chunk(title, fontZH).setLocalDestination(title)));
            document.add(new Paragraph("\n"));
            // 创建table,注意这里的2是两列的意思,下面通过table.addCell添加的时候必须添加整行内容的所有列
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100.0F);
            //第一列是列表名
            table.setHeaderRows(1);

            table.getDefaultCell().setHorizontalAlignment(1);
            table.addCell(new Paragraph("序号", fontZH));
            table.addCell(new Paragraph("性别", fontZH));
            table.addCell(new Paragraph("姓名", fontZH));
            table.addCell(new Paragraph("年龄", fontZH));
            table.addCell(new Paragraph("1", fontZH));
            table.addCell(new Paragraph("男", fontZH));
            table.addCell(new Paragraph("测试名字1", fontZH));
            table.addCell(new Paragraph("20", fontZH));
            table.addCell(new Paragraph("2", fontZH));
            table.addCell(new Paragraph("女", fontZH));
            table.addCell(new Paragraph("测试名字2", fontZH));
            table.addCell(new Paragraph("21", fontZH));

            document.add(table);
            document.add(new Paragraph("\n"));

            // 第七步，关闭document
            document.close();
            System.out.println("导出pdf成功~");

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }*/

    }
}
