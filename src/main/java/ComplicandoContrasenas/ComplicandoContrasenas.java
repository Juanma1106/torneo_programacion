package ComplicandoContrasenas;

import java.util.Scanner;

public class ComplicandoContrasenas {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			int x = scan.nextInt();
			int y = scan.nextInt();
			int z = scan.nextInt();
			int w = scan.nextInt();
			//
			// Secuencia abuelo Laino
			int max = x > y ? x : y;
			max = max > z ? max : z;
			max = max > w ? max : w;
			//String secuencia = generarSecuenciaAbueloLaino(max);
			//System.out.println(secuencia);
			// Ejecutando para valor 100, vemos un patron:
			// 4624832612248326122483261224832612248326122483261224832612248326122483261224832612248326122483261224
			// 46 24832612 24832612 24832612 24832612 24832612 24832612 248326122483261224832612248326122483261224832612
			System.out.print(getNumberInPos(x));
			System.out.print(getNumberInPos(y));
			System.out.print(getNumberInPos(z));
			System.out.print(getNumberInPos(w));
		}
	}

	private static int getNumberInPos(int pos) {
		if (pos == 1) {
			return 4;
		}
		if (pos == 2) {
			return 6;
		}
		switch ((pos - 2) % 8) {
		case 0:
			return 2;
		case 1:
			return 2;
		case 2:
			return 4;
		case 3:
			return 8;
		case 4:
			return 3;
		case 5:
			return 2;
		case 6:
			return 6;
		case 7:
			return 1;
		}
		return 0;
	}

	private static String generarSecuenciaAbueloLaino(long num) {
		StringBuilder secuencia = new StringBuilder();
		secuencia.append("4");
		if (num == 1L) {
			return secuencia.toString();
		}
		secuencia.append("6");
		if (num == 2L) {
			return secuencia.toString();
		}
		generarSecuenciaAbueloLainoAux(secuencia, 4, 6, num - 2);
		return secuencia.toString();
	}

	private static void generarSecuenciaAbueloLainoAux(StringBuilder secuencia, int last1, int last2, long num) {
		if (num == 0) {
			return;
		}
		int newNumber = last1 * last2;
		int newLast1;
		int newLast2;
		if (newNumber > 9) {
			newLast1 = newNumber / 10;
			newLast2 = newNumber % 10;
			if (num >= 2) {
				secuencia.append(newNumber);
				num = num - 2;
			} else {
				secuencia.append(newLast1);
				num--;
			}
		} else {
			newLast1 = last2;
			newLast2 = newNumber;
			num--;
			secuencia.append(newNumber);
		}
		generarSecuenciaAbueloLainoAux(secuencia, newLast1, newLast2, num);
	}
}
