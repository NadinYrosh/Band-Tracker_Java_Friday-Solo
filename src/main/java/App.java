import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;


public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("bands", Band.all());
      model.put("venues", Venue.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

//Band -----//

    post("/bands/form", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String input_band = request.queryParams("input_band");
      Band newBand = new Band(input_band);
      newBand.save();
      response.redirect("/");
      return null;
    });

    post("/bands/:id/update_form", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String inputUpdate = request.queryParams("update_band");
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      band.update(inputUpdate);
      response.redirect("/band/" + band.getId());
      return null;
    });

    get("/band/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      model.put("band", band);
      model.put("venues", Venue.all());
      model.put("template", "templates/band.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/band/:id/delete", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));
      band.delete();
      response.redirect("/");
      return null;
    });

    post("/band/:id/venue_to_band", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Band band = Band.find(Integer.parseInt(request.params(":id")));

      String stringId = request.queryParams("add_venue");
      Integer intId = Integer.parseInt(stringId);
      Venue venue = Venue.find(intId);
      band.addVenue(venue);

      response.redirect("/band/" + band.getId());
      return null;
    });

//Venue -----//

    post("/venues/form", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String input_venue = request.queryParams("input_venue");
      Venue newVenue = new Venue(input_venue);
      if ((newVenue.getName()).trim().length() != 0) {
        newVenue.save();
      }
      response.redirect("/");
      return null;
    });

    get("/venue/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Venue venue = Venue.find(Integer.parseInt(request.params(":id")));
      model.put("venue", venue);
      model.put("bands", Band.all());
      model.put("template", "templates/venue.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


  }
}
