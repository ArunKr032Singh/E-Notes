/**
 * Created By Arun Singh
 * Date:31-01-2025
 * Time:00:59
 * Project Name:Enotes-Api-Service
 */

package com.nontech.enotes.serviceImpl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nontech.enotes.dto.NotesDto;
import com.nontech.enotes.entity.FileDetails;
import com.nontech.enotes.entity.Notes;
import com.nontech.enotes.exception.ResourceNotFoundException;
import com.nontech.enotes.repository.CategoryRepo;
import com.nontech.enotes.repository.FileDetailsRepo;
import com.nontech.enotes.repository.NotesRepo;
import com.nontech.enotes.service.NotesService;
import org.apache.commons.io.FilenameUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class NotesServiceImpl implements NotesService {
    @Autowired
    private NotesRepo notesRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private FileDetailsRepo fileDetailsRepo;

    @Value("${file.upload.path}")
    private String uploadPath;

    @Override
    public Boolean saveNotes(String notes, MultipartFile file) throws Exception {
        ObjectMapper ob = new ObjectMapper();
        NotesDto notesDto = ob.readValue(notes, NotesDto.class);

        //category validation
        checkCategoryExists(notesDto.getCategory());
        Notes notesMap = mapper.map(notesDto, Notes.class);
        FileDetails fileDetails = saveFileDetails(file);

        if (!ObjectUtils.isEmpty(fileDetails)) {
            notesMap.setFileDetails(fileDetails);
        }else {
            notesMap.setFileDetails(null);
        }

        Notes savedNotes = notesRepo.save(notesMap);
        if (!ObjectUtils.isEmpty(savedNotes)) {
            return true;
        }
        return false;
    }

    private FileDetails saveFileDetails(MultipartFile file) throws IOException {
        if (!ObjectUtils.isEmpty(file) && !file.isEmpty()) {
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);

            List<String> extensionAllow = Arrays.asList(".pdf",".xlx",".jpg");
            if(!extensionAllow.contains(extension))
            {
                throw new IllegalArgumentException("Invalid file format ! We can upload only .pdf, .xlx, .jpg");
            }

            String randomString = UUID.randomUUID().toString();

            String uploadedFileName = randomString + "." + extension;

            File saveFile = new File(uploadPath);
            if(!saveFile.exists())
            {
                saveFile.mkdir();
            }
            //path:enotesapiservice/notes/abc.png
            String storePath = uploadPath.concat(uploadedFileName);

            //upload file
            long upload = Files.copy(file.getInputStream(), Paths.get(storePath));
            if(upload!=0)
            {
                FileDetails fileDetails = new FileDetails();
                fileDetails.setOriginalFileName(originalFilename);
                fileDetails.setDisplayFileName(getDisplayName(originalFilename));
                fileDetails.setUploadedFileName(uploadedFileName);
                fileDetails.setPath(storePath);
                fileDetails.setFileSize(file.getSize());

                FileDetails savedFileDetails = fileDetailsRepo.save(fileDetails);
                return savedFileDetails;
            }
        }
        return null;
    }

    private String getDisplayName(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        String fileName = FilenameUtils.removeExtension(originalFilename);
        if (fileName.length() > 8) {
            fileName = fileName.substring(0, 8);
        }
        fileName = fileName + "." + extension;
        return fileName;
    }

    private void checkCategoryExists(NotesDto.CategoryDto category) throws Exception {
        categoryRepo.findById(category.getId()).orElseThrow(() -> new ResourceNotFoundException("Category id is invalid"));
    }


    @Override
    public List<NotesDto> getAllNotes() {
        return notesRepo.findAll().stream().map(note -> mapper.map(note, NotesDto.class)).toList();
    }
}
