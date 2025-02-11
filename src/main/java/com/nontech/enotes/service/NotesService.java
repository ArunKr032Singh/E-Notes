package com.nontech.enotes.service;

import com.nontech.enotes.dto.NotesDto;
import com.nontech.enotes.dto.response.NotesResponse;
import com.nontech.enotes.entity.FileDetails;
import com.nontech.enotes.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import java.util.List;

public interface NotesService {

    public Boolean saveNotes(String notes, MultipartFile file) throws Exception;

    public List<NotesDto> getAllNotes();

    byte[] downLoadFile(FileDetails fileDetails) throws ResourceNotFoundException, IOException;

    FileDetails getFileDetails(Integer id) throws ResourceNotFoundException;

    NotesResponse getAllNotesByUser(Integer userId, Integer pageNo, Integer pageSize);

}
