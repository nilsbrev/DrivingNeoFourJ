import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Neo4JHandler handler = new Neo4JHandler("bolt://localhost:7687","javadriver","javadriver");

        // Menu à choix qui permet d'éxécuter les commandes
        while(true){
            Command.printWelcomeMessage();
            Scanner s = new Scanner(System.in);
            int choix = s.nextInt();
            switch (choix){
                case 0 :
                    System.exit(0);
                    handler.closeDriver();
                case 1 :
                    Command.listAvailableMovies(handler.getSession());
                    break;
                case 2 :
                    Command.listAvailablePersons(handler.getSession());
                    break;
                case 3 :
                    Command.printTopThreeMovies(handler.getSession());
                    break;
                case 4 :
                    System.out.println("Quel est le titre du film ?");
                    Scanner x = new Scanner(System.in);
                    String movie = x.nextLine();
                    Command.printFiveRelatedMovies(handler.getSession(),movie);
                    break;
                default:
                    System.out.println("Veuillez rentrer un numéro valide");
            }
        }
    }
}
