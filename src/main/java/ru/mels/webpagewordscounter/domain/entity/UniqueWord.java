package ru.mels.webpagewordscounter.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity of word statistics
 *
 * @author Meleshkin Alexandr
 * @since 02.02.2022
 */
@Getter
@Setter
@Entity
@Table(name = "unique_words")
public class UniqueWord extends BaseEntity{
    String URL;
    String word;
    Long count;
}
