package Estacionamiento;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Estacionamiento {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			int m = scan.nextInt();		// cantidad maxima de cuadras en cada direccion
			List<Double> izq = new ArrayList<>();
			List<Double> der = new ArrayList<>();
			for (int i = 0; i < m; i++) {
				double prob = scan.nextDouble();
				izq.add(prob);
			}
			for (int i = 0; i < m; i++) {
				double prob = scan.nextDouble();
				der.add(prob);
			}
			Map<Integer, Double> mapProm = new HashMap<>();
			obtenerPromedio(mapProm, izq, der, )
		}
	}
}
