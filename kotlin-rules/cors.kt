// Примеры @CrossOrigin

@CrossOrigin
class Vuln1 {}

@CrossOrigin()
class Vuln2 {}

@CrossOrigin(
    allowCredentials = "true",
    maxAge = 3600)
class Vuln3 {}

@CrossOrigin(
    origins = ["https://example.com:8443", "*"]
)
class Vuln4 {}

@CrossOrigin(
    originPatterns = ["*"]
)
class Vuln5 {

}

@CrossOrigin(originPatterns = ["*"], allowCredentials = "true")
fun getProfile() = "Sensitive User Data"

@CrossOrigin
class OpenController1 {
    @CrossOrigin("*")
    fun getAll1() {}

    @CrossOrigin(origins = ["*"])
    fun getAll2() {}

    @CrossOrigin(origins = "*")
    fun getAll3() {}

    @CrossOrigin(origins = ["https://example.com"])
    fun getSafe() {}
}

@CrossOrigin(origins = "*", allowCredentials = "true")
class OpenController2 {
    fun getAll() {}
}


////
// Примеры уязвимой конфигурации через методы registry.addMapping
////

fun addCorsMappings(registry: CorsRegistry) {

    registry.addMapping("/**").allowedOrigins("*")

    registry.addMapping("/api/**")
        .allowedOrigins("*")
        .allowedMethods("GET", "POST")

    registry.addMapping("/pattern/**")
        .allowedOriginPatterns("*")
}


fun dynamicOrigin() {
    val anyOrigin = "*"
    registry.addMapping("/**").allowedOrigins(anyOrigin)
}

// ok
fun safeCorsMappings(registry: CorsRegistry) {
    registry.addMapping("/safe/**")
        .allowedOrigins("https://example.com")
        .allowedMethods("GET", "POST")
}

override fun safeCorsPatternMappings(registry: CorsRegistry) {
    registry.addMapping("/api/**")
        .allowedOriginPatterns("*")
}

////
// Примеры уязвимой конфигурации через методы: addAllowedOrigin, applyPermitDefaultValues, allowedOrigins, allowedOriginPatterns, addAllowedOriginPattern, setAllowedOrigins, setAllowedOriginPatterns
////

fun corsfilter_addAllowedOrigin() {
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedOrigin("*")
}

fun corsfilter_addAllowedOrigin() {
    val config = CorsConfiguration()
    config.allowCredentials = true
    config.addAllowedOriginPattern("*")
}

fun corsfilter_applyPermitDefaultValues(): UrlBasedCorsConfigurationSource {
    val config = CorsConfiguration().applyPermitDefaultValues()
}

fun corsFilter_allowedOrigins(): CorsFilter {
    val config = CorsConfiguration()
    config.allowedOrigins  = listOf("*") 
}

fun corsFilter_allowedOriginPatterns_list(): CorsFilter {
    val config = CorsConfiguration()
    config.allowedOriginPatterns = listOf("*") 
    config.allowCredentials = true               
}


fun corsFilter_setAllowedOrigins(): CorsFilter {
    val config = CorsConfiguration()
    config.setAllowedOrigins(listOf("*"))
}

fun corsFilter_setAllowedOriginPatterns(): CorsFilter {
    val config = CorsConfiguration()
    config.setAllowedOriginPatterns(listOf("*"))
}

// Safe examples

@CrossOrigin(
    origins = ["https://example.com:8443"]
)
class Safe1 {}

@CrossOrigin(
    originPatterns = ["https://*.example.com"]
)
class Safe2 {}
