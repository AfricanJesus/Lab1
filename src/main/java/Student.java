import java.util.Arrays;

public class Student{

    private String firstName, lastName;
    private String favColor;
    private String[] pets;
    private String hometown;
    private String favMovies;
    private String shoeSize;

    public Student(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFavColor() {
        return favColor;
    }

    public void setFavColor(String favColor) {
        this.favColor = favColor;
    }

    public String[] getPets() {
        return pets;
    }

    public void setPets(String[] pets) {
        this.pets = pets;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getFavMovies() {
        return favMovies;
    }

    public void setFavMovies(String favMovies) {
        this.favMovies = favMovies;
    }

    public String getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(String shoeSize) {
        this.shoeSize = shoeSize;
    }

    @Override
    public String toString() {
        return
                "First Name: "      + firstName                 + '\n' +
                " Last Name: "      + lastName                  + '\n' +
                " Favorite Color: " + favColor        + '\n'    +
                " Pets: "           + Arrays.toString(pets)     + '\n' +
                " Hometown: "       + hometown + '\n'           +
                " Favorite Movie: " + favMovies                 + '\n' +
                " Shoe Size: "      + shoeSize                  + '\n';
    }

}
