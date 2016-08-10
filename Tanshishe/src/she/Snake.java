package she;

public class Snake {
	Cell[] snake = new Cell[3];
	public Snake(){
		snake[0] = (new Cell(9,10,Gogo.T));
		snake[1] = (new Cell(9,9,Gogo.L));
		snake[2] = (new Cell(9,8,Gogo.L));
	}
	public void Drop(){
		for(Cell cell:snake){
			cell.Drop();
		}
	}
	public void moveLeft(){
		for(Cell cell:snake){
			cell.moveLeft();
		}
	}
	public void moveRight(){
		for(Cell cell:snake){
			cell.moveRight();
		}
	}
	public void Up(){
		for(Cell cell:snake){
			cell.Up();
		}
	}
}
