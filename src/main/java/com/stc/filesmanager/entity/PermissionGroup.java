package com.stc.filesmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "permission_group")
@Data
public class PermissionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "group_name", nullable = false)
    private String groupName;

    @ManyToMany(mappedBy = "permissionGroups")
    private Set<Item> items;

    @OneToMany(mappedBy = "permissionGroup")
    private List<Permission> permissions;


}