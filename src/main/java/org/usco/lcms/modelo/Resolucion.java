/**
 * 
 */
package org.usco.lcms.modelo;

/**
 * @author jankarlos
 *
 */
public class Resolucion {

	private int codigo;
	private String fecha;
	private int dependencia;
	private String descripcion;
	private String numero;

	/**
	 * @param codigo
	 * @param fecha
	 * @param dependencia
	 * @param descripcion
	 * @param numero
	 */
	public Resolucion(int codigo, String fecha, int dependencia, String descripcion, String numero) {
		super();
		this.codigo = codigo;
		this.fecha = fecha;
		this.dependencia = dependencia;
		this.descripcion = descripcion;
		this.numero = numero;
	}

	/**
	 * 
	 */
	public Resolucion() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the codigo
	 */
	public int getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            the codigo to set
	 */
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha
	 *            the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the dependencia
	 */
	public int getDependencia() {
		return dependencia;
	}

	/**
	 * @param dependencia
	 *            the dependencia to set
	 */
	public void setDependencia(int dependencia) {
		this.dependencia = dependencia;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion
	 *            the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

}
