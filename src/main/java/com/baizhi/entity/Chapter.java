package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {
    private String id;
    private String title;
    private String album_id;
    private String size;
    private String duration;
    private String src;
    private String status;
    private @DateTimeFormat(pattern = "yyyy-MM-dd") Date create_date;
}