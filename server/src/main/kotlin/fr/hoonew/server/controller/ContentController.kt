package fr.hoonew.server.controller

import fr.hoonew.server.service.ContentService
import fr.hoonew.server.service.dto.ContentDto
import fr.hoonew.server.service.dto.common.PageDto
import fr.hoonew.server.service.dto.common.PaginationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import javax.ws.rs.BeanParam
import javax.ws.rs.PathParam

/**
 * Rest endpoint for Contents.
 */
@RestController
@RequestMapping("/content")
@ResponseBody
class ContentController {

    @Autowired
    private lateinit var contentService: ContentService

    @GetMapping("/{id}")
    fun getContent(@PathParam("id") id: Long?) = contentService.getContent(id)

    @GetMapping
    fun getContents(@BeanParam pagination: PaginationDto): PageDto<ContentDto> = contentService.getContents(pagination)

    @PostMapping
    fun createContent(dto: ContentDto): Long? = contentService.createContent(dto)

    @PutMapping
    fun updateContent(dto: ContentDto) = contentService.updateContent(dto)

    @DeleteMapping
    fun deleteContent(@PathParam("id") id: Long?) = contentService.deleteContent(id)
}
