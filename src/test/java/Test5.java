import com.baizhi.App;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Test5 {
    @Autowired
    private BannerDao bannerDao;
    @Test
    public void test() throws IOException {
        //创建一个excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //指定日期格式
        HSSFDataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd hh:mm:ss");
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(format);
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short)10);
        font.setFontName("华文新魏");
        font.setBold(true);
        font.setColor(Font.COLOR_NORMAL);
        HSSFCellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setFont(font);
        cellStyle1.setAlignment(HorizontalAlignment.RIGHT);
        //创建工作溥
        HSSFSheet sheet = workbook.createSheet();
        sheet.setColumnWidth(3,20*256);
        HSSFRow row = sheet.createRow(0);
        String[] titles = {"id","标题","图片路径","创建时间","当前状态"};
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(title);
            cell.setCellStyle(cellStyle1);
        }
        List<Banner> list = bannerDao.queryAll();
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(list.get(i).getId());
            row1.createCell(1).setCellValue(list.get(i).getTitle());
            row1.createCell(2).setCellValue(list.get(i).getImg());
            HSSFCell cell = row1.createCell(3);
            cell.setCellValue(list.get(i).getCreate_date());
            cell.setCellStyle(cellStyle);
            row1.createCell(4).setCellValue(list.get(i).getStatus());
        }
        workbook.write(new File("D:/banner.xls"));
        workbook.close();
    }
}
