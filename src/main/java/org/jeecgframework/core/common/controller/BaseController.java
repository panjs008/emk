package org.jeecgframework.core.common.controller;

import com.emk.approval.approvaldetail.entity.EmkApprovalDetailEntity;
import com.emk.check.sizecheck.entity.EmkSizeEntity;
import com.emk.check.sizecheck.entity.EmkSizeTotalEntity;
import com.emk.storage.enquiry.entity.EmkEnquiryEntity;
import com.emk.storage.enquirydetail.entity.EmkEnquiryDetailEntity;
import com.emk.storage.gl.entity.EmkGlEntity;
import com.emk.storage.pb.entity.EmkPbEntity;
import com.emk.storage.price.entity.EmkPriceEntity;
import com.emk.storage.sampledetail.entity.EmkSampleDetailEntity;
import com.emk.storage.samplegx.entity.EmkSampleGxEntity;
import com.emk.storage.sampleran.entity.EmkSampleRanEntity;
import com.emk.storage.samplerequired.entity.EmkSampleRequiredEntity;
import com.emk.storage.sampleyin.entity.EmkSampleYinEntity;
import com.emk.util.DateUtil;
import com.emk.util.Utils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.core.interceptors.DateConvertEditor;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.SendMailUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.sms.entity.TSSmsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 基础控制器，其他控制器需集成此控制器获得initBinder自动转换的功能
 * @author  张代浩
 * 
 */
@Controller
@RequestMapping("/baseController")
public class BaseController {
	@Autowired
	public SystemService systemService;
	/**
	 * 将前台传递过来的日期格式的字符串，自动转化为Date类型
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat(
//				"yyyy-MM-dd hh:mm:ss");
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, true));
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
	}

	/**
	 * 分页公共方法(非easyui)
	 * 
	 * @author Alexander
	 * @date 20131022
	 */
	public List<?> pageBaseMethod(HttpServletRequest request,
			DetachedCriteria dc, CommonService commonService, int pageRow) {
		// 当前页
		// 总条数
		// 总页数

		int currentPage = 1;

		int totalRow = 0;
		int totalPage = 0;
		// 获取当前页
		String str_currentPage = request.getParameter("str_currentPage");
		currentPage = str_currentPage == null || "".equals(str_currentPage) ? 1
				: Integer.parseInt(str_currentPage);
		// 获取每页的条数
		String str_pageRow = request.getParameter("str_pageRow");
		pageRow = str_pageRow == null || "".equals(str_pageRow) ? pageRow
				: Integer.parseInt(str_pageRow);

		// 统计的总行数
		dc.setProjection(Projections.rowCount());

		totalRow = Integer.parseInt(commonService.findByDetached(dc).get(0)
				.toString());
		totalPage = (totalRow + pageRow - 1) / pageRow;

		currentPage = currentPage < 1 ? 1 : currentPage;
		currentPage = currentPage > totalPage ? totalPage : currentPage;
		// 清空统计函数
		dc.setProjection(null);
		// dc.setResultTransformer(dc.DISTINCT_ROOT_ENTITY);
		List<?> list = commonService.pageList(dc, (currentPage - 1) * pageRow,
				pageRow);

		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pageRow", pageRow);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("totalPage", totalPage);
		return list;
	}

