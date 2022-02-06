package ru.mels.webpagewordscounter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mels.webpagewordscounter.domain.entity.UniqueWord;
import ru.mels.webpagewordscounter.repository.UniqueWordRepository;
import ru.mels.webpagewordscounter.service.HtmlWordsParserService;
import ru.mels.webpagewordscounter.service.UniqueWordService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Service responsible for adding statistics of the words into database and retrieving them
 *
 * @author Meleshkin Alexandr
 * @since 03.02.2022
 */
@Service
@RequiredArgsConstructor
public class UniqueWordServiceImpl implements UniqueWordService {
    private final HtmlWordsParserService htmlWordsParserService;
    private final UniqueWordRepository uniqueWordRepository;

    /**
     * Saves statistics of words into database by URL
     *
     * If records with such URL already exist, at first they would be deleted and only then new {@code UniqueWord} entities would be added.
     *
     * @param URL of the webpage
     * @return List of all added {@code UniqueWord} entities
     */
    @Transactional
    public List<UniqueWord> saveToDB(String URL){
        uniqueWordRepository.deleteByURL(URL);
        Map<String, Long> wordsMap = htmlWordsParserService.parseWordsFromUrl(URL);
        List<UniqueWord> uniqueWords = new ArrayList();

        for(Map.Entry<String, Long> entry : wordsMap.entrySet()){
            UniqueWord wordRecord = new UniqueWord();
            wordRecord.setWord(entry.getKey());
            wordRecord.setCount(entry.getValue());
            wordRecord.setURL(URL);

            uniqueWords.add(wordRecord);
        }
        return uniqueWordRepository.saveAll(uniqueWords);
    }

    /**
     * Gets the page of statistics by given URL from database.
     *
     * @param URL of the webpage
     * @param page page number
     * @param size size of the page
     * @return Page of {@code UniqueWord} entities
     */
    public Page<UniqueWord> findPaginated(String URL, Integer page, Integer size){
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("count").descending());
        return uniqueWordRepository.findAllByURL(URL, pageRequest);
    }

}
