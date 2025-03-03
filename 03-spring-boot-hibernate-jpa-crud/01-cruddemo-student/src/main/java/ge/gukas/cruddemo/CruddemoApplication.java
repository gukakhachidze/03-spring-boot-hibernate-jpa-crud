package ge.gukas.cruddemo;

import ge.gukas.cruddemo.dao.StudentDAO;
import ge.gukas.cruddemo.dao.StudentDAOImpl;
import ge.gukas.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){
		return runner -> {
			// createStudent(studentDAO);

			createMultipleStudents(studentDAO);

			// readStudent(studentDAO);

			// queryForStudents(studentDAO);

			// queryStudentsByLastName(studentDAO);

			// updateStudent(studentDAO);

			// deleteStudent(studentDAO);

			// deleteAllStudent(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO){
		// ვქმნით სტუდენტის ობიექტს
		System.out.println("Creating new student object ...");
		Student tempStudent = new Student("Derrick", "Rose", "d.rose@gmail.com");

		// ვინახავთ სტუდენტის ობიექტს
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// გამოგვაქვს აიდი შენახული სტუდენტის
		System.out.println("Saved student. Generated id: " + tempStudent.getId());
	}

	private void createMultipleStudents(StudentDAO studentDAO){

		// ვქმნით რამოდენიმე სტუდენტს
		System.out.println("Creating 3 student objects ...");
		Student tempStudent1 = new Student("Michael", "Jordan", "mj23@gmail.com");
		Student tempStudent2 = new Student("Russell", "Westbrook", "westbrook@gmail.com");
		Student tempStudent3 = new Student("Kobe", "Bryant", "blackmamba@gmail.com");

		// ვინახავთ სტუდენტის ობიექტებს
		System.out.println("Saving the students ...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void readStudent(StudentDAO studentDAO){

		//  ვქმნით სტუდენტის ობიექტს
		System.out.println("Creating new student ...");
		Student tempStudent = new Student("Lebron", "James", "lebronjames@gmail.com");

		// ვინახავთ სტუდენტს
		System.out.println("Saving the student ...");
		studentDAO.save(tempStudent);

		// გამოგვაქვს აიდი შენახული სტუდენტის
		int theId = tempStudent.getId();
		System.out.println("Saved student. Generated id: " + theId);

		// ვაბრუნებთ სტუდენტს აიდის მიხედვით
		System.out.println("Retrieving student with id: " + theId);
		Student newStudent = studentDAO.findById(theId);

		// გამოგვაქვს სტუდენტი
		System.out.println("Found the student: " + newStudent);
	}

	private void queryForStudents(StudentDAO studentDAO){
		// მივიღოთ სტუდენტების სია
		List<Student> theStudents = studentDAO.findAll();

		// გამოვაჩინოთ სტუდენტები
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void queryStudentsByLastName(StudentDAO studentDAO){
		// სტუდენტების სიის მიღება
		List<Student> theStudents = studentDAO.findByLastName("James");

		// გამოჩენა ამ სიის
		for (Student tempStudent : theStudents){
			System.out.println(tempStudent);
		}
	}

	private void updateStudent(StudentDAO studentDAO){
		// ვიპოვოთ სტუდენტი აიდით
		int studentId = 1;
		Student myStudent = studentDAO.findById(studentId);

		// შევცვალოთ ის ველი რაც გვინდა (მაგ: სახელი)
		System.out.println("Updating student ...");
		myStudent.setFirstName("Derrick");

		// განვაახლოთ სტუდენტი
		studentDAO.update(myStudent);

		// გამოვაჩინოთ განახლებულის სტუდენტი
		System.out.println(myStudent);
	}

	private void deleteStudent(StudentDAO studentDAO){
		int studentId = 1;
		System.out.println("Deleting student id: " + studentId);
		studentDAO.delete(studentId);
	}

	private void deleteAllStudent(StudentDAO studentDAO){
		System.out.println("Deleting all students ...");
		int nomRowsDeleted = studentDAO.deleteAll();
		System.out.println("Deleted row count: " + nomRowsDeleted);
	}
}
