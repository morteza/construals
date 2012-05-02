package controllers;

import models.ConstrualData;
import play.mvc.*;

public class Construals extends Controller {

    public static void index() {
        render();
    }

    public static void getConstrualData(String type) {
    	renderJSON(ConstrualData.findDataByType(type));
    }
}
