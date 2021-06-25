package com.github.td.springBoot.shoppingList.command;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

public class ItemCreateDto {

    @NotNull
    @ApiModelProperty(notes = "Item name")
    private String itemName;

    @NotNull
    @ApiModelProperty(notes = "Item price")
    private int itemPrice;

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return this.itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    @Override
    public String toString() {
        return "ItemCreateDto{" +
                "itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                '}';
    }
}
