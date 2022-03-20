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



@WebServlet("/excluir")
public class Excluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Gson gson;
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PessoaControle pc = new PessoaControle();
		Pessoa pessoa = new Pessoa();
		
		gson = new GsonBuilder().create();
		gson = new GsonBuilder().setPrettyPrinting().create();
		
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String pessoaJsonString = "";
		
		String id = req.getParameter("id");
		int idInt = Integer.parseInt(id);
		
		pessoaJsonString = this.gson.toJson(pc.excluir(idInt));
			
		out.println(pessoaJsonString);
		out.flush();
	}

}
