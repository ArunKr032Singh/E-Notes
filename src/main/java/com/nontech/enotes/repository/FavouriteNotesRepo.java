package com.nontech.enotes.repository;

import com.nontech.enotes.entity.FavouriteNotes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavouriteNotesRepo extends JpaRepository<FavouriteNotes, Integer> {

}
