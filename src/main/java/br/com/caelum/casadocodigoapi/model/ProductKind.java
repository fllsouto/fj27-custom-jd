package br.com.caelum.casadocodigoapi.model;

public enum ProductKind {
	
	EBOOK("Livro digital"),
	PRINTED("Livro físico"),
	COMBO("Combo livro digital + físico");

	private String description;

	ProductKind(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
