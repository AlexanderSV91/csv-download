package com.faceit.example.util;

import com.faceit.example.model.Msi;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static final char DELIMITER = ';';
    public static final String TYPE = "text/csv";
    public static final String[] HEADERS = {"State", "Name", "Institution Type", "Phone Number", "Website"};

    private Utils() {
    }

    public static boolean hasCSVFormat(final MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    public static List<Msi> csvToMsi(final InputStream is) {
        try (final BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             final CSVParser csvParser = new CSVParser(fileReader, CSVFormat.newFormat(DELIMITER).withHeader(HEADERS))) {
            return csvParser.getRecords()
                    .stream()
                    .skip(1)
                    .map(csvRecord -> Msi.builder()
                            .state(csvRecord.get(HEADERS[0]))
                            .name(csvRecord.get(HEADERS[1]))
                            .institutionType(csvRecord.get(HEADERS[2]))
                            .phoneNumber(csvRecord.get(HEADERS[3]))
                            .website(csvRecord.get(HEADERS[4]))
                            .build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
