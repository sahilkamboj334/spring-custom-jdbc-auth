package com.example.demo.res;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

	@GetMapping("/{value}")
	public ResponseEntity<String> response(@PathVariable("value") String value){
		return ResponseEntity.ok(value);
	}
	
	@PostMapping("/{value}")
	public ResponseEntity<String> response1(@PathVariable("value") String value){
		System.out.println("post");
		return ResponseEntity.ok(value);
	}
	@DeleteMapping("/{value}")
	public ResponseEntity<String> response2(@PathVariable("value") String value){
		System.out.println("delete");
		return ResponseEntity.ok(value);
	}
	@PutMapping("/{value}")
	public ResponseEntity<String> response3(@PathVariable("value") String value){
		System.out.println("put");
		return ResponseEntity.ok(value);
	}

}

