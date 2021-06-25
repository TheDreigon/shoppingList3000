package com.github.td.springBoot.shoppingList.services;

import com.github.td.springBoot.shoppingList.aop.ItemExceptionHandler;
import com.github.td.springBoot.shoppingList.aop.ShoppingListExceptionHandler;
import com.github.td.springBoot.shoppingList.command.ItemCreateDto;
import com.github.td.springBoot.shoppingList.command.ItemGetDto;
import com.github.td.springBoot.shoppingList.converter.ItemCreateDtoToNewItem;
import com.github.td.springBoot.shoppingList.converter.ItemToItemGetDtoConverter;
import com.github.td.springBoot.shoppingList.exceptions.ItemException;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.model.Item;
import com.github.td.springBoot.shoppingList.persistence.dao.jpa.ItemDatabase;
import com.github.td.springBoot.shoppingList.persistence.dao.jpa.ShoppingListDatabase;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

    private long id = 0L;
    private ShoppingListDatabase shoppingListDatabase;
    private ItemDatabase itemDatabase;
    private ItemToItemGetDtoConverter itemToItemGetDtoConverter;
    private ItemCreateDtoToNewItem itemCreateDtoToNewItem;
    private ShoppingListExceptionHandler shoppingListExceptionHandler;
    private ItemExceptionHandler itemExceptionHandler;
    private final List<ItemGetDto> itemGetDtoList = new ArrayList<>();

    @Autowired
    public void setShoppingListDatabase(ShoppingListDatabase shoppingListDatabase) {
        this.shoppingListDatabase = shoppingListDatabase;
    }

    @Autowired
    public void setItemDatabase(ItemDatabase itemDatabase) {
        this.itemDatabase = itemDatabase;
    }

    @Autowired
    public void setItemToItemGetDtoConverter(ItemToItemGetDtoConverter itemToItemGetDtoConverter) {
        this.itemToItemGetDtoConverter = itemToItemGetDtoConverter;
    }

    @Autowired
    public void setItemCreateDtoToNewItem(ItemCreateDtoToNewItem itemCreateDtoToNewItem) {
        this.itemCreateDtoToNewItem = itemCreateDtoToNewItem;
    }

    @Autowired
    public void setShoppingListExceptionHandler(ShoppingListExceptionHandler shoppingListExceptionHandler) {
        this.shoppingListExceptionHandler = shoppingListExceptionHandler;
    }

    @Autowired
    public void setItemExceptionHandler(ItemExceptionHandler itemExceptionHandler) {
        this.itemExceptionHandler = itemExceptionHandler;
    }

    /**
     * POST {@inheritDoc}
     */
    public Item createItem(long sLID, ItemCreateDto itemCreateDto) throws ShoppingListException, ItemException {

        System.out.println("ITEM POST METHOD CALLED");

        this.shoppingListExceptionHandler.checkIfShoppingListExists(Long.valueOf(sLID));
        this.itemExceptionHandler.checkItemPrice(itemCreateDto.getItemPrice());

        Item newItem = this.itemCreateDtoToNewItem.convert(itemCreateDto);
        newItem.setId(Long.valueOf(this.id++));
        this.itemDatabase.getItemHashMap().put(newItem.getId(), newItem);
        this.shoppingListDatabase.getShoppingListHashMap().get(Long.valueOf(sLID)).addItem(newItem);

        System.out.println(this.itemDatabase.getItemHashMap().get(newItem.getId()).toString());
        System.out.println();

        return newItem;
    }

    /**
     * GET ALL {@inheritDoc}
     */
    public List<ItemGetDto> getItemList(long sLID) throws ShoppingListException {

        System.out.println("ITEM GET ALL METHOD CALLED");

        this.shoppingListExceptionHandler.checkIfShoppingListExists(Long.valueOf(sLID));

        for (Item item : this.itemDatabase.getItemHashMap().values()) {
            System.out.println(this.itemToItemGetDtoConverter.convert(item).toString());
            this.itemGetDtoList.add(this.itemToItemGetDtoConverter.convert(item));
        }

        System.out.println("List size: " + this.shoppingListDatabase.
                getShoppingListHashMap().get(Long.valueOf(sLID)).getItemList().size());

        return this.itemGetDtoList;
    }

    /**
     * GET {@inheritDoc}
     */
    public ItemGetDto getItem(long sLID, long iID) throws ShoppingListException, ItemException {

        System.out.println("ITEM GET METHOD CALLED");

        this.shoppingListExceptionHandler.checkIfShoppingListExists(Long.valueOf(sLID));
        this.itemExceptionHandler.checkIfItemExists(Long.valueOf(iID));
        Item item = this.itemDatabase.getItemHashMap().get(Long.valueOf(iID));

        System.out.println(this.itemToItemGetDtoConverter.convert(item).toString());

        return this.itemToItemGetDtoConverter.convert(item);
    }

    /**
     * DELETE {@inheritDoc}
     */
    public void deleteItem(long sLID, long iID) throws ShoppingListException, ItemException {

        System.out.println("ITEM DELETE METHOD CALLED");

        this.shoppingListExceptionHandler.checkIfShoppingListExists(Long.valueOf(sLID));
        this.itemExceptionHandler.checkIfItemExists(Long.valueOf(iID));

        System.out.println("Corresponding shopping list size: " + this.shoppingListDatabase
                .getShoppingListHashMap().get(Long.valueOf(sLID)).getItemList().size());

        this.itemDatabase.getItemHashMap().remove(Long.valueOf(iID));

        System.out.println("Shopping list item deleted. \nUpdated list size: " +
                this.shoppingListDatabase
                .getShoppingListHashMap().get(Long.valueOf(sLID)).getItemList().size());
    }
}
