package com.olx.categories.Controller;

import com.olx.categories.DTO.ItemDTO;
import com.olx.categories.Entity.Item;
import com.olx.categories.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RequestMapping("/api/v1/categories")
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private RestTemplate restTemplate;

    //testing purpose
    @GetMapping("/hello")
    public ResponseEntity<String> replyHello(){

        ResponseEntity<String> responseEntity = new ResponseEntity<>("Hello", HttpStatus.OK);
        return responseEntity;
    }

    /**
     * Description : Get all items
     * @return : Returns a List of Item objects
     */
    @GetMapping("/items")
    public ResponseEntity<List> getAllItems(){
        return itemService.getAllItems();
    }

    /**
     * Description : Get an item by itemId
     * @param itemId : Primary Key of the Entity Item
     * @return : Returns an Object of Item
     */
    @GetMapping("/item/{itemId}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable("itemId") Long itemId){
        return itemService.getItemById(itemId);
    }

    /**
     * Description : get all items posted by an user
     * @param userId :
     * @return :
     */
    @GetMapping("/items/seller/{userId}")
    public ResponseEntity<List> getAllItemsBySellerId(@PathVariable("userId") Long userId){
        return itemService.getAllItemsBySellerId(userId);
    }

//    /**
//     * Description : get all items in which an user is interested
//     * @param userId :
//     * @return :
//     */
//    @GetMapping("/items/buyer/{userId}")
//    public ResponseEntity<List> getAllItemsByBuyerId(@PathVariable("userId") Long userId){
//        return itemService.getAllItemsByBuyerId(userId);
//    }

    /**
     * Description : update an item details, can only be update by the user who added the item
     */
    @PutMapping("item/{itemId}/{userId}")
    public String updateItem(@PathVariable("itemId") Long itemId, @PathVariable("userId") Long userId, @RequestBody Item item){
        return itemService.updateItem(itemId, userId, item);
    }

    /**
     * Description : soft delete the item or disable the item, can only be done by the user who added teh item
     * @param itemId :
     * @param userId :
     * @return :
     */
    @PutMapping("item/remove/{itemId}/{userId}")
    public ResponseEntity<String> deleteItem(@PathVariable("itemId") Long itemId, @PathVariable("userId") Long userId){
        return itemService.deleteItem(itemId, userId);
    }

    /**
     * Description : Search all items by title
     * @param title : title or part of title to be used to search the items
     * @return : Returns a List of Item objects
     */
    @GetMapping("/search/{title}")
    public ResponseEntity<List> searchAllItems(@PathVariable("title") String title){
        return itemService.searchAllItems(title);
    }

//    /**
//     * Description : Get all items in the country
//     * @param name : name of the country to be used to search the items
//     * @return : List of Item in the given country
//     */
//    @GetMapping("/items/country/{name}")
//    public ResponseEntity<List> searchAllItemsByCountry(@PathVariable("name") String name){
//        return itemService.searchAllItemsByCountry(name);
//    }
//
//    /**
//     * Description : Get all items in the state
//     * @param name : name of the state to be used to search the items
//     * @return : List of Item in the given state
//     */
//    @GetMapping("/items/state/{name}")
//    public ResponseEntity<List> searchAllItemsByState(@PathVariable("name") String name){
//        return itemService.searchAllItemsByState(name);
//    }
//
//    /**
//     * Description : Get all items in the city
//     * @param name : name of the city to be used to search the items
//     * @return : List of Item in the given city
//     */
//    @GetMapping("/items/city/{name}")
//    public ResponseEntity<List> searchAllItemsByCity(@PathVariable("name") String name){
//        return itemService.searchAllItemsByCity(name);
//    }

    /**
     * Description : Get all items in the neighborhood using pincode
     * @param pincode : pincode for the locality to be used to search the items
     * @return : List of Item at the given pincode
     */
    @GetMapping("/items/neighborhood/{pincode}")
    public ResponseEntity<List> searchAllItemsByNeighborhood(@PathVariable("pincode") Long pincode){
        return itemService.searchAllItemsByNeighborhood(pincode);
    }

}
