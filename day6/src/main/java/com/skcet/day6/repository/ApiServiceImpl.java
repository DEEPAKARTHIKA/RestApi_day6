package com.skcet.day6.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.skcet.day6.model.Child;
import com.skcet.day6.service.ApiService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ApiServiceImpl implements ApiService{
	@Autowired
	private ChildRepo childRepo;
	
	@Override
	public boolean addChild(Child child) {
		boolean userExists=childRepo.existsByBabyId(child.getBabyId());
		if(!userExists) {
			childRepo.save(child);
			return true;
		}
		else {
			return false;
		}
	}
	@Override
	public List<Child> getChild(){
		return childRepo.findAll();
		}
	
	@Override
	public boolean updateChild(Long babyId,Child child) {
//		userRepository.saveAndFlush(userClass);
		Optional<Child>existingUserOptional=childRepo.findByBabyId(babyId);
		if(existingUserOptional.isPresent()) {
		Child userExists=existingUserOptional.get()	;
		userExists.setBabyFirstName(child.getBabyFirstName());
		userExists.setBabyLastName(child.getBabyLastName());
		userExists.setFatherName(child.getFatherName());
		userExists.setMotherName(child.getMotherName());
		userExists.setAddress(child.getAddress());
		childRepo.save(userExists);
				return true;
		}
	else {
		return false;
	}
	}
	@Override
	public boolean deleteChild(Long babyId) {
		
		Optional<Child> existingUserOptional = childRepo.findByBabyId(babyId);
		if(existingUserOptional.isPresent()) {
			childRepo.deleteBybabyId(babyId);
			return true;
		} else {
			return false;
		}
	}
	@Override
	public Page<Child> getAllUser(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Child> getAllChild(PageRequest pageRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

