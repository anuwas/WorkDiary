/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ambigo
 * 08-Feb-2025 11:19:45 pm
 */
@Component
public class FileGenerateInMemory {
	
	@Autowired
	DBConnection dbConnection;
	
	@Autowired
	FileGenerateService fileGenerateService;

	List<Object[]> dataList = null;
	
	public ByteArrayInputStream generateReportFileInMemory(String DBquery) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		ResultSet rs = fileGenerateService.getResultSetByQuery(DBquery);
		Map<String,String> headerMetaDta = fileGenerateService.getQueryMetadataFromResultset(rs);
		if(!headerMetaDta.isEmpty() || headerMetaDta!=null) {
			dataList = fileGenerateService.generateReportDBData(rs, headerMetaDta);
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
			
			int rowCount = 0;
			for(Object[] obj: dataList) {
				Row row = sheet.createRow(++rowCount);
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
			
			try {
				workbook.write(out);
				 return new ByteArrayInputStream(out.toByteArray());		
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			
			
		}else {
			System.out.println("No result fetched");
		}
		return null;
	}
	
}
