package com.stc.filesmanager.DTO;

import com.stc.filesmanager.entity.File;
import com.stc.filesmanager.entity.Item;
import com.stc.filesmanager.entity.PermissionGroup;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class ItemDTO {


    String type;
    String name;
    private Set<PermissionGroup> permissionGroups;

    private Item parentItem;

    private File file;
}
