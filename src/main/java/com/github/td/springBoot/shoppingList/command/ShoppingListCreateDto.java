package com.github.td.springBoot.shoppingList.command;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class ShoppingListCreateDto {

    @NotNull(message = "Shop name is mandatory")
    @ApiModelProperty(notes = "Shop name")
    @Column(name = "shopName", length = 255)
    private String shopName;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Override
    public String toString() {
        return "ShoppingListCreateDto{" +
                "shopName='" + shopName + '\'' +
                '}';
    }
}