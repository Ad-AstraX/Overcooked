package Model;

public class GameState {
    public double timeLeft;
    public int totalPay;
    public int payGoal;

    public GameState(double time, int payGoal) {
        timeLeft = time;
        this.payGoal = payGoal;
    }
}
