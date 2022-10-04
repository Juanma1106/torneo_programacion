package AGuardar;

public class Posicion {

	private Integer posFila1;
	private Integer posFila2;
	private Integer posColumna1;
	private Integer posColumna2;
	private Estado estado;
	private String caminoRecorrido;

	public Posicion(Integer posFila1, Integer posFila2, Integer posColumna1, Integer posColumna2,
			String caminoRecorrido) {
		this.posFila1 = posFila1;
		this.posFila2 = posFila2;
		this.posColumna1 = posColumna1;
		this.posColumna2 = posColumna2;
		this.caminoRecorrido = caminoRecorrido;
		llenarEstado();
	}

	private void llenarEstado() {
		if (posFila2 != null) {
			estado = Estado.ACOSTADA_VERTICAL;
		} else if (posColumna2 != null) {
			estado = Estado.ACOSTADA_HORIZONTAL;
		} else {
			estado = Estado.PARADA;
		}
	}

	public Integer getPosFila1() {
		return posFila1;
	}

	public Integer getPosFila2() {
		return posFila2;
	}

	public Integer getPosColumna1() {
		return posColumna1;
	}

	public Integer getPosColumna2() {
		return posColumna2;
	}

	public Estado getEstado() {
		return estado;
	}

	public String getCaminoRecorrido() {
		return caminoRecorrido;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + ((posColumna1 == null) ? 0 : posColumna1.hashCode());
		result = prime * result + ((posColumna2 == null) ? 0 : posColumna2.hashCode());
		result = prime * result + ((posFila1 == null) ? 0 : posFila1.hashCode());
		result = prime * result + ((posFila2 == null) ? 0 : posFila2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (estado != other.estado)
			return false;
		if (posColumna1 == null) {
			if (other.posColumna1 != null)
				return false;
		} else if (!posColumna1.equals(other.posColumna1))
			return false;
		if (posColumna2 == null) {
			if (other.posColumna2 != null)
				return false;
		} else if (!posColumna2.equals(other.posColumna2))
			return false;
		if (posFila1 == null) {
			if (other.posFila1 != null)
				return false;
		} else if (!posFila1.equals(other.posFila1))
			return false;
		if (posFila2 == null) {
			if (other.posFila2 != null)
				return false;
		} else if (!posFila2.equals(other.posFila2))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Posicion [posFila1=" + posFila1 + ", posFila2=" + posFila2 + ", posColumna1=" + posColumna1
				+ ", posColumna2=" + posColumna2 + ", estado=" + estado + ", caminoRecorrido=" + caminoRecorrido + "]";
	}
}
