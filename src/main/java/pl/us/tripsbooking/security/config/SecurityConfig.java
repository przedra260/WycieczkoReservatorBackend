package pl.us.tripsbooking.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import pl.us.tripsbooking.security.filters.JsonObjectAuthenticationFilter;
import pl.us.tripsbooking.security.filters.JwtAuthorizationFilter;
import pl.us.tripsbooking.security.handlers.RestAuthenticationFailureHandler;
import pl.us.tripsbooking.security.handlers.RestAuthenticationSuccessHandler;
import pl.us.tripsbooking.security.utils.Base64PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${jwt.secret}")
    private String secret;


    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService).passwordEncoder(new Base64PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/test/**",
                        "/account/**",
                        "/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/trips/saveTrip").hasRole("ADMIN")
                .antMatchers("/trips/deleteTrip").hasRole("ADMIN")
                .antMatchers("/trips/assignGuide").hasRole("ADMIN")
                .antMatchers("/users/getAllUsers").hasRole("ADMIN")
                .antMatchers("/users/getAllGuides").hasRole("ADMIN")
                .antMatchers("/users/getAvailableGuides").hasRole("ADMIN")
                .antMatchers("/trips/book/cancel").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/getTripDetails").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/getBookedTripDetails").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/getUserTrips").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/book").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/paid").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/bookedTrips").hasAnyRole("USER", "ADMIN")
                .antMatchers("/users/getUserInfo").hasAnyRole("USER", "ADMIN")
                .antMatchers("/users/recharge-balance/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/trips/participants").hasAnyRole("TRIPS_GUIDE", "ADMIN")
                .antMatchers("/trips/getGuideTrips").hasAnyRole("TRIPS_GUIDE", "ADMIN")
                .antMatchers("/trips/getAllTrips").authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtAuthorizationFilter(authenticationManager(), secret, userDetailsService))
                .cors();
    }

    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {
        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(super.authenticationManagerBean());
        return filter;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager mgr = new JdbcUserDetailsManager();
        mgr.setDataSource(dataSource);
        return mgr;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();

        config.addAllowedMethod(HttpMethod.GET);
        config.addAllowedMethod(HttpMethod.POST);
        config.addAllowedMethod(HttpMethod.DELETE);
        config.addAllowedMethod(HttpMethod.PATCH);
        config.addAllowedMethod(HttpMethod.PUT);

        config.addAllowedOrigin(CorsConfiguration.ALL);

        config.addAllowedHeader(CorsConfiguration.ALL);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}