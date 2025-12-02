package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLViewer {
    private static final String TAG_PATTERN = "<\\s*([a-zA-Z0-9]+)";

    public static void main(String[] args) {
        countAndPrintTagsFromUrl("https://example.com/", true);
    }

    private static Map<String, Integer> countTags(String html) {
        Map<String, Integer> tagFrequency = new HashMap<>();

        Pattern pattern = Pattern.compile(TAG_PATTERN);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String tag = matcher.group(1).toLowerCase();
            tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
        }

        return tagFrequency;
    }

    private static Optional<String> buildHtml(String urlString) {
        try {
            URI uri = URI.create(urlString);
            URL url = uri.toURL();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                StringBuilder htmlBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlBuilder.append(line);
                }
                return Optional.of(htmlBuilder.toString());

            } catch (IOException ioEx) {
                System.err.println("Error during reading data: " + ioEx.getMessage());
            }

        } catch (MalformedURLException malformedEx) {
            System.err.println("URL format is invalid: " + malformedEx.getMessage());

        } catch (IllegalArgumentException uriEx) {
            System.err.println("Error during URL creation: " + uriEx.getMessage());

        } catch (Exception e) {
            System.err.println("Unknown error: " + e.getClass().getSimpleName() + " — " + e.getMessage());
        }
        return Optional.empty();
    }

    public static Map<String, Integer> countTagsFromUrl(String urlString) {
        return buildHtml(urlString)
                .map(URLViewer::countTags)
                .orElse(new HashMap<>());
    }

    public static void countAndPrintTagsFromUrl(String urlString, boolean isOrderedByKey) {
        printTags(countTagsFromUrl(urlString), isOrderedByKey);
    }

    private static void printTags(Map<String, Integer> tagFrequency, boolean isOrderedByKey) {
        tagFrequency.entrySet().stream()
                .sorted(isOrderedByKey ? Map.Entry.comparingByKey() : Map.Entry.comparingByValue())
                .forEach(entry ->
                        System.out.println(entry.getKey() + " = " + entry.getValue()));
    }
}
