package br.com.pucgo.Cadastro_Pessoa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.edu.pucgo.Cadastro_Pessoa.modelo.Pessoa;

public class PessoaDao {

	BaseDao bd = new BaseDao();
	
	public Pessoa criaPessoa(ResultSet rs) throws SQLException{
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId(rs.getInt("ID"));
		pessoa.setNome(rs.getString("NOME"));
		pessoa.setCpf(rs.getString("CPF"));
		
		return pessoa;
	}
	
	public Pessoa findById(int id) throws SQLException {
		Pessoa pessoa = new Pessoa();
		PreparedStatement pstm = null;
		Connection conn = null;
		
			try {
				conn = bd.getConnection();
				
				pstm = conn.prepareStatement("SELECT * FROM pessoa WHERE id=?");
				
				pstm.setInt(1, id);
				
				ResultSet rs = pstm.executeQuery();
				
					if (rs.next())
					{
						pessoa = this.criaPessoa(rs);
					}
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
			
		return pessoa;
	}
	
	public List<Pessoa> obterPessoaPorNome(String nome) throws SQLException {
		List<Pessoa> pessoas = new ArrayList<>();
		Pessoa pessoa = new Pessoa();
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs = null;
		
			try {
				conn = bd.getConnection();
				
				pstm = conn.prepareStatement("SELECT * FROM pessoa WHERE LOWER(NOME) LIKE ?");
				
				pstm.setString(1, "%"+nome.toLowerCase()+"%");
				
				rs = pstm.executeQuery();
				
					while (rs.next()) {
						pessoa = this.criaPessoa(rs);
						pessoas.add(pessoa);
					}
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
		
		return pessoas;
	}
	
	public List<Pessoa> obterTodos() throws SQLException
	{
		List<Pessoa> pessoas = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		Pessoa pessoa = null;
		
			try {
				conn = bd.getConnection();
				pstm = conn.prepareStatement("SELECT * FROM pessoa ORDER BY nome");
				rs = pstm.executeQuery();
				
					while (rs.next()) {
						pessoa = this.criaPessoa(rs);
						pessoas.add(pessoa);
					}
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
		
		return pessoas;
	}
	
	public static Integer getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				Integer id = rs.getInt(1);
				return id;
			}
		return 0;
	}
	
	public void salvarPessoa(Pessoa pessoa) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean isEmpty = false;
		String sql = "";
		
			try {
				conn = bd.getConnection();
				
					if (pessoa.getId() == 0) {
						sql = "INSERT INTO pessoa (nome, cpf) VALUES (?, ?)";
					} else {
						sql = "UPDATE pessoa SET nome = ?, cpf = ?  WHERE id = ?";
					}
				
				pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstm.setString(1, pessoa.getNome());
				pstm.setString(2, pessoa.getCpf());
				
					if (pessoa.getId() != 0) {
						pstm.setInt(3, pessoa.getId());
					}
					
				int idAux = pstm.executeUpdate();
				
					if (idAux == 0) {
						throw new SQLException("Erro ao atualizar Cliente");
					}
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
	}
	
	public boolean alterar(Pessoa pessoa) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		boolean retorno = true;
		String sql = "update pessoa SET nome = ?, cpf = ? WHERE id = ?";
		
			try
			{
				conn = bd.getConnection();
				pstm = conn.prepareStatement(sql);
				
				pstm.setString(1, pessoa.getNome());
				pstm.setInt(2, pessoa.getId());
				
				int count = pstm.executeUpdate();
				
				retorno = count > 0;
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
			
		return retorno;
	}

	public boolean excluir(int id) throws SQLException {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
			try {
				conn = bd.getConnection();
				
				String sql = "DELETE FROM pessoa WHERE id = ?";
				
				pstm = conn.prepareStatement(sql);
				
				pstm.setInt(1, id);
				
				int count = pstm.executeUpdate();
				boolean retorno = count > 0;
				
				return retorno;
			} finally {
				if (pstm != null) {
					pstm.close();
				}
				if (pstm != null) {
					conn.close();
				}
			}
	}
}
