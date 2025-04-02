package com.org.pack.wd.appsettings.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.org.pack.wd.skillup.WordingRepository;

@Controller
public class AppsettingsController {
	
	@Autowired
	WordingRepository wordingRepository;
	
	@GetMapping("/appsetings")
	public String workDiarySettings(Model model) {
	/*
		DatabaseMetaData metaData = dataSource.getConnection().getMetaData();
        ResultSet tables = metaData.getTables(null, null, null, new String[] { "TABLE" });
        while (tables.next()) {
            String tableName=tables.getString("TABLE_NAME");
            System.out.println(tableName);
            ResultSet columns = metaData.getColumns(null,  null,  tableName, "%");
            while (columns.next()) {
                String columnName=columns.getString("COLUMN_NAME");
                System.out.println("\t" + columnName);
            }
            */
		int totalWord = (int) wordingRepository.count();
		model.addAttribute("wordCount", totalWord);
		return "appsettings/index";
	}
}
