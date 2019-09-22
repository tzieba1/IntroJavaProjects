package PolymorphismAndExceptionHandling.VehicleTesting;

public class Motorcycle extends Vehicle
  {
  public Motorcycle(int horsepower) { super(horsepower, "MotorCycle"); }
  
  @Override
  public int getRacingSpeed() { return (3 * getHorsePower()) / 2; }
  }
