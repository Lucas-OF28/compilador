package Views;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.text.AbstractDocument;
import javax.swing.text.Element;
import javax.swing.text.ParagraphView;
import javax.swing.text.StyledEditorKit;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;

public class TextEditField extends JTextPane{{
    super.setEditorKit(new CustomEditorKit());
    super.setText("");
}

class CustomEditorKit extends StyledEditorKit {

    private static final long serialVersionUID = 1L;

    @Override
    public ViewFactory getViewFactory() {
      return new CustomViewFactory(super.getViewFactory());
    }
}

class CustomViewFactory implements ViewFactory {

    private ViewFactory defaultViewFactory;

    CustomViewFactory(ViewFactory defaultViewFactory) {
      this.defaultViewFactory = defaultViewFactory;
    }

    @Override
    public View create(Element elem) {
      if (elem != null && elem.getName().equals(AbstractDocument.ParagraphElementName)) {
        return new CustomParagraphView(elem);
      }
      return defaultViewFactory.create(elem);
    }
}
  
class CustomParagraphView extends ParagraphView {

    public final short MARGIN_WIDTH_PX = 30;

    private Element thisElement;

    private Font font;

    public CustomParagraphView(Element elem) {
      super(elem);
      thisElement = elem;
      this.setInsets((short) 0, (short) 0, (short) 0, (short) 0);
    }

    @Override
    protected void setInsets(short top, short left, short bottom, short right) {
      super.setInsets(top, (short) (left + MARGIN_WIDTH_PX), bottom, right);
    }

    @Override
    public void paintChild(Graphics g, Rectangle alloc, int index) {
      super.paintChild(g, alloc, index);
      
      g.setColor(Color.GRAY);

      if (index > 0) {
        return;
      }

      int lineNumber = getLineNumber() + 1;
      String lnStr = String.format("%3d", lineNumber);

      font = font != null ? font : new Font(Font.MONOSPACED, Font.PLAIN, getFont().getSize());
      g.setFont(font);

      int x = alloc.x - getLeftInset();
      int y = alloc.y + alloc.height - 3;
      g.drawString(lnStr, x, y);
    }

    private int getLineNumber() {
      Element root = getDocument().getDefaultRootElement();
      int len = root.getElementCount();
      for (int i = 0; i < len; i++) {
        if (root.getElement(i) == thisElement) {
          return i;
        }
      }
      return 0;
    }
}
}