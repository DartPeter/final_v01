package com.my;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestParam;

public interface SomethingRepository extends JpaRepository<Something, Integer>{
	
	List<Something> findAll();
	Something findById(@RequestParam int id);
	
}
