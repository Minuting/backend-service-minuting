package net.huray.backend.minuting.account;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.base.BaseDateTimeEntity;

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

}
