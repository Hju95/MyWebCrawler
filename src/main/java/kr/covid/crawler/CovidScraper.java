package kr.covid.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CovidScraper {
    public static void main(String[] args) {
        String url = "https://ncov.kdca.go.kr/sido.do?brdId=&brdGubun=&dataGubun=&ncvContSeq=&contSeq=&board_id=&gubun=";

        try {
            Document document = Jsoup.connect(url).get();

            String date = document.select("span.t_date > em").first().text();


            Element table = document.select("table.dragTable").first();
            Elements rows = table.select("tbody > tr");

            List<CovidPrevention> covidPreventionList = new ArrayList<>();

            for (Element row : rows) {
                String region = row.select("th").text();
                int firstweeklynew = Integer.parseInt(row.select("td:nth-child(2)").text().replaceAll(",",""));
                int firstaccrue = Integer.parseInt(row.select("td:nth-child(3)").text().replaceAll(",",""));
                String firstpercent = row.select("td:nth-child(4)").text();
                int secondweeklynew = Integer.parseInt(row.select("td:nth-child(5)").text().replaceAll(",",""));
                int secondaccrue = Integer.parseInt(row.select("td:nth-child(6)").text().replaceAll(",",""));
                String secondpercent = row.select("td:nth-child(7)").text();

                covidPreventionList.add(new CovidPrevention(region, firstweeklynew, firstaccrue, firstpercent, secondweeklynew, secondaccrue, secondpercent));
            }

            System.out.println("일일 접종률 (" + date + ")");
            System.out.println("시도 | 1차주간신규 | 1차누적 | 1차접종률 | 2차주간신규 | 2차누적 | 2차접종률");

            for (CovidPrevention covidPrevention : covidPreventionList) {
                System.out.println(covidPrevention);
            }

            //엑셀 파일 저장
            String excelName = "covid_prevention_" + date.replace(".","_") + ".xlsx";
            ExcelExporter.fileToExcel(date, covidPreventionList, excelName);
            System.out.println("엑셀 파일 저장함 : " + excelName);


        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
