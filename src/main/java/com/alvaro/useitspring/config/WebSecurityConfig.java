package com.alvaro.useitspring.config;

import com.alvaro.useitspring.models.dao.IAdminDao;
import com.alvaro.useitspring.models.dao.IUserDao;
import com.alvaro.useitspring.models.entity.Admin;
import com.alvaro.useitspring.models.entity.User;
import com.alvaro.useitspring.models.service.UserService;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${allowed-origins}")
	String[] allowedOrigins;
	@Value("${default-password}")
	String defaultPassword;
	@Autowired
	IUserDao userDao;
	@Autowired
	IAdminDao adminDao;

	@Bean
	public UserDetailsService userDetailsService() {
		return new UserService();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		if (!adminDao.existsById("admin")) {
			Admin admin = new Admin();
			admin.setUsername("admin");
			admin.setPassword(defaultPassword);
			admin.encodePassword();
			adminDao.save(admin);
		}
		if (!userDao.existsById("alvaro")) {
			User user = new User();
			user.setUsername("alvaro");
			user.setPassword(defaultPassword);
			user.encodePassword();
			userDao.save(user);
		}
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests().antMatchers(HttpMethod.GET, "/agenda")
				.permitAll().antMatchers(HttpMethod.POST, "/crear-agenda*/**").hasRole("ADMIN").antMatchers(HttpMethod.GET, "/agenda*/**").hasRole("USER").and()
				.httpBasic().realmName("UseIt").and().cors().and().csrf().disable().headers().frameOptions().sameOrigin();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList(allowedOrigins));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
		corsConfiguration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return source;
	}

	@Bean
	public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
		return new SecurityEvaluationContextExtension();
	}
}
