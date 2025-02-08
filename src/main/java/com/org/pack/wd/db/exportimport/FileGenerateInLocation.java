/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.sl.usermodel.Sheet;
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
public class FileGenerateInLocation {
	
	
	@Autowired
	FileGenerateService fileGenerateService;

	private String fileLocation = null;
	List<Object[]> dataList = null;
	
	public String generateReportFile(String DBquery) {
		File currDir = new File(".");
		String path = currDir.getAbsolutePath()+"/genfiles/";
		String generatedFileName = "exportFile.xlsx";
		fileLocation = path+generatedFileName;
		
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
			
			try(FileOutputStream outputstream = new FileOutputStream(fileLocation)){
				workbook.write(outputstream);
				workbook.close();
			}catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
						
			/*
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
			*/
			return generatedFileName;
		}else {
			System.out.println("No result fetched");
		}
		return null;
	}
	

	

	
	
}
