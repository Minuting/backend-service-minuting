package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

@Getter
@Entity(name = "company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private String ceo;

    private String telNumber;

    private String address;

    private String serviceKey;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
