/**
 *
 */
package com.org.pack.db.exportimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ambigo
 * 28-Jan-2025 12:13:01 am
 */
@Component
public class FileGeneratorService {
	
	@Autowired
	DBConnection dbConnection;

	private String fileLocation = null;
	List<Object[]> dataList = null;
	
	public String generateReportFile(String DBquery) {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath()+"/generated-file/";
		String generatedFileName = "exportFile.xlsx";
		fileLocation = path+generatedFileName;
		
		ResultSet rs = getResultSetByQuery(DBquery);
		Map<String,String> headerMetaDta = getQueryMetadataFromResultset(rs);
		if(!headerMetaDta.isEmpty() || headerMetaDta!=null) {
			dataList = this.generateReportDBData(rs, headerMetaDta);
		}
		
		if(!dataList.isEmpty() || dataList!=null) {
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("Sheet-1");
			
			Row headerRow = sheet.createRow(0);
			int headerColumnCount = 0;
			for(Map.Entry<String, String> entry : headerMetaDta.entrySet()) {
				Cell headerCell = headerRow.createCell(headerColumnCount);
				headerCell.setCellValue(entry.getKey().toUpperCase());
				headerColumnCount++;
			}
			try(FileOutputStream outputstream = new FileOutputStream(fileLocation)){
				workbook.write(outputstream);
				workbook.close();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
			
			try {
				FileInputStream inputStream = new FileInputStream(fileLocation);
				XSSFWorkbook wb_template = new XSSFWorkbook(inputStream);
				inputStream.close();
				SXSSFWorkbook wb = new SXSSFWorkbook(wb_template);
				CreationHelper creattionHelper = wb.getCreationHelper();
				wb.setCompressTempFiles(true);
				
				SXSSFSheet sh = wb.getSheetAt(0);
				sh.setRandomAccessWindowSize(500);
				
				int rowCount = 0;
				for(Object[] obj: dataList) {
					Row row = sh.createRow(++rowCount);
					int columnCount = 0;
					for(int i=0; i<obj.length;i++) {
						Cell cell = row.createCell(columnCount);
						if(obj[i] instanceof String) {
							cell.setCellValue((String)obj[i]);
						} else if(obj[i] instanceof Integer) {
							cell.setCellValue((Integer)obj[i]);
						} else if(obj[i] instanceof Long) {
							cell.setCellValue((Long)obj[i]);
						}
						columnCount++;
					}
				}
				
				FileOutputStream out = new FileOutputStream(fileLocation);
				wb.write(out);
				out.close();
				return generatedFileName;
				
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		}else {
			System.out.println("No result fetched");
		}
		return null;
	}
	
	private List<Object[]> generateReportDBData(ResultSet rs, Map<String,String> queryMetadata){
		List<Object[]> dataList = new ArrayList<>();
		try {
			int resultColumnCount = rs.getMetaData().getColumnCount();
			while(rs.next()) {
				Object exdata[] = new Object[resultColumnCount];
				int i = 0;
				for(Map.Entry<String, String> entry : queryMetadata.entrySet()) {
					if("INT".equals(entry.getValue())) {
						exdata[i] = rs.getInt(entry.getKey());
					}
					if("NUMBER".equals(entry.getValue())) {
						exdata[i] = rs.getLong(entry.getKey());
					}
					if("VARCHAR".equals(entry.getValue())) {
						exdata[i] = rs.getString(entry.getKey());
					}
					if("TIMESTAMP".equals(entry.getValue())) {
						//exdata[i] = rs.getString(entry.getKey());
						exdata[i] = Util.getDateStr(rs.getDate(entry.getKey()));
					}
					if("DATE".equals(entry.getValue())) {
						//exdata[i] = rs.getString(entry.getKey());
						exdata[i] = Util.getDateStr(rs.getDate(entry.getKey()));
					}
					if("DATETIME".equals(entry.getValue())) {
						//exdata[i] = rs.getString(entry.getKey());
						exdata[i] = Util.getDateStr(rs.getDate(entry.getKey()));
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
					i++;
				}
				dataList.add(exdata);
			}
		}
		catch(Exception ex) {
			
			
		}
		return dataList;
	}
	
	private Map<String,String> getQueryMetadataFromResultset(ResultSet rs){
		int columnCounter = 1;
		Map<String,String> queryMetadta = new LinkedHashMap<>();
		
		try {
			int resultColumnCount = rs.getMetaData().getColumnCount();
			while(resultColumnCount>=columnCounter) {
				queryMetadta.put(rs.getMetaData().getColumnName(columnCounter), rs.getMetaData().getColumnTypeName(columnCounter));
			}
			}catch(Exception ex) {
			
		}
		return queryMetadta;
	}
	
	private ResultSet getResultSetByQuery(String query) {
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
	
}
