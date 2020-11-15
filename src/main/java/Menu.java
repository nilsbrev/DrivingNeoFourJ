import org.neo4j.driver.Session;

import static org.neo4j.driver.Values.parameters;

public class Menu {

    void printWelcomeMessage(){
        System.out.println("Tapez le chiffre correspondant à la commande voulue");
        System.out.println("(1) : Lister les films disponibles ");
        System.out.println("(2) : Lister les personnes disponibles ");
        System.out.println("(3) : Afficher les trois films les mieux notés");
        System.out.println("(4) : Afficher au plus cinq films proches \n");
        System.out.println("(0) : Quitter l'application");
    }

    void listAvailableMovies(Session session){
        session.run("")

    }
    void listAvailablePersons(Session session){
        System.out.println(2);

    }
    void printTopThreeMovies(Session session){
        System.out.println(3);

    }

    void printFiveRelatedMovies(Session session, String movie){
        session.run(
                "MATCH (a:Person) WHERE a.name STARTS WITH $m RETURN a.name AS name",
                parameters("m", movie));
        System.out.println("4 "+movie);

    }
}
