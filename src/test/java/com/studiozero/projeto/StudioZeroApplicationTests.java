package com.studiozero.projeto;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Disabled("Desativado temporariamente — causa erro por conta das variáveis de ambiente que não são reconhecidas MySQL/RabbitMQ")
@ActiveProfiles("test")
@SpringBootTest(properties = "springdoc.api-docs.enabled=false")
class StudioZeroApplicationTests {

	@Test
	void contextLoads() {
	}

}
