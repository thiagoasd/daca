package models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Solucao {

	@Id
	@GeneratedValue
	private long id;

	@Column
	private boolean last;

	@Column(nullable = false)
	@NotBlank
	private String body;

	@ElementCollection
	private List<String> outputs;

	@Column(nullable = false)
	private long problemID;

	public Solucao(int id, boolean last, String body, List<String> outputs, long problemID) {

		this.id = id;
		this.last = last;
		this.body = body;
		this.outputs = outputs;
		this.problemID = problemID;
	}

	public Solucao(boolean last, String body, List<String> outputs, long problemID) {

		this.last = last;
		this.body = body;
		this.outputs = outputs;
		this.problemID = problemID;
	}

	public Solucao() {
	}

	/**
	 * @return the last
	 */
	public boolean isLast() {
		return last;
	}

	/**
	 * @param Sets
	 *            last value to last
	 */
	public void setLast(boolean last) {
		this.last = last;
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param Sets
	 *            body value to body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the outputs
	 */
	public List<String> getOutputs() {
		return outputs;
	}

	/**
	 * @param Sets
	 *            outputs value to outputs
	 */
	public void setOutputs(List<String> outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param Sets
	 *            id value to id
	 */
	public void setId(long id) {
		this.id = id;
	}

	public long getProblemID() {
		return problemID;
	}

	public void setProblemID(long problemID) {
		this.problemID = problemID;
	}

}
