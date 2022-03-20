package com.org.pack.wd.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "BOOK_MARK")
@Setter
@Getter
public class BookMark {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "BOOK_MARK_TITLE",columnDefinition = "TEXT")
	private String bookMarkTitle;
	
	@Column(name = "BOOK_MARK_TAG",columnDefinition = "TEXT")
	private String bookMarkTag;

	@Column(name = "BOOK_MARK_DESCRIPTION",columnDefinition = "TEXT")
	private String bookMarkDescription;
	
	@Column(name = "BOOK_MARK_ACTIVE",columnDefinition = "varchar(50)")
	private String bookMarkActive;
	
	@Column(name = "CREATED_DATE",insertable=true,updatable=false)
	@CreationTimestamp
	private Timestamp createdDate;
	
	
	@Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
	@UpdateTimestamp
	private Timestamp modifiedDate;

}
