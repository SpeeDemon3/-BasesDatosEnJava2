package actividad07.libreria.ejercicio02;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * 
 * @author Antonio Ruiz Benito -> DAW
 *
 */
@Entity  // La clase estara mapeada en una base de datos
public class Tienda {

	// Atributos
	@Id @GeneratedValue private int id; // @Id -> Indica que el atributo es clave @GeneratedValue -> Indica que se generara automaticamente
	private String direccion;
	private int ventasSemanales;
	
	// Asigno a mappedBy el nombre del atributo de la clase N (en este caso Empleado)
	// Añado la propidad cascade = CascadeType.ALL, orphanRemoval = true  para que se propaguen todos los cambios que se produzcan entre las clases relacionadas
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "tienda", orphanRemoval = true) // Una tienda puede tener mas de 1 empleado
	private List<Empleado> empleados = new ArrayList<>(); 
	
	// Constructor
	public Tienda(String direccion, int ventasSemanales) {
		this.direccion = direccion;
		this.ventasSemanales = ventasSemanales;
	}
	
	// Metodos Setters y Getters
	public int getId() {
		return id;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public List<Empleado> getEmpleados() {
		return empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getVentasSemanales() {
		return ventasSemanales;
	}
	
	public void setVentasSemanales(int ventas) {
		this.ventasSemanales = ventas;
	}
	
	public void addEmpleado(Empleado empleado) {
		// Vinculo el objeto emf con la base de datos
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb:db/tiendas.odb"); 
		// Creo la conexion con la base de datos
	    EntityManager em = emf.createEntityManager();
	    
	    
	    
		try {
			// Inicio la transaccion 
			em.getTransaction().begin();
			
			empleado.setTienda(this); // Vinculo la tienda con el empleado añadido
			this.empleados.add(empleado); // Añado el empleado al List<Empleado> empleados
						
			// Inserto los datos en la base de datos
			em.persist(empleado); 
			
			// Finalizo y envio la transaccion 
			em.getTransaction().commit();
			
			System.out.println("Operación de inserción de empleado en tienda realizada con exito.");
			
		} catch (Exception exc) {
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

	// Sobrescribo el metodo toString()
	@Override
	public String toString() {
		return "Id: " + id + " - Dirección: " + direccion + " - Ventas Semanales: " + ventasSemanales + " - Empleados: " + empleados;
	}
	

}
