package com.olx.categories.DTO;

import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Data
public class ItemDTO {

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
    private Long categoryID;

}
