package com.smartfit.app.smartfitmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.smartfit.app.smartfitmanager.pageobjects.LoginPage;

@SpringBootTest
// Validación del login
class SmartfitManagerApplicationTests {

	@Autowired
	LoginPage loginPage;

	@Test
	void navigateToHomePage() throws InterruptedException {
		loginPage.navigate();
		loginPage.login("yerko.munoze@smartfit.cl", "1234");
	}

}
