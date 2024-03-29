package com.sumup.hackathonqrcode

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class HackathonQrcodeApplication {

	@Bean
	fun gsonBuilder(): GsonBuilder = GsonBuilder()
			.excludeFieldsWithoutExposeAnnotation()
			.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)

}

fun main(args: Array<String>) {
	runApplication<HackathonQrcodeApplication>(*args)
}
