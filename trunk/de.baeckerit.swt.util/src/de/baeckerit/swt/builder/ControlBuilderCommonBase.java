package de.baeckerit.swt.builder;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;

import de.baeckerit.swt.builder.grid.BuilderCommon;
import de.baeckerit.swt.builder.listener.RadioBehaviourSelectionListener;
import de.baeckerit.swt.builder.listener.ReflectiveSelectionListener;
import de.baeckerit.swt.builder.listener.ReflectiveSimpleSelectionListener;

/**
 * Basic implementation of an SWT control builder. A control builder provides an easy-to-use API for creating a hierarchy of SWT controls,
 * including layouts and layout data.
 * <p>
 * In most cases, the subclass {@link BuilderCommon} should be used to create the control hierarchy, using {@link GridLayout} and
 * {@link GridData}.
 * <p>
 * The basic control builder does not support the creation of layouts for composites, nor does it support setting layout data on children.
 * However, it is useful for creating controls that normally do not need a layout data, for example when the composite's layout is a
 * {@link RowLayout}.
 * 
 * @author Andreas Baecker
 */
public abstract class ControlBuilderCommonBase<T extends ControlBuilderCommonBase<T>> {

  public static final Color INVALID_PROPERTY_COLOR = Display.getCurrent().getSystemColor(SWT.COLOR_RED);

  /**
   * The parent composite for the controls created by this control builder.
   */
  Composite composite;
  IControlStyles defaultStyles;
  int stylesToAdd;
  int stylesToRemove;

  public ControlBuilderCommonBase() {
    this((Composite) null, true);
  }

  public ControlBuilderCommonBase(Composite parent) {
    this(parent, false);
  }

  protected ControlBuilderCommonBase(Composite parent, boolean allowNull) {
    if (!allowNull && parent == null)
      throw new NullPointerException("parent");//$NON-NLS-1$
    this.composite = parent;
    initNewPrivate();
  }

  protected ControlBuilderCommonBase(ControlBuilderCommonBase<?> other) {
    if (other == null)
      throw new NullPointerException("other");
    this.composite = other.composite;
    this.stylesToAdd = other.stylesToAdd;
    this.stylesToRemove = other.stylesToRemove;
    this.defaultStyles = other.defaultStyles;
  }

  protected void initNew() {
    initNewPrivate();
  }

  private void initNewPrivate() {
    stylesToAdd = SWT.NONE;
    stylesToRemove = SWT.NONE;
    defaultStyles = getDefaultStyles();
  }

  protected IControlStyles getDefaultStyles() {
    return ControlStyles.DEFAULT_STYLES;
  }

  public Composite getComposite() {
    return composite;
  }

  public CoolBar getCoolBar() {
    return (CoolBar) composite;
  }

  public ExpandBar getExpandBar() {
    return (ExpandBar) composite;
  }

  public Group getGroup() {
    return (Group) composite;
  }

  public Table getTable() {
    return (Table) composite;
  }

  public Tree getTree() {
    return (Tree) composite;
  }

  public Shell getShell() {
    return composite instanceof Shell ? (Shell) composite : composite.getShell();
  }

  protected abstract T copy();

  protected abstract T _this();

  public T withDefaults() {
    T builder = copy();
    builder.initNew();
    return builder;
  }

  public Layout createLayout() {
    return null;
  }

  public T withStyles(IControlStyles defaultStyles) {
    final T newBuilder = copy();
    newBuilder.defaultStyles = defaultStyles;
    return newBuilder;
  }

  public T connectTo(Composite composite) {
    final T newBuilder = copy();
    newBuilder.composite = composite;
    return newBuilder;
  }

  public Object createLayoutData() {
    return null;
  }

  protected final <C extends Control> C initializeControl(C control) {
    if (control.getParent() != null && control.getParent().getLayout() != null) {
      control.setLayoutData(createLayoutData());
    }
    return control;
  }

  protected final <C extends Item> C initializeItem(C control) {
    return control;
  }

  protected final <C extends Composite> C initializeComposite(C composite, boolean createLayout) {
    if (createLayout) {
      composite.setLayout(createLayout());
    }
    return initializeControl(composite);
  }

  public T addStyle(int style) {
    final T newBuilder = copy();
    newBuilder.stylesToAdd |= style;
    return newBuilder;
  }

