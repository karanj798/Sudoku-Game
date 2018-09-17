import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 100619029
 */
public class GameBoard extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 4869598710581014247L;
	JButton b[][];
	JLabel l[][];
	Container con;
	JButton restart, back, submit;
	private int[][] gameBoard, currentBoardSolution, guiBoard;

	int size;

	public GameBoard(int size) {
		JMenuBar jMenuBar1 = new javax.swing.JMenuBar();
		JMenu jMenu1 = new javax.swing.JMenu();
		JMenuItem exitMenuItem = new javax.swing.JMenuItem();
		JMenu jMenu2 = new javax.swing.JMenu();
		JMenuItem instructionMenuItem = new javax.swing.JMenuItem();

		jMenu1.setText("File");

		exitMenuItem.setText("Exit");

		exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				exitMenuItemActionPerformed(evt);
			}

			private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_exitMenuItemActionPerformed
				// TODO add your handling code here:
				setVisible(false);
				dispose();
			}// GEN-LAST:event_exitMenuItemActionPerformed
		});
		jMenu1.add(exitMenuItem);
		jMenuBar1.add(jMenu1);

		instructionMenuItem.setText("Instructions");
		instructionMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				instructionMenuItemActionPerformed(evt);
			}

			private void instructionMenuItemActionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null,
						"Each row, column, and nonet can contain each number (typically 1 to 9) exactly once.\r\n"
								+ "The sum of all numbers in any nonet, row, or column must match the small number printed in its corner. For traditional Sudoku puzzles featuring the numbers 1 to 9, this sum is equal to 45.");

			}
		});
		jMenu2.add(instructionMenuItem);
		jMenu2.setText("Help");
		jMenuBar1.add(jMenu2);

		setJMenuBar(jMenuBar1);

		setLayout(new BorderLayout());
		JPanel jp = new JPanel(new GridLayout(size, size));
		this.b = new JButton[size][size];
		this.l = new JLabel[size][size];
		this.size = size;
		for (int i = 0; i < b.length; i++) {
			for (int j = 0; j < b[i].length; j++) {
				b[i][j] = new JButton();
				jp.add(b[i][j]);
			}
		}
		JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel jp1sub = new JPanel(new GridLayout(0, 3));
		back = new JButton("Back");
		back.addActionListener(this);
		jp1sub.add(back, BorderLayout.WEST);
		restart = new JButton("Restart");
		restart.addActionListener(this);
		jp1sub.add(restart, BorderLayout.EAST);
		submit = new JButton("Submit");
		submit.addActionListener(this);
		jp1sub.add(submit, BorderLayout.CENTER);

		jp1.add(jp1sub);
		add(jp, BorderLayout.NORTH);
		add(jp1, BorderLayout.SOUTH);

		int len = size;
		switch (size) {
		case 4: {
			setSize(300, 225);
			break;
		}
		case 6: {
			setSize(300, 275);
			break;
		}
		default: {
			setSize(375, 375);
			break;
		}
		}

		currentBoardSolution = creatSolutionBoard(new int[len][len], 0, len * len);
		gameBoard = generateGame(copy2Dimensional(currentBoardSolution));
		guiBoard = new int[len][len];

		for (int x = 0; x < currentBoardSolution.length; x++) {
			for (int y = 0; y < currentBoardSolution[x].length; y++) {
				System.out.print(currentBoardSolution[x][y] + " ");
			}
			System.out.println();
		}

		int c = 0;
		System.out.println();
		for (int x = 0; x < gameBoard.length; x++) {
			for (int y = 0; y < gameBoard[x].length; y++) {
				System.out.print(gameBoard[x][y] + " ");
				if (gameBoard[x][y] == 0)
					c++;
			}
			System.out.println();
		}

		System.out.println(c);
		System.out.println(gameBoard.length);

		initializeBoard(gameBoard);

	}

	public int[][] getGameBoard() {
		return this.gameBoard;
	}

	public int[][] getSolutionGameBoard() {
		return this.currentBoardSolution;
	}

	public int[][] getGuiBoard() {
		for (int x = 0; x < b.length; x++) {
			for (int y = 0; y < b[x].length; y++) {
				if(b[x][y].getText().equals("")) 
					guiBoard[x][y] = 0;
				else 
					guiBoard[x][y] = Integer.parseInt(b[x][y].getText());
				
			}
		}
		return guiBoard;
	}

	private void initializeBoard(int board[][]) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0) {
					b[i][j].setText("");
					b[i][j].setName(String.valueOf(i) + String.valueOf(j));
					b[i][j].addActionListener(this);

				} else {
					b[i][j].setText(String.valueOf(board[i][j]));
					b[i][j].setVisible(true);

				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == restart) {
			this.setVisible(false);
			this.dispose();
			new GameBoard(size).setVisible(true);
		} else if (ae.getSource() == submit) {
			System.out.println("submit pressed");
			if (Arrays.deepEquals(gameBoard, currentBoardSolution)) {
				JOptionPane.showMessageDialog(null, "Valid Solution.");
			}
		} else if (ae.getSource() == back) {
			setVisible(false);
			dispose();
			new StartActivity().setVisible(true);
			System.out.println("back");
		}

		for (JButton[] b1 : b) {
			for (JButton b11 : b1) {
				if (ae.getSource().equals(b11)) {
					String input;
					input = JOptionPane.showInputDialog("Enter input: ");
					if (input != null) {
						while (!input.matches("[0-" + size + "]")) {
							input = JOptionPane.showInputDialog("Enter valid input: ");
						}
						while (Integer.valueOf(input) > size || Integer.valueOf(input) < 1) {
							input = JOptionPane.showInputDialog("Enter valid input: ");
						}
					}
					b11.setText(input);
					updateBoard();
				}
			}
		}
	}

	public void updateBoard() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (b[i][j].getText() == "") {
					gameBoard[i][j] = 0;
				} else {
					gameBoard[i][j] = Integer.valueOf(b[i][j].getText());
				}
			}
		}
	}

	public int[][] creatSolutionBoard(int[][] gameBoard, int currIndex, int length) {
		if (currIndex > length - 1)
			return gameBoard; // return board after filled

		int x = currIndex % gameBoard.length; // x position of given index
		int y = currIndex / gameBoard.length; // y position of given index

		ArrayList<Integer> numbers = new ArrayList<Integer>();
		for (int i = 1; i <= gameBoard.length; i++)
			numbers.add(i);
		Collections.shuffle(numbers);

		while (numbers.size() > 0) {
			int number = getNextPossibleNumber(gameBoard, x, y, numbers);
			if (number == -1)
				return null;

			gameBoard[y][x] = number;
			int[][] tempSolGame = creatSolutionBoard(gameBoard, currIndex + 1, length);
			if (tempSolGame != null)
				return tempSolGame;
			gameBoard[y][x] = 0;
		}

		return null;
	}

	public int getNextPossibleNumber(int[][] game, int x, int y, List<Integer> numbers) {
		while (numbers.size() > 0) {
			int number = numbers.remove(0); // takes the first number in the shuffled array list
			if (isNextNumLegal(game, y, x, number) && isPossibleInBlock(game, x, y, number)) // checks the removed number if it fits in the game board
				return number;
		}
		return -1;
	}

	public boolean isNextNumLegal(int[][] gameBoard, int xRowCheck, int yColCheck, int givenNumber) {
		for (int len = 0; len < gameBoard.length; len++) {
			if (gameBoard[xRowCheck][len] == givenNumber)
				return false;
			else if (gameBoard[len][yColCheck] == givenNumber)
				return false;
		}
		return true;
	}

	public int[][] copy2Dimensional(int[][] gameBoard) {
		int[][] copyOfGame = new int[gameBoard.length][gameBoard.length];
		for (int x = 0; x < copyOfGame.length; x++) {
			for (int y = 0; y < copyOfGame[x].length; y++)
				copyOfGame[x][y] = gameBoard[x][y];
		}
		return copyOfGame;
	}

	public int[][] generateGame(int[][] gameBoard) {
		ArrayList<Integer> positionsNum = new ArrayList<Integer>();
		for (int i = 0; i < gameBoard.length * gameBoard.length; i++)
			positionsNum.add(i);
		Collections.shuffle(positionsNum);
		return generateGameOnPosition(gameBoard, positionsNum);
	}

	public int[][] generateGameOnPosition(int[][] gameBoard, ArrayList<Integer> positionsNum) {
		while (positionsNum.size() > 0) {
			int position = positionsNum.remove(0);
			int x = position % gameBoard.length;
			int y = position / gameBoard.length;
			int temp = gameBoard[y][x];
			gameBoard[y][x] = 0;

			if (!isPlacementValid(gameBoard, 0, new int[] { 0 }))
				gameBoard[y][x] = temp;
		}

		return gameBoard;
	}

	public boolean isPlacementValid(int[][] gameBoard, int currIndex, int[] numberOfSolutions) {
		if (currIndex > gameBoard.length * gameBoard.length - 1) {
			return ++numberOfSolutions[0] == 1;
		}

		int x = currIndex % gameBoard.length;
		int y = currIndex / gameBoard.length;

		if (gameBoard[y][x] == 0) {
			List<Integer> numbers = new ArrayList<Integer>();
			for (int i = 1; i <= gameBoard.length; i++)
				numbers.add(i);

			while (numbers.size() > 0) {
				int number = getNextPossibleNumber(gameBoard, x, y, numbers);
				if (number == -1)
					break;
				gameBoard[y][x] = number;

				if (!isPlacementValid(gameBoard, currIndex + 1, numberOfSolutions)) {
					gameBoard[y][x] = 0;
					return false;
				}
				gameBoard[y][x] = 0;
			}
		} else if (!isPlacementValid(gameBoard, currIndex + 1, numberOfSolutions))
			return false;

		return true;
	}
	
	private static boolean isPossibleInBlock(int[][] game, int x, int y, int number) {
        int x1 = 0;
        int y1 = 0;
 
        switch (game.length) {
        case 4: {
            if (x < 2)
                x1 = 0;
            else
                x1 = 2;
 
            if (y < 2)
                y1 = 0;
            else
                y1 = 2;
 
            break;
        }
        case 6: {
            if (x < 3) {
                x1 = 0;
            } else
                x1 = 3;
 
            if (y < 2)
                y1 = 0;
            else if (y < 4)
                y1 = 2;
            else
                y1 = 4;
 
            break;
 
        }
        default: {
 
            if (x < 3)
                x1 = 0;
            else if (x < 6)
                x1 = 3;
            else
                x1 = 6;
 
            if (y < 3)
                y1 = 0;
            else if (y < 6)
                y1 = 3;
            else
                y1 = 6;
 
            break;
        }
        }
 
        for (int yy = y1; yy < y1 + (game.length == 9 ? 3 : 2); yy++) {
            for (int xx = x1; xx < x1 + (game.length == 4 ? 2 : 3); xx++) {
                if (game[yy][xx] == number)
                    return false;
            }
        }
        return true;
    }
}