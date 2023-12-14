public class Result {
    private int crystalsFromRounds;
    private int diamondsFromBattles;

    public Result(int crystalsFromRounds, int diamondsFromBattles) {
        this.crystalsFromRounds = crystalsFromRounds;
        this.diamondsFromBattles = diamondsFromBattles;
    }

    public int getCrystalsFromRounds() {
        return crystalsFromRounds;
    }

    public int getDiamondsFromBattles() {
        return diamondsFromBattles;
    }
}
