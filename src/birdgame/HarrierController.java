//package birdgame;
//
//public class HarrierController extends Controller{
//	private HarrierModel model;
//	private HarrierView view;
//	
//	public HarrierController() {
//        view = new HarrierView();
//        model = new HarrierModel(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
//    }
//    /**
//     *start() will be called from the main() method in the Main class and will 
//     * have a loop to iterate through the game.
//     */
//    public void start() {
//    	model.spawnGamePieces();
//    	while(model.getPlayer().isAlive()){
//            model.handleTicks();
//            view.update(model.getPlayer().getX(), model.getPlayer().getY(),model.getCurrentGPs(), model.getDirection(),model.getPlayer().getHealth(), model.getPlayer().getScore());
//        }
//    }
//    
//    public View getView() {
//    	return view;
//      }
//
//      public Model getModel() {
//    	return model;
//      }
//
//}
