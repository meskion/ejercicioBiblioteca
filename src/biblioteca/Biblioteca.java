package biblioteca;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Biblioteca {

	static Map<String, Socio> socios;
	static List<Libro> libros;
	static Scanner sc;

	public static void main(String[] args) {
		libros = new LinkedList<Libro>();
		socios = new HashMap<String, Socio>();
		sc = new Scanner(System.in);
		String opcion = "";
		System.out.println("BIENVENIDO A LA BIBLIOTECA");
		do {
			// Al inicio de cada iteración mostramos el menú, y recogemos la opción
			menu();
			try {
				opcion = sc.nextLine();

				switch (opcion) {
				case "1":
					insertarSocio();
					break;
				case "2":
					insertarLibro();
					break;
				case "3":
					atenderPeticion();
					break;
				case "4":
					borrarSocio();
					break;
				case "5":
					mostrarLibros();
					break;
				case "6":
					mostrarSocios();
					break;
				case "7":
					System.out.println("APAGANDO APP");
					break;
				default:
					System.out.println("Opción no valida. Introduzca una opcion valida, por favor.");
				}

			} catch (NumberFormatException e) {
				System.err.println("Dato introducido no valido, debe ser un numero");
			}

		} while (!opcion.equals("7"));

		sc.close();

	}
	/**
	 * Muestra Por pantalla todos los socios de la biblioteca 
	 */
	private static void mostrarSocios() {

		for (Socio s : socios.values())
			System.out.println(s);

	}
/**
 * Muestra los libros de los que dispone la biblioteca
 */
	private static void mostrarLibros() {

		for (Libro l : libros)
			System.out.println(l);

	}
	
	/**
	 * Elimina un socio segun su numero de carné de la biblioteca. todos sus libros prestados 
	 * son automaticamente devueltos.
	 */

	private static void borrarSocio() {
		System.out.print("introduzca su carne de socio:");
		String carne = sc.nextLine();
		Socio socio = socios.get(carne);

		if (socio == null) {
			System.out.println("Este socio no existe");
			return;
		}

		/*
		 * coger el socio y y sus libros prestados y actualizarlos en la lista de la
		 * biblioteca para que dejen de estar prestados
		 */

		libros.parallelStream()
		.filter(l -> l.getPrestadoA().equals(socio))
		.forEach(l -> l.setPrestadoA(null));

//			for (Libro l : libros) {
//				if (l.getPrestadoA().equals(socio)) {
//					l.setPrestadoA(null);
//				}
//			}

		socios.remove(carne, socio);
		System.out.println("Socio eliminado");

	}

	private static void atenderPeticion() {
		System.out.print("introduzca su carne de socio:");
		String carne = sc.nextLine();
		Socio socio = socios.get(carne);
		if (socio == null) {
			System.out.println("No existe un socio asociado a ese carne");
			return;
		}

		System.out.print("Titulo del libro que quiere:");
		String titulo = sc.nextLine();

		// Coger el libro que me han dicho y el socio y cambiarle un atributo, añadir el
		// libro.

		libros.stream().filter(l -> l.getTitulo().equals(titulo) && l.getPrestadoA() == null).findAny()
				.ifPresentOrElse(l -> {
					socio.addLibro(l);
					l.setPrestadoA(socio);
					System.out.println("Prestamo realizado");
				}, () -> System.out.println("No hay ninguna copia disponible"));

//		for (Libro l : libros) {
//
//			if (l.getTitulo().equals(titulo)) {
//				if (l.getPrestadoA() == null) {
//					socio.addLibro(l);
//					l.setPrestadoA(socio);
//					System.out.println("Prestamo realizado");
//					return;
//
//				} else {
//					System.out.println("copia encontrada,pero prestada a " + l.getPrestadoA().getNombre());
//
//				}
//
//			}
//		}

	}

	private static void insertarLibro() {

		System.out.print("Introduzca el titulo: ");
		String titulo = sc.nextLine();
		System.out.print("Introduzca el autor: ");
		String autor = sc.nextLine();

		libros.add(new Libro(titulo, autor));

	}

	private static void insertarSocio() {
		System.out.print("Introduzca el nombre: ");
		String nombre = sc.nextLine();

		Socio socio = new Socio(nombre);
		socios.put(socio.getnCarnet(), socio);
	}

	private static void menu() {

		System.out.println("escoja una operacion:");
		System.out.println("1:		 insertar socio");
		System.out.println("2:		 insertar libro");
		System.out.println("3:		 atender peticion");
		System.out.println("4:		 borrar socio");
		System.out.println("5: 		 mostrar libros");
		System.out.println("6: 		 mostrar socios");
		System.out.println("7:		 Salir");

	}
}
