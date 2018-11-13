package fr.hoonew.server.controller

import fr.hoonew.server.service.ContentService
import fr.hoonew.server.service.dto.ContentDto
import fr.hoonew.server.service.dto.common.PageDto
import fr.hoonew.server.service.dto.common.PaginationDto
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@WebMvcTest(ContentController::class)
class ContentControllerTest {

    private lateinit var mockMvc: MockMvc

    @InjectMocks
    private lateinit var contentController: ContentController
    @Mock
    private lateinit var contentService: ContentService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        mockMvc = MockMvcBuilders.standaloneSetup(contentController).setMessageConverters(
            MappingJackson2HttpMessageConverter()
        ).build()
    }

    @Test
    fun getContent() {
        Mockito
            .`when`(contentService.getContent(1L))
            .thenReturn((ContentDto(1L)))

        mockMvc.perform(MockMvcRequestBuilders.get("/content/2")).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun getContents() {
        Mockito
            .`when`(contentService.getContents(PaginationDto()))
            .thenReturn(PageDto(listOf(ContentDto(1L)), 1, 1))

        mockMvc.perform(MockMvcRequestBuilders.get("/content")).andExpect(MockMvcResultMatchers.status().isOk)
    }
}
