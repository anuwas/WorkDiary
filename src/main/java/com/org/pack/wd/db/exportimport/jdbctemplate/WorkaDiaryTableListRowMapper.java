/**
 *
 */
package com.org.pack.wd.db.exportimport.jdbctemplate;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author ambigo
 * 15-Feb-2025 6:47:13 pm
 */
public class WorkaDiaryTableListRowMapper implements RowMapper<WorkaDiaryTableList> {

	@Override
	public WorkaDiaryTableList mapRow(ResultSet rs, int rowNum) throws SQLException {
		WorkaDiaryTableList workaDiaryTableList = new WorkaDiaryTableList();
		workaDiaryTableList.setTableName(rs.getString("TABLE_NAME"));
		workaDiaryTableList.setRowCount(rs.getLong("ROW_COUNT_ESTIMATE"));
		return workaDiaryTableList;
	}

}
