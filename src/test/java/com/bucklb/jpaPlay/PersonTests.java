package com.bucklb.jpaPlay;

import com.bucklb.jpaPlay.model.Person;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

// Not sure I need tis ...
//@RunWith(SpringRunner.class)
//@SpringBootTest
public class PersonTests {

    final String FIRST_NAME="Fred";
    final String LAST_NAME ="Flintoff";
    final String EMAIL_ID  ="Freddy@Flintoff.com";
    final long PERSON_ID=69;

    final String TO_STRING="Person{" +
            "id=" + PERSON_ID +
            ", firstName='" + FIRST_NAME + '\'' +
            ", lastName='" + LAST_NAME + '\'' +
            ", email='" + EMAIL_ID + '\'' +
            '}';

    @Test
    public void NodAndSmile() {
    }

    @Test
    public void PersonConstructorAndGettersTest() {
        // Make sure what we create is what we see
        Person person=new Person(FIRST_NAME,LAST_NAME,EMAIL_ID);

        assertEquals(FIRST_NAME,person.getFirstName());
        assertEquals(LAST_NAME,person.getLastName());
        assertEquals(EMAIL_ID,person.getEmail());
    }

    @Test
    public void PersonToStringTest() {
        // Test that toString performs as expected
        Person person=new Person(FIRST_NAME,LAST_NAME,EMAIL_ID);
        person.setId(PERSON_ID);

        assertEquals(TO_STRING,person.toString());

    }

// Serializable stuff ...



}
