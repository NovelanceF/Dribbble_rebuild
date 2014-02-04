package nl.lance.dribbb.views;


public class FooterState {
  
  protected State mState = State.Idle;
  public static enum State {
    Idle, TheEnd, Loading
  }
  
  public FooterState() {
    this.mState = State.Idle;
  }
  
  public State getState() {
    return mState;
  }
  
  public void setState(State status) {
    if(mState == status) {
      return;
    }
    mState = status;
  }

}
