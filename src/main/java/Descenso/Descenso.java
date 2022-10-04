package Descenso;

import java.util.Scanner;

public class Descenso {

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {
			int n = scan.nextInt();
			int m = scan.nextInt();
			// Buscamos el mayor puntaje posible que puede tener un equipos que desciende.
			// A priori suponemos que m-1 equipos pierden todo.
			// El equipo restante hace la mayor cantidad de puntos posibles.
			//
			// La cantidad total de puntos de todos los equipos es igual a la cantidad de partidos ya que en cada partido se suma 1 punto:
			// (n-1) + (n-2) + (n-3) + .... + 1 = n * (n-1) / 2
			//
			// (n * (n-1) / 2) / (n-(m-1)) es el puntaje más alto que se puede tener y aún así poder descender.
			// Superando este valor, nos aseguramos no descender
			int totalPuntos = n * (n - 1) / 2;
			int participantesPorDescender = n - (m - 1);
			double puntajeNecesario = Double.valueOf(totalPuntos) / Double.valueOf(participantesPorDescender);
			double puntajeNecesarioRedondeado = Math.floor(puntajeNecesario) + 0.5;
			if (puntajeNecesarioRedondeado < puntajeNecesario) {
				puntajeNecesarioRedondeado += 0.5;
			}
			System.out.println(puntajeNecesarioRedondeado);
		}
	}
}
