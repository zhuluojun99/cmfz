package com.baizhi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
  private String id;
  private String title;
  private String author;
  private String content;
  private String guru_id;
  private Date create_date;
  private String status;
}
