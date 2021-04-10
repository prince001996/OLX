package com.olx.categories.Controller;

import com.olx.categories.Entity.Item;
import com.olx.categories.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/categories")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;


//    @PostMapping

    //Get all items

    /**
     * Description :
     * @return
     */
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return itemService.getAllItems();
    }

    /**
     * Description : Get an item by itemId
     * @param itemId : Primary Key of the Entity Item
     * @return : Returns an Object of Item
     */
    @GetMapping("/item/{itemId}")
    public Item getItemById(@PathVariable("itemId") Long itemId){
        return itemService.getItemById(itemId);
    }

    //get all items posted by an user
    @GetMapping("/items/seller/{userId}")
    public List<Item>getAllItemsBySellerId(@PathVariable("userId") Long userId){
        return itemService.getAllItemsBySellerId(userId);
    }

    //get all items in which an user is interested
    @GetMapping("/items/buyer/{userId}")
//    public Item getAllItemsByBuyerId(@PathVariable("userId") Long userId){
//        return itemService.getAllItemsById(itemId);
//    }

    //update an item details, can only be update by the user who added the item
    @PutMapping("item/{itemId}/{userId}")
    public String updateItem(@PathVariable("itemId") Long itemId, @PathVariable("userId") Long userId, @RequestBody Item item){
        return itemService.updateItem(itemId, userId, item);
    }

    //soft delete the item or disable the item, can only be done by the user who added teh item
    @PutMapping("item/remove/{itemId}/{userId}")
    public String deleteItem(@PathVariable("itemId") Long itemId, @PathVariable("userId") Long userId){
        return itemService.deleteItem(itemId, userId);
    }


    //Get an item by title
    @GetMapping("/item/serach/{title}")
    public List<Item> searchAllItems(@PathVariable("title") String title){
        return itemService.searchAllItems(title);
    }



}
