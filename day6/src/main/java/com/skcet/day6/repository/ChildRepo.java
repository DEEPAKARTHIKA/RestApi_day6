package com.skcet.day6.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skcet.day6.model.Child;

public interface ChildRepo extends JpaRepository<Child, Integer>{

	boolean existsByBabyId(int babyId);

	Optional<Child> findByBabyId(Long babyId);

	boolean deleteBybabyId(Long babyId);

	
       
}
