package com.faceit.example.service;

import com.faceit.example.dto.MsiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface MsiService {

    void save(MultipartFile file);

    Page<MsiResponse> findPageableMsi(Pageable pageable);
}
