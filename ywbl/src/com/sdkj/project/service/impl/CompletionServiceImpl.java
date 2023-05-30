package com.sdkj.project.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sdkj.project.service.ICompletionService;
import com.sdkj.project.vo.CompleteApply;
import com.sdkj.util.basic.dao.Dao;

@Service
public class CompletionServiceImpl implements ICompletionService{

	
	@Autowired
	private Dao dao;
	
	@Transactional
	public void addcompletionpro(String[] fileName, String[] filePath,CompleteApply apply) throws Exception {
		
		String caid = dao.savestr("addcompletion", apply, "ca_id");
		////////System.out.println(caid);
		
		if(filePath  != null && filePath.length > 0){
			for (int i = 0; i < filePath.length; i++) {
				Map map = new HashMap();
				
				if(fileName[i] != null){
				    map.put("caf_name", fileName[i]);
				}
				map.put("caf_url", filePath[i]);
				map.put("ca_id",caid);
				dao.save("addcompletionprofile", map);
			}
		}
		
		if(apply.getCa_flag() == 33){
			this.SubmitApproval("17", caid ,String.valueOf(apply.getCa_user()), apply.getCa_remark(),String.valueOf(apply.getPro_id()));
		}
		
	}

	public CompleteApply showCompletion(int projectId) throws Exception {
		
		return (CompleteApply) dao.queryById("showcompletionpro", projectId);
	}

	public List showCompletionFile(int projectId) throws Exception {
		
		return dao.queryForList("showcompletionfile", projectId);
	}

	@Transactional
	public void delete(CompleteApply apply) throws Exception {
		
		
		String caid = dao.savestr("addcompletion", apply, "ca_id");
		
		
		dao.delete("deletecompletionfile", Integer.parseInt(caid));
		
		dao.delete("deletecompletionpro", apply.getPro_id());
		
		
		
	
		
	}

	public int showCompletionCount(int projectId) throws Exception {
		
		
		 int i = (Integer) dao.queryById("compcount" , projectId);
		return i;
	}
	
	
	
	
	
	/**
	 * 
	 * describe: 发送信息
	 * 
	 * @param sendUserId
	 *            发件人id
	 * @param receiveUserId
	 *            收件人id
	 * @param title
	 *            标题
	 * @param content
	 *            内容
	 * @throws Exception
	 *             2014-4-24
	 * @author txb
	 */
	public void sendMessage(String sendUserId, String receiveUserId,
			String title, String content) throws Exception { }

	/**
	 * 
	 * describe:
	 * 
	 * @param alType
	 *            审批类型 14：合同审批 15：工程审批 16： 变更审批 17：竣工审批
	 * @param alRelationId
	 *            合同id 或 工程id 等
	 * @param alUser
	 *            操作人员的id
	 * @param remark
	 *            备注
	 * @throws Exception
	 *             2014-4-24
	 * @author txb
	 */
	public void SubmitApproval(String alType, String alRelationId,
			String alUser, String remark,String proId) throws Exception { }


}
