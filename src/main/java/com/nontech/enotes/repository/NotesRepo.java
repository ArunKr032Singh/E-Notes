package com.nontech.enotes.repository;

import com.nontech.enotes.entity.Category;
import com.nontech.enotes.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotesRepo extends JpaRepository<Notes, Integer>{


}
