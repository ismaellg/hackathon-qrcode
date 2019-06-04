package com.sumup.hackathonqrcode

import net.glxn.qrgen.javase.QRCode
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import java.util.*
import javax.servlet.http.HttpServletRequest


@RestController
@RequestMapping("/config")
class ConfigEndpoint {

    @Autowired
    private lateinit var repository: ConfigurationRepository

    @Autowired
    private lateinit var request: HttpServletRequest


    @GetMapping("{code}")
    fun get(@PathVariable("code") code: String): ResponseEntity<Configuration?> {
        val configuration = repository.findByCode(code)
        val httpStatus = if(configuration != null) HttpStatus.OK else HttpStatus.NOT_FOUND
        return ResponseEntity(configuration, httpStatus)
    }

    @PostMapping
    fun post(@RequestBody config: Configuration): ResponseEntity<InputStreamResource> {
        val entity = repository.save(config.copy(id = 0, code = generateCode()))
        val url = "${request.scheme}://${request.serverName}:${request.serverPort}/config/${entity.code}"
        val bytes = QRCode.from(url).withSize(300, 300).stream().toByteArray()
        val inputStreamResource = InputStreamResource(ByteArrayInputStream(bytes))
        return ResponseEntity(inputStreamResource, HttpStatus.OK)
    }

    private fun generateCode(): String {
        val letters = ('A'..'Z').joinToString(separator = "")
        val random = Random()
        return (0 until 5).map { letters[random.nextInt(letters.length)] }.joinToString(separator = "")
    }

}