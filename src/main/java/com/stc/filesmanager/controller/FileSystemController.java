package com.stc.filesmanager.controller;

import com.stc.filesmanager.DTO.ItemDTO;
import com.stc.filesmanager.DTO.PermissionGroupDTO;
import com.stc.filesmanager.entity.Item;
import com.stc.filesmanager.entity.PermissionGroup;
import com.stc.filesmanager.exceptions.DuplicateItemException;
import com.stc.filesmanager.exceptions.InvalidParentItemException;
import com.stc.filesmanager.exceptions.NoItemFoundException;
import com.stc.filesmanager.exceptions.NoPermissionException;
import com.stc.filesmanager.model.*;
import com.stc.filesmanager.service.FileSystemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class FileSystemController {

    @Autowired
    FileSystemService fileSystemService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/space/add")
    public ResponseEntity<ItemDTO> addSpace(@RequestBody String spaceName) throws DuplicateItemException {
        Item space = fileSystemService.addSpace(spaceName);
        ItemDTO spaceDTO = modelMapper.map(space, ItemDTO.class);
        return new ResponseEntity<>(spaceDTO, HttpStatus.OK);
    }

    @PostMapping("/folder/add")
    public ResponseEntity<ItemDTO> addFolder(@RequestBody FolderCreationRequest request) throws NoItemFoundException, InvalidParentItemException, NoPermissionException {
        ItemDTO folderDTO = modelMapper.map(fileSystemService.addFolder(request.getFolderName(), request.getParentName(), request.getUserEmail()), ItemDTO.class);
        return new ResponseEntity<>(folderDTO, HttpStatus.OK);
    }


    @PostMapping("/file/upload")
    public ResponseEntity<String> uploadFile(@ModelAttribute FileCreationRequest request) throws NoItemFoundException, IOException, InvalidParentItemException, NoPermissionException {
        MultipartFile file = request.getFile();
        if (file == null)
            return new ResponseEntity<>("File is empty", HttpStatus.BAD_REQUEST);
        String fileName = file.getOriginalFilename();
        fileSystemService.storeFile(fileName, file, request.getParentName(), request.getUserEmail());
        return ResponseEntity.ok("File uploaded successfully: " + fileName);

    }


    @GetMapping("/permissions/{fileName}")
    public ResponseEntity<Set<PermissionGroupDTO>> getItemMetaData(@PathVariable String fileName) {
        Set<PermissionGroupDTO> permissionGroupDTO = (fileSystemService.getAllPermissionGroups(fileName))
                .stream()
                .map(permissionGroup -> modelMapper.map(permissionGroup, PermissionGroupDTO.class))
                .collect(Collectors.toSet());
        return new ResponseEntity<>(permissionGroupDTO, HttpStatus.OK);
    }




}
