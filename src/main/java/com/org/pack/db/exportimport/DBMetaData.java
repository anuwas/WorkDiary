/**
 *
 */
package com.org.pack.db.exportimport;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
 * 26-Jan-2025 11:38:29 pm
 */
@Component
public class DBMetaData {
	
	@Autowired
	DBConnection dbConnection;
	
	
	public void getQueryMetaData(String query) {
		
		try {
			
		int coulmnCounter = 1;
		Map<String,String> queryMetadata = new LinkedHashMap<>();
		
			Statement stmt = dbConnection.conn().createStatement();
			List<Object[]> dataList = new ArrayList<>();
			ResultSet rs = stmt.executeQuery(query);
			int resultColumnCount = rs.getMetaData().getColumnCount();
			while(resultColumnCount>=coulmnCounter) {
				queryMetadata.put(rs.getMetaData().getColumnName(coulmnCounter), rs.getMetaData().getColumnTypeName(coulmnCounter));
				coulmnCounter++;
			}
			while(rs.next()) {
				Object exdata[] = new Object[resultColumnCount];
				int i = 0;
				for(Map.Entry<String, String> entry : queryMetadata.entrySet()) {
					if("INT".equals(entry.getValue())) {
						System.out.println(entry.getKey());
						exdata[i] = rs.getInt(entry.getKey());
					}
					if("VARCHAR".equals(entry.getValue())) {
						System.out.println(entry.getKey());
						exdata[i] = rs.getInt(entry.getKey());
					}
					i++;
				}
				dataList.add(exdata);
			}
			System.out.println(rs.getMetaData().getColumnName(2));
			System.out.println(queryMetadata);
			
			XSSFWorkbook workbook = new XSSFWorkbook();
			XSSFSheet sheet = workbook.createSheet("WorkDiary");
			int rowCount = 0;
			for(Object[] obj : dataList) {
				Row row = sheet.createRow(++rowCount);
				int columnCount = 0;
				for(int i =0;i<resultColumnCount;i++) {
					Cell cell = row.createCell(columnCount);
					if(obj[i] instanceof String) {
						cell.setCellValue((String) obj[i]);
					}else if (obj[i] instanceof Integer) {
						cell.setCellValue((Integer) obj[i]);
					}
					columnCount++;
				}
			}
			FileOutputStream outputStream = new FileOutputStream("workdiary.xlsx");
			workbook.write(outputStream);
					
		} catch (SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
