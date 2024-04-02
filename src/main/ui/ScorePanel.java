package ui;

import javax.swing.*;
import java.awt.*;

public class ScorePanel extends JPanel {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    public static final int LBL_WIDTH = 100;
    public static final int LBL_HEIGHT = 25;
    public static final int BOT_SPACE = 5;
    private Game g;
    private JLabel scoreLbl;

    public ScorePanel(Game g) {
        this.g = g;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        scoreLbl = new JLabel(String.valueOf(g.getScore()));
        scoreLbl.setBounds(0, HEIGHT - LBL_HEIGHT - BOT_SPACE, LBL_WIDTH, LBL_HEIGHT);
        scoreLbl.setFont(new Font("Georgia", Font.BOLD, 16));
        add(scoreLbl);
    }

    public void update() {
        scoreLbl.setText(String.valueOf(Math.round(g.getScore())));
    }
}
