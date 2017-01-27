package com.example.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.model.Activity;

public interface ActivityRepository extends CrudRepository<Activity, Long>{
	
	@Query("SELECT a FROM Activity a "
			+ "JOIN a.activitiesBlock ab "
			+ "WHERE MONTH(ab.startDate) = :month AND YEAR(ab.startDate) = :year "
			+ "ORDER BY ab.startDate ASC")
	Set<Activity> findAllByMonthAndYear(@Param("month") Integer month, @Param("year") Integer year);
	
	//TODO: repasar y mejorar
	@Query("SELECT a FROM Activity a WHERE MONTH(a.dateTimeStart) = ?1 AND YEAR(a.dateTimeStart) = ?2 AND a.zone.name LIKE ?3 ORDER BY a.dateTimeStart ASC")
	Set<Activity> findAllByMonthAndYearAndZone(@Param("month") Integer month, @Param("year") Integer year, @Param("zone") String zone);
	
}
