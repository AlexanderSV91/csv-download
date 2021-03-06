package com.faceit.example.service.impl;

import com.faceit.example.dto.response.MsiResponse;
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
import java.util.List;

@Service
public class MsiServiceImpl implements MsiService {

    private final MsiRepository msiRepository;
    private final MsiMapper msiMapper;

    public MsiServiceImpl(MsiRepository msiRepository, MsiMapper msiMapper) {
        this.msiRepository = msiRepository;
        this.msiMapper = msiMapper;
    }

    @Override
    public void save(MultipartFile file) {
        try {
            List<Msi> tutorials = Utils.csvToMsi(file.getInputStream());
            msiRepository.saveAll(tutorials);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    @Override
    public Page<MsiResponse> findPageableMsi(Pageable pageable) {
        Page<Msi> pageableMsi = msiRepository.findAll(pageable);
        if (pageableMsi.getContent().isEmpty()) {
            throw new RuntimeException("not found");
        }
        List<MsiResponse> bookResponseList = msiMapper.msiListToMsiResponseList(pageableMsi.getContent());
        return pageEntityToPageResponse(pageableMsi, bookResponseList);
    }

    private PageImpl<MsiResponse> pageEntityToPageResponse(Page<Msi> page, List<MsiResponse> list) {
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }
}
