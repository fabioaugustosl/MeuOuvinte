package br.com.virtz.ouvinte.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;

import br.com.meuouvinte.modelos.Usuario;
import br.com.virtz.ouvinte.service.UsuarioService;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB  {

	private static final long serialVersionUID = 1L;

	@EJB
	private UsuarioService usuarioService;

	private String email;

	private String senha;
	
	private Usuario usuario;

	private Usuario usuarioLogado;

	private Usuario[] usuariosSelecionados;
	private List<Usuario> usuarios;

	private String senhaAntiga;
	private String senhaNova;
	private String senhaNovaConfirmacao;

	public UsuarioMB() {
		super();
	}

	public String autenticar() {
		try {
			usuarioLogado = usuarioService.autenticar(getEmail(), getSenha());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Email ou Senha incorretos!"));
			e.printStackTrace();
		}
		return null;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String alterarSenha(ActionEvent event) throws Exception{
		//usuarioService.alterarSenha(usuario);
		
		if(usuarioLogado.getSenha().equals(new String(usuarioService.gerarSenha(senhaAntiga)))){
			if(!senhaNova.equals(senhaNovaConfirmacao)){
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha novas n�o s�o iguais"));
				return null;
			}

			usuarioLogado.setSenha(new String(usuarioService.gerarSenha(senhaNova)));

			usuarioService.salvar(usuarioLogado);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha alterada com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Senha antiga n�o � igual a senha do usu�rio logado!"));
		}
		return null;
		
	}

	public String salvarAjax(ActionEvent event) throws NoSuchAlgorithmException {
		try {
			usuarioService.salvar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Usuario salvo com sucesso!"));
//		carregarUsuarios();
		return null;
	}
	
	public void novo(){
		this.usuario = new Usuario();
	}

	public void remover(){
		usuarioService.remover(usuariosSelecionados);
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage("Usuário excluidos com sucesso!"));
	}
	
	public void prepararListagem(){
		usuarioService.carregarUsuarios();
	}

		

}
