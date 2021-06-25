package com.github.td.springBoot.shoppingList.persistence.dao.jpa;

import com.github.td.springBoot.shoppingList.persistence.model.Item;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class ItemDatabase {
    private final HashMap<Long, Item> itemHashMap = new HashMap<>();

    public HashMap<Long, Item> getItemHashMap() {
        return this.itemHashMap;
    }
}
