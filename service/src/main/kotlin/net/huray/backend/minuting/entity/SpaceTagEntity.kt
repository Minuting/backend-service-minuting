package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "space_tags")
class SpaceTagEntity(
    space: SpaceEntity,
    tag: TagEntity
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id")
    var space: SpaceEntity = space


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    var tag: TagEntity = tag
}