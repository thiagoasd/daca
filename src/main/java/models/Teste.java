package models;

public class Teste {

	private String dica;
	private String entrada;
	private String nome;
	private String saida;
	private int id;
	private int problemID;
	private boolean publico;

	public Teste() {

	}

	public Teste(String dica, String entrada, String nome, String saida, int id, int problemID, boolean publico) {

		this.dica = dica;
		this.entrada = entrada;
		this.nome = nome;
		this.saida = saida;
		this.id = id;
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
	public int getId() {
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
	public int getProblemID() {
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
	public void setId(int id) {
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
	public void setProblemID(int problemID) {
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
