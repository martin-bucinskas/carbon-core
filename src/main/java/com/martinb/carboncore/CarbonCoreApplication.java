package com.martinb.carboncore;

import com.martinb.carboncore.entities.transactions.BasicTransaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarbonCoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarbonCoreApplication.class, args);

		BasicTransaction basicTx = new BasicTransaction() {};

		System.out.println(basicTx.toString());
	}

}
