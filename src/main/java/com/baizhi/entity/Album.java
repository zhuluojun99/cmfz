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
public class Album implements Serializable {

  private String id;
  private String title;
  private String img;
  private String score;
  private String author;
  private String broadcaster;
  private Integer count;
  private String brief;
  private @DateTimeFormat(pattern = "yyyy-MM-dd") Date create_date;
  private String status;
}
