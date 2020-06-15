package it.register.tech.exercise.one;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

public class MainTechOne {
    public static void main(String[] args) {

        String filePath = "src/test/resources/logfiles/requests.log";
        try (
                Reader reader = Files.newBufferedReader(Paths.get(filePath));
        ) {
            CsvToBean<LogDetail> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(LogDetail.class)
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            List<LogDetail> csvUsers = csvToBean.parse();



            for(LogDetail csvUser : csvUsers){

                System.out.println("Name : " + csvUser.getTimestamp());
                System.out.println("Email : " + csvUser.getBytes());
                System.out.println("PhoneNo : " + csvUser.getStatus());
                System.out.println("Country : " + csvUser.getRemoteAddress());
                System.out.println("==========================");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

