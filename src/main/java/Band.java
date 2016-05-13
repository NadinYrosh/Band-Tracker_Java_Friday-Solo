import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class Band {
  private String name;
  private int id;


  public Band(String name){
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public int getId() {
    return id;
  }

  public static List<Band> all() {
    String sql = "SELECT id, name FROM bands";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Band.class);
    }
  }

  @Override
  public boolean equals(Object otherBand) {
    if (!(otherBand instanceof Band)) {
      return false;
    } else {
      Band newBand =  (Band) otherBand;
      return this.getName().equals(newBand.getName()) &&
             this.getId() == newBand.getId();
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO bands (name) VALUES (:name)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("name", this.name)
        .executeUpdate()
        .getKey();
    }
  }

  public static Band find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM bands WHERE id = :id";
      Band band = con.createQuery(sql)
              .addParameter("id", id)
              .executeAndFetchFirst(Band.class);
      return band;
    }
  }

  // public void addVenue(Recipe newRecipe) {
  //   try(Connection con = DB.sql2o.open()) {
  //     String sql = "INSERT INTO categories_recipes (recipe_id, category_id) VALUES (:recipe, :category)";
  //     con.createQuery(sql)
  //       .addParameter("category", this.id)
  //       .addParameter("recipe",newRecipe.getId())
  //       .executeUpdate();
  //   }
  // }
  //
//   public List<Venue> getVenue() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "SELECT recipe_id FROM categories_recipes WHERE category_id = :category_id";
//
//       List<Integer> recipeIds =  con.createQuery(sql)
//         .addParameter("category_id", this.id)
//         .executeAndFetch(Integer.class);
//
//       List<Recipe> recipes = new ArrayList<Recipe>();
//
//       for (Integer recipe_id : recipeIds) {
//         String recipeQuery = "Select * FROM recipes WHERE id = :recipe_id";
//         Recipe tempRecipe = con.createQuery(recipeQuery)
//           .addParameter("recipe_id", recipe_id)
//           .executeAndFetchFirst(Recipe.class);
//         recipes.add(tempRecipe);
//       }
//       return recipes;
//     }
//   }
//
  public void update(String newName) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE bands SET name = :name WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .addParameter("name", newName)
        .executeUpdate();
    }
  }

  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM bands WHERE id = :id";
      con.createQuery(sql)
        .addParameter("id", this.id)
        .executeUpdate();

      String joinTableDelete = "DELETE FROM bands_venues WHERE band_id = :band_id";
      con.createQuery(joinTableDelete)
        .addParameter("band_id", this.id)
        .executeUpdate();
    }
  }
}
