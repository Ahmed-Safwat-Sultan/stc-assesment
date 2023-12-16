package com.stc.filesmanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "permissions")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_email", nullable = false)
    private String userEmail;

    @Column(name = "permission_level", nullable = false)
    private String permissionLevel;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonIgnore
    private PermissionGroup permissionGroup;



}