package it.trevis.beans;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DataExampleJPA")
public class DataExample implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_data_gen")
	@SequenceGenerator(sequenceName = "seq_data", name = "seq_data_gen")
	private BigInteger id;
	
	@Column(name = "something")
	private String something;

	public String getSomething() {
		return something;
	}

	public void setSomething(String something) {
		this.something = something;
	}

	public BigInteger getId() {
		return id;
	}

	@Override
	public String toString() {
		return "DataExample [id=" + id + ", something=" + something + "]";
	}

}
