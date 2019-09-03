package es.backend.meetup.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author lucia
 *
 */

public class MeetupActionEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5603396312298108522L;

	public enum Action {
		START,
		STOP
	} 
	
	private Action action;
	
	public MeetupActionEvent(Object source, Action action) {
		super(source);
		this.action = action;
	}

	/**
	 * @return the action
	 */
	public Action getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	
	@Override
	public String toString() {
		return "MeetupActionEvent [action=" + action + "]";
	}

}
