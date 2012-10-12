package test.entity;

import java.text.MessageFormat;

import com.epam.hlibornet.annotation.BlackJack;

@BlackJack
public class Student {
	
	private int id;
	
	private String firstName;
	
	private String secondName;
	
	private int refFaculty;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}
	
	
	public int getRefFaculty() {
		return refFaculty;
	}

	public void setRefFaculty(int refFaculty) {
		this.refFaculty = refFaculty;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Student other = (Student) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return MessageFormat
				.format("Student [id={0}, firstName={1}, secondName={2}, refFaculty={3}]",
						id, firstName, secondName, refFaculty);
	}
	
	
	
}
