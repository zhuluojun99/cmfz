package com.baizhi.entity;


import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Banner implements Serializable {
  @Excel(name = "id")
  private String id;
  @Excel(name = "标题")
  private String title;
  @Excel(name = "轮播图",type = 2,height = 20,width = 50)
  private String img;
  @Excel(name = "上传时间",format = "yyyy-MM-dd hh:mm:ss")
  private Date create_date;
  @Excel(name = "状态")
  private String status;
}
