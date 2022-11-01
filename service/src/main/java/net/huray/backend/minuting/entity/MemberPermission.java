package net.huray.backend.minuting.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@DiscriminatorValue("MemberPermission")
public class MemberPermission extends Permission{

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = true)
    private Member member;
}
