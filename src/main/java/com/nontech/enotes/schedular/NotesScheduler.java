/**
 * Created By Arun Singh
 * Date:12-02-2025
 * Time:01:14
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.schedular;

import com.nontech.enotes.entity.Notes;
import com.nontech.enotes.repository.NotesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotesScheduler {
    @Autowired
    private NotesRepo notesRepo;

    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(cron = "* * * ? * *")
    public void deleteNoteScheduler() {
//        System.out.println("Hello");
        LocalDateTime cutOffDate = LocalDateTime.now().minusDays(7);
        List<Notes> deleteNotes = notesRepo.findAllByIsDeletedAndDeletedOnBefore(true,cutOffDate);
        notesRepo.deleteAll(deleteNotes);
    }
}
