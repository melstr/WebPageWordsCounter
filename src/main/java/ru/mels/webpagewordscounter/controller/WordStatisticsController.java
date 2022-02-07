package ru.mels.webpagewordscounter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mels.webpagewordscounter.domain.entity.UniqueWord;
import ru.mels.webpagewordscounter.service.HtmlWordsParserService;
import ru.mels.webpagewordscounter.service.UniqueWordService;

import java.util.List;
import java.util.Map;

/**
 * Controller for getting statistics of words on the website's <b>body</b>'s displayed text by its URL
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
@Tag(name = "WordStatisticsController", description = "Getting statistics of words on the website's body's displayed text by its URL")
@ApiResponse(responseCode = "500", description = "Internal error")
@RestController
@RequiredArgsConstructor
@RequestMapping("word_statistics")
public class WordStatisticsController {
    private final HtmlWordsParserService htmlWordsParserService;
    private final UniqueWordService uniqueWordService;

    /**
     * Counting amount of unique words in the webpage and returning Json of the map
     *
     * @param URL of the webpage
     * @return  Json of map of the words and amount of them in upperCase
     */
    @Operation(description = "Gets word statistics of webpage from url")
    @ApiResponse(responseCode = "200", description = "Statistics successfully parsed")
    @GetMapping("/parse_webpage")
    Map<String, Long> parseWordStatisticsFromUrl(@RequestParam String URL){
        return htmlWordsParserService.parseWordsFromUrl(URL);
    }

    /**
     * Saves statistics of words into database by URL
     *
     * If records with such URL already exist, at first they would be deleted and only then new {@code UniqueWord} entities would be added.
     *
     * @param URL of the webpage
     * @return Json of all added {@code UniqueWord} entities
     */
    @Operation(description = "Save statistics to database, and get all saved")
    @ApiResponse(responseCode = "200", description = "Statistics successfully added to database")
    @PostMapping
    List<UniqueWord> saveStatisticsToDB(@RequestParam String URL){
        return uniqueWordService.saveToDB(URL);
    }

    /**
     * Gets the Json page of statistics by given URL from database.
     *
     * @param URL of the webpage
     * @param page page number
     * @param size size of the page
     * @return Json page of {@code UniqueWord} entities sorted by amount
     */
    @Operation(description = "Gets the Json page of statistics by given URL from database.")
    @ApiResponse(responseCode = "200", description = "Statistics loaded from the database")
    @GetMapping()
    Page<UniqueWord> getWordsPageFromDB(@RequestParam("page") Integer page,
                                        @RequestParam("size") Integer size,
                                        @RequestParam String URL){
        return uniqueWordService.findPaginated(URL, page, size);
    }
}
