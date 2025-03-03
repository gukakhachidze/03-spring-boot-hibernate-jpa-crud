package ge.gukas.cruddemo.dao;

import ge.gukas.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO{

    //ვაცხადებთ ფილდს ენთითი მენეჯერისთვის
    private EntityManager entityManager;

    // ვაინჯექთებთ ენთით მენეჯერს კონსტრუქტორ ინჯექშენით
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // იმპლემენტაცია შენახვის მეთოდის
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        // ქვერის დაწერა
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);

        // დაბრუნება ქვერის რეზულტატის
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // ქვერის შექმნა
        TypedQuery<Student> theQuery = entityManager.createQuery(
                "FROM Student WHERE lastName=:theData", Student.class);

        // პარამეტრების მინიჭება ქვერიზე
        theQuery.setParameter("theData", theLastName);

        // დაბრუნება რეზულტატის
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // ვაბრუნებთ სტუდენტს(ვპოულობთ)
        Student theStudent = entityManager.find(Student.class, id);

        // ვშლით სტუდენტს
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM  Student").executeUpdate();

        return numRowsDeleted;
    }


}




