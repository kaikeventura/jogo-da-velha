package br.com.kaikeventura.jdv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Game {

    private String[] line1;
    private String[] line2;
    private String[] line3;
    private String[] p1;
    private String[] p2;
    private String[] nextPlayer;
    private BufferedReader reader;
    private String endGame;

    void initGame() {
        this.line1 = new String[]{"1", "2", "3"};
        this.line2 = new String[]{"4", "5", "6"};
        this.line3 = new String[]{"7", "8", "9"};
        this.reader = new BufferedReader(new InputStreamReader(System.in));
        this.players();

        this.checkFistPlayerAndRunGame();
    }

    public void players() {
        try {
            System.out.println("Player 1 name:");
            this.p1 = new String[]{this.reader.readLine(), "O"};
            System.out.println("Player 2 name:");
            this.p2 = new String[]{this.reader.readLine(), "X"};
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkFistPlayerAndRunGame() {
        try {
            System.out.println("First Player? 1 - " + this.p1[0] + " or 2 - " + this.p2[0]);
            this.nextPlayer = this.checkPlayer(this.reader.readLine());
            this.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] checkPlayer(String firstPlayer) {
        return (firstPlayer.equals("1")) ? this.p1 : this.p2;
    }

    private void start() {
        try {
            System.out.println(this.nextPlayer[0] + " plays. \n Choose an available position to play:");
            this.printGame();
            this.registerMove(this.reader.readLine());
            this.checkIfTheGameIsOver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printGame() {
        System.out.println(
                this.line1[0] + " | " + this.line1[1] + " | " + this.line1[2] + "\n"
                        + this.line2[0] + " | " + this.line2[1] + " | " + this.line2[2] + "\n"
                        + this.line3[0] + " | " + this.line3[1] + " | " + this.line3[2]);
    }

    private void registerMove(final String move) {
        switch (Integer.parseInt(move)) {
            case 1 -> this.line1[0] = this.nextPlayer[1];
            case 2 -> this.line1[1] = this.nextPlayer[1];
            case 3 -> this.line1[2] = this.nextPlayer[1];
            case 4 -> this.line2[0] = this.nextPlayer[1];
            case 5 -> this.line2[1] = this.nextPlayer[1];
            case 6 -> this.line2[2] = this.nextPlayer[1];
            case 7 -> this.line3[0] = this.nextPlayer[1];
            case 8 -> this.line3[1] = this.nextPlayer[1];
            case 9 -> this.line3[2] = this.nextPlayer[1];
        }
    }

    private void checkIfTheGameIsOver() {
        Arrays.stream(new String[]{"O", "X"}).forEach(symbol -> {
            if (Arrays.stream(this.line1).filter(pos -> pos.equals(symbol)).count() == 3) {
                this.endGame = symbol;
            }
            else if (Arrays.stream(this.line2).filter(pos -> pos.equals(symbol)).count() == 3) {
                this.endGame = symbol;
            }
            else if (Arrays.stream(this.line3).filter(pos -> pos.equals(symbol)).count() == 3) {
                this.endGame = symbol;
            }
            else if (this.line1[0].equals(symbol) && this.line2[0].equals(symbol) && this.line3[0].equals(symbol)) {
                this.endGame = symbol;
            }
            else if (this.line1[1].equals(symbol) && this.line2[1].equals(symbol) && this.line3[1].equals(symbol)) {
                this.endGame = symbol;
            }
            else if (this.line1[2].equals(symbol) && this.line2[2].equals(symbol) && this.line3[2].equals(symbol)) {
                this.endGame = symbol;
            }
            else if (this.line1[0].equals(symbol) && this.line2[1].equals(symbol) && this.line3[2].equals(symbol)) {
                this.endGame = symbol;
            }
            else if (this.line1[2].equals(symbol) && this.line2[1].equals(symbol) && this.line3[0].equals(symbol)) {
                this.endGame = symbol;
            }
        });
        if (this.endGame != null) {
            System.out.println("End Game!\n" + "Winner: " + Arrays.toString(getWinnerPlayer(this.endGame)));
            this.printGame();
        }
        else {
            this.checkNextPlayer();
        }
    }

    private String[] getWinnerPlayer(String endGame) {
        return (endGame.equals(this.p1[1])) ? this.p1 : this.p2;
    }

    private void checkNextPlayer() {
        if (Arrays.equals(this.nextPlayer, this.p1)) this.nextPlayer = this.p2; else this.nextPlayer = this.p1;
        this.start();
    }

}
