package com.mkpiutang.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mkpiutang.webservice.component.MCrypt;
import com.mkpiutang.webservice.dao.ResponseComboDao;
import com.mkpiutang.webservice.dto.RecordDto;
import com.mkpiutang.webservice.dto.ResponseComboDto;

@RestController
@RequestMapping("/responsecombo")
public class ResponseComboController extends BaseController {

	@Autowired
	private ResponseComboDao rcDao;
	
	@RequestMapping("/upi")
	public RecordDto<ResponseComboDto> getUpiWithPaging(
		@ModelAttribute RecordDto<ResponseComboDto> recordDto,
		@RequestParam(value="param", required=true) String param){
		return rcDao.getUpi(recordDto, param);
	}
	
	@RequestMapping("/ap")
	public RecordDto<ResponseComboDto> getApWithPaging(
			@ModelAttribute RecordDto<ResponseComboDto> recordDto, 
			@RequestParam(value="param", required=true) String param){
		return rcDao.getAp(recordDto, param);
	}
	
	@RequestMapping("/up")
	public RecordDto<ResponseComboDto> getUpWithPaging(
			@ModelAttribute RecordDto<ResponseComboDto> recordDto, 
			@RequestParam(value="param", required=true) String param){
		return rcDao.getUp(recordDto, param);
	}
	
	@RequestMapping("/gardu")
	public RecordDto<ResponseComboDto> getGardu(
			@ModelAttribute RecordDto<ResponseComboDto> recordDto, 
			@RequestParam(value="param", required=true) String param){
		return rcDao.getGardu(recordDto, param);
	}
	
	@RequestMapping("/rbm")
	public RecordDto<ResponseComboDto> getRbm(
			@ModelAttribute RecordDto<ResponseComboDto> recordDto, 
			@RequestParam(value="param", required=true) String param){
		return rcDao.getRbm(recordDto, param);
	}
	
	@RequestMapping("/setencode")
	public String getCekiWithPaging(
		@ModelAttribute RecordDto<ResponseComboDto> recordDto,
		@RequestParam(value="param", required=true) String param){
		
		MCrypt enkripsi = new MCrypt();
		String paramreq = enkripsi.encode(param);
		return paramreq;
	}
	
	@RequestMapping("/decode")
	public String getDecode(
		@ModelAttribute RecordDto<ResponseComboDto> recordDto,
		@RequestParam(value="param", required=true) String param){
		
		MCrypt enkripsi = new MCrypt();
		String paramreq = enkripsi.decode(param);
		return paramreq;
	}
	
}