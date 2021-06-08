package com.task;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Processor {

    private String initialCleanString(String input) {
        input = input.replaceAll("[-(!\"#$%&')*+,./:;<=>?@^_`{|}\\]~\\[\\\\]", "");
        input = input.replaceAll("\\s+", " ");
        input = input.toUpperCase();
        return input;
    }

    private TotalFrequencyResults countTotalFrequency(String input, String keyword) {
        String withoutSpaces = input.replaceAll("\\s", "");

        BigDecimal count = countTotalKeywordChars(input, keyword);
        BigDecimal divide = new BigDecimal(withoutSpaces.length());
        BigDecimal res = count.divide(divide, 2, RoundingMode.HALF_UP);
        return new TotalFrequencyResults(count, withoutSpaces.length(), res);

    }

    private List<String> convertStringToWordsArray(String input) {
        String[] parts = input.split(" ");
        return new ArrayList<>(Arrays.asList(parts));
    }

    private BigDecimal countTotalKeywordChars(String input, String keyword) {
        BigDecimal total = BigDecimal.ZERO;
        String keywordToUpperCase = keyword.toUpperCase();

        List<String> wordsArray = convertStringToWordsArray(input);

        for (int i = 0; i < keywordToUpperCase.length(); i++) {
            char singleCharacter = keywordToUpperCase.charAt(i);
            for (int j = 0; j < wordsArray.size(); j++) {
                for (int k = 0; k < wordsArray.get(j).length(); k++) {
                    char temp = wordsArray.get(j).charAt(k);
                    if (temp == singleCharacter) {
                        total = total.add(new BigDecimal(1));
                    }
                }
            }
        }
        return total;
    }


    public CycleSummary start(List<String> list) {
        String initial = list.get(0);
        String keyword = list.get(1).toUpperCase();
        List<String> wordsArray = convertStringToWordsArray(initialCleanString(initial));
        wordsArray = deleteWordsNotContainingKeyword(wordsArray, keyword);
        Map<FrequencyElement, Integer> resultMap = calculate(wordsArray, keyword);
        Map<FrequencyElement, Integer> sortedMap = sortMap(resultMap);
        TotalFrequencyResults results = countTotalFrequency(initialCleanString(initial), keyword);
        IOService.printResults(sortedMap, results);

        return new CycleSummary(sortedMap, results);
    }

    private Map<FrequencyElement, Integer> sortMap(Map<FrequencyElement, Integer> unsortedMap) {
        LinkedHashMap<FrequencyElement, Integer> sortedMap = new LinkedHashMap<>();
        unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));

        return sortedMap;
    }

    private List<String> fillArrayWithUsedKeywords(List<String> input, String keyword) {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < input.size(); i++) {
            elements.add("");
        }
        for (int i = 0; i < keyword.length(); i++) {
            for (int j = 0; j < input.size(); j++) {
                if (input.get(j).indexOf(keyword.charAt(i)) != -1) {
                    elements.set(j, elements.get(j) + keyword.charAt(i));
                }
            }
        }
        return elements;
    }

    private Map<FrequencyElement, Integer> calculate(List<String> input, String keyword) {
        Map<FrequencyElement, Integer> elementsMap = new HashMap<>();
        List<String> elements = fillArrayWithUsedKeywords(input, keyword);

        for (int i = 0; i < elements.size(); i++) {
            List<Character> charList = new ArrayList<>();
            for (int j = 0; j < elements.get(i).length(); j++) {
                if (!charList.contains(elements.get(i).charAt(j))) {
                    charList.add(elements.get(i).charAt(j));
                }
            }
            FrequencyElement element = new FrequencyElement(charList, input.get(i).length());

            if (elementsMap.isEmpty()) {
                elementsMap.put(element, checkSpecificWordForKeywordChars(input.get(i), elements.get(i)));
            } else {
                for (Map.Entry<FrequencyElement, Integer> entry : elementsMap.entrySet()) {
                    if (element.equals(entry.getKey())) {
                        entry.setValue(entry.getValue() + checkSpecificWordForKeywordChars(input.get(i),
                                elements.get(i)));
                    } else {
                        elementsMap.put(element,
                                checkSpecificWordForKeywordChars(input.get(i), elements.get(i)));
                        break;
                    }
                }
            }
        }
        return elementsMap;
    }

    private List<String> deleteWordsNotContainingKeyword(List<String> input, String keyword) {
        for (int i = 0; i < input.size(); i++) {
            boolean containsAtLeastOneLetter = false;
            for (int j = 0; j < keyword.length(); j++) {
                if (input.get(i).indexOf(keyword.charAt(j)) != -1) {
                    containsAtLeastOneLetter = true;
                    break;
                }
            }
            if (!containsAtLeastOneLetter) {
                input.remove(i);
                i--;
            }
        }
        return input;
    }

    private int checkSpecificWordForKeywordChars(String word, String keyword) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < keyword.length(); j++) {
                if (word.charAt(i) == keyword.charAt(j)) count++;
            }
        }
        return count;
    }
}
