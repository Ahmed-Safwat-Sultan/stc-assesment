package com.stc.filesmanager.repository;

import com.stc.filesmanager.entity.Item;
import com.stc.filesmanager.entity.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PermissionGroupRepository extends JpaRepository<PermissionGroup, Long> {

    Set<PermissionGroup> findPermissionGroupsByItems(Item item);
    PermissionGroup findPermissionGroupByGroupName(String groupName);
}