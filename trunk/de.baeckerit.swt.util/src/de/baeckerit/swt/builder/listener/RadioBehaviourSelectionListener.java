package de.baeckerit.swt.builder.listener;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

public class RadioBehaviourSelectionListener implements SelectionListener {

    private final List<Button> buttons;

    public RadioBehaviourSelectionListener() {
        this.buttons = new ArrayList<Button>();
    }
    
    public void addButton(Button button) {
        buttons.add(button);
    }

    @Override
    public void widgetDefaultSelected(SelectionEvent e) {
        throw new RuntimeException("widgetDefaultSelected on a push button");
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        for (Button button : buttons) {
            if (button != e.widget) {
                button.setSelection(false);
            }
        }
    }
}
