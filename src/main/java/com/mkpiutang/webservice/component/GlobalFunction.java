package com.mkpiutang.webservice.component;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Map;

public class GlobalFunction {
	
	public Map<String, String> splitQuery(String query){
		String[] params = query.split("\\|");  
	    Map<String, String> map = new HashMap<String, String>();  
	    for (String param : params)  
	    {  String [] p=param.split("=");
	        String name = p[0];  
	      if(p.length>1)  {String value = p[1];  
	        map.put(name, value);
	      }  
	    }  
	    return map;  
	}
	
	public Map<String, String> splitParam(String query){
		String[] params = query.split("\\|");  
	    Map<String, String> map = new HashMap<String, String>();  
	    for (String param : params)  
	    {  String [] p=param.split("=");
	        String name = p[0];  
	      if(p.length>1)  {String value = p[1];  
	        map.put(name, value);
	      }  
	    }  
	    return map;  
	}
	
	public String generateCondition(String param, String field){
		
		if(param == null || param.isEmpty() || param.equalsIgnoreCase("all")){
			return " 1=1 AND ";
		} else {
			return field +" = '"+ param +"' AND ";		
		}
		
	}
	
	public String getNumberFormat(String Data){
		String VarKonvert=null;
		try {
			if (Data!=null || Data.trim().isEmpty()){
				DecimalFormatSymbols CaseSymbols = new DecimalFormatSymbols();
				CaseSymbols.setDecimalSeparator(',');
				CaseSymbols.setGroupingSeparator('.');
				String StringFormat = "###,###";
				DecimalFormat weirdFormatter = new DecimalFormat(StringFormat, CaseSymbols);
				VarKonvert = weirdFormatter.format(Integer.parseInt(Data));
			}
		} catch (Exception e) {
		
		}
	
		return VarKonvert;
	}		

	
}
