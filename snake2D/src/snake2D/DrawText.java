package snake2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class DrawText {
	Graphics g;
	public static void main(String[] args){
		
	}
	public DrawText(){
		g.setColor(Color.black);
		g.setFont(new Font("arial",Font.ITALIC,20));
		g.drawString("Hello",260,300);
	}
	public void Paint(Graphics g){
		
	}
}
