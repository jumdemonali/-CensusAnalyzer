package com.bl.censusanalyser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CensusAnalyzerTest {

    private String INDIA_CENSUS_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private String INDIA_STATE_CSV_FILE_PATH = "./src/main/resources/IndiaStateCode.csv";
    private String INIDAN_CENSUS_WrongCSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
    private String INIDAN_CENSUS_WrongCSV_FILE_FORMAT = "./src/main/resources/IndiaStateCode.txt";
    private String CENSUS_INCORRECT_DELIMITER = ".src/main/resources/StateCensusIncorrectDelimiter.csv";

    @Test
    public void givenIndianCensusCSVFIle_WhenLoad_ShouldReturnCorrectRecords() throws CensusAnalyserException {
        CensusAnalyzer censusAnalyzer = new CensusAnalyzer();
        int count = censusAnalyzer.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        Assert.assertEquals(29, count);
    }

    @Test
    public void givenIndianCensusWrongCSVFile_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INIDAN_CENSUS_WrongCSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.CENSUS_FILE_INCORRECT);
            e.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusWrongCSVFormat_ShouldReturnException() {
        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INIDAN_CENSUS_WrongCSV_FILE_FORMAT);
        } catch (CensusAnalyserException e) {
            Assert.assertEquals(e.type, CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_FILE_FORMAT);
        }
    }

    @Test
    public void givenStateCensusCSVFile_WhenDelimeter_Incorrect_Should_returnCustomException() {
        try {
            CensusAnalyzer censusAnalyser = new CensusAnalyzer();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadIndiaCensusData(INDIA_CENSUS_CSV_FILE_PATH);
        } catch (CensusAnalyserException a) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_INCORRECT_DELIMITER,a.type);
        }
    }

}
