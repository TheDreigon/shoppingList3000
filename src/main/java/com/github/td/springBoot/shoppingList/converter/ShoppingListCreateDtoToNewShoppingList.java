package com.github.td.springBoot.shoppingList.converter;

import com.github.td.springBoot.shoppingList.command.ShoppingListCreateDto;
import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListCreateDtoToNewShoppingList {

    public ShoppingList convert(ShoppingListCreateDto shoppingListCreateDto) {

        ShoppingList newShoppingList = new ShoppingList();
        newShoppingList.setShopName(shoppingListCreateDto.getShopName());
        return newShoppingList;
    }
}
