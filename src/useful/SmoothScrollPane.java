package useful;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

/**
 * 
 * @author GürayPC
 * Diese Klasse ist komplett selbst geschrieben und stammt aus meinem privaten Workspace
 *
 * Anleitung: scrollPane.addMouseWheelListener(new SmoothScrollPane(scrollPane));
 *
 */

public class SmoothScrollPane implements MouseWheelListener {

	private volatile int ValToScroll = 0;
	private volatile boolean started = false;
	private volatile int mouseRoll = 1;

	private JScrollPane sp;

	public SmoothScrollPane(JScrollPane sp) {
		this.sp = sp;
		this.sp.setWheelScrollingEnabled(false);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {

		if (mouseRoll != e.getWheelRotation()) {
			mouseRoll = e.getWheelRotation();
			ValToScroll = 0;
		}
		
		/** INCREASE OR DECREASE EVERYTIME MOUSE ROLLS **/

		ValToScroll += (mouseRoll * 50);

		if (started) {
			return;
		}

		new Thread(new Runnable() { @Override public void run() {
			
				started = true;

				while (ValToScroll != 0) {

					/** PICK AND SET SCROLL INCREASE/DECREASE BY MOUSEWHEEL **/
					
					SwingUtilities.invokeLater(new Runnable() {
						@Override
						public void run() {
							
							sp.getVerticalScrollBar()
							.setValue(sp.getVerticalScrollBar().getValue()
									+ (ValToScroll > 500 || ValToScroll < -500 ? mouseRoll * 14
									: (ValToScroll > 300 || ValToScroll < -300 ? mouseRoll * 12
									: (ValToScroll > 200 || ValToScroll < -200 ? mouseRoll * 10
									: (ValToScroll > 150 || ValToScroll < -150 ? mouseRoll * 8
									: (ValToScroll > 100 || ValToScroll < -100 ? mouseRoll * 6 
									: (ValToScroll > 80 || ValToScroll < -80 ? mouseRoll * 5
									: (ValToScroll > 60 || ValToScroll < -60 ? mouseRoll * 4
									: (ValToScroll > 40 || ValToScroll < -40 ? mouseRoll * 3
									: (ValToScroll > 20 || ValToScroll < -20 ? mouseRoll * 2
									: mouseRoll))))))))));
						}
					});

					/** NOT ADD/SUB FACTORIZED MOUSEROLL FOR VELOCITY EFFECT **/

					ValToScroll -= mouseRoll;

					/** STOP SCROLLING AT MIN OR MAX **/
					
					if(mouseRoll==1){
						if(sp.getVerticalScrollBar().getMaximum() - 413 == sp.getVerticalScrollBar().getValue()){
							ValToScroll = 0;started = false;return;
							
						}
					}else{
						if(sp.getVerticalScrollBar().getMinimum() == sp.getVerticalScrollBar().getValue()){
							ValToScroll = 0;started = false;return;
						}
					}
					
					/** FRAMERATE AND CALCULATING SPEED **/

					try {Thread.sleep(4);} 
					catch (InterruptedException e1) {e1.printStackTrace();}
				}

				started = false;
				
		}}).start();

	}

}
