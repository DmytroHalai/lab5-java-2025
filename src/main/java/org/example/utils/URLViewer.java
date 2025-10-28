package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLViewer {
    public static void countTagsFromUrl(String urlString, boolean isOrderedByKey) {
        try {
            URI uri = URI.create(urlString);
            URL url = uri.toURL();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                StringBuilder htmlBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    htmlBuilder.append(line);
                }

                String html = htmlBuilder.toString();
                Map<String, Integer> tagFrequency = new HashMap<>();

                Pattern pattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)");
                Matcher matcher = pattern.matcher(html);
                while (matcher.find()) {
                    String tag = matcher.group(1).toLowerCase();
                    tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
                }

                printTags(tagFrequency, isOrderedByKey);

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
    }

     private static void printTags(Map<String, Integer> tagFrequency, boolean isOrderedByKey) {
        if (isOrderedByKey) {
            tagFrequency.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + " = " + entry.getValue()));
        } else {
            tagFrequency.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + " = " + entry.getValue()));
        }
    }
}
