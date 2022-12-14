package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "accounts")
class AccountEntity(
    email: String,
    memberEntity: MemberEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    var email: String = email
        protected set

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var memberEntity: MemberEntity = memberEntity
        protected set

}