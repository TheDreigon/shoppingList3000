package com.github.td.springBoot.shoppingList.services;

import com.github.td.springBoot.shoppingList.command.ItemCreateDto;
import com.github.td.springBoot.shoppingList.command.ItemGetDto;
import com.github.td.springBoot.shoppingList.exceptions.ItemException;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.model.Item;
import java.util.List;

public interface ItemService {

    //CREATE
    Item createItem(long paramLong, ItemCreateDto paramItemCreateDto) throws ShoppingListException, ItemException;

    //READ ALL
    List<ItemGetDto> getItemList(long paramLong) throws ShoppingListException;

    //READ
    ItemGetDto getItem(long paramLong1, long paramLong2) throws ShoppingListException, ItemException;

    //DELETE
    void deleteItem(long paramLong1, long paramLong2) throws ShoppingListException, ItemException;
}
