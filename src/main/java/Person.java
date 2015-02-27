import com.google.common.base.Objects;

import java.util.Optional;

public class Person {

    private String name;
    private int height;
    private Gender gender;
    private int age;

    private Optional<Address> address = Optional.empty();

    public Person(String name, Gender gender, int height, int age, Optional<Address> address) {
        this.name = name;
        this.gender = gender;
        this.height = height;
        this.age = age;
        this.address = address;
    }

    public Person(String name, Gender gender, int height, int age) {
        this(name, gender, height, age, Optional.<Address>empty());
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public Gender getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public Optional<Address> getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person person = (Person) obj;

        return Objects.equal(this.name, person.name)
                && Objects.equal(this.age, person.age)
                && Objects.equal(this.gender, person.gender)
                && Objects.equal(this.height, person.height)
                && Objects.equal(this.address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                this.name,
                this.height,
                this.age,
                this.gender,
                this.address
        );
    }

    public static enum Gender {
        MALE, FEMALE;
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
