import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main1 {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		FirstFrame obj = new FirstFrame();
	}
}

@SuppressWarnings("serial")
class FirstFrame extends JFrame {

	public FirstFrame() {
		JButton b1 = new JButton("Easy");
		JButton b2 = new JButton("Medium");
		JButton b3 = new JButton("Hard");
		JLabel l1 = new JLabel("Brick Breaker");
		add(b1);
		add(b2);
		add(b3);
		add(l1);

		b1.setBounds(230, 200, 200, 50);
		b2.setBounds(230, 270, 200, 50);
		b3.setBounds(230, 340, 200, 50);

		b1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame obj = new JFrame();

				Gameplay gameplay = new Gameplay();
				gameplay.value(21, 10, 3, 7);
				// gameplay.matrixmake();
				obj.setBounds(10, 10, 700, 600);
				obj.setTitle("Brick Breaker");
				obj.setResizable(false);
				obj.setVisible(true);
				obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				obj.add(gameplay);
				dispose();
			}
		});
		b2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame obj = new JFrame();
				Gameplay gameplay = new Gameplay();
				gameplay.value(45, 6, 5, 9);
				// gameplay.matrixmake();
				obj.setBounds(10, 10, 700, 600);
				obj.setTitle("Brick Breaker");
				obj.setResizable(false);
				obj.setVisible(true);
				obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				obj.add(gameplay);
				dispose();
			}
		});
		b3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame obj = new JFrame();
				Gameplay gameplay = new Gameplay();
				gameplay.value(96, 2, 8, 12);
				// gameplay.matrixmake();
				obj.setBounds(10, 10, 700, 600);
				obj.setTitle("Brick Breaker");
				obj.setResizable(false);
				obj.setVisible(true);
				obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				obj.add(gameplay);
				dispose();
			}
		});

		setVisible(true);
		setTitle("Brick Breaker");
		setResizable(false);
		setBounds(10, 10, 700, 600);
		// setSize(400,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new FlowLayout()); // setLayout(new FlowLayout()); // Constructs a new FlowLayout with a centered
		// alignment and a default 5-unit
		// horizontal and vertical gap.

	}

	public void paint(Graphics g) {
		g.setColor(Color.black); // setting background color (black)

		g.fillRect(1, 1, 692, 592); // filling background rectangle which leaves 4 unit width and 4 unit height
									// each side of frame as frame size id (700,600)

		g.setColor(Color.red); // borders color (yellow)
		g.fillRect(0, 0, 30, 600);
		g.fillRect(670, 0, 30, 600); // border of background rectangle
		g.fillRect(684, 0, 3, 592);

		g.setColor(Color.yellow); // Brick Breaker Heading at frame where level are selected
		g.setFont(new Font("arial", Font.ITALIC, 50));
		g.drawString("Brick Breaker", 200, 100);

		g.setColor(Color.cyan); // Brick Breaker Heading at frame where level are selected
		g.setFont(new Font("serif", Font.PLAIN, 15));
		g.drawString("� AKHIL GUPTA", 525, 565);

		g.setColor(Color.green); // set paddle at first frame
		g.fillRect(310, 580, 100, 8); // coordinates of paddle

		g.setColor(Color.yellow); // set ball color
		g.fillOval(120, 350, 20, 20); // ball coordinates
	}
}
