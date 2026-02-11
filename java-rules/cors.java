class PermissiveCorsAllowedOriginPatternTest {

  public void bad_pattern() {
    CorsConfiguration config = new CorsConfiguration();
    // ruleid
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
    // ruleid
    config.addAllowedOriginPattern(s);
  }
}