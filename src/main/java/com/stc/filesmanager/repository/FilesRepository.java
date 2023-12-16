package com.stc.filesmanager.repository;

import com.stc.filesmanager.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilesRepository extends JpaRepository<File, Long> {
}