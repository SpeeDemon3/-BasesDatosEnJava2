package actividad07.libreria.ejercicio02;

import javax.persistence.*;

/**
 * 
 * @author Antonio Ruiz Benito -> DAW
 *
 */
@Entity // La clase estara mapeada en una base de datos
public class Empleado {
	
	// Atributos
	@Id @GeneratedValue private int id;  // @Id -> Indica que el atributo es clave @GeneratedValue -> Indica que se generara automaticamente
	private String nombre;
	private String apellido;
	
	// Relaciono esta clase con la clase Tienda
	@ManyToOne // Muchos empleados pueden estar relacionados con 1 tienda
	private Tienda tienda;
	
	// Constructor
	public Empleado(String nombre, String apellido) {
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
	// Setters y Getters
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getApellido() {
		return apellido;
	}
	
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	public int getId() {
		return id;
	}
	
	public Tienda getTienda() {
		return tienda;
	}
	
	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
	
	// Metodo toString() sobrescrito
	@Override
	public String toString() {
		return "Id: " + id + " - Nombre: " + nombre + " - Apellido: " + apellido; 
	}

}
