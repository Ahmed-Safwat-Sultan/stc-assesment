package com.stc.filesmanager.DTO;

import com.stc.filesmanager.entity.Item;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FileDTO {

    private byte[] content;
    private Long permissionGroupId;

    private List<Item> item = new ArrayList<>();
}
