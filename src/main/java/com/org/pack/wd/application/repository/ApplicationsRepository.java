package com.org.pack.wd.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.pack.wd.application.entity.Applications;


@Repository
public interface ApplicationsRepository extends JpaRepository<Applications, Long>{

	List<Applications> findAllByAppStatus(String appstatus);
}
