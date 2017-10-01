package Übung10;

public class Life{
	private int rows;
	private int columns;
	private Stone[][] gameBoard;
	
	public Life(int rows, int columns){
		this.rows=rows;
		this.columns=columns;
		this.gameBoard=new Stone[rows][columns];
	}	
	
	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public Stone[][] getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(Stone[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public static void main(String args[]) {
		Life life = new Life(5,11);
		
		life.init();
		
		while(true){
			life.setNeighboursForAllStones();
			life.print();
			life.calc();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	private void init() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				double random = Math.random();
				if (random > 0.5) {
					random = Math.random();
					if(random>0.8){
						gameBoard[i][j]=new AlwaysStone();
					}else{
						gameBoard[i][j] = new Stone(true);
					}
				} else {
					random = Math.random();
					if(random>0.8){
						gameBoard[i][j]=new NeverStone();
					}else{
						gameBoard[i][j] = new Stone(false);
					}
				}
			}
		}
		
	}
	
	private void setNeighboursForAllStones(){
		for(int i=0; i<gameBoard.length;i++){
			for(int j=0; j<gameBoard[i].length;j++){
				Stone stone = gameBoard[i][j];
				Stone[] neighbours = new Stone[8];
				
				//oben links
				neighbours[0]=gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)]; 	
				//oben
				neighbours[1]=gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][j];
				//oben rechts
				neighbours[2]=gameBoard[(i - 1 + gameBoard.length) % (gameBoard.length)][(j + 1) % gameBoard[i].length];
				//rechts
				neighbours[3]=gameBoard[i][(j + 1) % gameBoard[i].length];
				//unten rechts
				neighbours[4]=gameBoard[(i + 1) % gameBoard.length][(j + 1) % gameBoard[i].length];
				//unten
				neighbours[5]=gameBoard[(i + 1) % gameBoard.length][j];
				//unten links
				neighbours[6]=gameBoard[(i + 1) % gameBoard.length][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)];
				//links
				neighbours[7]=gameBoard[i][(j - 1 + gameBoard[i].length) % (gameBoard[i].length)];
				
				stone.setNeighbours(neighbours);
			}
		}
	}

	private void print() {
		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				System.out.print(gameBoard[i][j].print());	
			}
			System.out.println();
		}

		System.out.println("____________________________________");
		System.out.println();
	}

	private void calc() {
		Stone[][] newGameBoard = new Stone[rows][columns];

		for (int i = 0; i < gameBoard.length; i++) {
			for (int j = 0; j < gameBoard[i].length; j++) {
				Stone stone = gameBoard[i][j];
				stone.countNeighbours();
				
				if((stone.isAlive() && stone.getNumberOfNeighbours()==3)){
					newGameBoard[i][j]=stone.isAlwaysStone()?new Stone(true,false,stone.getRoundsAlive()+1):new Stone(true, stone.getRoundsAlive()+1);
				}else if (stone.isAlive() && stone.getNumberOfNeighbours() == 2) {
					newGameBoard[i][j]=stone.isAlwaysStone()?new Stone(true,false,stone.getRoundsAlive()+1):new Stone(true, stone.getRoundsAlive()+1);
				} else if (!stone.isAlive() && stone.getNumberOfNeighbours() == 3) {
					newGameBoard[i][j]=stone.isNeverStone()?new Stone(false,true,stone.getRoundsAlive()+1):new Stone(true, stone.getRoundsAlive()+1);
				} else if (stone.getNumberOfNeighbours() > 3 || stone.getNumberOfNeighbours() < 2) {
					newGameBoard[i][j] = stone.isNeverStone() ? new Stone(false,true) : (stone.isAlwaysStone()?new Stone(true,false):new Stone(false));
				} else {
					newGameBoard[i][j] = stone.isNeverStone() ? new Stone(false,true) : (stone.isAlwaysStone()?new Stone(true,false):new Stone(false));
				}
			}
		}

		for (int i = 0; i < newGameBoard.length; i++) {
			for (int j = 0; j < newGameBoard[i].length; j++) {
				gameBoard[i][j] = newGameBoard[i][j];
			}
		}
		
	}
}