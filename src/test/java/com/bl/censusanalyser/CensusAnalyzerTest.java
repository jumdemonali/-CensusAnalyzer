package com.bl.censusanalyser;
import org.junit.Assert;
import org.junit.Test;
public class CensusAnalyzerTest {

    private String INDIA_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private String INDIA_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";

    @Test
    public void givenIndianCensusCSVFIle_WhenLoad_ShouldReturnCorrectRecords() {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int count = censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }


}
