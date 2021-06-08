package com.task;

import java.util.Map;

public class CycleSummary {
    private final Map<FrequencyElement, Integer> sortedMap;
    private final TotalFrequencyResults totalFrequencyResults;

    public CycleSummary(Map<FrequencyElement, Integer> sortedMap, TotalFrequencyResults totalFrequencyResults) {
        this.sortedMap = sortedMap;
        this.totalFrequencyResults = totalFrequencyResults;
    }

    public Map<FrequencyElement, Integer> getSortedMap() {
        return sortedMap;
    }

    public TotalFrequencyResults getTotalFrequencyResults() {
        return totalFrequencyResults;
    }
}
