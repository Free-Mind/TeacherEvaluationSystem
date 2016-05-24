package com.te.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.te.vo.ScoreExcelVO;
import com.te.vo.co.ScoreRowVO;

public class ExcelProcessUtil {

	@SuppressWarnings("deprecation")
	public static Workbook readExcel(String path){
		Workbook wb = null;
		try {  
            if (path.endsWith("xls")) {  
                wb =  new HSSFWorkbook(new FileInputStream(new File(path)));  
            } else {  
                wb = new XSSFWorkbook(path);  
            }  
            System.out.println(wb);  
        } catch (IOException e) { 
            e.printStackTrace();  
        } 
		return wb;
	}
	
	public static void outputExcel(ScoreExcelVO sevo,String out_path){
		
		Workbook wb = new HSSFWorkbook();
		Sheet sheet = wb.createSheet("教师成绩表");
		Row row = sheet.createRow(0);
		CellStyle style = wb.createCellStyle();
		style.setAlignment(CellStyle.ALIGN_CENTER);
		ArrayList<String> head = new ArrayList<String>();
		addHead(head);
		for(int i=0;i<head.size();i++){
			Cell cell = row.createCell(i);
			cell.setCellValue(head.get(i));
			cell.setCellStyle(style);
		}
		List<ScoreRowVO> srvo_list = sevo.getS_list();
		for(int i=0;i<srvo_list.size();i++){
			row = sheet.createRow(i+1);
			ScoreRowVO srvo = srvo_list.get(i);
			double score = srvo.getScore();  
			BigDecimal b = new BigDecimal(score);  
			double score2 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
			for(int j=0;j<head.size();j++){
				Cell cell = row.createCell(j);
				if(j==0)
					cell.setCellValue(srvo.getTid());
				else if(j==1)
					cell.setCellValue(srvo.getName());
				else if(j==2)
					cell.setCellValue(srvo.getOrg());
				else if(j==3)
					cell.setCellValue(score2);
				cell.setCellStyle(style);
			}
			row = sheet.createRow(srvo_list.size()+1);
			Cell cell = row.createCell(0);
			cell.setCellValue("更新于");
			cell.setCellStyle(style);
			cell = row.createCell(1);
			DateFormat dateformat = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String datetime = dateformat.format(sevo.getDate());
			cell.setCellValue(datetime);
			cell.setCellStyle(style);
			try {
				FileOutputStream fout = new FileOutputStream(out_path);
				wb.write(fout);
				fout.close();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
	
	private static void addHead(ArrayList<String> head){
		head.add("用户名");
		head.add("姓名");
		head.add("工作单位");
		head.add("成绩");
	}
	
}
