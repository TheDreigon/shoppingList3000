package com.github.td.springBoot.shoppingList.converter;

import com.github.td.springBoot.shoppingList.command.ItemCreateDto;
import com.github.td.springBoot.shoppingList.persistence.model.Item;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class ItemCreateDtoToNewItem {

    public Item convert(ItemCreateDto itemCreateDto) {

        Item newItem = new Item();

        newItem.setSerialNumber(UUID.randomUUID());
        newItem.setItemName(itemCreateDto.getItemName());
        newItem.setItemPrice(itemCreateDto.getItemPrice());

        return newItem;
    }
}
