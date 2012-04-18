package de.baeckerit.swt.builder.grid;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import de.baeckerit.swt.builder.ControlBuilderCommonBase;

public abstract class BuilderCommonBase<T extends BuilderCommonBase<T>> extends ControlBuilderCommonBase<T> {

  GridLayout layout;
  GridData data;

  public BuilderCommonBase() {
    super((Composite) null, true);
    initNewPrivate();
  }

  public BuilderCommonBase(Composite parent) {
    super(parent, false);
    initNewPrivate();
    if (!(getComposite().getLayout() instanceof GridLayout)) {
      getComposite().setLayout(new GridLayout());
    }
  }

  public BuilderCommonBase(Composite parent, int numColumns) {
    this(parent);
    ((GridLayout) (getComposite().getLayout())).numColumns = numColumns;
  }

  protected BuilderCommonBase(BuilderCommonBase<T> other) {
    super(other);
    this.data = other.data;
    this.layout = other.layout;
  }

  public T createComposite(int numColumns) {
    return numColumns(numColumns).createComposite();
  }

  public T createGroup(int numColumns) {
    return numColumns(numColumns).createGroup();
  }

  public T createGroup(int numColumns, String titleText) {
    return numColumns(numColumns).createGroup(titleText);
  }

  public T createRow() {
    return noMargins().createComposite();
  }

  public T createRow(int numColumns) {
    return numColumns(numColumns).createRow();
  }

  public T createGrabbingRow() {
    return fillGrabH().createRow();
  }

  public T createGrabbingRow(int numColumns) {
    return numColumns(numColumns).createGrabbingRow();
  }

  @Override
  public GridLayout createLayout() {
    return deepCopyOfLayout();
  }

  @Override
  public T connectTo(Composite composite) {
    T newBuilder = super.connectTo(composite);
    // This must not set a layout data, so we can't use
    // initializeComposite() or initializeControl()
    newBuilder.getComposite().setLayout(createLayout());
    return newBuilder;
  }

  @Override
  public GridData createLayoutData() {
    return data == null ? null : copyData(data);
  }

