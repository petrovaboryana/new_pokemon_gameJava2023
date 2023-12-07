import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonMenu {//да оправя сканера
    private List<Pokemon> availablePokemon;
    private List<Pokemon> userPokemon;

    public PokemonMenu() {
        this.availablePokemon = new ArrayList<>();
        this.availablePokemon.add(new ElectricPokemon("Pikachu", Size.SMALL));
        this.availablePokemon.add(new WaterPokemon("Squirtle", Size.NORMAL));
        this.availablePokemon.add(new GroundPokemon("Geodude", Size.LARGE));
        this.availablePokemon.add(new WaterPokemon("Slowbro",Size.NORMAL));
        this.availablePokemon.add(new ElectricPokemon("Venusaur",Size.SMALL));
        this.userPokemon = new ArrayList<>();
    }

    public void choosePokemon() {
        displayAvailablePokemon();
        Scanner scanner = new Scanner(System.in);//тук

        for (int i = 0; i < 3; i++) {
            int selectedIndex;
            do {
                System.out.println("Choose your Pokemon #" + (i + 1) + " (select by number):");
                selectedIndex = scanner.nextInt() + 1;
                if (selectedIndex < 1 || selectedIndex > (availablePokemon.size()+1)) {
                    System.out.println("Invalid choice. Please select a valid Pokemon between 1-5.");
                }else {
                    Pokemon selectedPokemon = availablePokemon.get(selectedIndex - 2);
                    if(!userPokemon.contains(selectedPokemon)){
                        userPokemon.add(selectedPokemon);
                        break;
                    }else {
                        System.out.println("these pokemon is not available");
                    }
                }
            } while(true);
            System.out.println("You successfully add " + userPokemon.get(i).name + " to your list of Pokemons");
        }
    }

    public List<Pokemon> getUserPokemon() {
        return userPokemon;
    }

    private void displayAvailablePokemon() {
        System.out.println("Available Pokemons are:");
        for (int i = 0; i < availablePokemon.size(); i++) {
            System.out.println((i + 1) + ". " + availablePokemon.get(i).toString());
        }
    }
    public static void displayUserPokemon(List<Pokemon> userPokemon) {
        System.out.println("Your Pokemon:");
        for (int i = 0; i < userPokemon.size(); i++) {
            Pokemon pokemon = userPokemon.get(i);
            System.out.println((i + 1) + ". " + pokemon.name + " (HP: " + pokemon.healthPoints + ")");
        }
    }
    public static Pokemon chooseActivePokemon(List<Pokemon> userPokemon) {
        Scanner scanner = new Scanner(System.in);//тук

        System.out.println("Choose the active Pokemon for this round:");
        displayUserPokemon(userPokemon);
        int selectedIndex;

        do {
            System.out.println("Select by number:");
            selectedIndex = scanner.nextInt();
            if (selectedIndex < 1 || selectedIndex > userPokemon.size()) {
                System.out.println("Invalid choice. Please select a valid Pokemon.");
            }
        } while (selectedIndex < 1 || selectedIndex > userPokemon.size());

        return userPokemon.get(selectedIndex - 1);
    }

}
