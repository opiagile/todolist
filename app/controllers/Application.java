package controllers;

import java.sql.Connection;

import javax.sql.DataSource;

import play.mvc.*;
import play.data.*;
import models.*;
import views.html.*;
import play.db.*;

public class Application extends Controller {

	static Form<Task> taskForm = Form.form(Task.class);
	Connection connection = DB.getConnection();
	DataSource ds = DB.getDataSource();
	
    public static Result index() {
        //return ok(index.render("Olá Mundo!", taskForm));
    	return TODO;
    }

    public static Result tasks() {
    	return ok(index.render(Task.all(), taskForm));
    }
      
    public static Result newTask() {
    	Form<Task> filledForm = taskForm.bindFromRequest();
    	if(filledForm.hasErrors()) {
    		return badRequest(views.html.index.render(Task.all(), filledForm));
    	} else {
    		Task.create(filledForm.get());
    	    return redirect(routes.Application.tasks());  
    	}
    }
      
    public static Result deleteTask(Long id) {
    	Task.delete(id);
    	return redirect(routes.Application.tasks());
	}
         
}
