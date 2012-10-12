package test.entity;

import java.text.MessageFormat;
import java.util.List;

import com.epam.hlibornet.annotation.BlackJack;
import com.epam.hlibornet.annotation.Id;

@BlackJack
public class Student {
	
	@Id
	private int id;
	
	private String firstName;
	
	private String secondName;
	
	private Faculty refFaculty;

	private List<Diploma> diplomas;
	
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
	
	public Faculty getRefFaculty() {
		return refFaculty;
	}

	public void setRefFaculty(Faculty refFaculty) {
		this.refFaculty = refFaculty;
	}
	
	public List<Diploma> getDiplomas() {
		return diplomas;
	}

	public void setDiplomas(List<Diploma> diplomas) {
		this.diplomas = diplomas;
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
				.format("Student [id={0}, firstName={1}, secondName={2}, refFaculty={3}, diplomas={4}]",
						id, firstName, secondName, refFaculty, diplomas);
	}
	
}
