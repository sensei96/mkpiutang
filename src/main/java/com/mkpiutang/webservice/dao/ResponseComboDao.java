package com.mkpiutang.webservice.dao;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mkpiutang.webservice.component.GlobalFunction;
import com.mkpiutang.webservice.component.MCrypt;
import com.mkpiutang.webservice.dto.RecordDto;
import com.mkpiutang.webservice.dto.ResponseComboDto;

@Repository("responseComboDao")
@Transactional
public class ResponseComboDao extends BaseDao {

	@Autowired
	public ResponseComboDao(EntityManagerFactory factory) {
		super(factory);
	}
	
	@SuppressWarnings("unchecked")
	public RecordDto<ResponseComboDto> getUpi(RecordDto<ResponseComboDto> recordDto, String param){
		Session session = getCurrentSession();
		String condition;
		
		MCrypt enkripsi = new MCrypt();
		param = enkripsi.decode(param);
		
		GlobalFunction arrParam	=	new GlobalFunction();
		Map mapsdata = arrParam.splitQuery(param);
		
		String id=(String)mapsdata.get("idupi");
		
		if(id.toString().equalsIgnoreCase("all")){
			condition	=	"1=1";
		} else {
			condition	=	" UNITUPI = "+ id +" ";
		}
		 
		String sql = "SELECT 'all' AS ID, ' -PILIH SEMUA- ' AS NAME FROM M_UNITUPI WHERE ROWNUM <= 1 "+
					 "UNION ALL "+
					 "SELECT UNITUPI AS ID, NAMAUNITUPI AS NAME FROM M_UNITUPI "+
					 "WHERE "+condition+
					 "ORDER BY NAME";
		List<ResponseComboDto> queryResult = (List<ResponseComboDto>) 
				session.createSQLQuery(sql)
				.addScalar("ID", StringType.INSTANCE)
				.addScalar("NAME", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ResponseComboDto.class))
				.list();
		
