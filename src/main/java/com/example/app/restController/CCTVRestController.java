package com.example.app.restController;



import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.*;


@RestController
@Slf4j
public class CCTVRestController {

//    @GetMapping("/getMain")
//    public ResponseEntity<String> getMain(){
//
//        System.out.println("GET /getMain..");
//        WebDriverManager.chromedriver().setup();
//        ChromeOptions options = new ChromeOptions();
//        options.setBinary("/bin/google-chrome-stable");
//
//        WebDriver driver = new ChromeDriver(options);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//
//        try {
//            // 웹 페이지 열기
//            driver.get("https://safecity.busan.go.kr/#/");
//
//            // 페이지 로드 대기
//            Thread.sleep(3000); // 페이지 로드 대기
//
////            // 팝업 닫기 및 원하는 동작 수행 (생략된 부분은 동일)
////            WebElement Popupbutton = driver.findElement(By.cssSelector(".popupClose"));
////            Popupbutton.click();
////            Thread.sleep(500);
////
////            WebElement Popupbutton2 = driver.findElement(By.cssSelector(".center"));
////            Popupbutton2.click();
////            Thread.sleep(500);
//
//            // 여기서 HTML 전체 소스 가져오기
//            String pageSource = driver.getPageSource(); // HTML 소스 가져오기
//
//            return ResponseEntity.ok(pageSource); // HTML 소스를 그대로 반환
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred during Selenium test.");
//        } finally {
//            driver.quit();
//        }
//    }






