package com.bl.censusanalyser;

public class CensusAnalyserException extends Exception {
    public enum ExceptionType {
       CENSUS_FILE_INCORRECT,CENSUS_INCORRECT_FILE_FORMAT,RUNTIME_EXCEPTION;
    }

    public ExceptionType type;
    public CensusAnalyserException(String message, ExceptionType type) {
        super(message);
        this.type = type;

    }
}