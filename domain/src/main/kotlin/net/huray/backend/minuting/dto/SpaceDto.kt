package net.huray.backend.minuting.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import net.huray.backend.minuting.entity.PermissionEntity
import net.huray.backend.minuting.entity.SpaceEntity
import net.huray.backend.minuting.entity.SpaceTagEntity
import net.huray.backend.minuting.enums.PermissionType
import net.huray.backend.minuting.enums.SpacePermissionType
import springfox.documentation.annotations.ApiIgnore

object SpaceDto {

    @ApiIgnore
    open class BaseRes(
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

    @ApiModel("스페이스 기본 응답 정보")
    class SimpleRes private constructor(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean
    ) : BaseRes(id, name, description, icon, isPublic) {

        companion object {
            operator fun invoke(space: SpaceEntity) =
                SimpleRes(space.id, space.name, space.description, space.icon, space.isPublic)
        }

    }

    @ApiModel("공개 스페이스 응답 정보")
    class PublicRes private constructor(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean
    ) : BaseRes(id, name, description, icon, isPublic) {

        @ApiModelProperty("스페이스 참가여부", required = true)
        var isJoined: Boolean = false

        companion object {
            operator fun invoke(public: SpaceEntity, spaceEntityList: List<SpaceEntity>) =
                PublicRes(public.id, public.name, public.description, public.icon, public.isPublic)
                    .apply { isJoined = spaceEntityList.any { it.id == public.id } }
        }

    }

    @ApiModel("스페이스 상세 응답 정보")
    class DetailRes private constructor(
        id: Long,
        name: String,
        description: String,
        icon: String,
        isPublic: Boolean,
        @ApiModelProperty("스페이스 참여 권한", required = true)
        val spacePermissionType: SpacePermissionType,
        @ApiModelProperty("스페이스 멤버 목록", required = true)
        val memberList: List<SpaceMemberBase>,
        @ApiModelProperty("스페이스 태그 목록", required = true)
        val tagList: List<TagDto.SimpleRes>
    ) : BaseRes(id, name, icon, description, isPublic) {

        companion object {
            operator fun invoke(
                space: SpaceEntity,
                spacePermissionType: SpacePermissionType,
                permissionList: List<PermissionEntity>,
                spaceTagList: List<SpaceTagEntity>
            ) = DetailRes(
                space.id,
                space.name,
                space.description,
                space.icon,
                space.isPublic,
                spacePermissionType,
                permissionList.map { SpaceMemberBase(it.member.name, it.type) },
                spaceTagList.map { it.tag.run { TagDto.SimpleRes(id, name, type, color, orderNum) } }
            )
        }


    }

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
        val permissionList: MutableList<PermissionDto.CreateReq> = mutableListOf(),
        @ApiModelProperty("스페이스 태그 ID 목록", required = true)
        val tagIdList: List<Long> = arrayListOf()
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
        val permissionList: MutableList<PermissionDto.UpdateReq> = mutableListOf(),
        @ApiModelProperty("스페이스 태그 ID 목록", required = true)
        val tagIdList: List<Long> = arrayListOf()
    )

    class SpaceMemberBase(
        @ApiModelProperty("멤버 명")
        val name: String,
        @ApiModelProperty("스페이스 멤버 권한")
        val permissionType: PermissionType
    )

}