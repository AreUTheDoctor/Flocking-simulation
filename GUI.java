import turtle.FlockingTurtle;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import drawing.Canvas;
import geometry.CartesianCoordinate;
import obstacle.Obstacle;

public class GUI {
	private JFrame frame;
	private Canvas canvas;
	private JPanel lowerPanel;
	private final int WINDOW_X_SIZE = 800;
	private final int WINDOW_Y_SIZE = 600;
	private Obstacle obstacle;
	private Gameplay gameplay;

	private List<FlockingTurtle> turtles;
	private JButton addTurtleButton;
	
	private JSlider cohesion;
	private JSlider align;	
	private JSlider separation;
	private final int WEIGHT_MIN = 0;
	private final int WEIGHT_MAX = 100;
	private final int WEIGHT_INIT = 0;
	
	private JSlider speed;
	private final int MIN_SPEED = 0;
	private final int MAX_SPEED = 10;
	private final int INIT_SPEED = 5; 
	
	public GUI() {
		frame = new JFrame();
		canvas = new Canvas();
		lowerPanel = new JPanel();
		turtles = Collections.synchronizedList(new ArrayList<FlockingTurtle>());
		addTurtleButton = new JButton("Add new turtle!!");
		cohesion = new JSlider(JSlider.HORIZONTAL, WEIGHT_MIN, WEIGHT_MAX, WEIGHT_INIT);
		align = new JSlider(JSlider.HORIZONTAL, WEIGHT_MIN, WEIGHT_MAX, WEIGHT_INIT);
		separation = new JSlider(JSlider.HORIZONTAL, WEIGHT_MIN, WEIGHT_MAX, WEIGHT_INIT);
		speed = new JSlider(JSlider.HORIZONTAL, MIN_SPEED, MAX_SPEED, INIT_SPEED);
		
		setup_fieldVariables();
		
		gameplay = new Gameplay(turtles, canvas, obstacle);
		gameplay.gameLoop();
	}

	private void setup_fieldVariables() {
		setup_frame();
		setup_lowerPanel();
		setupObstacle();
	}

	private void setup_lowerPanel() {
		lowerPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = 0;
		c.gridy = 1;
		lowerPanel.add(cohesion, c);
		setup_cohesion();
		JLabel cohesionLabel = new JLabel("Cohesion weight%");
		cohesionLabel.setLabelFor(cohesion);
		c.gridy = 0;
		lowerPanel.add(cohesionLabel, 1);
		
		c.gridx = 1;
		c.gridy = 1;
		lowerPanel.add(align, c);
		setup_align();
		c.gridy = 0;
		JLabel alignLabel = new JLabel("Alignment weight%");
		alignLabel.setLabelFor(align);
		lowerPanel.add(alignLabel, c);
		
		c.gridx = 2;
		c.gridy = 1;
		lowerPanel.add(separation, c);
		setup_separate();
		JLabel separateLabel = new JLabel("Separation weight%");
		separateLabel.setLabelFor(separation);
		c.gridy = 0;
		lowerPanel.add(separateLabel, c);
		
		c.gridx = 0;
		c.gridy = 3;
		lowerPanel.add(addTurtleButton, c);
		setup_addTurtleButton();
		
		c.gridx = 2;
		c.gridy = 3;
		lowerPanel.add(speed, c);
		setup_turtleSpeed();
		c.gridy = 2;
		JLabel speedLabel = new JLabel("Speed");
		speedLabel.setLabelFor(speed);
		lowerPanel.add(speedLabel, c);
	}
	
	private void setup_addTurtleButton() {
		addTurtleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				synchronized(turtles) {
					FlockingTurtle turtle = spawnNewTurtleAtRandomPlace();
					turtles.add(turtle);
				}
			}
		});
	}
	
	private void setup_turtleSpeed() {
		speed.setMajorTickSpacing(5);
		speed.setPaintLabels(true);
		speed.setMinorTickSpacing(1);
		speed.setPaintTicks(true);
		speed.addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent bc) {
				synchronized (turtles) {
					for (FlockingTurtle turtle : turtles) {
						turtle.setSpeed(speed.getValue());
					}
				}
			}
		});
	}
	
	private void setup_cohesion() {
		cohesion.setMajorTickSpacing(50);
		cohesion.setPaintLabels(true);
		cohesion.setMinorTickSpacing(10);
		cohesion.setPaintTicks(true);
		cohesion.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent bc) {
				double cohesionWeight = cohesion.getValue() / 100;
				gameplay.setCohesion(cohesionWeight); 
			}
		});
	}
	
	private void setup_align() {
		align.setMajorTickSpacing(50);
		align.setPaintLabels(true);
		align.setMinorTickSpacing(10);
		align.setPaintTicks(true);
		align.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent bc) {
				double alignWeight = align.getValue() / 100;
				gameplay.setAlign(alignWeight);
			}
		});
	}
	
	private void setup_separate() {
		separation.setMajorTickSpacing(50);
		separation.setPaintLabels(true);
		separation.setMinorTickSpacing(10);
		separation.setPaintTicks(true);
		separation.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent bc) {
				double separateWeight = separation.getValue() / 100;
				gameplay.setSeparate(separateWeight);
			}
		});
	}
	
	private FlockingTurtle spawnNewTurtleAtRandomPlace() {
		double xCoord = (double)(Math.random()*(800 - 600) + 600);
		double yCoord = (double)(Math.random()*(800 - 100) + 100);
		FlockingTurtle turtle = new FlockingTurtle(canvas, xCoord, yCoord, turtles);
		turtle.setAlign((double)align.getValue() / 100);
		turtle.setCohesion((double)cohesion.getValue() / 100);
		turtle.setSeparate((double)separation.getValue() / 100);
		turtle.setSpeed(speed.getValue()); // To make sure new turtle has the same speed as existing turtle
		return turtle;
	}

	private void setup_frame() {
		frame.setTitle("Flock simulation");
		frame.setSize(WINDOW_X_SIZE, WINDOW_Y_SIZE); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(lowerPanel, BorderLayout.SOUTH);
		frame.add(canvas, BorderLayout.CENTER);
	}

	private void setupObstacle() {
		CartesianCoordinate topLeftCorner = new CartesianCoordinate(250, 150);
		double width = 200;
		double height = 100;
		obstacle = new Obstacle(topLeftCorner, width, height, canvas);
		obstacle.draw();
	}

	public static void main(String[] args) {
		new GUI();
	}

}
