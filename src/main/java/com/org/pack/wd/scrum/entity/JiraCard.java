package com.org.pack.wd.scrum.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "JIRA_CARD")
@Setter
@Getter
public class JiraCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "jira_card_title", columnDefinition = "TEXT")
    private String jiraCardTitle;

    @Column(name = "jira_card_description", columnDefinition = "TEXT")
    private String jiraCardDescription;

    @Column(name = "jira_card_status", columnDefinition = "TEXT")
    private String jiraCardStatus;

    @Column(name = "CREATED_DATE",insertable=true,updatable=false)
    @CreationTimestamp
    private Timestamp createdDate;

    @Column(name = "MODIFIED_DATE",insertable=false,updatable=true)
    @UpdateTimestamp
    private Timestamp modifiedDate;

}
