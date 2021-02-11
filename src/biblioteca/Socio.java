package biblioteca;

import java.util.ArrayList;
import java.util.List;

public class Socio {

	private String nombre;
	private String nCarnet;
	static int codigo = 0;
	private List<Libro> prestados;

	public Socio(String nombre) {
		super();
		this.nombre = nombre;
		this.nCarnet = String.valueOf(codigo++);
		prestados = new ArrayList<Libro>();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getnCarnet() {
		return nCarnet;
	}

	

	public void addLibro(Libro l) {
		prestados.add(l);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nCarnet == null) ? 0 : nCarnet.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((prestados == null) ? 0 : prestados.hashCode());
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
		Socio other = (Socio) obj;
		if (nCarnet == null) {
			if (other.nCarnet != null)
				return false;
		} else if (!nCarnet.equals(other.nCarnet))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (prestados == null) {
			if (other.prestados != null)
				return false;
		} else if (!prestados.equals(other.prestados))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre + ", nº Carnet: " + nCarnet + " \n" + stringLibros();
	}

	private String stringLibros() {

		String res = " libros prestados: ";
		if (!prestados.isEmpty()) {
			for (Libro libro : prestados) {
				res += libro.getTitulo();
			}
		}
		return res;
	}

}
