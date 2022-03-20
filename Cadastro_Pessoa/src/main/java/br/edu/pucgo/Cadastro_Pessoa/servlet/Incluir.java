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

@WebServlet("/inclui")
public class Incluir extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Gson gson;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		    	PessoaControle pc = new PessoaControle();
				Pessoa pessoa = new Pessoa();
				
				gson = new GsonBuilder().create();
				gson = new GsonBuilder().setPrettyPrinting().create();
				
				String nome = req.getParameter("nome");
				String cpf = req.getParameter("cpf");
				
				pessoa.setNome(nome);
				pessoa.setCpf(cpf);
				
				resp.setContentType("application/json");
				resp.setCharacterEncoding("UTF-8");
				PrintWriter out = resp.getWriter();
				
				String pessoaJsonString = this.gson.toJson(pc.salvarPessoa(pessoa));
				
				out.println(pessoaJsonString);
				out.flush();
		}
	}


