package test;

import java.util.List;

import org.apache.log4j.Logger;

import test.entity.Faculty;
import test.entity.Student;

import com.epam.hlibornet.Configuration;
import com.epam.hlibornet.Session;
import com.epam.hlibornet.SessionFactory;

public class Main {
	private static final Logger LOGGER = Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		LOGGER.debug("STARTED");
		Configuration configuration = new Configuration();
		configuration.setPassword("toor");
		configuration.setUsername("root");
		configuration.setUrl("jdbc:mysql://localhost:3306/");
		configuration.setScanPackage("test.entity");
		configuration.setBdName("hib");
		SessionFactory sessionFactory = SessionFactory.getInstance(configuration);
		Session session = sessionFactory.openSession();
		List<Student> all = session.getAll(Student.class);
		for(Student student : all){
			System.out.println(student);
		}
//		List<Faculty> faculties = session.getAll(Faculty.class); 
//		for(Faculty faculty : faculties){
//			System.out.println(faculty);
//		}
//		System.out.println(session.getById(Student.class, 1));
	}
}
