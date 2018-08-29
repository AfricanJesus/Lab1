import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class Main {
    static ArrayList<Student> listOfStudents = new ArrayList();
    static CategoryTypes[] categoryValues = CategoryTypes.values();
    public static void main(String[] args) throws IOException {

        parseFile("src/main/Student.csv");

        ArrayList<Student> results;
        int typeValue;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            showMenu();
            do {
                System.out.println("Enter a value from the menu:");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid value, try again.");
                    scanner.next(); // this is important!
                }
                typeValue = scanner.nextInt();
            } while (typeValue < -1 || typeValue > 6);
            if (typeValue == -1) {
                System.exit(1);
            }

            System.out.println("Enter value to search by: ");
            String value = scanner.next();

            results = findBy(value, categoryValues[typeValue]);

            if (results == null || results.size() == 0) {
                System.out.println("/nNo Results");
            } else {
                System.out.println("Results("+ results.size()+ "): ");
                System.out.println("-----------------------------------------------");
                Collections.sort(results, new SortByName());
                for (Student s : results) {
                    System.out.println(s.toString());
                }
                System.out.println("-----------------------------------------------");
            }
        }

    }



    public static void parseFile(String fileToParse) throws IOException{

        String[] studentInfo;

        CSVReader reader = new CSVReader(new FileReader(fileToParse));
        reader.readNext();
        while((studentInfo = reader.readNext()) != null){
            for(int i = 0; i < studentInfo.length; i++){
                studentInfo[i] = studentInfo[i].toLowerCase();
                if(studentInfo[i].contains("n/a") || studentInfo[i].contains("none") || studentInfo[i].contains("no pets")){
                    studentInfo[i] = null;
                }
            }

            Student student = new Student();
            student.setFirstName(studentInfo[0]);
            student.setLastName(studentInfo[1]);
            student.setFavColor(studentInfo[2]);
            student.setPets(parsePets(studentInfo[3]));
            student.setHometown(studentInfo[4]);
            student.setFavMovies(studentInfo[5]);
            student.setShoeSize(studentInfo[6]);
            listOfStudents.add(student);
        }
    }



    public static String[] parsePets(String pets){
        if(pets != null){
            if(pets.contains(",")){
                return pets.split(",");
            }else if(pets.contains("/")){
                return pets.split("/");
            }else {
                String[] listOfPets = new String[]{pets};
                return listOfPets;
            }
        }else
            return null;
    }

    public static ArrayList<Student> findBy(String value, CategoryTypes type){
        ArrayList<Student> students = new ArrayList();

        switch(type){
            case FIRSTNAME:
                for (Student s: listOfStudents) {
                    if(s.getFirstName() != null && s.getFirstName().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case LASTNAME:
                for (Student s: listOfStudents) {
                    if(s.getLastName() != null && s.getLastName().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case COLOR:
                for (Student s: listOfStudents) {
                    if(s.getFavColor() != null && s.getFavColor().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case PETS:
                for(Student s: listOfStudents){
                    String[] pets = s.getPets();
                    if(pets != null) {
                        for (int i = 0; i < pets.length; i++) {
                            if (pets[i].contains(value.toLowerCase())) {
                                students.add(s);
                            }
                        }
                    }
                }
                return students;
            case HOMETOWN:
                for (Student s: listOfStudents) {
                    if(s.getHometown() != null && s.getHometown().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case MOVIES:
                for (Student s: listOfStudents) {
                    if(s.getFavMovies() != null && s.getFavMovies().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case SHOESIZE:
                for (Student s: listOfStudents) {
                    if(s.getShoeSize() != null && s.getShoeSize().contentEquals(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            default:
                System.out.println("Incorrect Type");
        }
        return null;
    }


    public static void showMenu() {
        System.out.println(
                "Find By Options:" + "\n" +
                        "   0(First Name) " + "\n" +
                        "   1(Last Name)" + "\n" +
                        "   2(Color) " + "\n" +
                        "   3(Pets)" + "\n" +
                        "   4(Hometown)" + "\n" +
                        "   5(Movie)" + "\n" +
                        "   6(Shoe Size)" + "\n" +
                        "   -1(Exit)"

        );


    }

}
