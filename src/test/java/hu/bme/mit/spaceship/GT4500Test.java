package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private TorpedoStore mockPrimary;
  private TorpedoStore mockSecondary;
  private GT4500 ship;

  @BeforeEach
  public void init() {
    mockPrimary = mock(TorpedoStore.class);
    mockSecondary = mock(TorpedoStore.class);
    this.ship = new GT4500(mockPrimary, mockSecondary);
  }

  @Test
  public void fireTorpedo_Single_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_SinglePrimaryThenSecondary_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean resultPrimary = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultSecondary = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, resultPrimary);
    assertEquals(true, resultSecondary);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_SinglePrimaryEmpty_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_SingleBothEmpty_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockSecondary, times(0)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(0)).fire(1);
  }

  @Test
  public void fireTorpedo_SingleOneNotEmpty_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
  }

  @Test
  public void fireTorpedo_SingleNeitherFired_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_AllOneEmpty_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_SingleFirePrimaryAgain_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean resultFirst = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultSecond = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, resultFirst);
    assertEquals(true, resultSecond);
    verify(mockPrimary, times(2)).isEmpty();
    verify(mockPrimary, times(2)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_SingleFirePrimaryAgainButEmpty_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false).thenReturn(true);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean resultFirst = ship.fireTorpedo(FiringMode.SINGLE);
    boolean resultSecond = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, resultFirst);
    assertEquals(false, resultSecond);
    verify(mockPrimary, times(2)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
  }

  @Test
  public void fireTorpedo_AllBothSucceed_Success() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AllBothEmpty_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockSecondary, times(0)).isEmpty();
  }

  @Test
  public void fireTorpedo_AllOnlyPrimarySucceeds_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(true);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(false);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_AllOnlySecondarySucceeds_Failure() {
    // Arrange
    when(mockPrimary.isEmpty()).thenReturn(false);
    when(mockPrimary.fire(1)).thenReturn(false);
    when(mockSecondary.isEmpty()).thenReturn(false);
    when(mockSecondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(false, result);
    verify(mockPrimary, times(1)).isEmpty();
    verify(mockPrimary, times(1)).fire(1);
    verify(mockSecondary, times(1)).isEmpty();
    verify(mockSecondary, times(0)).fire(1);
  }

}
