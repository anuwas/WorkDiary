/**
 *
 */
package com.org.pack.wd.db.exportimport;

import org.springframework.http.HttpHeaders;

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
import org.aspectj.weaver.ast.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.org.pack.wd.application.entity.Applications;
import com.org.pack.wd.db.exportimport.jdbctemplate.DBExportImportRepository;
import com.org.pack.wd.db.exportimport.jdbctemplate.WorkaDiaryTableList;

/**
 * @author ambigo
 * 27-Jan-2025 12:22:55 am
 */

@Controller
public class DBExportimportController {

	@Autowired
	DBMetaData dbMetaData;
	
	@Autowired
	FileGenerateInLocation fileGenerateInLocation;
	
	@Autowired
	FileGenerateInMemory fileGenerateInMemory;
	
	@Autowired
	DBExportImportRepository dbExportImportRepository;
	
	@Autowired
	DBImportService dbImportService;
	
	@GetMapping("/file-generate-by-query")
	public String generateFileByQuery(Model model) {
		
		//dbMetaData.getQueryMetaData("SELECT * FROM DIARY_TASK");
		fileGenerateInLocation.generateReportFile("SELECT * FROM DIARY_TASK");
		return "dbexportimport/application-table-list";
	}
	
	@GetMapping("/application-table-list")
	public String getWorkdiaryTableList(Model model) {
		
		List<WorkaDiaryTableList> workaDiaryTableList = dbExportImportRepository.getAllWorkDiaryTableList();
		model.addAttribute("workaDiaryTableList", workaDiaryTableList);
		return "dbexportimport/application-table-list";
	}
	
	@GetMapping("/download-table/{tablename}")
	public ResponseEntity<Resource> downloadTableInExcel(Model model,@PathVariable String tablename) {
		
		//String query = "SELECT * FROM DIARY_TASK";
		String query = "SELECT * FROM "+tablename;
		//String filename = "exportFile.xlsx";
		String filename = tablename+".xlsx";

		InputStreamResource file = new InputStreamResource(fileGenerateInMemory.generateReportFileInMemory(query));
		return ResponseEntity.ok()
		        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
	}
	
	@PostMapping("/import")
	public String mapReapExcelDatatoDB(@RequestParam("file") MultipartFile reapExcelDataFile,
			@RequestParam("table_name") String tableName) throws IOException {
	    //System.out.println(tableName);
	    List<Test> tempStudentList = new ArrayList<Test>();
	    XSSFWorkbook workbook = new XSSFWorkbook(reapExcelDataFile.getInputStream());
	    XSSFSheet worksheet = workbook.getSheetAt(0);
	    
	    //Read with forloop
	    /*
	    for(int i=1;i<worksheet.getPhysicalNumberOfRows() ;i++) {
	    	Applications applications = new Applications();
	            
	        XSSFRow row = worksheet.getRow(i);
	            
	        applications.setId((long) row.getCell(0).getNumericCellValue());
	        applications.setApplicationName(row.getCell(1).getStringCellValue());
	        
	        System.out.println(applications);   
	       
	    }*/
	    
	    //Read with Iterator
	    
	    /*
	    Iterator<Row> iterator = worksheet.iterator();
	    while (iterator.hasNext()) {

            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();

            while (cellIterator.hasNext()) {

                Cell currentCell = cellIterator.next();
                if (currentCell.getCellType() == CellType.STRING) {
                    System.out.print(currentCell.getStringCellValue() + "--");
                } else if (currentCell.getCellType() == CellType.NUMERIC) {
                    System.out.print(currentCell.getNumericCellValue() + "--");
                }

            }
            System.out.println();

        }
        */
	    dbImportService.ImportToDB(tableName, reapExcelDataFile);
	    
	    return "redirect:/application-table-list";
	}
	
}
