package com.stc.filesmanager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ITEM_METADATA",
        uniqueConstraints = @UniqueConstraint(columnNames={"ITEM_NAME", "PARENT_ITEM_ID"})
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @GeneratedValue()
    @Id()
    Long id;

    @Column(name = "ITEM_TYPE")
    String type;
    @Column(name = "ITEM_NAME")
    String name;
    @ManyToMany
    @JoinTable(
            name = "item_permission_group",
            joinColumns = @JoinColumn(name = "item_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_group_id")
    )
    private Set<PermissionGroup> permissionGroups;

    @ManyToOne()
    @JoinColumn(name = "parent_item_id")
    private Item parentItem;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;



}
