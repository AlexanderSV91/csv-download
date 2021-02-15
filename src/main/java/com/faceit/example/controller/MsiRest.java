package com.faceit.example.controller;

import com.faceit.example.dto.response.MsiResponse;
import com.faceit.example.mapper.MsiMapper;
import com.faceit.example.model.Msi;
import com.faceit.example.service.MsiService;
import com.faceit.example.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = {"/api"})
public class MsiRest {

    private final MsiService msiService;
    private final MsiMapper msiMapper;

    @Autowired
    public MsiRest(MsiService msiService, MsiMapper msiMapper) {
        this.msiService = msiService;
        this.msiMapper = msiMapper;
    }

    @PostMapping("/msi")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (Utils.hasCSVFormat(file)) {
            try {
                msiService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(message);
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
            }
        }
        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

    @GetMapping("/msi")
    public ResponseEntity<Page<MsiResponse>> findPageableMsi(final Pageable pageable) {
        Page<Msi> pageableMsi = msiService.findPageableMsi(pageable);
        if (pageableMsi.getContent().isEmpty()) {
            throw new RuntimeException("not found");
        }
        List<MsiResponse> bookResponseList = msiMapper.msiListToMsiResponseList(pageableMsi.getContent());
        Page<MsiResponse> msiResponses = Utils.pageEntityToPageResponse(pageableMsi, bookResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(msiResponses);
    }
}
