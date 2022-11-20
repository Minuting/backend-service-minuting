package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "space_tag")
@IdClass(SpaceTagId::class)
class SpaceTagEntity {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    lateinit var space: SpaceEntity

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    lateinit var tag: TagEntity

}