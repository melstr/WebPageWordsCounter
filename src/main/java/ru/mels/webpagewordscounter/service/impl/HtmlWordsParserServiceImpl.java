package ru.mels.webpagewordscounter.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;;
import ru.mels.webpagewordscounter.service.HtmlWordsParserService;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Class for taking text from <b>body</b> of the webpage, and parsing words and amount of them into Map
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
@Service
@RequiredArgsConstructor
public class HtmlWordsParserServiceImpl implements HtmlWordsParserService {

    /**
     * Counting amount of unique words in the webpage and putting them into {@code Map}
     *
     * @param URL of the webpage
     * @return  Map of words and amount of them in upperCase
     */
    @Override
    public Map<String, Long> parseWordsFromUrl(String URL) {
        String scrappedText = scrapeBodyTextFromWebPage(URL);

        /* Pattern for delimiters. Includes everything except for Latin, Cyrillic, digits and underscore */
        String delimitersRegex = "[^a-zA-Zа-яА-Я0-9_]";

        return Arrays.stream(scrappedText.split(delimitersRegex))
                .filter(word ->!Objects.equals(word, ""))
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Scrapes text from the webpage
     *
     * @param URL of the webpage
     * @return displayed text in the <b>body</b> of html
     */
    @SneakyThrows
    protected String scrapeBodyTextFromWebPage(String URL){
        return Jsoup.connect(URL).get().body().text();
    }
}
