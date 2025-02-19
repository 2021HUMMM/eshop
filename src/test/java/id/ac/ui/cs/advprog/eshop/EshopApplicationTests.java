package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class EshopApplicationTest {

	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		// Memastikan bahwa konteks Spring Boot berhasil dimuat
		assertThat(context).isNotNull();
	}

	@Test
	void mainMethodRunsApplication() {
		// Memastikan bahwa metode main dapat menjalankan aplikasi tanpa exception
		EshopApplication.main(new String[] {});
	}
}