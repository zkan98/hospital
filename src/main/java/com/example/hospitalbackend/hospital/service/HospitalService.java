package com.example.hospitalbackend.hospital.service;

import com.example.hospitalbackend.hospital.entity.Hospital;
import com.example.hospitalbackend.hospital.repository.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    private static final Logger logger = LoggerFactory.getLogger(HospitalService.class);

    public void fetchAndSaveHospitalData() {
        String serviceKey = "ujQYUsjsfyuEAFHXJUvQfibDKiHR77QD4czLDXiJzOTN6FIGAxAAWkZy%2FtIVV%2B%2FlHv%2Bily25gDSSGo29%2FGq18g%3D%3D"; // 유효한 서비스 키를 입력하세요.
        int pageNo = 1;
        int numOfRows = 100; // 한 번에 가져올 행의 수 설정
        boolean moreData = true;

        while (moreData) {
            String url = "https://apis.data.go.kr/B551182/hospInfoServicev2/getHospBasisList?ServiceKey=" + serviceKey + "&pageNo=" + pageNo + "&numOfRows=" + numOfRows;
            try {
                HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                conn.setRequestMethod("GET");

                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();
                logger.info("Fetched data: {}", sb.toString());
                List<Hospital> hospitals = parseHospitalsFromXml(sb.toString());
                hospitalRepository.saveAll(hospitals);
                logger.info("Saved {} hospitals", hospitals.size());

                // 더 이상 데이터가 없으면 반복 중지
                if (hospitals.size() < numOfRows) {
                    moreData = false;
                } else {
                    pageNo++;
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Error fetching data", e);
                moreData = false;
            }
        }
    }

    private List<Hospital> parseHospitalsFromXml(String xmlResponse) {
        List<Hospital> hospitals = new ArrayList<>();
        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new InputSource(new java.io.StringReader(xmlResponse)));
            NodeList items = doc.getElementsByTagName("item");

            for (int i = 0; i < items.getLength(); i++) {
                org.w3c.dom.Node item = items.item(i);
                Hospital hospital = new Hospital();
                hospital.setName(Optional.ofNullable(getValue("yadmNm", item)).orElse("Unknown"));
                hospital.setAddress(Optional.ofNullable(getValue("addr", item)).orElse("Unknown"));
                hospital.setLatitude(Double.parseDouble(Optional.ofNullable(getValue("YPos", item)).orElse("0.0")));
                hospital.setLongitude(Double.parseDouble(Optional.ofNullable(getValue("XPos", item)).orElse("0.0")));
                // 필요한 다른 필드 설정
                hospitals.add(hospital);
                logger.info("Parsed hospital: {}", hospital);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error parsing XML", e);
        }
        return hospitals;
    }


    private String getValue(String tag, org.w3c.dom.Node item) {
        NodeList nodeList = ((org.w3c.dom.Element) item).getElementsByTagName(tag);
        if (nodeList.getLength() > 0) {
            NodeList nodes = nodeList.item(0).getChildNodes();
            if (nodes != null && nodes.getLength() > 0) {
                return nodes.item(0).getNodeValue();
            }
        }
        return null; // 태그가 없거나 값이 없을 때 null 반환
    }


    public Optional<Hospital> getHospitalById(Long id) {
        return hospitalRepository.findById(id);
    }
}
