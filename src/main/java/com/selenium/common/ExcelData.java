package com.selenium.common;

/**
 * @author Tran Viet Duc
 * 
 * @version 0.1
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.streaming.SXSSFRow.CellIterator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelData {

	protected static XSSFSheet sheet;
	protected static XSSFWorkbook workbook;
	// private static Map<String, Integer> mapTestCol = new
	// HashMap<String,Integer>();

	private static String SHEET_DATA = "Data";

	// private static String SHEET_DATA_1="Data1";

	private static XSSFRow row;
	private static XSSFCell cell;

	public static void loadFile(String fileName, String sheetName) throws FileNotFoundException {

		File file = new File(fileName);
		file.canRead();
		FileInputStream fis = new FileInputStream(file);

		try {

			workbook = new XSSFWorkbook(fis);
			// XSSFWorkbook test = new XSSFWorkbook();
			sheet = workbook.getSheet(sheetName);
			// fis.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void loadFile(String fileName) throws FileNotFoundException {
		loadFile(fileName, SHEET_DATA);
	}

	public static String getCellValueData(String fieldSearch) throws FileNotFoundException {
		loadFile(Global.CurrDataFileName);

		return getCellValue(fieldSearch);

	}

	public static String getCellValue(String fieldSearch) {

		String r = null;

		int iRow = 0;

		DataFormatter formatter = new DataFormatter();

		FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();

		iRow = getRowIndex(fieldSearch);

		Cell cell = sheet.getRow(iRow).getCell(1);

		if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {

			r = formatter.formatCellValue(cell);

		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

			evaluator.equals(cell);
			r = formatter.formatCellValue(cell, evaluator);
		}

		else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {

			r = cell.getStringCellValue();

		}

		return r;

	}

	public static int findRow(int iCol, String searchText) {
		for (Row row : sheet) {
			Cell cell = row.getCell(iCol);
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().equals(searchText)) {
					return row.getRowNum();
				}
			}
		}

		return 0;
	}

	public static int findCol(int iRow, String searchText) {

		Row row = sheet.getRow(iRow);
		for (Cell cell : row) {
			if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
				if (cell.getRichStringCellValue().getString().contains(searchText)) {
					return cell.getColumnIndex();
				}
			}
		}
		return 0;
	}

	public static int getRowIndex(String search_text) {

		Iterator<Row> itr = sheet.iterator();

		while (itr.hasNext()) {
			Row row = itr.next();

			Iterator<Cell> cellIterator = row.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();

				if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
					if (cell.getRichStringCellValue().getString().trim().equals(search_text)) {

						// row.getRowNum();

						return row.getRowNum();
					}

				} else if (cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {

					if (cell.getRichStringCellValue().getString().trim().equals(search_text)) {

						return row.getRowNum();
					}

				} else {

					System.out.println("No value");
				}
			}

		}
		return 0;

	}

	public ArrayList<String> readExecelBaseColNum(int colNo) {

		Iterator<Row> r = sheet.iterator();

		r.next();

		ArrayList<String> list = new ArrayList<String>();

		while (r.hasNext()) {

			list.add(r.next().getCell(colNo).getStringCellValue());

		}

		System.out.println("list of values:" + list);
		return list;
	}
	
}