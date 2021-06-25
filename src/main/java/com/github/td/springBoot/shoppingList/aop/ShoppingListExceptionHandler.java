package com.github.td.springBoot.shoppingList.aop;

import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.dao.jpa.ShoppingListDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListExceptionHandler {
    private ShoppingListDatabase creditCardDatabase;

    @Autowired
    public void setCreditCardDatabase(ShoppingListDatabase shoppingListDatabase) {
        this.creditCardDatabase = shoppingListDatabase;
    }

    public void checkIfShoppingListExists(Long creditCardId) throws ShoppingListException {

        System.out.println("Checking if shopping list exists...");
        if (!this.creditCardDatabase.getShoppingListHashMap().containsKey(creditCardId))
            throw new ShoppingListException("Shopping list does not exist");
        System.out.println("Shopping list exists!");
    }
}
