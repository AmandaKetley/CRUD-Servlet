package br.edu.pucgo.Cadastro_Pessoa.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import br.edu.pucgo.Cadastro_Pessoa.controle.PessoaControle;
import br.edu.pucgo.Cadastro_Pessoa.modelo.Pessoa;

@WebServlet("/listarTodos")
public class ListarTodos extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private PessoaControle pessoaControle = new PessoaControle();
	private Gson gson;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Pessoa> pessoas = pessoaControle.getPessoa();
			String pessoasString = pessoas.toString();
			resp.getWriter().write(pessoasString);
			resp.getWriter().write("<br>");
			resp.getWriter().write("<br>");
			
			gson = new GsonBuilder().create();
			gson = new GsonBuilder().setPrettyPrinting().create();
			
			JsonArray jarray = gson.toJsonTree(pessoas).getAsJsonArray();
			JsonObject jsonObject = new JsonObject();
			jsonObject.add("estoques", jarray);
			
			resp.getWriter().write(jsonObject.toString());
			PrintWriter pw = resp.getWriter();
			pw.println(" ");
			pw.println(gson.toJson(pessoas));
			
		} catch (SQLException e) {
			
		}
	}

}
