package com.trent.common.utils.excel;

import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: Trent
 * @Date: 2022/7/7 15:28
 * @program: BAM
 * @Description:
 */
@Slf4j
public class ExcelUtil{
	private static final Integer DEFAULT_SHEET_MAXSIZE = 5000;
	
	public ExcelUtil() {
	}
	
	public static void writeToExcel(File objFile, String title, List titleList, List<Float> widthList, List<String> keyList, List<Map<String, Object>> dataList, Integer sheetMaxSize) throws IOException{
		long startTime = System.currentTimeMillis();
		log.info("写数据到execl");
		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
		setExcelStyle(workbook);
		createSheet(workbook, widthList, dataList.size(), sheetMaxSize);
		Sheet sheet = workbook.getSheetAt(0);
		setTitleString(workbook, sheet, title, 0, titleList.size() - 1);
		setTitle(workbook, sheet, titleList, widthList, 1);
		setBodyContent(workbook, sheet, keyList, dataList, 2, sheetMaxSize, false);
		long contentTime = System.currentTimeMillis();
		log.info("写数据到execl结束 执行时间" + (contentTime - startTime) + "ms");
		FileOutputStream fileOut = new FileOutputStream(objFile);
		
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException var23) {
			log.error("writeToExcel error:{}", var23);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException var22) {
				log.error("writeToExcel error:{}", var22);
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.info("文件流输出时间 " + (endTime - contentTime) + "ms");
	}
	
	public static void writeToExcelByPage(File objFile, List titleList, List<Float> widthList, List<String> keyList, Map codeMap, List<Map<String, Object>> dataList, Integer sheetMaxSize) throws IOException {
		long startTime = System.currentTimeMillis();
		log.error("写数据到execl");
		SXSSFWorkbook workbook = new SXSSFWorkbook(1000);
		setExcelStyle(workbook);
		Sheet sheet = workbook.getSheetAt(0);
		setTitle(workbook, sheet, titleList, widthList, 1);
		setBodyContent(workbook, sheet, keyList, dataList, 2, sheetMaxSize, false);
		long contentTime = System.currentTimeMillis();
		log.error("写数据到execl结束 执行时间" + (contentTime - startTime) + "ms");
		FileOutputStream fileOut = new FileOutputStream(objFile);
		
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException var23) {
			log.error("writeToExcel error:{}", var23);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException var22) {
				log.error("writeToExcel error:{}", var22);
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.error("文件流输出时间 " + (endTime - contentTime) + "ms");
	}
	
	public static void writeToExcel(File objFile, List titleList, List<Float> widthList, List<String> keyList, List<Map<String, Object>> dataList, Integer sheetMaxSize) throws IOException {
		if (null == sheetMaxSize) {
			sheetMaxSize = DEFAULT_SHEET_MAXSIZE;
		}
		
		long startTime = System.currentTimeMillis();
		log.info("写数据到execl");
		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		setExcelStyle(workbook);
		createSheet(workbook, widthList, dataList.size(), sheetMaxSize);
		Sheet sheet = workbook.getSheetAt(0);
		setTitle(workbook, sheet, titleList, widthList, 0);
		setBodyContent(workbook, sheet, keyList, dataList, 1, sheetMaxSize, false);
		long contentTime = System.currentTimeMillis();
		log.info("写数据到execl结束 执行时间" + (contentTime - startTime) + "ms");
		FileOutputStream fileOut = new FileOutputStream(objFile);
		
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException var22) {
			log.error("writeToExcel error:{}", var22);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException var21) {
				log.error("writeToExcel error:{}", var21);
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.info("文件流输出时间 " + (endTime - contentTime) + "ms");
	}
	