    @GetMapping("/cctv2")
    public List<WebElement> cctv2() {
        // WebDriverManager를 통해 ChromeDriver 설정
        WebDriverManager.chromedriver().setup();

        // Headless 모드로 ChromeDriver 설정
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless");  // UI 없이 실행
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-dev-shm-usage");

        // ChromeDriver 인스턴스 생성
        WebDriver driver = new ChromeDriver(options);
        options.setBinary("/bin/google-chrome-stable");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        try {
            // 웹 페이지 열기
            driver.get("https://safecity.busan.go.kr/#/");


            // 페이지 로드 대기
            Thread.sleep(3000);  // 페이지 로딩을 위해 잠시 대기 (필요 시 명시적으로 대기)

            // 페이지 로드 후 DOM 가져오기 (JavaScript가 실행된 후의 상태)
            // String pageSource = driver.getPageSource();

            // 출력 또는 다른 처리 수행
            //System.out.println(pageSource);

            //팝업1 닫기 버튼 클릭
            WebElement Popupbutton = driver.findElement(By.cssSelector(".popupClose"));
            System.out.println("Popupbutton" + Popupbutton);
            Popupbutton.click();
            Thread.sleep(500);

            //팝업2 닫기 버튼 클릭
            WebElement Popupbutton2 = driver.findElement(By.cssSelector(".center"));
            Popupbutton2.click();
            Thread.sleep(500);

            //재난감시 CCTV 클릭
            WebElement 재난감시CCTV버튼 = driver.findElement(By.cssSelector(".map_dep1_ul>li:nth-child(1)"));
            재난감시CCTV버튼.click();
            Thread.sleep(100);

            //교통감지끄기
            List<WebElement> CCTVFrameEls = driver.findElements(By.cssSelector(".induationCheckBox ul li .selectOption.active"));
            for(WebElement el : CCTVFrameEls)
                el.click();
            Thread.sleep(100);

            //재난감지CCTV 켜기
            WebElement CCTVFloodingEl = driver.findElement(By.cssSelector(".induationCheckBox ul li:nth-child(1) .selectOption"));
            if(CCTVFloodingEl.getText().contains("OFF"))
                CCTVFloodingEl.click();

            //zommin - 5
            WebElement zoominEl = driver.findElement(By.cssSelector(".leaflet-control-zoom-out"));
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            zoominEl.click();Thread.sleep(100);
            Thread.sleep(1500);
            
            //최상위 클러스터 클릭
            WebElement 재난CCTV = driver.findElement(By.cssSelector(".leaflet-marker-icon.marker-cluster.marker-cluster-large.leaflet-zoom-animated.leaflet-interactive"));
            재난CCTV.click();
            Thread.sleep(3000);


            //
            List<WebElement> ALLClusteredMarkers = driver.findElements(By.cssSelector(".leaflet-pane.leaflet-marker-pane>div"));
            System.out.println("ALLClusteredMarkers : " + ALLClusteredMarkers.size());



            WebElement mapEl = driver.findElement(By.cssSelector("#map"));
            System.out.println("map : " + mapEl);

            // 자바스크립트를 사용하여 브라우저의 모든 전역 객체를 검색
//            JavascriptExecutor js = (JavascriptExecutor) driver;
//            List<String> keys = (List<String>) js.executeScript(
//                    "return Object.keys(window);"
//            );
//
//            // 전역 객체 목록 출력
//            for (String key : keys) {
//                System.out.println("Window 객체 이름: " + key);
//            }


            //
            try {
                List<Map<String, Double>> markers = (List<Map<String, Double>>) ((JavascriptExecutor) driver).executeScript(

                        "console.log('map instanceof L.Map:', map instanceof L.Map);"
//                        "var markers = [];" +
//                                "console.log('L', L);" +
//                                "console.log('MAP', map);" +
//                                "if (typeof L !== 'undefined' && typeof map !== 'undefined') {" +
//                                "    map.eachLayer(function(layer) {" +
//                                "        if (layer instanceof L.Marker) {" +
//                                "            var latLng = layer.getLatLng();" +
//                                "            markers.push({'lat': latLng.lat, 'lng': latLng.lng});" +
//                                "        }" +
//                                "    });" +
//                                "} else {" +
//                                "    console.log('Leaflet 또는 map 객체를 찾을 수 없습니다.');" +
//                                "}" +
//                                "return markers;"
                );
            } catch (JavascriptException e) {
                System.out.println("JavaScript 오류 발생: " + e.getMessage());
            }




            //마커클릭해서 경로 가졍괴
            List<WebElement> ALLImgMarkers = driver.findElements(By.cssSelector(".leaflet-pane.leaflet-marker-pane>img"));
            System.out.println("ALLImgMarkers : " + ALLImgMarkers.size());
            for(WebElement e : ALLImgMarkers){
                e.click();
                System.out.println(e);
                Thread.sleep(3000);

                // 새로 열린 모든 창 핸들 가져오기
                Set<String> allWindowHandles = driver.getWindowHandles();

                // 현재 창의 핸들을 저장
                String mainWindowHandle = driver.getWindowHandle();

                // 새창 탐색
                for (String windowHandle : allWindowHandles) {
                    if (!windowHandle.equals(mainWindowHandle)) {
                        // 새창으로 전환
                        driver.switchTo().window(windowHandle);

                        // 새창의 URL 확인
                        String popupURL = driver.getCurrentUrl();
                        System.out.println("팝업 창 URL: " + popupURL);

                        // 팝업창 닫기
                        driver.close();

                        // 메인 창으로 다시 전환
                        driver.switchTo().window(mainWindowHandle);
                    }
                }

            }
            
            //클러스터 선별
//            List<WebElement> Cluster = driver.findElements(By.cssSelector(".leaflet-marker-icon.marker-cluster.leaflet-zoom-animated.leaflet-interactive"));
//            for(WebElement e : Cluster){
//                e.click();
//                Thread.sleep(2000);
//            }


            //마커 선별
//            List<WebElement> markerList = driver.findElements(By.cssSelector(".leaflet-marker-icon.leaflet-zoom-animated.leaflet-interactive"));
//            System.out.println("SIZE : " + markerList.size());
////            for(WebElement e : markerList){
////                e.click();
////                Thread.sleep(2000);
////            }


            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            // 브라우저 닫기
            //driver.quit();
        }
    }




//    @GetMapping("/cctv1")
//    public void handleCctvRequest() throws Exception {
//        try {
//            // JSoup을 사용해 웹 페이지의 모든 DOM 요소를 가져오기
//            String url = "https://safecity.busan.go.kr/#/";  // 대상 웹 페이지 URL
//            Document doc = Jsoup.connect(url).get();  // 웹 페이지 파싱
//
//            // 전체 DOM 가져오기
//            Elements allElements = doc.getAllElements();
//            System.out.println(allElements);
////            // 결과를 StringBuilder에 저장
////            StringBuilder sb = new StringBuilder();
////            sb.append("Total elements found: ").append(allElements.size()).append("\n\n");
////
////            // 모든 DOM 요소의 태그 이름 출력
////            for (Element element : allElements) {
////                System.out.println(element);
////                sb.append("Tag: ").append(element.tagName()).append("\n");
////            }
//
////            return sb.toString();  // 결과 반환
//        } catch (IOException e) {
//            e.printStackTrace();
////            return "Error occurred while fetching the DOM elements.";
//        }
//    }




//    // 부산광역시_CCTV 설치 현황정보
//    @GetMapping("/cctv")
//    public List<Item> cctv(){
//        log.info("GET /cctv..");
//
//        //[임시] URL 요청
////        String url = "https://apis.data.go.kr/6260000/BusanITSCCTV/CCTVList";
////        String pageNo = "1";
////        String numOfRows = "10";
////        String serviceKey = "xYZ80mMcU8S57mCCY/q8sRsk7o7G8NtnfnK7mVEuVxdtozrl0skuhvNf34epviHrru/jiRQ41FokE9H4lK0Hhg==";
////
////        String total = url
////                + "?serviceKey=" + serviceKey
////                + "&pageNo=" + pageNo
////                + "&numOfRows=" + numOfRows;
////
////        RestTemplate restTemplate = new RestTemplate();
////
////        ResponseEntity<Root> response = restTemplate.getForEntity(total, Root.class);
////        System.out.println(response.getBody());
//        // 고정된 CCTV 데이터 100개
//
//
//        List<Item> cctvList = new ArrayList<>();
//        cctvList.add(new Item("충무교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_1.stream/playlist.m3u8", 129.024297, 35.096521));
//        cctvList.add(new Item("남포동", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_2.stream/playlist.m3u8", 129.029273, 35.097858));
//        cctvList.add(new Item("옛시청", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_3.stream/playlist.m3u8", 129.035343, 35.097879));
//        cctvList.add(new Item("부산우체국", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_4.stream/playlist.m3u8", 129.036658, 35.103092));
//        cctvList.add(new Item("영주사거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_5.stream/playlist.m3u8", 129.037914, 35.11186));
//        cctvList.add(new Item("부산역", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_6.stream/playlist.m3u8", 129.039353, 35.114967));
//        cctvList.add(new Item("초량교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_7.stream/playlist.m3u8", 129.042203, 35.120005));
//        cctvList.add(new Item("좌천삼거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_8.stream/playlist.m3u8", 129.052554, 35.132734));
//        cctvList.add(new Item("자유시장", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_9.stream/playlist.m3u8", 129.059168, 35.141795));
//        cctvList.add(new Item("범내골교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_10.stream/playlist.m3u8", 129.059051, 35.147345));
//        // 계속해서 100개의 데이터를 추가합니다.
//        // 실제 데이터에 맞게 아래의 형식을 따라 나머지 데이터를 채워주세요.
//        cctvList.add(new Item("천우장", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_11.stream/playlist.m3u8", 129.058965, 35.15453));
//        cctvList.add(new Item("서면교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_12.stream/playlist.m3u8", 129.059124, 35.157704));
//        cctvList.add(new Item("삼전교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_13.stream/playlist.m3u8", 129.063771, 35.163847));
//        cctvList.add(new Item("양정교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_15.stream/playlist.m3u8", 129.070956, 35.172854));
//        cctvList.add(new Item("부산지방경찰청", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_16.stream/playlist.m3u8", 129.075663, 35.178605));
//        cctvList.add(new Item("연산교차로", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_17.stream/playlist.m3u8", 129.081753, 35.186064));
//        cctvList.add(new Item("교대사거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_18.stream/playlist.m3u8", 129.080232, 35.19575));
//        cctvList.add(new Item("동래롯데", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_19.stream/playlist.m3u8", 129.078823, 35.211747));
//        cctvList.add(new Item("온천교사거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_20.stream/playlist.m3u8", 129.084225, 35.218093));
//        cctvList.add(new Item("부산대학교사거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_21.stream/playlist.m3u8", 129.086384, 35.231319));
//        cctvList.add(new Item("부곡사거리", "https://its-stream3.busan.go.kr:8443/rtplive/cctv_22.stream/playlist.m3u8", 129.091587, 35.230741));
//
//
//        return cctvList;
//    }
//    @Data
//    private static class Content{
//        public int pageNo;
//        public int numOfRows;
//        public int totalCount;
//        public ArrayList<Item> items;
//    }
//    @Data
//    @AllArgsConstructor
//    private static class Item{
//        public String instlPos;
//        public String hlsAddr;
//        public double lot;
//        public double lat;
//    }
//    @Data
//    private static class Root{
//        public String resultCode;
//        public String resultMsg;
//        public Content content;
//    }


}