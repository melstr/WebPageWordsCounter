package ru.mels.webpagewordscounter.service;

import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Class for taking text from <b>body</b> of the webpage, and parsing words and amount of them into Map
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
@Service
public interface HtmlWordsParserService {

    /**
     * Counting amount of unique words in the webpage and putting them into {@code Map}
     *
     * @param URL of the webpage
     * @return  Map of words and amount of them in upperCase
     */
    Map<String, Long> parseWordsFromUrl(String URL);

}
