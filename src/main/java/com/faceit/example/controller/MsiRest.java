package com.faceit.example.controller;

import com.faceit.example.dto.response.MsiResponse;
import com.faceit.example.service.MsiService;
import com.faceit.example.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/api"})
public class MsiRest {

    private final MsiService msiService;

    @Autowired
    public MsiRest(MsiService msiService) {
        this.msiService = msiService;
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
        Page<MsiResponse> msiResponses = msiService.findPageableMsi(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(msiResponses);
    }
}
