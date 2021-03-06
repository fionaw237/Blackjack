import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cards;
    private int lowAces;
    private int numberOfAces;

    public Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
        this.lowAces = 0;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getCards() {
        return new ArrayList<>(this.cards);
    }

    public int numberOfCards(){
        return this.cards.size();
    }

    public void receiveCard(Card card) {
        this.cards.add(card);
        if (card.getRank() == Rank.ACE){
            this.numberOfAces += 1;
        }
    }

    public boolean hasCard() {
        return this.cards.size() != 0;
    }

    public int getHandValue() {
        int total = 0;
        for (Card card : this.cards){
            total += card.getValue();
        }
        total -= 10*this.lowAces;
        return total;
    }

    public Card firstCard(){
        return this.cards.get(0);
    }

    public boolean checkIfBust() {
        return getHandValue() > 21;
    }

    public boolean hasCertainCard(Rank rank){
        for (Card card : this.cards){
            if (card.getRank() == rank){
                return true;
            }
        }
        return false;
    }

    public boolean hasRoyalCard(){
        ArrayList<Rank> royalRanks = new ArrayList<>();
        royalRanks.add(Rank.JACK);
        royalRanks.add(Rank.QUEEN);
        royalRanks.add(Rank.KING);
        for (Card card : this.cards){
            if (royalRanks.contains(card.getRank())){
                return true;
            }
        }
        return false;
    }

    public boolean hasBlackjack(){
        return (numberOfCards() == 2 && hasCertainCard(Rank.ACE) && hasRoyalCard());
    }

    public void chooseAceLow() {
        this.lowAces += 1;
        }

    public int getNumberOfAces() {
        return this.numberOfAces;
    }

    public boolean has21(){
        return getHandValue() == 21;
    }

}
