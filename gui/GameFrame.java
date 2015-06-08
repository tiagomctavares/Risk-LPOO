package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Logic.Game;

public class GameFrame {
	private static JPanel board;
	private static Game game;
	private static JFrame GameFrame;
	private static JPanel optionsForm;
	
	private static JComboBox<String> comboTo;
	private static JComboBox<String> comboFrom;
	private static JComboBox<Integer> numberReinforcements;
	private static String conquerRegionFrom;
	private static String conquerRegionTo;
	private static JComboBox<Integer> deployCombo;
	
	
	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	game = new Game();
            	board = new BoardPanel(game);
                createAndShowGUI();
            }
        });
	}
	
	public boolean gameEnd() {
		return false;
	}
	
	private static void createAndShowGUI() {
        GameFrame = new JFrame("Risk");
        GameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameFrame.setLayout(new BorderLayout());
		
		optionsForm = new JPanel();
		comboFrom = new JComboBox<String>();
        deployGameForm();
        optionsForm.setFocusable(false);

		GameFrame.add(optionsForm, BorderLayout.SOUTH);
		GameFrame.add(board, BorderLayout.NORTH);
		GameFrame.getContentPane().setSize(750,560);
        GameFrame.pack();
        GameFrame.setVisible(true);
    }
	
	private static void attackGameForm() {
		optionsForm.removeAll();
		comboFrom.removeAll();
		
		JPanel playForm = new JPanel();
		JLabel lblPlayer = new JLabel("Current Player - "+game.getCurrentPlayer().getName());
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblPlayer);
		
		JLabel lblBoardFrom = new JLabel("From:");
		lblBoardFrom.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblBoardFrom);

		String[] fromRegionsList = game.getPlayableRegionsList();
		comboFrom = new JComboBox<String>(fromRegionsList);
		comboFrom.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(comboFrom.getItemCount() > 0)
		    		refreshComboToValues(comboFrom.getSelectedItem().toString());
		    }
		});
		playForm.add(comboFrom);
		
		JLabel lblBoardTo = new JLabel("To:");
		lblBoardTo.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblBoardTo);
		String[] toRegions = game.getAttackableRegionsList(comboFrom.getSelectedItem().toString());
		comboTo = new JComboBox<String>(toRegions);
		
		playForm.add(comboTo);
		
		JButton attackAction = new JButton("Attack");
		attackAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboFrom.getItemCount() > 0)
					attackButton();
			}
		});
		playForm.add(attackAction);

		
		JButton endAttacks = new JButton("End Attacks");
		endAttacks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				endAttacksButton();
			}
		});
		playForm.add(endAttacks);
		
		optionsForm.add(playForm);
		optionsForm.validate();
		optionsForm.repaint();
}
	

	protected static void endAttacksButton() {
		reinforcementGameForm();
		
	}

	private static void refreshComboFromValues() {
		String[] fromRegionsList = game.getPlayableRegionsList();
		Object selected = comboFrom.getSelectedItem();
		comboFrom.removeAllItems();
		for(int i = 0; i < fromRegionsList.length; i++)
			comboFrom.addItem(fromRegionsList[i]);
		
		comboFrom.setSelectedItem(selected);
	}
	

	private static void refreshComboToValues(String string) {
		String[] toRegions = game.getAttackableRegionsList(string);
		comboTo.removeAllItems();
		for(int i = 0; i < toRegions.length; i++)
			comboTo.addItem(toRegions[i]);
	}


	private static void attackButton() {
		boolean canMoveFromConquerRegion = game.performAttack(comboFrom.getSelectedItem().toString(), comboTo.getSelectedItem().toString());
		if(canMoveFromConquerRegion) {
			conquerGameForm();
		} else {
			refreshComboFromValues();
			if(comboFrom.getItemCount() > 0)
				refreshComboToValues(comboFrom.getSelectedItem().toString());
			else
				comboTo.removeAllItems();
		}
		board.repaint();
	}

	private static void conquerGameForm() {
		conquerRegionFrom = comboFrom.getSelectedItem().toString(); 
		conquerRegionTo = comboTo.getSelectedItem().toString(); 
		
		optionsForm.removeAll();
		comboFrom.removeAll();
		
		JPanel playForm = new JPanel();
		JLabel lblFrom = new JLabel("From - "+ conquerRegionFrom);
		lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblFrom);
		
		JLabel lblTo = new JLabel("To - "+ conquerRegionTo);
		lblTo.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblTo);
		
		int maxReinforcements = game.getReinforcements(conquerRegionFrom);
		numberReinforcements = new JComboBox<Integer>();
		for(int i=maxReinforcements; i>=0; i--)
			numberReinforcements.addItem(i);
		playForm.add(numberReinforcements);
		
		JButton reinforceAction = new JButton("Move Troops");
		reinforceAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				moveToConquerRegion();
			}
		});
		playForm.add(reinforceAction);
		
		optionsForm.add(playForm);
		optionsForm.validate();
		optionsForm.repaint();
	}
	
	protected static void moveToConquerRegion() {
		game.moveTroops(conquerRegionFrom, conquerRegionTo, Integer.parseInt(numberReinforcements.getSelectedItem().toString()));
		attackGameForm();
		board.repaint();
	}
	
	private static void reinforcementGameForm() {
		optionsForm.removeAll();
		comboFrom.removeAll();
		
		JPanel playForm = new JPanel();
		JLabel lblPlayer = new JLabel("Current Player - "+game.getCurrentPlayer().getName());
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblPlayer);
		
		JLabel lblBoardFrom = new JLabel("From:");
		lblBoardFrom.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblBoardFrom);

		String[] fromRegionsList = game.getReinforceRegionsList();
		comboFrom = new JComboBox<String>(fromRegionsList);
		comboFrom.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if(comboFrom.getItemCount() > 0) {
		    		refreshReinforceComboToValues(comboFrom.getSelectedItem().toString());
		    		refreshComboTroopsNumber(comboFrom.getSelectedItem().toString());
		    	}
		    }
		});
		playForm.add(comboFrom);
		
		JLabel lblBoardTo = new JLabel("To:");
		lblBoardTo.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblBoardTo);
		String[] toRegions = game.getReinforceDestinationRegionsList(comboFrom.getSelectedItem().toString());
		comboTo = new JComboBox<String>(toRegions);
		
		playForm.add(comboTo);
		
		int maxReinforcements = game.getReinforcements(comboFrom.getSelectedItem().toString());
		numberReinforcements = new JComboBox<Integer>();
		for(int i=maxReinforcements; i>=1; i--)
			numberReinforcements.addItem(i);
		
		playForm.add(numberReinforcements);
		
		JButton reinforceAction = new JButton("Reinforce");
		reinforceAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reinforceButton();
			}
		});
		playForm.add(reinforceAction);
		
		JButton endReinforceAction = new JButton("End Turn");
		endReinforceAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				endReinforceButton();
			}
		});
		
		playForm.add(endReinforceAction);
		
		optionsForm.add(playForm);
		optionsForm.validate();
		optionsForm.repaint();
	}

	protected static void refreshReinforceComboToValues(String string) {
		String[] toRegions = game.getReinforceDestinationRegionsList(string);
		comboTo.removeAllItems();
		for(int i = 0; i < toRegions.length; i++)
			comboTo.addItem(toRegions[i]);
	}

	protected static void refreshComboTroopsNumber(String string) {
		numberReinforcements.removeAllItems();
		int maxReinforcements = game.getReinforcements(string);
		for(int i = maxReinforcements; i>=1; i--)
			numberReinforcements.addItem(i);
	}

	protected static void reinforceButton() {
		String regionFromName = comboFrom.getSelectedItem().toString();
		String regionToName = comboTo.getSelectedItem().toString();
		int troopsNumber = Integer.parseInt(numberReinforcements.getSelectedItem().toString());
		
		game.moveTroops(regionFromName, regionToName, troopsNumber);
		
		endReinforceButton();
	}

	protected static void endReinforceButton() {
		board.repaint();
		game.playerEndTurn();
		
		deployGameForm();
	}
	
	private static void deployGameForm() 
	{
		optionsForm.removeAll();
		comboFrom.removeAll();
		
		JPanel playForm = new JPanel();
		JLabel lblPlayer = new JLabel("Current Player - "+game.getCurrentPlayer().getName());
		lblPlayer.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblPlayer);
		
		JLabel lblDeployFrom = new JLabel("Deploy On:");
		lblDeployFrom.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblDeployFrom);

		String[] deployRegionsList = game.getDeployRegionsList();
		comboFrom = new JComboBox<String>(deployRegionsList);
		playForm.add(comboFrom);

		JLabel lblTroopsTo = new JLabel("Troops:");
		lblTroopsTo.setHorizontalAlignment(SwingConstants.LEFT);
		playForm.add(lblTroopsTo);

		int deployTroops = game.getDeployNumberForPlayer();
		deployCombo = new JComboBox<Integer>();
		for(int i = deployTroops; i>=1; i--) {
			deployCombo.addItem(i);
		}
		
		playForm.add(deployCombo);
		
		JButton deployAction = new JButton("Deploy");
		deployAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				deployButton();
			}
		});
		playForm.add(deployAction);
		
		optionsForm.add(playForm);
		optionsForm.validate();
		optionsForm.repaint();
	}

	protected static void deployButton() {
		String regionDeploy = comboFrom.getSelectedItem().toString();
		int deployNumber = Integer.parseInt(deployCombo.getSelectedItem().toString());
		
		game.deployTroops(regionDeploy, deployNumber);
		if(game.getDeployNumberForPlayer() == 0)
			attackGameForm();
		else
			deployGameForm();
		
		board.repaint();
	}
}