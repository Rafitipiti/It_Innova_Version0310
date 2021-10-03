package pe.edu.upc.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import pe.edu.upc.serviceimpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
http.authorizeRequests()

.antMatchers("/users/**").hasAuthority("ADMIN")
.antMatchers("/register/**").hasAuthority("ADMIN")
.antMatchers("/**/edit/**").hasAuthority("USER")
.antMatchers("/paciente/**").hasAuthority("USER")
		.antMatchers(HttpMethod.POST).permitAll()
            .and()
		    .csrf().disable()
            .formLogin().loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/inicio/bienvenido").
            usernameParameter("email").permitAll()
            .and().logout().logoutUrl("/inicio/logout").
            logoutSuccessUrl("/inicio/logout").permitAll().
            and().exceptionHandling().accessDeniedPage("/error_403");
    }
		
}

/*http.authorizeRequests()
		.antMatchers("/users/**").hasRole("ADMIN")
		.antMatchers("/paciente/**").hasRole("USER")
		.and()
		.formLogin().permitAll()
		.and()
		.formLogin().loginPage("/login").
		usernameParameter("email")
			.defaultSuccessUrl("/inicio");*/

