/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2023/2024
 * Group Capstone Project
 * Group #9
 * 1 - 5026221131 - Maulina Nur Laila
 * 2 - 5026221172 - Arya Putra Tsabitah Firjatulloh
 * 3 - 5026221179 - Kadek Mawar Kumala Dewi
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


//Mendeklarasikan kelas SudokuMain yang merupakan turunan dari kelas JFrame, yang digunakan untuk membuat frame GUI.
public class SudokuMain extends JFrame {
    private static final long serialVersionUID = 1L;  // to prevent serial warning
    // untuk gambar boardnya
    GameBoardPanel board = new GameBoardPanel();
    //untuk membuat button new game
    JButton btnNewGame = new JButton("New Game");
    //timer
    JLabel timerLabel = new JLabel("Timer: 0 seconds");
    private Timer timer;
    private int seconds;
    private Object mainPanel;
    Input input = new Input(9);

    public static void quitActionPerformed(ActionEvent evt){
        System.exit(0);
    }

    // Constructor
    public SudokuMain() {
        JOptionPane.showMessageDialog(null, "Welcome! click OK to start game! Sudoko");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = getContentPane();
        //inisialisasi layout frame
        cp.setLayout(new BorderLayout());
        //Menambahkan panel permainan dan panel tombol ke frame dengan menggunakan layout BorderLayout.
        cp.add(board, BorderLayout.CENTER);

        // Add buttons and timer label to the right to re-start the game via board.newGame() and restart
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnNewGame);
        buttonPanel.add(timerLabel);
        cp.add(buttonPanel, BorderLayout.EAST);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem aboutUsItem = new JMenuItem("About Us");
        JMenuItem quit = new JMenuItem("Quit");
        setJMenuBar(menuBar);

        // Add to menuBar
        menuBar.add(menu);
        menu.add(aboutUsItem);
        menu.add(quit);

        // Add action listener to "About Us" menu item
        aboutUsItem.addActionListener(e -> showAboutUsDialog());

        //Menambahkan action listener untuk tombol "New Game" yang akan memanggil metode startNewGame().
        btnNewGame.addActionListener(e -> startNewGame());

        // Add action listener to quit
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SudokuMain.quitActionPerformed(e);
            }
        });

        //Menambahkan action listener untuk tombol "New Game" yang akan memanggil metode startNewGame().
        btnNewGame.addActionListener(e -> startNewGame());
        if (input.textField.getText().matches("\\d+")) {
            startNewGame();
        } else {
            JOptionPane.showMessageDialog(null, "Please enter a number between 1 and 9", "Input Error", JOptionPane.ERROR_MESSAGE);
        }

        // Initialize the game board and timer
        initializeTimer();
        startTimer();
        board.newGame();

        pack();   // Mempaketkan komponen-komponen UI untuk menyesuaikan ukuran frame.
        //Mengatur posisi frame, judul, dan membuat frame terlihat.
        setLocationRelativeTo(null);
        setTitle("Sudoku");
        setVisible(true);
    }

    private void showAboutUsDialog() {
        AboutUs aboutUs = new AboutUs(this);
        aboutUs.setVisible(true);
    }

    // Method to start a new game
    private void startNewGame() {
        restartTimer();
        board.newGame();
    }

    // Method to initialize the timer
    private void initializeTimer() {
        seconds = 0;
        timer = new Timer(1000, e -> {
            seconds++;
            updateTimerLabel();
        });
    }

    // Method to start the timer
    private void startTimer() {
        timer.start();
    }

    // Method to restart the timer
    private void restartTimer() {
        timer.stop();
        seconds = 0;
        updateTimerLabel();
        timer.start();
    }

    // Method to update the timer label
    private void updateTimerLabel() {
        timerLabel.setText("Timer: " + seconds + " seconds");
    }

    /** The entry main() entry method */
    public static void main(String[] args) {
        // Run GUI codes in the Event-Dispatching thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SudokuMain();
            }
        });
    }
}
