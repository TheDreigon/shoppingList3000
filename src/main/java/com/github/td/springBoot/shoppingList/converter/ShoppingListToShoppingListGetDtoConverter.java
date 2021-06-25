package com.github.td.springBoot.shoppingList.converter;

import com.github.td.springBoot.shoppingList.command.ShoppingListGetDto;
import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;
import org.springframework.stereotype.Component;

@Component
public class ShoppingListToShoppingListGetDtoConverter {

    public ShoppingListGetDto convert(ShoppingList shoppingList) {

        ShoppingListGetDto shoppingListGetDto = new ShoppingListGetDto();
        shoppingListGetDto.setId(shoppingList.getId());
        shoppingListGetDto.setShopName(shoppingList.getShopName());
        shoppingListGetDto.setLastUpdate(shoppingList.getLastUpdate());
        return shoppingListGetDto;
    }
}
