package cn.changeyd.crawler.model;

public class SymptomDi {
	private int symptom_di_id;
	private String symptom_di_name;
	private int symptom_association_disease_id;
	private String symptom_association_disease_name;

	public int getSymptom_di_id() {
		return symptom_di_id;
	}

	public void setSymptom_di_id(int symptom_di_id) {
		this.symptom_di_id = symptom_di_id;
	}

	public String getSymptom_di_name() {
		return symptom_di_name;
	}

	public void setSymptom_di_name(String symptom_di_name) {
		this.symptom_di_name = symptom_di_name;
	}

	public int getSymptom_association_disease_id() {
		return symptom_association_disease_id;
	}

	public void setSymptom_association_disease_id(int symptom_association_disease_id) {
		this.symptom_association_disease_id = symptom_association_disease_id;
	}

	public String getSymptom_association_disease_name() {
		return symptom_association_disease_name;
	}

	public void setSymptom_association_disease_name(String symptom_association_disease_name) {
		this.symptom_association_disease_name = symptom_association_disease_name;
	}

}
