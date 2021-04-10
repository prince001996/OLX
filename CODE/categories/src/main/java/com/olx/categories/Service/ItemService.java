package com.olx.categories.Service;

import com.olx.categories.Entity.Item;
import com.olx.categories.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;


    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    public List<Item> getAllItemsBySellerId(Long userId) {
        //check if user exists or not
//        boolean user = api call
        return itemRepository.findAllByUserId(userId);
    }

    public String updateItem(Long itemId, Long userId, Item item) {
        // check if user exists or not
//        boolean user = api call
        Optional<Item> item1 = itemRepository.findById(itemId);
        if(item1.isPresent()){
            if(item1.get().isStatus()){
                itemRepository.save(item);
                return "item updated succesfully";
            }
            return "item has been deleted or marked sold";
        }
        return "item doesn't exist";
    }

    public String deleteItem(Long itemId, Long userId) {
        // check if user exists or not
//        boolean user = api call
        Optional<Item> item1 = itemRepository.findById(itemId);
        if(item1.isPresent()){
            item1.get().setStatus(false);
            return "Item deleted";
        }
        return "item doesn't exist";
    }

    public List<Item> searchAllItems(String title) {
        return itemRepository.findAllByTitle(title);
    }
}
