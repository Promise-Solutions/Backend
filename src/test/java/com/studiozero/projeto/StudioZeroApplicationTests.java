package com.studiozero.projeto;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(properties = "springdoc.api-docs.enabled=false")
class StudioZeroApplicationTests {

	@Test
	void contextLoads() {
	}

}
