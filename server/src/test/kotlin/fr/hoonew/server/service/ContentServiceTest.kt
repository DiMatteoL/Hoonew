package fr.hoonew.server.service

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import fr.hoonew.server.model.Content
import fr.hoonew.server.model.Creator
import fr.hoonew.server.repository.ContentRepository
import fr.hoonew.server.repository.CreatorRepository
import fr.hoonew.server.service.dto.ContentDto
import org.junit.Assert.assertEquals
import org.springframework.test.context.junit4.SpringRunner
import java.util.Optional
import javax.ws.rs.BadRequestException
import javax.ws.rs.NotFoundException

/**
 * Content service test class.
 */
@RunWith(SpringRunner::class)
class ContentServiceTest {

    @Mock
    private lateinit var creatorRepository: CreatorRepository
    @Mock
    private lateinit var contentRepository: ContentRepository
    @InjectMocks
    private lateinit var contentService: ContentService

    @Test
    fun testGetContent() {
        /* Given */
        val contentId = 1L
        whenever(contentRepository.findById(contentId)).thenReturn(Optional.of(Content(id = contentId)))

        /* When */
        val result = contentService.getContent(contentId)

        /* Then */
        assertEquals(contentId, result.id)
    }

    @Test(expected = NotFoundException::class)
    fun testGetContentIsNotFound() {
        /* Given */
        val contentId : Long? = 2L
        whenever(contentRepository.findById(contentId!!)).thenReturn(Optional.empty())

        /* When */
        contentService.getContent(contentId)
    }

    @Test(expected = BadRequestException::class)
    fun createWithoutAuthor() {
        /* Given */
        val contentId = 2L
        val creatorId = 5L
        whenever(contentRepository.save(any<Content>())).thenReturn(Content(id = contentId))
        whenever(creatorRepository.findById(creatorId)).thenReturn(Optional.empty())

        contentService.createContent(ContentDto(creatorId = creatorId))
    }

    @Test
    fun createContent() {
        /* Given */
        val contentId = 2L
        val creatorId = 5L
        whenever(contentRepository.save(any<Content>())).thenReturn(Content(id = contentId))
        whenever(creatorRepository.findById(5)).thenReturn(Optional.of(Creator(id = creatorId)))

        /* When */
        val result = contentService.createContent(ContentDto(creatorId = creatorId))

        /* Then */
        assertEquals(contentId, result)
    }

    @Test
    fun updateContent() {
        /* Given */
        val contentId = 1L;
        val content = Content(id = contentId)
        whenever(contentRepository.findById(contentId)).thenReturn(Optional.of(content))

        /* When */
        contentService.updateContent(contentId, ContentDto())

        /* Then */
        verify(contentRepository).findById(contentId)
    }
}
