public class PokemonGame {
    public static void main(String[] args) {
        String redColor = "\u001B[31m";
        String reset = "\u001B[0m";
        MusicPlayer.playMusic("Pokémon Theme Song (Music Video).wav");

        System.out.println(redColor + "------WELCOME TO POKEMON GAME BATTLE. ARE YOU READY FOR THIS?------" + reset);
        System.out.println("Please choose 3 pokemons to play with (select by number): ");
        GameEngine gameEngine = new GameEngine();
        gameEngine.startGame();
    }
}
