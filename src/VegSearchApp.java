import client.core.ClientFactory;
import client.core.ModelFactory;
import client.core.ViewHandler;
import client.core.ViewModelFactory;
import javafx.application.Application;
import javafx.stage.Stage;

public class VegSearchApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ViewHandler vh = ViewHandler.getInstance();
        vh.start();
    }
}
