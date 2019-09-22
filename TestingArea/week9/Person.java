package week9;

/**
 * Solution to exercise 4c, Week 10. NEeds commenting and a main method.
 *
 * @author Sam Scott
 */
public class Person implements Comparable<Person> {

    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", lastName=" + lastName + ", age=" + age + '}';
    }

    @Override
    public int compareTo(Person p) {
        if (lastName.compareTo(p.getLastName()) == 0) {
            if (firstName.compareTo(p.getFirstName()) == 0) {
                return age - p.getAge();
            }
            return firstName.compareTo(p.getFirstName());
        }
        return lastName.compareTo(p.getLastName());
    }

    public static void main(String[] args) {
    }

}
