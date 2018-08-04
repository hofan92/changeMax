package cn.changeyd.crawler.model;

public class Complication {
    

	private int complication_id;
	private String complication_name;
	private String complication_association_disease_id;
	private String complication_association_disease_name;

	public int getComplication_id() {
		return complication_id;
	}

	public void setComplication_id(int complication_id) {
		this.complication_id = complication_id;
	}

	public String getComplication_name() {
		return complication_name;
	}

	public void setComplication_name(String complication_name) {
		this.complication_name = complication_name;
	}

	public String getComplication_association_disease_id() {
		return complication_association_disease_id;
	}

	public void setComplication_association_disease_id(String complication_association_disease_id) {
		this.complication_association_disease_id = complication_association_disease_id;
	}

	public String getComplication_association_disease_name() {
		return complication_association_disease_name;
	}

	public void setComplication_association_disease_name(String complication_association_disease_name) {
		this.complication_association_disease_name = complication_association_disease_name;
	}

}
