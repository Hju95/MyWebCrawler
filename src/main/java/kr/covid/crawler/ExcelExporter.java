package kr.covid.crawler;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelExporter {
    public static void fileToExcel(String date, List<CovidPrevention> covidPreventionList, String fileName) throws IOException {

        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("코로나 접종률");

            //헤더 생성
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("시도");
            headerRow.createCell(1).setCellValue("1차 주간신규");
            headerRow.createCell(2).setCellValue("1차 누적");
            headerRow.createCell(3).setCellValue("1차 접종률");
            headerRow.createCell(4).setCellValue("2차 주간신규");
            headerRow.createCell(5).setCellValue("2차 누적");
            headerRow.createCell(6).setCellValue("2차 접종률");

            //데이터 생성
            for (int i = 0; i < covidPreventionList.size(); i++) {
                CovidPrevention covidPrevention = covidPreventionList.get(i);
                Row row = sheet.createRow(i + 1);

                row.createCell(0).setCellValue(covidPrevention.getRegion());
                row.createCell(1).setCellValue(covidPrevention.getFirstweeklynew());
                row.createCell(2).setCellValue(covidPrevention.getFirstaccrue());
                row.createCell(3).setCellValue(covidPrevention.getFirstpercent());
                row.createCell(4).setCellValue(covidPrevention.getSecondweeklynew());
                row.createCell(5).setCellValue(covidPrevention.getSecondaccrue());
                row.createCell(6).setCellValue(covidPrevention.getSecondpercent());
            }

            //엑셀 파일 저장
            String filename = "temp.xlsx";
            FileOutputStream outputStream = new FileOutputStream(new File(filename));
            workbook.write(outputStream);
            workbook.close();
            System.out.println("엑셀 파일이 저장되었습니다 : " + filename);
        }catch (IOException e){
            System.out.println("엑셀 파일 저장 중 오류가 발생했습니다");
            e.printStackTrace();
        }


    }
}
