package me.s4h.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
}


@Controller
@RequestMapping("/")
class MyController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/")
    public String index() {
        return "main";
    }

    @RequestMapping("/p/{id}")
    @ResponseBody
    public Person pp(@PathVariable Long id) {
        return personRepository.findOne(id); // don't use getOne()
    }


}

@Component
class CmdRunner implements CommandLineRunner {

    @Autowired
    PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("inserting....");
        personRepository.save(new Person("zhangsan", 10));
        personRepository.save(new Person("lisi", 10));
        personRepository.save(new Person("wangwu", 12));
        personRepository.save(new Person("zhaoliu", 11));
        System.out.println(personRepository.findOne(1L).getName());
    }
}

@Repository
interface PersonRepository extends JpaRepository<Person, Long> {
}

@Entity
class Person {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int age;

    public Person(){}// for jpa constructor

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}