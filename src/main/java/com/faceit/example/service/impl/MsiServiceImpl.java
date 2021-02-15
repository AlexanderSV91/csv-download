package com.faceit.example.service.impl;

import com.faceit.example.model.Msi;
import com.faceit.example.repository.MsiRepository;
import com.faceit.example.service.MsiService;
import com.faceit.example.util.Utils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class MsiServiceImpl implements MsiService {

    private final MsiRepository msiRepository;

    public MsiServiceImpl(MsiRepository msiRepository) {
        this.msiRepository = msiRepository;
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
    public Page<Msi> findPageableMsi(Pageable pageable) {
        return msiRepository.findAll(pageable);
    }
}
