package com.github.td.springBoot.shoppingList.converter;

import com.github.td.springBoot.shoppingList.command.ItemGetDto;
import com.github.td.springBoot.shoppingList.persistence.model.Item;
import org.springframework.stereotype.Component;

@Component
public class ItemToItemGetDtoConverter {

    public ItemGetDto convert(Item item) {

        ItemGetDto itemGetDto = new ItemGetDto();

        itemGetDto.setId(item.getId());
        itemGetDto.setSerialNumber(item.getSerialNumber());
        itemGetDto.setItemName(item.getItemName());
        itemGetDto.setItemPrice(item.getItemPrice());

        return itemGetDto;
    }
}
