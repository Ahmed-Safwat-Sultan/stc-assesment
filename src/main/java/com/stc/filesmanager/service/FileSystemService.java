package com.stc.filesmanager.service;

import com.stc.filesmanager.exceptions.DuplicateItemException;
import com.stc.filesmanager.exceptions.InvalidParentItemException;
import com.stc.filesmanager.exceptions.NoItemFoundException;
import com.stc.filesmanager.exceptions.NoPermissionException;
import com.stc.filesmanager.entity.File;
import com.stc.filesmanager.entity.Item;
import com.stc.filesmanager.entity.Permission;
import com.stc.filesmanager.entity.PermissionGroup;
import com.stc.filesmanager.repository.FilesRepository;
import com.stc.filesmanager.repository.ItemRepository;
import com.stc.filesmanager.repository.PermissionGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Service
public class FileSystemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PermissionGroupRepository permissionGroupRepository;
    @Autowired
    private FilesRepository filesRepository;

    public Item addSpace(String spaceName) throws DuplicateItemException {
        var item = new Item();
        item.setName(spaceName);
        item.setType("SPACE");
        var group = getAdminGroup();

        Item fetchedItem = itemRepository.findItemByName(spaceName);
        if(fetchedItem != null){
            throw new DuplicateItemException("This space name is already used");
        }

        var permissionGroupSet = new HashSet<PermissionGroup>();
        permissionGroupSet.add(group);
        item.setPermissionGroups(permissionGroupSet);
        return itemRepository.save(item);
    }

    public Item addFolder(String folderName, String parentName, String userEmail) throws NoItemFoundException, InvalidParentItemException, NoPermissionException {
        var item = new Item();
        item.setName(folderName);
        item.setType("FOLDER");
        Item parentItem = findParentItem(parentName);




        if(!isPermitted(userEmail, parentItem)){
            throw new NoPermissionException("This userEmail is not permitted to edit under: " + parentName);
        }

        item.setParentItem(parentItem);

        var group = getAdminGroup();

        var permissionGroupSet = new HashSet<PermissionGroup>();
        permissionGroupSet.add(group);
        item.setPermissionGroups(permissionGroupSet);
        return itemRepository.save(item);
    }


    public void storeFile(String fileName, MultipartFile file, String parentItemName, String userEmail) throws IOException, NoItemFoundException, InvalidParentItemException, NoPermissionException {


        Item parentItem = findParentItem(parentItemName);

        Item item = new Item();
        item.setName(fileName);
        item.setParentItem(parentItem);

        var group = getAdminGroup();
        var permissionGroupSet = new HashSet<PermissionGroup>();
        permissionGroupSet.add(group);
        item.setPermissionGroups(permissionGroupSet);

        if(!isPermitted(userEmail, parentItem)){
            throw new NoPermissionException("This userEmail is not permitted to edit under: " + parentItem.getName());
        }

        File newFile = new File();
        var items = new ArrayList<Item>();
        items.add(item);
        newFile.setItem(items);
        newFile.setContent(file.getBytes());

        filesRepository.save(newFile);
    }

    public Item findItemByName(String fileName) throws NoItemFoundException {
        Item item = itemRepository.findItemByName(fileName);

        if(item == null )
            throw new NoItemFoundException("No items found called " + fileName);

        return item;
    }

    public Item findParentItem(String parentItemName) throws NoItemFoundException, InvalidParentItemException {
        Item parentItem = findItemByName(parentItemName);

        if("FILE".equals(parentItem.getType()))
            throw new InvalidParentItemException("The parent file cannot be a file");
        return parentItem;
    }


    public boolean isPermitted(String userEmail, Item parentItem){
        var itemsSet = new HashSet<Item>();
        itemsSet.add(parentItem);
        boolean isPermitted = false;
        var permissionGroupsForParent = permissionGroupRepository.findPermissionGroupsByItems(parentItem);
        for(PermissionGroup pg : permissionGroupsForParent){
            for(Permission p : pg.getPermissions()){
                if(userEmail.equals(p.getUserEmail()) && p.getPermissionLevel().equals("EDIT")){
                    isPermitted = true;
                }
            }
        }
        return isPermitted;
    }

    public Set<PermissionGroup> getAllPermissionGroups(String fileName){
        Item fetchedItem = itemRepository.findItemByName(fileName);
        return permissionGroupRepository.findPermissionGroupsByItems(fetchedItem);
    }

    public PermissionGroup getAdminGroup(){
        var group = permissionGroupRepository.findPermissionGroupByGroupName("admin");
        if(group == null)
            throw new RuntimeException("admin group is not configured");
        return group;
    }
}
