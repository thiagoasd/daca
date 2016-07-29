package models;

public class Solucao {

	private int id;
	private boolean last;
	private String body;
	private String[] outputs;

	public Solucao(boolean last, String body, String[] outputs) {

		this.last = last;
		this.body = body;
		this.outputs = outputs;
	}

	public Solucao(int id, boolean last, String body, String[] outputs) {
		
		this.id = id;
		this.last = last;
		this.body = body;
		this.outputs = outputs;
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
	public String[] getOutputs() {
		return outputs;
	}

	/**
	 * @param Sets
	 *            outputs value to outputs
	 */
	public void setOutputs(String[] outputs) {
		this.outputs = outputs;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 *@param Sets id value to id
	 */
	public void setId(int id) {
		this.id = id;
	}

}
