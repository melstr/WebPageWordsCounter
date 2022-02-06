package ru.mels.webpagewordscounter.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.mels.webpagewordscounter.domain.entity.UniqueWord;

import java.util.UUID;

/**
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
public interface UniqueWordRepository extends JpaRepository<UniqueWord, UUID> {
    void deleteByURL(String URL);

    Page<UniqueWord> findAllByURL(String URL, Pageable pageable);
}
