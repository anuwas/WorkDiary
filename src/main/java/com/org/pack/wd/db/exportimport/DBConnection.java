/**
 *
 */
package com.org.pack.wd.db.exportimport;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ambigo
 * 26-Jan-2025 11:13:31 pm
 */
@Component
public class DBConnection {
	
	@Value("${spring.datasource.driverClassName}")
	private String jdbcDriver;
	
	@Value("${spring.datasource.url}")
	private String jdbcUrl;
	
	@Value("${spring.datasource.username}")
	private String jdbcUserName;
	
	@Value("${spring.datasource.password}")
	private String jdbcPassword;
	
	public Connection conn() {
		try {
			Class.forName(jdbcDriver);
			
			Connection con = DriverManager.getConnection(jdbcUrl,jdbcUserName,jdbcPassword);
			return con;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return null;
		
	}
	
}
