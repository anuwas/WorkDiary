/**
 *
 */
package com.org.pack.db.exportimport;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.org.pack.wd.application.entity.Applications;

/**
 * @author ambigo
 * 27-Jan-2025 12:22:55 am
 */

@Controller
public class DBExportImportController {

	@Autowired
	DBMetaData dbMetaData;
	
	@GetMapping("/application-table-list}")
	public String editApplicaiton(Model model) {
		
		return "dbexportimport/application-table-list";
	}
	
}
