public class GameDto {
    private int userGu; // 유저 구슬
    private int yourGu; // 상대 구슬
    private int bet; // 배팅 구슬

    public void gameDto(int userGu, int yourGu, int bet) {
        this.userGu = userGu;
        this.yourGu = yourGu;
        this.bet = bet;
    }

    public int getUserGu() {
        return userGu;
    }

    public void setUserGu(int userGu) {
        this.userGu = userGu;
    }

    public int getYourGu() {
        return yourGu;
    }

    public void setYourGu(int yourGu) {
        this.yourGu = yourGu;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int userGuAdd() {
        int relust = userGu += bet;
        return relust;
    }

    public int userGuRemove() {
        int relust = userGu -= bet;
        return relust;
    }

    public int yourGuAdd() {
        int relust = yourGu += bet;
        return relust;
    }

    public int yourGuRemove() {
        int relust = yourGu -= bet;
        return relust;
    }
}
