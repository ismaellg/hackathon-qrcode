package com.sumup.hackathonqrcode

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ConfigurationRepository: CrudRepository<Configuration, Int> {

    fun findByCode(code: String): Configuration?

}