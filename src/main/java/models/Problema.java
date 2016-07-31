package models;

public class Problema {

	private int id;
	private String codigo;
	private String descricao;
	private String dica;
	private String nome;

	public Problema() {

	}

	public Problema(int id, String nome, String codigo, String dica, String descricao) {
		this.id = id;
		this.codigo = codigo;
		this.dica = dica;
		this.descricao = descricao;
		this.nome = nome;

	}

	public Problema(String nome, String codigo, String dica, String descricao) {
		this.codigo = codigo;
		this.dica = dica;
		this.descricao = descricao;
		this.nome = nome;

	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @return the dica
	 */
	public String getDica() {
		return dica;
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
	 * @param Sets
	 *            codigo value to codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @param Sets
	 *            descricao value to descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
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

}