  public T styleCenter() {
    return addStyle(SWT.CENTER);
  }

  public T styleRight() {
    return addStyle(SWT.RIGHT);
  }

  public T styleLeft() {
    return addStyle(SWT.LEFT);
  }

  public T removeStyle(int style) {
    final T newBuilder = copy();
    newBuilder.stylesToRemove |= style;
    return newBuilder;
  }

  public int computeStyle(int defaultStyles) {
    return (defaultStyles | stylesToAdd) & ~stylesToRemove;
  }

  public T createComposite() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getCompositeStyle());
    newBuilder.composite = initializeComposite(new Composite(composite, style), true);
    newBuilder.initNew();
    return newBuilder;
  }

  public T createCoolBar() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getCoolBarStyle());
    newBuilder.composite = initializeComposite(new CoolBar(composite, style), true);
    newBuilder.initNew();
    return newBuilder;
  }

  /**
   * Creates an TabFolder control.
   * <p>
   * No layout will be created for the tab folder.
   * 
   * @return a builder suitable for creating children of the tab folder.
   */
  public T createTabFolder() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getTabFolderStyle());
    newBuilder.composite = initializeComposite(new TabFolder(composite, style), false);
    newBuilder.initNew();
    return newBuilder;
  }

  public TabItem createTabItem(Control control, String text) {
    final int style = computeStyle(defaultStyles.getTabItemStyle());
    TabItem tabItem = new TabItem((TabFolder) composite, style);
    tabItem.setText(text);
    tabItem.setControl(control);
    return tabItem;
  }

  public TabItem createTabItem(ControlBuilderCommonBase<T> builder, String text) {
    return createTabItem(builder.getComposite(), text);
  }

  /**
   * Creates an ExpandBar control.
   * <p>
   * No layout will be created for the expand bar.
   * 
   * @return a builder suitable for creating children of the expand bar.
   */
  public T createExpandBar() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getExpandBarStyle());
    newBuilder.composite = initializeComposite(new ExpandBar(composite, style), false);
    newBuilder.initNew();
    return newBuilder;
  }

  public ExpandItem createExpandItem(Control control, String text) {
    ExpandBar bar = (ExpandBar) composite;
    final int style = computeStyle(defaultStyles.getExpandItemStyle());
    ExpandItem expandItem = new ExpandItem(bar, style);
    expandItem.setText(text);
    expandItem.setHeight(control.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
    expandItem.setControl(control);
    return expandItem;
  }

  public ExpandItem createExpandItem(ControlBuilderCommonBase<T> builder, String text) {
    return createExpandItem(builder.getComposite(), text);
  }

  public T createToolBar() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getToolBarStyle());
    newBuilder.composite = initializeComposite(new ToolBar(composite, style), false);
    newBuilder.initNew();
    return newBuilder;
  }

  public ToolItem createToolItem(int style) {
    return new ToolItem((ToolBar) composite, style | computeStyle(defaultStyles.getToolItemStyle()));
  }

  public ToolItem createToolItem(int style, int index) {
    return new ToolItem((ToolBar) composite, style | computeStyle(defaultStyles.getToolItemStyle()), index);
  }

  public ToolItem createToolItem(Control control) {
    ToolItem toolItem = new ToolItem((ToolBar) composite, SWT.SEPARATOR);
    toolItem.setControl(control);
    toolItem.setWidth(control.computeSize(SWT.DEFAULT, SWT.DEFAULT).x);
    return toolItem;
  }

  public ToolItem createToolItem(ControlBuilderCommonBase<T> builder) {
    return createToolItem(builder.getComposite());
  }

  public T createTable() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getTableStyle());
    newBuilder.composite = initializeComposite(new Table(composite, style), false);
    newBuilder.initNew();
    return newBuilder;
  }

  public TableColumn createTableColumn() {
    final int style = computeStyle(defaultStyles.getTableColumnStyle());
    return initializeItem(new TableColumn((Table) composite, style));
  }

  public TableColumn createTableColumn(String text, int width) {
    final int style = computeStyle(defaultStyles.getTableColumnStyle());
    TableColumn tc = new TableColumn((Table) composite, style);
    tc.setText(text);
    tc.setWidth(width);
    return initializeItem(tc);
  }

  public TableColumn createTableColumn(String text, int width, int alignment) {
    final int style = computeStyle(defaultStyles.getTableColumnStyle());
    TableColumn tc = new TableColumn((Table) composite, style);
    tc.setText(text);
    tc.setWidth(width);
    tc.setAlignment(alignment);
    return initializeItem(tc);
  }

  public TableColumn createTableColumn(int index) {
    final int style = computeStyle(defaultStyles.getTableColumnStyle());
    return initializeItem(new TableColumn((Table) composite, style, index));
  }

  public T createTree() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getTreeStyle());
    newBuilder.composite = initializeComposite(new Tree(composite, style), false);
    newBuilder.initNew();
    return newBuilder;
  }

  public T createGroup() {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getGroupStyle());
    newBuilder.composite = initializeComposite(new Group(composite, style), true);
    newBuilder.initNew();
    return newBuilder;
  }

  public T createGroup(String titleText) {
    T builder = createGroup();
    builder.getGroup().setText(titleText);
    return builder;
  }

  public T createShell(Display display) {
    final T newBuilder = copy();
    final int style = computeStyle(defaultStyles.getShellStyle());
    newBuilder.composite = initializeComposite(new Shell(display, style), true);
    newBuilder.initNew();
    return newBuilder;
  }

  public Text createText() {
    final int style = computeStyle(defaultStyles.getTextStyle());
    return initializeControl(new Text(composite, style));
  }

  public Text createTextArea() {
    final int style = computeStyle(defaultStyles.getTextAreaStyle());
    return initializeControl(new Text(composite, style));
  }

  public Label createLabel() {
    final int style = computeStyle(defaultStyles.getLabelStyle());
    return initializeControl(new Label(composite, style));
  }

  public Label createLabel(String text) {
    Label label = createLabel();
    label.setText(text);
    return label;
  }

  public T withLabel(String text) {
    withDefaults().createLabel(text);
    return _this();
  }

  public Link createLink() {
    final int style = computeStyle(defaultStyles.getLinkStyle());
    return initializeControl(new Link(composite, style));
  }

  public Link createLink(String text) {
    final int style = computeStyle(defaultStyles.getLinkStyle());
    final Link link = initializeControl(new Link(composite, style));
    link.setText(text);
    return link;
  }

  public Link createLink(String text, SelectionListener listener) {
    final Link link = createLink(text);
    link.addSelectionListener(listener);
    return link;
  }

  public Link createLink(String text, Object target, String methodName) {
    final Link link = createLink(text);
    link.addSelectionListener(new ReflectiveSimpleSelectionListener(target, methodName));
    return link;
  }

  public Button createArrowButtonRight() {
    final int style = computeStyle(SWT.RIGHT | defaultStyles.getArrowButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createArrowButtonLeft() {
    final int style = computeStyle(SWT.LEFT | defaultStyles.getArrowButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createArrowButtonUp() {
    final int style = computeStyle(SWT.UP | defaultStyles.getArrowButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createArrowButtonDown() {
    final int style = computeStyle(SWT.DOWN | defaultStyles.getArrowButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createPushButton() {
    final int style = computeStyle(defaultStyles.getPushButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createPushButton(String text) {
    final int style = computeStyle(defaultStyles.getPushButtonStyle());
    final Button button = initializeControl(new Button(composite, style));
    button.setText(text);
    return button;
  }

  public Button createPushButton(String text, Object target, String methodName) {
    final Button button = createPushButton(text);
    button.addSelectionListener(new ReflectiveSimpleSelectionListener(target, methodName));
    return button;
  }

  public Button createPushButton(String text, SelectionListener listener) {
    final Button button = createPushButton(text);
    button.addSelectionListener(listener);
    return button;
  }

  public Button createToggleButton() {
    final int style = computeStyle(defaultStyles.getToggleButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createToggleButton(String text) {
    final int style = computeStyle(defaultStyles.getToggleButtonStyle());
    final Button button = initializeControl(new Button(composite, style));
    button.setText(text);
    return button;
  }

  public Button createToggleButton(String text, Object target, String methodName) {
    final Button button = createToggleButton(text);
    button.addSelectionListener(new ReflectiveSimpleSelectionListener(target, methodName));
    return button;
  }

  public Button createToggleButton(String text, SelectionListener listener) {
    final Button button = createToggleButton(text);
    button.addSelectionListener(listener);
    if (listener instanceof RadioBehaviourSelectionListener) {
      ((RadioBehaviourSelectionListener) listener).addButton(button);
    }
    return button;
  }

  public Button createRadioButton() {
    final int style = computeStyle(defaultStyles.getRadioButtonStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createRadioButton(String text) {
    final int style = computeStyle(defaultStyles.getRadioButtonStyle());
    final Button button = initializeControl(new Button(composite, style));
    button.setText(text);
    return button;
  }

  public Button createRadioButton(String text, Object target, String methodName) {
    final Button button = createRadioButton(text);
    button.addSelectionListener(new ReflectiveSimpleSelectionListener(target, methodName));
    return button;
  }

  public Button createRadioButton(String text, SelectionListener listener) {
    final Button button = createRadioButton(text);
    button.addSelectionListener(listener);
    if (listener instanceof RadioBehaviourSelectionListener) {
      ((RadioBehaviourSelectionListener) listener).addButton(button);
    }
    return button;
  }

  public Button createCheckBox() {
    final int style = computeStyle(defaultStyles.getCheckBoxStyle());
    return initializeControl(new Button(composite, style));
  }

  public Button createCheckBox(String text) {
    final int style = computeStyle(defaultStyles.getCheckBoxStyle());
    final Button button = initializeControl(new Button(composite, style));
    button.setText(text);
    return button;
  }

  public Button createCheckBox(String text, Object target, String methodName) {
    final Button button = createCheckBox(text);
    button.addSelectionListener(new ReflectiveSimpleSelectionListener(target, methodName));
    return button;
  }

  public Button createCheckBox(String text, SelectionListener listener) {
    final Button button = createCheckBox(text);
    button.addSelectionListener(listener);
    if (listener instanceof RadioBehaviourSelectionListener) {
      ((RadioBehaviourSelectionListener) listener).addButton(button);
    }
    return button;
  }

  public DateTime createDateTime() {
    final int style = computeStyle(defaultStyles.getDateTimeStyle());
    return initializeControl(new DateTime(composite, style));
  }

  public DateTime createDateTime(SelectionListener listener) {
    final DateTime dateTime = createDateTime();
    dateTime.addSelectionListener(listener);
    return dateTime;
  }

  public DateTime createDateTime(Object target, String methodName) {
    final DateTime dateTime = createDateTime();
    dateTime.addSelectionListener(new ReflectiveSelectionListener(target, methodName));
    return dateTime;
  }

  public Combo createCombo() {
    final int style = computeStyle(defaultStyles.getComboStyle());
    return initializeControl(new Combo(composite, style));
  }

  public Combo createCombo(SelectionListener listener) {
    final Combo combo = createCombo();
    combo.addSelectionListener(listener);
    return combo;
  }

  public Combo createCombo(Object target, String methodName) {
    final Combo combo = createCombo();
    combo.addSelectionListener(new ReflectiveSelectionListener(target, methodName));
    return combo;
  }

  public List createList() {
    final int style = computeStyle(defaultStyles.getListStyle());
    return initializeControl(new List(composite, style));
  }

  public List createList(SelectionListener listener) {
    final List list = createList();
    list.addSelectionListener(listener);
    return list;
  }

  public List createList(Object target, String methodName) {
    final List list = createList();
    list.addSelectionListener(new ReflectiveSelectionListener(target, methodName));
    return list;
  }

  public Canvas createCanvas() {
    final int style = computeStyle(defaultStyles.getCanvasStyle());
    return initializeControl(new Canvas(composite, style));
  }

  public ProgressBar createProgressBar() {
    final int style = computeStyle(defaultStyles.getProgressBarStyle());
    return initializeControl(new ProgressBar(composite, style));
  }

  public Scale createScale() {
    final int style = computeStyle(defaultStyles.getScaleStyle());
    return initializeControl(new Scale(composite, style));
  }

  public Slider createSlider() {
    final int style = computeStyle(defaultStyles.getSliderStyle());
    return initializeControl(new Slider(composite, style));
  }

  public Spinner createSpinner() {
    final int style = computeStyle(defaultStyles.getSpinnerStyle());
    return initializeControl(new Spinner(composite, style));
  }
}
