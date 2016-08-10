package she;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Gogo extends JPanel {
	public static final int ROWS = 20;
	public static final int COLS = 20;
	public static final int CELL_SIZE = 26;
	// private Snake;
	/**
	 * 墙
	 */
	static Cell[][] wall = new Cell[20][20];
	/**
	 * 添加图片
	 */
	public static Image background;
	public static Image T;
	public static Image L;
	public static Image gameOverImg;
	private static Snake Snake;
	private static Cell randomOne;
	private Timer timer;
	private int inteval = 300;
	private boolean gameOver;

	public static void main(String[] args) {
		JFrame frame = new JFrame("贪食蛇");
		Gogo gogo = new Gogo();
		frame.setBackground(new Color(0xffffff));
		frame.add(gogo);
		frame.setSize(528, 550);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gogo.Action();

	}

	public void paintWall(Graphics g) {
		for (int y = 0; y < wall.length; y++) {
			Cell[] line = wall[y];
			for (int x = 0; x < line.length; x++) {
				Cell cell = line[x];
				int a = x * CELL_SIZE;
				int b = y * CELL_SIZE;
				if (cell == null) {
					g.setColor(new Color(0));
					g.drawRect(a, b, CELL_SIZE, CELL_SIZE);

				} else {
					g.drawImage(cell.getImage(), a - 1, b - 1, null);
				}
			}
		}

	}

	static {
		// class 类提供了一个方法 getResource() 可以找到
		// package中的文件位置
		// 图片文件到内存中的对象
		// teteis.png 文件 与 Tetris.class 在同一个包中
		try {
			Class cls = Gogo.class;
			background = ImageIO.read(cls.getResource("background.png"));
			T = ImageIO.read(cls.getResource("T.png"));
			L = ImageIO.read(cls.getResource("L.png"));

			gameOverImg = ImageIO.read(cls.getResource("game-over.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		g.drawImage(background, 0, 0, null);
		// g.translate(15, 15);// 坐标系平移
		paintWall(g);
		paintCell(g);
		paintSnake(g);
		if (gameover()) {
			timer.cancel();
			repaint();
			g.drawImage(gameOverImg, 0, 0, null);
		}
		// paintNextOne(g);
		// g.translate(-15, -15);
	}

	private void paintSnake(Graphics g) {

		Cell[] cells = Snake.snake;
		for (int i = 0; i < cells.length; i++) {
			Cell cell = cells[i];
			int x = cell.getX() * CELL_SIZE;
			int y = cell.getY() * CELL_SIZE;
			g.drawImage(cell.getImage(), x - 1, y - 1, null);
		}
	}

	private void paintCell(Graphics g) {
		Cell cell = randomOne;
		int x = cell.getX() * CELL_SIZE;
		int y = cell.getY() * CELL_SIZE;
		g.drawImage(cell.getImage(), x - 1, y - 1, null);
	}

	public void randomOne() {
		Random random = new Random();
		int a = random.nextInt(20);
		int b = random.nextInt(20);
		randomOne = new Cell(a, b, L);
		for (int i = 0; i < Snake.snake.length; i++) {
			int x = Snake.snake[i].getX();
			int y = Snake.snake[i].getY();
			if (a == x && b == y) {
				randomOne();
			}
		}
	}

	public void Action() {
		starAction();
		dropAction();
		checkGameOver();
		System.out.println("lkasjdfklkdsjl");
		// System.out.println(randomOne.getX() + "," + randomOne.getY());
		// nextOne = new Cell(a,b,L);
		
		KeyListener l = new KeyAdapter() {
			int index = 4;
			/** 如果有按键按下完成时候(Pressed) 就会执行 */
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if(index  == 4){
				switch (key) {
				case KeyEvent.VK_DOWN:
					timer.cancel();
					index  =4;
					dropAction();
					break;
				case KeyEvent.VK_RIGHT:
					timer.cancel();
					index  = 2;
					moveRightAction();
					
					break;
				case KeyEvent.VK_LEFT:
					timer.cancel();
					index  = 3;
					moveLeftAction();
					break;	
				}
				
				}
				if(index  == 2){
					switch (key) {
					case KeyEvent.VK_DOWN:
						timer.cancel();
						index  =4;
						dropAction();
						break;
					case KeyEvent.VK_RIGHT:
						timer.cancel();
						index  = 2;
						moveRightAction();
						
						break;
					case KeyEvent.VK_UP:
						timer.cancel();
						index  = 1;
						upAction();
						break;	
					}
					
					}
				if(index  == 3){
					switch (key) {
					case KeyEvent.VK_DOWN:
						timer.cancel();
						index  =4;
						dropAction();
						break;
					case KeyEvent.VK_UP:
						timer.cancel();
						index  = 1;
						upAction();
						
						break;
					case KeyEvent.VK_LEFT:
						timer.cancel();
						index  = 3;
						moveLeftAction();
						break;	
					}
					
					}
				if(index  == 1){
					switch (key) {
					case KeyEvent.VK_UP:
						timer.cancel();
						index  =1;
						upAction();
						break;
					case KeyEvent.VK_RIGHT:
						timer.cancel();
						index  = 2;
						moveRightAction();
						
						break;
					case KeyEvent.VK_LEFT:
						timer.cancel();
						index  = 3;
						moveLeftAction();
						break;	
					}
					
					}
				repaint();
				System.out.println(key);
				// tetromino.moveLeft();
				// repaint();
			}
		};
		this.addKeyListener(l);
		this.requestFocus();
	}

	public void starAction() {

		for (Cell[] line : wall) {
			Arrays.fill(line, null);
		}
		Snake = new Snake();
		randomOne();

	}

	public boolean outOfLeft() {
		int x = Snake.snake[0].getX();
		if (x < 0) {
			return true;
		}
		return false;
	}

	public boolean outOfRight() {
		int x = Snake.snake[0].getX();
		if (x > 19) {
			return true;
		}
		return false;
	}

	public boolean outOfUp() {
		int y = Snake.snake[0].getY();
		if (y < 0) {
			return true;
		}
		return false;
	}

	public boolean outOfDrop() {
		int y = Snake.snake[0].getY();
		if (y > 19) {
			return true;
		}
		return false;
	}

	public void moveLeftAction() {

		TimerTask task = new TimerTask() {
			public void run() {
				moveLeft();

				if (gameover()) {
					timer.cancel();
				}
				eatSnake();

				repaint();
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, inteval);

	}

	public void moveRightAction() {
		TimerTask task = new TimerTask() {
			public void run() {
				moveRight();
				if (gameover()) {
					timer.cancel();
				}
				eatSnake();

				repaint();
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, inteval);

	}

	public void dropAction() {
		//inteval = 400;
		TimerTask task = new TimerTask() {
			public void run() {
				drop();
				if (gameover()) {
					timer.cancel();
				}
				eatSnake();

				repaint();
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, inteval);

	}

	public void upAction() {

		TimerTask task = new TimerTask() {
			public void run() {
				up();
				if (gameover()) {
					timer.cancel();
				}
				eatSnake();

				repaint();
			}
		};
		timer = new Timer();
		timer.schedule(task, 0, inteval);

	}

	public void moveLeft() {
		if (outOfLeft()) {
			for (Cell cell : Snake.snake) {
				cell.moveRight();
				timer.cancel();
				
			}
		} else {
			for (int i = Snake.snake.length - 1; i >= 0; i--) {
				if (i == 0) {
					Cell cell = Snake.snake[0];
					cell.moveLeft();
					break;
				}
				int x = Snake.snake[i - 1].getX();
				Snake.snake[i].setX(x);
				int y = Snake.snake[i - 1].getY();
				Snake.snake[i].setY(y);

			}
		}
	}

	public void moveRight() {
		if (outOfRight()) {
			for (Cell cell : Snake.snake) {
				cell.moveLeft();
				timer.cancel();
			}
		} else {
			for (int i = Snake.snake.length - 1; i >= 0; i--) {
				if (i == 0) {
					Cell cell = Snake.snake[0];
					cell.moveRight();
					break;
				}
				int x = Snake.snake[i - 1].getX();
				Snake.snake[i].setX(x);
				int y = Snake.snake[i - 1].getY();
				Snake.snake[i].setY(y);

			}
		}
	}

	public void drop() {

		if (outOfDrop()) {
			for (Cell cell : Snake.snake) {
				cell.Up();
				timer.cancel();
			}
		} else {
			for (int i = Snake.snake.length - 1; i >= 0; i--) {
				if (i == 0) {
					Cell cell = Snake.snake[0];
					cell.Drop();
					break;
				}
				int x = Snake.snake[i - 1].getX();
				Snake.snake[i].setX(x);
				int y = Snake.snake[i - 1].getY();
				Snake.snake[i].setY(y);

			}
		}
	}

	public void up() {
		if (outOfUp()) {
			for (Cell cell : Snake.snake) {
				cell.Drop();
				timer.cancel();
			}

		} else {
			for (int i = Snake.snake.length - 1; i >= 0; i--) {
				if (i == 0) {
					Cell cell = Snake.snake[0];
					cell.Up();
					break;
				}
				int x = Snake.snake[i - 1].getX();
				Snake.snake[i].setX(x);
				int y = Snake.snake[i - 1].getY();
				Snake.snake[i].setY(y);

			}
		}
	}

	KeyListener l = new KeyAdapter() {
		/** 如果有按键按下完成时候(Pressed) 就会执行 */
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (gameover()) {
				return;
			}
			switch (key) {

			case KeyEvent.VK_DOWN:
				drop();
				break;
			case KeyEvent.VK_RIGHT:
				moveRight();
				break;
			case KeyEvent.VK_LEFT:
				moveLeft();
				break;
			case KeyEvent.VK_UP:
				up();
				break;

			}
			repaint();
			System.out.println(key);
			// tetromino.moveLeft();
			// repaint();
		}
	};

	public void eatSnake() {
		int x = Snake.snake[0].getX();
		int y = Snake.snake[0].getY();
		int a = randomOne.getX();
		int b = randomOne.getY();
		Cell cell = new Cell(Snake.snake[1].getX(), Snake.snake[1].getY(), L);
		//checkGameOver();
		if (x == a && y == b) {
		//	Cell cell = new Cell(randomOne.getX(),randomOne.getY(),randomOne.getImage());
			
			Snake.snake = Arrays.copyOf(Snake.snake, Snake.snake.length + 1);
			Snake.snake[Snake.snake.length - 1] = cell;
			randomOne();
		}

	}

	public boolean gameover() {

		Cell snakehead = Snake.snake[0];
		for (int i = 1; i < Snake.snake.length; i++) {
			Cell cell = Snake.snake[i];
			int x = cell.getX();
			int y = cell.getY();
			if (x == snakehead.getX() && y == snakehead.getY()) {
				return true;
			}
		}
		return false;
	}

	public void checkGameOver() {
		if (gameover()) {
			timer.cancel();
			repaint();
		}
	}
}
