package ru.mels.webpagewordscounter.service;

import org.springframework.data.domain.Page;
import ru.mels.webpagewordscounter.domain.entity.UniqueWord;

import java.util.List;

/**
 * Service responsible for adding statistics of the words into database and retrieving them
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
public interface UniqueWordService {

    /**
     * Saves statistics of words into database by URL
     *
     * If records with such URL already exist, at first they would be deleted and only then new {@code UniqueWord} entities would be added.
     *
     * @param URL of the webpage
     * @return List of all added {@code UniqueWord} entities
     */
    List<UniqueWord> saveToDB(String URL);

    /**
     * Gets the page of statistics by given URL from database.
     *
     * @param URL of the webpage
     * @param page page number
     * @param size size of the page
     * @return Page of {@code UniqueWord} entities
     */
    Page<UniqueWord> findPaginated(String URL, Integer page, Integer size);
}
