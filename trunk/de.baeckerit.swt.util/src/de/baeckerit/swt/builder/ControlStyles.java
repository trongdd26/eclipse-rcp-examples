package de.baeckerit.swt.builder;

import org.eclipse.swt.SWT;

public class ControlStyles implements IControlStyles {

  public static ControlStyles DEFAULT_STYLES = new ControlStyles();

  @Override
  public int getCompositeStyle() {
    return SWT.NONE;
  }

  @Override
  public int getGroupStyle() {
    return SWT.SHADOW_IN;
  }

  @Override
  public int getShellStyle() {
    return SWT.SHELL_TRIM;
  }

  @Override
  public int getLabelStyle() {
    return SWT.NONE;
  }

  @Override
  public int getLinkStyle() {
    return SWT.NONE;
  }

  @Override
  public int getListStyle() {
    return SWT.BORDER;
  }

  @Override
  public int getTextStyle() {
    return SWT.BORDER | SWT.SINGLE;
  }

  @Override
  public int getTextAreaStyle() {
    return SWT.BORDER | SWT.MULTI;
  }

  @Override
  public int getStyledTextStyle() {
    return SWT.BORDER | SWT.SINGLE;
  }

  @Override
  public int getToggleButtonStyle() {
    return SWT.TOGGLE;
  }

  @Override
  public int getPushButtonStyle() {
    return SWT.PUSH;
  }

  @Override
  public int getArrowButtonStyle() {
    return SWT.ARROW;
  }

  @Override
  public int getRadioButtonStyle() {
    return SWT.RADIO;
  }

  @Override
  public int getCheckBoxStyle() {
    return SWT.CHECK;
  }

  @Override
  public int getDateTimeStyle() {
    return SWT.NONE;
  }

  @Override
  public int getCanvasStyle() {
    return SWT.NONE;
  }

  @Override
  public int getComboStyle() {
    return SWT.NONE;
  }

  @Override
  public int getCoolBarStyle() {
    return SWT.NONE;
  }

  @Override
  public int getExpandBarStyle() {
    return SWT.NONE;
  }

  @Override
  public int getExpandItemStyle() {
    return SWT.NONE;
  }

  @Override
  public int getProgressBarStyle() {
    return SWT.NONE;
  }

  @Override
  public int getScaleStyle() {
    return SWT.NONE;
  }

  @Override
  public int getSliderStyle() {
    return SWT.NONE;
  }

  @Override
  public int getSpinnerStyle() {
    return SWT.NONE;
  }

  @Override
  public int getTabFolderStyle() {
    return SWT.NONE;
  }

  @Override
  public int getTabItemStyle() {
    return SWT.NONE;
  }

  @Override
  public int getTableStyle() {
    return SWT.NONE;
  }

  @Override
  public int getTableColumnStyle() {
    return SWT.NONE;
  }

  @Override
  public int getToolBarStyle() {
    return SWT.NONE;
  }

  @Override
  public int getToolItemStyle() {
    return SWT.NONE;
  }

  @Override
  public int getTreeStyle() {
    return SWT.NONE;
  }
}
