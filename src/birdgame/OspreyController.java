package birdgame;

public class OspreyController extends Controller{
	
	private OspreyModel model;
	private OspreyView view;
	
	public OspreyController() {
        view = new OspreyView();
        model = new OspreyModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
    }
    /**
     *start() will be called from the main() method in the Main class and will 
     * have a loop to iterate through the game.
     */
    public void start() {
    	model.spawnGamePieces();
    	while(model.getPlayer().isAlive()){
            model.handleTicks();
            view.update(model.getPlayer().getX(), model.getPlayer().getY(),model.getCurrentGPs(), model.getDirection(),model.getPlayer().getHealth(), model.getPlayer().getScore());
        }
    }
  public View getView() {
	return view;
  }

  public Model getModel() {
	return model;
  }

}