	public static void writeToExcel(File objFile, Map<String, List<String>> orderFormTitleMap, Map<String, List<Float>> orderFormWidthMap, Map<String, List<String>> orderFormKeyMap, Map<String, List<Map<String, Object>>> serviceFormRecordMap, Map<String, String> serviceFormTitleMap, Integer sheetMaxSize) throws IOException {
		if (null == sheetMaxSize) {
			sheetMaxSize = DEFAULT_SHEET_MAXSIZE;
		}
		
		new ArrayList();
		new ArrayList();
		new ArrayList();
		new ArrayList();
		long startTime = System.currentTimeMillis();
		log.info("写数据到execl");
		Map<Integer, Integer> sheelTitleMap = new HashMap();
		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		setExcelStyle(workbook);
		int i = 0;
		Iterator var16 = orderFormTitleMap.entrySet().iterator();
		
		while(var16.hasNext()) {
			Map.Entry entry = (Map.Entry)var16.next();
			
			try {
				List titleList = (List)entry.getValue();
				List keyList = (List)orderFormKeyMap.get(entry.getKey());
				List<Float> widthList = (List)orderFormWidthMap.get(entry.getKey());
				List<Map<String, Object>> dataList = (List)serviceFormRecordMap.get(entry.getKey());
				String sheelTitle = (String)serviceFormTitleMap.get(entry.getKey());
				String sheelTitleNew = "";
				Integer number = (Integer)sheelTitleMap.get(sheelTitle.hashCode());
				if (null != number) {
					number = number + 1;
					sheelTitleNew = sheelTitle + "(" + number + ")";
					sheelTitleMap.put(sheelTitle.hashCode(), number);
				} else {
					sheelTitleMap.put(sheelTitle.hashCode(), 0);
					sheelTitleNew = sheelTitle;
				}
				
				createSheet(workbook, widthList, validateSheetNameAndChang(sheelTitleNew, i));
				Sheet sheet = workbook.getSheetAt(i);
				setTitle(workbook, sheet, titleList, widthList, 0);
				setBodyContent(workbook, sheet, keyList, dataList, 1, sheetMaxSize, false);
				++i;
			} catch (Exception var33) {
				log.error("writeToExcel error:{}",var33);
			}
		}
		
		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(objFile);
			workbook.write(fileOut);
		} catch (IOException var32) {
			log.error("writeToExcel error:{}", var32);
		} finally {
			if (null != fileOut) {
				try {
					fileOut.flush();
					fileOut.close();
				} catch (IOException var31) {
					log.error("writeToExcel error:{}", var31);
				}
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.info("写数据到execl " + (endTime - startTime) + "ms");
	}
	
	public static String validateSheetNameAndChang(String srcName, int index) {
		String newSheetName = null;
		if ( StringUtils.isNotBlank(srcName)) {
			String regex = "[:\\\\/?*\\[\\]]";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(srcName);
			StringBuffer sf = new StringBuffer();
			
			while(m.find()) {
				m.appendReplacement(sf, "");
			}
			
			m.appendTail(sf);
			newSheetName = sf.toString();
			if (newSheetName.length() > 31) {
				newSheetName = newSheetName.substring(0, 31);
			}
			
			if (StringUtils.isBlank(newSheetName)) {
				newSheetName = "Sheet" + index;
			}
		}
		
		return newSheetName;
	}
	
	public static void writeToExcelNoTitle(File objFile, List<String> keyList, List<Map<String, Object>> dataList, int count, Integer sheetMaxSize) throws IOException {
		FileInputStream fs = new FileInputStream(objFile);
		XSSFWorkbook wb = new XSSFWorkbook(fs);
		Sheet sheet1 = wb.getSheetAt(0);
		SXSSFWorkbook workbook = new SXSSFWorkbook(wb);
		log.info("填充内容++++++++++++++++++++++++++++++++++++++++++++++");
		setBodyContent(workbook, sheet1, keyList, dataList, count, sheetMaxSize, true);
		FileOutputStream fileOut = new FileOutputStream(objFile);
		log.info("填充内容++++++++++++++++++++++++++++++++++++++++++++++");
		wb.write(fileOut);
		fs.close();
		fileOut.close();
	}
	
	public static SXSSFWorkbook writeToExcelByData(File objFile, List titleList, List<Float> widthList, List<String> keyList, List<Map<String, Object>> dataList, Integer sheetMaxSize) throws IOException {
		if (null == sheetMaxSize) {
			sheetMaxSize = DEFAULT_SHEET_MAXSIZE;
		}
		
		long startTime = System.currentTimeMillis();
		log.info("写数据到execl");
		SXSSFWorkbook workbook = new SXSSFWorkbook(100);
		setExcelStyle(workbook);
		createSheet(workbook, widthList, dataList.size(), sheetMaxSize);
		Sheet sheet = workbook.getSheetAt(0);
		setTitle(workbook, sheet, titleList, widthList, 0);
		setBodyContent(workbook, sheet, keyList, dataList, 1, sheetMaxSize, false);
		long contentTime = System.currentTimeMillis();
		log.info("写数据到execl结束 执行时间" + (contentTime - startTime) + "ms");
		FileOutputStream fileOut = new FileOutputStream(objFile);
		
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException var22) {
			log.error("writeToExcel error:{}", var22);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException var21) {
				log.error("writeToExcel error:{}", var21);
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.info("文件流输出时间 " + (endTime - contentTime) + "ms");
		return workbook;
	}
	
	public static SXSSFWorkbook writeToExcelByData(SXSSFWorkbook workbook, File objFile, List titleList, List<Float> widthList, List<String> keyList, List<Map<String, Object>> dataList, Integer sheetMaxSize, Integer sheetNumber) throws IOException {
		if (null == sheetMaxSize) {
			sheetMaxSize = DEFAULT_SHEET_MAXSIZE;
		}
		
		long startTime = System.currentTimeMillis();
		log.info("写数据到execl");
		Sheet sheet = workbook.getSheetAt(0);
		setTitle(workbook, sheet, titleList, widthList, 0);
		setBodyContent(workbook, sheet, keyList, dataList, 1, sheetMaxSize, false);
		long contentTime = System.currentTimeMillis();
		log.info("写数据到execl结束 执行时间" + (contentTime - startTime) + "ms");
		FileOutputStream fileOut = new FileOutputStream(objFile);
		
		try {
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException var23) {
			log.error("writeToExcel error:{}", var23);
		} finally {
			try {
				fileOut.flush();
				fileOut.close();
			} catch (IOException var22) {
				log.error("writeToExcel error:{}", var22);
			}
			
		}
		
		long endTime = System.currentTimeMillis();
		log.info("文件流输出时间 " + (endTime - contentTime) + "ms");
		return workbook;
	}
	
	public static void createSheet(SXSSFWorkbook workbook, List<Float> widthList, Integer dataLength, Integer sheetMaxSize) {
		int sheetPageTotalNumber = (int)Math.floor((double)(dataLength / sheetMaxSize));
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setWrapText(true);
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)10);
		
