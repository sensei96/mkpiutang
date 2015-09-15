package com.mkpiutang.webservice.component;


import java.io.UnsupportedEncodingException;
//import java.math.BigInteger;
//import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
//import com.google.common.io.BaseEncoding;

//import org.springframework.util.Base64Utils;

//import org.apache.commons.codec.binary.Base64;


public class MCrypt {

    private String iv="fffbb78dde123123";
    private IvParameterSpec ivspec; 
    private SecretKeySpec keyspec;
    private Cipher cipher;
    private String SecretKey="fffbb78dde123123"; 
//    private Utils utils;  
    private String MKEY = "MkP1uTanG@2015!";
    
    
    
    public MCrypt(){
//    	
//    	if(LocalConf.MYIV != ""){
//    		this.iv				=	LocalConf.MYIV;
//    	}
//    	
//    	if(LocalConf.MYKEYSTORE != ""){    		
//    		this.SecretKey		=	LocalConf.MYKEYSTORE;//utils.Base64decode(LocalConf.MYKEYSTORE);			    		
//    	}
    	
    	//if(LocalConf.MYDEBUG != "" ){
    		//LocalConf.MYKEY = LocalConf.MYDEBUG;
    	//}
    	
//]    	MCrypt m = new MCrypt();
//    	m.setMKEY("");
//    	String data = m.encode(source);
    	
    	
    	
    	ivspec = new IvParameterSpec(iv.getBytes());
        keyspec = new SecretKeySpec(SecretKey.getBytes(), "AES");

        try {
            cipher = Cipher.getInstance("AES/CBC/NOPADDING");
        } 
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } 
        catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } 
    }
    
    
    
    
    
    
    public String getMKEY() {
		return MKEY;
	}






	public void setMKEY(String mKEY) {
		MKEY = mKEY;
	}






	private static String safe_b64encode(String source) throws UnsupportedEncodingException {
    	byte[] databyte = source.getBytes("ISO-8859-1");
    	String data = DatatypeConverter.printBase64Binary(databyte);
//    	String data = BaseEncoding.base64().encode(databyte);
//    			Base64Utils.encodeToString(databyte);//, Base64Utils.DEFAULT);
    	data = data.replace("+", "-");
    	data = data.replace("/", "_");
    	data = data.replace("=", "");       
        return data;
    }
    
    
    //==-- ENCODE
    public String encode(String source){
    	if(source.isEmpty()){return null;}
    	String str = source;
    	String result="",chars,keychar = "";
    	int chr,key;
    	
    	for(int i=0; i<str.length(); i++) {
    		chars = String.valueOf(str.charAt(i));
    		int ind = (i % getMKEY().length()) - 1;
    		ind = ind<0?(getMKEY().length()-1):ind;		
    		keychar = String.valueOf(getMKEY().charAt(ind));
    		chr	  = chars.charAt(0);
    		key   = keychar.charAt(0);
    		chr	  = chr+key;		
			chars = String.valueOf((char)chr);
			result = result+chars;
    	}
//        System.out.println("hasil encoding "+result);
    	try {
			source = safe_b64encode(result).trim();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//        System.out.println("encodingBase64 "+source);
    	
 
    	return source;
    }

    public String safe_b64decode(String source) {
        String data = source;
        data = data.replace('-','_');
        data = data.replace('+','/');
        
        int mod4 = data.length() % 4;
        if (mod4 != 0) {
            data += "====".substring(mod4, 4);
        }
        
        try {
//        	data = new String(Base64Utils.decode(data, Base64.DEFAULT),"ISO-8859-1");
//			data = new String(Base64Utils.decodeFromString(data),"ISO-8859-1");
        	data = new String(DatatypeConverter.parseBase64Binary(data),"ISO-8859-1");
//        	data = new String(BaseEncoding.base64().decode(data),"ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
        
        return data;
    }
    
    
    
    //==-- DECODE
    public String decode(String source){
    	 
		if(source.isEmpty()){return null;}
		String str = source;
    	String result="",chars,keychar = "";
    	int chr,key;
//        System.out.println("data encodingbase64 "+str);
	  	str = safe_b64decode(str);
//        System.out.println("hasil decoding "+str);
        
	  	for(int i=0; i<str.length(); i++) {
    		chars = String.valueOf(str.charAt(i));
    		int ind = (i % getMKEY().length()) - 1;
    		ind = ind<0?(getMKEY().length()-1):ind;		
    		keychar = String.valueOf(getMKEY().charAt(ind));
    		chr	  = chars.charAt(0);
    		key   = keychar.charAt(0);
    		chr	  = chr-key;
			chars = String.valueOf((char)chr); 
    		result = result+chars;
    	}

//        System.out.println("pesan asli : "+result);
 
    	return result.trim();
    }
}

