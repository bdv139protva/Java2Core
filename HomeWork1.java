/**
 * Java Core. Homework #1
 *
 * @author Bakeshko Daria
 * @version 29.03.22
 *
 */

class HomeWork1 {
    public static void main(String[] args) {
        Friend friend1 = new Friend("Rachel", 30);
        Friend friend2 = new Friend("Monica", 500);
        Friend friend3 = new Friend("Joey", 350);
        Friend friend4 = new Friend("Chandler", 200);

        Team team = new Team("Friends", friend1, friend2, friend3, friend4);
        Course c = new Course(50, 250, 300);

        c.doIt(team);
        System.out.println("Our team: ");
        team.info();
        System.out.println();
        System.out.println("Let's congratulation to: ");
        team.showResults();
    }
}

class Team {
    private String name;
    private Friend[] friends;

    // конструктор
    public Team(String name, Friend friend1, Friend friend2, Friend friend3, Friend friend4) {
        this.name = name;
        friends = new Friend[] { friend1, friend2, friend3, friend4 };
    }

    /*
     * public void showResults() {
     * for (int i = 0; i < friends.length; i++) {
     * if (friends[i].isWinner()) {
     * System.out.println(friends[i]);
     * }
     * }
     * };
     * 
     * public void info() {
     * for (int i = 0; i < friends.length; i++) {
     * System.out.println(friends[i]);
     * }
     * }
     */

    public void showResults() {
        for (Friend f : friends) {
            if (f.isWinner()) {
                System.out.println(f);
            }
        }
    };

    public void info() {
        for (Friend f : friends) {
            System.out.println(f);
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Friend[] getFriends() {
        return friends;
    }

    public void setFriends(Friend[] friends) {
        this.friends = friends;
    }
}

class Friend {
    private String name;
    private int jumpLimit;
    private boolean isWinner = false;

    public Friend(String name, int jumpLimit) {
        this.name = name;
        this.jumpLimit = jumpLimit;
    }

    @Override
    public String toString() {
        return "Friend: " + name + ", jumpLimit: " + jumpLimit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJumpLimit() {
        return jumpLimit;
    }

    public void setJumpLimit(int jumpLimit) {
        this.jumpLimit = jumpLimit;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(boolean winner) {
        isWinner = winner;
    }
}

class Course {

    private int[] jumpBarriers;

    // конструктор
    Course(int jumpBarrier1, int jumpBarrier2, int jumpBarrier3) {
        this.jumpBarriers = new int[] { jumpBarrier1, jumpBarrier2, jumpBarrier3 };
    }

    void doIt(Team team) {
        for (Friend f : team.getFriends()) {
            for (int j : jumpBarriers) {
                if (f.getJumpLimit() < j) {
                    f.setWinner(false);
                    break;
                } else {
                    f.setWinner(true);
                }
            }
        }
        /*
         * for (int i = 0; i < team.getFriends().length; i++) {
         * for (int j = 0; j < jumpBarriers.length; j++) {
         * if (team.getFriends()[i].getJumpLimit() < jumpBarriers[j]) {
         * team.getFriends()[i].setWinner(false);
         * break;
         * } else {
         * team.getFriends()[i].setWinner(true);
         * }
         * }
         * }
         */
    }
}
