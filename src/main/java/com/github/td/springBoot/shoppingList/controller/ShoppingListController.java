package com.github.td.springBoot.shoppingList.controller;

import com.github.td.springBoot.shoppingList.command.ShoppingListCreateDto;
import com.github.td.springBoot.shoppingList.command.ShoppingListGetDto;
import com.github.td.springBoot.shoppingList.exceptions.ShoppingListException;
import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;
import com.github.td.springBoot.shoppingList.services.ShoppingListService;
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
import org.springframework.web.bind.annotation.*;

/**
 * REST controller responsible for {@link ShoppingList} related CRUD operations
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping({"/api/springboot/store"})
@Api("Store API")
public class ShoppingListController {

    private ShoppingListService shoppingListService;

    @Autowired
    public void setShoppingListServiceImpl(ShoppingListService shoppingListService) {

        this.shoppingListService = shoppingListService;
    }

    @ApiOperation(value = "Create Shopping List", response = ShoppingListGetDto.class)
    @PostMapping(produces = {"application/json"}, consumes = {"application/json"}, path = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponses({@ApiResponse(code = 201, message = "Successful shopping list creation"), @ApiResponse(code = 400, message = "Invalid attribute on the payload"), @ApiResponse(code = 422, message = "The shopping list number is already on the database")})
    public ResponseEntity<ShoppingList> createShoppingList(@Valid @RequestBody ShoppingListCreateDto shoppingListCreateDto) {

        return new ResponseEntity(this.shoppingListService.createShoppingList(shoppingListCreateDto), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Shopping List list information", response = ShoppingListGetDto.class)
    @GetMapping(produces = {"application/json"}, path = {"/", "", "/list"})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful credit card list retrieval"), @ApiResponse(code = 404, message = "Shopping list not found")})
    public ResponseEntity<List<ShoppingListGetDto>> getShoppingListList() {

        return new ResponseEntity(this.shoppingListService.getShoppingListList(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get Shopping List information", response = ShoppingListGetDto.class)
    @GetMapping(produces = {"application/json"}, path = {"/{shoppingListId}"})
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses({@ApiResponse(code = 200, message = "Successful shopping list retrieval"), @ApiResponse(code = 404, message = "Shopping list not found")})
    public ResponseEntity<ShoppingListGetDto> getShoppingList(@PathVariable @NonNull Long shoppingListId) {

        if (shoppingListId == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            return new ResponseEntity(this.shoppingListService.getShoppingList(shoppingListId.longValue()), HttpStatus.OK);

        } catch (ShoppingListException e) {
            System.out.println("Shopping list not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation("Delete Shopping List")
    @DeleteMapping(path = {"/{shoppingListId}"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses({@ApiResponse(code = 204, message = "Successful Shopping List deletion"), @ApiResponse(code = 404, message = "Shopping list not found")})
    public ResponseEntity<ShoppingListGetDto> deleteShoppingList(@PathVariable @NonNull Long shoppingListId) {

        if (shoppingListId == null)
            return new ResponseEntity(HttpStatus.NO_CONTENT);

        try {
            this.shoppingListService.deleteShoppingList(shoppingListId.longValue());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (ShoppingListException e) {
            System.out.println("Shopping List not found");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
