package ru.mels.webpagewordscounter.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;



class HtmlWordsParserServiceImplTest {

    HtmlWordsParserServiceImpl mockService;

    @BeforeEach
    void setUp(){
        HtmlWordsParserServiceImpl mockService = Mockito.spy(new HtmlWordsParserServiceImpl());
    }

    @Test
    void parseWordsFromMockedText() {
        //given
        String bodyText = new String("Программная инженерия (англ. software engineering) — " +
                "приложение систематического, " +
                "инженерия, SOFtwAre " +
                "инженерия к engineering, software");

        // mocking method that returns Jsoup.connect(URL).get().body().text()
        Mockito.doReturn(bodyText).when(mockService).scrapeBodyTextFromWebPage(ArgumentMatchers.anyString());

        //when
        Map<String, Long> actual = mockService.parseWordsFromUrl("");

        //expected map
        Map<String, Long> expected = new HashMap<>();
        expected.put("ПРОГРАММНАЯ", 3L);
        expected.put("ИНЖЕНЕРИЯ", 3L);
        expected.put("АНГЛ", 1L);
        expected.put("SOFTWARE", 3L);
        expected.put("ENGINEERING", 2L);
        expected.put("ПРИЛОЖЕНИЕ", 1L);
        expected.put("СИСТЕМАТИЧЕСКОГО", 1L);
        expected.put("К", 1L);

        //then
        Assertions.assertThat(actual.equals(expected));

    }

}