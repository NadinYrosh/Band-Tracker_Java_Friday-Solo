import org.sql2o.*;
import org.junit.*;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.*;

public class VenueTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Venue_instantiatesCorrectly_true() {
    Venue myVenue = new Venue("Rose Garden");
    assertEquals(true, myVenue instanceof Venue);
  }

  @Test
  public void getName_instantiatesWithName_string() {
    Venue myVenue = new Venue("Rose Garden");
    assertEquals("Rose Garden", myVenue.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Venue.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNameAreTheSame_true() {
    Venue myVenue = new Venue("Rose Garden");
    Venue mySecondVenue = new Venue("Rose Garden");
    assertTrue(myVenue.equals(mySecondVenue));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    assertTrue(Venue.all().get(0).equals(myVenue));
  }

  // @Test
  // public void save_assignsIdToObject() {
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   Recipe savedRecipe = Recipe.all().get(0);
  //   assertEquals(myRecipe.getId(), savedRecipe.getId());
  // }

  // @Test
  // public void find_findsRecipesInDatabase_True() {
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   Recipe savedRecipe = Recipe.find(myRecipe.getId());
  //   assertTrue(myRecipe.equals(savedRecipe));
  // }
  //
  // @Test
  // public void update_updatesRecipes_true() {
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   myRecipe.update("Scone", "Bake a scone", 10);
  //   assertEquals("Scone", Recipe.find(myRecipe.getId()).getTitle());
  //   assertEquals("Bake a scone", Recipe.find(myRecipe.getId()).getInstructions());
  //   assertTrue(10 == Recipe.find(myRecipe.getId()).getRating());
  // }
  //
  // @Test
  // public void delete_deletesRecipe_true() {
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   int myRecipeId = myRecipe.getId();
  //   myRecipe.delete();
  //   assertEquals(null, Recipe.find(myRecipeId));
  // }
//------------------------------------------------------------------------//
  // @Test
  // public void addBand_addsBandToVenue() {
  //   Category myCategory = new Category("Dinner");
  //   myCategory.save();
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   myRecipe.addCategory(myCategory);
  //   Category savedCategory = myRecipe.getCategories().get(0);
  //   assertTrue(myCategory.equals(savedCategory));
  // }
  //
  // @Test
  // public void getBands_returnsAllBands_List() {
  //   Category myCategory = new Category("Dinner");
  //   myCategory.save();
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   myRecipe.addCategory(myCategory);
  //   List savedCategories = myRecipe.getCategories();
  //   assertEquals(1, savedCategories.size());
  // }

  // @Test
  // public void delete_deletesAllRecipeAndCategoryAssociations() {
  //   Category myCategory = new Category("Dinner");
  //   myCategory.save();
  //   Recipe myRecipe = new Recipe("Pie", "Bake a pie", 5);
  //   myRecipe.save();
  //   myRecipe.addCategory(myCategory);
  //   myRecipe.delete();
  //   assertEquals(0, myRecipe.getCategories().size());
  // }
}
