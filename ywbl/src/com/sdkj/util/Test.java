package com.sdkj.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

public class Test {

	public static void main(String[] args) {
		
		
		
		System.out.println(randomUtil(5,100).toString());

	}
	
	public static List<String> randomUtil(int rnum,int maxNum){
		List<String> list = new ArrayList<String>();
		Random df = new Random();
		for(int i=0;i<maxNum;i++){
			int number = df.nextInt(maxNum);
			if(!list.contains(number))
				list.add(String.valueOf(number));
			if(list.size()>=rnum)
				return list;
		}
		return null;
	}

}
