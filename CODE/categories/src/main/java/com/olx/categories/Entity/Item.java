package com.olx.categories.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String brand;
    private String description;
    private Integer price;
    private String subCategory;
    private Long userId;
    private Date date;
    private Time time;
    private Integer pincode;
    private boolean status;

    @ManyToOne
    private Category category;
}
