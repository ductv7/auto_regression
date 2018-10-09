package com.selenium.common;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 


import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class ExcelAPI
{
    static FileInputStream fis = null;
    static XSSFWorkbook workbook = null;
    static XSSFSheet sheet = null;
    static XSSFRow row = null;
    static XSSFCell cell = null;
    
    //static String sheetName="Data";
 
    private static void loadFile() throws Exception
    {
        fis = new FileInputStream(Global.CurrDataFileName);
        workbook = new XSSFWorkbook(fis);
        sheet = workbook.getSheet(Global.SheetName);
        fis.close();
    }
    
    public void loadFiles() throws Exception{
    	
    	loadFile();
    }
 
    public String getCellDataByColName(String colName, int rowNum) throws Exception
    {
    	loadFile();
    	
        try
        {
            int col_Num = -1;
            row = sheet.getRow(0);
            for(int i = 0; i < row.getLastCellNum(); i++)
            {
                if(row.getCell(i).getStringCellValue().trim().equals(colName.trim()))
                    col_Num = i;
            }
 
            row = sheet.getRow(rowNum - 1);
            cell = row.getCell(col_Num);
 
            if(cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue();
            else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC || cell.getCellType() == Cell.CELL_TYPE_FORMULA)
            {
                String cellValue = String.valueOf(cell.getNumericCellValue());
                if(HSSFDateUtil.isCellDateFormatted(cell))
                {
                    DateFormat df = new SimpleDateFormat("dd/MM/yy");
                    Date date = cell.getDateCellValue();
                    cellValue = df.format(date);
                }
                return cellValue;
            }else if(cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return "row "+rowNum+" or column "+colName+" does not exist  in Excel";
        }
    }
}