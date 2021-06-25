package com.github.td.springBoot.shoppingList.persistence.dao.jpa;

import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class ShoppingListDatabase {

    private final HashMap<Long, ShoppingList> shoppingListHashMap = new HashMap<>();

    public HashMap<Long, ShoppingList> getShoppingListHashMap() {
        return shoppingListHashMap;
    }
}
