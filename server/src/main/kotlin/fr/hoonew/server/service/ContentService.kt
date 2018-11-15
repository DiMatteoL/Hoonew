package fr.hoonew.server.service

import fr.hoonew.server.repository.ContentRepository
import fr.hoonew.server.repository.CreatorRepository
import fr.hoonew.server.service.dto.ContentDto
import fr.hoonew.server.service.dto.common.PageDto
import fr.hoonew.server.service.dto.common.PaginationDto
import fr.hoonew.server.service.dto.converter.ContentDtoConverter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.ws.rs.BadRequestException
import javax.ws.rs.NotFoundException

@Service
class ContentService {
    @Autowired
    private lateinit var contentRepository : ContentRepository

    @Autowired
    private lateinit var creatorRepository : CreatorRepository

    @Transactional(readOnly = true)
    fun getContent(id: Long?) : ContentDto {
        val contentId = id ?: throw BadRequestException("Content id must not be null")
        return contentRepository.findById(contentId)
            .map { ContentDtoConverter.convert(it) }
            .orElse(null)
            ?: throw NotFoundException("Content id: $id doesn't exist")
    }

    @Transactional(readOnly = true)
    fun getContents(pagination: PaginationDto): PageDto<ContentDto> {
        val page = contentRepository.findAll(pagination.toPageable())
        return ContentDtoConverter.convert(page)
    }

    @Transactional
    fun createContent(dto: ContentDto): Long? {
        val creatorId = dto.creatorId ?: throw BadRequestException("Author id must not be null")
        // Find creator in database and throw exception if it does not exist.
        val creator = creatorRepository.findById(creatorId).orElse(null)
            ?: throw BadRequestException("Author ${dto.creatorId} does not exist")

        // Create content.
        val content = ContentDtoConverter.revert(dto)
        content.creator = creator
        return contentRepository.save(content).id
    }

    @Transactional
    fun updateContent(dto: ContentDto) {
        val contentId = dto.id ?: throw BadRequestException("Content id must not be null")

        contentRepository.findById(contentId).orElse(null)
            ?: throw NotFoundException()

        val content = ContentDtoConverter.revert(dto)

        // Update creator if it was changed.
        if(dto.creatorId != null && dto.creatorId == content.creator?.id) {
            val creator = creatorRepository.findById(dto.creatorId!!).orElse(null)
                ?: throw BadRequestException()
            content.creator = creator
        }
        contentRepository.save(content)
    }

    @Transactional
    fun deleteContent(id: Long?) {
        val contentId = id ?: throw BadRequestException("Content id must not be null")

        val content = contentRepository.findById(contentId).orElse(null) ?: throw NotFoundException()

        contentRepository.delete(content)
    }
}
