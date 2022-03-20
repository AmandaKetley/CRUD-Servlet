package br.edu.pucgo.Cadastro_Pessoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.edu.pucgo.Cadastro_Pessoa.controle.PessoaControle;
import br.edu.pucgo.Cadastro_Pessoa.modelo.Pessoa;

@WebServlet("/retorna")
public class RetornaPorId extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private Gson gson;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		gson = new GsonBuilder().create();
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		
		Pessoa pessoa = new Pessoa();
		PessoaControle ec = new PessoaControle();
		pessoa = ec.findById(idInt);
		
		String pessoaJsonString = this.gson.toJson(pessoa);
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		
		out.print(pessoaJsonString);
		out.flush();
	}

}
