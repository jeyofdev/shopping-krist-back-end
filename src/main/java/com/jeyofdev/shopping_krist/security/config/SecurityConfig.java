package com.jeyofdev.shopping_krist.security.config;


import com.jeyofdev.shopping_krist.core.constants.ApiRoutes;
import com.jeyofdev.shopping_krist.core.enums.RoleEnum;
import com.jeyofdev.shopping_krist.security.filter.JwtAuthenticationFilter;
import com.jeyofdev.shopping_krist.security.handler.AccessDeniedHandler;
import com.jeyofdev.shopping_krist.security.handler.JwtAuthenticationErrors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationErrors jwtAuthenticationErrors;
    private final AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            //use the CORS configuration of our implementation
            .cors(cors -> cors.configure(http))

            // disable session management
            .sessionManagement(session -> session .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // disable CSRF
            .csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).ignoringRequestMatchers("/**").disable())

            // Liste des routes protégées / non protégées
            .authorizeHttpRequests((requests) -> requests
                    .requestMatchers(HttpMethod.GET,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.USER,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ADDRESS,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.COMMENT,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE
                    ).hasRole(RoleEnum.ADMIN.name())

                    .requestMatchers(HttpMethod.GET,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.USER + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ADDRESS + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.COMMENT + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CITY,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CITY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE + "/**"
                    ).hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())

                    .requestMatchers(HttpMethod.GET,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT + "/**"
                    ).permitAll()

                    .requestMatchers(HttpMethod.POST,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.COMMENT + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER + "/**"
                    ).hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())

                    .requestMatchers(HttpMethod.POST,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CITY,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT
                    ).hasRole(RoleEnum.ADMIN.name())

                    .requestMatchers(HttpMethod.POST,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ADDRESS + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT + "/{productId}" + ApiRoutes.PROFILE + "{profileId}",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS + "/**"
                    ).hasRole(RoleEnum.USER.name())

                    .requestMatchers(HttpMethod.PUT,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CITY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT + "/**"
                    ).hasRole(RoleEnum.ADMIN.name())

                    .requestMatchers(HttpMethod.PUT,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS + "/**"
                    ).hasRole(RoleEnum.USER.name())

                    .requestMatchers(HttpMethod.PUT,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ADDRESS + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.COMMENT + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER + "/**"
                    ).hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())

                    .requestMatchers(HttpMethod.DELETE,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CATEGORY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CITY + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PRODUCT + "/**"
                    ).hasRole(RoleEnum.ADMIN.name())

                    .requestMatchers(HttpMethod.DELETE,
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ADDRESS + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.COMMENT + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.CART_ITEM + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.NOTIFICATION + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.ORDER + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE + "/**",
                            ApiRoutes.BASE_API_V1 + ApiRoutes.PROFILE_SETTINGS + "/**"
                    ).hasAnyRole(RoleEnum.ADMIN.name(), RoleEnum.USER.name())

                    .requestMatchers(ApiRoutes.BASE_API_V1 + ApiRoutes.AUTH + "/**").permitAll()

                    .anyRequest().permitAll()
            )

            // authentication errors
            .exceptionHandling((exception) ->  exception
                    .authenticationEntryPoint(jwtAuthenticationErrors)
                    .accessDeniedHandler(accessDeniedHandler)
            )

            // specify the authentication provider used
            .authenticationProvider(authenticationProvider)

            // add the JWT authentication filter
            // before the UsernamePasswordAuthenticationFilter
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();

    }
}
