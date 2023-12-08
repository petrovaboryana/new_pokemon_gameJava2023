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

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getAttackPoints() {
        return attackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefensePoints() {
        return defensePoints;
    }

    public void setDefensePoints(int defensePoints) {
        this.defensePoints = defensePoints;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public AttackMenu getAttackMenu() {
        return attackMenu;
    }

    public void setAttackMenu(AttackMenu attackMenu) {
        this.attackMenu = attackMenu;
    }

    protected Pokemon(String name, Type type, Size size,String color) {
        this.name = name;
        this.type = type;
        this.size = size;
        this.healthPoints = 100;
        this.attackPoints = 50;
        this.defensePoints = 50;
        this.attackMenu = new AttackMenu();
        this.color = color;
        initializeAttacks();
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
        Attack chosenAttack = attackMenu.chooseAttack();
        chosenAttack.performAttack(this, opponent);
    }
    public void performRandomAttack(Pokemon opponent) {
        Attack randomAttack = attackMenu.getRandomAttack();
        randomAttack.performAttack(this, opponent);
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", healthPoints=" + healthPoints +
                ", attackPoints=" + attackPoints +
                ", defensePoints=" + defensePoints +
                ", type='" + type + '\'' +
                ", size=" + size;
    }
}
