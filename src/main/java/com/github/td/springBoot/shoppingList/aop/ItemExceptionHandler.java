package com.github.td.springBoot.shoppingList.aop;

import com.github.td.springBoot.shoppingList.exceptions.ItemException;
import com.github.td.springBoot.shoppingList.persistence.dao.jpa.ItemDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemExceptionHandler {
    private ItemDatabase itemDatabase;

    @Autowired
    public void setItemDatabase(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    public void checkIfItemExists(Long itemId) throws ItemException {

        System.out.println("Checking if item exists...");
        if (!this.itemDatabase.getItemHashMap().containsKey(itemId))
            throw new ItemException("Item does not exist");
        System.out.println("Item exists!");
    }

    public void checkItemPrice(int itemPrice) throws ItemException {

        System.out.println("Checking item price...");
        if (itemPrice <= 0)
            throw new ItemException("Item price too low");
        System.out.println("Item price is valid!");
    }
}
