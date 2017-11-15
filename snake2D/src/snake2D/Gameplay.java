package snake2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


import sun.audio.AudioPlayer;
import sun.audio.ContinuousAudioDataStream;

public class Gameplay extends JPanel implements KeyListener ,ActionListener {
	
	private int[] snakeXLength=new int[1000];
	private int[] snakeYLength=new int[1000];
	
	private int[] ballXArray=new int[100];
	private int[] ballYArray=new int[100];
	
	private int point=0;
	private int speed=30;
	
	private int levelCounter=1;
	
	private int snakeLength=3;
	private int snakePosition=0;
	
	private boolean up=false;
	private boolean down=false;
	private boolean right=false;
	private boolean left=false;
	
	private ImageIcon northMouth;
	private ImageIcon southMouth;
	private ImageIcon eastMouth;
	private ImageIcon westMouth;
	private ImageIcon body;
	private ImageIcon ballImage;
	private Timer snakeSpeed;
	private int interval=160;
	private int canvas_1=1;
	
	private ImageIcon gameTitle;
	
	private Random random=new Random();
	private int ballXPos;
	private int ballYPos;
	
	//private BackGroundMusic music=new BackGroundMusic();
	
	public Gameplay(){
		addKeyListener(this);
		
		this.setFocusable(true);
		
		
		snakeSpeed=new Timer(interval,this);
		
		snakeSpeed.start();
		
		
		int l=0;
		int m=0;
		for(int i=25;i<=850;i=i+25){
			ballXArray[l]=i;
			l++;
		}
		
		
		for(int i=75;i<=625;i=i+25){
			ballYArray[m]=i;
			m++;
		}
		
		ballXPos=random.nextInt(34);
		ballYPos=random.nextInt(23);
		
		
		
		
	}
	
