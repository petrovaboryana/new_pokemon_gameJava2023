import Pokemons.ElectricPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PokemonMenu {
    private List<Pokemon> availablePokemon;
    private List<Pokemon> userPokemon;

    public PokemonMenu() {
        this.availablePokemon = new ArrayList<>();
        this.availablePokemon.add(new ElectricPokemon("Pikachu", Size.SMALL));
        this.availablePokemon.add(new WaterPokemon("Squirtle", Size.NORMAL));
        this.availablePokemon.add(new GroundPokemon("Geodude", Size.LARGE));
        //да добавя още

        this.userPokemon = new ArrayList<>();
    }

    public void choosePokemon() {
        displayAvailablePokemon();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            int selectedIndex;
            do {
                System.out.println("Choose your Pokemon #" + (i + 1) + " (select by number):");
                selectedIndex = scanner.nextInt();
            } while (selectedIndex < 1 || selectedIndex > availablePokemon.size());

            userPokemon.add(availablePokemon.remove(selectedIndex - 1));
        }
    }

    public List<Pokemon> getUserPokemon() {
        return userPokemon;
    }

    private void displayAvailablePokemon() {
        System.out.println("Available Pokemon:");
        for (int i = 0; i < availablePokemon.size(); i++) {
            System.out.println((i + 1) + ". " + availablePokemon.get(i).name);
        }
    }
}
