package com.olx.categories.Repository;

import com.olx.categories.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findAllByUserId(Long userId);

    List<Item> findAllByTitleContaining(String title);

    Optional<Item> findItemById

    List<Item> findAllByPincode(Long pincode);

}
