package com.faceit.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
ТЗ (Java backend)

Стек
1 java 8+
2 Spring Boot
3 H2 (Mysql)

Нужно написать простой backend для загрузки .csv файла и получения загруженных данных.
1) Файл должен загружаться через Rest API, распрасиваться и данные сохраняться в базу данных.
2) Все загруженные данные можно получиться в JSON формате также через Rest API.
(не обязательно) 3) сделать пагинация и сортировку.
*/
@SpringBootApplication
public class CsvDownloadApplication {

    public static void main(String[] args) {
        SpringApplication.run(CsvDownloadApplication.class, args);
    }
}
