package de.baeckerit.jface.examples.databinding.portfolio;

import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Map;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import de.baeckerit.jface.examples.databinding.portfolio.data.Security;
import de.baeckerit.jface.examples.databinding.portfolio.data.SecurityPosition;

public class EventHandling {

  private static final String TOPIC_NEW_SECURITY = "portMan/new/security";
  private static final String TOPIC_NEW_SECURITY_POSITION = "portMan/new/securityPosition";

  public static void postNewSecurityEvent(Security newSecurity) {
    postEvent(newSecurity, TOPIC_NEW_SECURITY);
  }

  public static void postNewSecurityPositionEvent(SecurityPosition newSecurityPosition) {
    postEvent(newSecurityPosition, TOPIC_NEW_SECURITY_POSITION);
  }

  public static void addNewSecurityEventHandler(EventHandler handler) {
    addEventHandler(handler, TOPIC_NEW_SECURITY);
  }

  public static void addNewSecurityPositionEventHandler(EventHandler handler) {
    addEventHandler(handler, TOPIC_NEW_SECURITY_POSITION);
  }

  public static Security extractNewSecurity(Event event) {
    return (Security) event.getProperty(TOPIC_NEW_SECURITY);
  }

  public static SecurityPosition extractNewSecurityPosition(Event event) {
    return (SecurityPosition) event.getProperty(TOPIC_NEW_SECURITY_POSITION);
  }

  private static void postEvent(Object param, String topic) {
    Map<String, ?> properties = Collections.singletonMap(topic, param);
    PmActivator.getDefault().getEventAdmin().postEvent(new Event(topic, properties));
  }

  private static void addEventHandler(EventHandler handler, String topic) {
    String[] topics = new String[] { EventConstants.EVENT_TOPIC, topic };

    Dictionary<String, Object> ht = new Hashtable<String, Object>();
    ht.put(EventConstants.EVENT_TOPIC, topics);

    PmActivator.getDefault().getBundleContext().registerService(EventHandler.class.getName(), handler, ht);
  }
}
