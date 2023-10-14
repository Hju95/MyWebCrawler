# MyWebCrawler
### [과제]
****시도별 코로나 예방접종률 웹 크롤러****

코로나바이러스감염증-19 사이트에서 시도별 접종 현황을 크롤링한다.

[https://ncov.kdca.go.kr/](https://ncov.kdca.go.kr/sido.do?brdId=&brdGubun=&dataGubun=&ncvContSeq=&contSeq=&board_id=&gubun=)

### [구현 기능]

![covid_prevention](https://github.com/Hju95/MyWebCrawler/assets/59231743/a7b71ab3-9469-412c-b060-5e96e4338f14)

- 위 이미지의 데이터를 크롤링

![excel_covid](https://github.com/Hju95/MyWebCrawler/assets/59231743/437fed5c-c92c-4ebb-8b84-0b78f0adca1f)

- 엑셀 파일로 저장

![pdf_covid](https://github.com/Hju95/MyWebCrawler/assets/59231743/0d2597f0-ac58-44c6-98a1-c6301d769754)

- PDF 파일로 저장

### [기능 상세 설명]

> 4가지의 파일로 구분

****CovidPrevention.java****

- 필요한 데이터(시도별 접종률)를 담는 역할

****CovidScraper.java****

- 크롤링 역할
1. Jsoup 을 사용하여 connect 한다.
```
Document document = Jsoup.connect(url).get();
```
   
2. Element 를 사용하여 원하는 데이터만 추출하여 CovidPrevention 형식으로 만든다.
```
String region = row.select("th").text();
int firstweeklynew = Integer.parseInt(row.select("td:nth-child(2)").text().replaceAll(",",""));
...
covidPreventionList.add(new CovidPrevention(region, firstweeklynew, firstaccrue, firstpercent, secondweeklynew, secondaccrue, secondpercent));
```

****ExcelExporter.java****

엑셀 파일 저장 역할
1. poi 를 사용하여 Sheet와 헤더를 생성한다.
```
Workbook workbook = new XSSWorkbook();
Sheet sheet = workbook.createSheet("코로나 접종률);

Row headerRow = sheet.createRow(0);
headerRow.createCell(0).setCellValue("시도");
headerRow.createCell(1).setCellValue("1차 주간신규");
...
```

2. 데이터를 생성한다.
```
row.createCell(0).setCellValue(covidPrevention.getRegion());
row.createCell(1).setCellValue(covidPrevention.getFirstweeklynew());
...
```
3. 엑셀 파일 저장
```
FileOutputStream outputStream = new FileOutputStream(new File(fileName));
workbook.write(outputStream);
workbook.close();
```

****PdfExporter.java****
1. itext 를 사용하여 타이틀, 테이블, 테이블 헤더를 생성한다.
```
Paragraph titleParagraph = new Paragraph("일일 코로나 예방 접종률( "+ date + ")");
document.add(titleParagraph);
...

Table table = new Table(UnitValue.createPercentArray(new float[]{10, 10, 10, 10, 10, 10, 10}));

table.addHeaderCell(createCell("시도", true));
table.addHeaderCell(createCell("1차 주간신규", true));
...
```

2. 데이터를 생성한다.
```
table.addCell(new Cell().add(new Paragraph(covidPrevention.getRegion()).setFont(font)));
table.addCell(new Cell().add(new Paragraph(String.valueOf(covidPrevention.getFirstweeklynew())).setFont(font)));
...
```

3. PDF 파일 저장
```
document.add(table);
document.close();
```

### [개발 도구]
IDE : IntelliJ IDEA 2023.2.2 (Community Edition)

Dependency : poi(ver 5.2.0), poi-ooxml(ver 5.2.0), log4j-core(ver 2.17.1), jsoup(1.15.3), itext7-core(ver 7.1.18)

..