		recordDto.setRecords(queryResult);
		return recordDto;
	}


	@SuppressWarnings("unchecked")
	public RecordDto<ResponseComboDto> getAp(RecordDto<ResponseComboDto> recordDto, String param){
		Session session = getCurrentSession();
		String condition;
		
		MCrypt enkripsi = new MCrypt();
		param = enkripsi.decode(param);
		
		GlobalFunction arrParam	=	new GlobalFunction();
		Map mapsdata = arrParam.splitQuery(param);
		
		String id=(String)mapsdata.get("idupi");

		if (id == null || id.isEmpty()){
			condition = "UNITUPI = '0' ";
		}else{
			condition = "UNITUPI = '" + id + "' ";
		}
		
		String sql = "SELECT 'all' AS ID, ' -PILIH SEMUA- ' AS NAME FROM M_UNITAP WHERE ROWNUM <= 1 "+
					 "UNION ALL "+
				 	 "SELECT UNITAP AS ID, NAMAUNITAP AS NAME "+
					 "FROM M_UNITAP "+
					 "WHERE " + condition + 
					 "ORDER BY NAME";
		
		List<ResponseComboDto> queryResult = (List<ResponseComboDto>) 
				session.createSQLQuery(sql)
				.addScalar("ID", StringType.INSTANCE)
				.addScalar("NAME", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ResponseComboDto.class))
				.list();
		
		recordDto.setRecords(queryResult);
		return recordDto;
	}
	
	@SuppressWarnings("unchecked")
	public RecordDto<ResponseComboDto> getUp(RecordDto<ResponseComboDto> recordDto, String param){
		Session session = getCurrentSession();
		
		String condition;
		String conditionupi;
		
		MCrypt enkripsi = new MCrypt();
		param = enkripsi.decode(param);
		
		GlobalFunction arrParam	=	new GlobalFunction();
		Map mapsdata = arrParam.splitQuery(param);
		
		String idap	=(String)mapsdata.get("idap");
		String idupi=(String)mapsdata.get("idupi");
		
		if (idap == null || idap.isEmpty()){
			condition = "A.UNITAP = '0' ";
		}else{
			condition = "A.UNITAP = '" + idap + "' ";
		}	
		
		if (idupi == null || idupi.isEmpty() ){
			conditionupi = "AND 1=1 ";
		}else{
			conditionupi = "AND B.UNITUPI = '" + idupi + "' ";
		}	
		
		String sql = "SELECT 'all' AS ID, ' -PILIH SEMUA- ' AS NAME FROM M_UNITUP WHERE ROWNUM <= 1 "+
				 	 "UNION ALL "+
				 	 "SELECT A.UNITUP AS ID, A.NAMAUNITUP AS NAME " +
					 "FROM M_UNITUP A " +
					 "LEFT JOIN M_UNITAP B ON A.UNITAP = B.UNITAP WHERE " + condition + conditionupi + " "+
					 "ORDER BY NAME";
		
		List<ResponseComboDto> queryResult = (List<ResponseComboDto>) 
				session.createSQLQuery(sql)
				.addScalar("ID", StringType.INSTANCE)
				.addScalar("NAME", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ResponseComboDto.class))
				.list();
		
		recordDto.setRecords(queryResult);
		return recordDto;
	}
	
	@SuppressWarnings("unchecked")
	public RecordDto<ResponseComboDto> getGardu(RecordDto<ResponseComboDto> recordDto, String param){
		Session session = getCurrentSession();
		
		String condition;
		
		MCrypt enkripsi = new MCrypt();
		param = enkripsi.decode(param);
		
		GlobalFunction arrParam	=	new GlobalFunction();
		Map mapsdata = arrParam.splitQuery(param);
		
		String idup	=(String)mapsdata.get("gardu");
		
		if (idup == null || idup.isEmpty()){
			condition = "UNITUP = '0' ";
		}else{
			condition = "UNITUP = '" + idup + "' ";
		}
		
		String gardu	=(String)mapsdata.get("gardu");
		
		if (gardu == null || gardu.isEmpty()){
			condition = "NOGARDU = '0' ";
		}else{
			condition = "NOGARDU = '" + gardu + "' ";
		}	
		
		String sql = "SELECT 'all' AS ID, ' -PILIH SEMUA- ' AS NAME FROM MASTERDPP201509 WHERE ROWNUM <= 1 "+
				 	 "UNION ALL "+
				 	 "SELECT NOGARDU AS ID, NOGARDU AS NAME " +
					 "FROM MASTERDPP201509 " +
					 "WHERE " + condition + " "+
					 "ORDER BY NAME";
		
		List<ResponseComboDto> queryResult = (List<ResponseComboDto>) 
				session.createSQLQuery(sql)
				.addScalar("ID", StringType.INSTANCE)
				.addScalar("NAME", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ResponseComboDto.class))
				.list();
		
		recordDto.setRecords(queryResult);
		return recordDto;
	}
	
	@SuppressWarnings("unchecked")
	public RecordDto<ResponseComboDto> getRbm(RecordDto<ResponseComboDto> recordDto, String param){
		Session session = getCurrentSession();
		
		String condition;
		
		MCrypt enkripsi = new MCrypt();
		param = enkripsi.decode(param);
		
		GlobalFunction arrParam	=	new GlobalFunction();
		Map mapsdata = arrParam.splitQuery(param);
		
		String idup	=(String)mapsdata.get("idup");
		
		if (idup == null || idup.isEmpty()){
			condition = "UNITUP = '0' ";
		}else{
			condition = "UNITUP = '" + idup + "' ";
		}
		
		String rbm	=(String)mapsdata.get("rbm");
		
		if (rbm == null || rbm.isEmpty()){
			condition = "RBM = '0' ";
		}else{
			condition = "RBM = '" + rbm + "' ";
		}	
		
		String sql = "SELECT 'all' AS ID, ' -PILIH SEMUA- ' AS NAME FROM MASTERDPP201509 WHERE ROWNUM <= 1 "+
				 	 "UNION ALL "+
				 	 "SELECT RBM AS ID, RBM AS NAME " +
					 "FROM MASTERDPP201509 " +
					 "WHERE " + condition + " " +
					 "ORDER BY NAME";
		
		List<ResponseComboDto> queryResult = (List<ResponseComboDto>) 
				session.createSQLQuery(sql)
				.addScalar("ID", StringType.INSTANCE)
				.addScalar("NAME", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ResponseComboDto.class))
				.list();
		
		recordDto.setRecords(queryResult);
		return recordDto;
	}
	
}