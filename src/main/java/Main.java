import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Menu m = new Menu();

        while(true){
            m.printWelcomeMessage();
            Scanner s = new Scanner(System.in);
            int choix = s.nextInt();
            switch (choix){
                case 0 :
                    System.exit(0);
                case 1 :
                    m.listAvailableMovies();
                    break;
                case 2 :
                    m.listAvailablePersons();
                    break;
                case 3 :
                    m.printTopThreeMovies();
                    break;
                case 4 :
                    System.out.println("Quel est le titre du film ?");
                    String movie = s.next();
                    m.printFiveRelatedMovies(movie);
                    break;
                default:
                    System.out.println("Veuillez rentrer un numéro valide");
            }

        }

    }
}
