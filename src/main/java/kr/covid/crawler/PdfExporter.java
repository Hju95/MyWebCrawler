package kr.covid.crawler;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.UnitValue;

import javax.swing.text.StyleConstants;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static org.apache.poi.ss.util.CellUtil.createCell;

public class PdfExporter {
    public static void fileToPdf(String date, List<CovidPrevention> covidPreventionList, String fileName) throws FileNotFoundException {
        PdfWriter writer = new PdfWriter(fileName);
        PdfDocument pdfDocument = new PdfDocument(writer);
        Document document = new Document(pdfDocument);
        document.setFontSize(11);
        PdfFont font = null;
        try {
            font = PdfFontFactory.createFont("NanumGothicLight.ttf", PdfEncodings.IDENTITY_H, true);
            document.setFont(font);

            //타이틀 생성
            Paragraph titleParagraph = new Paragraph("일일 코로나 예방 접종률( "+ date + ")");
            titleParagraph.setFontSize(24);
            titleParagraph.setTextAlignment(TextAlignment.CENTER);
            titleParagraph.setBold();
            document.add(titleParagraph);

            //테이블 생성
            Table table = new Table(UnitValue.createPercentArray(new float[]{10, 10, 10, 10, 10, 10, 10}));
            table.setWidth(UnitValue.createPercentValue(100));
            table.setMarginTop(20);

            //테이블 헤더 생성
            table.addHeaderCell(createCell("시도", true));
            table.addHeaderCell(createCell("1차 주간신규", true));
            table.addHeaderCell(createCell("1차 누적", true));
            table.addHeaderCell(createCell("1차 접종률", true));
            table.addHeaderCell(createCell("2차 주간신규", true));
            table.addHeaderCell(createCell("2차 누적", true));
            table.addHeaderCell(createCell("2차 접종률", true));

            //정보를 데이터에 넣기
            for (CovidPrevention covidPrevention : covidPreventionList) {
                table.addCell(new Cell().add(new Paragraph(covidPrevention.getRegion()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(covidPrevention.getFirstweeklynew())).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(covidPrevention.getFirstaccrue())).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(covidPrevention.getFirstpercent()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(covidPrevention.getSecondweeklynew())).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(String.valueOf(covidPrevention.getSecondaccrue())).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(covidPrevention.getSecondpercent()).setFont(font)));
            }

            document.add(table);
            document.close();

        } catch (IOException e) {
            System.out.println("PDF 파일 저장 중 오류가 발생했습니다");
            e.printStackTrace();
        }



    }
    private static Cell createCell(String content, boolean isHeader) {
        Paragraph paragraph = new Paragraph(content);
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(5);
        if (isHeader) {
            cell.setBackgroundColor(ColorConstants.LIGHT_GRAY);
            cell.setFontSize(14);
            cell.setBold();
        }
        return cell;
    }
}
