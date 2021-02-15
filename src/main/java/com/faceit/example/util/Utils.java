package com.faceit.example.util;

import com.faceit.example.dto.response.MsiResponse;
import com.faceit.example.model.Msi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static String type = "text/csv";
    public static String[] headers = {"State", "Name", "Institution Type", "Phone Number", "Website"};

    public static boolean hasCSVFormat(MultipartFile file) {
        return type.equals(file.getContentType());
    }

    public static List<Msi> csvToMsi(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.newFormat(';').withHeader(headers))) {

            List<Msi> msiList = new ArrayList<>();
            List<CSVRecord> csvRecords = csvParser.getRecords();

            boolean isHeader = true;
            for (CSVRecord csvRecord : csvRecords) {
                if (isHeader) {
                    isHeader = false;
                    continue;
                }
                Msi msi = new Msi(
                        csvRecord.get(headers[0]),
                        csvRecord.get(headers[1]),
                        csvRecord.get(headers[2]),
                        csvRecord.get(headers[3]),
                        csvRecord.get(headers[4])
                );
                msiList.add(msi);
            }

            return msiList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static PageImpl<MsiResponse> pageEntityToPageResponse(Page<Msi> page, List<MsiResponse> list) {
        return new PageImpl<>(list, page.getPageable(), page.getTotalElements());
    }
}
