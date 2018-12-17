import java.awt.*;

public class Wall extends WallOrChain {

	public Wall(int x_Ind, int y_Ind, int[] xCoors, int[] yCoors, Color c, int index, int initialXShift,
			int initialYShift, int squareHeight, int squareWidth, int mapHeight, int mapWidth) {
		super(x_Ind, y_Ind, xCoors, yCoors, c, index, initialXShift, initialYShift, squareHeight, squareWidth,
				mapHeight, mapWidth);
	}

	void draw(Graphics g, int initialXShift, int initialYShift, int squareHeight, int squareWidth, int shiftY) {
		drawWallOption(g, initialXShift, initialYShift, squareHeight, squareWidth);
		setTheRectanglePoints(squareHeight, squareWidth, shiftY);
		g.setColor(getColor());

		if (visible) {
			
			Graphics2D g2 = (Graphics2D) g;
			g2.fill(area);

			int healthWidth = squareWidth / 3;
			int healthHeight = squareWidth / 8;
			Rectangle nearestToCenter = getNearestRectToCenter();
			int CoorX = (int) nearestToCenter.getCenterX();
			int CoorY = (int) nearestToCenter.getCenterY();

			g.setColor(Color.GREEN);
			g.drawRect(CoorX - healthWidth / 2, CoorY - healthHeight / 2 - lineWidth, healthWidth,
					healthHeight);
			int r = (int) (510 * (1 - health * 1.0 / initialHealth));
			int gr = (int) (510 * (health * 1.0 / initialHealth));
			g.setColor(new Color(r >= 255 ? 255 : r, gr>= 255 ? 255 : gr, 0));
			g.fillRect(CoorX - healthWidth / 2, CoorY - healthHeight / 2 - lineWidth,
					(int) (healthWidth * (health * 1.0 / initialHealth)), healthHeight);
			       
			Graphics2D g3 = (Graphics2D) g;
           		g3.setColor(Color.gray.brighter().brighter().brighter());
         		g3.fill(areaForSquare);

		} else {
			Graphics2D g2 = (Graphics2D) g;
			g2.fill(areaForSquare);
		}

		if (collapsed) {
			g.setColor(Color.GRAY);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(2));
			g2.drawLine(wallContainer.x, wallContainer.y, wallContainer.x + wallContainer.width,
					wallContainer.y + wallContainer.height);
			g2.drawLine(wallContainer.x + wallContainer.width, wallContainer.y, wallContainer.x,
					wallContainer.y + wallContainer.height);
			g2.setStroke(new BasicStroke(1));
		}
	}

	private void drawWallOption(Graphics g, int initialXShift, int initialYShift, int squareHeight, int squareWidth) {

		g.setColor(Color.GRAY.brighter());
		g.fillRect(wallContainer.x, wallContainer.y, wallContainer.width, wallContainer.height);

		g.setColor(Color.GRAY);
		g.drawRect(wallContainer.x, wallContainer.y, wallContainer.width, wallContainer.height);

		g.setColor(Color.BLACK);
		g.setFont(new Font("TimesRoman", Font.PLAIN, squareHeight / 6));

		g.drawString("" + (index + 1), wallContainer.x + squareWidth / 14, wallContainer.y + squareHeight / 6);

	}

	public int getWholeMapIndex() {
		return Model.WALL;
	}
}