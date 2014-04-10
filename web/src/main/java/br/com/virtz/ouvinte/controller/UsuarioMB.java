package br.com.meuouvinte.managedbean;

import java.security.NoSuchAlgorithmException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import br.com.meuouvinte.models.UsuarioModel;
import br.com.meuouvinte.servlet.BasePageBean;

import com.google.inject.Inject;

@ManagedBean(name = "usuarioMB")
@SessionScoped
public class UsuarioMB extends BasePageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioModel usuarioModel;

	private String email;

	private String senha;

	public UsuarioMB() {
		super();
	}

	public String autenticar() {
		return usuarioModel.autenticar(getEmail(), getSenha());
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

	public String alterarSenha(ActionEvent event) throws NoSuchAlgorithmException{
		return usuarioModel.alterarSenha(event);
	}

	public String salvarAjax(ActionEvent event) throws NoSuchAlgorithmException {
		return usuarioModel.salvar(event);
	}

	public void prepararListagem(){
		usuarioModel.carregarUsuarios();
	}

	public void novo(){
		usuarioModel.novo();
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}


}
