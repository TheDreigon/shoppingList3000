package com.github.td.springBoot.shoppingList.command;

import io.swagger.annotations.ApiModelProperty;
import java.util.UUID;
import javax.validation.constraints.NotNull;

public class ItemGetDto {

    private long id;

    private UUID serialNumber;

    @NotNull(message = "Item name is mandatory")
    @ApiModelProperty(notes = "Item name")
    private String itemName;

    @NotNull(message = "Item price is mandatory")
    @ApiModelProperty(notes = "Item price")
    private int itemPrice;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

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

    public String toString() {
        return "ItemGetDto{id=" + this.id +
                ", serialNumber=" + this.serialNumber +
                ", itemName='" + this.itemName +
                '\'' + ", itemPrice=" + this.itemPrice +
                '}';
    }
}
