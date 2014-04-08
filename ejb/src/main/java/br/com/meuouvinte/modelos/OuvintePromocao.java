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
@Table(name = "OUVINTE_PROMOCAO")
@NamedQueries({
	@NamedQuery(name="OuvintePromocao.recuperarPorOuvinte",
			query="SELECT op FROM OuvintePromocao op WHERE op.ouvinte.id = :idOuvinte "),
	@NamedQuery(name="OuvintePromocao.recuperarPorPromocao",
			query="SELECT op FROM OuvintePromocao op WHERE op.promocao.id = :idPromocao "),
	@NamedQuery(name="OuvintePromocao.recuperarPorOuvinteEPromocao",
			query="SELECT op FROM OuvintePromocao op WHERE op.ouvinte.id = :idOuvinte AND op.promocao.id = :idPromocao ")
})
public class OuvintePromocao implements Serializable, EntidadePersistencia {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;

	@ManyToOne
    @JoinColumn(name = "id_promocao")
	private Promocao promocao;

	@ManyToOne
    @JoinColumn(name = "id_ouvinte")
	private Ouvinte ouvinte;

	@Temporal(TemporalType.DATE)
    @Column(name = "data_adicao")
	private Date dataAdicao;

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

	public Ouvinte getOuvinte() {
		return ouvinte;
	}

	public void setOuvinte(Ouvinte ouvinte) {
		this.ouvinte = ouvinte;
	}

	public Date getDataAdicao() {
		return dataAdicao;
	}

	public void setDataAdicao(Date dataAdicao) {
		this.dataAdicao = dataAdicao;
	}

}