package com.org.pack.wd.appsettings.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.pack.wd.appsettings.entity.Appsettings;

public interface AppsettingsRepository extends JpaRepository<Appsettings,Long>{

}
