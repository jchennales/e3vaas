package com.citigroup.demo.poc.pvd;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.citigroup.demo.poc.pvd.model.MBoolean;
import com.citigroup.demo.poc.pvd.service.SwiftValidator;

@SpringBootTest
public class SwiftMesaagvalidationtest {

	@Autowired
	private SwiftValidator swiftValidator;
	@Test
	public void test () {
		MBoolean mboolean = swiftValidator.validate(mt101String);
		System.out.println(mboolean.value());
		System.out.println(mboolean.getMessage());
		System.out.println(mt101String);
			
	}
	
	private static final String mt101String =
            "{1:F01COPZBEB0AXXX0377002843}{2:O1011519110804LRLRXXXX4A1100009044661108041720N}{3:{108:MT101 006 OF 020}{433:/AOK/NO HIT DETECTED     }}{4:\n" +
                    ":20:00043\n" +
                    ":28D:1/1\n" +
                    ":50F:/409074-293-45/786\n" +
                    "1/George Philips\n" +
                    "2/High Street 1\n" +
                    "3/GB/London\n" +
                    ":30:011231\n" +
                    ":21:PQR-27ZZ-01\n" +
                    ":32B:USD2564,50\n" +
                    ":57D:/C/Clementine Nuggets-1842-Y\n" +
                    "MynR49R RailRoad Trust\n" +
                    "Cloudsboro ARTUI\n" +
                    ":59F:1/Beneficiary Name-1234567891234123\n" +
                    "2/QWERT\n" +
                    "3/US/Beneficiary Address Line 21\n" +
                    "3/Beneficiary Name-1234567891234123\n" +
                    ":71A:OUR\n" +
                    "-}{5:{MAC:00000000}{CHK:19DA346889CC}{TNG:}}{S:{SAC:}{COP:P}}";
}
