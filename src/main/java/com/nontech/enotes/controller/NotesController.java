package com.nontech.enotes.controller;

import com.nontech.enotes.dto.NotesDto;
import com.nontech.enotes.dto.response.NotesResponse;
import com.nontech.enotes.entity.FileDetails;
import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.service.NotesService;
import com.nontech.enotes.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {
    @Autowired
    private NotesService notesService;

    @PostMapping("/save")
    public ResponseEntity<?> saveNotes(@RequestParam String notes, @RequestParam(required = false) MultipartFile file) throws Exception {

        Boolean saveNotes = notesService.saveNotes(notes, file);
        if (saveNotes) {
            return CommonUtil.createBuildResponseMessage("Saved notes", HttpStatus.CREATED);
        } else {
            return CommonUtil.createErrorResponseMessage("Not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception {
        FileDetails fileDetails = notesService.getFileDetails(id);
        byte[] downloadFile = notesService.downLoadFile(fileDetails);
        HttpHeaders httpHeaders = new HttpHeaders();
        String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
        httpHeaders.setContentType(MediaType.parseMediaType(contentType));
        httpHeaders.setContentDispositionFormData("attachment", fileDetails.getOriginalFileName());

        return ResponseEntity.ok().headers(httpHeaders).body(downloadFile);
    }

    @GetMapping("/")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllNotes() {
        List<NotesDto> notesDtos = notesService.getAllNotes();
        if (CollectionUtils.isEmpty(notesDtos)) {
            return ResponseEntity.noContent().build();
        } else {
            return CommonUtil.createBuildResponse(notesDtos, HttpStatus.OK);
        }
    }

    @GetMapping("/user-notes")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllNotesByUser(@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                                               @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        Integer userId = 2;
        NotesResponse notes = notesService.getAllNotesByUser(userId, pageNo, pageSize);
//        if (CollectionUtils.isEmpty(notesDtoList)) {
//            return ResponseEntity.noContent().build();
//        }
        return CommonUtil.createBuildResponse(notes, HttpStatus.OK);

    }

    @GetMapping("delete/{id}")
    public ResponseEntity<?> deleteNotes(@PathVariable Integer id) throws ResourceNotFoundException {
        boolean deleted = notesService.sofDeleteNote(id);
        if (deleted) {
            return CommonUtil.createBuildResponse("Notes deleted success", HttpStatus.OK);
        }
        return CommonUtil.createErrorResponseMessage("Notes not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("restore/{id}")
    public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws ResourceNotFoundException {
        boolean restored = notesService.restoreNotes(id);
        if (restored) {
            return CommonUtil.createBuildResponse("Notes restored success", HttpStatus.OK);
        }
        return CommonUtil.createErrorResponseMessage("Notes not restored", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("recycle-bin")
    public ResponseEntity<?> restoreUserNotesFromRecycle() throws ResourceNotFoundException {
        Integer userId = 2;
        List<NotesDto> notes = notesService.restoreUserNotesFromRecycleBin(userId);
        if (CollectionUtils.isEmpty(notes)) {
            return CommonUtil.createBuildResponseMessage("Notes not available in recycle bin", HttpStatus.OK);
        }
        return CommonUtil.createBuildResponse(notes, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws ResourceNotFoundException {
        notesService.hardDeleteNotes(id);
        return CommonUtil.createBuildResponse("Notes deleted success", HttpStatus.OK);
    }

    @DeleteMapping("empty-recycle-bin")
    public ResponseEntity<?> emptyRecycleBin() throws ResourceNotFoundException {
        int userId =2;
        notesService.emptyRecycleBin(userId);
        return CommonUtil.createBuildResponse("Notes deleted success", HttpStatus.OK);
    }


}
