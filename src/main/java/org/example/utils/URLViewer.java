package org.example.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLViewer {
    public static void countTagsFromUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder htmlBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                htmlBuilder.append(line);
            }
            reader.close();
            String html = htmlBuilder.toString();
            Map<String, Integer> tagFrequency = new HashMap<>();
            Pattern pattern = Pattern.compile("<\\s*([a-zA-Z0-9]+)");
            Matcher matcher = pattern.matcher(html);
            while (matcher.find()) {
                String tag = matcher.group(1).toLowerCase();
                tagFrequency.put(tag, tagFrequency.getOrDefault(tag, 0) + 1);
            }
            tagFrequency.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + " = " + entry.getValue()));
            tagFrequency.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue())
                    .forEach(entry ->
                            System.out.println(entry.getKey() + " = " + entry.getValue()));
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
