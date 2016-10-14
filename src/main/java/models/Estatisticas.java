package models;

public class Estatisticas {

	long num_Problemas;
	long num_Solucoes;
	long num_Testes;

	public Estatisticas(long num_Problemas, long num_Solucoes, long num_Testes) {
		this.num_Problemas = num_Problemas;
		this.num_Solucoes = num_Solucoes;
		this.num_Testes = num_Testes;
	}

	public long getNum_Problemas() {
		return num_Problemas;
	}

	public long getNum_Solucoes() {
		return num_Solucoes;
	}

	public long getNum_Testes() {
		return num_Testes;
	}

	public void setNum_Problemas(long num_Problemas) {
		this.num_Problemas = num_Problemas;
	}

	public void setNum_Solucoes(long num_Solucoes) {
		this.num_Solucoes = num_Solucoes;
	}

	public void setNum_Testes(long num_Testes) {
		this.num_Testes = num_Testes;
	}

}
