package fr.hoonew.server.repository

import fr.hoonew.server.model.Creator
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CreatorRepository : PagingAndSortingRepository<Creator, Long>