package net.huray.backend.minuting.entity;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.enums.TagType;


@Getter
@Entity(name = "tag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    TagType type;

    Long color;

    Integer orderNum;
}
