package de.baeckerit.swt.util.examples.builder.grid.views;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.ui.PlatformUI;

import de.baeckerit.swt.builder.grid.BuilderCommon;
import de.baeckerit.swt.builder.listener.RadioBehaviourSelectionListener;
import de.baeckerit.swt.util.examples.ExampleView;


public class SampleForm extends ExampleView {

    private static final String LINK_TEXT = "The <a href=\"http://www.easyswt.de\">homepage</a> of EasySWT";
    private static final String[] ITEMS = new String[] {"The", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"};

    @Override
    public void createPartControl(Composite parent) {
        BuilderCommon mainBuilder = new BuilderCommon(parent, 2);
        BuilderCommon leftColumn = mainBuilder.fillGrabV().createComposite();

        Image image = parent.getDisplay().getSystemImage(SWT.ICON_QUESTION);
        BuilderCommon expandBarBuilder = leftColumn.fillGrabV().widthHint(200).addStyle(SWT.V_SCROLL).createExpandBar();
        BuilderCommon expandable = expandBarBuilder.createComposite();
        expandable.addStyle(SWT.CALENDAR|SWT.LONG).createDateTime();
        expandBarBuilder.createExpandItem(expandable, "Calendar").setImage(image);
        
        expandBarBuilder.createExpandItem(createTimeControls(expandBarBuilder), "Time Controls").setImage(image);
        expandBarBuilder.createExpandItem(expandBarBuilder.addStyle(SWT.SHORT).createDateTime(), "Date Controls").setImage(image);
        expandBarBuilder.createExpandItem(createLists(expandBarBuilder), "Lists").setImage(image);

        BuilderCommon tabFolder = expandBarBuilder.createTabFolder();
        tabFolder.createTabItem(createLists(tabFolder), "Lists");
        tabFolder.createTabItem(createTimeControls(tabFolder), "DateTimes");
        expandBarBuilder.createExpandItem(tabFolder, "Tab Folder").setImage(image);
        
        BuilderCommon rightColumn = mainBuilder.fillGrab().createComposite(2);
        rightColumn.withLabel("First Name:").createText();
        rightColumn.withLabel("Last Name:").createText();
        rightColumn.withLabel("Phone:").createText();
        
        BuilderCommon buttons = rightColumn.spanAll().createRow(2);
        buttons.createPushButton("Open Dialog...", this, "sayHello");
        buttons.createPushButton("Close View", this, "closeView");
        
        BuilderCommon dateControls = rightColumn.spanAll().createRow(4);
        dateControls.addStyle(SWT.SHORT).createDateTime();
        dateControls.addStyle(SWT.MEDIUM).createDateTime(new SelectionAdapter() {});
        dateControls.addStyle(SWT.LONG).createDateTime(this, "printDateTime");
        dateControls.addStyle(SWT.LONG|SWT.DROP_DOWN).createDateTime(this, "printDateTime");
        
        createTimeControls(rightColumn.spanAll());
        
        BuilderCommon calendarControls = rightColumn.spanAll().createGroup(3, "Calendars");
        calendarControls.addStyle(SWT.CALENDAR|SWT.LONG).createDateTime();
        calendarControls.addStyle(SWT.CALENDAR|SWT.SHORT).createDateTime(new SelectionAdapter() {});
        calendarControls.addStyle(SWT.CALENDAR|SWT.MEDIUM).createDateTime(this, "printDateTime");
        
        BuilderCommon combos = rightColumn.spanAll().createRow(3);
        combos.createCombo().setItems(ITEMS);
        combos.createCombo(new SelectionAdapter() {}).setItems(ITEMS);
        combos.createCombo(this, "printComboItem").setItems(ITEMS);

        BuilderCommon links = rightColumn.spanAll().createRow(3);
        links.createLink(LINK_TEXT);
        links.createLink(LINK_TEXT, new SelectionAdapter() {});
        links.createLink(LINK_TEXT, this, "printLinkText");
        
        BuilderCommon arrowButtons = rightColumn.spanAll().createRow(4);
        arrowButtons.createArrowButtonRight();
        arrowButtons.createArrowButtonLeft();
        arrowButtons.createArrowButtonUp();
        arrowButtons.createArrowButtonDown();
        
        BuilderCommon toggleButtons = rightColumn.spanAll().createRow(4);
        SelectionListener listener = new RadioBehaviourSelectionListener();
        toggleButtons.createToggleButton("Toggle 1", listener);
        toggleButtons.createToggleButton("Toggle 2", listener).setSelection(true);
        toggleButtons.createToggleButton("Toggle 3", listener);
        toggleButtons.createToggleButton("Toggle 4", listener);
        
        BuilderCommon radioButtons = rightColumn.spanAll().createRow(4);
        radioButtons.createRadioButton("Radio 1");
        radioButtons.createRadioButton("Radio 2").setSelection(true);
        radioButtons.createRadioButton("Radio 3");
        radioButtons.createRadioButton("Radio 4");
        
        // Demonstrates the NO_RADIO_GROUP style
        radioButtons = rightColumn.spanAll().addStyle(SWT.NO_RADIO_GROUP).createRow(4);
        listener = new RadioBehaviourSelectionListener();
        radioButtons.createRadioButton("Radio 1", listener);
        radioButtons.createRadioButton("Radio 2", listener).setSelection(true);
        radioButtons.createRadioButton("Radio 3", listener);
        radioButtons.createRadioButton("Radio 4", listener);
        
        BuilderCommon checkBoxes = rightColumn.spanAll().createRow(4);
        checkBoxes.createCheckBox("CheckBox 1");
        checkBoxes.createCheckBox("CheckBox 2").setSelection(true);
        checkBoxes.createCheckBox("CheckBox 3");
        checkBoxes.createCheckBox("CheckBox 4");
        
        checkBoxes = rightColumn.spanAll().createRow(4);
        listener = new RadioBehaviourSelectionListener();
        checkBoxes.createCheckBox("RadioBox 1", listener);
        checkBoxes.createCheckBox("RadioBox 2", listener).setSelection(true);
        checkBoxes.createCheckBox("RadioBox 3", listener);
        checkBoxes.createCheckBox("RadioBox 4", listener);
        
        BuilderCommon sticks = rightColumn.spanAll().fillGrabH().createGroup(4, "Progress, Scale, Slider, Spinner");
        ProgressBar progressBar = sticks.fillGrabH().createProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setSelection(50);
        Scale scale = sticks.fillGrabH().createScale();
        scale.setMinimum(0);
        scale.setMaximum(100);
        scale.setSelection(25);
        Slider slider = sticks.fillGrabH().createSlider();
        slider.setMinimum(0);
        slider.setMaximum(100);
        slider.setSelection(25);
        Spinner spinner = sticks.createSpinner();
        spinner.setValues(1, 0, 100, 1, 1, 10);

        BuilderCommon toolBarGroup = rightColumn.spanAll().createGroup(1, "ToolBar");
        BuilderCommon toolBarBuilder = toolBarGroup.createToolBar();
        toolBarBuilder.createToolItem(SWT.PUSH).setText("Push");
        toolBarBuilder.createToolItem(SWT.CHECK).setText("Toggle");
        toolBarBuilder.createToolItem(SWT.RADIO).setText("Radio a");
        toolBarBuilder.createToolItem(SWT.RADIO).setText("Radio b");
        toolBarBuilder.createToolItem(createCombo(toolBarBuilder));
        BuilderCommon itemBuilder = toolBarBuilder.createRow(2);
        createCombo(itemBuilder);
        createCombo(itemBuilder);
        toolBarBuilder.createToolItem(itemBuilder);
    }

    private Combo createCombo(BuilderCommon toolBarBuilder) {
        Combo combo = toolBarBuilder.addStyle(SWT.READ_ONLY).createCombo();
        combo.setItems(ITEMS);
        combo.select(0);
        return combo;
    }

    private BuilderCommon createLists(BuilderCommon parentBuilder) {
        BuilderCommon listBuilder = parentBuilder.createRow(3).addStyle(SWT.BORDER);
        listBuilder.addStyle(SWT.V_SCROLL).createList().setItems(ITEMS);
        listBuilder.createList(new SelectionAdapter() {}).setItems(ITEMS);
        listBuilder.createList(this, "printListItem").setItems(ITEMS);
        return listBuilder;
    }

    private BuilderCommon createTimeControls(BuilderCommon parentBuilder) {
        BuilderCommon timeControlBuilder = parentBuilder.createRow(1).addStyle(SWT.TIME);
        timeControlBuilder.addStyle(SWT.LONG).createDateTime();
        timeControlBuilder.addStyle(SWT.SHORT).createDateTime(new SelectionAdapter() {});
        timeControlBuilder.addStyle(SWT.MEDIUM).createDateTime(this, "printDateTime");
        return timeControlBuilder;
    }

    public void sayHello(SelectionEvent event) {
        MessageDialog.openInformation(event.display.getActiveShell(), "Hello", "Hello!");
    }
    
    public void closeView(SelectionEvent event) {
        PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().hideView(this);
    }
    
    public void printDateTime(SelectionEvent event, boolean isDefault) {
        DateTime dt = (DateTime)event.widget;
        System.out.println(dt.getYear() + "/" + dt.getMonth() + "/" + dt.getDay() + " " + dt.getHours() + ":" + dt.getMinutes() + "." + dt.getSeconds());
    }
    
    public void printComboItem(SelectionEvent event, boolean isDefault) {
        Combo combo = (Combo)event.widget;
        System.out.println("Selected: " + combo.getItem(combo.getSelectionIndex()));
    }
    
    public void printListItem(SelectionEvent event, boolean isDefault) {
        List list = (List)event.widget;
        System.out.println("Selected: " + list.getItem(list.getSelectionIndex()));
    }
    
    public void printLinkText(SelectionEvent event) {
        System.out.println(event.text);
    }
}
