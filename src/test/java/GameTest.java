import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class GameTest {

    Player player1;
    Player player2;
    Dealer dealer;
    Deck deck;
    Game game;
    ArrayList<Player> players;
    ArrayList<String> allowedChoices;

    @Before
    public void before(){
        player1 = new Player("Player 1");
        player2 = new Player("Player 2");
        dealer = new Dealer("Dealer");
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        deck = new Deck();
        game = new Game(players, deck, dealer);
        allowedChoices = new ArrayList<>();
        allowedChoices.add("s");
        allowedChoices.add("t");
    }

    @Test
    public void hasDealer(){
        assertEquals(dealer, game.getDealer());
    }

    @Test
    public void canGetNoOfPlayers(){
        assertEquals(2, game.numberOfPlayers());
    }

    @Test
    public void initialDeal(){
        game.initialDeal();
        assertEquals(2, player1.numberOfCards());
        assertEquals(2, player2.numberOfCards());
        assertEquals(2, dealer.numberOfCards());
        assertEquals(46, game.getDeck().numberOfCards());
    }

    @Test
    public void canGetWinner(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.NINE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.THREE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.JACK));
        assertEquals(dealer, game.getWinner(player1));
    }

    @Test
    public void isDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.THREE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        dealer.receiveCard(new Card(Suit.DIAMONDS, Rank.THREE));
        assertEquals(true, game.isDraw(player1));
    }

    @Test
    public void isNotDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TWO));
        dealer.receiveCard(new Card(Suit.DIAMONDS, Rank.TWO));
        assertEquals(false, game.isDraw(player1));
    }

    @Test
    public void getWinnerReturnsNullIfDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.SIX));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.SIX));
        dealer.receiveCard(new Card(Suit.DIAMONDS, Rank.ACE));
        assertEquals(null, game.getWinner(player1));
    }


    @Test
    public void checkUserInputTrue(){
        String choice = "S";
        assertEquals(true, game.checkInput(choice, allowedChoices));
    }

    @Test
    public void checkUserInputFalse(){
        String choice = "batman";
        assertEquals(false, game.checkInput(choice, allowedChoices));
    }

    @Test
    public void bothScore21DealerWinsWithBlackjack(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        player1.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
        dealer.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(false, game.isDraw(player1));

        assertEquals(game.getDealer(), game.getWinner(player1));
    }

    @Test
    public void bothScore21PlayerWinsWithBlackjack(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.HEARTS, Rank.TEN));
        player1.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(false, game.isDraw(player1));
        assertEquals(player1, game.getWinner(player1));
    }

    @Test
    public void bothHaveBlackjackDraw(){
        player1.receiveCard(new Card(Suit.HEARTS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.CLUBS, Rank.ACE));
        dealer.receiveCard(new Card(Suit.HEARTS, Rank.QUEEN));
        player1.receiveCard(new Card(Suit.DIAMONDS, Rank.KING));
        assertEquals(21, game.getDealer().getHandValue());
        assertEquals(21, player1.getHandValue());
        assertEquals(true, game.isDraw(player1));
    }

}
