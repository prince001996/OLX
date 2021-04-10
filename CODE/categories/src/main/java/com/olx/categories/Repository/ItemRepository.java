package com.olx.categories.Repository;

import com.olx.categories.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("FROM Item WHERE userId = ?1")
    List<Item> findAllByUserId(Long userId);

    @Query("FROM Item WHERE title = ?1")
    List<Item> findAllByTitle(String title);

}
