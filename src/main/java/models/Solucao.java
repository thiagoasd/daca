package models;

public class Solucao {

	private boolean last;
	private String body;
	private String[] outputs;
	
	
	/**
	 * @return the last
	 */
	public boolean isLast() {
		return last;
	}
	/**
	 *@param Sets last value to last
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
	 *@param Sets body value to body
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
	 *@param Sets outputs value to outputs
	 */
	public void setOutputs(String[] outputs) {
		this.outputs = outputs;
	}

	
}
