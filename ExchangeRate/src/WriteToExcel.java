import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.CellFormat;
import jxl.write.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by GL on 2016/12/23.
 */
public class WriteToExcel {
    public static void writeToExcel(ArrayList<ArrayList<String>> all_data) {
        String[] title = {"日期","美元 ","欧元 ","日元 ","港元 ","英镑 ","林吉特 ","卢布 ","兰特 ","韩元 ","迪拉姆 ","里亚尔 ","福林 ","兹罗提 ","丹麦克朗 ","瑞典克朗 "
        ,"挪威克朗 ","里拉 ","比索 ","澳元 ","加元 ","新西兰元 ","新加坡元 ","瑞士法郎"};
        try {
            // 获得开始时间
            long start = System.currentTimeMillis();
            // 输出的excel的路径
            String filePath = ".\\汇率中间价.xls";
            // 创建Excel工作薄
            WritableWorkbook wwb;
            // 新建立一个jxl文件,即在d盘下生成testJXL.xls
            OutputStream os = new FileOutputStream(filePath);
            wwb = Workbook.createWorkbook(os);
            // 添加第一个工作表并设置第一个Sheet的名字
            WritableSheet sheet = wwb.createSheet("汇率中间价", 0);
            Label label;
            for (int i = 0; i < title.length; i++) {
                // Label(x,y,z) 代表单元格的第x+1列，第y+1行, 内容z
                // 在Label对象的子对象中指明单元格的位置和内容
                label = new Label(i, 0, title[i]);
//                label = new Label(i, 0, title[i], getHeader());
                // 将定义好的单元格添加到工作表中
                sheet.addCell(label);
            }
            // 下面是填充数据
              /*
               * 保存数字到单元格，需要使用jxl.write.Number
               * 必须使用其完整路径，否则会出现错误
              * */
            // 填充产品编号
            for(int i = 1;i < all_data.size(); i ++){
                for(int j = 0; j < 24; j++){
                    label = new Label(j, i, all_data.get(i).get(j));
                    sheet.addCell(label);
                }
            }
             /*
               *
               * 定义公共字体格式
               * 通过获取一个字体的样式来作为模板
              * 首先通过web.getSheet(0)获得第一个sheet
             * */
            CellFormat cf = wwb.getSheet(0).getCell(1, 0).getCellFormat();
            WritableCellFormat wc = new WritableCellFormat();
            // 设置居中
            wc.setAlignment(Alignment.CENTRE);
            // 设置边框线
            wc.setBorder(Border.ALL, BorderLineStyle.THIN);
            // 设置单元格的背景颜色
            wc.setBackground(jxl.format.Colour.RED);


            // 写入数据
            wwb.write();
            // 关闭文件
            wwb.close();
            long end = System.currentTimeMillis();
            System.out.println("----完成该操作共用的时间是:" + (end - start) / 1000);
        } catch (Exception e) {
            System.out.println("---出现异常---");
            e.printStackTrace();
        }
    }
}
