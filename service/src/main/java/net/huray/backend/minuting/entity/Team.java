package net.huray.backend.minuting.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

@Getter
@Entity(name = "team")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team  extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    private String name;

    private Long pId;




}
