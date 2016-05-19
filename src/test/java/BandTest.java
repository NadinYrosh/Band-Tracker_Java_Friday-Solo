import java.util.List;
import org.junit.*;
import java.util.ArrayList;
import org.sql2o.*;
import static org.junit.Assert.*;

public class BandTest {


  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Band_instantiatesCorrectly_true() {
    Band myBand = new Band("Lotus");
    assertEquals(true, myBand instanceof Band);
  }

  @Test
  public void getName_instantiatesWithBand_string() {
    Band myBand = new Band("Lotus");
    assertEquals("Lotus", myBand.getName());
  }

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Band.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame_true() {
    Band myBand = new Band("Lotus");
    Band mySecondBand = new Band("Lotus");
    assertTrue(myBand.equals(mySecondBand));
  }

  @Test
  public void save_savesObjectIntoDatabase_true() {
    Band myBand = new Band("Lotus");
    myBand.save();
    assertTrue(Band.all().get(0).equals(myBand));
  }

  @Test
  public void save_assignsIdToObject() {
    Band myBand = new Band("Lotus");
    myBand.save();
    Band savedBand = Band.all().get(0);
    assertEquals(myBand.getId(), savedBand.getId());
  }

  @Test
  public void find_findsBandssInDatabase_True() {
    Band myBand = new Band("Lotus");
    myBand.save();
    Band savedBand = Band.find(myBand.getId());
    assertTrue(myBand.equals(savedBand));
  }

  @Test
  public void update_updatesRecipes_true() {
    Band myBand = new Band("Lotus");
    myBand.save();
    myBand.update("Imagine Dragons");
    assertEquals("Imagine Dragons", Band.find(myBand.getId()).getName());
  }

  @Test
  public void delete_deletesBand_true() {
    Band myBand = new Band("Lotus");
    myBand.save();
    int myBandId = myBand.getId();
    myBand.delete();
    assertEquals(null, Band.find(myBandId));
  }

  @Test
  public void addVenue_addsVenueToBand() {
    Band myBand = new Band("Lotus");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myBand.addVenue(myVenue);
    Venue savedVanue = myBand.getVenues().get(0);
    assertTrue(myVenue.equals(savedVanue));
  }

  @Test
  public void getVenues_returnsAllVenues_List() {
    Band myBand = new Band("Lotus");
    myBand.save();
    Venue myVenue = new Venue("Rose Garden");
    myVenue.save();
    myBand.addVenue(myVenue);
    List savedVenues = myBand.getVenues();
    assertEquals(1, savedVenues.size());
  }
}
