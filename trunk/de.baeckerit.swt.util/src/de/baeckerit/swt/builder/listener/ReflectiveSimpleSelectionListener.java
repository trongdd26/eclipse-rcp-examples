package de.baeckerit.swt.builder.listener;

import java.lang.reflect.Method;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

public class ReflectiveSimpleSelectionListener implements SelectionListener {
    
    private final Object target;
    private final Method method;
    
    public ReflectiveSimpleSelectionListener(Object target, String methodName) {
        this.target = target;
        try {
            this.method = target.getClass().getMethod(methodName, SelectionEvent.class);
        } catch (SecurityException e) {
            throw new IllegalArgumentException("Unable to call method: " + methodName, e);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("No such public method: " + methodName, e);
        }
    }
    
    @Override
    public void widgetDefaultSelected(SelectionEvent event) {
        throw new RuntimeException("widgetDefaultSelected on a push button");
    }

    @Override
    public void widgetSelected(SelectionEvent event) {
        invoke(event);
    }

    private void invoke(SelectionEvent event) {
        try {
            method.invoke(target, event);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
