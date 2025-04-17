/**
 *
 */
package com.org.pack.wd.skillup;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

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
	@Column(name = "WORDING_ID")
	private long wordingId;
	
	@Column(name = "SEQ_ORDER",columnDefinition = "integer default 0")
	private Integer seqOrder = 0; 
	
	@Column(name = "ENG_WORD")
	private String engWord; 
	
	@Column(name = "BEN_WORD")
	private String benWord; 
	
	@Column(name = "ENG_SYNONAME")
	private String engSynoname; 
	
	@Column(name = "BEN_SYNONAME")
	private String benSynoname;
	
	@Column(name = "BEN_SOUNDIN_ENG")
	private String benSoudingEng;

	@Column(name = "ENG_MEANING",columnDefinition = "TEXT")
	private String engMeaning;
	
	@Column(name = "SENTNC_EXAMPLE",columnDefinition = "TEXT")
	private String sentncExample;
	
	@Column(name = "MEMORISED")
	private String memorised;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CORRECT_TIMES",columnDefinition = "integer default 0")
	private Integer correctTimes;
	
	@Column(name = "WRONG_TIMES",columnDefinition = "integer default 0")
	private Integer wrongTimes;
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;

}
