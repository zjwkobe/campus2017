import jxl.Workbook;
import jxl.write.*;

import java.io.File;

/**
 * Created by mumu462 on 2017/1/13.
 */
public class ExcelWrite {
    private String path;
    private WritableSheet sheet;
    private WritableWorkbook workbook;
    ExcelWrite(String path)
    {
        this.path=path;
    }
    public void createExcel()
    {
        try
        {
            workbook= Workbook.createWorkbook(new File(path));
            sheet = workbook.createSheet("第一页", 0);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
    public void save() throws Exception
    {
        workbook.write();
        workbook.close();
    }
    public void createRowCol(int row,int col)
    {
        sheet.insertRow(row);
        sheet.insertRow(col);
    }
    public void fillLabel(int row,int col ,Object value)
    {
        Label label=new Label(col,row,(String)value);
        try
        {
            WritableCellFormat cellFormat=new WritableCellFormat();
            cellFormat.setAlignment(jxl.format.Alignment.LEFT);
            cellFormat.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            cellFormat.setWrap(true);
            cellFormat.setShrinkToFit(true);
            label.setCellFormat(cellFormat);
            this.sheet.addCell(label);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
