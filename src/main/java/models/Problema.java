package models;

public class Problema {
	
	private long id;
	private String nome;
	private String descricao;
	private String codigo;
	private String dica;
	
	public Problema(long id, String name, String codigo, String dica, String descricao){
		this.id = id;
		this.codigo = codigo;
		this.dica = dica;
		this.descricao = descricao;
		this.nome = name;
	
	}
	
	public Problema(){
		
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return nome;
	}

	/**
	 *@param Sets name value to name
	 */
	public void setName(String name) {
		this.nome = name;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 *@param Sets descricao value to descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 *@param Sets codigo value to codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * @return the dica
	 */
	public String getDica() {
		return dica;
	}

	/**
	 *@param Sets dica value to dica
	 */
	public void setDica(String dica) {
		this.dica = dica;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 *@param Sets id value to id
	 */
	public void setId(long id) {
		this.id = id;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 *@param Sets nome value to nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	}