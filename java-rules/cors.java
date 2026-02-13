class PermissiveCorsAllowedOriginPatternTest {

  public void bad_pattern() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-allowedOrigin
    config.addAllowedOriginPattern("*");
  }

  public void ok_pattern_1() {
    CorsConfiguration config = new CorsConfiguration();
    // ok
    config.addAllowedOriginPattern("https://example.com");
  }

  public void ok_pattern_2() {
    CorsConfiguration config = new CorsConfiguration();
    // ok
    config.addAllowedOriginPattern("https://*.example.com");
  }

  public void star_in_context() {
    String s = "*";
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-allowedOrigin
    config.addAllowedOriginPattern(s);
  }

  public void bad_add_allowed_origin() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-allowedOrigin
    config.addAllowedOrigin("*");
  }

  public void ok_add_allowed_origin() {
    CorsConfiguration config = new CorsConfiguration();
    // ok
    config.addAllowedOrigin("https://example.com");
  }

  public void bad_set_allowed_origins_listof() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-setAllowedOrigin-list
    config.setAllowedOrigins(List.of("*"));
  }

  public void bad_set_allowed_origins_arrays() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-setAllowedOrigin-list
    config.setAllowedOrigins(Arrays.asList("*"));
  }

  public void ok_set_allowed_origins() {
    CorsConfiguration config = new CorsConfiguration();
    // ok
    config.setAllowedOrigins(List.of("https://example.com"));
  }

  public void bad_set_allowed_origin_patterns() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-setAllowedOriginPatterns-list
    config.setAllowedOriginPatterns(List.of("*"));
  }

  public void ok_set_allowed_origin_patterns() {
    CorsConfiguration config = new CorsConfiguration();
    // ok
    config.setAllowedOriginPatterns(List.of("https://*.example.com"));
  }

  public void bad_registry_allowed_origins(CorsRegistry config) {
    // ruleid: cors-allowedOrigin
    config.allowedOrigins("*");
  }

  public void ok_registry_allowed_origins(CorsRegistry config) {
    // ok
    config.allowedOrigins("https://example.com");
  }

  public void bad_registry_add_mapping(CorsRegistry registry) {
    // ruleid: cors-allowedOrigin
    registry.addMapping("/api/**")
            .allowedOrigins("*");
  }

  public void ok_registry_add_mapping(CorsRegistry registry) {
    // ok
    registry.addMapping("/api/**")
            .allowedOrigins("https://example.com");
  }

  public void bad_apply_permit_default_values() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: cors-allowedOrigin
    config.applyPermitDefaultValues();
  }
}
