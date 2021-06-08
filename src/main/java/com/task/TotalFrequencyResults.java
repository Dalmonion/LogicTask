package com.task;

import java.math.BigDecimal;

public class TotalFrequencyResults {

    private final BigDecimal totalKeywordsChars;
    private final Integer totalStringLength;
    private final BigDecimal divisionResult;

    public TotalFrequencyResults(BigDecimal totalKeywordsChars, Integer totalStringLength, BigDecimal divisionResult) {
        this.totalKeywordsChars = totalKeywordsChars;
        this.totalStringLength = totalStringLength;
        this.divisionResult = divisionResult;
    }

    public BigDecimal getTotalKeywordsChars() {
        return totalKeywordsChars;
    }

    public Integer getTotalStringLength() {
        return totalStringLength;
    }

    public BigDecimal getDivisonResult() {
        return divisionResult;
    }
}
