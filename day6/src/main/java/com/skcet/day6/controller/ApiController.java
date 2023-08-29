package com.skcet.day6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skcet.day6.model.Child;
import com.skcet.day6.service.ApiService;



@RestController
@RequestMapping("api/v1/child")
public class ApiController {
         @Autowired
         private ApiService apiservice;
         
         @GetMapping("/getChild")
         public ResponseEntity<List<Child>>getchild(){
        	 return ResponseEntity.status(200).body(apiservice.getChild());
         }
         
         @GetMapping("/getAllUser")
      	public ResponseEntity<Page<Child>> getAllUser(
      				@RequestParam(defaultValue = "0") int page, 
      				@RequestParam(defaultValue = "10") int size
      				
      			){
      		PageRequest pageRequest = PageRequest.of(page, size);
      		return ResponseEntity.status(200).body(apiservice.getAllUser(pageRequest));
      	}
         
         @GetMapping("/getAllChild")
       	public ResponseEntity<Page<Child>> getAllChild(
       				@RequestParam(defaultValue = "0") int page, 
       				@RequestParam(defaultValue = "10") int size,
       				@RequestParam(defaultValue = "id") String sortField,
       				@RequestParam(defaultValue = "asc") String sortOrder
       			){
       		PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortField));
       		return ResponseEntity.status(200).body(apiservice.getAllChild(pageRequest));
       	}

         
         @PostMapping("/addChild")
         public ResponseEntity<String> addChild(@RequestBody Child child){
        	 boolean dataSaved=apiservice.addChild(child);
        	 if(dataSaved) {
        		 return ResponseEntity.status(200).body(" added successfully");
        	 }
        	 else {
        		 return ResponseEntity.status(404).body("not added");
        	 }
         }
         @PutMapping("/updateChild/{babyId}")
         public ResponseEntity<String>updateChild(@PathVariable Long babyId,@RequestBody Child child){
        	 boolean userData=apiservice.updateChild(babyId,child);
        	 if(userData) {
        		 return ResponseEntity.status(200).body("updated successfully");
        	 }
        	 else {
        		 return ResponseEntity.status(404).body("No record found to be updated");
        	 }
         }
         @DeleteMapping("/deleteChild")
 	 	public ResponseEntity<String> deleteChild(@RequestParam Long babyId){
 	 		boolean userDeleted =apiservice.deleteChild(babyId);
 	 		if(userDeleted) { 
 	 			return ResponseEntity.status(200).body("deleted successfully");
 	 		} else {
 	 			return ResponseEntity.status(404).body("No record found to be updated");
 	 		}
 	 	}
}

