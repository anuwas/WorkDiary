/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ambigo
 * 08-Feb-2025 11:33:12 pm
 */
@Service
public class FileGenerateService {
	
	@Autowired
	DBConnection dbConnection;
	
	public ResultSet getResultSetByQuery(String query) {
		Statement stmt;
		ResultSet rs = null;
		try{
			stmt = dbConnection.conn().createStatement();
			rs=stmt.executeQuery(query);
			dbConnection.conn().close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return rs;
	}

	public Map<String,String> getQueryMetadataFromResultset(ResultSet rs){
		int columnCounter = 1;
		Map<String,String> queryMetadta = new LinkedHashMap<>();
		
		try {
			int resultColumnCount = rs.getMetaData().getColumnCount();
			while(resultColumnCount>=columnCounter) {
				queryMetadta.put(rs.getMetaData().getColumnName(columnCounter), rs.getMetaData().getColumnTypeName(columnCounter));
				columnCounter++;
			}
			}catch(Exception ex) {
			
		}
		return queryMetadta;
	}
	
	public List<Object[]> generateReportDBData(ResultSet rs, Map<String,String> queryMetadata){
		List<Object[]> dataList = new ArrayList<>();
		try {
			int resultColumnCount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object exdata[] = new Object[resultColumnCount];
				int i = 0;
				for(Map.Entry<String, String> entry : queryMetadata.entrySet()) {
					if("".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("INT".equals(entry.getValue())) {
						exdata[i] = rs.getInt(entry.getKey());
					}
					if("BIGINT".equals(entry.getValue())) {
						exdata[i] = rs.getInt(entry.getKey());
					}
					if("NUMBER".equals(entry.getValue())) {
						exdata[i] = rs.getLong(entry.getKey());
					}
					if("VARCHAR".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("TIMESTAMP".equals(entry.getValue())) {
						//exdata[i] = rs.getTimestamp(entry.getKey());
						exdata[i] = DBExportUtil.getTimestampStr(rs.getTimestamp(entry.getKey()));
					}
					if("DATE".equals(entry.getValue())) {
						//exdata[i] = rs.getString(entry.getKey());
						exdata[i] = DBExportUtil.getDateStr(rs.getDate(entry.getKey()));
					}
					if("DATETIME".equals(entry.getValue())) {
						//exdata[i] = rs.getString(entry.getKey());
						exdata[i] = DBExportUtil.getDateStr(rs.getDate(entry.getKey()));
					}
					if("TIME".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("CHAR".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("TEXT".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("CLOB".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					i++;
				}
				dataList.add(exdata);
			}
		}
		catch(Exception ex) {
			
			
		}
		return dataList;
	}
	
}
