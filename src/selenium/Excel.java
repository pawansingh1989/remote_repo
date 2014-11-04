package selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel 
{
	private FileInputStream file;
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private XSSFCell cell;
	private DataFormatter formatter;
	
	public void setExcel(String path,String sheetname) throws IOException
	{
		file=new FileInputStream(path);
		workbook=new XSSFWorkbook(file);
		sheet=workbook.getSheet(sheetname);
		
	}
	
	public String getCellValue(int row,int col)
	{
		{
		cell=sheet.getRow(row).getCell(col);
		formatter=new DataFormatter();
		return formatter.formatCellValue(cell);
		
		}
		
	}

}
