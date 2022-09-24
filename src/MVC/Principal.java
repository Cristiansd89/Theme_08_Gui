package MVC;

public class Principal {

	public static void main(String[] args) {

		View view = new View();
		Controller controller = new Controller (view);
		view.conectaControlador(controller);
	}

}
