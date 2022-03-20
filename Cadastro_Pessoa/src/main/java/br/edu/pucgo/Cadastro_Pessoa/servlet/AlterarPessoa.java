package br.edu.pucgo.Cadastro_Pessoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.pucgo.Cadastro_Pessoa.dao.PessoaDao;
import br.edu.pucgo.Cadastro_Pessoa.controle.PessoaControle;
import br.edu.pucgo.Cadastro_Pessoa.modelo.Pessoa;

@WebServlet("/alterar")
public class AlterarPessoa extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private Gson gson;
	
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PessoaDao pd = new PessoaDao();
		Pessoa pessoa = new Pessoa();
		PessoaControle pc = new PessoaControle();
		
		gson = new GsonBuilder().create();
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		String nome = req.getParameter("nome");
		String cpf = req.getParameter("cpf");
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		
		pessoa = pc.findById(idInt);
		pessoa.setId(idInt);
		pessoa.setNome(nome);
		
		boolean retorno = true;
		
			try {
				pd.salvarPessoa(pessoa);
			} catch (SQLException e) {
				e.printStackTrace();
				retorno = false;
			}
		
		String pessoaJsonString = this.gson.toJson(pessoa);
			
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(pessoaJsonString);
		out.flush();
	}
}
