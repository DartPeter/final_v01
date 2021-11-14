package com.my;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
 * http://localhost:8080/api/all
 * http://localhost:8080/api/single/1
 * http://localhost:8080/actuator/health
 * http://localhost:8080/actuator/metrics/jvm.threads.live
 */

@RestController
@RequestMapping("/api")
public class SomethingController {
	
	@Autowired
	SomethingRepository somethingRepository;
	
	@GetMapping("/all")
	public ResponseEntity<List<Something>> getAll() {
		System.out.println("all controller");
		List<Something> rl = somethingRepository.findAll();
		System.out.println(rl);
		System.out.println(rl.get(0));
		return new ResponseEntity<>(rl, HttpStatus.OK);
	}
	
	@GetMapping("/single/{id}")
	public ResponseEntity<Something> getSomethingById(@PathVariable("id") int id) {
		Something entityData = somethingRepository.findById(id);
		if (entityData != null) {
			return new ResponseEntity<> (entityData, HttpStatus.OK);
		} else {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
	}

}
