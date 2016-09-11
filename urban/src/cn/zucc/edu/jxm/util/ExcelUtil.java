package cn.zucc.edu.jxm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelUtil
{

    // public static void fillExcelData(ResultSet rs,Workbook wb,String[] headers)throws Exception{
    // int rowIndex=0;
    // Sheet sheet=wb.createSheet();
    // Row row=sheet.createRow(rowIndex++);
    // for(int i=0;i<headers.length;i++){
    // row.createCell(i).setCellValue(headers[i]);
    // }
    // while(rs.next()){
    // row=sheet.createRow(rowIndex++);
    // for(int i=0;i<headers.length;i++){
    // row.createCell(i).setCellValue(rs.getObject(i+1).toString());
    // }
    // }
    // }
    //
    // public static Workbook fillExcelDataWithTemplate(ResultSet rs,String templateFileName)throws Exception{
    // InputStream inp=ExcelUtil.class.getResourceAsStream("/com/java1234/template/"+templateFileName);
    // POIFSFileSystem fs=new POIFSFileSystem(inp);
    // Workbook wb=new HSSFWorkbook(fs);
    // Sheet sheet=wb.getSheetAt(0);
    // // 获取列数
    // int cellNums=sheet.getRow(0).getLastCellNum();
    // int rowIndex=1;
    // while(rs.next()){
    // Row row=sheet.createRow(rowIndex++);
    // for(int i=0;i<cellNums;i++){
    // row.createCell(i).setCellValue(rs.getObject(i+1).toString());
    // }
    // }
    // return wb;
    // }

    public static String formatCell(Cell cell)
    {
        if (cell == null)
        {
            return "";
        }
        else
        {
            if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN)
            {
                return String.valueOf(cell.getBooleanCellValue());
            }
            else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
            {
                return String.valueOf(cell.getNumericCellValue());
            }
            else
            {
                return String.valueOf(cell.getStringCellValue());
            }
        }
    }

    public static Integer formatCellInt(Cell cell)
    {

        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        {
            return Integer.valueOf((int) cell.getNumericCellValue());

        }
        return null;
    }

    public static Double formatCellDouble(Cell cell)
    {

        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
        {
            return cell.getNumericCellValue();

        }
        return null;
    }

    public static Date formatCellDate(Cell cell)
    {
           Date date=null;
          if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC){
            if (HSSFDateUtil.isCellDateFormatted(cell))
            {   
                // 处理日期格式、时间格式
                SimpleDateFormat sdf = null;
                if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm"))
                {
                    sdf = new SimpleDateFormat("HH:mm");
                }
                else if(cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm:ss"))
                {
                    sdf = new SimpleDateFormat("HH:mm:ss");
                }
                else
                {
                    sdf = new SimpleDateFormat("yyyy-MM-dd");
                }
                date = cell.getDateCellValue();
            }
            else if (cell.getCellStyle().getDataFormat() == 58)
            {
                // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                double value = cell.getNumericCellValue();
                 date = org.apache.poi.ss.usermodel.DateUtil
                        .getJavaDate(value);
            }
    }
        return date;
    }
}
