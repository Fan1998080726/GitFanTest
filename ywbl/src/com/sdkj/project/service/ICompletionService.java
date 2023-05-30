package com.sdkj.project.service;

import java.util.List;

import com.sdkj.project.vo.CompleteApply;

public interface ICompletionService {

	void addcompletionpro(String[] fileName, String[] filePath,CompleteApply apply)  throws Exception ;

	CompleteApply showCompletion(int projectId)   throws Exception ;

	List showCompletionFile(int projectId)   throws Exception ;

	void delete(CompleteApply apply)   throws Exception;

	int showCompletionCount(int projectId)  throws Exception;

}
