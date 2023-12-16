package com.stc.filesmanager.repository;

import com.stc.filesmanager.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findItemByName(String name);

}