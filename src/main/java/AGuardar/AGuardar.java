package AGuardar;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AGuardar {

	public static void main(String[] args) {
		List<Posicion> posicionesRecorridas = new ArrayList<>();
		Posicion posInicial = null;
		Posicion posFinal = null;
		try (Scanner scan = new Scanner(System.in)) {
			int n = scan.nextInt();
			int m = scan.nextInt();
			scan.nextLine();
			char casa[][] = new char[n][m];
			for (int i = 0; i < n; i++) {
				String ceils = scan.nextLine();
				for (int j = 0; j < ceils.length(); j++) {
					casa[i][j] = ceils.charAt(j);
					if (casa[i][j] == 'E') {
						posFinal = new Posicion(i, null, j, null, "");
					}
					if (casa[i][j] == 'C') {
						posInicial = new Posicion(i, null, j, null, "");
					}
				}
			}
			String caminoParaLlegar = recorrer(casa, n, m, posicionesRecorridas, List.of(posInicial), posFinal);
			if (!caminoParaLlegar.equals("-1")) {
				System.out.println(caminoParaLlegar.length());
			}
			System.out.println(caminoParaLlegar);
		}
	}

	private static String recorrer(char[][] casa, int n, int m, List<Posicion> posicionesRecorridas,
			List<Posicion> posiciones, Posicion posFinal) {
		posicionesRecorridas.addAll(posiciones);
		List<Posicion> proximasPosiciones = new ArrayList<>();
		for (Posicion pos : posiciones) {
			List<Posicion> listPosiciones = posiblesMovimientos(casa, n, m, pos);
			for (Posicion posicion : listPosiciones) {
				if (posicion.equals(posFinal)) {
					return posicion.getCaminoRecorrido().toString();
				}
				if (!posicionesRecorridas.contains(posicion)) {
					proximasPosiciones.add(posicion);
				}
			}
		}
		if (proximasPosiciones.isEmpty()) {
			return "-1";
		}
		return recorrer(casa, n, m, posicionesRecorridas, proximasPosiciones, posFinal);
	}

	private static List<Posicion> posiblesMovimientos(char casa[][], int filas, int columnas, Posicion pos) {
		List<Posicion> listPosiciones = new ArrayList<>();
		// U
		moveUp(casa, pos, listPosiciones);
		// D
		moveDown(casa, filas, pos, listPosiciones);
		// R
		moveRight(casa, columnas, pos, listPosiciones);
		// L
		moveLeft(casa, pos, listPosiciones);
		return listPosiciones;
	}

	private static void moveLeft(char[][] casa, Posicion pos, List<Posicion> listPosiciones) {
		Posicion newPos = null;
		Integer posColumna1 = pos.getPosColumna1();
		Integer posFila1 = pos.getPosFila1();
		Integer posFila2 = pos.getPosFila2();
		switch (pos.getEstado()) {
		case PARADA:
			if (posColumna1 > 1 && casa[posFila1][posColumna1 - 1] != '#' && casa[posFila1][posColumna1 - 2] != '#') {
				// OK
				newPos = new Posicion(posFila1, null, posColumna1 - 2, posColumna1 - 1, pos.getCaminoRecorrido() + 'L');
			}
			break;
		case ACOSTADA_HORIZONTAL:
			if (posColumna1 > 0 && casa[posFila1][posColumna1 - 1] != '#') {
				// OK
				newPos = new Posicion(posFila1, null, posColumna1 - 1, null, pos.getCaminoRecorrido() + 'L');
			}
			break;
		case ACOSTADA_VERTICAL:
			if (posColumna1 > 0 && casa[posFila1][posColumna1 - 1] != '#' && casa[posFila2][posColumna1 - 1] != '#') {
				// OK
				newPos = new Posicion(posFila1, posFila2, posColumna1 - 1, null, pos.getCaminoRecorrido() + 'L');
			}
			break;
		}
		if (newPos != null) {
			listPosiciones.add(newPos);
		}
	}

	private static void moveRight(char[][] casa, int columnas, Posicion pos, List<Posicion> listPosiciones) {
		Posicion newPos = null;
		Integer posColumna1 = pos.getPosColumna1();
		Integer posColumna2 = pos.getPosColumna2();
		Integer posFila1 = pos.getPosFila1();
		Integer posFila2 = pos.getPosFila2();
		String caminoRecorrido = pos.getCaminoRecorrido() + 'R';
		switch (pos.getEstado()) {
		case PARADA:
			// Se acuesta
			if (posColumna1 < columnas - 2 && casa[posFila1][posColumna1 + 1] != '#'
					&& casa[posFila1][posColumna1 + 2] != '#') {
				// OK
				newPos = new Posicion(posFila1, null, posColumna1 + 1, posColumna1 + 2, caminoRecorrido);
			}
			break;
		case ACOSTADA_HORIZONTAL:
			// Se para
			if (posColumna2 < columnas - 1 && casa[posFila1][posColumna2 + 1] != '#') {
				// OK
				newPos = new Posicion(posFila1, null, posColumna2 + 1, null, caminoRecorrido);
			}
			break;
		case ACOSTADA_VERTICAL:
			// Sigue acostada
			if (posColumna1 < columnas - 1 && casa[posFila1][posColumna1 + 1] != '#'
					&& casa[posFila2][posColumna1 + 1] != '#') {
				// OK
				newPos = new Posicion(posFila1, posFila2, posColumna1 + 1, null, caminoRecorrido);
			}
			break;
		}
		if (newPos != null) {
			listPosiciones.add(newPos);
		}
	}

	private static void moveDown(char[][] casa, int filas, Posicion pos, List<Posicion> listPosiciones) {
		Posicion newPos = null;
		Integer posColumna1 = pos.getPosColumna1();
		Integer posColumna2 = pos.getPosColumna2();
		Integer posFila1 = pos.getPosFila1();
		Integer posFila2 = pos.getPosFila2();
		switch (pos.getEstado()) {
		case PARADA:
			if (posFila1 < filas - 2 && casa[posFila1 + 1][posColumna1] != '#'
					&& casa[posFila1 + 2][posColumna1] != '#') {
				// OK
				newPos = new Posicion(posFila1 + 1, posFila1 + 2, posColumna1, null, pos.getCaminoRecorrido() + 'D');
			}
			break;
		case ACOSTADA_HORIZONTAL:
			if (posFila1 < filas - 1 && casa[posFila1 + 1][posColumna1] != '#'
					&& casa[posFila1 + 1][posColumna2] != '#') {
				// OK
				newPos = new Posicion(posFila1 + 1, null, posColumna1, posColumna2, pos.getCaminoRecorrido() + 'D');
			}
			break;
		case ACOSTADA_VERTICAL:
			if (posFila2 < filas - 1 && casa[posFila2 + 1][posColumna1] != '#') {
				// OK
				newPos = new Posicion(posFila2 + 1, null, posColumna1, null, pos.getCaminoRecorrido() + 'D');
			}
			break;
		}
		if (newPos != null) {
			listPosiciones.add(newPos);
		}
	}

	private static void moveUp(char[][] casa, Posicion pos, List<Posicion> listPosiciones) {
		Posicion newPos = null;
		Integer posColumna1 = pos.getPosColumna1();
		Integer posColumna2 = pos.getPosColumna2();
		Integer posFila1 = pos.getPosFila1();
		switch (pos.getEstado()) {
		case PARADA:
			if (posFila1 > 1 && casa[posFila1 - 1][posColumna1] != '#' && casa[posFila1 - 2][posColumna1] != '#') {
				// OK
				newPos = new Posicion(posFila1 - 2, posFila1 - 1, posColumna1, null, pos.getCaminoRecorrido() + 'U');
			}
			break;
		case ACOSTADA_HORIZONTAL:
			if (posFila1 > 0 && casa[posFila1 - 1][posColumna1] != '#' && casa[posFila1 - 1][posColumna2] != '#') {
				// OK
				newPos = new Posicion(posFila1 - 1, null, posColumna1, posColumna2, pos.getCaminoRecorrido() + 'U');
			}
			break;
		case ACOSTADA_VERTICAL:
			if (posFila1 > 0 && casa[posFila1 - 1][posColumna1] != '#') {
				// OK
				newPos = new Posicion(posFila1 - 1, null, posColumna1, null, pos.getCaminoRecorrido() + 'U');
			}
			break;
		}
		if (newPos != null) {
			listPosiciones.add(newPos);
		}
	}
}
