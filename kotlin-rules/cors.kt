package com.example.cors

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

////////////////////////////////////////////////////////////////////////////////
// 1️⃣ Тесты для kotlin-spring-cors
////////////////////////////////////////////////////////////////////////////////

class CorsConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        // ❌ Wildcard literal — должно матчиться
        registry.addMapping("/**").allowedOrigins("*")

        // ❌ Разбитая цепочка вызовов — должно матчиться
        registry.addMapping("/api/**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST")

        // ❌ allowedOriginPatterns("*") — должно матчиться
        registry.addMapping("/pattern/**")
            .allowedOriginPatterns("*")
    }

    fun dynamicOriginExample() {
        val anyOrigin = "*"
        // ❌ Переменная с "*" — должно матчиться
        registry.addMapping("/dynamic/**").allowedOrigins(anyOrigin)
    }

    // ✅ Безопасный пример — не должно матчиться
    override fun addSafeCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/safe/**")
            .allowedOrigins("https://example.com")
            .allowedMethods("GET", "POST")
    }
}

////////////////////////////////////////////////////////////////////////////////
// 2️⃣ Тесты для kotlin-cors-CrossOrigin
////////////////////////////////////////////////////////////////////////////////

// ❌ Wildcard default — должно матчиться
@CrossOrigin
class OpenController1 {

    // ❌ Wildcard string — должно матчиться
    @CrossOrigin("*")
    fun getAll1() {}

    // ❌ Array wildcard — должно матчиться
    @CrossOrigin(origins = ["*"])
    fun getAll2() {}

    // ❌ String wildcard — должно матчиться
    @CrossOrigin(origins = "*")
    fun getAll3() {}

    // ✅ Безопасный origin — не должно матчиться
    @CrossOrigin(origins = ["https://example.com"])
    fun getSafe() {}
}

// ❌ Critical: wildcard + credentials — должно матчиться, если правило есть
@CrossOrigin(origins = "*", allowCredentials = "true")
class OpenController2 {
    fun getAll() {}
}

// ✅ Безопасный: wildcard с ограничением origin — не должно матчиться
@CrossOrigin(origins = ["https://mycompany.com"], allowCredentials = "true")
class SafeController {
    fun getAll() {}
}

    fun insecureCors() {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("*") // ruleid: kotlin-cors-allowed-origins-wildcard
    }

    fun insecureCorsMultiple() {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("https://example.com", "*") // ruleid: kotlin-cors-allowed-origins-wildcard
    }

    fun secureCors() {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf("https://example.com") // ok: kotlin-cors-allowed-origins-wildcard
    }

    fun secureEmpty() {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf() // ok: kotlin-cors-allowed-origins-wildcard
    }
