package fr.hoonew.server.service.dto.converter

import fr.hoonew.server.model.Content
import fr.hoonew.server.service.dto.ContentDto
import fr.hoonew.server.service.dto.common.DtoConverter

object ContentDtoConverter : DtoConverter<Content, ContentDto> {
    override fun convert(entity: Content): ContentDto =
        ContentDto(
            id = entity.id,
            name = entity.name,
            url = entity.url,
            category = entity.category,
            creatorId = entity.creator?.id,
            creatorName = entity.creator?.name ?: ""
        )

    override fun revert(dto: ContentDto): Content =
        Content(
            id = dto.id,
            name = dto.name,
            url = dto.url,
            category = dto.category
        )
}
