package com.github.td.springBoot.shoppingList.command;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;

public class ShoppingListGetDto {

    private long id;

    @NotNull(message = "Shop name is mandatory")
    @ApiModelProperty(notes = "Shop name")
    @Column(name = "shopName", length = 255)
    private String shopName;

    @NotNull
    @ApiModelProperty(notes = "Last update")
    @Column(name = "lastUpdate")
    private String lastUpdate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public String toString() {
        return "ShoppingListGetDto{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                '}';
    }
}
