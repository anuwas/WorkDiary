package com.org.pack.wd.scrum.repository;

import com.org.pack.wd.scrum.entity.JiraCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraCardRepository extends JpaRepository<JiraCard, Long> {

}
