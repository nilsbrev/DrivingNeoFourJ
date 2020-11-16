import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;

class Neo4JHandler {
    Driver d;
    Session s;
    /**
     * @param uri URI pour attraper le service Neo4J : protocol:dns:port
     * @param username Identifiant pour s'identifier à la base de données
     * @param password Mot de passe pour s'identifier à la base de données
     */
     Neo4JHandler(String uri, String username, String password) {
        try {
            this.d = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
            // Je choisis la base de données nommée "movies"
            this.s = this.d.session(SessionConfig.forDatabase("movies"));
        } catch (Neo4jException e) {
            System.out.println("Couldn't connect to Neo4J DB");
            e.toString();
        }
    }
     void closeDriver(){
        this.s.close();
        this.d.close();
    }
     Session getSession() {
        return this.s;
    }
}
