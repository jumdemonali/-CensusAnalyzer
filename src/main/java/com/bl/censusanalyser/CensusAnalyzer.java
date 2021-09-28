package com.bl.censusanalyser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class CensusAnalyzer {

    public int loadIndiaCensusData(String csvFilePath) {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(csvFilePath));
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndiaCensusCSV> iterator = csvToBean.iterator();
            int numOfEntries = 0;
            while (iterator.hasNext()) {
                numOfEntries++;
                iterator.next();
            }
            return numOfEntries;
        } catch (
                IOException e) {
            System.out.println(e);
        }
        return 0;
    }
}