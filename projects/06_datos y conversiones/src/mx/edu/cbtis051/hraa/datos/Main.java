package mx.edu.cbtis051.hraa.datos;

public class Main {

	public static void main(String[] args) {
		// Tipos de datos y conversiones
		
		long habitantes = 9999999999L;
		habitantes = 9_999_999_999L;
		
		double capacidad = 9999999999.99;
		float capacidad_float = 9999999999.99f;
		
		int valor_binario = 1001011;
		System.out.println(valor_binario);
		valor_binario = 0b1001011;
		System.out.println(valor_binario);
		
		long valor_hexa = 0xCAFE_CACA;
		valor_hexa = 0xBEBE;
		valor_hexa = 0xDEAD;
		valor_hexa = 0xFACE_FB;
		valor_hexa = 0xDEAD_DEAD_DEADL;
		valor_hexa = 0xFF;
		System.out.println(valor_hexa);
		
		
		// Conversiones (cast)
		double galon = 3;
		int litros = (int) galon;
		galon = litros;
		System.out.println(litros);
		
		long valor_long = 10;
		int valor_int = (int) valor_long;
		valor_long = valor_int;
		
		
		// Operadores
		System.out.println("División: " + 10/3);
		
		float resultado = 10/3f;
		System.out.println(resultado);
		

	}
	
	

}







