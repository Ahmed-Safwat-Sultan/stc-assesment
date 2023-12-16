package com.stc.filesmanager.repository;

import com.stc.filesmanager.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
}