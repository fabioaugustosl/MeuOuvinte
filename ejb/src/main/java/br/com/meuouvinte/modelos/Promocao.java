package br.com.meuouvinte.modelos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PROMOCAO")
public class Promocao implements Serializable, EntidadePersistencia {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue
	private Integer id;

	@NotNull
    @Column(name = "nome", length=100, nullable=false)
	private String nome;

    @Column(name = "descricao", length=1024)
	private String descricao;

    @Column(name = "premiacao", length=1024)
	private String premiacao;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_inicio", length=12)
	private Date dataInicio = new Date();

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fim", length=12)
	private Date dataFim = new Date();

    @OneToMany(mappedBy="promocao")
	private List<OuvintePromocao> ouvintesPromocao;
    
    @OneToMany(mappedBy="promocao")
    private List<Sorteio> sorteios;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}

	public List<OuvintePromocao> getOuvintesPromocao() {
		return ouvintesPromocao;
	}

	public void setOuvintesPromocao(List<OuvintePromocao> ouvintesPromocao) {
		this.ouvintesPromocao = ouvintesPromocao;
	}
	
	public List<Sorteio> getSorteios() {
		return sorteios;
	}

	public void setSorteios(List<Sorteio> sorteios) {
		this.sorteios = sorteios;
	}

	public Boolean getAtiva(){
		if(dataFim!= null && dataInicio != null){
			Date c = new Date();
			return c.after(getDataInicio()) && c.before(getDataFim());
		}
		return Boolean.FALSE;
	}

	public String getPremiacao() {
		return premiacao;
	}

	public void setPremiacao(String premiacao) {
		this.premiacao = premiacao;
	}

	public String getNomeAtiva(){
		return getNome() + ((getAtiva()) ? " (Ativa)" : "");
	}

}
