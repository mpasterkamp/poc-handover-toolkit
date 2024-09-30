package nl.axxes.poc_handover_toolkit;

import org.springframework.boot.SpringApplication;

public class TestPocHandoverToolkitApplication {

	public static void main(String[] args) {
		SpringApplication.from(PocHandoverToolkitApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
