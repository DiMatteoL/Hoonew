package fr.hoonew.server.repository

import fr.hoonew.server.model.Content
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepository : PagingAndSortingRepository<Content, Long>