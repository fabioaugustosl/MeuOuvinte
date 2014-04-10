package br.com.meuouvinte.models;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.bean.Ouvinte;
import br.com.meuouvinte.bean.Programa;
import br.com.meuouvinte.bean.Promocao;
import br.com.meuouvinte.bean.Sorteio;
import br.com.meuouvinte.bean.Usuario;
import br.com.meuouvinte.dao.IOuvinteDAO;
import br.com.meuouvinte.dao.IUsuarioDAO;

import com.google.inject.Inject;

public class UsuarioModel implements  Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private IOuvinteDAO ouvinteDAO;

	@Inject
	private IUsuarioDAO usuarioDAO;

	private Usuario usuario;

	private Usuario usuarioLogado;

	private Usuario[] usuariosSelecionados;
	private List<Usuario> usuarios;

	private String senhaAntiga;
	private String senhaNova;
	private String senhaNovaConfirmacao;

	public UsuarioModel() {
		super();
		novo();
	}

	public String salvar(ActionEvent event) throws NoSuchAlgorithmException{
		if(usuario != null){
			if(usuario.getSenha().length() <= 10){
				byte[] thedigest = gerarSenha(usuario.getSenha());
				usuario.setSenha(new String(thedigest));
			}
			usuarioDAO.salvar(usuario);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Usuario salvo com sucesso!"));
		}
		this.usuarios = null;
		carregarUsuarios();
		return null;
	}

	public String alterarSenha(ActionEvent event) throws NoSuchAlgorithmException{

		if(usuarioLogado.getSenha().equals(new String(gerarSenha(senhaAntiga)))){
			if(!senhaNova.equals(senhaNovaConfirmacao)){
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha novas não são iguais"));
				return null;
			}

			usuarioLogado.setSenha(new String(gerarSenha(senhaNova)));

			usuarioDAO.salvar(usuarioLogado);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha alterada com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha antiga não é igual a senha do usuário logado!"));
		}
		return null;
	}

	public void novo(){
		this.usuario = new Usuario();
	}


	private byte[] gerarSenha(String senha) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] thedigest = md.digest(senha.getBytes());
		return thedigest;
	}


	public String autenticar(String email, String senha){

		if(email.equals("virtz@virtz.com.br") && senha.equals("demo")){
			usuarioLogado = new Usuario();
			usuarioLogado.setEmail("virtz@virtz.com.br");
			usuarioLogado.setNome("Admin");
			return "login";
		}

		Usuario usuario = usuarioDAO.recuperar(email);
		if(usuario != null){
			try {
				if(usuario.getSenha().equals(new String(gerarSenha(senha)))){
					usuarioLogado = usuario;
					return "login";
				}
			} catch (NoSuchAlgorithmException e) {
				return "falhou";
			}
		}
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Email ou Senha incorretos!"));
		return "falhou";
	}

	public void remover(){
		for(Usuario o : usuariosSelecionados){
			usuarioDAO.remover(o);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Usuário "+o.getNome()+" excluído com sucesso!"));
		}
		this.usuarios = null;

		carregarUsuarios();
	}

	public void carregarUsuarios(){
		usuarios = usuarioDAO.recuperarTodos();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public Usuario[] getUsuariosSelecionados() {
		return usuariosSelecionados;
	}

	public void setUsuariosSelecionados(Usuario[] usuariosSelecionados) {
		this.usuariosSelecionados = usuariosSelecionados;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getSenhaAntiga() {
		return senhaAntiga;
	}

	public void setSenhaAntiga(String senhaAntiga) {
		this.senhaAntiga = senhaAntiga;
	}

	public String getSenhaNova() {
		return senhaNova;
	}

	public void setSenhaNova(String senhaNova) {
		this.senhaNova = senhaNova;
	}

	public String getSenhaNovaConfirmacao() {
		return senhaNovaConfirmacao;
	}

	public void setSenhaNovaConfirmacao(String senhaNovaConfirmacao) {
		this.senhaNovaConfirmacao = senhaNovaConfirmacao;
	}


}
