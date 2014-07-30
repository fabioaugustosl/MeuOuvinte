package br.com.meuouvinte.modelos;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SORTEIO")
@NamedQueries({
	@NamedQuery(name="Sorteio.recuperarPorIdOuvinte",
				query="SELECT s FROM Sorteio s WHERE s.sorteado.id = :idOuvinte"),
	@NamedQuery(name="Sorteio.recuperarPorIdPromocao",
				query="SELECT s FROM Sorteio s WHERE s.promocao.id = :idPromocao")
})
public class Sorteio implements Serializable, EntidadePersistencia {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id_promocao")
	private Promocao promocao;

	@ManyToOne
	@JoinColumn(name="id_sorteado")
	private Ouvinte sorteado;

	@Column(name="email_responsavel", length=100, nullable=false)
	private String emailUsuarioResponsavel;

	@Temporal(TemporalType.DATE)
    @Column(name = "data_sorteio", length=12)
	private Date dataSorteio;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}

	public Ouvinte getSorteado() {
		return sorteado;
	}

	public void setSorteado(Ouvinte sorteado) {
		this.sorteado = sorteado;
	}

	public String getEmailUsuarioResponsavel() {
		return emailUsuarioResponsavel;
	}

	public void setEmailUsuarioResponsavel(String emailUsuarioResponsavel) {
		this.emailUsuarioResponsavel = emailUsuarioResponsavel;
	}

	public Date getDataSorteio() {
		return dataSorteio;
	}

	public void setDataSorteio(Date dataSorteio) {
		this.dataSorteio = dataSorteio;
	}

}
