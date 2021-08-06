package com.ecommerce.rodolpho.controller.form;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class LoginForm {

	private String email;
	private String senha;

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(email, senha);
	}

}
