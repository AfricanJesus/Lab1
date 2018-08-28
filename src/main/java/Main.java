import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Main {
    static ArrayList<Student> listOfStudents = new ArrayList();

    public static void main(String[] args) throws IOException {

        parseFile("/Users/rn9627cw/Desktop/CS 385/Lab 1/src/main/Student.csv");

        for (Student s: listOfStudents) {
            System.out.println(s.toString());
        }

        ArrayList<Student> temp;
        temp = findBy("green", CategoryTypes.COLOR);
        Collections.sort(temp, new SortByName());
        System.out.println("Student with favorite color green: ");
        for (Student s : temp) {
            System.out.println(s.getFirstName());
        }

        temp = findBy("MN", CategoryTypes.COLOR);
        //Collections.sort(temp, new SortByName());
        System.out.println("Students with the hometown rochester: ");
        for (Student s : temp) {
            System.out.println(s.getFirstName());
        }
    }

    public static void parseFile(String fileToParse) throws IOException{

        String[] studentInfo = null;

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
                    if(s.getFirstName().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case LASTNAME:
                for (Student s: listOfStudents) {
                    if(s.getLastName().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case COLOR:
                for (Student s: listOfStudents) {
                    if(s.getFavColor().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case PETS:
                for(Student s: listOfStudents){
                    String[] pets = s.getPets();
                    for(int i = 0; i < pets.length; i++){
                        if (pets[i].contains(value.toLowerCase())) {
                            students.add(s);
                        }
                    }
                }
                return students;
            case HOMETOWN:
                for (Student s: listOfStudents) {
                    if(s.getHometown().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case MOVIES:
                for (Student s: listOfStudents) {
                    if(s.getFavMovies().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            case SHOESIZE:
                for (Student s: listOfStudents) {
                    if(s.getShoeSize().contains(value.toLowerCase())){
                        students.add(s);
                    }
                }
                return students;
            default:
                System.out.println("Incorrect Type");
        }
        return null;
    }

}
