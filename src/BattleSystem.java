import Pokemons.ElectricPokemon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleSystem {
    private static final int NUM_ROUNDS = 5;
    private static final int CRYSTALS_PER_VICTORY = 10;

    public int startTournament(List<Pokemon> userPokemon) {
        int crystals = 0;

        for (int round = 1; round <= NUM_ROUNDS; round++) {
            System.out.println("\n----- Round " + round + " -----");
            Pokemon opponent = getRandomOpponent();

            System.out.println("You are facing a wild " + opponent.name + "!");

            boolean allUserPokemonDead = false;
            boolean opponentDead = false;

            while (!allUserPokemonDead && !opponentDead) {
                for (Pokemon userPoke : userPokemon) {
                    if (userPoke.healthPoints > 0) {
                        System.out.println("\nYour " + userPoke.name + "'s turn:");
                        displayBattleOptions();
                        Scanner scanner = new Scanner(System.in);
                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            userPoke.performAttack(opponent);
                        } else if (choice == 2) {
                            //да довърша....
                        }

                        if (opponent.healthPoints <= 0) {
                            opponentDead = true;
                            break;
                        }

                        System.out.println("Opponent's turn:");
                        opponent.performAttack(userPoke);

                        if (userPoke.healthPoints <= 0) {
                            System.out.println(userPoke.name + " fainted!");
                            boolean allUserPokemonFainted = true;
                            for (Pokemon userPokeCheck : userPokemon) {
                                if (userPokeCheck.healthPoints > 0) {
                                    allUserPokemonFainted = false;
                                    break;
                                }//да погледна логиката!!
                            }
                        }
                    }
                }
            }

            if (opponentDead) {
                System.out.println("You defeated the wild " + opponent.name + "!");
                crystals += CRYSTALS_PER_VICTORY;
            } else {
                System.out.println("All your Pokemon fainted. Game over!");
                break;
            }
        }

        return crystals;//да го направя срещу три покемона
    }

    private void displayBattleOptions() {
        System.out.println("Choose your action:");
        System.out.println("1. Attack");
        System.out.println("2. Change Pokemon (not yet implemented)");
    }

    private Pokemon getRandomOpponent() {
        List<Pokemon> availableOpponents = new ArrayList<>();
        availableOpponents.add(new ElectricPokemon("Wild Pikachu", Size.NORMAL));
        availableOpponents.add(new WaterPokemon("Wild Squirtle", Size.NORMAL));
        availableOpponents.add(new GroundPokemon("Wild Geodude", Size.NORMAL));
//да добавя още
        Random random = new Random();
        int randomIndex = random.nextInt(availableOpponents.size());
        return availableOpponents.get(randomIndex);
    }
}
