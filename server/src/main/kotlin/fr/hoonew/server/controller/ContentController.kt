package fr.hoonew.server.controller

import fr.hoonew.server.service.ContentService
import fr.hoonew.server.service.dto.ContentDto
import fr.hoonew.server.service.dto.common.PageDto
import fr.hoonew.server.service.dto.common.PaginationDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

/**
 * Rest endpoint for Contents.
 */
@Path("/content")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Component
class ContentController {

    @Autowired
    private lateinit var contentService: ContentService

    @GET
    @Path("/{id}")
    fun getContent(@PathParam("id") id: Long) = contentService.getContent(id)

    @GET
    fun getContents(@BeanParam pagination: PaginationDto): PageDto<ContentDto> = contentService.getContents(pagination)

    @POST
    fun createContent(dto: ContentDto): Long? = contentService.createContent(dto)

    @POST
    @Path("/{id}")
    fun updateContent(@PathParam("id") id: Long, dto: ContentDto) = contentService.updateContent(id, dto)

    @DELETE
    @Path("/{id}")
    fun deleteContent(@PathParam("id") id: Long) = contentService.deleteContent(id)
}