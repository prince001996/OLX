package com.olx.categories.Service;

import com.olx.categories.DTO.ItemDTO;
import com.olx.categories.Entity.Item;
import com.olx.categories.Repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private RestTemplate restTemplate;

    public ResponseEntity<List> getAllItems() {
        ResponseEntity<List> responseEntity = new ResponseEntity<>(itemRepository.findAll(), HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<ItemDTO> getItemById(Long itemId) {
        Item item = itemRepository.findById(itemId);
        if()
        ResponseEntity<ItemDTO> responseEntity = new ResponseEntity<>(, HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<List> getAllItemsBySellerId(Long userId) {
        //check if user exists or not
        ResponseEntity<String> user = restTemplate.getForEntity("http://localhost:2020/api/v1/exist/"+userId, String.class);
        if(user.equals("Yes"))
        {
            ResponseEntity<List> responseEntity = new ResponseEntity<>(itemRepository.findAllByUserId(userId), HttpStatus.OK);
            return responseEntity;
        }
        ResponseEntity<List> responseEntity2 = new ResponseEntity<>(itemRepository.findAllByUserId(userId), HttpStatus.NOT_FOUND);
        return responseEntity2;
    }

//    public ResponseEntity<List> getAllItemsByBuyerId(Long userId) {
//        //check if user exists or not
//        ResponseEntity<Boolean> user = restTemplate.getForEntity("http://localhost:2020/api/v1/user/"+userId, Boolean.class);
//        if(user)
//        ResponseEntity<List> items = restTemplate.getForEntity("http://localhost:3030/api/v1/offers/"+userId, List.class);
//        return items;
//    }

    public String updateItem(Long itemId, Long userId, Item item) {
        //check if user exists or not
        ResponseEntity<String> user = restTemplate.getForEntity("http://localhost:2020/api/v1/exist/"+userId, String.class);
        if(user.equals("Yes"))
        {
            Optional<Item> item1 = itemRepository.findById(itemId);
            if (item1.isPresent()) {
                if (item1.get().isStatus()) {
                    itemRepository.save(item);
                    return "item updated succesfully";
                }
                return "item has been deleted or marked sold";
            }
            return "item doesn't exist";
        }
        return "User doesn't exist";
    }

    public ResponseEntity<String> deleteItem(Long itemId, Long userId) {
        //check if user exists or not
        ResponseEntity<String> user = restTemplate.getForEntity("http://localhost:2020/api/v1/exist/"+userId, String.class);
        if(user.equals("Yes"))
        {
            Optional<Item> item1 = itemRepository.findById(itemId);
            if (item1.isPresent()) {
                item1.get().setStatus(false);
                ResponseEntity<String> responseEntity1 = new ResponseEntity<>("Item Deleted", HttpStatus.OK);
                return responseEntity1;
            }
            ResponseEntity<String> responseEntity2 = new ResponseEntity<>("item doesn't exist", HttpStatus.NOT_FOUND);
            return responseEntity2;
        }
        ResponseEntity<String> responseEntity3 = new ResponseEntity<>("User doesn't exist", HttpStatus.NOT_FOUND);
        return responseEntity3;
    }

    public ResponseEntity<List> searchAllItems(String title) {
        ResponseEntity<List> responseEntity = new ResponseEntity<>(itemRepository.findAllByTitleContaining(title), HttpStatus.OK);
        return responseEntity;
    }

    public ResponseEntity<List> searchAllItemsByNeighborhood(Long pincode) {
        ResponseEntity<List> responseEntity = new ResponseEntity<>(itemRepository.findAllByPincode(pincode), HttpStatus.OK);
        return responseEntity;
    }
}
