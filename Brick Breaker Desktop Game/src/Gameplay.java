import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Gameplay extends JPanel implements KeyListener, ActionListener {

	boolean play = false; // this is for that game starts only when we start it (play true)otherwise it
							// will start automatically
	int score = 0; // for counting score

	int totalbricks; // bricks number in matrix format (3*7)
	Timer timer; // for how fast the ball should move so create timer class reference
	int delay; // timer delay how fast ball move (given to timer object)

	int playerX = 310;
	int ballposX = 120;
	int ballposY = 350; // setting position for slider and ball
	int balldirX = -1;
	int balldirY = -2;
	int row, col;
	int totalbricksbreak; // keeps original no. of bricks
	int originaldelay;// keeps original no. of delay

	Map1 map; // reference of MapGenerator class

	public void value(int dynamicbrick, int dynamicdelay, int row, int col) {
		totalbricks = dynamicbrick;
		totalbricksbreak = dynamicbrick;
		originaldelay = dynamicdelay;
		delay = dynamicdelay;
		this.row = row;
		this.col = col;
		// speed set up according to the level user selected
		map = new Map1(row, col);
		timer = new Timer(delay, this); // setting timer objects by delay;
		timer.start(); // Starts the Timer,causing it to start sending action events to its listeners.
	}

	public Gameplay() { // called by FirstFrame method as its object created in FirstFrame
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);

	}

	@Override
	public void paint(Graphics g) { // for drawing box, balls, bricks, sliders, tiles etc
		// this method invoked by Swing to draw components.Applications should not
		// invoke paint directly,but should instead use the repaint method to schedule
		// the component for redrawing as we do in actionPerformed method by calling
		// repaint();
		g.setColor(Color.black);// setting background color (Black)
		g.fillRect(1, 1, 692, 592); // filling background rectangle which leaves 4 unit width and 4 unit height
									// each side of frame as frame size id (700,600)

		g.setColor(Color.yellow); // borders color (yellow)
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3); // border of background rectangle
		g.fillRect(684, 0, 3, 592);

		// setting score to display
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.ITALIC, 25));
		g.drawString("Score: " + score, 530, 35);

		if (totalbricks <= 0) { // if all bricks are removed or break then You Won
			play = false; // play will got false
			balldirX = 0; // and ball will not move and stuck at one position ie. direction of ball now be
							// zero
			balldirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.ITALIC, 30));
			g.drawString("You Won !!", 265, 300); // string of You Won displayed

			g.setFont(new Font("arial", Font.ITALIC, 30));
			g.drawString("Score:" + score, 275, 340); // score displayed at last

			g.setColor(Color.yellow);
			g.setFont(new Font("arial", Font.ITALIC, 20));
			g.drawString("\"Press Enter to Restart\"", 250, 380); // if want to continue then press enter for this press
																	// Enter key event must be listen in keypressed
			timer.stop(); // stop the timer
		}

		if (ballposY > 570) { // if ball goes below the frame border bottom then game is over
			play = false; // play will got false
			balldirX = 0; // and ball will not move and stuck at one position ie. direction of ball now be
							// zero
			balldirY = 0;
			g.setColor(Color.red);
			g.setFont(new Font("arial", Font.ITALIC, 30));
			g.drawString("Game Over", 265, 300); // string of Game Over displayed

			g.setFont(new Font("arial", Font.ITALIC, 30));
			g.drawString("Score:" + score, 275, 340); // score displayed at last

			g.setColor(Color.yellow);
			g.setFont(new Font("arial", Font.ITALIC, 20));
			g.drawString("\"Press Enter to Restart\"", 250, 380); // if want to continue then press enter for this press
																	// Enter key event must be listen in keypressed//
																	// method of KeyListener interface
			timer.stop(); // stop the timer
		}

		// drawing map
		map.draw((Graphics2D) g); // convert graphics g to Graphics2d as Graphics 2d extends Graphics

		g.setColor(Color.green); // set paddle
		g.fillRect(playerX, 550, 100, 8); // coordinates of paddle which moves and playerX changes as we move right or
											// left

		g.setColor(Color.yellow); // set ball color
		g.fillOval(ballposX, ballposY, 20, 20); // ball coordinates need to change with time as action will performed so
												// we take its coordinates in a variable

		g.dispose(); // disposes of this graphics context and releases any system resources that it
						// is using
	}

	@Override
	public void actionPerformed(ActionEvent e) { // called when action is performed every time
		timer.start(); // started timer
		if (play == true) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				/*
				 * this is for when ball intersects with the slider as ball position changes as
				 * we take oval ball so for checking intersection we need rectangle with
				 * rectangle so we create it every time action is performed and checks is it
				 * going to intersects .If intersects change position of ball upwards then
				 */
				balldirY = -balldirY; // changes the y direction of ball
			}
			A: for (int i = 0; i < map.map.length; i++) { // map.map.length is ---> this first map is of MapGenerator
															// class made in Gameplay class and second map is 2d matrix
															// map declared in MapGenerator class so we are accesing the
															// instance variable of MapGenerator class with the
															// reference of MapGenerator class
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						// checking the intersection of brick and ball
						int brickX = j * map.brickwidth + 80;
						int brickY = i * map.brickheight + 50; // creates variable in which the position of bricks
																// is there at a particular time
						int brickwidth = map.brickwidth;
						int brickheight = map.brickheight;

						Rectangle rect = new Rectangle(brickX, brickY, brickwidth, brickheight);// Creates rectangle
						/*
						 * // at bricks // position at a // time now this // rectangle will // checks
						 * its // intersection with // the ball(for ball // also rectangle is // created
						 * as ball // is oval shape and // can't be check // intersect with //
						 * rectangle). so // ball rectangle // intersects with // bricks rectangle //
						 * check if it is // true then change // the map matrix // entry to zero and //
						 * disappear the // brick at that // position
						 */
						Rectangle rectball = new Rectangle(ballposX, ballposY, 20, 20); // creates a rectangle
																						// around a ball for
																						// checking intersection
																						// with a brick
						Rectangle brickrect = rect;
						if (rectball.intersects(brickrect)) {
							map.setBrickValue(0, i, j); // if its intersects then change the map array value to 0 at
														// that matrix position
							totalbricks--; // now as it intersects total bricks got one reduced
							score += 5; // also increase the score + 5

							if (ballposX + 19 <= brickrect.x || ballposX + 1 >= brickrect.x + brickrect.width) {
								balldirX = -balldirX; // change ball direction to its opposite direction in which it
														// is previously moving
							} else {
								balldirY = -balldirY; // change ball direction to its opposite direction i.e either
														// top or bottom direction
							}
							break A; // if ball has been intersected and change its direction by intersecting brick
										// and all done break out from outer for loop
						}

					}
				}

			}

			ballposX += balldirX; // here we are changing the position of ball in x direction i.e balldirX= -1;
			ballposY += balldirY; // here we are changing the position of ball in y direction i.e balldirY= -2; //
									// so now ball moves diagonally upward in left direction
			if (ballposX < 0) { // for checking that ball does not go out of border left side
				balldirX = -balldirX; // if ball touches border left then it now changes ball direction x in right
										// sides +1;
			}
			if (ballposY < 0) { // for checking that ball does not go out of border top side
				balldirY = -balldirY; // if ball touches border top then it now changes ball direction y in right
										// sides +2 i.e downwards diagonally;
			}
			if (ballposX > 670) { // for checking that ball does not go out of border right side
				balldirX = -balldirX; // if ball touches border right then it now changes ball direction x in right
										// sides +1;
			}
		}

		repaint(); // it will recall paint method again and draw all components we made in paint
					// method likes slider, ball etc
		// this method causes a call to this component's paint method as soon as
		// possible because when we are changing the coordinates of slider by
		// moveright() and moveleft method then the components are not redraw at that
		// specific position ( we changed by pressing right or left arrow key but they
		// initially draw at thier initial positon so we cannot see the slider moving if
		// we dont recall this method) themselves for this we need to recall the paint
		// method again

	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) { // it will check which keyboard key is pressed
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) { // if right arrow key is pressed then this will run (right arrow key
													// will have some code associated with it)
			if (playerX >= 600) { // it checking that if slider does not go out of panel
				playerX = 600; // if this go out we set it to extreme right position of panel
			} else {
				moveRight(); // else it move right side as we pressed a right arrow key
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) { // if left arrow key is pressed then this will run (left arrow key
													// will have some code associated with it)
			if (playerX <= 0) { // it checking that if slider does not go out of panel
				playerX = 0; // if this go out we set it to extreme left position of panel
			} else {
				moveLeft();// else it move left side as we pressed a left arrow key
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_ENTER) { // when enter is clicked when game is over then it will restart the
													// game
			if (play == false) { // first it check if game is over or not otherwise in game if we press enter the
									// it will behave game abnormally
				play = true; // now play is true if we press enter after game is stop
				ballposX = 120;
				ballposY = 350;
				balldirX = -1;// position of ball and direction of ball in x and y coordinates and slider
								// position is again initialized to its initial condition
				balldirY = -2;
				playerX = 310; // all things we initialized earlier are initialized again here
				score = 0;
				value(totalbricksbreak, originaldelay, row, col);
				repaint();
				// map object again created as because matrix again will be
				// created
				// otherwise when we enter restart then previous game bricks are there
				// as it is in new game so create matrix again so that bricks created again from
				// scratch and
				// components are again will be painted by calling repaint method
			}
		}

	}

	public void moveRight() {
		play = true; // play flag got true means we have started playing
		playerX += 20; // if we pressed right arrow key one time it will move 20 pixel coordinates to
						// the right
	}

	public void moveLeft() {
		play = true; // play flag got true means we have started playing
		playerX -= 20; // if we pressed left arrow key one time it will move 20 pixel coordinates to
						// the left
	}

}
