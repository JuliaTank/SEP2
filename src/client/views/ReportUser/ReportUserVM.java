package client.views.ReportUser;

import client.core.ModelFactory;
import client.model.VegSearchModel;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ReportUserVM {
    private VegSearchModel model;
    private StringProperty textAreaProperty;

    public ReportUserVM() throws IOException, NotBoundException {
        this.model = ModelFactory.getInstance().getModel();
        textAreaProperty = new SimpleStringProperty();
    }
    public StringProperty getAreaProperty() {
        return textAreaProperty;
    }
    public void report(String text) throws RemoteException {
        model.report(text);
    }
}
