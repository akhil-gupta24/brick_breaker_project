import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map1 {

	public int map[][]; // represent 2d matrix of bricks
	int brickwidth;
	int brickheight;

	public Map1(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1; // indicates that brick at that position is still there and not intersect with
								// ball , if it intersects with ball then map[i][j]=0 indicates that brick is
								// break and got disappear from frame
			}
		}
		brickwidth = 540 / col; // one brick width
		brickheight = 150 / row;// one brick height
	}

	public void draw(Graphics2D g) { // to draw the brick rectangle at place where matrix map[i][j]=1; at that
										// position
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if (map[i][j] > 0) { // if map[i][j]=1 them draw bricks at that position
					g.setColor(Color.white);
					g.fillRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight); // position of
																									// bricks starts at
																									// initially 80
																									// pixels x and 50
																									// pixels y
																									// direction and as
																									// loop iterate it
																									// form a matrix
																									// bricks by
																									// mutliply thr
																									// height and width
																									// of bricks at
																									// coordinates of a
																									// matrix

					g.setStroke(new BasicStroke(3));// Sets the Stroke for the Graphics2D context and constructs a solid
													// BasicStroke with the specified line width
					g.setColor(Color.black);
					g.drawRect(j * brickwidth + 80, i * brickheight + 50, brickwidth, brickheight);// Draws the outline
																									// of the specified
																									// rectangle.The
																									// left and right
																									// edges of the
																									// rectangle are at
																									// x and x +
																									// width.The top and
																									// bottom edges are
																									// at y and y +
																									// height
				}

			}
		}
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

}
