package test.entity;

import java.text.MessageFormat;

import com.epam.hlibornet.annotation.BlackJack;
import com.epam.hlibornet.annotation.Id;

@BlackJack
public class Diploma {
	@Id
	private int id;
	
	private String name;

	@Override
	public String toString() {
		return MessageFormat.format("Diploma [id={0}, name={1}]", id, name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
