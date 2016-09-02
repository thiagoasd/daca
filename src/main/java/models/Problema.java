package models;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;


@Entity(name = "Problema")
public class Problema {

	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable = false)
	@NotBlank
	private String codigo;

	@Column
	private String descricao;

	@Column
	private String dica;
	
	@Column(nullable = false)
	private String nome;
	
	@Column
	@OneToMany(cascade = CascadeType.ALL)
	private List<Teste> testes;
	
	@Autowired
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
	 * @return the testes
	 */
	public List<Teste> getTestes() {
		return testes;
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
	 *@param Sets testes value to testes
	 */
	public void setTestes(List<Teste> testes) {
		this.testes = testes;
	}

}