package com.github.td.springBoot.shoppingList.persistence.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.td.springBoot.shoppingList.persistence.model.ShoppingList;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

}