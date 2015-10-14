package conwaysgameoflife.main;

import conwaysgameoflife.Constructs;
import conwaysgameoflife.Screen;
import conwaysgameoflife.tools.CoordinateList;
import conwaysgameoflife.universe.Universe;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends Canvas implements Runnable {

    public static final String NAME = "Conway's Game of Life";
    public static final int HEIGHT = 540;
    public static final int WIDTH = HEIGHT * 16 / 9;
    public static final int TILE_SIZE = 4;
    public static final int BIT_SHIFT = (int) (Math.log((double) TILE_SIZE) / Math.log((double) 2));
    private static final int SCALE = 2;
    private long UPDATES_PER_SECOND = 10;
    private boolean running;
    private JFrame frame;
    private Screen screen;
    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
    private CoordinateList initialConditions;
    private Universe universe;

    public Main(CoordinateList initialConditions) {
        this.initialConditions = initialConditions;
        //System.out.println(initialConditions.toString());
        this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        frame = new JFrame(NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(this, BorderLayout.CENTER);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton reset = new JButton("Reset Universe");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetUniverse();
            }
        });
        final JTextField speed = new JTextField();
        speed.setColumns(4);
        speed.setText(Integer.toString((int) this.UPDATES_PER_SECOND));
        JButton setSpeed = new JButton("Set Speed");
        setSpeed.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int i = -1;
                try {
                    i = Integer.valueOf(speed.getText());
                } catch (java.lang.NumberFormatException ex) {
                }
                setSpeed(i);
            }
        });
        buttonPanel.add(reset);
        buttonPanel.add(setSpeed);
        buttonPanel.add(speed);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        init();
    }

    private void init() {
        universe = new Universe(60, WIDTH / TILE_SIZE, HEIGHT / TILE_SIZE);
        screen = new Screen(TILE_SIZE, WIDTH, HEIGHT);
    }

    public static void main(String[] args) {

        new Main(Constructs.gliderGun(20, 20)).start();
//        for (int y = 0; y < 200; y++) {
//            for (int x = 0; x < 200; x++) {
//                for (int direction = 0; direction < 8; direction++) {
//                    if(direction == 1) System.out.print("{");
//                    System.out.print(u.get(x, y).get(direction));
//                    if(direction !=7) System.out.print(", ");
//                    else System.out.println("]");
//                }
//            }
//        }
    }

    public synchronized void start() {
        running = true;
        new Thread(this).start();
    }

    public synchronized void stop() {
        running = false;
    }

    public void update(int counter, long miliseconds) {
        screen.update(counter, miliseconds);
        universe.update(counter, miliseconds);
    }

    public void render(int xPos, int yPos, Screen screen) {
        BufferStrategy bufferStrategy = getBufferStrategy();
        if (bufferStrategy == null) {
            createBufferStrategy(3);
            return;
        }
        screen.clear();
        screen.render(universe, 0, 0);
        for (int i = 0; i < pixels.length; i++)
            pixels[i] = screen.getPixel(i);

        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        graphics.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        graphics.dispose();
        bufferStrategy.show();
    }

    public void resetUniverse() {
        init();
    }

    public void setSpeed(int speed) {
        if (speed > -1 && speed < 101)
            this.UPDATES_PER_SECOND = speed;
    }

    @Override
    public void run() {
        int frames = 0;
        int updates = 0;

        long lastTimeNano = System.nanoTime();
        long lastTimeMili = System.currentTimeMillis();
        double delta = 0; //how many nano seconds have passed
        this.requestFocus();
        while (running) {
            long nowNano = System.nanoTime();
            long nowMili = System.currentTimeMillis();
            delta += (nowNano - lastTimeNano) / (1000000000D / UPDATES_PER_SECOND);
            lastTimeNano = nowNano;

            boolean render = true;

            while (delta >= 1) {
                update(updates++, nowMili - lastTimeMili);
                delta--;
                render = true;
            }
            if (render) {
                frames++;
                render(0, 0, this.screen);
            }
            if (nowMili - lastTimeMili >= 1000) {
                lastTimeMili += 1000;
                frame.setTitle(
                        NAME
                        + " UPS: " + updates
                        + " FPS: " + frames);
                frames = 0;
                updates = 0;
            }
        }
    }
}
