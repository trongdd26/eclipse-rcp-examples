/*******************************************************************************
 * Copyright (c) 2000, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.swt.snippets;

/*
 * Tool Tips example snippet: create tool tips for a tab item, tool item, and shell
 *
 * For a list of all SWT example snippets see
 * http://www.eclipse.org/swt/snippets/
 */
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackListener;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Snippet4141 {

  private static final class ButtonToolTipSupport implements MouseTrackListener, MouseMoveListener {
    private final Composite composite;
    private final Button button;

    private ButtonToolTipSupport(Composite composite, Button secondButton) {
      this.composite = composite;
      this.button = secondButton;
    }

    @Override
    public void mouseHover(MouseEvent e) {
      System.err.println("Hover");
      if (!button.isEnabled()) {
        Rectangle bounds = button.getBounds();
        if (bounds.contains(e.x, e.y)) {
          String ttt = button.getToolTipText();
          if (ttt != null && !ttt.equals(composite.getToolTipText())) {
            System.err.println("Set TT: " + ttt);
            composite.setToolTipText(ttt);
          }
        }
      }
    }

    @Override
    public void mouseExit(MouseEvent e) {
      System.err.println("Exit");
    }

    @Override
    public void mouseEnter(MouseEvent e) {
      System.err.println("Enter");
    }

    @Override
    public void mouseMove(MouseEvent e) {
      Rectangle bounds = button.getBounds();
      if (!bounds.contains(e.x, e.y)) {
        String ttt = composite.getToolTipText();
        if (ttt != null && ttt.equals(button.getToolTipText())) {
          System.err.println("Clear TT: " + ttt);
          composite.setToolTipText("");
        }
      }
    }
  }

  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    FillLayout layout = new FillLayout();
    layout.spacing = 25;
    layout.marginHeight = 25;
    layout.marginWidth = 25;
    shell.setLayout(layout);
    shell.setToolTipText("");

    Button firstButton = new Button(shell, SWT.PUSH);
    firstButton.setText("First");
    firstButton.setToolTipText("Tooltip of first button");

    Button secondButton = new Button(shell, SWT.PUSH);
    secondButton.setText("Second");
    secondButton.setToolTipText("Tooltip of second button");
    secondButton.setEnabled(false);

    Button thirdButton = new Button(shell, SWT.PUSH);
    thirdButton.setText("Third");
    thirdButton.setToolTipText("Tooltip of third button");
    thirdButton.setEnabled(false);

    ButtonToolTipSupport toolTipListener = new ButtonToolTipSupport(shell, secondButton);
    shell.addMouseTrackListener(toolTipListener);
    shell.addMouseMoveListener(toolTipListener);

    toolTipListener = new ButtonToolTipSupport(shell, thirdButton);
    shell.addMouseTrackListener(toolTipListener);
    shell.addMouseMoveListener(toolTipListener);

    shell.pack();
    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
