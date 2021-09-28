package com.bl.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Scanner;
import java.util.stream.StreamSupport;
import java.util.regex.Pattern;
public class CensusAnalyzer {

    public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try {
            if (csvFilePath.contains("txt")) {
                throw new CensusAnalyserException("Wrong file type...File should be in CSV Format", CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT);
            }
            Reader reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndiaCensusCSV> iterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> csvIterable = () -> iterator;
            int count = (int) StreamSupport.stream(csvIterable.spliterator(), true).count();
            return count;
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_INCORRECT);
        }
    }
        public boolean readDelimeter(String filePath, String delimeterInput) throws CensusAnalyserException {
            boolean flag = true;

            File file = new File(filePath);
            Scanner scanner = null;
            try {
                scanner = new Scanner(file);
                scanner.useDelimiter(delimeterInput);

                Pattern result = scanner.delimiter();
                if (result.pattern().equals(",")){
                    flag = true;
                }
                    flag = false;
            } catch ( Exception e) {
                throw new CensusAnalyserException(e.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_DELIMITER);
            }

            return flag;

    }

}