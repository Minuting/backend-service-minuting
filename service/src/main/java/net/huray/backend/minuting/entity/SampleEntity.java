package net.huray.backend.minuting.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.huray.backend.minuting.entity.common.BaseDateTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "samples")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SampleEntity extends BaseDateTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "sample_id")
    private Long id;

    private Integer no;

    private String data;

    public SampleEntity(Integer no, String data) {
        this.no = no;
        this.data = data;
    }

    public void updateEntity(Integer no, String data) {
        this.no = no;
        this.data = data;
    }

}
