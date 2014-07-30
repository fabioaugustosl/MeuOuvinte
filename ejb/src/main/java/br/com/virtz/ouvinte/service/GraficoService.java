package br.com.virtz.ouvinte.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.meuouvinte.modelos.Ouvinte;

@Stateless
public class GraficoService implements IGraficoService {

	private static final long serialVersionUID = 1L;

//	private PieChartModel pizzaOuvinteCidade;
//	private PieChartModel pizzaOuvinteIdade;

	@EJB
	private IOuvinteService ouvinteModel;

	private List<Ouvinte> ouvintes;

	/**
	 * Recupera todos os ouvintes do BD
	 */
	public void carregarDadosOuvintes(){
		ouvintes = ouvinteModel.recuperarOuvintes();
		/*criarPizzaOuvinteCidade();
		criarPizzaOuvinteIdade();*/
	}


	public Map<String, Integer> criarPizzaOuvinteCidade() {

		Map<String, Integer> mapOuvintesCidade = new HashMap<String, Integer>();

		for(Ouvinte o : ouvintes){

			if(o.getCidade() == null){
				continue;
			}

			Integer qtd = mapOuvintesCidade.get(o.getCidade());
			if(qtd == null){
				qtd = 0;
			}
			mapOuvintesCidade.put(o.getCidade(), (qtd+1));
		}

		/*pizzaOuvinteCidade = new PieChartModel();
		for(String cid : mapOuvintesCidade.keySet()){
			pizzaOuvinteCidade.set(cid, mapOuvintesCidade.get(cid));
		}*/
		
		return mapOuvintesCidade;

    }


	public Map<String, Integer> criarPizzaOuvinteIdade() {

		Map<String, Integer> mapOuvintesIdade = new HashMap<String, Integer>();

		for(Ouvinte o : ouvintes){
			Integer idade = o.getIdade();
			if(idade == null){
				continue;
			}

			String faixaEtaria = getFaixaEtaria(idade);

			Integer qtd = mapOuvintesIdade.get(faixaEtaria);
			if(qtd == null){
				qtd = 0;
			}
			mapOuvintesIdade.put(faixaEtaria, (qtd+1));
		}

		/*pizzaOuvinteIdade = new PieChartModel();
		for(String faixa : mapOuvintesIdade.keySet()){
			pizzaOuvinteIdade.set(faixa, mapOuvintesIdade.get(faixa));
		}*/
		
		return mapOuvintesIdade;

    }


	private String getFaixaEtaria(Integer idade) {
		if(idade <= 20){
			return "0-20";
		} else if(idade > 20 && idade <= 30){
			return "21-30";
		} else if(idade > 30 && idade <= 40){
			return "31-40";
		} else if(idade > 40 && idade <= 50){
			return "41-50";
		} else if(idade > 50 && idade <= 60){
			return "51-60";
		} else {
			return "acima 61 ";
		}
	}


	/*public PieChartModel getPizzaOuvinteCidade() {
		return pizzaOuvinteCidade;
	}


	public PieChartModel getPizzaOuvinteIdade() {
		return pizzaOuvinteIdade;
	}*/

}
