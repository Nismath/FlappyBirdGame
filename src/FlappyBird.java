import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {
	private int birdY = 10;
	private int birdVelocity = 1;
	private int gravity = 1 ;
	private boolean gameStarted = false;
	private int pipeX = 600;
	private int pipeGap = 200;
	private int pipeY = (int) (Math.random() * 300) + 50;
	private int score = 0;
	private Image birdImage;
	private Image topPipeImage;
	private Image bottomPipeImage;


	public FlappyBird() {
		Timer timer = new Timer(10, this);
		timer.start();
		addKeyListener(this);
		setFocusable(true);
		try {
			birdImage = ImageIO.read(new File("flappybird.png"));
			topPipeImage = ImageIO.read(new File("topPipe.png"));
			bottomPipeImage = ImageIO.read(new File("bottompipe.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Overrides the paintComponent method to customize the appearance of the panel.
	 * Draws the bird image, pipes, and the current score on the panel.
	 *
	 * @param g The Graphics object used for painting
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		setBackground(Color.black);
		g.setColor(Color.green);
		g.drawImage(birdImage, 10, birdY, 50,40,null);
		g.drawImage(topPipeImage, pipeX, pipeY - topPipeImage.getHeight(null), 60, topPipeImage.getHeight(null), null);
		g.drawImage(bottomPipeImage, pipeX, pipeY + pipeGap, 50, bottomPipeImage.getHeight(null), null);


		g.drawString("Score: " + score, 700, 50);
		if (!gameStarted) {
			g.setFont(new Font("Arial", Font.BOLD, 20));
			g.setColor(Color.white);
			String message = "Press SPACE to start";
			int messageWidth = g.getFontMetrics().stringWidth(message);
			int messageHeight = g.getFontMetrics().getHeight();
			g.drawString(message, (800 - messageWidth) / 2, (600 + messageHeight) / 2 - 10);
		}
	}


	public void actionPerformed(ActionEvent e) {
		if(gameStarted) {
			birdVelocity += gravity;
			birdY += birdVelocity;
			pipeX -= 5;
			if (pipeX < -50) {
				pipeX = 800;
				pipeY = (int) (Math.random() * 300) + 50;
				score++;
			}
			//is the bird touching ground
			if (birdY > 550) {
				gameOver();
			}
			//pipe collision
			if ((birdY < pipeY || birdY > pipeY + pipeGap) && pipeX < 150 && pipeX + 50 > 100) {
				gameOver();
			}
			repaint();
		}
	}

	public void gameOver() {
		JOptionPane.showMessageDialog(null, "Game Over! Your score: " + score);
		System.exit(0);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			birdVelocity = -15;
			if (!gameStarted) {
				gameStarted = true;
			}
		}
	}

	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}


}
