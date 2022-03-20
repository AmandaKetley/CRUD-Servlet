package br.edu.pucgo.Cadastro_Pessoa.controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.pucgo.Cadastro_Pessoa.dao.PessoaDao;
import br.edu.pucgo.Cadastro_Pessoa.modelo.Pessoa;

public class PessoaControle {

	private PessoaDao pd = new PessoaDao();
	
	public List<Pessoa> getPessoa() throws SQLException {
		try {
			List<Pessoa> pessoas = pd.obterTodos();
			return pessoas;
		} catch (SQLException e) {
			return new ArrayList<Pessoa>();
		}
	}
	
	public Pessoa findById(int id) {
		try {
			return pd.findById(id);
		} catch (SQLException e) {
			return null;
		}
	}
	
	public boolean excluir(int id) {
		try {
			return pd.excluir(id);
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean salvarPessoa(Pessoa pessoa) {
		try {
			pd.salvarPessoa(pessoa);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public boolean alterarPessoa(Pessoa pessoa) {
		try {
			pd.alterar(pessoa);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	public List<Pessoa> obterPessoaPorNome(String nome) {
		try {
			return pd.obterPessoaPorNome(nome);
		} catch (SQLException e) {
			return null;
		}
	}
}
