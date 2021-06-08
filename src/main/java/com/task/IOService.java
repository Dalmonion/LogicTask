package com.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class IOService {

    private IOService() {
    }

    private final static Scanner scanner = new Scanner(System.in);

    public static List<String> welcomeMessage() {
        System.out.println("Type the string:");
        String message = scanner.nextLine();
        System.out.println("Type keyword");
        String keyword = scanner.nextLine();
        return List.of(message, keyword);
    }

    public static void printResults(Map<FrequencyElement, Integer> sortedMap, TotalFrequencyResults total) {
        for (Map.Entry<FrequencyElement, Integer> entry : sortedMap.entrySet()) {
            BigDecimal result = new BigDecimal(entry.getValue()).divide(total.getTotalKeywordsChars(), 2, RoundingMode.HALF_UP);
            System.out.println("{" + entry.getKey().getCharactersList() + ", " + entry.getKey().getWordLength() +
                    "} = " + result + " (" + entry.getValue() + "/" + total.getTotalKeywordsChars() + ")");
        }
        System.out.println("Total Frequency: " + total.getDivisonResult() + " (" + total.getTotalKeywordsChars() +
                "/" + total.getTotalStringLength() + ")");
    }
}
