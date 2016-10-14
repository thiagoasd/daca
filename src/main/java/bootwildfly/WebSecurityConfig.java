package bootwildfly;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import models.Account;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService());

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(HttpMethod.GET, "/h2-console/**", "/Problema", "/Problema/{\\d+}");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/**").hasAnyRole("USER", "ADMIN").and().formLogin().and().csrf()
				.disable();
	}

	@Bean
	protected UserDetailsService userDetailsService() {
		return new UserDetailsService() {

			@Autowired
			AccountRepository AR;

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				if (AR.count() == 0) {
					AR.save(new Account("daca", "daca", Arrays.asList("ROLE_ADMIN", "ROLE_USER")));
					AR.save(new Account("user", "user", Arrays.asList("ROLE_USER")));
				}

				Account account = AR.findByUsername(username);

				if (account != null) {
					return new User(account.getUsername(), account.getPassword(), true, true, true, true, AuthorityUtils
							.createAuthorityList(account.getRoles().toArray(new String[account.getRoles().size()])));
				} else {
					throw new UsernameNotFoundException("could not find the user '" + username + "'");
				}
			}

		};

	}

}
