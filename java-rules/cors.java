class PermissiveCorsAllowedOriginPatternTest {

  public void bad_direct() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern("*");
  }

  public void bad_indirect_after_other_calls() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    // ruleid: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern("*");
    config.addAllowedHeader("*");
  }

  public void bad_static_helper(CorsConfiguration config) {
    // ruleid: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern("*");
  }

  public void ok_specific_origin() {
    CorsConfiguration config = new CorsConfiguration();
    // ok: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern("https://example.com");
  }

  public void ok_pattern_not_wildcard() {
    CorsConfiguration config = new CorsConfiguration();
    // ok: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern("https://*.example.com");
  }

  public void ok_other_method() {
    CorsConfiguration config = new CorsConfiguration();
    // ok: permissive-cors-allowed-origin-pattern
    config.addAllowedHeader("*");
  }

  public void ok_star_in_other_context() {
    String s = "*";
    CorsConfiguration config = new CorsConfiguration();
    // ok: permissive-cors-allowed-origin-pattern
    config.addAllowedOriginPattern(s);
  }
}