    /**
     * 抽取由逗号分隔的主键列表
     *
     * @param ids
     *            由逗号分隔的多个主键值
     * @return 主键类表
     * @author 张国明 2014-8-21 21:57:16
     */
    protected List<String> extractIdListByComma(String ids) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.hasText(ids)) {
            for (String id : ids.split(",")) {
                if (StringUtils.hasLength(id)) {
                    result.add(id.trim());
                }
            }
        }

        return result;
    }

	//保存样品需求单数据
	protected  String saveSampleRequire(EmkEnquiryEntity t){
		try {
			EmkSampleRequiredEntity emkSampleRequiredEntity = new EmkSampleRequiredEntity();
			MyBeanUtils.copyBeanNotNull2Bean(t,emkSampleRequiredEntity);
			Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(REQUIRED_NO, 3)),0)+1 AS signed) orderNum from emk_sample_required");
			emkSampleRequiredEntity.setRequiredNo("YPXP" + t.getCusNum() + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
			emkSampleRequiredEntity.setId(null);
			emkSampleRequiredEntity.setKdDate(DateUtil.getCurrentTimeString("yyyy-MM-dd"));
			emkSampleRequiredEntity.setFormType("1");
			emkSampleRequiredEntity.setIsEnquiry("0");
			emkSampleRequiredEntity.setState("0");
			systemService.save(emkSampleRequiredEntity);

			EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());
			EmkSizeEntity sizeEntity = new EmkSizeEntity();
			MyBeanUtils.copyBeanNotNull2Bean(emkSizeEntity,sizeEntity);
			sizeEntity.setId(null);
			sizeEntity.setFormId(emkSampleRequiredEntity.getId());
			systemService.save(sizeEntity);

			List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",t.getId());
			EmkEnquiryDetailEntity enquiryDetailEntity = null;
			EmkSizeTotalEntity sizeTotalEntity = null;
			for(EmkEnquiryDetailEntity emkEnquiryDetailEntity : emkEnquiryDetailEntityList){
				enquiryDetailEntity = new EmkEnquiryDetailEntity();
				MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity,enquiryDetailEntity);
				enquiryDetailEntity.setId(null);
				enquiryDetailEntity.setEnquiryId(emkSampleRequiredEntity.getId());

				systemService.save(enquiryDetailEntity);

				sizeTotalEntity = new EmkSizeTotalEntity();
				MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity.getEmkSizeTotalEntity(),sizeTotalEntity);
				sizeTotalEntity.setId(null);
				sizeTotalEntity.setpId(enquiryDetailEntity.getId());
				systemService.save(sizeTotalEntity);
			}
			return emkSampleRequiredEntity.getRequiredNo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	protected  String saveSampleRequire2(EmkEnquiryEntity t){
		try {
			EmkSampleRequiredEntity emkSampleRequiredEntity = new EmkSampleRequiredEntity();
			MyBeanUtils.copyBeanNotNull2Bean(t,emkSampleRequiredEntity);
			Map orderNum = systemService.findOneForJdbc("select CAST(ifnull(max(right(REQUIRED_NO, 3)),0)+1 AS signed) orderNum from emk_sample_required");
			emkSampleRequiredEntity.setRequiredNo("YPXP" + t.getCusNum() + DateUtil.format(new Date(), "yyMMdd") + String.format("%03d", Integer.valueOf(Integer.parseInt(orderNum.get("orderNum").toString()))));
			emkSampleRequiredEntity.setId(null);
			emkSampleRequiredEntity.setKdDate(DateUtil.getCurrentTimeString("yyyy-MM-dd"));
			emkSampleRequiredEntity.setFormType("1");
			emkSampleRequiredEntity.setIsEnquiry("0");
			emkSampleRequiredEntity.setState("0");
			systemService.save(emkSampleRequiredEntity);

			EmkSizeEntity emkSizeEntity = systemService.findUniqueByProperty(EmkSizeEntity.class,"formId",t.getId());
			EmkSizeEntity sizeEntity = new EmkSizeEntity();
			MyBeanUtils.copyBeanNotNull2Bean(emkSizeEntity,sizeEntity);
			sizeEntity.setId(null);
			sizeEntity.setFormId(emkSampleRequiredEntity.getId());
			systemService.save(sizeEntity);

			List<EmkEnquiryDetailEntity> emkEnquiryDetailEntityList = systemService.findHql("from EmkEnquiryDetailEntity where enquiryId = ?",t.getId());
			EmkEnquiryDetailEntity enquiryDetailEntity = null;
			EmkSizeTotalEntity sizeTotalEntity = null;
			for(EmkEnquiryDetailEntity emkEnquiryDetailEntity : emkEnquiryDetailEntityList){
				enquiryDetailEntity = new EmkEnquiryDetailEntity();
				MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity,enquiryDetailEntity);
				enquiryDetailEntity.setId(null);
				enquiryDetailEntity.setEnquiryId(emkSampleRequiredEntity.getId());

				systemService.save(enquiryDetailEntity);

				sizeTotalEntity = new EmkSizeTotalEntity();
				MyBeanUtils.copyBeanNotNull2Bean(emkEnquiryDetailEntity.getEmkSizeTotalEntity(),sizeTotalEntity);
				sizeTotalEntity.setId(null);
				sizeTotalEntity.setpId(enquiryDetailEntity.getId());
				systemService.save(sizeTotalEntity);
			}

			List<EmkPriceEntity> priceEntities = systemService.findHql("from EmkPriceEntity where xpNo=? order by id desc",t.getEnquiryNo());
			EmkPriceEntity emkPrice = priceEntities.get(0);

			//保存坯布参数
			EmkPbEntity emkPbEntity = new EmkPbEntity();
			EmkPbEntity emkPb = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",emkPrice.getId());
			MyBeanUtils.copyBeanNotNull2Bean(emkPb,emkPbEntity);
			emkPbEntity.setId(null);
			emkPbEntity.setPriceId(emkSampleRequiredEntity.getId());
			systemService.save(emkPbEntity);


			//保存原料辅料包装数据
			List<EmkSampleDetailEntity> emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ?",emkPrice.getId());
			EmkSampleDetailEntity emkSampleDetailEntity = null;
			for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
				emkSampleDetailEntity = new EmkSampleDetailEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
				emkSampleDetailEntity.setId(null);
				emkSampleDetailEntity.setSampleId(emkSampleRequiredEntity.getId());
				systemService.save(emkSampleDetailEntity);
			}
			//保存工序数据
			List<EmkSampleGxEntity> emkSampleGxEntityList = systemService.findHql("from EmkSampleGxEntity where sampleId = ?",emkPrice.getId());
			EmkSampleGxEntity emkSampleGxEntity = null;
			for(EmkSampleGxEntity sampleGxEntity : emkSampleGxEntityList){
				emkSampleGxEntity = new EmkSampleGxEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleGxEntity,emkSampleGxEntity);
				emkSampleGxEntity.setId(null);
				emkSampleGxEntity.setSampleId(emkSampleRequiredEntity.getId());
				systemService.save(emkSampleGxEntity);
			}
			//保存染色数据
			List<EmkSampleRanEntity> emkSampleRanEntityList = systemService.findHql("from EmkSampleRanEntity where sampleId = ?",emkPrice.getId());
			EmkSampleRanEntity emkSampleRanEntity = null;
			for(EmkSampleRanEntity sampleRanEntity : emkSampleRanEntityList){
				emkSampleRanEntity = new EmkSampleRanEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleRanEntity,emkSampleRanEntity);
				emkSampleRanEntity.setId(null);
				emkSampleRanEntity.setSampleId(emkSampleRequiredEntity.getId());
				systemService.save(emkSampleRanEntity);
			}
			//保存印花数据
			List<EmkSampleYinEntity> emkSampleYinEntityList = systemService.findHql("from EmkSampleYinEntity where sampleId = ?",emkPrice.getId());
			EmkSampleYinEntity emkSampleYinEntity = null;
			for(EmkSampleYinEntity sampleYinEntity : emkSampleYinEntityList){
				emkSampleYinEntity = new EmkSampleYinEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleYinEntity,emkSampleYinEntity);
				emkSampleYinEntity.setId(null);
				emkSampleYinEntity.setSampleId(emkSampleRequiredEntity.getId());
				systemService.save(emkSampleYinEntity);
			}
			return emkSampleRequiredEntity.getRequiredNo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//保存报价单数据
	protected  String savePrice(EmkEnquiryEntity t,String userName,String realName){
		try {
			EmkPriceEntity emkPrice = new EmkPriceEntity();
			MyBeanUtils.copyBeanNotNull2Bean(t,emkPrice);
			emkPrice.setId(null);
			emkPrice.setKdDate(DateUtil.getCurrentTimeString("yyyy-MM-dd"));
			emkPrice.setCreateBy(userName);
			emkPrice.setCreateName(realName);
			emkPrice.setPirceNo(emkPrice.getSampleNo() + DateUtil.format(new Date(), "yyMMdd"));
			emkPrice.setState("0");
			emkPrice.setFormType("1");
			emkPrice.setXpNo(t.getEnquiryNo());
			systemService.save(emkPrice);
			return emkPrice.getPirceNo();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	protected  String savePrice2(EmkEnquiryEntity t,EmkPriceEntity emkPrice,String userName,String realName,String sId){
		try {
			MyBeanUtils.copyBeanNotNull2Bean(t,emkPrice);
			emkPrice.setId(null);
			emkPrice.setKdDate(DateUtil.getCurrentTimeString("yyyy-MM-dd"));
			emkPrice.setCreateBy(userName);
			emkPrice.setCreateName(realName);
			emkPrice.setPirceNo(emkPrice.getSampleNo() + DateUtil.format(new Date(), "yyMMdd"));
			emkPrice.setState("0");
			emkPrice.setFormType("1");
			emkPrice.setXpNo(t.getEnquiryNo());
			systemService.save(emkPrice);

			//保存坯布参数
			EmkPbEntity emkPbEntity = new EmkPbEntity();
			EmkPbEntity emkPb = systemService.findUniqueByProperty(EmkPbEntity.class,"priceId",sId);
			MyBeanUtils.copyBeanNotNull2Bean(emkPb,emkPbEntity);
			emkPbEntity.setId(null);
			emkPbEntity.setPriceId(emkPrice.getId());
			systemService.save(emkPbEntity);


			//保存原料辅料包装数据
			List<EmkSampleDetailEntity> emkSampleDetailEntityList = systemService.findHql("from EmkSampleDetailEntity where sampleId = ?",sId);
			EmkSampleDetailEntity emkSampleDetailEntity = null;
			for(EmkSampleDetailEntity sampleDetailEntity : emkSampleDetailEntityList){
				emkSampleDetailEntity = new EmkSampleDetailEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleDetailEntity,emkSampleDetailEntity);
				emkSampleDetailEntity.setId(null);
				emkSampleDetailEntity.setSampleId(emkPrice.getId());
				systemService.save(emkSampleDetailEntity);
			}
			//保存工序数据
			List<EmkSampleGxEntity> emkSampleGxEntityList = systemService.findHql("from EmkSampleGxEntity where sampleId = ?",sId);
			EmkSampleGxEntity emkSampleGxEntity = null;
			for(EmkSampleGxEntity sampleGxEntity : emkSampleGxEntityList){
				emkSampleGxEntity = new EmkSampleGxEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleGxEntity,emkSampleGxEntity);
				emkSampleGxEntity.setId(null);
				emkSampleGxEntity.setSampleId(emkPrice.getId());
				systemService.save(emkSampleGxEntity);
			}
			//保存染色数据
			List<EmkSampleRanEntity> emkSampleRanEntityList = systemService.findHql("from EmkSampleRanEntity where sampleId = ?",sId);
			EmkSampleRanEntity emkSampleRanEntity = null;
			for(EmkSampleRanEntity sampleRanEntity : emkSampleRanEntityList){
				emkSampleRanEntity = new EmkSampleRanEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleRanEntity,emkSampleRanEntity);
				emkSampleRanEntity.setId(null);
				emkSampleRanEntity.setSampleId(emkPrice.getId());
				systemService.save(emkSampleRanEntity);
			}
			//保存印花数据
			List<EmkSampleYinEntity> emkSampleYinEntityList = systemService.findHql("from EmkSampleYinEntity where sampleId = ?",sId);
			EmkSampleYinEntity emkSampleYinEntity = null;
			for(EmkSampleYinEntity sampleYinEntity : emkSampleYinEntityList){
				emkSampleYinEntity = new EmkSampleYinEntity();
				MyBeanUtils.copyBeanNotNull2Bean(sampleYinEntity,emkSampleYinEntity);
				emkSampleYinEntity.setId(null);
				emkSampleYinEntity.setSampleId(emkPrice.getId());
				systemService.save(emkSampleYinEntity);
			}
			return emkPrice.getPirceNo();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	//单个保存消息发送邮件
	protected void saveSmsAndEmailForOne(String title, String content, TSUser receviceUser, String sendUser) throws Exception {
		TSSmsEntity smsEntity = new TSSmsEntity();
		smsEntity.setEsReceiver(receviceUser.getUserName());
		smsEntity.setEsTitle(title);
		smsEntity.setEsSender(sendUser);
		smsEntity.setEsStatus("0");
		smsEntity.setEsContent(content);
		systemService.save(smsEntity);
		if(Utils.notEmpty(receviceUser.getEmail())){
			SendMailUtil.sendCommonMail(receviceUser.getEmail(),title,content);
		}
	}
	//多个保存消息发送邮件
	protected void saveSmsAndEmailForMany(String role,String title,String content,String sendUser) throws Exception {
		List<TSUser> userList = systemService.findHql("from TSUser where userKey=?",role);
		for(TSUser tsUser : userList){
			TSSmsEntity smsEntity = new TSSmsEntity();
			smsEntity.setEsReceiver(tsUser.getUserName());
			smsEntity.setEsTitle(title);
			smsEntity.setEsSender(sendUser);
			smsEntity.setEsStatus("0");
			smsEntity.setEsContent(content);
			systemService.save(smsEntity);
			if(Utils.notEmpty(tsUser.getEmail())){
				SendMailUtil.sendCommonMail(tsUser.getEmail(),title,content);
			}
		}
	}

	protected void backProcess(String processId,String taskDef,String taskDef2,String taskDef2Name){
		systemService.executeSql("delete from act_hi_actinst  where proc_inst_id_=? and act_id_=? ",processId,taskDef);
		systemService.executeSql("delete from act_hi_taskinst where proc_inst_id_=? and task_def_key_=? ",processId,taskDef);
		systemService.executeSql("update act_ru_task set name_=?,task_def_key_=? where proc_inst_id_=? ",taskDef2Name,taskDef2,processId);
		systemService.executeSql("update act_ru_execution set rev_=(rev_-1),act_id_=? where id_=?", taskDef2,processId);
	}

	protected void saveApprvoalDetail(EmkApprovalDetailEntity approvalDetailEntity, String bpmName, String bpmNode, int approvalStatus, String approvalAdvice){
		approvalDetailEntity.setBpmName(bpmName);
		approvalDetailEntity.setBpmNode(bpmNode);
		approvalDetailEntity.setApproveStatus(approvalStatus);
		approvalDetailEntity.setApproveAdvice(approvalAdvice);
	}

	//保存和计算原料辅料染色人工印花成本
	protected void saveSampleDetail(EmkPriceEntity emkPrice,EmkGlEntity emkGlEntity,Map<String, String> map,String type,String sId){
		String sampleId = "";
		if(type.equals("0")){
			sampleId = emkPrice.getId();
			Map glBean = systemService.findOneForJdbc("select * from emk_gl where price_id=?",sampleId);
			if(Utils.notEmpty(glBean)){
				if(Utils.notEmpty(map.get("manageid"))){
					systemService.executeSql("delete from emk_gl where price_id=?",sampleId);
					emkGlEntity.setPriceId(sampleId);
					systemService.save(emkGlEntity);
				}
			}else{
				emkGlEntity.setPriceId(sampleId);
				systemService.save(emkGlEntity);
			}
		}else if(type.equals("1")){
			sampleId = sId;
		}

		String dataRows = map.get("orderMxListID");
		//保存原料面料数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=0",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].proZnName"))) {
					emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].proZnName"));
					emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].proNum"));
					emkSampleDetailEntity.setPrecent(map.get("orderMxList["+i+"].precent"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].yongliang"))){
						emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].yongliang")));
					}
					emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].gysCode"));
					emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].signPrice"));
					emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].unit"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].sunhaoPrecent"))){
						emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].sunhaoPrecent")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].chengben"))){
						emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].chengben")));
					}

					emkSampleDetailEntity.setSampleId(sampleId);
					emkSampleDetailEntity.setType("0");
					systemService.save(emkSampleDetailEntity);
				}
			}
		}

		dataRows = map.get("orderMxListID2");
		//保存缝制辅料数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=1",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].bproZnName"))) {
					emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].bproZnName"));
					emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].bproNum"));
					emkSampleDetailEntity.setPrecent(map.get("orderMxList["+i+"].bprecent"));
					emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].bbrand"));

					if(Utils.notEmpty(map.get("orderMxList["+i+"].byongliang"))){
						emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].byongliang")));
					}
					emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].bgysCode"));
					emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].bsignPrice"));
					emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].bunit"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].bsunhaoPrecent"))){
						emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].bsunhaoPrecent")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].bchengben"))){
						emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].bchengben")));
					}
					emkSampleDetailEntity.setSampleId(sampleId);
					emkSampleDetailEntity.setType("1");
					systemService.save(emkSampleDetailEntity);
				}
			}
		}
		dataRows = map.get("orderMxListID3");
		//保存包装辅料数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_detail where sample_id = ? and type=2",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleDetailEntity emkSampleDetailEntity = new EmkSampleDetailEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].cproZnName"))) {
					emkSampleDetailEntity.setProZnName(map.get("orderMxList["+i+"].cproZnName"));
					emkSampleDetailEntity.setProNum(map.get("orderMxList["+i+"].cproNum"));
					emkSampleDetailEntity.setPrecent(map.get("orderMxList["+i+"].cprecent"));
					emkSampleDetailEntity.setBrand(map.get("orderMxList["+i+"].cbrand"));

					if(Utils.notEmpty(map.get("orderMxList["+i+"].cyongliang"))){
						emkSampleDetailEntity.setYongliang(Double.parseDouble(map.get("orderMxList["+i+"].cyongliang")));
					}
					emkSampleDetailEntity.setGysCode(map.get("orderMxList["+i+"].cgysCode"));
					emkSampleDetailEntity.setSignPrice(map.get("orderMxList["+i+"].csignPrice"));
					emkSampleDetailEntity.setUnit(map.get("orderMxList["+i+"].cunit"));

					if(Utils.notEmpty(map.get("orderMxList["+i+"].csunhaoPrecent"))){
						emkSampleDetailEntity.setSunhaoPrecent(Double.parseDouble(map.get("orderMxList["+i+"].csunhaoPrecent")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].cchengben"))){
						emkSampleDetailEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].cchengben")));
					}

					emkSampleDetailEntity.setSampleId(sampleId);
					emkSampleDetailEntity.setType("2");
					systemService.save(emkSampleDetailEntity);
				}
			}
		}

		dataRows = map.get("gxListID");
		//保存工序数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_gx where sample_id = ?",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleGxEntity emkSampleGxEntity = new EmkSampleGxEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].gxmc"))) {
					emkSampleGxEntity.setGxmc(map.get("orderMxList["+i+"].gxmc"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].gxdjhs"))){
						emkSampleGxEntity.setDjhs(Double.parseDouble(map.get("orderMxList["+i+"].gxdjhs")));
					}
					emkSampleGxEntity.setUnit(map.get("orderMxList["+i+"].gxunit"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].gxproductTotal"))) {
						emkSampleGxEntity.setProductTotal(Integer.parseInt(map.get("orderMxList["+i+"].gxproductTotal")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].gxchengben"))) {
						emkSampleGxEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].gxchengben")));
					}
					emkSampleGxEntity.setSampleId(sampleId);
					systemService.save(emkSampleGxEntity);
				}
			}
		}

		dataRows = map.get("ranListID");
		//保存染色数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_ran where sample_id = ? and type=0",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleRanEntity emkSampleRanEntity = new EmkSampleRanEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].bmzl"))) {
					emkSampleRanEntity.setBuType(map.get("orderMxList["+i+"].bmzl"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].rsdj"))){
						emkSampleRanEntity.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].rsdj")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].rskz"))){
						emkSampleRanEntity.setOneWeight(Double.parseDouble(map.get("orderMxList["+i+"].rskz")));
					}
					if(Utils.notEmpty(map.get("orderMxList["+i+"].rscb"))){
						emkSampleRanEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].rscb")));
					}
					emkSampleRanEntity.setSampleId(sampleId);
					emkSampleRanEntity.setType("0");
					systemService.save(emkSampleRanEntity);
				}
			}
		}
		if(type.equals("0")){
			dataRows = map.get("zjListID");
			//保存助剂数据
			if (Utils.notEmpty(dataRows)) {
				systemService.executeSql("delete from emk_sample_ran where sample_id = ? and type=1",sampleId);
				int rows = Integer.parseInt(dataRows);
				for (int i = 0; i < rows; i++) {
					EmkSampleRanEntity emkSampleRanEntity = new EmkSampleRanEntity();
					if (Utils.notEmpty(map.get("orderMxList["+i+"].zjbmzl"))) {
						emkSampleRanEntity.setBuType(map.get("orderMxList["+i+"].zjbmzl"));
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zjrsdj"))){
							emkSampleRanEntity.setPrice(Double.parseDouble(map.get("orderMxList["+i+"].zjrsdj")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zjrskz"))){
							emkSampleRanEntity.setOneWeight(Double.parseDouble(map.get("orderMxList["+i+"].zjrskz")));
						}
						if(Utils.notEmpty(map.get("orderMxList["+i+"].zjrscb"))){
							emkSampleRanEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].zjrscb")));
						}
						emkSampleRanEntity.setSampleId(sampleId);
						emkSampleRanEntity.setType("1");
						systemService.save(emkSampleRanEntity);
					}
				}
			}
		}

		if(type.equals("1")){
			//保存明细数据
			dataRows = map.get("orderMxListIDSR");
			if (Utils.notEmpty(dataRows)) {
				this.systemService.executeSql("delete from emk_size_total where FIND_IN_SET(p_id,(SELECT GROUP_CONCAT(id) FROM emk_enquiry_detail where enquiry_id=?))", sId);
				systemService.executeSql("delete from emk_enquiry_detail where enquiry_id=?",sId);
				int rows = Integer.parseInt(dataRows);
				EmkEnquiryDetailEntity orderMxEntity = null;
				EmkSizeTotalEntity emkSizeTotalEntity = null;

				for (int i = 0; i < rows; i++) {
					if (Utils.notEmpty(map.get("orderMxList["+i+"].color"))){
						orderMxEntity = new EmkEnquiryDetailEntity();
						orderMxEntity.setEnquiryId(sId);
						orderMxEntity.setColor(map.get("orderMxList["+i+"].color"));
						orderMxEntity.setSortDesc(String.valueOf(i+1));
						orderMxEntity.setColorValue(map.get("orderMxList["+i+"].colorNum"));

						if(Utils.notEmpty(map.get("orderMxList["+i+"].sjTotal00"))){
							orderMxEntity.setSjtotal(Integer.parseInt(map.get("orderMxList["+i+"].sjTotal00")));
						}
						systemService.save(orderMxEntity);

						emkSizeTotalEntity = new EmkSizeTotalEntity();
						emkSizeTotalEntity.setTotalA(map.get("orderMxList["+i+"].totalA"));
						emkSizeTotalEntity.setTotalB(map.get("orderMxList["+i+"].totalB"));
						emkSizeTotalEntity.setTotalC(map.get("orderMxList["+i+"].totalC"));
						emkSizeTotalEntity.setTotalD(map.get("orderMxList["+i+"].totalD"));
						emkSizeTotalEntity.setTotalE(map.get("orderMxList["+i+"].totalE"));
						emkSizeTotalEntity.setTotalF(map.get("orderMxList["+i+"].totalF"));
						emkSizeTotalEntity.setTotalG(map.get("orderMxList["+i+"].totalG"));
						emkSizeTotalEntity.setTotalH(map.get("orderMxList["+i+"].totalH"));
						emkSizeTotalEntity.setTotalI(map.get("orderMxList["+i+"].totalI"));
						emkSizeTotalEntity.setTotalJ(map.get("orderMxList["+i+"].totalJ"));
						emkSizeTotalEntity.setTotalK(map.get("orderMxList["+i+"].totalK"));
						emkSizeTotalEntity.setpId(orderMxEntity.getId());
						systemService.save(emkSizeTotalEntity);
					}
				}
			}
		}


		dataRows = map.get("yinListID");
		//保存印花数据
		if (Utils.notEmpty(dataRows)) {
			systemService.executeSql("delete from emk_sample_yin where sample_id = ?",sampleId);
			int rows = Integer.parseInt(dataRows);
			for (int i = 0; i < rows; i++) {
				EmkSampleYinEntity emkSampleYinEntity = new EmkSampleYinEntity();
				if (Utils.notEmpty(map.get("orderMxList["+i+"].yhzl"))) {
					emkSampleYinEntity.setGyzy(map.get("orderMxList["+i+"].yhzl"));
					emkSampleYinEntity.setSize(map.get("orderMxList["+i+"].yhdx"));
					if(Utils.notEmpty(map.get("orderMxList["+i+"].yhcb"))){
						emkSampleYinEntity.setChengben(Double.parseDouble(map.get("orderMxList["+i+"].yhcb")));

					}
					emkSampleYinEntity.setSampleId(sampleId);

					systemService.save(emkSampleYinEntity);
				}
			}
		}
		if(type.equals("0")) {
			Map sumYlCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","0",emkPrice.getId());
			Map sumFzCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","1",emkPrice.getId());
			Map sumBzCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_detail where type=? and sample_id=?","2",emkPrice.getId());
			Map sumRgCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_gx where sample_id=?",emkPrice.getId());
			Map sumRanCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_ran where sample_id=? and type=0",emkPrice.getId());
			Map sumZjCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_ran where sample_id=? and type=1",emkPrice.getId());
			Map sumYinCb = systemService.findOneForJdbc("select ifnull(sum(chengben),0) sumCb from emk_sample_yin where sample_id=?",emkPrice.getId());

			emkPrice.setSumYl(Double.parseDouble(sumYlCb.get("sumCb").toString()));
			emkPrice.setSumFeng(Double.parseDouble(sumFzCb.get("sumCb").toString()));
			emkPrice.setSumBao(Double.parseDouble(sumBzCb.get("sumCb").toString()));
			emkPrice.setSumRg(Double.parseDouble(sumRgCb.get("sumCb").toString()));
			emkPrice.setSumRan(Double.parseDouble(sumRanCb.get("sumCb").toString()));
			emkPrice.setSumYin(Double.parseDouble(sumYinCb.get("sumCb").toString()));
			emkPrice.setSumZj(Double.parseDouble(sumZjCb.get("sumCb").toString()));

			double summoney = Double.parseDouble(sumYlCb.get("sumCb").toString())+Double.parseDouble(sumFzCb.get("sumCb").toString())+Double.parseDouble(sumBzCb.get("sumCb").toString())+Double.parseDouble(sumRgCb.get("sumCb").toString())+Double.parseDouble(sumRanCb.get("sumCb").toString())+Double.parseDouble(sumYinCb.get("sumCb").toString());
			if(emkPrice.getTestMoney() != null){
				summoney += emkPrice.getTestMoney();
			}

			//EmkGlEntity emkGlEntity = systemService.findUniqueByProperty(EmkGlEntity.class,"priceId",emkPrice.getId());
			double gl = 0;
			if(Utils.notEmpty(emkGlEntity)){
				if(Utils.notEmpty(emkGlEntity.getPlace())){
					gl += emkGlEntity.getPlace();
				}
				if(Utils.notEmpty(emkGlEntity.getCarCost())){
					gl += emkGlEntity.getCarCost();
				}
				if(Utils.notEmpty(emkGlEntity.getDianfei())){
					gl += emkGlEntity.getDianfei();
				}
				if(Utils.notEmpty(emkGlEntity.getEquip())){
					gl += emkGlEntity.getEquip();
				}
				if(Utils.notEmpty(emkGlEntity.getGlf())){
					gl += emkGlEntity.getGlf();
				}
				if(Utils.notEmpty(emkGlEntity.getOther())){
					gl += emkGlEntity.getOther();
				}
				if(Utils.notEmpty(emkGlEntity.getTranCost())){
					gl += emkGlEntity.getTranCost();
				}
				if(Utils.notEmpty(emkGlEntity.getWaterCost())){
					gl += emkGlEntity.getWaterCost();
				}

				summoney += gl;
				emkPrice.setSumGl(gl);
			}
			if(emkPrice.getUnableMoney() != null){
				summoney += emkPrice.getUnableMoney();
			}
			BigDecimal b = new BigDecimal(summoney);
			emkPrice.setSumMoney(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			summoney = summoney * 0.17;
			b = new BigDecimal(summoney);
			double dTax = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			emkPrice.setTax(dTax);

			b = new BigDecimal(emkPrice.getSumMoney()-emkPrice.getTax());
			emkPrice.setProfit(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());

			if(Utils.notEmpty(emkPrice.getHuilv())){
				b = new BigDecimal(emkPrice.getSumMoney()/emkPrice.getHuilv());
				emkPrice.setSumWb(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
			}

			try {
				systemService.saveOrUpdate(emkPrice);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	
}