	public void paint(Graphics g){
		
		System.out.println("Null");
		//snake initial position
		if(snakePosition==0){
			
			snakeXLength[2]=50;
			snakeXLength[1]=75;
			snakeXLength[0]=100;
			
			
			
		
			snakeYLength[2]=100;
			snakeYLength[1]=100;
			snakeYLength[0]=100;
		}
		//draw game title
		gameTitle=new ImageIcon("snaketitle.jpg");
		gameTitle.paintIcon(this,g,25,11);
		
		//draw game area
		
		g.setColor(Color.BLACK);
		g.fillRect(25,75,850,575);
		//draw point
		
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Game point : "+point,700,30);
		//DRAW Snake LENGTH....
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Snake Length: "+snakeLength,700,50);
		//DRAW LEVEL
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Level : "+levelCounter,50,30);
		//DRAW SNAKE SPEED
		g.setColor(Color.RED);
		g.setFont(new Font("arial",Font.BOLD,20));
		g.drawString("Speed: "+speed+"x",50,50);
		//draw Snake
		if(canvas_1==1){
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.ITALIC,30));
			g.drawString("Please Enter to start game",270,300);
			//BackGroundMusic.startingMusic();
			//g.drawString("Use 'Right Arrow Key','Left Arrow Key','Down Arrow Key' and 'Up Arrow Key' to move your snake", arg1, arg2);
		}
		
		if(canvas_1==2){
			eastMouth=new ImageIcon("rightmouth.png");
			eastMouth.paintIcon(this,g,snakeXLength[0],snakeYLength[0]);
			
			for(int i=0;i<snakeLength;i++){
				if(i==0&& right){
					eastMouth=new ImageIcon("rightmouth.png");
					eastMouth.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
				}
				if(i==0&& left){
					westMouth=new ImageIcon("leftmouth.png");
					westMouth.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
				}
				if(i==0&&down){
					southMouth=new ImageIcon("downmouth.png");
					southMouth.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
				}
				if(i==0 && up){
					northMouth=new ImageIcon("upmouth.png");
					northMouth.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
				}
				
				if(i!=0){
					body=new ImageIcon("snakeimage.png");
					body.paintIcon(this,g,snakeXLength[i],snakeYLength[i]);
				}
			}
			
		}
		ballImage=new ImageIcon("enemy.png");
		//pick up ball....&&....increase Point....INCREASE...SNAKE BODY....
		if(snakeXLength[0]==ballXArray[ballXPos]&&snakeYLength[0]==ballYArray[ballYPos]){
			
			BackGroundMusic.ballMusic();
			if(snakeLength==10&&levelCounter==1){
				speed=speed+10;
				levelCounter++;
				interval=interval/2;
				snakeSpeed.setDelay(interval);
			}
			if(snakeLength==15&&levelCounter==2){
				
				speed=speed+10;
				levelCounter++;
				interval=interval/2;
				snakeSpeed.setDelay(interval);
			}
			
			point+=5;
			ballXPos=random.nextInt(34);
			ballYPos=random.nextInt(23);
			snakeLength++;
		}
		
		//CREATE BALL IMAGE
		if(canvas_1==2){
			ballImage.paintIcon(this,g,ballXArray[ballXPos],ballYArray[ballYPos]);
		}
		
		
		for(int i=1;i<snakeLength;i++){
			if(snakeYLength[0]==snakeYLength[i]&&snakeXLength[0]==snakeXLength[i]){
				right=false;
				left=false;
				down=false;
				up=false;
				canvas_1=1;
				snakeSpeed.setDelay(160);
			BackGroundMusic.snakeMusic();	
			g.setColor(Color.RED);
			g.setFont(new Font("arial",Font.BOLD,30));
			g.drawString("!!!COLLIiSON!!! YOU BITE Y'RSELF",230,300);
			
			g.drawString("Press 'SPACE BAR' To Restart",250,350);
				
			}
		}
		
		
	}
	
	
	public void actionPerformed(ActionEvent arg0) {
		
		snakeSpeed.start();
		System.out.println("Snake movement start");
		if(right){
			for(int i=snakeLength-1;i>=0;i--){
				snakeYLength[i+1]=snakeYLength[i];
			}
			for(int i=snakeLength;i>=0;i--){
				if(i==0){
					snakeXLength[i]=snakeXLength[i]+25;
				}
				else{
					snakeXLength[i]=snakeXLength[i-1];
				}
				if(snakeXLength[i]>850){
					snakeXLength[i]=25;
				}
			}
			repaint();
		}
		
		
		if(left){
			for(int i=snakeLength-1;i>=0;i--){
				snakeYLength[i+1]=snakeYLength[i];
			}
			for(int i=snakeLength;i>=0;i--){
				if(i==0){
					snakeXLength[i]=snakeXLength[i]-25;
				}
				else{
					snakeXLength[i]=snakeXLength[i-1];
				}
				if(snakeXLength[i]<25){
					snakeXLength[i]=850;
				}
			}
			repaint();
		}
		
		if(up){
			for(int i=snakeLength-1;i>=0;i--){
				snakeXLength[i+1]=snakeXLength[i];
			}
			for(int i=snakeLength;i>=0;i--){
				if(i==0){
					snakeYLength[i]=snakeYLength[i]-25;
				}
				else{
					snakeYLength[i]=snakeYLength[i-1];
				}
				if(snakeYLength[i]<75){
					snakeYLength[i]=625;
				}
			}
			repaint();
		}
		if(down){
			for(int i=snakeLength-1;i>=0;i--){
				snakeXLength[i+1]=snakeXLength[i];
			}
			for(int i=snakeLength;i>=0;i--){
				if(i==0){
					snakeYLength[i]=snakeYLength[i]+25;
				}
				else{
					snakeYLength[i]=snakeYLength[i-1];
				}
				if(snakeYLength[i]>625){
					snakeYLength[i]=75;
				}
			}
			
			
			repaint();
		}
	}

	public void keyPressed(KeyEvent e) {
	
		if(e.getKeyCode()==KeyEvent.VK_ENTER){
			
				
					if(canvas_1==1)
					{
						canvas_1=2;
						repaint();
					}
					else{
						canvas_1=1;
						repaint();
					}
				
				
			
			
			
		}
		if(e.getKeyCode()== KeyEvent.VK_SPACE)
		{
			
			snakePosition=0;
			point=0;
			levelCounter=1;
			speed=30;
			snakeSpeed.setDelay(160);
			snakeLength=3;
			repaint();
		}
		if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			System.out.println("Key pressed");
			snakePosition++;
			right=true;
			if(left==false){
				right=true;
			}
			else{
				right=false;
				left=true;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT){
			snakePosition++;
			left=true;
			if(right==false){
				left=true;
			}
			else{
				right=true;
				left=false;
			}
			up=false;
			down=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_UP){
			snakePosition++;
			up=true;
			if(down==false){
				up=true;
			}
			else{
				up=false;
				down=true;
			}
			right=false;
			left=false;
		}
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			Main m=new Main();
			
			snakePosition++;
			down=true;
			if(up==false){
				down=true;
			}
			else{
				down=false;
				up=true;
			}
			right=false;
			left=false;
		}
		
		
	}

	public void keyReleased(KeyEvent arg0) {
		
		
	}

	public void keyTyped(KeyEvent arg0) {
		
		
	}









}

 