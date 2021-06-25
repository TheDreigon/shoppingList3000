package com.github.td.springBoot.shoppingList.controller;

import com.github.td.springBoot.shoppingList.command.ItemCreateDto;
import com.github.td.springBoot.shoppingList.command.ItemGetDto;
import com.github.td.springBoot.shoppingList.command.ShoppingListGetDto;
import com.github.td.springBoot.shoppingList.exceptions.ItemException;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.model.Item;
import com.github.td.springBoot.shoppingList.services.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.validation.Valid;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller responsible for {@link Item} related CRUD operations
 */
@CrossOrigin(origins = {"*"}, maxAge = 3600)
@RestController
@RequestMapping({"/api/springboot/store/shoppingList/"})
@Api("Store API - Item")
public class ItemController {
    private ItemService itemService;

    @Autowired
    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "Create an item", response = ShoppingListGetDto.class)
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"}, path = {"/{sLID}/"})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({@ApiResponse(code = 201, message = "Successful item creation"), @ApiResponse(code = 400, message = "Invalid attribute on the payload")})
    public ResponseEntity<Item> createItem(@PathVariable long sLID, @Valid @RequestBody ItemCreateDto itemCreateDto) {

        try {
            return new ResponseEntity(this.itemService.createItem(sLID, itemCreateDto), HttpStatus.OK);

        } catch (ShoppingListException e) {
            System.out.println("Shopping list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ItemException e) {
            System.out.println("Item not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get an item list information", response = ShoppingListGetDto.class)
    @GetMapping(produces = {"application/json"}, path = {"/{sLID}/list"})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful item list retrieval"), @ApiResponse(code = 404, message = "Shopping list not found")})
    public ResponseEntity<List<ItemGetDto>> getShoppingListList(long sLID) {

        try {
            return new ResponseEntity(this.itemService.getItemList(sLID), HttpStatus.OK);
        } catch (ShoppingListException e) {
            System.out.println("Shopping list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Get an item information", response = ShoppingListGetDto.class)
    @GetMapping(produces = {"application/json"}, path = {"/{sLID}/{iId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful shopping list retrieval"), @ApiResponse(code = 404, message = "Shopping list not found")})
    public ResponseEntity<ItemGetDto> getShoppingList(@PathVariable @NonNull Long sLID, @PathVariable @NonNull Long iID) {

        if (sLID == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        if (iID == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            return new ResponseEntity(this.itemService.getItem(sLID.longValue(), iID.longValue()), HttpStatus.OK);
        } catch (ShoppingListException e) {
            System.out.println("Shopping list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ItemException e) {
            System.out.println("Item not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete item")
    @DeleteMapping(path = {"/{sLID}/{iId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({@ApiResponse(code = 204, message = "Successful item deletion"), @ApiResponse(code = 404, message = "Item not found")})
    public ResponseEntity<ShoppingListGetDto> deleteShoppingList(@PathVariable @NonNull Long sLID, @PathVariable @NonNull Long iID) {

        if (sLID == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        if (iID == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            this.itemService.deleteItem(sLID.longValue(), iID.longValue());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ShoppingListException e) {
            System.out.println("Shopping list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ItemException e) {
            System.out.println("Item not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
