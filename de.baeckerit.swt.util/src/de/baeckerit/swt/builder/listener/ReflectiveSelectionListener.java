package de.baeckerit.swt.builder.listener;

import java.lang.reflect.Method;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class ReflectiveSelectionListener implements SelectionListener {
    
    private final Object target;
    private final Method method;
    
    public ReflectiveSelectionListener(Object target, String methodName) {
        this.target = target;
        try {
            this.method = target.getClass().getMethod(methodName, SelectionEvent.class, boolean.class);
        } catch (SecurityException e) {
            throw new IllegalArgumentException("Unable to call method: " + methodName, e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("No such public method: " + methodName, e);
        }
    }
    
    @Override
    public void widgetDefaultSelected(SelectionEvent event) {
        invoke(event, true);
    }

    @Override
    public void widgetSelected(SelectionEvent event) {
        invoke(event, false);
    }

    private void invoke(SelectionEvent event, boolean isDefault) {
        try {
            method.invoke(target, event, isDefault);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
