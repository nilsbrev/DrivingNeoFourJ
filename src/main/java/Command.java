import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Record;
import org.neo4j.driver.types.Node;

import java.util.*;
import java.util.stream.Collectors;

import static org.neo4j.driver.Values.parameters;
/** Classe abstraite ayant pour but de rendre le code plus clair en rassemblant les commandes du menu **/
abstract class Command {
    // Affichage du message d'acceuil
    static void printWelcomeMessage(){
        System.out.println("Tapez le chiffre correspondant à la commande voulue");
        System.out.println("(1) : Lister les films disponibles ");
        System.out.println("(2) : Lister les personnes disponibles ");
        System.out.println("(3) : Afficher les trois films les mieux notés");
        System.out.println("(4) : Afficher au plus cinq films proches \n");
        System.out.println("(0) : Quitter l'application");
    }
    // Question 3.1 du TP
    static void listAvailableMovies(Session session){
        Result result = session.run(
                "MATCH (m:Movie) RETURN m.title AS title, m.released as released, " +
                        "m.tagline as tagline ORDER BY released DESC");
        // Itération sur les résultats (stream de records)
        System.out.println("Les films disponibles :");
        while (result.hasNext())
        {
            Record record = result.next();
            String title = record.get("title").asString();
            int released = record.get("released").asInt();
            String tagline = record.get("tagline").asString();
            System.out.println("      "+title+" - "+released+" ("+tagline+")");
        }
    }
    // Question 3.2 du TP
    static void listAvailablePersons(Session session){
        List<String> names = new ArrayList<String>();
        Result result = session.run(
                "MATCH (p:Person)-[r]-(m:Movie) " +
                        "RETURN p.name AS name, p.born as born, type(r) as type, " +
                        "m.title as title ORDER BY name, type, title");
        // Itération sur les résultats (stream de records)
        System.out.println("Personnes disponibles :");
        while (result.hasNext())
        {
            Record record = result.next();
            String born;
            String name = record.get("name").asString();
            if(record.get("born").isNull()){
                born = "?";
            }
            else{
                born = record.get("born").toString();
            }
            String type = record.get("type").asString();
            String title = record.get("title").asString();
            // Regrouper par name
            if(!(names.contains(name))){
                names.add(name);
                System.out.println(name+" ("+born+")");
            }
            System.out.println("        "+type+" ["+title+"]");
        }

    }
    // Question 3.3 du TP
    static void printTopThreeMovies(Session session){
        Result result = session.run(
                "MATCH (m:Movie)-[r:REVIEWED]-(p:Person)" +
                        "WITH m.title as title, avg(r.rating) AS rating " +
                        "return title, rating ORDER BY rating DESC LIMIT 3");
        // Itération sur les résultats (stream de records)
        System.out.println("3 Films les mieux notés :");
        while (result.hasNext())
        {
            Record record = result.next();
            String title = record.get("title").asString();
            int rating = (record.get("rating").asInt());
            System.out.println("      "+title+" - "+rating);
        }

    }
    // Question 3.4 du TP
    static void printFiveRelatedMovies(Session session, String movie){
        Result result = session.run(
                "MATCH (m1:Movie)<-[:ACTED_IN]-(p:Person)-[:ACTED_IN]->(m2:Movie) " +
                        "WHERE m1.title = $movietitle " +
                        "RETURN m2.title as title, count(p) as value " +
                        "ORDER BY count(p) DESC, title " +
                        "LIMIT(5)",
                parameters("movietitle", movie));
        // Gérer l'eventualité où le titre du film n'a pas été trouvé
        if(!result.hasNext()){
            System.out.println("Le film "+movie+" n'existe pas dans la base...");
        }
        else{
            System.out.println("Au plus 5 Films proches de "+movie);
        }
        // Affichage des résultats
        while (result.hasNext())
        {
            Record record = result.next();
            String title = record.get("title").asString();
            int value = record.get("value").asInt();
            System.out.println("    "+title+" - "+value);

        }
    }
}
