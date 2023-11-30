public abstract class Pokemon {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected int defensePoints;
    protected String type;//enum
    protected Size size;
    protected AttackMenu attackMenu;

    protected Pokemon(String name, String type, Size size) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.healthPoints = 100;
        this.attackPoints = 50;
        this.defensePoints = 50;
        this.attackMenu = new AttackMenu();
        initializeAttacks();
        adjustStatsBasedOnSize();
    }
    abstract void initializeAttacks();

    void adjustStatsBasedOnSize() {
        if (size == Size.SMALL) {
            healthPoints -= 20;
        } else if (size == Size.LARGE) {
            healthPoints += 20;
        }
    }

    void performAttack(Pokemon opponent) {
        Attack chosenAttack = attackMenu.chooseAttack();
        chosenAttack.performAttack(this, opponent);
    }
}
