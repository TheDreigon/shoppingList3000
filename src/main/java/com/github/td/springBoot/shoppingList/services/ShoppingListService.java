package com.github.td.springBoot.shoppingList.services;

import com.github.td.springBoot.shoppingList.command.ShoppingListCreateDto;
import com.github.td.springBoot.shoppingList.command.ShoppingListGetDto;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;

import java.util.List;

public interface ShoppingListService {

    //CREATE
    ShoppingList createShoppingList(ShoppingListCreateDto shoppingListCreateDto);

    //READ ALL
    List<ShoppingListGetDto> getShoppingListList();

    //READ
    ShoppingListGetDto getShoppingList(long id)
            throws ShoppingListException;

    //DELETE
    void deleteShoppingList(long id)
            throws ShoppingListException;
}
