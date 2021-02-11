package biblioteca;

public class Libro {
	
	private String titulo, autor;
	
	private Socio prestadoA;
	
	

	public Libro(String titulo, String autor) {
		super();
		this.titulo = titulo;
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Socio getPrestadoA() {
		return prestadoA;
	}

	public void setPrestadoA(Socio prestadoA) {
		this.prestadoA = prestadoA;
	}

	@Override
	public String toString() {
		String prestado = prestadoA==null ?
					". Sin prestar" :
					". Prestado a " + prestadoA.getNombre()+"["+prestadoA.getnCarnet()+"]";

		return "\""+titulo+"\", de "+autor+prestado;
	}
	
	
	
	

}
