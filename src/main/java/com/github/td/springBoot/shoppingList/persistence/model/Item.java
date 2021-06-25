package com.github.td.springBoot.shoppingList.persistence.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@Entity
@ApiModel(value = "Item entity properties")
@Table(name = "item")
public class Item {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @ApiModelProperty(notes = "id")
    private long id;

    @NotNull
    @ApiModelProperty(notes = "serialNumber")
    @Column(name = "serialNumber", length = 16)
    private UUID serialNumber;

    @NotNull
    @ApiModelProperty(notes = "itemName")
    @Column(name = "itemName", length = 255)
    private String itemName;

    @NotNull
    @ApiModelProperty(notes = "itemPrice")
    @Column(name = "itemPrice")
    private int itemPrice;

    @ManyToOne
    private ShoppingList shoppingList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UUID getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(UUID serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public ShoppingList getShoppingList() {
        return shoppingList;
    }

    public void setShoppingList(ShoppingList shoppingList) {
        this.shoppingList = shoppingList;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", serialNumber=" + serialNumber +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", shoppingList=" + shoppingList +
                '}';
    }
}
