package models;

public class Teste {

	private String nome;
	private String dica;
	private String saida;
	private boolean publico;
	
	
	public boolean isCorrect(String entrada){
		return entrada.compareTo(saida) == 0;		
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
	 * @return the saida
	 */
	public String getSaida() {
		return saida;
	}
	
	/**
	 *@param Sets saida value to saida
	 */
	public void setSaida(String saida) {
		this.saida = saida;
	}

	/**
	 * @return the publico
	 */
	public boolean isPublico() {
		return publico;
	}

	/**
	 *@param Sets publico value to publico
	 */
	public void setPublico(boolean publico) {
		this.publico = publico;
	}
}
