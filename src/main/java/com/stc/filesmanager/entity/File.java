package com.stc.filesmanager.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FILES")
@Data
public class File {

    @GeneratedValue()
    @Id()
    Long id;

    @Lob
    @Column(name = "CONTENT")
    byte[] content;
    @Column(name = "ITEM_PERMISSION_GROUP_ID")
    Long permissionGroupId;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "file")
    List<Item> item = new ArrayList<>();
}
