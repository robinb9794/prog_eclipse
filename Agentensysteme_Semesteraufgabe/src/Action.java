public class Action {
	private String currentAction;
	private String color;

	public Action() {
	}

	public Action(String action) {
		setCurrentAction(action);
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void birth() {
		setCurrentAction("ANT_ACTION_BIRTH");
	}

	public void goUp() {
		setCurrentAction("ANT_ACTION_GOUP");
	}

	public void goDown() {
		setCurrentAction("ANT_ACTION_GODOWN");
	}

	public void goLeft() {
		setCurrentAction("ANT_ACTION_GOLEFT");
	}

	public void goRight() {
		setCurrentAction("ANT_ACTION_GORIGHT");
	}

	public void pick() {
		setCurrentAction("ANT_ACTION_PICK");
	}

	public void drop() {
		setCurrentAction("ANT_ACTION_DROP");
	}
}