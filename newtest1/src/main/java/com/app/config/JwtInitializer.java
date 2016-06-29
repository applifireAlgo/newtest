package com.app.config;
import com.app.server.repository.appbasicsetup.aaa.JwtConfigRepository;

import com.app.shared.appbasicsetup.aaa.JwtConfig;


public class JwtInitializer {

	JwtConfigRepository<JwtConfig> astJWTConfigRepository;

	public JwtInitializer(JwtConfigRepository<JwtConfig> astJWTConfigRepository) {
		this.astJWTConfigRepository = astJWTConfigRepository;
	}

	public String getKey() throws Exception {

		try {
			JwtConfig astJWTConfig = astJWTConfigRepository.findAll().get(0);
			return astJWTConfig.getTokenKey();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}
}
