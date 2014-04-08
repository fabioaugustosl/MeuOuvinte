package br.com.meuouvinte.modelos.enumerados;

public enum EnumCidades {
	ABAETE("Abaeté"),
	BOM_DESPACHO("Bom Despacho"),
	DIVINOPOLIS("Divinópolis"),
	LAGOA_PRATA("Lagoa da Patra"),
	LUZ("Luz"),
	MARTINHO_CAMPOS("Martinho Campos"),
	MOEMA("Moema"),
	NOVA_SERRANA("Nova Serrana"),
	PITANGUI("Pitangui");

	private String nomeCidade;

	private EnumCidades(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}


}
