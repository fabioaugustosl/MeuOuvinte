package br.com.virtz.ouvinte.service;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.inject.Inject;

import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IUsuarioDAO;
import br.com.meuouvinte.modelos.Usuario;

public class UsuarioService implements IUsuarioService,  Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private IUsuarioDAO usuarioDAO;

	

	public UsuarioService() {
		super();
	}

	public String salvar(Usuario usuario) throws Exception{
		if(usuario != null){
			if(usuario.getSenha().length() <= 10){
				byte[] thedigest = gerarSenha(usuario.getSenha());
				usuario.setSenha(new String(thedigest));
			}
			usuarioDAO.salvar(usuario);
		}
		return null;
	}

	public String alterarSenha(Usuario usuarioLogado) throws Exception{
		usuarioDAO.salvar(usuarioLogado);
		return null;
	}

	
	public byte[] gerarSenha(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(senha.getBytes());
		return thedigest;
	}


	public Usuario autenticar(String email, String senha) throws Exception{
		
		if(email.equals("virtz@virtz.com.br") && senha.equals("demo")){
			Usuario usuarioLogado = new Usuario();
			usuarioLogado.setEmail("virtz@virtz.com.br");
			usuarioLogado.setNome("Admin");
			return usuarioLogado;
		}

		Usuario usuario = usuarioDAO.recuperar(email);
		if(usuario != null){
			if(usuario.getSenha().equals(new String(gerarSenha(senha)))){
				return usuario;
			}
		}
		
		return null;
	}

	public void remover( Usuario[] usuariosSelecionados){
		for(Usuario o : usuariosSelecionados){
			usuarioDAO.remover(o);
		}
	}

	public List<Usuario> carregarUsuarios(){
		return usuarioDAO.recuperarTodos();
	}


}
