package com.utilities;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

public class GetCoordinates {
	
	public static Point getCoordinatesWithinElement(WebElement element, double xPercentage, double yPercentage) {
        Dimension size = element.getSize();
        int xOffset = (int) (size.getWidth() * xPercentage);
        int yOffset = (int) (size.getHeight() * yPercentage);
        return new Point(xOffset, yOffset);
    }
}
