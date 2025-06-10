import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.Timer;
import java.util.List;

public class Main {
    static JFrame frame = new JFrame();
    static JLabel nameOfPlayer = new JLabel("Name: ");
    static JLabel highScoreDisplay = new JLabel("0");
    static JPanel[][] blocksDisplay = new JPanel[3][2];
    static Block[] blocks = new Block[3];
    static JPanel bird = new JPanel();
    static Player player;
    static Timer timer;
    static int score = 0;
    static boolean gameStarted = false;
    static boolean spacePressed = false;
    static final int verticalGap = 450;
    static final String highScoreFile = "highscores.txt";
    static String playerName;
    static int blockSpeed = 2;
    static int highScore = 0;

    public static void main(String[] args) {
        createNewPlayer();
        design();
        createBlocks();
        designBird();
        startGameLoop();
        setUpKeyBindings();
    }

    public static void createNewPlayer() {
        playerName = JOptionPane.showInputDialog("Enter your name:");
        if (playerName == null || playerName.trim().isEmpty()) playerName = "Anonymous";
        loadHighScore(playerName);
        saveHighScore(playerName, highScore);
        player = new Player(playerName, highScore);
        nameOfPlayer.setText("Name: " + playerName);
        highScoreDisplay.setText(String.valueOf(highScore));
    }

