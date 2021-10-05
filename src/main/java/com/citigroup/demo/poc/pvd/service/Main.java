package com.citigroup.demo.poc.pvd.service;

import com.citigroup.demo.poc.pvd.model.ValidationStatus;

public class Main {
	
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
	
    public static void main(String[] args) {

    	System.out.println(mt101String);
    	System.out.println();
    	
    	SwiftMessageValidator swiftmt101=new MT01MessageValidator();
    	ValidationStatus verification=swiftmt101.validate(mt101String);
    	System.out.println("Verification :" + verification.getMessage());
    	
    	/*ParseValidMT01.execute();
        ParseInvalidMT01.execute();
        ConvertMT2XML.execute();
        BuildMT101_1.execute();
        BuildMT101_2.execute();*/
    	
    }
}
