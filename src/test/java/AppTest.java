import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.assertj.core.api.Assertions.assertThat;
import org.sql2o.*;
import org.junit.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Band Tracker");
  }

  @Test
  public void BandIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#input_band").with("Lotus");
    submit("#save_band");
    assertThat(pageSource()).contains("Lotus");
  }

  @Test
  public void VenueIsCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#input_venue").with("Rose park");
    submit("#add_venue");
    assertThat(pageSource()).contains("Rose park");
  }

  @Test
  public void bandIsCreatedAndDisplayedTest() {
   goTo("http://localhost:4567/");
   fill("#input_band").with("Lotus");
   submit("#save_band");
   assertThat(pageSource()).contains("Lotus");
  }

  @Test
  public void venueIsCreatedAndDisplayedTest() {
   goTo("http://localhost:4567/");
   fill("#input_venue").with("Rose Park");
   submit("#add_venue");
   assertThat(pageSource()).contains("Rose Park");
  }

  @Test
  public void bandShowPageDisplaysName() {
   Band testBand = new Band("Lotus");
   testBand.save();
   String url = String.format("http://localhost:4567/band/%d", testBand.getId());
   goTo(url);
   assertThat(pageSource()).contains("Lotus");
 }

 @Test
 public void venueIsAddedToBandTest() {
   Band testBand = new Band("Lotus");
   testBand.save();
   Venue testVenue = new Venue("Rose Garden");
   testVenue.save();
   String url = String.format("http://localhost:4567/band/%d", testBand.getId());
   goTo(url);
   click("option", withText("Rose Garden"));
   submit("#add_venue_btn");
   assertThat(pageSource()).contains("Rose Garden");
 }

 @Test
 public void venueShowPageDisplaysName() {
  Venue testVenue = new Venue("Rose Garden");
  testVenue.save();
  String url = String.format("http://localhost:4567/venue/%d", testVenue.getId());
  goTo(url);
  assertThat(pageSource()).contains("Rose Garden");
}



}
