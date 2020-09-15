package com.auto.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fis;
	public static FileOutputStream fos;
	public static XSSFWorkbook wb;
	public static XSSFSheet sh;
	public static XSSFRow row;
	public static XSSFCell cell;

	public static int getRowCount(String xlFile, String xlSheet) throws IOException {

		int rowCount = 0;
		try {
			fis = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet(xlSheet);
			rowCount = sh.getLastRowNum();

			wb.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCount;

	}

	public static int getCellCount(String xlFile, String xlSheet, int rowNum) throws IOException {

		int cellCount = 0;
		try {
			fis = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet(xlSheet);
			row = sh.getRow(rowNum);

			cellCount = row.getLastCellNum();

			wb.close();
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cellCount;

	}

	public static String getCellData(String xlFile, String xlSheet, int rowNum, int colNum) throws IOException {
		String cellData = null;
		try {
			fis = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet(xlSheet);
			row = sh.getRow(rowNum);
			cell = row.getCell(colNum);

			DataFormatter formatter = new DataFormatter();
			cellData = formatter.formatCellValue(cell);
			return cellData;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			cellData = "";
		}

		wb.close();
		fis.close();

		return cellData;

	}

	public static void setCellData(String xlFile, String xlSheet, int rowNum, int colNum, String data)
			throws IOException {
		try {
			fis = new FileInputStream(xlFile);
			wb = new XSSFWorkbook(fis);
			sh = wb.getSheet(xlSheet);
			row = sh.getRow(rowNum);

			cell = row.createCell(colNum);
			cell.setCellValue(data);

			fos = new FileOutputStream(xlFile);
			wb.write(fos);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		wb.close();
		fis.close();
		fos.close();

	}

}
