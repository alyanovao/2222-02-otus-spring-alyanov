package ru.otus.dao;

import org.springframework.stereotype.Repository;
import ru.otus.ApplicationRuntimeException;
import ru.otus.domain.Person;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@Repository
public class PersonDaoSimple implements PersonDao {
    @Override
    public Person inputPerson() {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Person person = new Person();
        System.out.println("Good mooning, input your name:");
        try {
            person.setName(reader.readLine());
        } catch (IOException e) {
            log(e);
            throw new ApplicationRuntimeException(e);
        }
        System.out.println("Input your lastname:");
        try {
            person.setLastName(reader.readLine());
        } catch (IOException e) {
            log(e);
            throw new ApplicationRuntimeException(e);
        }
        return person;
    }

    private void log(IOException e) {
        System.out.println(String.format("Exceprion :: %s \n" +
                "Method :: %s \n" +
                " stackTrace :: %s", e.getClass(), Arrays.stream(getClass().getMethods()).findFirst(), e.getMessage()));
    }
}
