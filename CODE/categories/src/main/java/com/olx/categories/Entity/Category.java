package com.olx.categories.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name="category_id", referencedColumnName="id")
    private List<Item> items;
    public void addItem(Item item){
        this.items.add(item);
    }
    public void removeItem(Item item){
        this.items.remove(item);
    }

}
