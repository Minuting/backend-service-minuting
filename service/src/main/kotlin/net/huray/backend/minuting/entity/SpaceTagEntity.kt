package net.huray.backend.minuting.entity

import javax.persistence.*

@Entity(name = "space_tags")
@IdClass(SpaceTagId::class)
class SpaceTagEntity(
    space: SpaceEntity,
    tag: TagEntity
) {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", insertable = false, updatable = false)
    var space: SpaceEntity = space

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    var tag: TagEntity = tag
}