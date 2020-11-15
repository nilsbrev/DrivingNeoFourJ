import org.neo4j.driver.*;
import org.neo4j.driver.exceptions.Neo4jException;

public class Neo4JWrapper {
    Driver d;
    Session s;

    /**
     *
     * @param uri URI of your Neo4J DB protocol:dns:port
     * @param username IDS to log in
     * @param password IDS to log in
     */
    public Neo4JWrapper(String uri, String username, String password) {
        try {
            this.d = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
            this.s = this.d.session();
        } catch (Neo4jException e) {
            System.out.println("Couldn't connect to Neo4J DB");
            e.toString();
        }
    }

    public void closeDriver(){
        this.s.close();
        this.d.close();
    }

    public Session getSession() {
        return this.s;
    }
}
