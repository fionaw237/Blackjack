import java.util.ArrayList;

public class Runner {

    public static void main(String[] args){
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        player2.setAsDealer();
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Deck deck = new Deck();
        Game game = new Game(players, deck);

        game.initialDeal();
        Player winner = game.getWinner();
        System.out.println(player1.getName() + " has " + player1.getHandValue());
        System.out.println(player2.getName() + " has " + player2.getHandValue());
        System.out.println("The winner is " + winner.getName());
    }

}