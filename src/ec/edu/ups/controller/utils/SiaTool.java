package ec.edu.ups.controller.utils;

import java.nio.charset.StandardCharsets;

import com.google.common.hash.Hashing;

public class SiaTool {
	
	private SiaTool() {
		
	}
	
	public static double getTrunkDecimal(double n) {
		n = Math.round(n * 100.0)/100.0;
		return n;
	}
	
	public static String getSha256(String input) {
        return Hashing.sha256()
        		.hashString(input, StandardCharsets.UTF_8)
				.toString();
    }
	
	public static boolean validateDNI(String dni) {
		boolean cedulaCorrecta = false;
		try {
			if (dni.length() == 10) {
				int tercerDigito = Integer.parseInt(dni.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(dni.substring(9,10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (dni.length() - 1); i++) {
						digito = Integer.parseInt(dni.substring(i, i + 1))* coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
		
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					}
					else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
					cedulaCorrecta = false;
			}
		} catch (Exception err) {
			cedulaCorrecta = false;
		}
		
		return cedulaCorrecta;
	}
	
}
