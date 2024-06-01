import javax.swing.JFrame;

/**
 * TBD
 *
 * @author Nismath - U781684
 */
public class Main {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Flappy Bird");
		frame.setSize(800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlappyBird flappyBird = new FlappyBird();
		frame.add(flappyBird);
		frame.setVisible(true);
	}}
