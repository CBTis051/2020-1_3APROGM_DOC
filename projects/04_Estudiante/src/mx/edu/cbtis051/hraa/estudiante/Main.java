package mx.edu.cbtis051.hraa.estudiante;

public class Main {

	public static void main(String[] args) {
		// Pruebas de la clase Estudiante
		Estudiante e1 = new Estudiante();
		Estudiante e2 = new Estudiante(242, "Angel Haya", "081000-2", 9.7);

		// Verificación de datos
		System.out.println("Estudiante e1.id: " + e1.getId());
		// TODO: imprimir línea por línea cada variable de clase de ambos objetos 
		
		System.out.println("Estudiante e2.id: " + e2.getId());
		
		System.out.println(e1);
	}

}
