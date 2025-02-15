/**
 *
 */
package com.org.pack.wd.db.exportimport;

import org.springframework.http.HttpHeaders;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
	
}
