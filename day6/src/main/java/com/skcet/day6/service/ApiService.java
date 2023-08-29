package com.skcet.day6.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.skcet.day6.model.Child;

public interface ApiService {

	
	List<Child> getChild();

	boolean addChild(Child child);

	boolean updateChild(Long babyId, Child child);

	boolean deleteChild(Long babyId);

    Page<Child> getAllUser(PageRequest pageRequest);

	Page<Child> getAllChild(PageRequest pageRequest);
     
}
