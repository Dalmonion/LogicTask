package com.task;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProcessorTest {

    @Test
    void testCaseOne() {
        //Given
        Processor processor = new Processor();
        List<String> initialData = List.of("plate level ILLGO I love-!(\" #$%&')*+,./:;<=>?@^_`{|}~^_`{|}~[[[]]\\\\\\\\\\ to work in global logic!   zzzzzz", "LoGiC");

        //When
        CycleSummary summary = processor.start(initialData);

        int testValue1 = summary.getSortedMap().get(new FrequencyElement(List.of('O'), 2));
        int testValue2 = summary.getSortedMap().get(new FrequencyElement(List.of('L', 'O', 'G', 'I', 'C'), 5));

        //Then
        try {
            assertEquals(new BigDecimal("0.51"), summary.getTotalFrequencyResults().getDivisonResult());
            assertEquals(new BigDecimal("23"), summary.getTotalFrequencyResults().getTotalKeywordsChars());
            assertEquals(45, summary.getTotalFrequencyResults().getTotalStringLength());
            assertEquals(9, summary.getSortedMap().entrySet().size());
            assertEquals(1, testValue1);
            assertEquals(5, testValue2);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}