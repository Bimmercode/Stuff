package useful;

import javax.swing.JLabel;
import javax.swing.JSeparator;

public class Animations {
	
	public void startSeparatorAnim(JSeparator s){
		new Thread(new Runnable() {@Override public void run() {
			
			//SAVE START VALUES
			int length = s.getWidth();
			int millis = 2;
			
			//MAKE SEPERATOR NOT VISIBLE
			s.setSize(0, s.getHeight());
			
			for(int i = 0; i<length; i++){
				
				//"ANIMATION" - INCREASE WIDTH
				s.setSize(i, s.getHeight());
				
				//SLEEP LONGER IN THE END - LOOKS SMOOTH
				if (i > length - 15) {millis++;}
				
				//SLEEP, OTHERWISE TOO FAST
				try {Thread.sleep(millis);} catch (InterruptedException e) {e.printStackTrace();}
				
			}
			
		}}).start();
	}
	
	public void startLabelAnim(JLabel l){
		new Thread(new Runnable() {@Override public void run() {
			
			//MOVE DISTANCE
			int moveDistance = 50;
			
			//SAVE START VALUES
			int endPoint = l.getX();
			int beginPoint = l.getX() - moveDistance;
			int millis = 2;
			
			//MAKE LABEL NOT VISIBLE
			l.setLocation(l.getX() - moveDistance, l.getY());
			
			for(int i = beginPoint; i < endPoint + 1; i++){
				
				//"ANIMATION" - INCREASE X
				l.setLocation(i, l.getY());
				
				//SLEEP LONGER IN THE END - LOOKS SMOOTH
				if (i > moveDistance / 2) {millis++;}
				
				try {Thread.sleep(millis);} catch (InterruptedException e) {e.printStackTrace();}
				
			}
			
		}}).start();
	}

}
