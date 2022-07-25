package com.springter.business.repository;


import com.springter.business.repository.model.PostDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringerRepository extends JpaRepository<PostDAO, Long> {

    List<PostDAO> findAllByUserId(Long id);
}
