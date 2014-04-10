package br.com.meuouvinte.modelos;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="OUVINTE")
@NamedQueries({
	@NamedQuery(name="Ouvinte.recuperarPorIdPromocao",
				query="SELECT o FROM Ouvinte o JOIN o.ouvintePromocoes s WHERE s.promocao.id = :idPromocao"),
	@NamedQuery(name="Ouvinte.ouvintesPorId",
				query="SELECT o FROM Ouvinte o WHERE o.id in (:ids)"),
	@NamedQuery(name="Ouvinte.pesquisar",
				query="SELECT o FROM Ouvinte o "
						+ " WHERE  UPPER(o.cpf) LIKE UPPER(:padrao) "
						+ " OR UPPER(o.nome) LIKE UPPER(:padrao) "
						+ " OR UPPER(o.telefone) LIKE UPPER(:padrao)")
})
public class Ouvinte implements Serializable, Comparable<Ouvinte>, EntidadePersistencia{

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue
	private Integer id;
	
	@NotNull
    @Column(name = "nome", length=100, nullable=false)
	private String nome;
	
	@Column(name = "cpf", length=20)
	private String cpf;
	
	@Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento", length=12)
	private Date dataNascimento;
	
	@Column(name = "endereco", length=100)
	private String endereco;
	
	@Column(name = "bairro", length=100)
	private String bairro;
	
	@Column(name = "cidade", length=100)
	private String cidade;
	
	@Column(name = "estado", length=2)
	private String estado;
	
	@Column(name = "telefone", length=20)
	private String telefone;
	
	@Column(name = "email", length=100)
	private String email;

	@OneToMany(mappedBy="sorteado")
	private List<Sorteio> sorteios;
	
	@OneToMany(mappedBy="ouvinte")
	private List<OuvintePromocao> ouvintePromocoes;
	
	@Transient
	private Promocao promocao;

	@Transient
	private Sorteio sorteado ;

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Promocao getPromocao() {
		return promocao;
	}

	public void setPromocao(Promocao promocao) {
		this.promocao = promocao;
	}
	
	public List<Sorteio> getSorteios() {
		return sorteios;
	}

	public void setSorteios(List<Sorteio> sorteios) {
		this.sorteios = sorteios;
	}

	public String getStatusSorteado() {
		return (isSorteado() ) ? "Sim" : "Não";
	}

	public Boolean isSorteado() {
		return (sorteios == null || sorteios.isEmpty()) ? Boolean.FALSE : Boolean.TRUE;
	}

	public Sorteio getSorteado() {
		return sorteado;
	}

	public void setSorteado(Sorteio sorteado) {
		this.sorteado = sorteado;
	}
	
	public List<OuvintePromocao> getOuvintePromocoes() {
		return ouvintePromocoes;
	}

	public void setOuvintePromocoes(List<OuvintePromocao> ouvintePromocoes) {
		this.ouvintePromocoes = ouvintePromocoes;
	}

	@Override
	public int compareTo(Ouvinte o) {
		if(o == null){
			return 1;
		}

		if(this.getSorteado()!=null && o.getSorteado() !=null && o.getSorteado().getDataSorteio()!=null && this.getSorteado().getDataSorteio() != null){
			Integer a = o.getSorteado().getDataSorteio().compareTo(this.getSorteado().getDataSorteio());
			if(a != null){
				return a;
			}
		} else if(this.getSorteado()==null && o.getSorteado() !=null){
			return 1;
		} else if(this.getSorteado()!=null && o.getSorteado() ==null){
			return -1;
		}

		return o.getNome().compareTo(this.getNome());
	}

	/**
	 * Calcula e retorna idade do ouvinte
	 * @param dataNasc
	 * @param pattern
	 * @return
	 */
	public Integer getIdade(){
		if(this.getDataNascimento() == null){
			return null;
		}

        Calendar dateOfBirth = new GregorianCalendar();
        dateOfBirth.setTime(this.getDataNascimento());

        Calendar today = Calendar.getInstance();

        int age = today.get(Calendar.YEAR) - dateOfBirth.get(Calendar.YEAR);

        dateOfBirth.add(Calendar.YEAR, age);

        if (today.before(dateOfBirth)) {
            age--;
        }
        return age;
    }
}
