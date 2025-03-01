package com.github.splendor_mobile_game.game.model;

import com.github.splendor_mobile_game.database.CardDatabase;
import com.github.splendor_mobile_game.game.enums.CardTier;
import com.github.splendor_mobile_game.game.enums.TokenType;

import java.util.ArrayList;
import java.util.Random;

public class Game {

    private TokenList emeraldTokens;
    private TokenList sapphireTokens;
    private TokenList rubyTokens;
    private TokenList diamondTokens;
    private TokenList onyxTokens;
    private TokenList goldTokens;

    private ArrayList<Card> cardTier1List = new ArrayList<>();
    private ArrayList<Card> cardTier2List = new ArrayList<>();
    private ArrayList<Card> cardTier3List = new ArrayList<>();

    private int maxTokenStack = 7; // Default number of each token type

    public Game() {

    }


    public TokenList getEmeraldTokens() {
        return emeraldTokens;
    }

    public TokenList getSapphireTokens() {
        return sapphireTokens;
    }

    public TokenList getRubyTokens() {
        return rubyTokens;
    }

    public TokenList getDiamondTokens() {
        return diamondTokens;
    }

    public TokenList getOnyxTokens() {
        return onyxTokens;
    }

    public TokenList getGoldTokens() {
        return goldTokens;
    }

    public int getMaxTokenStack() {
        return maxTokenStack;
    }






    public boolean startGame(Room room) {
        if (room.getPlayerCount() < 2) return false; // Minimum number of players to start a game is 2.
        if (room.getPlayerCount() > 4) return false; // Maximum number of players to start a game is 4.

        // Calculate number of tokens of each type
        if (room.getPlayerCount() == 2) this.maxTokenStack = 4;
        if (room.getPlayerCount() == 3) this.maxTokenStack = 5;

        // Assign all tokenLists
        this.emeraldTokens  = createTokenList(TokenType.EMERALD);
        this.sapphireTokens = createTokenList(TokenType.SAPPHIRE);
        this.rubyTokens     = createTokenList(TokenType.RUBY);
        this.diamondTokens  = createTokenList(TokenType.DIAMOND);
        this.onyxTokens     = createTokenList(TokenType.ONYX);
        this.goldTokens     = createTokenList(TokenType.GOLD_JOKER);


        // Choose random cards from deck.
        this.cardTier1List = getRandomCards(CardDatabase.getAllCards(CardTier.LEVEL_1), 4);
        this.cardTier2List = getRandomCards(CardDatabase.getAllCards(CardTier.LEVEL_2), 4);
        this.cardTier3List = getRandomCards(CardDatabase.getAllCards(CardTier.LEVEL_3), 4);


        return true;
    }






    /**
     *
     * @param tokenType -> Color of the token
     * @return TokenList -> Object representing all available tokens
     */
    private TokenList createTokenList(TokenType tokenType) {
        TokenList tokenList = new TokenList(tokenType);

        int numberOfRepeats = maxTokenStack;
        if (tokenType == TokenType.GOLD_JOKER) numberOfRepeats = 5; // There are only 5 golden tokens

        for (int i=0; i < numberOfRepeats; i++) {
            tokenList.addToken(new Token(tokenType));
        }

        return tokenList;
    }


    /**
     *
     * @param cardList -> Collection of all objects from we will draw a card
     * @param amount -> Amount of elements we want to draw
     * @return ArrayList<Card> -> Collection of randomly picked cards
     */
    private ArrayList<Card> getRandomCards(ArrayList<Card> cardList, int amount) {
        int size = cardList.size();
        if (size < amount) return null;

        ArrayList<Card> array = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>(size);
        for(int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        while(list.size() > 0) {
            int index = rand.nextInt(list.size()); // Get random index
            array.add(cardList.get(index));
            System.out.println("Selected: " + list.remove(index));
        }

        //TODO We can't pick one card few times. Verification of already chosen cards needs to be added

        return array;
    }


}
