
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class board {
    private JPanel gui;
    private JPanel board;
    private JButton button;
    private JLabel label;
    JButton[][] squares;
    int N;
    int M;
    Boolean setPlayerStart1 = false;
    Boolean setPlayerStart2 = false;
    Boolean startGame = false;
    Boolean displayMoves = false;
    Boolean gameEnded = false;

    board(JPanel getGui, int arrayLen) {
        gui = getGui;
        N = arrayLen;
        M = arrayLen + 1;
        squares = new JButton[N][M];
        board = new JPanel(new GridLayout(0, N));

        initializeGui();
    }

    public final void initializeGui() {
        gui.add(board);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                JButton b = new JButton();
                b.setPreferredSize(new Dimension(100, 100));
                b.setForeground(Color.BLACK);
                b.setBackground(Color.WHITE);
                b.setBorderPainted(true);
                b.setBorder(new LineBorder(Color.BLACK));
                b.setOpaque(true);

                squares[i][j] = b;
                board.add(squares[i][j]);

                squares[i][j].addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (setPlayerStart1) {
                                    Object source = e.getSource();
                                    for (int i = 0; i < N; i++) {
                                        for (int j = 0; j < M; j++) {
                                            if (source == squares[i][j]) {

                                                changeColor(i, j, 1);
                                                setPlayerStart1 = false;
                                                setPlayerStart2 = true;
                                                JOptionPane.showMessageDialog(null,
                                                        "Set the starting point of Player2");
                                                return;

                                            }
                                        }
                                    }
                                } else if (setPlayerStart2) {
                                    Object source = e.getSource();

                                    for (int i = 0; i < N; i++) {
                                        for (int j = 0; j < M; j++) {
                                            if (source == squares[i][j]) {
                                                if (squares[i][j].getBackground() != Color.BLUE) {
                                                    changeColor(i, j, 2);
                                                    setPlayerStart2 = false;
                                                    startGame = true;
                                                    displayMoves = true;

                                                    return;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        });
            }
        }
    }

    public void Setmoves() {
        JOptionPane.showMessageDialog(null, "Set the starting point of Player1");
        KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0);
        ActionListener action = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setPlayerStart1 = false;
            }
        };
        board.registerKeyboardAction(action, stroke, JComponent.WHEN_IN_FOCUSED_WINDOW);
        while (setPlayerStart1 == true) {
            System.out.print("");
            if (setPlayerStart1 == false) {
                break;
            }
            continue;
        }
    }

    public final JComponent getGui() {
        return gui;
    }

    public java.util.List<java.util.Map.Entry<Integer, Integer>> calculateLegal(int i, int j, int player) {

        java.util.List<java.util.Map.Entry<Integer, Integer>> pairList = new java.util.ArrayList<>();

        if (j - 1 < N && j - 1 >= 0) { // left
            if (player == 1 && squares[i][j - 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i, j - 1));
            } else if (player == 2 && squares[i][j - 1].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i, j - 1));
            }

        }
        if (j + 1 < N) { // right
            if (player == 1 && squares[i][j + 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i, j + 1));
            } else if (player == 2 && squares[i][j + 1].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i, j + 1));
            }

        }

        if (i + 1 < N) { // down
            if (player == 1 && squares[i + 1][j].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j));
            } else if (player == 2 && squares[i + 1][j].getBackground() != Color.CYAN) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j));
            }

        }

        if (i + 1 < N && j - 1 < N && j - 1 >= 0) { // down left
            if (player == 1 && squares[i + 1][j - 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j - 1));
            } else if (player == 2 && squares[i + 1][j - 1].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j - 1));
            }

        }
        if (i + 1 < N && j + 1 < N) { // down right
            if (player == 1 && squares[i + 1][j + 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j + 1));
            } else if (player == 2 && squares[i + 1][j + 1].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i + 1, j + 1));
            }

        }
        if (i - 1 < N && i - 1 >= 0) { // up
            if (player == 1 && squares[i - 1][j].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j));
            } else if (player == 2 && squares[i - 1][j].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j));
            }

        }
        if (i - 1 < N && i - 1 >= 0 && j + 1 < N) { // up right
            if (player == 1 && squares[i - 1][j + 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j + 1));
            } else if (player == 2 && squares[i - 1][j + 1].getBackground() != Color.CYAN) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j + 1));
            }

        }
        if (i - 1 < N && j - 1 < N && i - 1 >= 0 && j - 1 >= 0) { // up left
            if (player == 1 && squares[i - 1][j - 1].getBackground() != Color.RED) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j - 1));
            } else if (player == 2 && squares[i - 1][j - 1].getBackground() != Color.BLUE) {
                pairList.add(new java.util.AbstractMap.SimpleEntry<>(i - 1, j - 1));
            }

        }

        return pairList;
    }

    public void changeColor(int i, int j, int color) {
        if (color == 1) {
            squares[i][j].setBackground(Color.BLUE);
        } else if (color == 2) {
            squares[i][j].setBackground(Color.RED);
        }

    }
}
