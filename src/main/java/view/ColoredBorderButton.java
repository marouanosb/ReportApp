package view;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;


//customised button class to allow adding a bottom border color when clicked
public class ColoredBorderButton extends JButton {

	    public ColoredBorderButton(String text) {
	        super(text);
	    }

	    public void addBottomBorder(Color color, int size) {
	    	setBorder(new BottomBorder(color, size)); // Set the color and thickness of the bottom border
	    }
	    public void removeBottomBorder() {
	    	setBorder(null);
	    }
	    private static class BottomBorder implements Border {
	        private final Color color;
	        private final int thickness;

	        public BottomBorder(Color color, int thickness) {
	            this.color = color;
	            this.thickness = thickness;
	        }

	        @Override
	        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	            g.setColor(color);
	            g.fillRect(x, y + height - thickness, width, thickness);
	        }

	        @Override
	        public Insets getBorderInsets(Component c) {
	            return new Insets(0, 0, thickness, 0);
	        }

	        @Override
	        public boolean isBorderOpaque() {
	            return true;
	        }
	    }

}
