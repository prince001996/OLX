package com.olx.categories.Entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
public class Item {

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String brand;
    private String description;
    private Integer price;
    private String subCategory;
    private Long userId;
    private Date date;
    private Time time;
    private Long pincode;
    private boolean status;

    @ManyToOne
    private Category category;
}
