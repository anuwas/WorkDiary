/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.org.pack.wd.db.exportimport.jdbctemplate.DBExportImportRepository;

/**
 * @author ambigo
 * 20-Feb-2025 1:01:34 am
 */
@Service
public class DBImportService {
	
	@Autowired
	DBExportImportRepository dbExportImportRepository;
	
	public String ImportToDB(String tableName,MultipartFile excelfile) throws IOException {
		
		XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    String preComileDML = getExcelHeaderTableColumn(worksheet,tableName);
	    List<String> quiryList = writeQuery(preComileDML,worksheet);
	    quiryList.stream().forEach(n->dbExportImportRepository.insertToTableFromExcelImport(n));
	    System.out.println(quiryList);
		
		return null;
	}
	private List<String> writeQuery(String preComileDML,XSSFSheet worksheet) {
		boolean rowExist = false;
		List<String> quiryList = new ArrayList<>();
		//System.out.println(preComileDML);
		
	    Iterator<Row> rowIterator = worksheet.iterator();
	    
	    
	    rowIterator.next();
	    while (rowIterator.hasNext()) {
	    	rowExist = false;
            Row currentRow = rowIterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            String rowqueryString = preComileDML;

            while (cellIterator.hasNext()) {
            	rowExist = true;
                Cell currentCell = cellIterator.next();
                if (currentCell.getCellType() == CellType.STRING) {
                    //System.out.print(currentCell.getStringCellValue() + "--");
                	
                	if(currentCell.getStringCellValue().equals("no-date")) {
                		rowqueryString+="'"+DBExportUtil.getCurrentTimestamp()+"',";
                	}else {
                		rowqueryString+="'"+currentCell.getStringCellValue()+"',";
                	}
                } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    //System.out.print(currentCell.getNumericCellValue() + "--");
                	rowqueryString+=(int)currentCell.getNumericCellValue()+",";
                }
               
            }
            if(rowExist) {
            	 rowqueryString=removeChars(rowqueryString,1)+")";
                 //System.out.println(rowqueryString);
                 quiryList.add(rowqueryString);
            }
           
           // System.out.println();

        }
	    
		return quiryList;
	}
	
	public static String removeChars(String str, int numberOfCharactersToRemove) {
        if(str != null && !str.trim().isEmpty()) {
            return str.substring(0, str.length() - numberOfCharactersToRemove);
        }
        return "";
    }
	
	private String getExcelHeaderTableColumn(XSSFSheet worksheet,String tableName){
		
        XSSFRow row = worksheet.getRow(0);
        int totalColumn = row.getPhysicalNumberOfCells();
        String queryColumn = "INSERT INTO "+tableName+" (";
        for(int i=0;i<totalColumn;i++) {
        	//System.out.println(row.getCell(i).getStringCellValue());
        	if(i<totalColumn-1) {
        		queryColumn+=row.getCell(i).getStringCellValue()+",";
        	}else {
        		queryColumn+=row.getCell(i).getStringCellValue();
        	}
        }
        queryColumn+=") values (";
        
        //System.out.println(row.getCell(1).getStringCellValue());
       
        
		/*
		 * for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
		 * 
		 * 
		 * applications.setId((long) row.getCell(0).getNumericCellValue());
		 * applications.setApplicationName(row.getCell(1).getStringCellValue());
		 * 
		 * System.out.println(applications);
		 * 
		 * }
		 */
		
		return queryColumn;
		
	}

}
