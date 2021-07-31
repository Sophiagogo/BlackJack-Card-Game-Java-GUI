package Presentaion.CardGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class BlackjackGUI extends JPanel {
    JPanel topPanel = new JPanel();
    JPanel dcardPanel = new JPanel();
    JPanel pcardPanel = new JPanel();
    //JPanel infoPanel = new JPanel();
    JTextPane winlosebox = new JTextPane();
    JButton hitbutton = new JButton();
    JButton dealbutton = new JButton();
    JButton staybutton = new JButton();
    JButton playagainbutton = new JButton();
    JButton helpbutton = new JButton();
    JLabel dealerlabel = new JLabel();
    JLabel playerlabel = new JLabel();

    private Player player = new Player();
    private Dealer dealer = new Dealer();
    private Deck deck = new Deck();
    BlackjackGame game = new BlackjackGame();

    /*************************************************************
     the labels to represent the cards for the game
     *************************************************************/
    JLabel playercard1;
    JLabel playercard2;
    JLabel playercardhit;
    JLabel dealercard2;
    JLabel dealercard1;
    JLabel dealercardhit;

    /*************************************************************
     Constructs the screen
     *************************************************************/
    public BlackjackGUI() {

        topPanel.setBackground(new Color(0, 122, 0));
        dcardPanel.setBackground(new Color(0, 122, 0));
        pcardPanel.setBackground(new Color(0, 122, 0));
        //infoPanel.setBackground(new Color(0, 122, 0));

        topPanel.setLayout(new FlowLayout());
        winlosebox.setText("");
        winlosebox.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        dealbutton.setText("  Deal");
        dealbutton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        dealbutton.addActionListener(new dealbutton());
        hitbutton.setText("  Hit");
        hitbutton.addActionListener(new hitbutton());
        hitbutton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        hitbutton.setEnabled(false);
        staybutton.setText("  Stand");
        staybutton.addActionListener(new staybutton());
        staybutton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        staybutton.setEnabled(false);
        playagainbutton.setText("  Play Again");
        playagainbutton.addActionListener(new playagainbutton());
        playagainbutton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        playagainbutton.setEnabled(false);
        helpbutton.setText(" Help");
        helpbutton.addActionListener(new helpbutton());
        helpbutton.setFont(new Font("Comic Sans MS", Font.BOLD, 20));


        dealerlabel.setText("  Dealer:  ");
        dealerlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        dealerlabel.setForeground(Color.white);
        playerlabel.setText("  Player:  ");
        playerlabel.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        playerlabel.setForeground(Color.white);

        topPanel.add(winlosebox);
        topPanel.add(dealbutton);
        topPanel.add(hitbutton);
        topPanel.add(staybutton);
        topPanel.add(playagainbutton);
        topPanel.add(helpbutton);

        pcardPanel.add(playerlabel);
        dcardPanel.add(dealerlabel);


        setLayout(new BorderLayout());
        add(topPanel, BorderLayout.NORTH);
        add(dcardPanel, BorderLayout.CENTER);
        add(pcardPanel, BorderLayout.SOUTH);


    }//end BlackjackGUI

    /*************************************************************
     Shows the screen
     *************************************************************/
    public void display() {
        JFrame myFrame = new JFrame("BlackJack Game");
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(this);
        myFrame.setPreferredSize(new Dimension(700, 550));

        //Display the window.
        myFrame.pack();
        myFrame.setVisible(true);

    }//end display


    /*************************************************************
     DealButton
     *************************************************************/
    class dealbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            deck = new Deck();
            deck.shuffle();

            dcardPanel.add(dealerlabel);
            pcardPanel.add(playerlabel);

            /**
             Get's dealer and player cards from Hand
             and the image associated with that random
             card and puts them on the screen.
             */


            //get current player cards
            Card pcard1 = deck.nextCard();
            player.addCard(pcard1);
            Card pcard2 = deck.nextCard();
            player.addCard(pcard2);


            playercard1 = new JLabel(pcard1.getimage());
            playercard2 = new JLabel(pcard2.getimage());

            //get current dealer cards
            Card dcard1 = deck.nextCard();
            dealer.addCard(dcard1);
            dealercard1 = new JLabel(new ImageIcon(
                    "/Users/sophiafan/Documents/Spring 2021/CS 5004 Java/hw/hw/src/Presentaion/CardGame/Presentaion/CardGame/images/back.png"));
            Card dcard2 = deck.nextCard();
            dealer.addCard(dcard2);
            dealercard2 = new JLabel(dcard2.getimage());


            dcardPanel.add(dealercard1);
            dcardPanel.add(dealercard2);

            pcardPanel.add(playercard1);
            pcardPanel.add(playercard2);

            dealerlabel.setText(" Dealer Points: " + dealer.getTotalPoints());
            playerlabel.setText(" Player Points: " + player.getTotalPoints());

            hitbutton.setEnabled(true);
            staybutton.setEnabled(true);
            dealbutton.setEnabled(false);

            if (game.ifBlackjack()) {
                hitbutton.setEnabled(false);
                staybutton.setEnabled(false);
                dealbutton.setEnabled(false);
                playagainbutton.setEnabled(true);
                winlosebox.setText("BlackJack");
            }

            add(dcardPanel, BorderLayout.CENTER);
            add(pcardPanel, BorderLayout.SOUTH);

        }
    }//end dealbutton

    /*************************************************************
     HitButton
     every time the player wants another card
     until hand value is over 21.
     Hit button pressed
     *************************************************************/
    class hitbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {


            Card hitcard = deck.nextCard();
            player.addCard(hitcard);
            playercardhit = new JLabel(hitcard.getimage());
            pcardPanel.add(playercardhit);
            pcardPanel.repaint();

            int playerPoints = player.getTotalPoints();
            if (playerPoints > 21) {
                winlosebox.setText("Player Bust!");
                hitbutton.setEnabled(false);
                dealbutton.setEnabled(false);
                staybutton.setEnabled(false);
                playagainbutton.setEnabled(true);
            } else if (playerPoints == 21) {
                winlosebox.setText("Player Won!");
                hitbutton.setEnabled(false);
                dealbutton.setEnabled(false);
                staybutton.setEnabled(false);
                playagainbutton.setEnabled(true);
            }

            playerlabel.setText(" Player Points: " + playerPoints);

        }
    }//end hitbutton

    /*************************************************************
     StayButton
     dealer must hit on 16 or lower. determines the winner,
     player wins if under 21 and above dealer.
     Tie goes to dealer.
     Stay button pressed
     *************************************************************/
    class staybutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {

            game.dealersTurn();
            dcardPanel.removeAll();
            dcardPanel.add(dealerlabel);
            dealerlabel.setText(" " + dealerlabel.getText());

            //get cards and re-display and show the face-down card of dealer
            Card dhitcard = deck.nextCard();
            dealer.addCard(dhitcard);
            dealercardhit = new JLabel(dhitcard.getimage());
            dealercard1 = new JLabel(dealer.getHand().getCard(0).getimage());
            dealercard2 = new JLabel(dealer.getHand().getCard(1).getimage());
            dcardPanel.add(dealercard1);
            dcardPanel.add(dealercard2);
            dcardPanel.add(dealercardhit);

            dealerlabel.setText(" Dealer Points: " + dealer.getTotalPoints());
            playerlabel.setText(" Player Points: " + player.getTotalPoints());

            int userPoints = player.getTotalPoints();
            int dealerPoints = dealer.getTotalPoints();
            if (userPoints > 21) {
                winlosebox.setText("Player lost! Player has " + userPoints + " points and has busted.");
            } else if (dealerPoints > 21) {
                winlosebox.setText("Player won! Dealer has " + dealerPoints + " points and has busted.");
            } else if (dealerPoints > userPoints) {
                winlosebox.setText("Player lost! Dealer has " + dealerPoints + " points and player has " + userPoints + " points.");
            } else if (dealerPoints == userPoints) {
                winlosebox.setText("Tie! Player and dealer both have " + userPoints + " points.");
            } else {
                winlosebox.setText("Player won! Dealer has " + dealerPoints + " points and player has " + userPoints + " points.");
            }
            hitbutton.setEnabled(false);
            staybutton.setEnabled(false);

            playagainbutton.setEnabled(true);

        }
    }//end staybutton

    /*************************************************************
     PlayAgainButton
     resets screen
     Play Again button pressed
     *************************************************************/
    class playagainbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            dcardPanel.removeAll();
            pcardPanel.removeAll();
            dcardPanel.add(dealerlabel);
            pcardPanel.add(playerlabel);
            dealerlabel.setText(" Dealer: ");
            playerlabel.setText(" Player: ");
            winlosebox.setText("");
            dealer = new Dealer();
            player = new Player();
            game = new BlackjackGame();

            hitbutton.setEnabled(false);
            staybutton.setEnabled(false);
            playagainbutton.setEnabled(false);
            dealbutton.setEnabled(true);

        }
    }//end playagainbutton

    /*************************************************************
     HelpButton
     display instructions
     *************************************************************/
    class helpbutton implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String instructions = BlackjackGame.Instruction();
            Component palette = new Component() {
            };
            JOptionPane.showInternalMessageDialog(palette, instructions,
                    "QUICK&EASY BLACKJACK HELP",
                    JOptionPane.INFORMATION_MESSAGE);
        }

    }//end helpbutton
}//end BlackjackGUI