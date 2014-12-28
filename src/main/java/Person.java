import java.util.Optional;

public class Person {

    public static char FEMALE = 'F';
    public static char MALE = 'M';

    private String name;
    private int height;
    private char gender;
    private int age;

    private Optional<Address> address = Optional.empty();

    public Person(String name, char gender, int height, int age, Optional<Address> address) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public char getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    public static class Address {

        private String street;
        private int streetNo;
        private int postCode;

        public Address(String street, int streetNo, int postCode) {
            this.street = street;
            this.streetNo = streetNo;
            this.postCode = postCode;
        }

        public String getStreet() {
            return street;
        }

        public int getStreetNo() {
            return streetNo;
        }

        public int getPostCode() {
            return postCode;
        }

    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }

}
