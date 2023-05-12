package actividad07.libreria.ejercicio02;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * 
 * @author Antonio Ruiz Benito -> DAW
 *
 */
public class GestionaTiendas {

	public static void main(String[] args) {
		
			// Creo 3 objetos Tienda
			Tienda videojuegos = new Tienda("C/ Madrid", 500);
			Tienda muebles = new Tienda("C/ Valencia", 250);
			Tienda electronica = new Tienda("C/ Sevilla", 1000);
			
			// Creo 3 objetos Empleado
			Empleado emp1 = new Empleado("Iron", "Man");
			Empleado emp2 = new Empleado("Furia", "Nocturna");
			Empleado emp3 = new Empleado("Peter", "Parker");
			
			// Inserto las tiendas en la base de datos
			GestionObjectDB.insertarTiendas(videojuegos);
			GestionObjectDB.insertarTiendas(muebles);
			GestionObjectDB.insertarTiendas(electronica);

			// Asigno un empleado a cada tienda
			videojuegos.addEmpleado(emp1);
			muebles.addEmpleado(emp2);
			electronica.addEmpleado(emp3);
		
	
		// Mensaje de bienvenida
		System.out.println("Bienbenido al gestor de tiendas.\n"
						+ "Por favor seleccione una opción:");
		
		// Creo una variable de control para controlar el bucle
		boolean control = false;
		
		// Con un bucle do while mostrare el menu al usuario hasta que el decida finalizar el programa
		do {
			
			
			// Menu
			System.out.println("1 - Mostrar empleados\n"
							+  "2 - Mostrar tiendas\n"
							+  "3 - Mostrar tiendas ordenadas por ventas\n"
							+  "4 - Editar un empleado\n"
							+  "5 - Crear una nueva tienda\n"
							+  "6 - Eliminar todas las tiendas y empleados\n"
							+  "0 - Salir");
			
			try {
				// Creo una objeto Scanner para poder interactuar con el usuario
				Scanner sc = new Scanner(System.in);
				
				// Guardo el valor introducido por el usuario
				int opcionUsuario = sc.nextInt();
				
				if (opcionUsuario < 0 || opcionUsuario > 6) { // Si la opcion introducida es menor a 0 o mayor a 5 
					System.out.println("Introduce un opción numérica valida del 0 al 5.");
					continue; // continua a la siguiente iteracion
				}
				
				switch(opcionUsuario) {
					// Funcionalidad para salir del programa
					case 0:
						// Mensaje de despedida
						System.out.println("Gracias por utilizar el programa.");
						System.out.println("Ejercicio realizado por Antonio Ruiz Benito.");
						// Cambio la variable control a true para salir del bucle y finalizar el programa
						control = true;
					break;
					// Funcionalidad para mostrar todos los empleados
					case 1:
						// Invoco al metodo mostrarEmpleados()
						GestionObjectDB.mostrarEmpleados();
					break;
					// Funcionalidad para mostrar todas las tiendas 
					case 2:
						// Invoco al metodo mostrarTiendas()
						GestionObjectDB.mostrarTiendas();
					break;
					// Funcionalidad para mostrar las tiendas ordenadas por numero de ventas 
					case 3:
						// Invoco al metodo mostrarVentas()
						GestionObjectDB.mostrarVentas();
					break;
					// Funcionalidad para editar un empleado
					case 4:
						// Invoco al metodo editarEmpleado()
						GestionObjectDB.editarEmpleado();
					break;
					// Funcionalidad para crear una nueva tienda
					case 5:
						// Invoco al metodo crearNuevaTienda()
						GestionObjectDB.crearNuevaTienda();
					break;
					// Funcionalidad extra para borrar los registros de las tablas Tienda y Empleado
					case 6:
						// Invoco al metodo eliminarTodosLosRegistros()
						GestionObjectDB.eliminarTodosLosRegistros();
					break;
				}
				
			} catch (InputMismatchException ime) { // Controlo la excepcion que pudiera ocurrir si el usuario no introduce un valor numerico
				System.out.println("Debes introducir valores numéricos.");
			}
	
		} while(!control); // Mientras control no sea distinto de false el bucle seguira iterando
		
	}
	

}
