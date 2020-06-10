package it.uniroma3.siw.nw.authentication;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * The AuthConfiguration is a Spring Security Configuration.
 * It extends WebSecurityConfigurerAdapter, meaning that it provides the settings for Web security.
 * @author Agerincolt
 */
@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

	/**
	 * The datasource is automatically injected into the AuthConfiguration (using its getters and setters)
	 * and it is used to access the DB to get Credentials to perform a authentication and authorization
	 */
	@Autowired
	DataSource datasource; //datasource rappresenta un punto di accesso al database

	/**
	 * This method provides the whole authentication and authorization configuration to use.
	 */
	//in automatico il formLogin usa la get per ottenere la vista e la post per autenticarsi (la vista è preconfezionata)
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // authorization paragraph: here we define WHO can access WHICH pages
                .authorizeRequests()
                // anyone (authenticated or not) can access the welcome page, the login page, and the registration page
                .antMatchers(HttpMethod.GET, "/", "/index", "/login", "/users/register").permitAll()
                // anyone (authenticated or not) can send POST requests to the login endpoint and the register endpoint
                .antMatchers(HttpMethod.POST, "/login", "/users/register").permitAll()
                // only authenticated users with ADMIN authority can access the admin pag
                // all authenticated users can access all the remaining other pages
                .anyRequest().authenticated()

                // login paragraph: here we define how to login
                // use formlogin protocol to perform login
                .and().formLogin()
                // after login is successful, redirect to the logged user homepage
                .defaultSuccessUrl("/home")

                // NOTE: using the default configuration, the /login endpoint is mapped to an auto-generated login page.
                // If we wanted to create a login page of own page, we would need to
                // - use the .loginPage() method in this configuration
                // - write a controller method that returns our login view when a GET method is sent to /login
                //   (but Spring would still handle the POST automatically)

                // logout paragraph: we are going to define here how to logout
                .and().logout()
                .logoutUrl("/logout")               // logout is performed when sending a GET to "/logout"
                .logoutSuccessUrl("/index");        // after logout is successful, redirect to /index page
    }

	
	/**
	 * This method provides the SQL queries to get username and password.
	 * NOTE: field denoted in Java by camelCase convention
	 * 			are denoted in Postgres by snake_case convention by default
	 * 			(e.g. "userName" field in the java class results is "user_name" DB column
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication()
				//use the autowired datasource to access the saved credentials
				.dataSource(this.datasource)
				//retrieve username and role, "?" means a parameter
				.authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
				//retrieve username, password and a boolean flag specifying whether the user is enabled or not (1 means always enabled)
				.usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
	}
	
	/**
	 * This method defines a "passwordEncoder" Bean.
	 * The passwordEncoder Bean is used to encrypt and decrypt the Credentials passwords.
	 */
	
	/*Bean significa che l'oggetto gestito da quel medoto viene salvato nel contesto dell'applicazione
	 * in maniera che quando poi dovremo richiamarlo nuovamente l'applicazione non ha bisogno di reinizializzare
	 * quell'oggetto ma utilizzerà la stessa istanza che si era già salvato nel contesto. Lo si può ottenere
	 * tramite @Autowired (@see CredentialsService). In questo modo sostituiamo l'implementazione di default (nessun encoder). In automatico
	 * al login l'applicazione cifra la password passata come parametro dall'utente e la confronta con le credenziali
	 * salvate nel DB. Quello che non fa a meno di esplicita dichiarazione è salvare la password nel DB alla registrazione
	 */
	
	/*@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}*/
}