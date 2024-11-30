package com.org.pack.wd.appsettings.controller;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.org.pack.wd.appsettings.repository.AppsettingsRepository;
import com.org.pack.wd.dto.OperationNameDescription;
import com.org.pack.wd.operation.entity.Operation;

@Controller
public class AppsettingsController {

	@Autowired
	AppsettingsRepository appsettingsRepository;
	
	@GetMapping("/appsetings")
	public String allServiceActivities(Model model) {
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
		return "appsetings/index";
	}
}
