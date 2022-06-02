package com.springter.business.repository;


import com.springter.business.repository.model.PostDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringerRepository extends JpaRepository<PostDAO, Long> {
}
