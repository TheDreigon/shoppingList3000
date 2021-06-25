package com.github.td.springBoot.shoppingList.services;

import java.util.*;

import com.github.td.springBoot.shoppingList.command.ShoppingListGetDto;
import com.github.td.springBoot.shoppingList.converter.ShoppingListCreateDtoToNewShoppingList;
import com.github.td.springBoot.shoppingList.converter.ShoppingListToShoppingListGetDtoConverter;
import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;
import com.github.td.springBoot.shoppingList.aop.ShoppingListExceptionHandler;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.dao.jpa.ShoppingListDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.td.springBoot.shoppingList.command.ShoppingListCreateDto;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

    private int id = 0;
    private ShoppingListDatabase shoppingListDatabase;
    private ShoppingListToShoppingListGetDtoConverter shoppingListToShoppingListGetDtoConverter;
    private ShoppingListCreateDtoToNewShoppingList shoppingListCreateDtoToNewShoppingList;
    private ShoppingListExceptionHandler shoppingListExceptionHandler;
    private List<ShoppingListGetDto> shoppingListGetDtoList = new ArrayList<>();

    @Autowired
    public void setShoppingListDatabase(ShoppingListDatabase shoppingListDatabase) {
        this.shoppingListDatabase = shoppingListDatabase;
    }

    @Autowired
    public void setCreditCardToCreditCardGetDtoConverter(ShoppingListToShoppingListGetDtoConverter shoppingListToShoppingListGetDtoConverter) {
        this.shoppingListToShoppingListGetDtoConverter = shoppingListToShoppingListGetDtoConverter;
    }

    @Autowired
    public void setCreditCardCreateDtoToNewCreditCard(ShoppingListCreateDtoToNewShoppingList shoppingListCreateDtoToNewShoppingList) {
        this.shoppingListCreateDtoToNewShoppingList = shoppingListCreateDtoToNewShoppingList;
    }

    @Autowired
    public void setCreditCardExceptionHandler(ShoppingListExceptionHandler shoppingListExceptionHandler) {
        this.shoppingListExceptionHandler = shoppingListExceptionHandler;
    }

    /**
     * POST {@inheritDoc}
     */
    @Override
    public ShoppingList createShoppingList(final ShoppingListCreateDto shoppingListCreateDto) {

        System.out.println("POST METHOD CALLED");

        ShoppingList newShoppingList = this.shoppingListCreateDtoToNewShoppingList.convert(shoppingListCreateDto);
        newShoppingList.setId(this.id++);

        this.shoppingListDatabase.getShoppingListHashMap().put(newShoppingList.getId(), newShoppingList);

        System.out.println(this.shoppingListDatabase.getShoppingListHashMap().get(newShoppingList.getId()).toString());
        System.out.println("List size: " + this.shoppingListDatabase.getShoppingListHashMap().size());

        return newShoppingList;
    }

    /**
     * GET ALL {@inheritDoc}
     */
    @Override
    public List<ShoppingListGetDto> getShoppingListList() {

        System.out.println("GET ALL METHOD CALLED");

        for (ShoppingList shoppingList : this.shoppingListDatabase.getShoppingListHashMap().values()) {
            shoppingListGetDtoList.add(shoppingListToShoppingListGetDtoConverter.convert(shoppingList));
            System.out.println(shoppingListToShoppingListGetDtoConverter.convert(shoppingList).toString());
        }

        System.out.println("List size: " + this.shoppingListDatabase.getShoppingListHashMap().size());

        return shoppingListGetDtoList;
    }

    /**
     * GET {@inheritDoc}
     */
    @Override
    public ShoppingListGetDto getShoppingList(final long sLId) throws ShoppingListException {

        System.out.println("GET METHOD CALLED");
        shoppingListExceptionHandler.checkIfShoppingListExists(sLId);

        ShoppingList shoppingList = this.shoppingListDatabase.getShoppingListHashMap().get(sLId);

        System.out.println(shoppingListToShoppingListGetDtoConverter.convert(shoppingList).toString());

        return shoppingListToShoppingListGetDtoConverter.convert(shoppingList);
    }

    /**
     * DELETE {@inheritDoc}
     */
    @Override
    public void deleteShoppingList(final long sLId) throws ShoppingListException {

        System.out.println("DELETE METHOD CALLED");
        shoppingListExceptionHandler.checkIfShoppingListExists(sLId);

        System.out.println("List size: " + this.shoppingListDatabase.getShoppingListHashMap().size());

        shoppingListDatabase.getShoppingListHashMap().remove(sLId);

        System.out.println("Credit card deleted. \nUpdated list size: " +
                this.shoppingListDatabase.getShoppingListHashMap().size());
    }
}
