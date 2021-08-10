package com.faceit.example.controller;

import com.faceit.example.dto.MsiResponse;
import com.faceit.example.service.MsiService;
import com.faceit.example.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/api/v1/msi"})
public class MsiRest {

    private final MsiService msiService;

    public MsiRest(MsiService msiService) {
        this.msiService = msiService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") final MultipartFile file) {
        if (Utils.hasCSVFormat(file)) {
            try {
                this.msiService.save(file);
                return ResponseEntity
                        .ok("Uploaded the file successfully: " + file.getOriginalFilename());
            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.EXPECTATION_FAILED)
                        .body("Could not upload the file: " + file.getOriginalFilename());
            }
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Please upload a csv file!");
    }

    @GetMapping
    public ResponseEntity<Page<MsiResponse>> findPageableMsi(final Pageable pageable) {
        return ResponseEntity.ok(this.msiService.findPageableMsi(pageable));
    }
}
