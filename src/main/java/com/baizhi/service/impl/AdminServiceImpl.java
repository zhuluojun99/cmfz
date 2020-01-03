package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin querybyname(String username) {
        return adminDao.selectbyname(username);
    }

    @Override
    public void adminOut(HttpServletResponse response) {
        List<Admin> list = adminDao.queryAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("微软雅黑");
        font.setBold(true);
        font.setColor(Font.COLOR_NORMAL);
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.LEFT);
        //创建工作蒲
        HSSFSheet sheet = workbook.createSheet("管理员信息");
        HSSFRow row = sheet.createRow(0);
        String[] titles = {"编号","用户名","密码"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle);
        }
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getUsername());
            String password = DigestUtils.md5Hex(list.get(i).getPassword());
            row1.createCell(2).setCellValue(password);
        }
        response.setHeader("content-disposition","attachment;filename=admin.xls");
        ServletOutputStream outputStream=null;
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(workbook!=null){
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
