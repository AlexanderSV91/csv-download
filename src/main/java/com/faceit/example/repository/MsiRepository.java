package com.faceit.example.repository;

import com.faceit.example.model.Msi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsiRepository extends JpaRepository<Msi, Long> {
}
