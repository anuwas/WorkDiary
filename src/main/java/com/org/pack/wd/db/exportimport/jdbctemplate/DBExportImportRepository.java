/**
 *
 */
package com.org.pack.wd.db.exportimport.jdbctemplate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author ambigo
 * 15-Feb-2025 6:10:23 pm
 */
@Repository
public class DBExportImportRepository {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private static final String ALL_TABLE_SELECT = "SELECT TABLE_NAME,ROW_COUNT_ESTIMATE  FROM INFORMATION_SCHEMA.TABLES where TABLE_TYPE = ? "
			+" ORDER BY TABLE_NAME";
	
	public List<WorkaDiaryTableList> getAllWorkDiaryTableList(){
		//return this.jdbcTemplate.query(ALL_TABLE_SELECT, new Object[] {"TABLE"},);
			
		return this.jdbcTemplate.query(ALL_TABLE_SELECT, new WorkaDiaryTableListRowMapper(), new Object[] {"TABLE"});
	}
	
	public void insertToTableFromExcelImport(String query) {
		this.jdbcTemplate.update(query);
	}

}
