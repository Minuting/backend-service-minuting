package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.enums.SpacePermissionType

object SpaceDto {

    open class SpaceBase(
        @ApiModelProperty("스페이스 ID", required = true)
        val id: Long,
        @ApiModelProperty("스페이스 명", required = true)
        val name: String,
        @ApiModelProperty("스페이스 설명", required = true)
        val description: String,
        @ApiModelProperty("스페이스 Icon", required = true)
        val icon: String,
        @ApiModelProperty("스페이스 공개여부", required = true)
        val isPublic: Boolean
    )

    class SpaceSimple(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean
    ) : SpaceBase(id, name, description, icon, isPublic)

    class SpacePublic(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean
    ) : SpaceBase(id, name, description, icon, isPublic) {
        @ApiModelProperty("스페이스 참가여부", required = true)
        var isJoined: Boolean = false

    }

    class SpaceDetail(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean,
        @ApiModelProperty("스페이스 참여 권한", required = true)
        val spacePermissionType: SpacePermissionType = SpacePermissionType.GUEST,
        @ApiModelProperty("스페이스 태그 목록", required = true)
        val tags: List<TagDto.TagSimple> = arrayListOf()
    ) : SpaceBase(id, name, icon, description, isPublic)

    @ApiModel("스페이스 등록 요청 정보")
    class CreateReq(
        @ApiModelProperty("스페이스 명", required = true)
        val name: String,
        @ApiModelProperty("스페이스 설명", required = true)
        val description: String,
        @ApiModelProperty("스페이스 Icon", required = true)
        val icon: String,
        @ApiModelProperty("스페이스 공개여부", required = true)
        val isPublic: Boolean,
        @ApiModelProperty("스페이스 멤버 권한 리스트")
        val permissions: MutableList<PermissionDto.CreateReq> = mutableListOf(),
        @ApiModelProperty("스페이스 태그 ID 목록", required = true)
        val tags: List<Long> = arrayListOf()
    )

    @ApiModel("스페이스 수정 요청 정보")
    class UpdateReq(
        @ApiModelProperty("스페이스 명", required = true)
        val name: String,
        @ApiModelProperty("스페이스 설명", required = true)
        val description: String,
        @ApiModelProperty("스페이스 Icon", required = true)
        val icon: String,
        @ApiModelProperty("스페이스 공개여부", required = true)
        val isPublic: Boolean,
        @ApiModelProperty("스페이스 멤버 권한 리스트")
        val permissions: MutableList<PermissionDto.UpdateReq> = mutableListOf(),
        @ApiModelProperty("스페이스 태그 ID 목록", required = true)
        val tags: List<Long> = arrayListOf()
    )
}