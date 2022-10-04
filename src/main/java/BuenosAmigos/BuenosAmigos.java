package BuenosAmigos;

import java.util.Scanner;

public class BuenosAmigos {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			long d = scan.nextLong(); // distancia a libros
			int m = scan.nextInt(); // cantidad amigos
			long d1 = scan.nextLong(); // distancia maxima recorrida por amigos
			int n = scan.nextInt(); // conocidos
			long d2 = scan.nextLong(); // distancia maxima recorrida por conocidos
			scan.nextLine();
			long cant = contarGenteNecesaria(d, m, d1, n, d2);
			System.out.println(cant);
		}
	}

	static long contarGenteNecesaria(long d, int m, long d1, int n, long d2) {
		// Amigos pueden recorrer 102
		// Distancia inicial: 100
		// acerca1 = 102 - 100 = 2.	nueva dist: 98. acum: 2		= 2^1								= 2^2 - 2
		// acerca2 = 102 - 98 = 4.	nueva dist: 94. acum: 6 	= 2^1 + 2^2							= 2^3 - 2
		// acerca3 = 102 - 94 = 8.	nueva dist: 86. acum: 14 	= 2^1 + 2^2 + 2^3					= 2^4 - 2
		// acerca4 = 102 - 86 = 16. nueva dist: 70. acum: 30 	= 2^1 + 2^2 + 2^3 + 2^3				= 2^5 - 2
		// acerca5 = 102 - 70 = 32. nueva dist: 38. acum: 62 	= 2^1 + 2^2 + 2^3 + 2^4 + 2^5		= 2^6 - 2
		// acerca6 = 102 - 38 = 64. nueva dist: 0.  acum: 100 	= 2^1 + 2^2 + 2^3 + 2^4 + 2^5 + 2^6	= 2^7 - 2
		// 
		// Queremos d - acum <= 0
		if (d1 < d) {
			// Es imposible
			return -1;
		}
		long acercamiento = d1 - d;
		Double genteNecesariaAux = Math.floor(Math.log(d) / Math.log(acercamiento));
		if (genteNecesariaAux > m) {
			d = (long) (Math.pow(d1 - d, m + 1) - (d1 - d));
		} else {
			return Math.round(genteNecesariaAux);
		}
		if (d2 < d) {
			// Es imposible
			return -1;
		}
		acercamiento = d2 - d;
		genteNecesariaAux = Math.floor(Math.log(d) / Math.log(acercamiento));
		if (genteNecesariaAux > m) {
			return -1;
		} else {
			return Math.round(genteNecesariaAux) + m;
		}
	}

	static long contarGenteNecesaria2(long d, int m, long d1, int n, long d2) {
		int genteNecesaria = 0;
		if (d1 < d) {
			// Es imposible
			return -1;
		}
		while (m > 0) {
			m--;
			genteNecesaria++;
			d = 2 * d - d1;
			if (d <= 0) {
				return genteNecesaria;
			}
		}
		if (d2 < d) {
			// Es imposible
			return -1;
		}
		while (n > 0) {
			n--;
			genteNecesaria++;
			d = 2 * d - d2;
			if (d <= 0) {
				return genteNecesaria;
			}
		}
		return -1;
	}
}
