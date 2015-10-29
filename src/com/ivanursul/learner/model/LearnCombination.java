package com.ivanursul.learner.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class LearnCombination {

	@DatabaseField(generatedId=true)
    private int id;
	
	@DatabaseField
	private String originalLang;
	
	@DatabaseField
	private String translatedLang;
	
	@DatabaseField
	private String originalWord;
	
	@DatabaseField
	private String translatedWord;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOriginalLang() {
		return originalLang;
	}

	public void setOriginalLang(String originalLang) {
		this.originalLang = originalLang;
	}

	public String getTranslatedLang() {
		return translatedLang;
	}

	public void setTranslatedLang(String translatedLang) {
		this.translatedLang = translatedLang;
	}

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public String getTranslatedWord() {
		return translatedWord;
	}

	public void setTranslatedWord(String translatedWord) {
		this.translatedWord = translatedWord;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((originalLang == null) ? 0 : originalLang.hashCode());
		result = prime * result
				+ ((originalWord == null) ? 0 : originalWord.hashCode());
		result = prime * result
				+ ((translatedLang == null) ? 0 : translatedLang.hashCode());
		result = prime * result
				+ ((translatedWord == null) ? 0 : translatedWord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LearnCombination other = (LearnCombination) obj;
		if (id != other.id)
			return false;
		if (originalLang == null) {
			if (other.originalLang != null)
				return false;
		} else if (!originalLang.equals(other.originalLang))
			return false;
		if (originalWord == null) {
			if (other.originalWord != null)
				return false;
		} else if (!originalWord.equals(other.originalWord))
			return false;
		if (translatedLang == null) {
			if (other.translatedLang != null)
				return false;
		} else if (!translatedLang.equals(other.translatedLang))
			return false;
		if (translatedWord == null) {
			if (other.translatedWord != null)
				return false;
		} else if (!translatedWord.equals(other.translatedWord))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LearnCombination [id=" + id + ", originalLang=" + originalLang
				+ ", translatedLang=" + translatedLang + ", originalWord="
				+ originalWord + ", translatedWord=" + translatedWord + "]";
	}
	
}
