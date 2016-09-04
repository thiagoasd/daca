package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Teste {

	@Column(nullable = false)
	@NotBlank
	private String dica;
	
	@Column(nullable = false)
	@NotBlank
	private String entrada;
	
	@Column(nullable = false)
	@NotBlank
	private String nome;
	
	@Column(nullable = false)
	@NotBlank
	private String saida;
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	private long problemID;
	
	@Column(nullable = false)
	private boolean publico;

	public Teste() {

	}

	public Teste(String nome, String dica, String entrada, String saida, long problemID, boolean publico) {

		this.dica = dica;
		this.entrada = entrada;
		this.nome = nome;
		this.saida = saida;
		this.problemID = problemID;
		this.publico = publico;
	}

	/**
	 * @return the dica
	 */
	public String getDica() {
		return dica;
	}

	/**
	 * @return the entrada
	 */
	public String getEntrada() {
		return entrada;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the problemID
	 */
	public long getProblemID() {
		return problemID;
	}

	/**
	 * @return the saida
	 */
	public String getSaida() {
		return saida;
	}

	public boolean isCorrect(String entrada) {
		return entrada.compareTo(saida) == 0;
	}

	/**
	 * @return the publico
	 */
	public boolean isPublico() {
		return publico;
	}

	/**
	 * @param Sets
	 *            dica value to dica
	 */
	public void setDica(String dica) {
		this.dica = dica;
	}

	/**
	 * @param Sets
	 *            entrada value to entrada
	 */
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}

	/**
	 * @param Sets
	 *            id value to id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param Sets
	 *            nome value to nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @param Sets
	 *            problemID value to problemID
	 */
	public void setProblemID(long problemID) {
		this.problemID = problemID;
	}

	/**
	 * @param Sets
	 *            publico value to publico
	 */
	public void setPublico(boolean publico) {
		this.publico = publico;
	}

	/**
	 * @param Sets
	 *            saida value to saida
	 */
	public void setSaida(String saida) {
		this.saida = saida;
	}
}
