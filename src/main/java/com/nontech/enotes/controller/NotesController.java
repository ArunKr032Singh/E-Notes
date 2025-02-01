package com.nontech.enotes.controller;

import com.nontech.enotes.dto.CategoryDto;
import com.nontech.enotes.dto.NotesDto;
import com.nontech.enotes.service.NotesService;
import com.nontech.enotes.util.CommonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        Boolean saveNotes = notesService.saveNotes(notes,file);
        if (saveNotes) {
            return CommonUtil.createBuildResponseMessage("Saved notes", HttpStatus.CREATED);
        } else {
            return CommonUtil.createErrorResponseMessage("Not saved", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCategory() {
        List<NotesDto> notesDtos = notesService.getAllNotes();
        if (CollectionUtils.isEmpty(notesDtos)) {
            return ResponseEntity.noContent().build();
        } else {
            return CommonUtil.createBuildResponse(notesDtos, HttpStatus.OK);
        }
    }

//    @GetMapping("/active")
//    public ResponseEntity<?> getActiveCategory() {
//        List<CategoryResponse> categoryDtoList = categoryService.getActiveCategory();
//        if (CollectionUtils.isEmpty(categoryDtoList)) {
//            return ResponseEntity.noContent().build();
//        } else {
////            return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
//            return CommonUtil.createBuildResponse(categoryDtoList, HttpStatus.OK);
//        }
//    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception {
//
//
//        CategoryDto categoryDto = categoryService.getCategyById(id);
//        if (ObjectUtils.isEmpty(categoryDto)) {
////            return new ResponseEntity<>("Internal Server Error", HttpStatus.NOT_FOUND);
//            return CommonUtil.createErrorResponseMessage("Internal Server Error", HttpStatus.NOT_FOUND);
//        }
////        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
//        return CommonUtil.createBuildResponse(categoryDto, HttpStatus.OK);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id) {
//        boolean deleted = categoryService.deleteCategyById(id);
//        if (deleted) {
////            return new ResponseEntity<>("Category deleted", HttpStatus.OK);
//            return CommonUtil.createBuildResponse("Category deleted success",HttpStatus.OK);
//        }
////        return new ResponseEntity<>("Category not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
//        return CommonUtil.createErrorResponseMessage("Category not deleted ", HttpStatus.INTERNAL_SERVER_ERROR);
//    }


}
