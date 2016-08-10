package she;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class lianxi {
	private Cell[][] wall = new Cell[20][20];
	public static Image background;
	public static Image T;
	public static Image L;
	private final static int CELL_SIZE = 26;
	static{
		try{
			Class cls = lianxi.class;
			background = ImageIO.read(cls.getResource("tetris.png"));
			T = ImageIO.read(cls.getResource("T.png"));
			L = ImageIO.read(cls.getResource("L.png"));
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void paintwall(Graphics g){
		for(int i = 0;i<wall.length;i++){
			Cell[] line = wall[i];
			for(int j = 0;j<line.length;j++){
				int x = line[j].getX()*CELL_SIZE;
				int y = line[j].getY()*CELL_SIZE;
				if(line[j] == null){
				g.setColor(new Color(0));
				g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
				}
			}
		}
	}
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		lianxi lian = new lianxi();
		frame.setBackground(new Color(0xffffff));
		
		
		
		
	}
}