    public static void design() {
        frame.setSize(1000, 800);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(135, 206, 235));
        JPanel header = new JPanel(null);
        header.setBounds(0, 2, 1000, 100);
        header.setBackground(Color.white);
        JLabel title = new JLabel("Flappy Bird");
        title.setBounds(300, 0, 1000, 100);
        title.setFont(new Font("Times New Roman", Font.BOLD, 70));
        header.add(title);
        frame.add(header);
        JPanel menu = new JPanel(null);
        menu.setBackground(Color.lightGray);
        menu.setBounds(0, 103, 1000, 70);
        nameOfPlayer.setBounds(10, 0, 400, 70);
        nameOfPlayer.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        JLabel hsLabel = new JLabel("Score:");
        hsLabel.setBounds(700, 0, 200, 70);
        hsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        highScoreDisplay.setBounds(880, 0, 100, 70);
        highScoreDisplay.setFont(new Font("Times New Roman", Font.BOLD, 30));
        menu.add(nameOfPlayer);
        menu.add(hsLabel);
        menu.add(highScoreDisplay);
        frame.add(menu);
        frame.setVisible(true);
    }

    public static void createBlocks() {
        int startX = 500;
        int gap = 300;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(70, (int)(Math.random() * 300 + 100), startX + i * gap);
            blocksDisplay[i][0] = new JPanel();
            blocksDisplay[i][1] = new JPanel();
            frame.add(blocksDisplay[i][0]);
            frame.add(blocksDisplay[i][1]);
        }
        updateBlocks();
    }

    public static void updateBlocks() {
        for (int i = 0; i < blocks.length; i++) {
            int h = blocks[i].getHeight();
            blocksDisplay[i][0].setBounds(blocks[i].getX(), 170, blocks[i].getWidth(), h);
            blocksDisplay[i][0].setBackground(Color.GREEN);
            blocksDisplay[i][1].setBounds(blocks[i].getX(), h + verticalGap, blocks[i].getWidth(), 800 - (h + verticalGap));
            blocksDisplay[i][1].setBackground(Color.GREEN);
        }
        frame.repaint();
    }

    public static void designBird() {
        bird.setBounds(player.getX(), player.getY(), 40, 40);
        bird.setBackground(Color.YELLOW);
        frame.add(bird);
    }

    public static void setUpKeyBindings() {
        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    timer.stop();
                    createNewPlayer();
                    restartGame();
                    timer.start();
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_D && !gameStarted) {
                    String name = JOptionPane.showInputDialog("Enter name to delete:");
                    if (name != null) deletePlayerScore(name);
                    return;
                }
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    spacePressed = true;
                }
                if (spacePressed && player.isPlaying()) {
                    player.jump();
                    gameStarted = true;
                }
            }
        });
        frame.setFocusable(true);
    }

    public static void startGameLoop() {
        timer = new Timer(20, e -> {
            if (!player.isPlaying() || !spacePressed) return;
            player.setVelocity(player.getVelocity() + 1);
            player.setY(player.getY() + player.getVelocity());
            if (player.getY() <= 170 || player.getY() >= 730) GameManager.handleCollision();
            bird.setBounds(player.getX(), player.getY(), 40, 40);
            for (int i = 0; i < blocks.length; i++) {
                blocks[i].move(blockSpeed);
                if (blocks[i].getX() + blocks[i].getWidth() < 0) {
                    int lastX = 0;
                    for (Block b : blocks) {
                        if (b.getX() > lastX) lastX = b.getX();
                    }
                    blocks[i] = new Block(70, (int)(Math.random() * 300 + 100), lastX + 300);
                    GameManager.increaseScore();
                }
                int h = blocks[i].getHeight();
                blocksDisplay[i][0].setBounds(blocks[i].getX(), 170, blocks[i].getWidth(), h);
                blocksDisplay[i][1].setBounds(blocks[i].getX(), h + verticalGap, blocks[i].getWidth(), 800 - (h + verticalGap));
                Rectangle birdRect = new Rectangle(player.getX(), player.getY(), 40, 40);
                if (birdRect.intersects(blocksDisplay[i][0].getBounds()) || birdRect.intersects(blocksDisplay[i][1].getBounds())) {
                    GameManager.handleCollision();
                }
            }
            frame.repaint();
        });
        timer.start();
    }

    public static void restartGame() {
        player = new Player(playerName, highScore);
        bird.setBounds(player.getX(), player.getY(), 40, 40);
        int startX = 500, gap = 300;
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new Block(70, (int)(Math.random() * 300 + 100), startX + i * gap);
        }
        score = 0;
        highScoreDisplay.setText("0");
        blockSpeed = 2;
        spacePressed = false;
        gameStarted = false;
        updateBlocks();
    }

    public static void gameOver() {
        timer.stop();
        if (score > player.getHighScore()) {
            player.setHighScore(score);
            saveHighScore(player.getName(), score);
            highScore = score;
        }
        JOptionPane.showMessageDialog(frame, "Game Over!\nYour score: " + score + "\n" + getLeaderboard(), "Game Over", JOptionPane.INFORMATION_MESSAGE);
        restartGame();
        timer.start();
    }

    public static void saveHighScore(String name, int score) {
        try {
            Map<String, Integer> scores = new HashMap<>();
            File file = new File(highScoreFile);
            if (file.exists()) {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    String[] parts = sc.nextLine().split(":");
                    if (parts.length == 2) scores.put(parts[0], Integer.parseInt(parts[1]));
                }
                sc.close();
            }
            scores.put(name, Math.max(scores.getOrDefault(name, 0), score));
            PrintWriter pw = new PrintWriter(new FileWriter(highScoreFile));
            for (String p : scores.keySet()) pw.println(p + ":" + scores.get(p));
            pw.close();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void loadHighScore(String name) {
        highScore = 0;
        try {
            File file = new File(highScoreFile);
            if (!file.exists()) return;
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(":");
                if (parts.length == 2 && parts[0].equals(name)) {
                    highScore = Integer.parseInt(parts[1]);
                    break;
                }
            }
            sc.close();
        } catch (IOException ex) { ex.printStackTrace(); }
    }

    public static void deletePlayerScore(String name) {
        try {
            File file = new File(highScoreFile);
            if (!file.exists()) return;
            Map<String, Integer> scores = new HashMap<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(":");
                if (parts.length == 2) scores.put(parts[0], Integer.parseInt(parts[1]));
            }
            sc.close();
            if (scores.containsKey(name)) {
                scores.remove(name);
                PrintWriter pw = new PrintWriter(new FileWriter(highScoreFile));
                for (String p : scores.keySet()) pw.println(p + ":" + scores.get(p));
                pw.close();
                JOptionPane.showMessageDialog(frame, "Score deleted.");
            } else {
                JOptionPane.showMessageDialog(frame, "Player not found.");
            }
        } catch (IOException ex) { ex.printStackTrace(); }
    }


    public static String getLeaderboard() {
        StringBuilder message = new StringBuilder("Leaderboard:\n");
        try {
            File file = new File(highScoreFile);
            if (!file.exists()) return "No scores yet.";
            Map<String, Integer> scores = new HashMap<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] parts = sc.nextLine().split(":");
                if (parts.length == 2) {
                    scores.put(parts[0], Integer.parseInt(parts[1]));
                }
            }
            sc.close();
            List<Map.Entry<String, Integer>> leaderboard = new ArrayList<>(scores.entrySet());
            leaderboard.sort((a, b) -> b.getValue() - a.getValue());
            int count = 1;
            for (Map.Entry<String, Integer> entry : leaderboard) {
                message.append(count++).append(". ").append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
                if (count > 6) break;
            }
        } catch (IOException e) {
            message.append("Error reading leaderboard.");
        }
        return message.toString();
    }


    static class GameManager {
        public static void handleCollision() {
            player.setPlaying(false);
            gameOver();
        }

        public static void increaseScore() {
            score++;
            highScoreDisplay.setText(String.valueOf(score));
            if (score % 5 == 0) blockSpeed++;
        }
    }
}

class Block {
    private int width;
    private int height;
    private int x;

    public Block(int width, int height, int x) {
        this.width = width;
        this.height = height;
        this.x = x;
    }

    public int getWidth() { return width; }
    public int getHeight() { return height; }
    public int getX() { return x; }
    public void move(int speed) { x -= speed; }
}

class Player {
    private String name;
    private int highScore;
    private boolean playing;
    private int x = 100;
    private int y = 425;
    private int velocity = 0;

    public Player(String name, int highScore) {
        this.name = name;
        this.highScore = highScore;
        this.playing = true;
    }

    public void jump() { this.velocity = -15; }

    public String getName() { return name; }
    public int getHighScore() { return highScore; }
    public void setHighScore(int s) { highScore = s; }
    public boolean isPlaying() { return playing; }
    public void setPlaying(boolean b) { playing = b; }
    public int getX() { return x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getVelocity() { return velocity; }
    public void setVelocity(int v) { this.velocity = v; }
}
