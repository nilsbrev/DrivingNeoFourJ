import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Menu m = new Menu();
        Neo4JWrapper w = new Neo4JWrapper();

        while(true){
            m.printWelcomeMessage();
            Scanner s = new Scanner(System.in);
            int choix = s.nextInt();
            switch (choix){
                case 0 :
                    System.exit(0);
                    w.closeDriver();
                case 1 :
                    m.listAvailableMovies(w.getSession());
                    break;
                case 2 :
                    m.listAvailablePersons(w.getSession());
                    break;
                case 3 :
                    m.printTopThreeMovies(w.getSession());
                    break;
                case 4 :
                    System.out.println("Quel est le titre du film ?");
                    String movie = s.next();
                    m.printFiveRelatedMovies(w.getSession(),movie);
                    break;
                default:
                    System.out.println("Veuillez rentrer un numéro valide");
            }
        }
    }
}
