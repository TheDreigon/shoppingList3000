package com.github.td.springBoot.shoppingList.persistence.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name = "shoppinglist")
@ApiModel(value = "Shopping List entity properties")
public class ShoppingList {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @ApiModelProperty(notes = "id")
    @Column(name = "id")
    private long id;

    @NotNull
    @ApiModelProperty(notes = "Shop name")
    @Column(name = "shopName", length = 255)
    private String shopName;

    @NotNull
    @ApiModelProperty(notes = "First name")
    @Column(name = "lastUpdate")
    private String lastUpdate;

    @OneToMany(
            // propagate changes on shoppingList entity to item entities
            cascade = {CascadeType.ALL},

            // make sure to remove items if unlinked from shoppingLst
            orphanRemoval = true,

            // use item foreign key on item table to establish
            // the many-to-one relationship instead of a join table
            mappedBy = "id"
    )
    private List<Item> itemList = new ArrayList<>();

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

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public Item getItem(long iId) {
        return this.itemList.get((int) iId);
    }

    public void addItem(Item item) {
        this.itemList.add(item);
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "id=" + id +
                ", shopName='" + shopName + '\'' +
                ", lastUpdate='" + lastUpdate + '\'' +
                ", itemList=" + itemList +
                '}';
    }
}
