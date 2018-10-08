package com.olivia.util;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Date;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class JsonUtil {

	public static JSONArray formatRsToJsonArray(ResultSet rs)throws Exception{
		 JSONArray array=new JSONArray();
		 ResultSetMetaData metaData=rs.getMetaData();
		 int columnCount=metaData.getColumnCount();
		 
		while (rs.next()) {
			JSONObject mapofCloValues=new JSONObject();
			for (int i = 1; i <=columnCount; i++) {
				String columnName=metaData.getColumnName(i);
				/**
				 * 判断对象类型 instanceof
				 */
				Object o=rs.getObject(i);
				if (o instanceof Date) {
					mapofCloValues.put(columnName, DateUtil.formatDateToString((Date)(o)));
				}else{
					mapofCloValues.put(columnName, o);
				}
				/*if (columnName.equals("birthday")) {
					 value=DateUtil.formatDateToString((Date)(rs.getObject(5))); 
				}else {
					value=rs.getObject(i);
				}*/
			}
			array.add(mapofCloValues);
		}
		return array;
	}
}
