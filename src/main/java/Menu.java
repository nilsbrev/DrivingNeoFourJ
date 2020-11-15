public class Menu {

    void printWelcomeMessage(){
        System.out.println("Tapez le chiffre correspondant à la commande voulue");
        System.out.println("(1) : Lister les films disponibles ");
        System.out.println("(2) : Lister les personnes disponibles ");
        System.out.println("(3) : Afficher les trois films les mieux notés");
        System.out.println("(4) : Afficher au plus cinq films proches \n");
        System.out.println("(0) : Quitter l'application");
    }

    void listAvailableMovies(){
        System.out.println(1);

    }
    void listAvailablePersons(){
        System.out.println(2);

    }
    void printTopThreeMovies(){
        System.out.println(3);

    }

    void printFiveRelatedMovies(String movie){
        System.out.println("4 "+movie);

    }
}
