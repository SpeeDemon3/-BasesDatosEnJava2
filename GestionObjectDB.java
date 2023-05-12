package actividad07.libreria.ejercicio02;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.persistence.*;
/**
 * 
 * @author Antonio Ruiz Benito -> DAW
 *
 */
public class GestionObjectDB {

	// Atributos
	private static String baseDatos = "objectdb:db/tiendas.odb"; // Ubicacion de la base de datos

	// Constructor
	public GestionObjectDB() {}
	
	// Metodos
	/**

	/**
	 * Metodo para añadir tiendas en la base de datos tiendas
	 * @param t -> tienda de la clase Tienda
	 */
	public static void insertarTiendas(Tienda t) {
		// Vinculo el objeto emf con la base de datos (si la base de datos no existe se creara)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo una conexion con la base de datos tiendas
		EntityManager em = emf.createEntityManager();
		
		try {
			// Inicio la transaccion
			em.getTransaction().begin();
			// Inserto los datos en la base de datos
			em.persist(t);
			// Finalizo y envio la transaccion
			em.getTransaction().commit();
			
			System.out.println("Operación de inserción de tienda realizada con exito.");
			
		} catch (Exception e) {
	    	// Si se produce una excepcion se realizara un rollback y la transaccion se desara la transaccion
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	            System.out.println("Se realizó un rollback por algun fallo.");
	        } 
	    }finally {
			// Cierro la conexion con la base de datos
			em.close();
			// Cierro el vinculo con la base de datos
			emf.close();
		}
		
	}
	
	/**
	 * Metodo para listar todos los empleados en la base de datos
	 * a traves de una consulta SELECT y mostrar los resultados por consola
	 */
	public static void mostrarEmpleados() {
		// Vinculo el objeto emf con la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo la conexion con la base de datos
		EntityManager em = emf.createEntityManager();
		
		// Creo la consulta dentro de un objeto TypedQuery 
		// utilizando el metodo createQuery() pasando como primer parametro 
		// la consulta SQL y como segundo parametro el tipo de dato a retornar
		TypedQuery<Empleado> query = em.createQuery("SELECT e FROM Empleado e", Empleado.class);
		
		try {
			// Con el metodo getResultList() obtengo una lista con todos los objetos de la consulta
			List<Empleado> empleados = query.getResultList();
			
			// Con un bucle for each imprimo los resultados de la consulta
			for (Empleado emp : empleados) {
				System.out.println(emp);
			} 
		} finally {
			// Cierro la conexion con la base de datos
			em.close();
			// Cierro el vinculo con la base de datos
			emf.close();
		}
		
		System.out.println("La operación de mostrar empleados se realizó correctamente.");
		
		
	}
	
	/**
	 * Metodo para realizar una consulta SELECT y  mostrar todas las tiendas
	 * por consola 
	 */
	public static void mostrarTiendas() {
		// Vinculo el objeto emf con la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo la conexion con la base de datos
		EntityManager em = emf.createEntityManager();
		
		// Creo la consulta dentro de un objeto TypedQuery 
		// utilizando el metodo createQuery() pasando como primer parametro 
		// la consulta SQL y como segundo parametro el tipo de dato a retornar
		TypedQuery<Tienda> query = em.createQuery("SELECT t FROM Tienda t", Tienda.class);
		
		try {
			// Con el metodo getResultList() obtengo una lista con todos los objetos de la consulta
			List<Tienda> tiendas = query.getResultList();
						
			// Con un bucle for each imprimo los resultados de la consulta
			for (Tienda tienda: tiendas) {
				System.out.println(tienda);
			}
			
			System.out.println("La operación se realizó con exito.");
			
		} finally {
			// Cierro la conexion con la base de datos
			em.close();
			// Cierro el vinculo con la base de datos
			emf.close();
		}
		
	}
	
	/**
	 * Metodo para mostar las tiendas por orden de ventas ascendento o descendente
	 * pidiendo al usuario que eliga de que forma desea obtener la lista
	 */
	public static void mostrarVentas() {
		// Vinculo el objeto emf con la base de datos
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo la conexion con la base de datos
		EntityManager em = emf.createEntityManager();
		
		try {
			// Imprimo por consola las 2 opciones a elegir
			System.out.println("Selecciona si desea ver las tiendas por número de ventas de modo ascendente (PULSANDO 1) o descendente (PULSANDO 2):");
			// Creo un objeto Scanner para poder interactuar y guardar los valores introducidos por el usuario
			Scanner sc = new Scanner(System.in);
			// Guardo el valor introducido por el usuario
			int opcionUsuario = sc.nextInt();
			// Compruebo si el usuario introduce un valor dentro del rango 
			if (opcionUsuario < 1 || opcionUsuario > 2) {
				System.out.println("La opción introducida no es válida.");
			} else if (opcionUsuario == 1){
				// Creo la consulta para mostrar la consulta ordenada por ventas de modo ascendente
				TypedQuery<Tienda> queryAsc = em.createQuery("SELECT t FROM Tienda t ORDER BY t.ventasSemanales ASC", Tienda.class);
				// Con el metodo getResultList() obtengo una lista con todos los objetos de la consulta
				List<Tienda> tiendas = queryAsc.getResultList();
				// Con un bucle for each imprimo los resultados de la consulta
				for (Tienda t : tiendas) {
					System.out.println(t);
				}
			} else {
				// Creo la consulta para mostrar la consulta ordenada por ventas de modo descendente
				TypedQuery<Tienda> queryDesc = em.createQuery("SELECT t FROM Tienda t ORDER BY t.ventasSemanales DESC", Tienda.class);
				// Con el metodo getResultList() obtengo una lista con todos los objetos de la consulta
				List<Tienda> tiendas = queryDesc.getResultList();
				// Con un bucle for each imprimo los resultados de la consulta
				for (Tienda t : tiendas) {
					System.out.println(t);
				}
			}
			
		}catch(InputMismatchException ime) { // Controlo si el usuario no introduce un valor numerico
			System.out.println("Debes introducir un valos numérico.");
		}finally {
			// Cierro la conexion con la base de datos
			em.close();
			// Cierro el vinculo con la base de datos
			emf.close();
		}

			
	}
	
	/**
	 * Metodo para que el usuario pueda añadir una nueva tienda y un empleado para la tienda
	 * en la base de datos
	 */
	public static void crearNuevaTienda() {
	    // Creo un objeto Scanner para poder interactuar con el usuario y guardar los valores que introduzca
	    Scanner sc = new Scanner(System.in);
	    System.out.println("Introduce la dirección de la nueva tienda:");
	    String direccion = sc.nextLine(); // Guardo el valor introducido por el usuario
	    System.out.println("Introduce el número de ventas semanales de la tienda:");
	    int ventasSemanales = sc.nextInt(); // Guardo el valor introducido por el usuario
	    sc.nextLine(); // Hago un salto de linea

	    // Creo una nueva tienda con los valores introducidos por el usuario
	    Tienda nuevaTienda = new Tienda(direccion, ventasSemanales);
	    
	    insertarTiendas(nuevaTienda); // Inserto la nueva tienda en la base de datos

	    System.out.println("Debes contratar un empleado para poder comenzar a operar con tu tienda:");
	    System.out.println("Introduce el nombre del empleado:");
	    String nombre = sc.nextLine(); // Guardo el valor introducido por el usuario
	    System.out.println("Introduce el apellido del empleado:");
	    String apellido = sc.nextLine(); // Guardo el valor introducido por el usuario

	    // Creo un nuevo empleado con los valores introducidos por el usuario
	    Empleado nuevoEmpleado = new Empleado(nombre, apellido);

	    nuevaTienda.addEmpleado(nuevoEmpleado);

		// Vinculo el objeto emf con la base de datos
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo la conexion con la base de datos
	    EntityManager em = emf.createEntityManager();

	    try {
	        // Iniciar una transacción
	        em.getTransaction().begin();

	        // Persistir la nueva tienda y los empleados en la base de datos
	        em.persist(nuevaTienda);
	        em.persist(nuevoEmpleado);

	        // Confirmar la transacción
	        em.getTransaction().commit();
	        
	        System.out.println("La operación se realizó con exito.");
	        
	    } catch (Exception e) {
	    	// Si se produce una excepcion se realizara un rollback y la transaccion se desara la transaccion
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	            System.out.println("Se realizó un rollback por algun fallo.");
	        } 
	    } finally {
	        // Cerrar la conexión con la base de datos
	        em.close();
	        emf.close();
	    }

	    System.out.println(nuevaTienda); // Imprimo la informacion de la nueva tienda creada
	}
	
	/**
	 * Metodo para editar un empleado
	 */
	public static void editarEmpleado() {
		// Inicio la transaccion
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos);
		// Creo la conexion con la base de datos
		EntityManager em = emf.createEntityManager();
		
		try {
			// Variable para controlar el bucle
			boolean control = false;
			// Bucle para pedir los datos al usuario hasta que introduzca todo correctamente
			while(!control) { // Mientras control no sea distinto de false el bucle seguira iterando
				// Creo un objeto Scanner para poder interactuar con el usuario
				Scanner sc = new Scanner(System.in);
				
				// Pido al usuario que introduzca el ID
				System.out.println("Introduce el ID del empleado que desea modificar:");
				// Guardo el valor introducido por el usuario
				int idUsuario = sc.nextInt();
				
				// Creo un empleado en el que busco el ID introducido por el usuario utilizando el metodo find() al que le paso la clase donde buscar y el id introducido por el usuario
				Empleado emp = em.find(Empleado.class, idUsuario);

				// Compruebo si el ID introducido por el usuario existe
				if (emp == null) {
					System.out.println("Debes introducir un ID valido.");
					continue; // Si el usuario no introduce una opcion valida el bucle volvera a empezar
				}
				
				// Solicito que valor desea modificar
				System.out.println("Pulsa 1 para modificar el nombre.\n"
								+  "Pulsa 2 para modificar el apellido.\n"
								+  "Pusla 3 para modificar nombre y apellido.");
				// Guardo el valor introducido por el usuario
				int opcionModificar = sc.nextInt();
				sc.nextLine(); // Hago un salto de linea para vaciar le buffer
				
				// Compruebo si el valor esta dentro del rango numerico valido
				if (opcionModificar < 1 || opcionModificar > 3) {
					System.out.println("Debes seleccionar una opción valida.");
					continue; // Si el usuario no introduce una opcion valida el bucle volvera a empezar
					
				} else if (opcionModificar == 1) { // Se modificara el nombre del empleado
					// Pido el nuevo nombre
					System.out.println("Introduce el nuevo nombre:");
					// Guardo el nuevo nombre
					String nombre = sc.nextLine();
					
					// Modifico el nombre del empleado
					emp.setNombre(nombre);
					
					// Inicio la transaccion y modifico al empleado
					em.getTransaction().begin();
					// Confirmo la transaccion
					em.getTransaction().commit();
					
					System.out.println("El empleado se actualizo con exito.");
					System.out.println(emp); // Muestro por consola el empleado modificado
					
					control = true; // Cambio el valor de la variable control a true para poder salir del bucle
				} else if(opcionModificar == 2) {
					// Pido el nuevo nombre
					System.out.println("Introduce el nuevo apellido:");
					// Guardo el nuevo nombre
					String apellido = sc.nextLine();
					
					// Modifico el nombre del empleado
					emp.setApellido(apellido);
					
					// Inicio la transaccion y modifico al empleado
					em.getTransaction().begin();
					// Confirmo la transaccion
					em.getTransaction().commit();
					
					System.out.println("El empleado se actualizo con exito.");
					System.out.println(emp);  // Muestro por consola el empleado modificado
					
					control = true; // Cambio el valor de la variable control a true para poder salir del bucle	
				} else {
					// Pido el nuevo nombre
					System.out.println("Introduce el nuevo nombre:");
					// Guardo el nuevo nombre
					String nombre = sc.nextLine();
					
					// Pido el nuevo nombre
					System.out.println("Introduce el nuevo apellido:");
					// Guardo el nuevo nombre
					String apellido = sc.nextLine();
					
					// Modifico el nombre del empleado
					emp.setNombre(nombre);
					// Modifico el nombre del empleado
					emp.setApellido(apellido);
					
					// Inicio la transaccion y modifico al empleado
					em.getTransaction().begin();
					// Confirmo la transaccion
					em.getTransaction().commit();
					
					System.out.println("El empleado se actualizo con exito.");
					System.out.println(emp);  // Muestro por consola el empleado modificado
					
					control = true; // Cambio el valor de la variable control a true para poder salir del bucle	
					
				}	
				
			} 
		} catch (InputMismatchException ime) { // Controlo la excepcion que pudiera surgir si el usuario no introduce un valor numerico
				System.out.println("Debes introducir valores numéricos validos.");
		} catch (Exception e) {
		    	// Si se produce una excepcion se realizara un rollback y la transaccion se desara la transaccion
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
		} finally {
				// Cierro la conexion con la base de datos
				em.close();
				// Cierro el vinculo con la base de datos
				emf.close();
			}
				
			
}
	
	/**
	 * Metodo para eliminar los registros 
	 */
	public static void eliminarTodosLosRegistros() {
		// Vinculo el objeto emf con la base de datos
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory(baseDatos); 
		// Creo la conexion con la base de datos
	    EntityManager em = emf.createEntityManager();
	    
	    try {
			// Inicio la transaccion
	        em.getTransaction().begin();
	        // Creo y ejecuto la sentencia SQL para eliminar los registros de Tienda
	        em.createQuery("DELETE FROM Tienda").executeUpdate();
	        // Creo y ejecuto la sentencia SQL para eliminar los registros de Tienda
	        em.createQuery("DELETE FROM Empleado").executeUpdate();
			// Finalizo y envio la transaccion
	        em.getTransaction().commit();
	        
	        System.out.println("Operacion realizada con exito");
	        
	    } catch (Exception e) {
	    	// Si se produce una excepcion se realizara un rollback y la transaccion se desara la transaccion
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	            System.out.println("Se realizó un rollback por algun fallo.");
	        }

	    } finally {
			// Cierro la conexion con la base de datos
	        em.close();
			// Cierro el vinculo con la base de datos
	        emf.close();
	    }
	}



	
	
}
