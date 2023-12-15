public abstract class Pokemon {
    protected String name;
    protected int healthPoints;
    protected int attackPoints;
    protected int defensePoints;
    protected Type type;
    protected Size size;
    protected AttackMenu attackMenu;
    protected String color;

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    protected Pokemon(String name, Type type, Size size, String color) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.healthPoints = 80;
        this.attackPoints = 50;
        this.defensePoints = 50;
        this.attackMenu = new AttackMenu();
        this.color = color;
        initializeAttacks();
        adjustStatsBasedOnSize();
    }

    protected Pokemon(String name, Size size) {
        this.name = name;
        this.size = size;
        this.healthPoints = 80;
        this.attackPoints = 50;
        this.defensePoints = 50;
        this.color = "random";
        adjustStatsBasedOnSize();
    }

    public String getColor() {
        return color;
    }

    public abstract void initializeAttacks();

    public void adjustStatsBasedOnSize() {
        if (size == Size.SMALL) {
            healthPoints -= 20;
        } else if (size == Size.LARGE) {
            healthPoints += 20;
        }
    }

    public void performAttack(Pokemon opponent) {
        Attackable chosenAttack = attackMenu.chooseAttack();
        chosenAttack.performAttack(this, opponent);
    }

    public void performRandomAttack(Pokemon opponent) {
        Attackable randomAttack = attackMenu.getRandomAttack();
        randomAttack.performAttack(this, opponent);
    }

    public void heal(int healCost) {
        int maxHealth = 100;
        int currentHealth = getHealthPoints();

        if (currentHealth < maxHealth) {
            int newHealth = (currentHealth + healCost);
            setHealthPoints(newHealth);
        } else {
            System.out.println(getName() + " is already at full health.");
        }
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", healthPoints=" + healthPoints +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                ", type='" + type + '\'' +
                ", size=" + size;
    }
}