  public T numColumns(int numColumns) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.numColumns = numColumns;
    return newBuilder;
  }

  public T makeColumnsEqualWidth() {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.makeColumnsEqualWidth = true;
    return newBuilder;
  }

  public T makeColumnsEqualWidth(boolean flag) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.makeColumnsEqualWidth = flag;
    return newBuilder;
  }

  public T marginWidth(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginWidth = margin;
    return newBuilder;
  }

  public T marginHeight(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginHeight = margin;
    return newBuilder;
  }

  public T margins(int marginWidth, int marginHeight) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginWidth = marginWidth;
    newBuilder.layout.marginHeight = marginHeight;
    return newBuilder;
  }

  public T noMargins() {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginWidth = 0;
    newBuilder.layout.marginHeight = 0;
    return newBuilder;
  }

  public T marginTop(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginTop = margin;
    return newBuilder;
  }

  public T marginLeft(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginLeft = margin;
    return newBuilder;
  }

  public T marginBottom(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginBottom = margin;
    return newBuilder;
  }

  public T marginRight(int margin) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginRight = margin;
    return newBuilder;
  }

  public T margins(int marginTop, int marginBottom, int marginLeft, int marginRight) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.marginTop = marginTop;
    newBuilder.layout.marginBottom = marginBottom;
    newBuilder.layout.marginLeft = marginLeft;
    newBuilder.layout.marginRight = marginRight;
    return newBuilder;
  }

  public T hSpacing(int spacing) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.horizontalSpacing = spacing;
    return newBuilder;
  }

  public T vSpacing(int spacing) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.verticalSpacing = spacing;
    return newBuilder;
  }

  public T spacing(int hSpacing, int vSpacing) {
    T newBuilder = flatCopyWithDeepCopyOfLayout();
    newBuilder.layout.horizontalSpacing = hSpacing;
    newBuilder.layout.verticalSpacing = vSpacing;
    return newBuilder;
  }

  public T fillH() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.FILL;
    return newBuilder;
  }

  public T fillV() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalAlignment = SWT.FILL;
    return newBuilder;
  }

  public T hAlignLeft() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.BEGINNING;
    return newBuilder;
  }

  public T hAlignCenter() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.CENTER;
    return newBuilder;
  }

  public T hAlignRight() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.END;
    return newBuilder;
  }

  public T vAlignTop() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalAlignment = SWT.TOP;
    return newBuilder;
  }

  public T vAlignCenter() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalAlignment = SWT.CENTER;
    return newBuilder;
  }

  public T vAlignBottom() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalAlignment = SWT.END;
    return newBuilder;
  }

  public T alignTopLeft() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.LEFT;
    newBuilder.data.verticalAlignment = SWT.TOP;
    return newBuilder;
  }

  public T alignTopCenter() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.CENTER;
    newBuilder.data.verticalAlignment = SWT.TOP;
    return newBuilder;
  }

  public T alignTopRight() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.RIGHT;
    newBuilder.data.verticalAlignment = SWT.TOP;
    return newBuilder;
  }

  public T alignTopFill() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalAlignment = SWT.FILL;
    newBuilder.data.verticalAlignment = SWT.TOP;
    return newBuilder;
  }

  public T widthHint(int widthHint) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.widthHint = widthHint;
    return newBuilder;
  }

  public T heightHint(int heightHint) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.heightHint = heightHint;
    return newBuilder;
  }

  public T hIndent(int indent) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalIndent = indent;
    return newBuilder;
  }

  public T vIndent(int indent) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalIndent = indent;
    return newBuilder;
  }

  public T indent(int hIndent, int vIndent) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalIndent = hIndent;
    newBuilder.data.verticalIndent = vIndent;
    return newBuilder;
  }

  public T exclude() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.exclude = true;
    return newBuilder;
  }

  public T hSpan(int columns) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalSpan = columns;
    return newBuilder;
  }

  public T vSpan(int rows) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.verticalSpan = rows;
    return newBuilder;
  }

  public T span(int columns, int rows) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.horizontalSpan = columns;
    newBuilder.data.verticalSpan = rows;
    return newBuilder;
  }

  public T spanAll() {
    return hSpan(((GridLayout) (getComposite().getLayout())).numColumns);
  }

  public T grabH() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessHorizontalSpace = true;
    return newBuilder;
  }

  public T grabV() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessVerticalSpace = true;
    return newBuilder;
  }

  public T grab() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessHorizontalSpace = true;
    newBuilder.data.grabExcessVerticalSpace = true;
    return newBuilder;
  }

  public T fillGrabH() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessHorizontalSpace = true;
    newBuilder.data.horizontalAlignment = SWT.FILL;
    return newBuilder;
  }

  public T fillGrabV() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessVerticalSpace = true;
    newBuilder.data.verticalAlignment = SWT.FILL;
    return newBuilder;
  }

  public T fillGrab() {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessHorizontalSpace = true;
    newBuilder.data.grabExcessVerticalSpace = true;
    newBuilder.data.horizontalAlignment = SWT.FILL;
    newBuilder.data.verticalAlignment = SWT.FILL;
    return newBuilder;
  }

  public T fillGrab(int minimumHeight) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.grabExcessHorizontalSpace = true;
    newBuilder.data.grabExcessVerticalSpace = true;
    newBuilder.data.horizontalAlignment = SWT.FILL;
    newBuilder.data.verticalAlignment = SWT.FILL;
    newBuilder.data.minimumHeight = minimumHeight;
    return newBuilder;
  }

  public T minWidth(int minimumWidth) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.minimumWidth = minimumWidth;
    return newBuilder;
  }

  public T minHeigth(int minimumHeight) {
    T newBuilder = flatCopyWithDeepCopyOfLayoutData();
    newBuilder.data.minimumHeight = minimumHeight;
    return newBuilder;
  }

  @Override
  protected void initNew() {
    super.initNew();
    initNewPrivate();
  }

  private void initNewPrivate() {
    data = null;
    layout = null;
  }

  private T flatCopyWithDeepCopyOfLayoutData() {
    T newBuilder = copy();
    newBuilder.data = deepCopyOfLayoutData();
    return newBuilder;
  }

  private GridData deepCopyOfLayoutData() {
    return data == null ? new GridData() : copyData(data);
  }

  private static GridData copyData(GridData data) {
    GridData copy = new GridData(data.horizontalAlignment, data.verticalAlignment, data.grabExcessHorizontalSpace,
        data.grabExcessVerticalSpace, data.horizontalSpan, data.verticalSpan);
    copy.widthHint = data.widthHint;
    copy.heightHint = data.heightHint;
    copy.horizontalIndent = data.horizontalIndent;
    copy.verticalIndent = data.verticalIndent;
    copy.minimumWidth = data.minimumWidth;
    copy.minimumHeight = data.minimumHeight;
    copy.exclude = data.exclude;
    return copy;
  }

  private T flatCopyWithDeepCopyOfLayout() {
    T newBuilder = copy();
    newBuilder.layout = deepCopyOfLayout();
    return newBuilder;
  }

  private GridLayout deepCopyOfLayout() {
    return layout == null ? new GridLayout() : copyLayout(layout);
  }

  private static GridLayout copyLayout(GridLayout layout) {
    GridLayout copy = new GridLayout(layout.numColumns, layout.makeColumnsEqualWidth);
    copy.horizontalSpacing = layout.horizontalSpacing;
    copy.marginBottom = layout.marginBottom;
    copy.marginHeight = layout.marginHeight;
    copy.marginLeft = layout.marginLeft;
    copy.marginRight = layout.marginRight;
    copy.marginTop = layout.marginTop;
    copy.marginWidth = layout.marginWidth;
    copy.verticalSpacing = layout.verticalSpacing;
    return copy;
  }
}
