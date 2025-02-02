package com.nontech.enotes.repository;

import com.nontech.enotes.entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepo extends JpaRepository<Notes, Integer>{


    Page<Notes> findByCreatedBy(Integer userId, Pageable pageable);
}
