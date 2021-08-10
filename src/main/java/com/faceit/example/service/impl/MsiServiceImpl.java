package com.faceit.example.service.impl;

import com.faceit.example.dto.MsiResponse;
import com.faceit.example.mapper.MsiMapper;
import com.faceit.example.model.Msi;
import com.faceit.example.repository.MsiRepository;
import com.faceit.example.service.MsiService;
import com.faceit.example.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class MsiServiceImpl implements MsiService {

    private final MsiRepository msiRepository;
    private final MsiMapper msiMapper;

    public MsiServiceImpl(MsiRepository msiRepository,
                          MsiMapper msiMapper) {
        this.msiRepository = msiRepository;
        this.msiMapper = msiMapper;
    }

    @Override
    public void save(final MultipartFile file) {
        try {
            this.msiRepository.saveAll(Utils.csvToMsi(file.getInputStream()));
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public Page<MsiResponse> findPageableMsi(final Pageable pageable) {
        final Page<Msi> pageableMsi = this.msiRepository.findAll(pageable);
        if (pageableMsi.getContent().isEmpty()) {
            throw new RuntimeException("not found");
        }
        return pageEntityToPageResponse(pageableMsi);
    }

    private PageImpl<MsiResponse> pageEntityToPageResponse(final Page<Msi> page) {
        return new PageImpl<>(this.msiMapper.msiListToMsiResponses(page.getContent()),
                page.getPageable(),
                page.getTotalElements());
    }
}
