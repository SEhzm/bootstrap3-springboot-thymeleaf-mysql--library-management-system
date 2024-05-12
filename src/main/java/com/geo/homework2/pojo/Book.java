package com.geo.homework2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    private String ID;
    private String name;
    private String author;  //作者
    private Double price;
    private Date publishtime; //出版时间

}
