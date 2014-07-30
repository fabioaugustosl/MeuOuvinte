package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import javax.ejb.Local;

import br.com.meuouvinte.modelos.Usuario;

@Local
public interface IUsuarioService extends Serializable {
	public byte[] gerarSenha(String senha) throws NoSuchAlgorithmException;
	public String salvar(Usuario usuario) throws Exception;
	public String alterarSenha(Usuario usuarioLogado) throws Exception;
	public Usuario autenticar(String email, String senha) throws Exception;
	public void remover( Usuario[] usuariosSelecionados);
}
