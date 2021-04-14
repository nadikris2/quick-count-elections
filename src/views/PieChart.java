package views;

/*
Susilo: thanks to this website: http://blue-walrus.com/2012/09/simple-pie-chart-in-java-swing/
I can put pie chart in a JPanel
*/
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;
 
import javax.swing.JPanel;
 
public class PieChart extends JPanel {
 
	enum Type {
		STANDARD, SIMPLE_INDICATOR, GRADED_INDICATOR
	}
 
	private Type type = null; //the type of pie chart
 
        private ArrayList values;
        private ArrayList colors;
 
        private ArrayList gradingValues;
        private ArrayList gradingColors;
 
	double percent = 0; //percent is used for simple indicator and graded indicator
 
	public PieChart(int percent) {
 
		type = Type.SIMPLE_INDICATOR;
		this.percent = percent;
	}
 
	public PieChart(ArrayList values, ArrayList colors) {
 
		type = Type.STANDARD;
 
		this.values = values;
		this.colors = colors;
	}
 
	public PieChart(int percent, ArrayList gradingValues, ArrayList gradingColors) {
		type = Type.GRADED_INDICATOR;
 
		this.gradingValues = gradingValues;
		this.gradingColors = gradingColors;
		this.percent = percent;
 
	}
 
	@Override
	protected void paintComponent(Graphics g) {
 
		int width = getSize().width;
 
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, // Anti-alias!
				RenderingHints.VALUE_ANTIALIAS_ON);
 
		if (type == Type.SIMPLE_INDICATOR) {
 
			//colours used for simple indicator
			Color backgroundColor = Color.WHITE;
			Color mainColor = Color.BLUE;
 
			g2d.setColor(backgroundColor);
			g2d.fillOval(0, 0, width, width);
			g2d.setColor(mainColor);
			Double angle = (percent / 100) * 360;
			g2d.fillArc(0, 0, width, width, -270, -angle.intValue());
 
		} else if (type == Type.STANDARD) {
 
			int lastPoint = -270;
 
			for (int i = 0; i < values.size(); i++) {
				g2d.setColor((Color)colors.get(i));
 
				Double val = (Double)values.get(i);
				Double angle = (val / 100) * 360;
 
				g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
 
				lastPoint = lastPoint + -angle.intValue();
			}
		} else if (type == Type.GRADED_INDICATOR) {
 
			int lastPoint = -270;
 
			double gradingAccum = 0;
 
			for (int i = 0; i < gradingValues.size(); i++) {
                                        g2d.setColor((Color)gradingColors.get(i));
                                        Double val = (Double)gradingValues.get(i);
                                        gradingAccum = gradingAccum + val;
                                        Double angle = null;
                                        if (gradingAccum > percent) {
 
 
					//get the previous accumulated segments. Segments minus last one
					double gradingAccumMinusOneSegment = gradingAccum - val;
 
					//make an adjusted calculation of the last wedge
					angle = ((percent - gradingAccumMinusOneSegment) / 100) * 360;
 
					g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
 
					lastPoint = lastPoint + -angle.intValue();
 
					break;
 
				}else {
 
					angle = (val / 100) * 360;
 
					g2d.fillArc(0, 0, width, width, lastPoint, -angle.intValue());
 
					lastPoint = lastPoint + -angle.intValue();
				}
			}
		}
	}
}