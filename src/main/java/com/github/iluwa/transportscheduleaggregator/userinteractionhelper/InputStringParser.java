package com.github.iluwa.transportscheduleaggregator.userinteractionhelper;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class helps to parse string with spaces and quotes
 */
@Component
public class InputStringParser {
    private static final Pattern USER_INPUT_PATTERN = Pattern.compile("\"([^\"]*)\"|(\\S+)");

    // Default matcher with empty String
    private final Matcher matcher = USER_INPUT_PATTERN.matcher("");

    /**
     * @return next match of constatn pattern
     */
    public String nextMatch() {
        if (matcher.find()) {
            if (matcher.group(1) != null) {
                return matcher.group(1);
            } else {
                return matcher.group(2);
            }
        }
        return null;
    }

    /**
     * @return matches count in processed string
     */
    public int matchesCount() {
        int count = 0;
        int currMatch = getCurrMatchIndex();
        matcher.reset();

        while (matcher.find()) {
            count++;
        }
        matcher.find(currMatch);
        return count;
    }

    /**
     * Parse new string
     * @param userInput - string to parse
     */
    public void match(String userInput) {
        matcher.reset(userInput);
    }

    /**
     * @return if no match has happened, return 0, otherwise index of start of current match in string
     */
    private int getCurrMatchIndex() {
        int currMatch = 0;
        try {
            currMatch = matcher.start();
        } catch (IllegalStateException ignored) {
            // No match has yet been attempted
        }
        return currMatch;
    }
}
