/**
 *
 */
package com.org.pack.wd.skillup;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author ambigo
 * 06-Mar-2025 1:16:35 am
 */
@Entity
@Table(name = "WORDING")
@Setter
@Getter
public class Wording {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TWORDING_ID")
	private long wordingId;
	
	@Column(name = "SEQ_ORDER")
	private String seqOrder; 
	
	@Column(name = "ENG_WORD")
	private String engWord; 
	
	@Column(name = "BEN_WORD")
	private String benWord; 
	
	@Column(name = "ENG_SYNONAME")
	private String engSynoname; 
	
	@Column(name = "BEN_SYNONAME")
	private String benSynoname;
	
	@Column(name = "SENTNC_EXAMPLE",columnDefinition = "TEXT")
	private String sentncExample;
	
	@Column(name = "MEMORISED")
	private String memorised;
	
	@Column(name = "STATUS")
	private String status;

}
