package com.sumup.hackathonqrcode

import com.google.gson.annotations.Expose
import javax.persistence.*
import javax.validation.constraints.NotNull

@Entity
data class Configuration(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?,
        @Column(unique = true, nullable = false) var code: String?,
        @Expose
        @Column(nullable = false) val cubeUrl: String?,
        @Expose
        @Column(nullable = false) val txGwUrl: String?,
        @Expose
        @Column(nullable = false) val dashUrl: String?,
        @Expose
        @Column(nullable = false) val posUrl: String?)