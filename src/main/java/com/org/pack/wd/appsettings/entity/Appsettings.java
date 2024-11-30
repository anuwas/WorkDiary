package com.org.pack.wd.appsettings.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "APPSETTINGS")
@Setter
@Getter
public class Appsettings {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

}