		for(int i = 0; i <= sheetPageTotalNumber; ++i) {
			Sheet sheet = workbook.createSheet("sheet" + (i + 1));
			Row row = sheet.createRow(0);
			
			for(int j = 0; j < widthList.size(); ++j) {
				Cell hcell = row.createCell(j);
				float width = Float.parseFloat(widthList.get(j) + "");
				int w = (int)(width * 1000.0F);
				sheet.setColumnWidth(j, w);
				style.setFont(font);
				hcell.setCellStyle(style);
			}
		}
		
	}
	
	public static void createSheet(SXSSFWorkbook workbook, List<Float> widthList, String sheelTitle) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setWrapText(true);
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)10);
		Sheet sheet = workbook.createSheet(sheelTitle);
		Row row = sheet.createRow(0);
		
		for(int j = 0; j < widthList.size(); ++j) {
			Cell hcell = row.createCell(j);
			float width = Float.parseFloat(widthList.get(j) + "");
			int w = (int)(width * 1000.0F);
			sheet.setColumnWidth(j, w);
			style.setFont(font);
			hcell.setCellStyle(style);
		}
		
	}
	
	public static void setExcelStyle(SXSSFWorkbook workbook) {
		Font font = workbook.createFont();
		font.setColor(HSSFColor.HSSFColorPredefined.BLACK.getIndex());
		font.setBold(false);
		font.setFontName("宋体");
		font.setFontHeightInPoints((short)10);
		CellStyle hcell = workbook.createCellStyle();
		hcell.setFont(font);
		hcell.setAlignment(HorizontalAlignment.CENTER);
		hcell.setVerticalAlignment(VerticalAlignment.CENTER);
	}
	
	public static void setTitleString(SXSSFWorkbook workbook, Sheet sheet, String title, int rowNum, int colNum) {
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, colNum));
		Row row = sheet.createRow(rowNum);
		row.setHeight((short)400);
		Cell hcell = row.createCell(0);
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(HorizontalAlignment.CENTER);
		style.setBorderBottom(BorderStyle.THIN);
		style.setBorderLeft(BorderStyle.THIN);
		style.setBorderTop(BorderStyle.THIN);
		style.setBorderRight(BorderStyle.THIN);
		style.setWrapText(true);
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setBold(true);
		font.setFontHeightInPoints((short)16);
		style.setFont(font);
		hcell.setCellStyle(style);
		hcell.setCellValue(title.toString());
	}
	
	public static void setBodyContent(SXSSFWorkbook workbook, Sheet sheet, List keyList, List dataList, int count, Integer sheetMaxSize, boolean setCurrentNumber) {
		CellStyle style = workbook.createCellStyle();
		setBodyRow(workbook, sheet, style, keyList, dataList, count, sheetMaxSize, setCurrentNumber);
	}
	
	public static int setBodyRow(SXSSFWorkbook workbook, Sheet sheet, CellStyle style, List keyList, List<Map<String, Object>> dataList, int count, Integer sheetMaxSize, boolean setCurrentNumber) {
		Integer pageNumber = 2;
		Integer createRowNumber = 1;
		if (setCurrentNumber) {
			createRowNumber = count;
		}
		
		for(int j = 0; j < dataList.size(); ++j) {
			Row row = sheet.createRow(createRowNumber);
			Map dataMap = (Map)dataList.get(j);
			setRowContent(style, keyList, row, dataMap);
			++count;
			createRowNumber = createRowNumber + 1;
		}
		
		dataList.clear();
		return count;
	}
	
	public static void setRowContent(CellStyle style, List keyList, Row row, Map dataMap) {
		for(int i = 0; i < keyList.size(); ++i) {
			String key = (String)keyList.get(i);
			Object data = dataMap.get(key);
			Cell hcell = row.createCell(i);
			if (null == data) {
				data = "";
			}
			
			if (data instanceof Timestamp ) {
				hcell.setCellValue(DateUtil.format((Timestamp)data, "yyyy-MM-dd HH:mm:ss"));
			} else if (!(data instanceof Date ) && !(data instanceof java.sql.Date)) {
				if (data instanceof Double) {
					hcell.setCellValue((Double)data);
				} else {
					hcell.setCellValue(data.toString());
				}
			} else {
				hcell.setCellValue(DateUtil.format((Date)data, "yyyy-MM-dd"));
			}
			
			hcell.setCellStyle(style);
		}
		
	}
	
	public static void setTitle(SXSSFWorkbook workbook, Sheet sheet, List titleList, List widthList, int rowNum) {
		Row row = sheet.createRow(rowNum);
		
		for(int i = 0; i < titleList.size(); ++i) {
			Cell hcell = row.createCell(i);
			String title = titleList.get(i) == null ? "" : titleList.get(i).toString();
			hcell.setCellValue(title);
			float width = Float.parseFloat(widthList.get(i) + "");
			int w = (int)(width * 1000.0F);
			sheet.setColumnWidth(i, w);
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(HorizontalAlignment.CENTER);
			style.setBorderBottom(BorderStyle.THIN);
			style.setBorderLeft(BorderStyle.THIN);
			style.setBorderTop(BorderStyle.THIN);
			style.setBorderRight(BorderStyle.THIN);
			style.setWrapText(true);
			Font font = workbook.createFont();
			font.setFontName("宋体");
			font.setBold(true);
			font.setFontHeightInPoints((short)12);
			style.setFont(font);
			hcell.setCellStyle(style);
		}
		
	}
	
	public static List<Object> readFromExcel(String path) throws Exception {
		try {
			File file = new File(path);
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
			return readFromExcel((InputStream)in);
		} catch (Exception var3) {
			return new ArrayList();
		}
	}
	
	public static List<Object> readFromExcel(InputStream is) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet st = wb.getSheetAt(0);
		int rowSize = 0;
		XSSFCell cell = null;
		List<Object> result = new ArrayList();
		
		for(int rowIndex = 0; rowIndex <= st.getLastRowNum(); ++rowIndex) {
			XSSFRow row = st.getRow(rowIndex);
			if (row != null) {
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				
				List<Object> data = new ArrayList();
				boolean hasValue = false;
				
				for(short columnIndex = 0; columnIndex <= row.getLastCellNum(); ++columnIndex) {
					cell = row.getCell(columnIndex);
					data.add(checkDataType(cell, (Map)null));
					hasValue = true;
				}
				
				if (hasValue) {
					result.add(data);
				}
			}
		}
		
		return result;
	}
	
	public static List<Object> readFromExcel(InputStream is, Map<String, String> param) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook(is);
		XSSFSheet st = wb.getSheetAt(0);
		int rowSize = 0;
		XSSFCell cell = null;
		List<Object> result = new ArrayList();
		
		for(int rowIndex = 0; rowIndex <= st.getLastRowNum(); ++rowIndex) {
			XSSFRow row = st.getRow(rowIndex);
			if (row != null) {
				int tempRowSize = row.getLastCellNum() + 1;
				if (tempRowSize > rowSize) {
					rowSize = tempRowSize;
				}
				
				List<Object> data = new ArrayList();
				boolean hasValue = false;
				
				for(short columnIndex = 0; columnIndex <= row.getLastCellNum(); ++columnIndex) {
					cell = row.getCell(columnIndex);
					data.add(checkDataType(cell, param));
					hasValue = true;
				}
				
				if (hasValue) {
					result.add(data);
				}
			}
		}
		
		return result;
	}
	
	public static Object checkDataType(XSSFCell cell, Map<String, String> param) {
		Object value = null;
		if (cell == null) {
			return value;
		} else {
			if (null == param) {
				param = new HashMap(0);
			}
			
			switch(cell.getCellType()) {
				case STRING:
					value = cell.getStringCellValue();
					break;
				case NUMERIC:
					if ( HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = (new SimpleDateFormat(((Map)param).get("dateFormat") != null ? (String)((Map)param).get("dateFormat") : "yyyy-MM-dd")).format(date);
						} else {
							value = "";
						}
					} else {
						value = (new DecimalFormat("0")).format(cell.getNumericCellValue());
					}
					break;
				case FORMULA:
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
				case BLANK:
					break;
				case ERROR:
					value = "";
					break;
				case BOOLEAN:
					value = cell.getBooleanCellValue() ? "Y" : "N";
					break;
				default:
					value = "";
			}
			
			return value;
		}
	}
	
	public static Object checkDataType(XSSFCell cell) {
		Object value = null;
		if (cell == null) {
			return value;
		} else {
			switch(cell.getCellType()) {
				case STRING:
					value = cell.getStringCellValue();
					break;
				case NUMERIC:
					if (HSSFDateUtil.isCellDateFormatted(cell)) {
						Date date = cell.getDateCellValue();
						if (date != null) {
							value = (new SimpleDateFormat("yyyy-MM-dd")).format(date);
						} else {
							value = "";
						}
					} else {
						value = (new DecimalFormat("0")).format(cell.getNumericCellValue());
					}
					break;
				case FORMULA:
					if (!cell.getStringCellValue().equals("")) {
						value = cell.getStringCellValue();
					} else {
						value = cell.getNumericCellValue() + "";
					}
				case BLANK:
					break;
				case ERROR:
					value = "";
					break;
				case BOOLEAN:
					value = cell.getBooleanCellValue() ? "Y" : "N";
					break;
				default:
					value = "";
			}
			
			return value;
		}
	}
}
