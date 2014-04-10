package br.com.meuouvinte.managedbean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.model.chart.PieChartModel;

import br.com.meuouvinte.models.GraficoModel;
import br.com.meuouvinte.servlet.BasePageBean;

import com.google.inject.Inject;


@ManagedBean(name = "graficoMB")
@SessionScoped
public class GraficoMB extends BasePageBean {

	private static final long serialVersionUID = 1L;

	@Inject
	private GraficoModel graficoModel;

	public PieChartModel getGraficoOuvinteCidade(){
		return graficoModel.getPizzaOuvinteCidade();
	}

	public PieChartModel getGraficoOuvinteIdade(){
		return graficoModel.getPizzaOuvinteIdade();
	}

	public void carregarDadosOuvintes(){
		graficoModel.carregarDadosOuvintes();
	}

	public GraficoModel getGraficoModel() {
		return graficoModel;
	}

}
